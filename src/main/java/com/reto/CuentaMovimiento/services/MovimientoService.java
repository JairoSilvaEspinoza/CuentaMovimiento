package com.reto.CuentaMovimiento.services;

import com.reto.CuentaMovimiento.models.Cuenta;
import com.reto.CuentaMovimiento.models.Movimiento;
import com.reto.CuentaMovimiento.models.dtos.MovimientoDto;
import com.reto.CuentaMovimiento.models.dtos.ReporteClienteMovDto;
import com.reto.CuentaMovimiento.models.mappers.MovimientoMaper;
import com.reto.CuentaMovimiento.repositories.CuentaRepository;
import com.reto.CuentaMovimiento.repositories.MovimientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;
    @Autowired
    private CuentaRepository cuentaRepository;

    public List<Movimiento> getAllMovimientos() {
        return movimientoRepository.findAll();
    }



    @Transactional
    public MovimientoDto saveMovimiento(MovimientoDto movimientoDto) {
        Cuenta cuenta = cuentaRepository.findById(movimientoDto.getCuentaId()).orElseThrow(() ->
                new RuntimeException("Cuenta no encontrada"));
        BigDecimal nuevoSaldo = cuenta.getSaldo().add(movimientoDto.getValor());
        if(nuevoSaldo.compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("Saldo insuficiente");
        }
        cuenta.setSaldo(nuevoSaldo);
        cuentaRepository.save(cuenta);
        movimientoDto.setSaldo(nuevoSaldo);
        Movimiento movimiento = MovimientoMaper.INSTANCE.dtoToMovimiento(movimientoDto);
        movimiento.setCuenta(cuenta);
        movimientoRepository.save(movimiento);
        movimientoDto.setId(movimiento.getId());
        movimientoDto.setNumeroCuenta(cuenta.getNumeroCuenta());
        return movimientoDto ;
    }


    public List<ReporteClienteMovDto> getReportByPersonaId(Date fechaInicio, Date fechaFin, Long personaId) {
        if (fechaInicio.equals(fechaFin)){
            LocalDateTime localDateTime = fechaInicio.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();
            fechaFin = Date.from(localDateTime
                    .with(LocalTime.MAX)
                    .atZone(ZoneId.systemDefault())
                    .toInstant());
        }
        return movimientoRepository.getReportByPersonaId(fechaInicio, fechaFin, personaId);
    }
}