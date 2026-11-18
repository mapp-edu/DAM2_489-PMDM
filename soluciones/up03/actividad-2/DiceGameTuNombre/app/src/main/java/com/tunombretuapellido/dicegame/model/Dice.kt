/**
 * Dado de 6 caras (UP03 · Actividad 2 — Dice Game).
 *
 * Clase del paquete `model` pedida en el enunciado.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.dicegame.model

/** Dado de seis caras. */
class Dice {
    companion object {
        /** Número de caras del dado. */
        const val FACES = 6

        /**
         * Lanza el dado.
         *
         * @return un número aleatorio entre 1 y [FACES], ambos incluidos.
         */
        fun roll(): Int = (1..FACES).random()
    }
}
