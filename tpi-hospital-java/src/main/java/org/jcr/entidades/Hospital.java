package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hospital {

    private final String nombre;
    private final String direccion;
    private final String telefono;
    private final List<Departamento> departamentos = new ArrayList<>();
    private final List<Paciente> pacientes = new ArrayList<>();

    public Hospital(String nombre, String direccion, String telefono) {
        this.nombre = Persona.validarTexto(nombre, "El nombre");
        this.direccion = Persona.validarTexto(direccion, "La dirección");
        this.telefono = Persona.validarTexto(telefono, "El teléfono");
    }

    public String getNombre() { return nombre; }

    public String getDireccion() { return direccion; }

    public String getTelefono() { return telefono; }

    public void agregarDepartamento(Departamento departamento) {
        if (departamento == null) {
            throw new IllegalArgumentException("El departamento no puede ser nulo");
        }
        if (!departamentos.contains(departamento)) {
            departamentos.add(departamento);
        }
        if (departamento.getHospital() != this) {
            departamento.setHospital(this);
        }
    }

    public void agregarPaciente(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        if (!pacientes.contains(paciente)) {
            pacientes.add(paciente);
        }
        if (paciente.getHospital() != this) {
            paciente.setHospital(this);
        }
    }

    // Usados por Departamento.setHospital() y Paciente.setHospital() al cambiar de hospital.
    void quitarDepartamento(Departamento departamento) {
        departamentos.remove(departamento);
    }

    void quitarPaciente(Paciente paciente) {
        pacientes.remove(paciente);
    }

    public List<Departamento> getDepartamentos() { return Collections.unmodifiableList(departamentos); }

    public List<Paciente> getPacientes() { return Collections.unmodifiableList(pacientes); }
}