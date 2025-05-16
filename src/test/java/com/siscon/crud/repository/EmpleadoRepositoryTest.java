package com.siscon.crud.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.siscon.crud.model.Empleados;

/**
 * The Class EmpleadoRepositoryTest.
 */
@ExtendWith(MockitoExtension.class)
class EmpleadoRepositoryTest {
	
	/** The empleado repository. */
	@Mock
	private EmpleadoRepository empleadoRepository;

	/** The empleado. */
	private Empleados empleado;

	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		empleado = new Empleados(1L, "Luis", "Miguel", "Santos", "Mendoza", 35, "M", LocalDate.of(1989, 3, 20),
				"Ingeniero");
	}

	/**
	 * Test save.
	 */
	@Test
	void testSave() {
		when(empleadoRepository.save(any(Empleados.class))).thenReturn(empleado);

		Empleados resultado = empleadoRepository.save(empleado);

		assertNotNull(resultado);
		assertEquals("Luis", resultado.getPrimerNombre());
		verify(empleadoRepository).save(empleado);
	}

	/**
	 * Test find all.
	 */
	@Test
	void testFindAll() {
		List<Empleados> lista = List.of(empleado);
		when(empleadoRepository.findAll()).thenReturn(lista);

		List<Empleados> resultado = empleadoRepository.findAll();

		assertEquals(1, resultado.size());
		verify(empleadoRepository).findAll();
	}

	/**
	 * Test find by id.
	 */
	@Test
	void testFindById() {
		when(empleadoRepository.findById(1L)).thenReturn(Optional.of(empleado));

		Optional<Empleados> resultado = empleadoRepository.findById(1L);

		assertTrue(resultado.isPresent());
		assertEquals("Santos", resultado.get().getApellidoPaterno());
		verify(empleadoRepository).findById(1L);
	}

	/**
	 * Test delete.
	 */
	@Test
	void testDelete() {
		doNothing().when(empleadoRepository).deleteById(1L);

		empleadoRepository.deleteById(1L);

		verify(empleadoRepository).deleteById(1L);
	}
}
