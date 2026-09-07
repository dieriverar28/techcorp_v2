package cl.duoc

import kotlinx.coroutines.delay
import java.time.LocalDate
import java.time.Period

const val DOLAR = 900

class Empresa(var rut: String,var nombre: String, var fechaFundacion: LocalDate, var presupuesto: Double) {
    private val nomina = mutableListOf<Empleado>()

    fun contratar(empleado: Empleado) {
        //REGLAS DE NEGOCIO:

        if (empleado.calcularSalarioTotal() + calcularSalarios() > presupuesto*DOLAR){
            println("El empleado ${empleado.nombre} no se puede contratar porque excede el presupuesto.")
            return
        }
        if (empleado is Gerente && nomina.count { it is Gerente } == nomina.count { it is Desarrollador }) {
            println("No se pueden contratar más gerentes que desarrolladores")
            return
        }

        nomina.add(empleado)
    }
    fun despedir(empleado: Empleado) {
        nomina.remove(empleado)
    }
    fun calcularSalarios(): Double{
        return nomina.sumOf { it.calcularSalarioTotal() }
    }
    fun reporte(){
        println("""
            Rut: $rut
            Nombre: $nombre
            Antiguedad: ${Period.between(fechaFundacion,LocalDate.now()).years} años
            Presupuesto (USD): $$presupuesto
            Presupuesto (CLP): $${presupuesto * DOLAR}
            Gastos mensuales: $${calcularSalarios()}
            Total empleados: ${nomina.size}
              - Desarrolladores: ${nomina.count { it is Desarrollador }}
              - Gerentes: ${nomina.count { it is Gerente }}
        """.trimIndent())
    }

    // 5. Gestión de Proyectos //corrutina/coroutines
    suspend fun iniciarProyecto(empleado: Empleado, nombreProyecto: String) {
        if(empleado.estadoLaboral is EstadoLaboral.Disponible){
            empleado.estadoLaboral = EstadoLaboral.EnProyecto(nombreProyecto)
            println("${nombre} ha comenzado proyecto")
            delay(5000) //agregar suspend al lado de fun para sacar error
            empleado.estadoLaboral = EstadoLaboral.Disponible
        }
        else{
            println("No es posible asignar el proyecto a ${empleado.nombre}.\n" +
                    "El trabajador no se encuentra disponible.")
        }
    }
}