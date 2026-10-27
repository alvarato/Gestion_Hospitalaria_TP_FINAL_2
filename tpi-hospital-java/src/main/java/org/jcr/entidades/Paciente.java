package org.jcr.entidades;

import org.jcr.enums.TipoSangre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Paciente extends Persona {

    private final String telefono;
    private final String direccion;
    private Hospital hospital;
    private final HistoriaClinica historiaClinica;
    private final List<Cita> citas = new ArrayList<>();

    public Paciente(String nombre, String apellido, String dni, LocalDate fechaNacimiento,
                    TipoSangre tipoSangre, String telefono, String direccion) {
        super(nombre, apellido, dni, fechaNacimiento, tipoSangre);
        this.telefono = validarTexto(telefono, "El teléfono");
        this.direccion = validarTexto(direccion, "La dirección");
        this.historiaClinica = new HistoriaClinica(getDni());
    }

    public String getTelefono() { return telefono; }

    public String getDireccion() { return direccion; }

    public Hospital getHospital() { return hospital; }

    // Lo invoca Hospital.agregarPaciente() para mantener la relación bidireccional.
    void setHospital(Hospital hospital) { this.hospital = hospital; }

    public HistoriaClinica getHistoriaClinica() { return historiaClinica; }

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
