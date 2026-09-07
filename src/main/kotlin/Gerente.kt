package cl.duoc

class Gerente(
    id: Int,
    nombre: String,
    salarioBase: Double,
    departamento: Departamento,
    var bonoPorObjetivo: Double,
    var proyectosActivos: Int = 1
): Empleado(id, nombre, salarioBase, departamento) {
    init {
        require(bonoPorObjetivo >= 0) {"Bono de ser mayor o igual 0"}
        require(proyectosActivos >= 1){ "Proyectos activos deben ser mayor o igual a 1"}
    }

    override fun calcularSalarioTotal(): Double {
        var bonoFinal = bonoPorObjetivo * proyectosActivos
        return salarioBase + bonoFinal
    }
}