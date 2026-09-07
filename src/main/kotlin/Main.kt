package cl.duoc

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.time.LocalDate

fun main()= runBlocking {
    try {
        val e1 = Desarrollador(1, "Juan", 800_000.0, Departamento.INFORMATICA, "PSEINT")
        val e2 = Desarrollador(2, "Sandra",1_000_000.0, Departamento.INFORMATICA,"Kotlin",10)
        val e3 = Gerente(3, "Luis Patricio",1_500_000.0,Departamento.INFORMATICA,350000.0,4)
        val e4 = Gerente(4, "Jose Ignacio",1_500_000.0,Departamento.INFORMATICA,350000.0,4)
        val e5 = Desarrollador(5,"Maria",1_200_000.0,Departamento.INFORMATICA,"Python",3)
        val empresa = Empresa("100-1","TechCorp", LocalDate.of(2000,12,1),10000.0)

        empresa.contratar(e1)
        empresa.contratar(e2)
        empresa.contratar(e3)
        empresa.contratar(e4)
        empresa.contratar(e5)

        //agregar launch y runbloking en prueba
        launch { empresa.iniciarProyecto(e1,"formateo pc") }
        launch {empresa.iniciarProyecto(e2,"formateo note")}
        println("fin")

        //agregar rubloking en main para quitar error
        //empresa.reporte()
    }catch (e:Exception){
        println("Error: ${e.message}")
    }


}