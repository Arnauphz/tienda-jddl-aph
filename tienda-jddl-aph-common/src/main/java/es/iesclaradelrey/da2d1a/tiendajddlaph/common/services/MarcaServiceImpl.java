package es.iesclaradelrey.da2d1a.tiendajddlaph.common.services;

import es.iesclaradelrey.da2d1a.tiendajddlaph.common.entities.Marca;
import es.iesclaradelrey.da2d1a.tiendajddlaph.common.repositories.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Override
    public void guardar(Marca entity) {
        marcaRepository.save(entity);
    }

    @Override
    public Iterable<Marca> findAll() {
        return marcaRepository.findAll();
    }

    @Override
    public Marca findById(Long id) {
        return marcaRepository.findById(id).orElse(null);
    }
}