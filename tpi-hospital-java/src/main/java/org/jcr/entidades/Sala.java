package org.jcr.entidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sala {

    private final String numero;
    private final String tipo;
    private final Departamento departamento;
    private final List<Cita> citas = new ArrayList<>();

    // Constructor package-private: solo Departamento.crearSala() la instancia.
    Sala(String numero, String tipo, Departamento departamento) {
        this.numero = Persona.validarTexto(numero, "El número de sala");
        this.tipo = Persona.validarTexto(tipo, "El tipo de sala");
        if (departamento == null) {
            throw new IllegalArgumentException("El departamento no puede ser nulo");
        }
        this.departamento = departamento;
    }

    public String getNumero() { return numero; }

    public String getTipo() { return tipo; }

    public Departamento getDepartamento() { return departamento; }

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