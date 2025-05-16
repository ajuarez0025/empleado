package com.siscon.crud.service;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.siscon.crud.dto.EmpleadoDTO;
import com.siscon.crud.exception.ServiceException;
import com.siscon.crud.model.Empleados;
import com.siscon.crud.repository.EmpleadoRepository;
import com.siscon.crud.transformer.EmpleadoTransformer;
import com.siscon.crud.util.DateUtil;

import lombok.extern.slf4j.Slf4j;
import lombok.AllArgsConstructor;

/**
 * The Class EmpleadoServiceImpl.
 * 
 * @author ajuarez
 */
@Service
@Slf4j
@AllArgsConstructor
public class EmpleadoServiceImpl implements EmpleadoService {

	/** The empleado repository. */
	private final EmpleadoRepository empleadoRepository;

	/** The empleado transformer. */
	private final EmpleadoTransformer empleadoTransformer;

	
	/**
	 * Find all.
	 *
	 * @return the list
	 */
	@Override
	@Transactional(readOnly = true)
	public List<EmpleadoDTO> findAll() {
		log.info("Obteniendo todos los empleados");
		return empleadoRepository.findAll().stream().map(empleadoTransformer::entityToDto).toList();
	}

	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the empleado DTO
	 */
	@Override
	@Transactional(readOnly = true)
	public EmpleadoDTO findById(Long id) {
		log.info("Buscando empleado con ID: {}", id);
		return empleadoRepository.findById(id).map(empleadoTransformer::entityToDto)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "No se encontro el id " + id));
	}

	/**
	 * Save.
	 *
	 * @param empleadoDTO the empleado DTO
	 * @return the empleado DTO
	 */
	@Override
	@Transactional
	public EmpleadoDTO save(EmpleadoDTO empleadoDTO) {
		log.info("Guardando nuevo empleado: {}", empleadoDTO.getPrimerNombre());
		Empleados empleado = empleadoTransformer.dtoToEntity(empleadoDTO);
		return empleadoTransformer.entityToDto(empleadoRepository.save(empleado));
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param empleadoDTO the empleado DTO
	 * @return the empleado DTO
	 */
	@Override
	@Transactional
	public EmpleadoDTO update(Long id, EmpleadoDTO empleadoDTO) {

		log.info("Actualizando empleado con ID: {}", id);

		Empleados empleado = empleadoRepository.findById(id)
				.orElseThrow(() -> new ServiceException(HttpStatus.NOT_FOUND, "No se encontro: " + id));

		if (empleadoDTO.getPrimerNombre() != null) {
			empleado.setPrimerNombre(empleadoDTO.getPrimerNombre());
		}
		if (empleadoDTO.getSegundoNombre() != null) {
			empleado.setSegundoNombre(empleadoDTO.getSegundoNombre());
		}
		if (empleadoDTO.getApellidoPaterno() != null) {
			empleado.setApellidoPaterno(empleadoDTO.getApellidoPaterno());
		}
		if (empleadoDTO.getApellidoMaterno() != null) {
			empleado.setApellidoMaterno(empleadoDTO.getApellidoMaterno());
		}
		if (empleadoDTO.getEdad() != null) {
			empleado.setEdad(empleadoDTO.getEdad());
		}
		if (empleadoDTO.getSexo() != null) {
			empleado.setSexo(empleadoDTO.getSexo());
		}
		if (empleadoDTO.getFechaNacimiento() != null) {
			empleado.setFechaNacimiento(DateUtil.toLocalDate(empleadoDTO.getFechaNacimiento()));
		}
		if (empleadoDTO.getPuesto() != null) {
			empleado.setPuesto(empleadoDTO.getPuesto());
		}

		return empleadoTransformer.entityToDto(empleadoRepository.save(empleado));
	}

	
	/**
	 * Delete.
	 *
	 * @param id the id
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		log.info("Eliminando empleado con ID: {}", id);
		if (!empleadoRepository.existsById(id)) {
			throw new ServiceException(HttpStatus.NOT_FOUND, "No se encontro el id: " + id);
		}
		empleadoRepository.deleteById(id);
	}

	/**
	 * Save.
	 *
	 * @param empleadosDTO the empleados DTO
	 * @return the list
	 */
	@Override
	@Transactional
	public List<EmpleadoDTO> save(List<EmpleadoDTO> empleadosDTO) {
		log.info("Guardando {} empleados", empleadosDTO.size());
		List<Empleados> empleados = empleadoTransformer.dtoToEntity(empleadosDTO);

		empleados = empleadoRepository.saveAll(empleados);
		return empleadoTransformer.entityToDto(empleados);
	}
}
