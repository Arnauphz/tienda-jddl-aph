package es.iesclaradelrey.da2d1a.tiendajddlaph.api.service;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions.CategoriaNotFoundException;
import es.iesclaradelrey.da2d1a.tiendajddlaph.api.exceptions.MarcaNotFoundException;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.CategoriaRepository;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.MarcaRepository;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.ProductoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoXmlService {

    private final ProductoRepository productoRepository;
    private final MarcaRepository marcaRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoXmlService(ProductoRepository productoRepository,
                              MarcaRepository marcaRepository,
                              CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.marcaRepository = marcaRepository;
        this.categoriaRepository = categoriaRepository;
    }

    // -------------------------------------------------------
    // EXPORTACIÓN con DOM
    // -------------------------------------------------------
    public byte[] exportarProductosXml() throws Exception {
        List<Producto> productos = productoRepository.findAll(Sort.by("nombre"));

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.newDocument();

        // Elemento raíz
        Element root = doc.createElement("productos");
        doc.appendChild(root);

        for (Producto p : productos) {
            Element prodEl = doc.createElement("producto");

            // Datos básicos
            addElement(doc, prodEl, "id",          String.valueOf(p.getId()));
            addElement(doc, prodEl, "codigo",       p.getCodigo());
            addElement(doc, prodEl, "nombre",       p.getNombre());
            addElement(doc, prodEl, "descripcion",  p.getDescripcion());
            addElement(doc, prodEl, "precio",       String.valueOf(p.getPrecio()));
            addElement(doc, prodEl, "descuento",    String.valueOf(p.getDescuento()));
            addElement(doc, prodEl, "stock",        String.valueOf(p.getStock()));
            addElement(doc, prodEl, "imagen",       p.getImagen() != null ? p.getImagen() : "");

            // Marca
            Element marcaEl = doc.createElement("marca");
            addElement(doc, marcaEl, "id",     String.valueOf(p.getMarca().getId()));
            addElement(doc, marcaEl, "nombre", p.getMarca().getNombre());
            prodEl.appendChild(marcaEl);

            // Categorías
            Element categoriasEl = doc.createElement("categorias");
            for (Categoria c : p.getCategorias()) {
                Element catEl = doc.createElement("categoria");
                addElement(doc, catEl, "id",     String.valueOf(c.getId()));
                addElement(doc, catEl, "nombre", c.getNombre());
                categoriasEl.appendChild(catEl);
            }
            prodEl.appendChild(categoriasEl);

            root.appendChild(prodEl);
        }

        // Serializar a bytes
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer = tf.newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        transformer.transform(new DOMSource(doc), new StreamResult(out));
        return out.toByteArray();
    }

    private void addElement(Document doc, Element parent, String tag, String value) {
        Element el = doc.createElement(tag);
        el.setTextContent(value);
        parent.appendChild(el);
    }

    // -------------------------------------------------------
    // IMPORTACIÓN con SAX
    // -------------------------------------------------------
    @Transactional
    public int importarProductosXml(MultipartFile file) throws Exception {
        InputStream is = file.getInputStream();

        // Parseamos con SAX y recogemos los datos en una lista
        ProductoSaxHandler handler = new ProductoSaxHandler();
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxFactory.newSAXParser();
        saxParser.parse(is, handler);

        List<ProductoImportData> datos = handler.getProductos();

        // Validamos y persistimos dentro de la misma transacción
        for (ProductoImportData data : datos) {

            Marca marca = marcaRepository.findById(data.marcaId)
                    .orElseThrow(() -> new MarcaNotFoundException(data.marcaId));

            List<Categoria> categorias = new ArrayList<>();
            for (Long catId : data.categoriaIds) {
                Categoria cat = categoriaRepository.findById(catId)
                        .orElseThrow(() -> new CategoriaNotFoundException(catId));
                categorias.add(cat);
            }

            Producto producto = Producto.builder()
                    .codigo(data.codigo)
                    .nombre(data.nombre)
                    .descripcion(data.descripcion)
                    .precio(data.precio)
                    .descuento(data.descuento)
                    .stock(data.stock)
                    .imagen(data.imagen)
                    .marca(marca)
                    .categorias(categorias)
                    .build();

            productoRepository.save(producto);
        }

        return datos.size();
    }

    // -------------------------------------------------------
    // Clase interna: datos planos de un producto leído por SAX
    // -------------------------------------------------------
    private static class ProductoImportData {
        String codigo;
        String nombre;
        String descripcion;
        Double precio;
        Integer descuento;
        Integer stock;
        String imagen;
        Long marcaId;
        List<Long> categoriaIds = new ArrayList<>();
    }

    // -------------------------------------------------------
    // Handler SAX
    // -------------------------------------------------------
    private static class ProductoSaxHandler extends DefaultHandler {

        private final List<ProductoImportData> productos = new ArrayList<>();
        private ProductoImportData current;
        private boolean enMarca = false;
        private boolean enCategoria = false;
        private Long currentCategoriaId;
        private final StringBuilder sb = new StringBuilder();

        public List<ProductoImportData> getProductos() {
            return productos;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {
            sb.setLength(0); // Limpiamos el buffer en cada elemento
            switch (qName) {
                case "producto"  -> current = new ProductoImportData();
                case "marca"     -> enMarca = true;
                case "categoria" -> {
                    enCategoria = true;
                    currentCategoriaId = null;
                }
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) {
            sb.append(ch, start, length);
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            String text = sb.toString().trim();

            if (enMarca) {
                switch (qName) {
                    case "id"     -> current.marcaId = Long.parseLong(text);
                    case "marca"  -> enMarca = false;
                }
                return;
            }

            if (enCategoria) {
                switch (qName) {
                    case "id"        -> currentCategoriaId = Long.parseLong(text);
                    case "categoria" -> {
                        if (currentCategoriaId != null) {
                            current.categoriaIds.add(currentCategoriaId);
                        }
                        enCategoria = false;
                    }
                }
                return;
            }

            // Campos del producto
            switch (qName) {
                case "codigo"      -> current.codigo      = text;
                case "nombre"      -> current.nombre      = text;
                case "descripcion" -> current.descripcion = text;
                case "precio"      -> current.precio      = Double.parseDouble(text);
                case "descuento"   -> current.descuento   = Integer.parseInt(text);
                case "stock"       -> current.stock       = Integer.parseInt(text);
                case "imagen"      -> current.imagen      = text.isEmpty() ? null : text;
                case "producto"    -> productos.add(current);
            }
        }
    }
}