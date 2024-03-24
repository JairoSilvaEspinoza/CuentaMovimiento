package com.reto.CuentaMovimiento.repositories;

import com.reto.CuentaMovimiento.models.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    List<Cuenta> findByPersonaId(Long personaId);
}
