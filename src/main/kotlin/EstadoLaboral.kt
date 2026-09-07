package cl.duoc

//2. Estados Laborales mediante sealed class
//sirve para definir estados/elementos bien limitados
sealed class EstadoLaboral {
    data object Disponible : EstadoLaboral()
    data class Licencia(var dias : Int): EstadoLaboral()
    data class Vacaciones(var dias : Int, var idReemplazante: Int): EstadoLaboral()
    data class EnProyecto(var nombreProyecto : String): EstadoLaboral()
}