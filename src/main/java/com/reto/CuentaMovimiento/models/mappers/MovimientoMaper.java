package com.reto.CuentaMovimiento.models.mappers;

import com.reto.CuentaMovimiento.models.Movimiento;
import com.reto.CuentaMovimiento.models.dtos.MovimientoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MovimientoMaper {

    MovimientoMaper INSTANCE = Mappers.getMapper(MovimientoMaper.class);

    MovimientoDto movimientoToDto(Movimiento movimiento);
    Movimiento dtoToMovimiento(MovimientoDto movimientoDto);

}
