package cl.duoc

class Desarrollador(
    id: Int,
    nombre: String,
    salarioBase: Double,
    departamento: Departamento,
    var lenguajePrincipal: String,
    var horasExtra: Int = 0
): Empleado(id, nombre, salarioBase, departamento)  {
    val pagoHoraExtra = 15_000
    init{
        require(horasExtra >= 0) { "Las horas extras no pueden ser menor a 0"}
    }
    override fun calcularSalarioTotal(): Double{
        var pagoTotalExtra = pagoHoraExtra * horasExtra
        var total = salarioBase + pagoTotalExtra
        return total
    }
    override fun reportarActividad(){
        println("Estoy programando en $lenguajePrincipal")
    }
}