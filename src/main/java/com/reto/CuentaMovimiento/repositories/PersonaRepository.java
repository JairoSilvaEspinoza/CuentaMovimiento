package com.reto.CuentaMovimiento.repositories;

import com.reto.CuentaMovimiento.models.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {
}
