package cl.duoc

open class Empleado (
    val id: Int,
    val nombre: String,
    var salarioBase: Double,
    var departamento: Departamento = Departamento.GENERAL
)
{
    var estadoLaboral: EstadoLaboral = EstadoLaboral.Disponible
    constructor (id: Int, nombre: String): this(id, nombre,950_000.0, Departamento.GENERAL)
    init {
        require(id>0){ "ID debe ser mayor a 0"}
        require(nombre.isNotBlank()) { "Nombre no puede estar vacio" }
        require(salarioBase >= 800000) { "El salario deber ser $800.000 o superior" }
    }

    open fun calcularSalarioTotal(): Double{
        return salarioBase
    }
    fun mostrarFichaLaboral(){
        println("""
            ID: $id
            Nombre: $nombre
            Departamento: ${departamento}
            Salario: $salarioBase
            Salario liquido: ${calcularSalarioTotal()}.
        """.trimIndent())
    }
    open fun reportarActividad(){
        var estado = listOf("Estoy trabajando", "Estoy en el baño", "Estoy almorzando")
        println(estado.random())
    }
}

