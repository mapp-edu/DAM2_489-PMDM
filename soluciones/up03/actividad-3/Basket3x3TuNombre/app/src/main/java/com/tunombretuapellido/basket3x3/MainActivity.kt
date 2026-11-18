/**
 * Pantalla principal (UP03 · Actividad 3 — Basket 3x3).
 *
 * Marcador de baloncesto 3x3 bloqueado en horizontal (ver `AndroidManifest.xml`). El
 * estado es un historial de `MatchState` guardado con `rememberSaveable`: el último
 * elemento es el marcador actual y deshacer elimina el último elemento.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.basket3x3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.tunombretuapellido.basket3x3.model.MatchState
import com.tunombretuapellido.basket3x3.model.Team
import com.tunombretuapellido.basket3x3.ui.HistorySaver
import com.tunombretuapellido.basket3x3.ui.TeamPanel
import com.tunombretuapellido.basket3x3.ui.theme.Basket3x3Theme

/** Única pantalla de la aplicación. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Basket3x3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScoreboardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Marcador completo: un panel por equipo y, en el centro, los botones de deshacer y resetear.
 *
 * @param modifier modificador que recibe el *padding* calculado por el `Scaffold`.
 */
@Composable
fun ScoreboardScreen(modifier: Modifier = Modifier) {
    // Historial de estados; nunca está vacío
    var history by rememberSaveable(stateSaver = HistorySaver) {
        mutableStateOf(listOf(MatchState()))
    }
    val match = history.last()

    /** Guarda el nuevo estado si ha cambiado (así deshacer no se «gasta» en acciones sin efecto). */
    fun push(newState: MatchState) {
        if (newState != match) history = history + newState
    }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .padding(dimensionResource(id = R.dimen.screen_padding)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.small_spacing))
    ) {
        Team.entries.forEachIndexed { index, team ->
            if (index == 1) {
                // Columna central entre los dos equipos
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedButton(
                        onClick = { history = history.dropLast(1) },
                        enabled = history.size > 1
                    ) {
                        Text(text = stringResource(id = R.string.undo))
                    }
                    Button(onClick = { history = listOf(MatchState()) }) {
                        Text(text = stringResource(id = R.string.reset))
                    }
                }
            }
            TeamPanel(
                name = stringResource(id = if (team == Team.HOME) R.string.home else R.string.away),
                state = match.team(team),
                color = colorResource(id = if (team == Team.HOME) R.color.home else R.color.away),
                isWinner = match.winner == team,
                enabled = !match.isFinished,
                onScore = { points -> push(match.addPoints(team, points)) },
                onAddFoul = { push(match.addFoul(team)) },
                onRemoveFoul = { push(match.removeFoul(team)) },
                modifier = Modifier.weight(1f)
            )
        }
    }
}
