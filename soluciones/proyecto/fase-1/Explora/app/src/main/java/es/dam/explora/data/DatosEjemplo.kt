package es.dam.explora.data

import es.dam.explora.R

/**
 * Datos en memoria para la fase 1. En la fase 2 vendrán de una base de datos o de la red,
 * por eso no se traducen: son contenido, no textos de la interfaz.
 */
val lugaresEjemplo = listOf(
    Lugar(
        id = 1,
        nombre = "Lonja de la Seda",
        ciudad = "Valencia",
        imagen = R.drawable.lugar_lonja,
        retos = listOf(
            Reto("Entra en la sala de columnas", TipoReto.Visita, Dificultad.FACIL),
            Reto("Fotografía una gárgola", TipoReto.Foto("gárgola"), Dificultad.DIFICIL)
        )
    ),
    Lugar(
        id = 2,
        nombre = "Castillo de Sagunto",
        ciudad = "Sagunt",
        imagen = R.drawable.lugar_castillo,
        retos = listOf(
            Reto("Sube a la plaza de armas", TipoReto.Visita, Dificultad.MEDIA),
            Reto("Fotografía el teatro romano", TipoReto.Foto("teatro romano"), Dificultad.DIFICIL)
        )
    ),
    Lugar(
        id = 3,
        nombre = "Faro de Cullera",
        ciudad = "Cullera",
        imagen = R.drawable.lugar_faro,
        retos = listOf(
            Reto("Llega al faro", TipoReto.Visita, Dificultad.FACIL),
            Reto("Graba las olas", TipoReto.Sonido(15), Dificultad.FACIL),
            Reto("Fotografía la bahía", TipoReto.Foto("bahía"), Dificultad.MEDIA)
        )
    ),
    Lugar(
        id = 4,
        nombre = "L'Albufera",
        ciudad = "Valencia",
        imagen = R.drawable.lugar_albufera,
        retos = listOf(
            Reto("Visita el embarcadero del Palmar", TipoReto.Visita, Dificultad.FACIL),
            Reto("Fotografía una barca al atardecer", TipoReto.Foto("barca"), Dificultad.DIFICIL, puntosBase = 15)
        )
    ),
    Lugar(
        id = 5,
        nombre = "Palmeral d'Elx",
        ciudad = "Elx",
        imagen = R.drawable.lugar_palmeral,
        retos = listOf(
            Reto("Encuentra la palmera imperial", TipoReto.Visita, Dificultad.MEDIA),
            Reto("Graba el canto de los pájaros", TipoReto.Sonido(30), Dificultad.MEDIA)
        )
    ),
    Lugar(
        id = 6,
        nombre = "Castillo de Peñíscola",
        ciudad = "Peñíscola",
        imagen = R.drawable.lugar_peniscola,
        retos = listOf(
            Reto("Recorre la muralla", TipoReto.Visita, Dificultad.MEDIA),
            Reto("Fotografía el bufador", TipoReto.Foto("bufador"), Dificultad.DIFICIL),
            Reto("Graba el mar desde las almenas", TipoReto.Sonido(20), Dificultad.FACIL)
        )
    )
)
