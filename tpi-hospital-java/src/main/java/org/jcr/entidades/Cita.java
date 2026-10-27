package org.jcr.entidades;

import org.jcr.enums.EstadoCita;

import java.time.LocalDateTime;

public class Cita {

    private final Paciente paciente;
    private final Medico medico;
    private final Sala sala;
    private final LocalDateTime fechaHora;
    private final double costo;
    private EstadoCita estado;
    private String observaciones;

    public Cita(Paciente paciente, Medico medico, Sala sala, LocalDateTime fechaHora, double costo) {
        if (paciente == null || medico == null || sala == null || fechaHora == null) {
            throw new IllegalArgumentException(
                    "Paciente, médico, sala y fecha/hora son obligatorios");
        }
        this.paciente = paciente;
        this.medico = medico;
        this.sala = sala;
        this.fechaHora = fechaHora;
        this.costo = costo;
        this.estado = EstadoCita.PROGRAMADA;
        this.observaciones = "";
    }

    public Paciente getPaciente() { return paciente; }

    public Medico getMedico() { return medico; }

    public Sala getSala() { return sala; }

    public LocalDateTime getFechaHora() { return fechaHora; }

    public double getCosto() { return costo; }

    public EstadoCita getEstado() { return estado; }

    public void setEstado(EstadoCita estado) {
        if (estado == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo");
        }
        this.estado = estado;
    }

    public String getObservaciones() { return observaciones; }

    // Observaciones nulas quedan como texto vacío.
    public void setObservaciones(String observaciones) {
        this.observaciones = (observaciones == null) ? "" : observaciones;
    }
}