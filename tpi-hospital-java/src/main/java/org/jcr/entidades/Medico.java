package org.jcr.entidades;

import org.jcr.enums.EspecialidadMedica;
import org.jcr.enums.TipoSangre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Medico extends Persona {

    private final Matricula matricula;
    private final EspecialidadMedica especialidad;
    private Departamento departamento;
    private final List<Cita> citas = new ArrayList<>();

    public Medico(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                  TipoSangre tipoSangre, String numeroMatricula, EspecialidadMedica especialidad) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.matricula = new Matricula(numeroMatricula);
        if (especialidad == null) {
            throw new IllegalArgumentException("La especialidad no puede ser nula");
        }
        this.especialidad = especialidad;
    }

    public Matricula getMatricula() { return matricula; }

    public EspecialidadMedica getEspecialidad() { return especialidad; }

    public Departamento getDepartamento() { return departamento; }

    // Lo invoca Departamento.agregarMedico() para mantener la relación bidireccional.
    void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public void agregarCita(Cita cita) {
        if (cita == null) {
            throw new IllegalArgumentException("La cita no puede ser nula");
        }
        if (!citas.contains(cita)) {
            citas.add(cita);
        }
    }

    public List<Cita> getCitas() { return Collections.unmodifiableList(citas); }
}
