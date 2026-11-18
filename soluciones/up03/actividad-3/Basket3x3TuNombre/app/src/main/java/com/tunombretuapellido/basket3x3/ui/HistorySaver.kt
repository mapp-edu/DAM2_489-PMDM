/**
 * Guardado del historial (UP03 · Actividad 3 — Basket 3x3).
 *
 * `rememberSaveable` solo sabe guardar tipos que caben en un `Bundle`. Este `Saver`
 * convierte la lista de estados en una lista de enteros y al revés.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.basket3x3.ui

import androidx.compose.runtime.saveable.Saver
import com.tunombretuapellido.basket3x3.model.MatchState

/** Guarda el historial de estados del marcador como una lista plana de enteros. */
val HistorySaver = Saver<List<MatchState>, ArrayList<Int>>(
    save = { history -> ArrayList(history.flatMap { it.toIntList() }) },
    restore = { ints -> ints.chunked(MatchState.INT_SIZE).map { MatchState.fromIntList(it) } }
)
