package org.jcr.entidades;

import org.jcr.enums.EspecialidadMedica;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Departamento {

    private final String nombre;
    private final EspecialidadMedica especialidad;
    private Hospital hospital;
    private final List<Medico> medicos = new ArrayList<>();
    private final List<Sala> salas = new ArrayList<>();

    public Departamento(String nombre, EspecialidadMedica especialidad) {
        this.nombre = Persona.validarTexto(nombre, "El nombre");
        if (especialidad == null) {
            throw new IllegalArgumentException("La especialidad no puede ser nula");
        }
        this.especialidad = especialidad;
    }

    public String getNombre() { return nombre; }

    public EspecialidadMedica getEspecialidad() { return especialidad; }

    public Hospital getHospital() { return hospital; }

    // Lo invoca Hospital.agregarDepartamento() para mantener la relación bidireccional.
    void setHospital(Hospital hospital) { this.hospital = hospital; }

    public void agregarMedico(Medico medico) {
        if (medico == null) {
            throw new IllegalArgumentException("El médico no puede ser nulo");
        }
        if (medico.getEspecialidad() != especialidad) {
            throw new IllegalArgumentException(
                    "La especialidad del médico no coincide con la del departamento");
        }
        if (!medicos.contains(medico)) {
            medicos.add(medico);
        }
        medico.setDepartamento(this);
    }

    // Fábrica: las salas solo pueden ser creadas por el departamento (composición).
    public Sala crearSala(String numero, String tipo) {
        Sala sala = new Sala(numero, tipo, this);
        salas.add(sala);
        return sala;
    }

    public List<Medico> getMedicos() { return Collections.unmodifiableList(medicos); }

    public List<Sala> getSalas() { return Collections.unmodifiableList(salas); }
}
