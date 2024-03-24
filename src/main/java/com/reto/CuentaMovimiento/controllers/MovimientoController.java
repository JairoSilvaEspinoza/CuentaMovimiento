package com.reto.CuentaMovimiento.controllers;

import com.reto.CuentaMovimiento.models.Movimiento;
import com.reto.CuentaMovimiento.models.dtos.MovimientoDto;
import com.reto.CuentaMovimiento.models.dtos.ReporteClienteMovDto;
import com.reto.CuentaMovimiento.services.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @GetMapping
    public List<Movimiento> getAllMovimientos() {
        return movimientoService.getAllMovimientos();
    }



    @PostMapping
    public MovimientoDto createMovimiento(@RequestBody MovimientoDto movimiento) {
        return movimientoService.saveMovimiento(movimiento);
    }

    @GetMapping("/reportes")
    public List<ReporteClienteMovDto> getMovimientosByDateRangeAndClienteId(
            @RequestParam("startDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaInicio,
            @RequestParam("endDate") @DateTimeFormat(pattern = "dd/MM/yyyy") Date fechaFin,
            @RequestParam("clienteId") Long personaId) {
        return movimientoService.getReportByPersonaId(fechaInicio, fechaFin, personaId);
    }



}
