package org.jcr.servicios;

import org.jcr.entidades.Cita;
import org.jcr.entidades.Medico;
import org.jcr.entidades.Paciente;
import org.jcr.entidades.Sala;
import org.jcr.excepciones.CitaException;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CitaManager implements CitaService {

    public static final int HORAS_ENTRE_CITAS = 2;

    private final List<Cita> citas = new ArrayList<>();

    @Override
    public Cita programarCita(Paciente paciente, Medico medico, Sala sala,
                              LocalDateTime fechaHora, double costo) throws CitaException {
        if (paciente == null || medico == null || sala == null || fechaHora == null) {
            throw new CitaException("Paciente, médico, sala y fecha/hora son obligatorios");
        }
        if (fechaHora.isBefore(LocalDateTime.now())) {
            throw new CitaException("La fecha de la cita ya pasó");
        }
        if (costo <= 0) {
            throw new CitaException("El costo debe ser mayor a cero");
        }
        if (medico.getEspecialidad() != sala.getDepartamento().getEspecialidad()) {
            throw new CitaException(
                    "La especialidad del médico no coincide con la de la sala");
        }
        for (Cita existente : citas) {
            boolean mismoMedico = existente.getMedico() == medico;
            boolean mismaSala = existente.getSala() == sala;
            if ((mismoMedico || mismaSala) && enConflicto(existente.getFechaHora(), fechaHora)) {
                throw new CitaException(mismoMedico
                        ? "El médico tiene otra cita en una ventana menor a " + HORAS_ENTRE_CITAS + " horas"
                        : "La sala tiene otra cita en una ventana menor a " + HORAS_ENTRE_CITAS + " horas");
            }
        }

        Cita cita = new Cita(paciente, medico, sala, fechaHora, costo);
        citas.add(cita);
        paciente.addCita(cita);
        medico.addCita(cita);
        sala.addCita(cita);
        return cita;
    }

    private boolean enConflicto(LocalDateTime a, LocalDateTime b) {
        return Math.abs(Duration.between(a, b).toMinutes()) < HORAS_ENTRE_CITAS * 60L;
    }

    @Override
    public List<Cita> getCitasPorPaciente(Paciente paciente) {
        return filtrar(c -> c.getPaciente() == paciente);
    }

    @Override
    public List<Cita> getCitasPorMedico(Medico medico) {
        return filtrar(c -> c.getMedico() == medico);
    }

    @Override
    public List<Cita> getCitasPorSala(Sala sala) {
        return filtrar(c -> c.getSala() == sala);
    }

    private List<Cita> filtrar(java.util.function.Predicate<Cita> criterio) {
        List<Cita> resultado = new ArrayList<>();
        for (Cita c : citas) {
            if (criterio.test(c)) {
                resultado.add(c);
            }
        }
        return java.util.Collections.unmodifiableList(resultado);
    }
}