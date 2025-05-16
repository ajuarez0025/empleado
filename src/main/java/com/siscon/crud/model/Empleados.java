package com.siscon.crud.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

/**
 * The Class Empleados.
 * 
 * @author ajuarez
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "empleados")
public class Empleados {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** The primer nombre. */
	@Column(name = "primer_nombre", nullable = false)
	private String primerNombre;

	/** The segundo nombre. */
	@Column(name = "segundo_nombre")
	private String segundoNombre;

	/** The apellido paterno. */
	@Column(name = "apellido_paterno", nullable = false)
	private String apellidoPaterno;

	/** The apellido materno. */
	@Column(name = "apellido_materno")
	private String apellidoMaterno;

	/** The edad. */
	@Column(nullable = false)
	private Integer edad;

	/** The sexo. */
	@Column(nullable = false, length = 1)
	private String sexo;

	/** The fecha nacimiento. */
	@Column(name = "fecha_nacimiento", nullable = false)
	private LocalDate fechaNacimiento;

	/** The puesto. */
	@Column(nullable = false)
	private String puesto;
}
