package com.reto.CuentaMovimiento.controllers;

import com.reto.CuentaMovimiento.models.Cuenta;
import com.reto.CuentaMovimiento.models.dtos.CuentaDto;
import com.reto.CuentaMovimiento.services.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/usuario/{id}")
    public List<CuentaDto> getAllCuentasById(@PathVariable Long id) {
        return cuentaService.getAllCuentasById(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cuenta> getCuentaById(@PathVariable Long id) {
        return cuentaService.getCuentaById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    @PostMapping
    public CuentaDto createCuenta(@RequestBody CuentaDto cuenta) {
        return cuentaService.saveCuenta(cuenta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCuenta(@PathVariable Long id, @RequestBody CuentaDto cuenta) {
        return cuentaService.getCuentaById(id)
                .map(cuentaExistente -> {
                    cuentaService.updateEstado(id, cuenta.getEstado());
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCuenta(@PathVariable Long id) {
        return cuentaService.getCuentaById(id)
                .map(cuenta -> {
                    cuentaService.deleteCuenta(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
