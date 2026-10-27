package org.jcr.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoriaClinica {

    private final String numero;
    private final List<String> diagnosticos = new ArrayList<>();
    private final List<String> tratamientos = new ArrayList<>();
    private final List<String> alergias = new ArrayList<>();

    // Constructor package-private: solo Paciente puede crearla.
    HistoriaClinica(String dniPaciente) {
        this.numero = "HC-" + dniPaciente + "-" + LocalDate.now().getYear();
    }

    public String getNumero() { return numero; }

    public void agregarDiagnostico(String diagnostico) {
        diagnosticos.add(Persona.validarTexto(diagnostico, "El diagnóstico"));
    }

    public void agregarTratamiento(String tratamiento) {
        tratamientos.add(Persona.validarTexto(tratamiento, "El tratamiento"));
    }

    public void agregarAlergia(String alergia) {
        alergias.add(Persona.validarTexto(alergia, "La alergia"));
    }

    public List<String> getDiagnosticos() { return Collections.unmodifiableList(diagnosticos); }

    public List<String> getTratamientos() { return Collections.unmodifiableList(tratamientos); }

    public List<String> getAlergias() { return Collections.unmodifiableList(alergias); }
}
