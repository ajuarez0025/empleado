package com.siscon.crud.transformer;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.siscon.crud.dto.EmpleadoDTO;
import com.siscon.crud.model.Empleados;
import com.siscon.crud.util.DateUtil;

/**
 * The Class EmpleadoTransformer.
 * 
 * @author ajuarez
 */
@Component
public class EmpleadoTransformer implements Transformer<Empleados, EmpleadoDTO> {

	/**
	 * Entity to dto.
	 *
	 * @param entity the entity
	 * @return the empleado DTO
	 */
	@Override
	public EmpleadoDTO entityToDto(Empleados entity) {
		return EmpleadoDTO.builder().id(entity.getId()).primerNombre(entity.getPrimerNombre())
				.segundoNombre(entity.getSegundoNombre()).apellidoPaterno(entity.getApellidoPaterno())
				.apellidoMaterno(entity.getApellidoMaterno()).edad(entity.getEdad()).sexo(entity.getSexo())
				.fechaNacimiento(DateUtil.toString(entity.getFechaNacimiento()))
				.puesto(entity.getPuesto())
				.build();

	}

	/**
	 * Dto to entity.
	 *
	 * @param dto the dto
	 * @return the empleados
	 */
	@Override
	public Empleados dtoToEntity(EmpleadoDTO dto) {
		LocalDate fechaNacimiento = DateUtil.toLocalDate(dto.getFechaNacimiento());

		return Empleados.builder().primerNombre(dto.getPrimerNombre()).segundoNombre(dto.getSegundoNombre())
				.apellidoPaterno(dto.getApellidoPaterno()).apellidoMaterno(dto.getApellidoMaterno()).edad(dto.getEdad())
				.sexo(dto.getSexo()).fechaNacimiento(fechaNacimiento).puesto(dto.getPuesto()).build();
	}

}
