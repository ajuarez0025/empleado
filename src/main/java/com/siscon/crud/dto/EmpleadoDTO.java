package com.siscon.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * The Class EmpleadoDTO.
 * 
 * @author ajuarez
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpleadoDTO implements java.io.Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -7895292817160141858L;

	/** The id. */
	private Long id;

	/** The primer nombre. */
	@NotBlank(message = "El primer nombre es obligatorio")
	private String primerNombre;

	/** The segundo nombre. */
	private String segundoNombre;

	/** The apellido paterno. */
	@NotBlank(message = "El apellido paterno es obligatorio")
	private String apellidoPaterno;

	/** The apellido materno. */
	private String apellidoMaterno;

	/** The edad. */
	@NotNull(message = "La edad es obligatoria")
	@Positive(message = "La edad debe ser un número positivo")
	private Integer edad;

	/** The sexo. */
	@NotBlank(message = "El sexo es obligatorio")
	@Pattern(regexp = "[MF]", message = "El sexo debe ser M (Masculino) o F (Femenino)")
	private String sexo;

	/** The fecha nacimiento. */
	
	@NotBlank(message = "La fecha de nacimiento es obligatoria")
	@Pattern(regexp = "\\d{2}-\\d{2}-\\d{4}", message = "La fecha de nacimiento debe tener el formato dd-mm-yyyy")
	@Schema(example = "25-12-1990", description = "Fecha en formato dd-MM-yyyy")
	private String fechaNacimiento;

	/** The puesto. */
	@NotBlank(message = "El puesto es obligatorio")
	private String puesto;
}
