# TPI Programación 2 - Sistema de Gestión Hospitalaria

Trabajo Práctico Integrador de **Programación 2** (Tecnicatura Universitaria en Programación a Distancia, ciclo lectivo 2026).

Sistema de gestión hospitalaria con objetos en memoria: pacientes, médicos, departamentos, salas y citas, con validaciones, relaciones bidireccionales y reglas de disponibilidad.

## Entrega

- 🎥 **Video demostrativo:** [COMPLETAR: enlace al video con permisos públicos de visualización]
- 📚 **Documentación (PDF):** [COMPLETAR: enlace al PDF] *(o el archivo PDF incluido en la raíz del repositorio)*

## Datos del estudiante

- **Apellido y nombre:** Bernabey Izquierdo Alvaro.
- **Comisión / Cátedra:** Comisión 2, Programación 2.

## Requisitos técnicos

- Java 17 o superior.
- Solo biblioteca estándar: sin bases de datos, archivos ni librerías externas.
- Persistencia estrictamente en memoria.
- Maven (opcional): Group ID `org.jcr`, Artifact ID `tpi-hospital-java`.

## Estructura del proyecto

```
src/main/java/org/jcr/
├── MainPruebas.java        (provisto por la cátedra, no se modifica)
├── enums/
│   ├── TipoSangre.java
│   ├── EspecialidadMedica.java
│   └── EstadoCita.java
├── entidades/
│   ├── Persona.java        (abstracta)
│   ├── Paciente.java
│   ├── Medico.java
│   ├── Matricula.java
│   ├── HistoriaClinica.java
│   ├── Hospital.java
│   ├── Departamento.java
│   ├── Sala.java
│   └── Cita.java
├── excepciones/
│   └── CitaException.java  (excepción verificada)
└── servicios/
    ├── CitaService.java    (interfaz)
    └── CitaManager.java
```

## Diseño y reglas principales

- **Herencia:** `Persona` (abstracta) es la base de `Paciente` y `Medico`. Calcula el nombre completo y la edad en años cumplidos.
- **Composición:** `Paciente` crea su `HistoriaClinica` (número `HC-DNI-AÑO`), `Medico` crea su `Matricula` (formato `MP-` y 4 a 6 dígitos) y `Departamento.crearSala()` crea las `Sala`.
- **Relaciones bidireccionales:** Hospital–Departamento, Hospital–Paciente y Departamento–Medico se sincronizan desde ambas puntas, sin duplicar elementos. Un médico solo puede pertenecer a un departamento de su misma especialidad.
- **Validaciones:** los datos inválidos lanzan `IllegalArgumentException` desde los constructores y setters (textos vacíos, DNI de 7 u 8 dígitos, matrícula con formato incorrecto, campos obligatorios nulos).
- **Listas protegidas:** todos los getters de colecciones devuelven listas inmutables (`Collections.unmodifiableList`).
- **Programación de citas:** `CitaManager.programarCita()` lanza `CitaException` si hay datos nulos, la fecha ya pasó, el costo es menor o igual a 0, la especialidad del médico no coincide con la de la sala, o el médico o la sala tienen otra cita a menos de 2 horas (`HORAS_ENTRE_CITAS = 2`).

## Cómo compilar y ejecutar

Desde la raíz del proyecto, con la codificación UTF-8 (el código tiene tildes):

```bash
javac -encoding UTF-8 -d out $(find src/main/java -name "*.java")
java -cp out org.jcr.MainPruebas
```

Con Maven:

```bash
mvn clean compile
```

Al ejecutar `MainPruebas`, el resultado esperado es:

```
===== RESULTADO: 106 de 106 verificaciones correctas =====
Todas las pruebas pasaron.
```
