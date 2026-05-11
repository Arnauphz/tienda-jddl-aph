package es.iesclaradelrey.da2d1a.tiendajddlaph.api.controllers;

import es.iesclaradelrey.da2d1a.tiendajddlaph.api.service.ProductoXmlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequestMapping("/api/v1/xml")
public class ProductoXmlController {

    private final ProductoXmlService productoXmlService;

    public ProductoXmlController(ProductoXmlService productoXmlService) {
        this.productoXmlService = productoXmlService;
    }

    // 3.2 — GET /api/v1/xml — Exportar productos como XML descargable
    @GetMapping
    public ResponseEntity<byte[]> exportar() throws Exception {
        byte[] xml = productoXmlService.exportarProductosXml();

        String timestamp = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd.HH-mm"));
        String filename = "products-export." + timestamp + ".xml";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/xml;charset=UTF-8"))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(xml);
    }

    // 3.3 — POST /api/v1/xml — Importar productos desde XML
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> importar(
            @RequestParam("productsfile") MultipartFile file) throws Exception {

        int total = productoXmlService.importarProductosXml(file);
        return ResponseEntity.ok("Importados correctamente " + total + " productos.");
    }
}