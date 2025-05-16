package com.siscon.crud.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.siscon.crud.dto.EmpleadoDTO;
import com.siscon.crud.service.EmpleadoService;

/**
 * The Class EmpleadoControllerTest.
 */
@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {


	/** The mock mvc. */
	@Autowired
	private MockMvc mockMvc;


	/** The empleado service. */
	@SuppressWarnings("removal")
	@MockBean
	private EmpleadoService empleadoService;

	
	/** The empleado. */
	private EmpleadoDTO empleado;


	/**
	 * Sets the up.
	 */
	@BeforeEach
	void setUp() {
		empleado = EmpleadoDTO.builder().id(1L).apellidoMaterno("Test").apellidoPaterno("Test")
				.fechaNacimiento("12-05-1993").primerNombre("Test").puesto("Ingeniero").sexo("M").edad(20).build();

	}

	/**
	 * Test find all.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testFindAll() throws Exception {
		List<EmpleadoDTO> list = List.of(empleado);
		when(empleadoService.findAll()).thenReturn(list);

		mockMvc.perform(get("/api/empleados")).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].primerNombre").value("Test"));
	}

	/**
	 * Test save.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testSave() throws Exception {
		when(empleadoService.save(anyList())).thenReturn(List.of(empleado));

		mockMvc.perform(post("/api/empleados/saveAll").contentType(MediaType.APPLICATION_JSON).content(
				"[{\"primerNombre\":\"Test\",\"segundoNombre\":\"Test\",\"apellidoPaterno\":\"Test\",\"apellidoMaterno\":\"Test\",\"edad\":30,\"sexo\":\"M\",\"fechaNacimiento\":\"12-05-1993\",\"puesto\":\"Ingeniero\"}]"))
				.andExpect(status().isCreated()).andExpect(jsonPath("$[0].puesto").value("Ingeniero"));
	}

	/**
	 * Test delete.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testDelete() throws Exception {
		doNothing().when(empleadoService).delete(1L);

		mockMvc.perform(delete("/api/empleados/1")).andExpect(status().isNoContent());
	}

	/**
	 * Test update.
	 *
	 * @throws Exception the exception
	 */
	@Test
	void testUpdate() throws Exception {
		when(empleadoService.update(eq(1L), any(EmpleadoDTO.class))).thenReturn(empleado);

		mockMvc.perform(put("/api/empleados/1").contentType(MediaType.APPLICATION_JSON).content(
				"{\"primerNombre\":\"Test\",\"segundoNombre\":\"Test\",\"apellidoPaterno\":\"Test\",\"apellidoMaterno\":\"López\",\"edad\":30,\"sexo\":\"M\",\"fechaNacimiento\":\"12-05-1993\",\"puesto\":\"Analista\"}"))
				.andExpect(status().isOk()).andExpect(jsonPath("$.apellidoPaterno").value("Test"));
	}
}
