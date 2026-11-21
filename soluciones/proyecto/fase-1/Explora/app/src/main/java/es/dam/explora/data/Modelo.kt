package es.dam.explora.data

import androidx.annotation.DrawableRes

/** Dificultad de un reto: multiplica los puntos base. */
enum class Dificultad(val multiplicador: Int) {
    FACIL(1), MEDIA(2), DIFICIL(3)
}

/** Tipos de reto: jerarquía cerrada (mejora: sealed interface, UP02 · vídeo 3). */
sealed interface TipoReto {
    data object Visita : TipoReto
    data class Foto(val objeto: String) : TipoReto
    data class Sonido(val segundos: Int) : TipoReto
}

data class Reto(
    val titulo: String,
    val tipo: TipoReto,
    val dificultad: Dificultad,
    val puntosBase: Int = 10
) {
    /** Propiedad calculada: no se guarda, se obtiene a partir de otros datos. */
    val puntos: Int
        get() = puntosBase * dificultad.multiplicador
}

data class Lugar(
    val id: Int,
    val nombre: String,
    val ciudad: String,
    @DrawableRes val imagen: Int,
    val retos: List<Reto>,
    val visitado: Boolean = false
) {
    val puntos: Int
        get() = retos.sumOf { it.puntos }
}
