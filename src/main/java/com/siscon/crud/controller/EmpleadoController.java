package com.siscon.crud.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.siscon.crud.dto.EmpleadoDTO;
import com.siscon.crud.service.EmpleadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * The Class EmpleadoController.
 * 
 * @author ajuarez
 */
@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
@Slf4j
public class EmpleadoController {

	/** The empleado service. */
	private final EmpleadoService empleadoService;

	/**
	 * Find all.
	 *
	 * @return the response entity
	 */
	@GetMapping
	@Operation(summary = "Obtener todos los empleados", description = "Recupera el listado de todos los empleados registrados")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = EmpleadoDTO.class))) })
	public ResponseEntity<List<EmpleadoDTO>> findAll() {
		log.info("Solicitud para obtener todos los empleados");
		List<EmpleadoDTO> empleados = empleadoService.findAll();
		return ResponseEntity.ok(empleados);
	}


	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@GetMapping("/{id}")
	@Operation(summary = "Obtener empleado por ID", description = "Recupera un empleado por su identificador")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = EmpleadoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Empleado no encontrado") })
	public ResponseEntity<EmpleadoDTO> findById(@PathVariable Long id) {
		log.info("Solicitud para obtener empleado con ID: {}", id);
		EmpleadoDTO empleado = empleadoService.findById(id);
		return ResponseEntity.ok(empleado);
	}

	/**
	 * Save.
	 *
	 * @param empleadoDTO the empleado DTO
	 * @return the response entity
	 */
	@PostMapping
	@Operation(summary = "Guardar empleado", description = "Registra un nuevo empleado")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Empleado creado", content = @Content(schema = @Schema(implementation = EmpleadoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Datos inválidos") })
	public ResponseEntity<EmpleadoDTO> save(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
		log.info("Solicitud para guardar empleado: {}", empleadoDTO.getPrimerNombre());
		EmpleadoDTO empleadoGuardado = empleadoService.save(empleadoDTO);
		return new ResponseEntity<>(empleadoGuardado, HttpStatus.CREATED);
	}

	/**
	 * Save all.
	 *
	 * @param empleadosDTO the empleados DTO
	 * @return the response entity
	 */
	@PostMapping("/saveAll")
	@Operation(summary = "Guardar varios empleados", description = "Registra múltiples empleados en una sola petición")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Empleados creados", content = @Content(schema = @Schema(implementation = EmpleadoDTO.class))),
			@ApiResponse(responseCode = "400", description = "Datos inválidos") })
	public ResponseEntity<List<EmpleadoDTO>> saveAll(@Valid @RequestBody List<EmpleadoDTO> empleadosDTO) {
		log.info("Solicitud para guardar {} empleados", empleadosDTO.size());
		List<EmpleadoDTO> empleadosGuardados = empleadoService.save(empleadosDTO);
		return new ResponseEntity<>(empleadosGuardados, HttpStatus.CREATED);
	}

	/**
	 * Update.
	 *
	 * @param id the id
	 * @param empleadoDTO the empleado DTO
	 * @return the response entity
	 */
	@PutMapping("/{id}")
	@Operation(summary = "Actualizar empleado", description = "Actualiza los datos de un empleado existente")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Empleado actualizado", content = @Content(schema = @Schema(implementation = EmpleadoDTO.class))),
			@ApiResponse(responseCode = "404", description = "Empleado no encontrado"),
			@ApiResponse(responseCode = "400", description = "Datos inválidos") })
	public ResponseEntity<EmpleadoDTO> update(@PathVariable Long id, @Valid @RequestBody EmpleadoDTO empleadoDTO) {
		log.info("Solicitud para actualizar empleado con ID: {}", id);
		EmpleadoDTO empleadoActualizado = empleadoService.update(id, empleadoDTO);
		return ResponseEntity.ok(empleadoActualizado);
	}

	/**
	 * Delete.
	 *
	 * @param id the id
	 * @return the response entity
	 */
	@DeleteMapping("/{id}")
	@Operation(summary = "Eliminar empleado", description = "Elimina un empleado por su identificador")
	@ApiResponses(value = { @ApiResponse(responseCode = "204", description = "Empleado eliminado"),
			@ApiResponse(responseCode = "404", description = "Empleado no encontrado") })
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		log.info("Solicitud para eliminar empleado con ID: {}", id);
		empleadoService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
