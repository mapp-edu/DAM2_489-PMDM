/**
 * Mitad de la pantalla de un equipo (UP03 · Actividad 3 — Basket 3x3).
 *
 * Componente reutilizable: se usa una vez para el equipo local y otra para el visitante.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.basket3x3.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tunombretuapellido.basket3x3.R
import com.tunombretuapellido.basket3x3.model.MatchRules
import com.tunombretuapellido.basket3x3.model.TeamState

/**
 * Marcador, faltas y jugadores de un equipo.
 *
 * @param name nombre del equipo.
 * @param state puntos y faltas del equipo.
 * @param color color del equipo.
 * @param isWinner true si este equipo ha ganado (se resalta).
 * @param enabled false cuando el partido ha terminado: se desactivan los botones.
 * @param onScore se llama con 1 o 2 puntos al anotar.
 * @param onAddFoul añade una falta.
 * @param onRemoveFoul quita una falta.
 * @param modifier modificador externo.
 */
@Composable
fun TeamPanel(
    name: String,
    state: TeamState,
    color: Color,
    isWinner: Boolean,
    enabled: Boolean,
    onScore: (Int) -> Unit,
    onAddFoul: () -> Unit,
    onRemoveFoul: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxSize(),
        colors = CardDefaults.cardColors(
            containerColor = color.copy(alpha = if (isWinner) 0.45f else 0.12f)
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(dimensionResource(id = R.dimen.panel_padding)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_spacing))
        ) {
            // Nombre y marcador
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = name,
                    fontSize = dimensionResource(id = R.dimen.team_text).value.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = state.score.toString(),
                    fontSize = dimensionResource(id = R.dimen.score_text).value.sp,
                    fontWeight = FontWeight.Black,
                    color = color,
                    modifier = Modifier.padding(start = dimensionResource(id = R.dimen.panel_padding))
                )
            }
            if (isWinner) {
                Text(
                    text = stringResource(id = R.string.winner),
                    fontSize = dimensionResource(id = R.dimen.team_text).value.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            FoulsRow(
                fouls = state.fouls,
                inBonus = state.inBonus,
                enabled = enabled,
                onAddFoul = onAddFoul,
                onRemoveFoul = onRemoveFoul
            )

            // Jugadores en una cuadrícula de 2 x 2
            (1..MatchRules.PLAYERS).chunked(2).forEach { pair ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    pair.forEach { player ->
                        PlayerScorer(
                            number = player,
                            color = color,
                            enabled = enabled,
                            onScore = onScore
                        )
                    }
                }
            }
        }
    }
}

/**
 * Faltas de equipo con los botones para añadir y quitar, y el aviso de bonus.
 *
 * @param fouls faltas actuales.
 * @param inBonus true si el equipo está en bonus.
 * @param enabled si se pueden pulsar los botones.
 * @param onAddFoul añade una falta.
 * @param onRemoveFoul quita una falta.
 */
@Composable
fun FoulsRow(
    fouls: Int,
    inBonus: Boolean,
    enabled: Boolean,
    onAddFoul: () -> Unit,
    onRemoveFoul: () -> Unit,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_spacing))
    ) {
        OutlinedButton(onClick = onRemoveFoul, enabled = enabled && fouls > 0) {
            Text(text = stringResource(id = R.string.remove_foul))
        }
        Text(text = stringResource(R.string.fouls, fouls, MatchRules.MAX_FOULS))
        OutlinedButton(onClick = onAddFoul, enabled = enabled && fouls < MatchRules.MAX_FOULS) {
            Text(text = stringResource(id = R.string.add_foul))
        }
        if (inBonus) {
            Text(
                text = stringResource(id = R.string.bonus),
                color = MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

/**
 * Nombre de un jugador y sus botones para anotar 1 y 2 puntos.
 *
 * @param number número del jugador (1 a 4).
 * @param color color del equipo.
 * @param enabled si se pueden pulsar los botones.
 * @param onScore se llama con los puntos anotados.
 */
@Composable
fun PlayerScorer(number: Int, color: Color, enabled: Boolean, onScore: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = stringResource(R.string.player, number))
        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_spacing))) {
            Button(
                onClick = { onScore(1) },
                enabled = enabled,
                colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = Color.White)
            ) {
                Text(text = stringResource(id = R.string.one_point))
            }
            FilledTonalButton(
                onClick = { onScore(2) },
                enabled = enabled
            ) {
                Text(text = stringResource(id = R.string.two_points))
            }
        }
    }
}
