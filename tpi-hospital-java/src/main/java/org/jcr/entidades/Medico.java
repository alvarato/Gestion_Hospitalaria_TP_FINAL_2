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

    // Valida la especialidad y sincroniza los dos lados: sale del departamento anterior y entra en el nuevo.
    public void setDepartamento(Departamento nuevoDepartamento) {
        if (nuevoDepartamento != null && nuevoDepartamento.getEspecialidad() != especialidad) {
            throw new IllegalArgumentException(
                    "La especialidad del médico no coincide con la del departamento");
        }
        if (this.departamento == nuevoDepartamento) {
            return;
        }
        Departamento anterior = this.departamento;
        this.departamento = nuevoDepartamento;
        if (anterior != null) {
            anterior.quitarMedico(this);
        }
        if (nuevoDepartamento != null) {
            nuevoDepartamento.agregarMedico(this);
        }
    }

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