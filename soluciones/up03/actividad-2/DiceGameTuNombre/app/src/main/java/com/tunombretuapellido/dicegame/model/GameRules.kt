/**
 * Reglas del juego (UP03 · Actividad 2 — Dice Game).
 *
 * Toda la lógica está aquí, separada de la interfaz y sin dependencias de Android:
 * la pantalla solo guarda el estado y llama a estas funciones, y los tests de
 * `src/test` comprueban las reglas sin necesidad de emulador.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.dicegame.model

/**
 * Tipos de apuesta con lo que cuestan y lo que se gana si se acierta.
 *
 * @property cost puntos que se gastan al apostar.
 * @property prize puntos que se ganan si se acierta.
 */
enum class Bet(val cost: Int, val prize: Int) {
    /** La siguiente tirada será mayor que la anterior. */
    HIGHER(cost = 1, prize = 3),

    /** La siguiente tirada será menor que la anterior. */
    LOWER(cost = 1, prize = 3),

    /** La siguiente tirada será un número concreto. */
    NUMBER(cost = 3, prize = 15),
}

/**
 * Resultado de una jugada.
 *
 * @property roll número que ha salido.
 * @property won true si se ha acertado la apuesta.
 * @property points puntos del jugador después de la jugada.
 */
data class RoundResult(val roll: Int, val won: Boolean, val points: Int)

/** Reglas del juego de dados. */
object GameRules {

    /** Puntos con los que empieza la partida. */
    const val INITIAL_POINTS = 20

    /**
     * Indica si el jugador tiene puntos suficientes para la apuesta. Como nunca se
     * puede apostar sin poder pagarla, los puntos nunca son negativos.
     *
     * @param points puntos actuales.
     * @param bet apuesta que se quiere hacer.
     * @return true si los puntos cubren el coste.
     */
    fun canAfford(points: Int, bet: Bet): Boolean = points >= bet.cost

    /**
     * Indica si una apuesta puede ganarse: tras un 6 no puede salir nada mayor y tras
     * un 1 nada menor, así que esas flechas se desactivan.
     *
     * @param bet apuesta.
     * @param previous última tirada.
     * @return true si existe algún resultado ganador.
     */
    fun isPossible(bet: Bet, previous: Int): Boolean = when (bet) {
        Bet.HIGHER -> previous < Dice.FACES
        Bet.LOWER -> previous > 1
        Bet.NUMBER -> true
    }

    /**
     * La partida termina cuando el jugador se queda sin puntos.
     *
     * @param points puntos actuales.
     * @return true si no quedan puntos.
     */
    fun isGameOver(points: Int): Boolean = points <= 0

    /**
     * Juega una ronda: cobra la apuesta, lanza el dado y paga el premio si se acierta.
     *
     * @param points puntos antes de apostar.
     * @param bet tipo de apuesta.
     * @param previous última tirada (para las flechas).
     * @param number número elegido (solo para [Bet.NUMBER]).
     * @param roll tirada; por defecto se lanza el dado (se puede fijar en los tests).
     * @return el resultado de la ronda.
     * @throws IllegalArgumentException si el jugador no puede pagar la apuesta.
     */
    fun play(
        points: Int,
        bet: Bet,
        previous: Int,
        number: Int = 0,
        roll: Int = Dice.roll(),
    ): RoundResult {
        require(canAfford(points, bet)) { "No hay puntos suficientes para la apuesta" }
        val won = when (bet) {
            Bet.HIGHER -> roll > previous
            Bet.LOWER -> roll < previous
            Bet.NUMBER -> roll == number
        }
        val newPoints = points - bet.cost + if (won) bet.prize else 0
        return RoundResult(roll = roll, won = won, points = newPoints)
    }
}
