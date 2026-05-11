package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Categoria;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Producto;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    private ProductoRepository productoRepository;
    @Autowired
    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void guardar(Producto entity) {
        productoRepository.save(entity);
    }

    @Override
    public Producto findById(Long id){
            return productoRepository.findById(id).orElse(null);
    }

    @Override
    public Iterable<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
