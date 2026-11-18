/**
 * Pantalla principal (UP03 · Actividad 2 — Dice Game).
 *
 * Juego de dados: se apuesta a que la siguiente tirada es mayor, menor o un número
 * concreto. El estado (puntos, última tirada y resultado de la última jugada) se
 * guarda con `rememberSaveable` y las reglas están en el paquete `model`.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.dicegame

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.tunombretuapellido.dicegame.model.Bet
import com.tunombretuapellido.dicegame.model.Dice
import com.tunombretuapellido.dicegame.model.GameRules
import com.tunombretuapellido.dicegame.model.RoundResult
import com.tunombretuapellido.dicegame.ui.theme.DiceGameTheme

/** Única pantalla de la aplicación. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DiceGameTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    DiceGameScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Pantalla del juego: cabecera, zona de juego y pie con el nombre del autor.
 *
 * @param modifier modificador que recibe el *padding* calculado por el `Scaffold`.
 */
@Composable
fun DiceGameScreen(modifier: Modifier = Modifier) {
    var points by rememberSaveable { mutableIntStateOf(GameRules.INITIAL_POINTS) }
    // La partida empieza siempre con una tirada ya hecha
    var lastRoll by rememberSaveable { mutableIntStateOf(Dice.roll()) }
    // Resultado de la última jugada: null si todavía no se ha apostado
    var lastWon by rememberSaveable { mutableStateOf<Boolean?>(null) }
    var lastBet by rememberSaveable { mutableStateOf(Bet.HIGHER) }

    /** Aplica el resultado de una ronda al estado de la pantalla. */
    fun applyResult(result: RoundResult, bet: Bet) {
        points = result.points
        lastRoll = result.roll
        lastWon = result.won
        lastBet = bet
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(id = R.dimen.screen_padding)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing))
    ) {
        Header()

        GameStatus(points = points, lastRoll = lastRoll, lastWon = lastWon, lastBet = lastBet)

        val gameOver = GameRules.isGameOver(points)

        // Flechas: mayor o menor que la última tirada
        Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing))) {
            ArrowButton(
                symbol = stringResource(id = R.string.arrow_up),
                description = stringResource(id = R.string.bet_higher),
                enabled = !gameOver && GameRules.canAfford(points, Bet.HIGHER) &&
                    GameRules.isPossible(Bet.HIGHER, lastRoll),
                onClick = { applyResult(GameRules.play(points, Bet.HIGHER, lastRoll), Bet.HIGHER) }
            )
            ArrowButton(
                symbol = stringResource(id = R.string.arrow_down),
                description = stringResource(id = R.string.bet_lower),
                enabled = !gameOver && GameRules.canAfford(points, Bet.LOWER) &&
                    GameRules.isPossible(Bet.LOWER, lastRoll),
                onClick = { applyResult(GameRules.play(points, Bet.LOWER, lastRoll), Bet.LOWER) }
            )
        }

        // Números del 1 al 6, en dos filas de tres
        val canBetNumber = GameRules.canAfford(points, Bet.NUMBER)
        (1..Dice.FACES).chunked(3).forEach { row ->
            Row(horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing))) {
                row.forEach { number ->
                    NumberButton(
                        number = number,
                        enabled = canBetNumber,
                        onClick = {
                            applyResult(GameRules.play(points, Bet.NUMBER, lastRoll, number = number), Bet.NUMBER)
                        }
                    )
                }
            }
        }

        if (gameOver) {
            Text(
                text = stringResource(id = R.string.game_over),
                fontSize = dimensionResource(id = R.dimen.title_text).value.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.error
            )
        }
        Button(
            onClick = {
                points = GameRules.INITIAL_POINTS
                lastRoll = Dice.roll()
                lastWon = null
            },
            enabled = gameOver
        ) {
            Text(text = stringResource(id = R.string.restart))
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing)))
        Text(
            text = stringResource(id = R.string.author),
            style = MaterialTheme.typography.bodySmall
        )
    }
}

/** Título del juego y explicación de las reglas. */
@Composable
fun Header() {
    Text(
        text = stringResource(id = R.string.game_title),
        fontSize = dimensionResource(id = R.dimen.title_text).value.sp,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
    Text(
        text = stringResource(R.string.rules,
            Bet.HIGHER.cost, Bet.HIGHER.prize, Bet.NUMBER.cost, Bet.NUMBER.prize
        ),
        textAlign = TextAlign.Center
    )
}

/**
 * Puntos, última tirada y resultado de la última apuesta.
 *
 * @param points puntos actuales.
 * @param lastRoll última tirada.
 * @param lastWon resultado de la última apuesta, o null al empezar la partida.
 * @param lastBet tipo de la última apuesta, para saber cuánto se ganó o se perdió.
 */
@Composable
fun GameStatus(points: Int, lastRoll: Int, lastWon: Boolean?, lastBet: Bet) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(id = R.dimen.card_padding)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.points, points),
                fontSize = dimensionResource(id = R.dimen.title_text).value.sp
            )
            Text(text = stringResource(id = R.string.last_roll))
            Text(
                text = lastRoll.toString(),
                fontSize = dimensionResource(id = R.dimen.dice_text).value.sp,
                fontWeight = FontWeight.Bold
            )
            val message = when (lastWon) {
                null -> stringResource(id = R.string.first_roll)
                true -> stringResource(R.string.won, lastBet.prize)
                false -> stringResource(R.string.lost, lastBet.cost)
            }
            Text(text = message, textAlign = TextAlign.Center)
        }
    }
}

/**
 * Botón de apuesta con flecha.
 *
 * @param symbol flecha que se muestra.
 * @param description descripción para la accesibilidad (TalkBack).
 * @param enabled si se puede pulsar.
 * @param onClick acción al pulsar.
 */
@Composable
fun ArrowButton(symbol: String, description: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .size(dimensionResource(id = R.dimen.arrow_button))
            .semantics { contentDescription = description }
    ) {
        Text(text = symbol, fontSize = dimensionResource(id = R.dimen.title_text).value.sp)
    }
}

/**
 * Botón para apostar a un número concreto.
 *
 * @param number número al que se apuesta.
 * @param enabled si se puede pulsar.
 * @param onClick acción al pulsar.
 */
@Composable
fun NumberButton(number: Int, enabled: Boolean, onClick: () -> Unit) {
    FilledTonalButton(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.size(dimensionResource(id = R.dimen.number_button))
    ) {
        Text(text = number.toString(), fontSize = dimensionResource(id = R.dimen.title_text).value.sp)
    }
}
