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

    // Sincroniza los dos lados: sale del hospital anterior y entra en el nuevo.
    public void setHospital(Hospital nuevoHospital) {
        if (this.hospital == nuevoHospital) {
            return;
        }
        Hospital anterior = this.hospital;
        this.hospital = nuevoHospital;
        if (anterior != null) {
            anterior.quitarDepartamento(this);
        }
        if (nuevoHospital != null) {
            nuevoHospital.agregarDepartamento(this);
        }
    }

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
        if (medico.getDepartamento() != this) {
            medico.setDepartamento(this);
        }
    }

    // Usado por Medico.setDepartamento() al cambiar de departamento.
    void quitarMedico(Medico medico) {
        medicos.remove(medico);
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