package com.siscon.crud.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.siscon.crud.dto.EmpleadoDTO;
import com.siscon.crud.model.Empleados;
import com.siscon.crud.transformer.EmpleadoTransformer;
import com.siscon.crud.repository.EmpleadoRepository;

/**
 * The Class EmpleadoServiceTest.
 */
@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {
	
	/** The empleado repository. */
	@Mock
	private EmpleadoRepository empleadoRepository;

	/** The empleado transformer. */
	@Mock
	private EmpleadoTransformer empleadoTransformer;

	/** The empleado service. */
	@InjectMocks
	private EmpleadoServiceImpl empleadoService;

	/** The empleado. */
	private Empleados empleado;

	/** The empleado dto. */
	private EmpleadoDTO empleadoDto;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		empleado = new Empleados(1L, "Ana", "Laura", "García", "Ramírez", 27, "F", LocalDate.of(1996, 2, 10), "QA");

		empleadoDto = new EmpleadoDTO(1L, "Ana", "Laura", "García", "Ramírez", 27, "F", "02-10-1996", "QA");
	}

	/**
	 * Test find all.
	 */
	@Test
	void testFindAll() {
		when(empleadoRepository.findAll()).thenReturn(List.of(empleado));

		List<EmpleadoDTO> resultado = empleadoService.findAll();

		assertEquals(1, resultado.size());

	}

	/**
	 * Test save.
	 */
	@Test
	void testSave() {
		when(empleadoRepository.save(empleado)).thenReturn(empleado);

		when(empleadoTransformer.dtoToEntity(empleadoDto)).thenReturn(empleado);

		when(empleadoTransformer.entityToDto(empleado)).thenReturn(empleadoDto);

		EmpleadoDTO resultado = empleadoService.save(empleadoDto);

		assertNotNull(resultado);
		assertEquals("QA", resultado.getPuesto());

	}

	/**
	 * Test save all.
	 */
	@Test
	void testSaveAll() {
		List<Empleados> lista = List.of(empleado);
		when(empleadoRepository.saveAll(lista)).thenReturn(lista);

		when(empleadoTransformer.dtoToEntity(List.of(empleadoDto))).thenReturn(lista);

		when(empleadoTransformer.entityToDto(lista)).thenReturn(List.of(empleadoDto));

		List<EmpleadoDTO> resultado = empleadoService.save(List.of(empleadoDto));

		assertEquals(1, resultado.size());
		verify(empleadoRepository).saveAll(lista);
	}

	/**
	 * Test delete.
	 */
	@Test
	void testDelete() {
		when(empleadoRepository.existsById(1L)).thenReturn(Boolean.TRUE);
		doNothing().when(empleadoRepository).deleteById(1L);

		empleadoService.delete(1L);

		verify(empleadoRepository).deleteById(1L);
	}

	/**
	 * Tes update.
	 */
	@Test
	void tesUpdate() {
		when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));
		when(empleadoRepository.save(any(Empleados.class))).thenReturn(empleado);
		when(empleadoTransformer.entityToDto(empleado)).thenReturn(empleadoDto);

		EmpleadoDTO modificado = new EmpleadoDTO(null, "Ana", "Laura", "García", "Ramírez", 28, "F", "02-10-1996",
				"QA");

		EmpleadoDTO resultado = empleadoService.update(1L, modificado);

		assertNotNull(resultado);
	}

}
