/**
 * Lógica de los contadores (UP03 · Actividad 1 — Estadísticas).
 *
 * Funciones puras, sin dependencias de Android, para poder probarlas con tests
 * unitarios en `src/test`.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.statistics.model

/** Operaciones sobre los contadores de la calle. */
object Statistics {

    /** Valor máximo que asigna el botón Randomize. */
    const val MAX_RANDOM = 10

    /**
     * Suma uno al contador.
     *
     * @param count valor actual.
     * @return el valor incrementado.
     */
    fun increment(count: Int): Int = count + 1

    /**
     * Resta uno al contador sin bajar nunca de cero.
     *
     * @param count valor actual.
     * @return el valor decrementado, como mínimo 0.
     */
    fun decrement(count: Int): Int = maxOf(0, count - 1)

    /**
     * Calcula el porcentaje que representa un contador sobre el total.
     *
     * @param count valor del contador.
     * @param total suma de todos los contadores.
     * @return el porcentaje entre 0 y 100; 0 si el total es 0 (evita dividir entre cero).
     */
    fun percentage(count: Int, total: Int): Double =
        if (total == 0) 0.0 else count * 100.0 / total

    /**
     * Genera un valor aleatorio para un contador.
     *
     * @return un entero entre 0 y [MAX_RANDOM], ambos incluidos.
     */
    fun randomCount(): Int = (0..MAX_RANDOM).random()
}
