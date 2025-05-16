package com.siscon.crud.service;

import java.util.List;

import com.siscon.crud.dto.EmpleadoDTO;

/**
 * The Interface EmpleadoService.
 * 
 * @author ajuarez
 */
public interface EmpleadoService {
	
	/**
	 * Obtener todos.
	 *
	 * @return the list
	 */
	List<EmpleadoDTO> findAll();

	/**
	 * Obtener por id.
	 *
	 * @param id the id
	 * @return the empleado response DTO
	 */
	EmpleadoDTO findById(Long id);

	/**
	 * Guardar.
	 *
	 * @param empleadoDTO the empleado DTO
	 * @return the empleado response DTO
	 */
	EmpleadoDTO save(EmpleadoDTO empleadoDTO);
	
	
	/**
	 * Save.
	 *
	 * @param empleadosDTO the empleados DTO
	 * @return the list
	 */
	List<EmpleadoDTO> save(List<EmpleadoDTO> empleadosDTO);

	/**
	 * Actualizar.
	 *
	 * @param id the id
	 * @param empleadoDTO the empleado DTO
	 * @return the empleado response DTO
	 */
	EmpleadoDTO update(Long id, EmpleadoDTO empleadoDTO);

	/**
	 * Eliminar.
	 *
	 * @param id the id
	 */
	void delete(Long id);
}
