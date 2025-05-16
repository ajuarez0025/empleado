package com.siscon.crud.transformer;

import java.util.Collections;
import java.util.List;

/**
 * The Interface Transformer.
 *
 * @param <E> the element type
 * @param <D> the generic type
 * 
 * @author ajuarez
 */
public interface Transformer<E, D> {

	/**
	 * Dto to entity.
	 *
	 * @param dto the dto
	 * @return the e
	 */
	public E dtoToEntity(D dto);

	/**
	 * Entity to dto.
	 *
	 * @param entity the entity
	 * @return the d
	 */
	public D entityToDto(E entity);

	/**
	 * Dto to entity.
	 *
	 * @param dtos the dtos
	 * @return the list
	 */
	public default List<E> dtoToEntity(List<D> dtos) {
		if (dtos == null) {
			return Collections.emptyList();
		}
		return dtos.stream().map(this::dtoToEntity).toList();
	}

	/**
	 * Entity to dto.
	 *
	 * @param entitys the entitys
	 * @return the list
	 */
	public default List<D> entityToDto(List<E> entitys) {
		if (entitys == null) {
			return Collections.emptyList();
		}
		return entitys.stream().map(this::entityToDto).toList();
	}
}
