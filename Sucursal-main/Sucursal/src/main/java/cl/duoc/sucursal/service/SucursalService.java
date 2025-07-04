package cl.duoc.sucursal.service;

import cl.duoc.sucursal.model.Sucursal;
import cl.duoc.sucursal.repository.SucursalRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class SucursalService {

    private SucursalRepository sucursalRepository;

    public SucursalService(SucursalRepository sucursalRepository) {
        this.sucursalRepository = sucursalRepository;
    }

    public List<Sucursal> buscarSucursales() {
        return sucursalRepository.findAll();
    }

    public Sucursal buscarSucursal(Long idSucursal) {
        return sucursalRepository.findById(idSucursal).get();
    }

    public Sucursal guardarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public void eliminarSucursal(Long idSucursal) {
        sucursalRepository.deleteById(idSucursal);
    }




}
