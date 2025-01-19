package com.asbel.model.entity;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "local")
public class Local {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_local")
	private Integer id;

	@NotBlank(message = "El nombre es obligatorio")
	@Size(min = 2, max = 30, message = "El nombre debe tener entre 2 y 30 caracteres")
	private String nombre;

	@NotBlank(message = "La dirección es obligatoria")
	@Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
	private String direccion;

	@NotBlank(message = "El teléfono es obligatorio")
	@Size(min = 9, max = 9, message = "El teléfono debe tener 9 caracteres")
	private String telefono;

	@Column(name = "hora_apertura")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaApertura;

	@Column(name = "hora_cierre")
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaCierre;
}
