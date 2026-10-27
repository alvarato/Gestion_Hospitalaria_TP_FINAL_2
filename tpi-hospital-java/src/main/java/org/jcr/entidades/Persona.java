package org.jcr.entidades;

import org.jcr.enums.TipoSangre;

import java.time.LocalDate;
import java.time.Period;

public abstract class Persona {

    private final String nombre;
    private final String apellido;
    private final String dni;
    private final LocalDate fechaNacimiento;
    private final TipoSangre tipoSangre;

    protected Persona(String nombre, String apellido, String dni,
                      LocalDate fechaNacimiento, TipoSangre tipoSangre) {
        this.nombre = validarTexto(nombre, "El nombre");
        this.apellido = validarTexto(apellido, "El apellido");
        this.dni = validarDni(dni);
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        if (fechaNacimiento.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser futura");
        }
        this.fechaNacimiento = fechaNacimiento;
        if (tipoSangre == null) {
            throw new IllegalArgumentException("El tipo de sangre no puede ser nulo");
        }
        this.tipoSangre = tipoSangre;
    }

    static String validarTexto(String valor, String campo) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(campo + " no puede ser nulo ni vacío");
        }
        return valor.trim();
    }

    private static String validarDni(String dni) {
        if (dni == null || !dni.matches("\\d{7,8}")) {
            throw new IllegalArgumentException("El DNI debe tener 7 u 8 dígitos numéricos");
        }
        return dni;
    }

    public String getNombre() { return nombre; }

    public String getApellido() { return apellido; }

    public String getDni() { return dni; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    public TipoSangre getTipoSangre() { return tipoSangre; }

    public String getNombreCompleto() {
        return nombre + " " + apellido;
    }

    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
}