package com.siscon.crud.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.siscon.crud.model.Empleados;

/**
 * The Interface EmpleadoRepository.
 *
 * @author ajuarez
 */
@Repository
public interface EmpleadoRepository extends JpaRepository<Empleados, Long>{

}
