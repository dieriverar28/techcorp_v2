# 🏢 TechCorp - Sistema de Gestión de Empleados en Kotlin

![Kotlin](https://img.shields.io/badge/Language-Kotlin-7F52FF?style=for-the-badge&logo=kotlin&logoColor=white)
![POO](https://img.shields.io/badge/Paradigm-POO-blue?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

## 📌 Contexto del Proyecto

**TechCorp** es una empresa tecnológica chilena en rápido crecimiento. Con el aumento constante del personal, se ha hecho necesario implementar un sistema orientado a objetos para administrar la información de los trabajadores y calcular correctamente sus remuneraciones.

El sistema permite gestionar trabajadores generales así como roles especializados (**Desarrolladores** y **Gerentes de Proyecto**), calculando asignaciones adicionales como horas extraordinarias y bonos por cumplimiento de objetivos.

---

## 🛠️ Requisitos e Implementación del Sistema

### 1. Clase Base: `Empleado`

Representa a cualquier trabajador general de la empresa.

* **Propiedades:**
  * `id`: `Int` (Debe ser un número entero positivo).
  * `nombre`: `String` (No puede estar vacío ni contener solo espacios).
  * `salarioBase`: `Double` (Mínimo **$800.000 CLP**).
  * `departamento`: `String` (Por defecto: `"General"`).
* **Constructores:**
  * **Principal:** Recibe `id`, `nombre`, `salarioBase` y `departamento`.
  * **Secundario (Registro Simplificado):** Recibe solo `id` y `nombre`, asignando por defecto `$950.000 CLP` como salario base y `"General"` como departamento.
* **Métodos principales:**
  * `calcularSalarioTotal(): Double`: Retorna el sueldo total a recibir (en la clase base es igual al `salarioBase`).
  * `mostrarFichaLaboral()`: Muestra la información consolidada del trabajador. *Este método no puede ser sobrescrito por clases hijas (`final`)*.
  * `reportarActividad()`: Devuelve un mensaje aleatorio de actividad:
    > `["Estoy trabajando", "Sigo trabajando", "Estoy en el baño", "Estoy almorzando", "Estoy sacando la vuelta"]`

---

### 2. Clase Especializada: `Desarrollador` *(Hereda de `Empleado`)*

Representa a los integrantes del equipo de desarrollo de software.

* **Propiedades adicionales:**
  * `lenguajePrincipal`: `String` (Ej. `"Kotlin"`, `"Java"`).
  * `horasExtra`: `Int` (Por defecto `0`, validación: `horasExtra >= 0`).
* **Reglas de negocio:**
  * **Cálculo de Sueldo:** Recibe un adicional de **$15.000 CLP** por cada hora extraordinaria trabajada.
    $$\text{Sueldo Total} = \text{Sueldo Base} + (\text{Horas Extra} \times 15.000)$$
  * **Reporte de Actividad:** Muestra el mensaje:
    > *"El desarrollador está escribiendo código en [lenguajePrincipal]."*

---

### 3. Clase Especializada: `GerenteProyecto` *(Hereda de `Empleado`)*

Representa a los profesionales encargados de coordinar proyectos y equipos.

* **Propiedades adicionales:**
  * `bonoPorObjetivos`: `Double` (Validación con `require`: `bonoPorObjetivos >= 0`).
  * `proyectosActivos`: `Int` (Por defecto `1`, validación con `require`: `proyectosActivos >= 1`).
* **Reglas de negocio:**
  * **Cálculo de Sueldo:** Suma el bono al sueldo base.
    $$\text{Sueldo Total} = \text{Sueldo Base} + \text{Bono Por Objetivos}$$

---

## 📊 Resumen de Reglas de Negocio

| Rol | Validación Especial | Regla de Remuneración Adicional | Reporte de Actividad |
| :--- | :--- | :--- | :--- |
| **Empleado** | Salario base $\ge \$800.000$, ID $> 0$, Nombre no vacío | Sueldo Base | Mensaje aleatorio |
| **Desarrollador** | Horas extra $\ge 0$ | $+ \$15.000\text{ CLP}$ por hora extra | "Escribiendo código en [Lenguaje]" |
| **Gerente de Proyecto** | Bono $\ge 0$, Proyectos Activos $\ge 1$ | $+$ Bono por objetivos | Heredado / Personalizado |

---

## 🖥️ Ejemplo de Salida (Ficha Laboral)

```text
==================================================
              FICHA LABORAL TECHCORP              
==================================================
ID:           101
Nombre:       Constanza Silva
Departamento: Desarrollo
Sueldo Base:  $1.200.000 CLP
Sueldo Total: $1.350.000 CLP
==================================================
```
---
# 🏢 Desafío Adicional: Auditoría y Control Financiero de TechCorp

Debido al crecimiento acelerado de TechCorp, el departamento de finanzas necesita un módulo de auditoría y gestión centralizada para llevar el control del personal contratado y garantizar la sostenibilidad económica de la empresa.

Para cumplir con este requerimiento, deberás crear una clase llamada `Empresa`.

#### Información de la Empresa
La clase `Empresa` debe permitir registrar y representar la información básica de la compañía:
* **Identificación:** Debe almacenar el rol (RUT o identificador tributario) y el nombre de la empresa.
* **Fundación:** Debe registrar la fecha exacta de inauguración de la empresa.
* **Presupuesto Anual:** La empresa maneja su presupuesto anual en dólares (USD). Sin embargo, para efectos financieros y de cálculo de remuneraciones internas, este monto debe convertirse a pesos chilenos (CLP) considerando un tipo de cambio fijo de **1 USD = $900 CLP**.

#### Gestión de Personal y Nómina
La empresa debe mantener una estructura que le permita administrar a todo su personal contratado (empleados generales, desarrolladores y gerentes por igual).

Debe incluir los siguientes comportamientos:

1. **Contratar:** Permite incorporar un nuevo trabajador a la empresa. 
2. **Despedir:** Permite retirar a un trabajador de la empresa.
3. **Calcular Planilla de Salarios:** Calcula y retorna el monto total mensual en pesos chilenos que la empresa debe desembolsar para pagar los salarios líquidos totales de todo su personal actual.
4. **Generar Reporte de Auditoría:** Muestra por pantalla la Ficha General de la Empresa con el siguiente detalle estructurado:
   * Rol y Nombre de la empresa.
   * Antigüedad de la empresa (calculada en años y meses transcurridos desde su fecha de inauguración hasta la fecha actual).
   * Presupuesto anual (tanto en USD como en su equivalente en CLP).
   * Presupuesto disponible mensual en CLP (Presupuesto Anual en CLP / 12).
   * Gastos mensuales en salarios (Costo de planilla actual).
   * Cantidad total de trabajadores contratados.
   * Desglose de cantidad de trabajadores por tipo (Empleados Desarrolladores y Gerentes).

#### ⚠️ Reglas de Negocio y Restricciones Operativas

El proceso de contratación debe estar estrictamente regulado. Antes de incorporar a un nuevo trabajador, se deben validar las siguientes condiciones:

* **Límite Presupuestario Mensual:** La empresa no puede comprometer más dinero del que tiene asignado mensualmente. Si al intentar contratar a un nuevo trabajador el costo total de la planilla mensual supera el **presupuesto mensual en CLP**, la contratación **debe rechazarse** (el trabajador no se añade) y se debe notificar la falta de presupuesto.
* **Proporción de Liderazgo:** Para mantener una estructura organizacional sana, TechCorp establece que **no pueden existir más Gerentes qu

---
# 🏢 Aplicando Sealed Class, Enum Class y Corrutinas

Actualizaremos la estructura del proyecto:

#### Información de la Empresa
La clase `Empresa` debe permitir registrar y representar la información básica de la compañía:
* **Identificación:** Debe almacenar el rol (RUT o identificador tributario) y el nombre de la empresa.
* **Fundación:** Debe registrar la fecha exacta de inauguración de la empresa.
* **Presupuesto Anual:** La empresa maneja su presupuesto anual en dólares (USD). Sin embargo, para efectos financieros y de cálculo de remuneraciones internas, este monto debe convertirse a pesos chilenos (CLP) considerando un tipo de cambio fijo de **1 USD = $900 CLP**.

# 🧩 Desafío Adicional II: Organización, Estados Laborales y Gestión Asíncrona de Proyectos

Debido al crecimiento de **TechCorp**, la empresa necesita mejorar la organización interna de sus trabajadores y controlar de manera más precisa su disponibilidad laboral.

Actualmente, los departamentos se registran utilizando texto libre, lo que puede generar inconsistencias como `"Informatica"`, `"informática"`, `"INFO"` o valores no reconocidos por la empresa.

Además, TechCorp necesita conocer el **estado laboral actual de cada trabajador**, ya que un empleado puede encontrarse disponible, con licencia, de vacaciones o participando activamente en un proyecto.

Finalmente, debido a que la empresa desarrolla múltiples proyectos tecnológicos de manera simultánea, el sistema deberá permitir iniciar varios proyectos de forma concurrente sin bloquear la ejecución general del programa.

---

## 🏢 1. Organización de Departamentos mediante `enum class`

Para evitar inconsistencias en el registro de los departamentos, el sistema deberá implementar un `enum class` llamado:

```text
Departamento
```

Los únicos departamentos válidos dentro de TechCorp serán:

```text
INFORMATICA
RRHH
FINANZAS
MARKETING
GENERAL
```

La propiedad `departamento` definida actualmente en la clase `Empleado` deberá dejar de utilizar `String` y deberá utilizar el nuevo tipo `Departamento`.

Por ejemplo:

```kotlin
Departamento.INFORMATICA
Departamento.RRHH
Departamento.FINANZAS
```

El departamento por defecto de un empleado continuará siendo:

```kotlin
Departamento.GENERAL
```

De esta forma, el sistema deberá impedir de manera natural que un trabajador pueda ser registrado en un departamento que no exista dentro de la organización.

---

## 👤 2. Estados Laborales mediante `sealed class`

TechCorp necesita conocer en todo momento la situación laboral de cada trabajador.

Para representar estos estados deberás implementar una `sealed class` llamada:

```text
EstadoLaboral
```

Un empleado podrá encontrarse solamente en uno de los siguientes estados:

### 🟢 Disponible

El trabajador se encuentra disponible y puede ser asignado a un nuevo proyecto.

Este estado no necesita almacenar información adicional.

---

### 🟡 Licencia

El trabajador se encuentra temporalmente ausente debido a una licencia.

Este estado deberá almacenar:

* `dias`: `Int`

Representa la cantidad de días de licencia otorgados al trabajador.

El número de días deberá ser mayor que cero.

Ejemplo conceptual:

```text
Licencia
└── dias: 10
```

---

### 🟠 Vacaciones

El trabajador se encuentra haciendo uso de sus vacaciones legales.

Este estado deberá almacenar:

* `dias`: `Int`
* `idReemplazante`: `Int`

El identificador corresponde al trabajador que asumirá temporalmente sus responsabilidades.

El número de días deberá ser mayor que cero y el identificador del reemplazante deberá corresponder a un valor positivo.

Ejemplo conceptual:

```text
Vacaciones
├── dias: 15
└── idReemplazante: 104
```

---

### 🔵 EnProyecto

El trabajador se encuentra actualmente participando en un proyecto de TechCorp.

Este estado deberá almacenar:

* `nombreProyecto`: `String`

El nombre del proyecto no puede estar vacío.

Ejemplo conceptual:

```text
EnProyecto
└── nombreProyecto: "Sistema de Inventario"
```

---

## 🔄 3. Estado Inicial de los Trabajadores

Todos los trabajadores deberán encontrarse inicialmente en estado:

```text
Disponible
```

Por lo tanto, al crear un nuevo objeto `Empleado`, `Desarrollador` o `GerenteProyecto`, no será obligatorio indicar explícitamente su estado laboral.

Ejemplo conceptual:

```text
Nuevo empleado
      │
      ▼
  Disponible
```

El estado laboral deberá poder cambiar posteriormente de acuerdo con las operaciones realizadas por la empresa.

---

## 🔍 4. Consulta del Estado Laboral

El sistema deberá ser capaz de identificar correctamente el estado actual de cualquier trabajador.

Para ello, se deberá utilizar un `when` que contemple todos los posibles estados definidos en `EstadoLaboral`.

La información mostrada dependerá del estado actual.

### Ejemplos esperados

Para un trabajador disponible:

```text
Juan Pérez se encuentra disponible.
```

Para un trabajador con licencia:

```text
Sandra López se encuentra con licencia por 10 días.
```

Para un trabajador de vacaciones:

```text
Luis Soto se encuentra de vacaciones por 15 días.
Empleado reemplazante ID: 104
```

Para un trabajador asignado a un proyecto:

```text
María Torres está trabajando actualmente en:
Sistema de Inventario
```

El sistema deberá aprovechar las características de una `sealed class` para identificar cada estado y acceder a la información específica que contiene.

---

# 💻 5. Gestión de Proyectos

TechCorp trabaja constantemente en nuevos desarrollos tecnológicos.

Por esta razón, la clase `Empresa` deberá incorporar una nueva operación denominada:

```text
iniciarProyecto
```

Este método deberá recibir:

* Un objeto de tipo `Empleado`.
* El nombre del proyecto que se desea ejecutar.

Conceptualmente:

```kotlin
iniciarProyecto(
    empleado,
    nombreProyecto
)
```

---

## ⚠️ Regla de Disponibilidad

Un empleado solamente podrá iniciar un proyecto cuando su estado laboral actual sea:

```text
Disponible
```

Por lo tanto:

| Estado actual | ¿Puede iniciar proyecto? |
| :------------ | :----------------------: |
| Disponible    |           ✅ Sí           |
| Licencia      |           ❌ No           |
| Vacaciones    |           ❌ No           |
| EnProyecto    |           ❌ No           |

Si el trabajador no está disponible, el sistema deberá informar la situación y no deberá iniciar el proyecto.

Ejemplo:

```text
No es posible asignar el proyecto a Sandra López.
El trabajador no se encuentra disponible.
```

---

# ⚙️ 6. Ejecución Asíncrona de Proyectos mediante Corrutinas

Los proyectos de TechCorp representan procesos que requieren tiempo para completarse.

Para simular este comportamiento, la ejecución de un proyecto deberá utilizar **corrutinas de Kotlin**.

Cuando un trabajador inicia correctamente un proyecto, deberán ocurrir las siguientes etapas:

### Etapa 1 — Validación

El sistema verifica que el trabajador esté:

```text
Disponible
```

Si no se encuentra disponible, el proceso finaliza sin modificar su estado.

---

### Etapa 2 — Inicio del Proyecto

Si el trabajador está disponible, su estado deberá cambiar a:

```text
EnProyecto
```

almacenando el nombre correspondiente.

Por ejemplo:

```text
Disponible
     │
     ▼
EnProyecto("Sistema de Ventas")
```

El sistema deberá informar el inicio del trabajo:

```text
Juan Pérez ha comenzado el proyecto: Sistema de Ventas
```

---

### Etapa 3 — Procesamiento

La ejecución inicial del proyecto tendrá una duración simulada de:

```text
5 segundos
```

Esta espera deberá implementarse utilizando las herramientas de corrutinas de Kotlin.

Durante este tiempo, el trabajador deberá permanecer en estado:

```text
EnProyecto
```

El programa no deberá bloquear innecesariamente la ejecución de otras operaciones.

---

### Etapa 4 — Finalización

Una vez transcurridos los 5 segundos, se considerará que el proyecto ha terminado.

El trabajador deberá regresar automáticamente al estado:

```text
Disponible
```

Ejemplo:

```text
EnProyecto("Sistema de Ventas")
            │
            │ 5 segundos
            ▼
        Disponible
```

El sistema deberá informar:

```text
Juan Pérez ha terminado el proyecto: Sistema de Ventas
Juan Pérez vuelve a estar disponible.
```

---

# 🚀 7. Ejecución Simultánea de Proyectos

TechCorp puede desarrollar múltiples proyectos al mismo tiempo.

Por este motivo, el sistema deberá permitir que varios trabajadores ejecuten proyectos de forma concurrente.

Por ejemplo, si existen tres trabajadores disponibles:

```text
Juan
Sandra
María
```

la empresa podrá iniciar:

```text
Juan   → Sistema de Ventas
Sandra → Aplicación de Recursos Humanos
María  → Plataforma Financiera
```

Los proyectos no deberán ejecutarse obligatoriamente uno después del otro.

La ejecución esperada conceptualmente será:

```text
Tiempo ─────────────────────────────────────►

Juan       [ Sistema Ventas          ]
Sandra     [ Sistema RRHH            ]
María      [ Plataforma Financiera   ]

           0 s                    5 s
```

Por lo tanto, los tres proyectos podrán encontrarse activos simultáneamente.

Para lograr este comportamiento deberán utilizarse corrutinas mediante herramientas como:

```text
launch
coroutineScope
delay
```

según corresponda al diseño implementado.

---

# 🧠 8. Reglas de Negocio para la Asignación de Proyectos

Antes de comenzar cualquier proyecto, el sistema deberá validar las siguientes condiciones:

* El trabajador debe pertenecer actualmente a la nómina de la empresa.
* El trabajador debe encontrarse en estado `Disponible`.
* Un trabajador con `Licencia` no puede participar en proyectos.
* Un trabajador de `Vacaciones` no puede participar en proyectos.
* Un trabajador que ya se encuentra `EnProyecto` no puede recibir otro proyecto simultáneamente.
* El nombre del proyecto no puede estar vacío.
* Cuando un proyecto termina correctamente, el trabajador debe volver automáticamente al estado `Disponible`.

---

## 🧪 Ejemplo de Funcionamiento

Supongamos que TechCorp posee los siguientes trabajadores:

```text
Juan      → Disponible
Sandra    → Disponible
Luis      → Licencia (10 días)
María     → Vacaciones (15 días, reemplazante ID 101)
```

La empresa intenta ejecutar:

```text
Juan   → Sistema de Ventas
Sandra → Sistema de Inventario
Luis   → Aplicación Móvil
María  → Plataforma de Clientes
```

El comportamiento esperado sería:

```text
Juan ha comenzado el proyecto: Sistema de Ventas

Sandra ha comenzado el proyecto: Sistema de Inventario

No es posible asignar el proyecto a Luis.
El trabajador se encuentra con licencia.

No es posible asignar el proyecto a María.
El trabajador se encuentra de vacaciones.
```

Durante aproximadamente 5 segundos:

```text
Juan   → EnProyecto("Sistema de Ventas")
Sandra → EnProyecto("Sistema de Inventario")
Luis   → Licencia(10)
María  → Vacaciones(15, 101)
```

Posteriormente:

```text
Juan ha terminado el proyecto: Sistema de Ventas
Juan vuelve a estar disponible.

Sandra ha terminado el proyecto: Sistema de Inventario
Sandra vuelve a estar disponible.
```

Estado final:

```text
Juan   → Disponible
Sandra → Disponible
Luis   → Licencia(10)
María  → Vacaciones(15, 101)
```

---

# 📊 Resumen de Nuevos Requerimientos

| Elemento              | Implementación requerida            | Objetivo                                                    |
| :-------------------- | :---------------------------------- | :---------------------------------------------------------- |
| Departamentos         | `enum class Departamento`           | Evitar valores de departamento inválidos                    |
| Estado del trabajador | `sealed class EstadoLaboral`        | Representar situaciones laborales con información diferente |
| Disponible            | Estado sin información adicional    | Permitir asignación de proyectos                            |
| Licencia              | `dias`                              | Registrar ausencia temporal                                 |
| Vacaciones            | `dias`, `idReemplazante`            | Registrar vacaciones y reemplazo                            |
| EnProyecto            | `nombreProyecto`                    | Registrar el proyecto actualmente asignado                  |
| `iniciarProyecto()`   | Método de `Empresa`                 | Gestionar asignación de proyectos                           |
| Corrutinas            | `launch`, `delay`, `coroutineScope` | Ejecutar múltiples proyectos simultáneamente                |
| Duración simulada     | 5 segundos                          | Representar un proceso que requiere tiempo                  |

---

# 🎯 Objetivo del Desafío

Al finalizar esta ampliación, el sistema deberá demostrar correctamente la integración de:

```text
Programación Orientada a Objetos
          │
          ├── Herencia
          ├── Polimorfismo
          ├── Override
          │
          ├── enum class
          │      └── Departamento
          │
          ├── sealed class
          │      └── EstadoLaboral
          │
          ├── Colecciones
          │      └── MutableList<Empleado>
          │
          └── Corrutinas
                 ├── launch
                 ├── delay
                 └── ejecución concurrente
```

La finalidad no es únicamente ejecutar varios proyectos, sino mantener un **estado consistente de cada trabajador durante todo el proceso**, evitando que empleados no disponibles puedan recibir nuevas asignaciones.
