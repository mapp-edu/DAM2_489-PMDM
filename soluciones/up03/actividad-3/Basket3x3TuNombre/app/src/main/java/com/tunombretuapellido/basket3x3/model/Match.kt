/**
 * Modelo del marcador (UP03 · Actividad 3 — Basket 3x3).
 *
 * Clases inmutables: cada acción devuelve un estado nuevo en lugar de modificar el
 * actual. Así la interfaz se recompone al cambiar el estado y deshacer consiste en
 * volver al estado anterior de una lista (historial).
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.basket3x3.model

/** Equipos del partido. */
enum class Team { HOME, AWAY }

/** Reglas del baloncesto 3x3 que usa el marcador. */
object MatchRules {
    /** Puntos con los que se gana el partido. */
    const val WINNING_SCORE = 21

    /** Máximo de faltas de equipo que se registran; con este número se entra en bonus. */
    const val MAX_FOULS = 7

    /** Número de jugadores de cada equipo (3 en pista y 1 suplente). */
    const val PLAYERS = 4
}

/**
 * Situación de un equipo.
 *
 * @property score puntos anotados.
 * @property fouls faltas de equipo (0 a [MatchRules.MAX_FOULS]).
 */
data class TeamState(val score: Int = 0, val fouls: Int = 0) {
    /** El equipo está en bonus: sus faltas se castigan con 2 tiros libres. */
    val inBonus: Boolean get() = fouls >= MatchRules.MAX_FOULS

    /** El equipo ha ganado el partido. */
    val hasWon: Boolean get() = score >= MatchRules.WINNING_SCORE
}

/**
 * Estado completo del marcador.
 *
 * @property home equipo local.
 * @property away equipo visitante.
 */
data class MatchState(
    val home: TeamState = TeamState(),
    val away: TeamState = TeamState(),
) {
    /** Ganador del partido, o null si todavía se está jugando. */
    val winner: Team?
        get() = when {
            home.hasWon -> Team.HOME
            away.hasWon -> Team.AWAY
            else -> null
        }

    /** El partido ha terminado: solo se puede resetear (o deshacer). */
    val isFinished: Boolean get() = winner != null

    /**
     * Devuelve el estado de un equipo.
     *
     * @param team equipo.
     * @return su estado.
     */
    fun team(team: Team): TeamState = if (team == Team.HOME) home else away

    /**
     * Suma puntos a un equipo. No hace nada si el partido ha terminado.
     *
     * @param team equipo que anota.
     * @param points 1 (tiro libre o canasta dentro del arco) o 2 (fuera del arco).
     * @return el nuevo estado.
     */
    fun addPoints(team: Team, points: Int): MatchState {
        require(points == 1 || points == 2) { "En 3x3 solo hay canastas de 1 y 2 puntos" }
        if (isFinished) return this
        return update(team) { it.copy(score = it.score + points) }
    }

    /**
     * Añade una falta de equipo, como máximo hasta [MatchRules.MAX_FOULS].
     *
     * @param team equipo que comete la falta.
     * @return el nuevo estado.
     */
    fun addFoul(team: Team): MatchState {
        if (isFinished) return this
        return update(team) { it.copy(fouls = minOf(MatchRules.MAX_FOULS, it.fouls + 1)) }
    }

    /**
     * Quita una falta de equipo (por si se marcó por error), como mínimo hasta 0.
     *
     * @param team equipo.
     * @return el nuevo estado.
     */
    fun removeFoul(team: Team): MatchState {
        if (isFinished) return this
        return update(team) { it.copy(fouls = maxOf(0, it.fouls - 1)) }
    }

    /**
     * Convierte el estado en una lista de enteros para poder guardarlo en un `Bundle`
     * (lo usa el `Saver` de la interfaz).
     *
     * @return [puntos local, faltas local, puntos visitante, faltas visitante].
     */
    fun toIntList(): List<Int> = listOf(home.score, home.fouls, away.score, away.fouls)

    private fun update(team: Team, change: (TeamState) -> TeamState): MatchState =
        if (team == Team.HOME) copy(home = change(home)) else copy(away = change(away))

    companion object {
        /** Número de enteros que ocupa un estado en [toIntList]. */
        const val INT_SIZE = 4

        /**
         * Reconstruye un estado a partir de [toIntList].
         *
         * @param ints lista de [INT_SIZE] enteros.
         * @return el estado.
         */
        fun fromIntList(ints: List<Int>): MatchState = MatchState(
            home = TeamState(score = ints[0], fouls = ints[1]),
            away = TeamState(score = ints[2], fouls = ints[3]),
        )
    }
}
