package com.reto.CuentaMovimiento.models.mappers;

import com.reto.CuentaMovimiento.models.Cuenta;
import com.reto.CuentaMovimiento.models.dtos.CuentaDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CuentaMapper {
    CuentaMapper INSTANCE = Mappers.getMapper(CuentaMapper.class);

    Cuenta dtoToCuenta(CuentaDto clienteDto);
    CuentaDto cuentaToDto(Cuenta cuenta);
}
