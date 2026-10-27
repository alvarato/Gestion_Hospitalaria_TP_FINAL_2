package org.jcr.entidades;

public class Matricula {

    private final String numero;

    public Matricula(String numero) {
        if (numero == null || !numero.matches("MP-\\d{4,6}")) {
            throw new IllegalArgumentException(
                    "La matrícula debe tener el formato MP- seguido de 4 a 6 dígitos (ej. MP-1234)");
        }
        this.numero = numero;
    }

    public String getNumero() { return numero; }

    @Override
    public String toString() { return numero; }
}