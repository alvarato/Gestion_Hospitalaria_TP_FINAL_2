package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoriaClinica {

    private final Paciente paciente;
    private final String numeroHistoria;
    private final List<String> diagnosticos = new ArrayList<>();
    private final List<String> tratamientos = new ArrayList<>();
    private final List<String> alergias = new ArrayList<>();

    // Constructor package-private: solo Paciente puede crearla.
    HistoriaClinica(Paciente paciente) {
        if (paciente == null) {
            throw new IllegalArgumentException("El paciente no puede ser nulo");
        }
        this.paciente = paciente;
        this.numeroHistoria = "HC-" + paciente.getDni() + "-" + LocalDate.now().getYear();
    }

    public Paciente getPaciente() { return paciente; }

    public String getNumeroHistoria() { return numeroHistoria; }

    public void agregarDiagnostico(String diagnostico) {
        if (esValido(diagnostico)) {
            diagnosticos.add(diagnostico.trim());
        }
    }

    public void agregarTratamiento(String tratamiento) {
        if (esValido(tratamiento)) {
            tratamientos.add(tratamiento.trim());
        }
    }

    public void agregarAlergia(String alergia) {
        if (esValido(alergia)) {
            alergias.add(alergia.trim());
        }
    }

    private boolean esValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    public List<String> getDiagnosticos() { return Collections.unmodifiableList(diagnosticos); }

    public List<String> getTratamientos() { return Collections.unmodifiableList(tratamientos); }

    public List<String> getAlergias() { return Collections.unmodifiableList(alergias); }
}