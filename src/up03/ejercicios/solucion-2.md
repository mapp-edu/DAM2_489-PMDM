---
layout: doc
title: "Solución Actividad 2 — Dice Game"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 2 — Dice Game

Solución de referencia de la [Actividad 2](./#actividad-2) del boletín. Hay muchas soluciones correctas: úsala para **comparar** con la tuya después de intentarlo, no para copiarla.

::: tip-box Descarga
Proyecto completo: <a :href="withBase('/descargas/up03/soluciones-up03-actividad-2.zip')" download>soluciones-up03-actividad-2.zip</a>. Descomprímelo y, en Android Studio, elige **File → Open** y selecciona la carpeta `DiceGameTuNombre`. Al sincronizar, Android Studio descarga Gradle; si tu versión es más reciente, acepta la actualización que propone el *AGP Upgrade Assistant*.
:::

Paquete: `com.tunombretuapellido.dicegame` · SDK mínimo: API 28.

## Claves de la solución

- `model/Dice.kt`: clase `Dice` con `companion object` y `roll()`, como pide el enunciado.
- `model/GameRules.kt`:
  - `enum class Bet(cost, prize)`: `HIGHER`/`LOWER` cuestan 1 y pagan 3; `NUMBER` cuesta 3 y paga 15.
  - `play(...)` cobra la apuesta, tira el dado y paga el premio. Recibe la tirada como parámetro opcional para poder fijarla en los tests.
  - `canAfford` impide apostar sin puntos suficientes, así que **nunca hay puntos negativos**.
  - `isPossible` desactiva la flecha ▲ tras un 6 y la ▼ tras un 1, porque no se pueden ganar.
- Casos límite:
  - Si la tirada es **igual** a la anterior, las flechas pierden.
  - Con 1 o 2 puntos solo se pueden usar las flechas.
  - Con 0 puntos aparece **FIN DE LA PARTIDA** y se activa **Volver a empezar**.
- La partida empieza con una tirada ya hecha (`rememberSaveable { mutableIntStateOf(Dice.roll()) }`).
- Idiomas: inglés, español y valenciano (`values-ca`). Título inventado: *¿Más, menos o clavado?*.
- Las flechas son caracteres (▲ ▼) con `contentDescription` para TalkBack, sin depender de la librería de iconos.

## Comprueba tu versión

| Prueba | Resultado esperado |
|:---|:---|
| Quedarse con 1-2 puntos | No se puede apostar a un número |
| Llegar a 0 puntos | No se puede seguir; aparece reiniciar |
| Tirada igual a la anterior y apuesta con flecha | Se pierde |
| Cambiar el idioma del emulador a valenciano/catalán | Textos en valenciano |

## Código

### `MainActivity.kt`

```kotlin [MainActivity.kt]
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
```

### `model/Dice.kt`

```kotlin [model/Dice.kt]
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
```

### `model/GameRules.kt`

```kotlin [model/GameRules.kt]
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
```

## Tests unitarios

La lógica del paquete `model` no depende de Android, así que se prueba con JUnit sin emulador: clic derecho sobre la carpeta `test` → **Run 'Tests in ...'**.

```kotlin [GameRulesTest.kt]
package com.tunombretuapellido.dicegame.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

/** Tests de las reglas del juego (las tiradas se fijan para que sean deterministas). */
class GameRulesTest {

    @Test
    fun diceRollIsBetweenOneAndSix() {
        val rolls = List(1000) { Dice.roll() }
        assertTrue(rolls.all { it in 1..6 })
        assertEquals(6, rolls.toSet().size)
    }

    @Test
    fun higherWinsThreePointsMinusCost() {
        val result = GameRules.play(points = 20, bet = Bet.HIGHER, previous = 3, roll = 5)
        assertTrue(result.won)
        assertEquals(22, result.points)
    }

    @Test
    fun equalRollLosesArrowBets() {
        assertFalse(GameRules.play(20, Bet.HIGHER, previous = 4, roll = 4).won)
        assertFalse(GameRules.play(20, Bet.LOWER, previous = 4, roll = 4).won)
        assertEquals(19, GameRules.play(20, Bet.LOWER, previous = 4, roll = 4).points)
    }

    @Test
    fun numberBetPaysFifteen() {
        val win = GameRules.play(points = 20, bet = Bet.NUMBER, previous = 1, number = 6, roll = 6)
        assertEquals(32, win.points)
        val lose = GameRules.play(points = 20, bet = Bet.NUMBER, previous = 1, number = 6, roll = 2)
        assertEquals(17, lose.points)
    }

    @Test
    fun cannotBetWithoutEnoughPoints() {
        assertFalse(GameRules.canAfford(2, Bet.NUMBER))
        assertTrue(GameRules.canAfford(1, Bet.HIGHER))
        assertFalse(GameRules.canAfford(0, Bet.LOWER))
    }

    @Test(expected = IllegalArgumentException::class)
    fun playingWithoutPointsThrows() {
        GameRules.play(points = 0, bet = Bet.HIGHER, previous = 3)
    }

    @Test
    fun pointsNeverNegative() {
        var points = GameRules.INITIAL_POINTS
        var previous = Dice.roll()
        while (!GameRules.isGameOver(points)) {
            val bet = if (GameRules.canAfford(points, Bet.NUMBER)) Bet.NUMBER else Bet.LOWER
            val safeBet = if (GameRules.isPossible(bet, previous)) bet else Bet.HIGHER
            val result = GameRules.play(points, safeBet, previous, number = 1)
            assertTrue(result.points >= 0)
            points = result.points
            previous = result.roll
            if (points > 1000) break
        }
    }

    @Test
    fun impossibleArrows() {
        assertFalse(GameRules.isPossible(Bet.HIGHER, 6))
        assertFalse(GameRules.isPossible(Bet.LOWER, 1))
        assertTrue(GameRules.isPossible(Bet.NUMBER, 6))
    }
}
```

## Recursos

::: code-group
```xml [values/strings.xml]
<resources>
    <string name="app_name" translatable="false">Dice Game TuNombre</string>
    <string name="author" translatable="false">TuNombre TuApellido</string>
    <string name="arrow_up" translatable="false">▲</string>
    <string name="arrow_down" translatable="false">▼</string>
    <string name="game_title">Higher, lower or spot on?</string>
    <string name="rules">Guess the next roll. An arrow costs %1$d point and pays %2$d if the roll is higher (▲) or lower (▼) than the last one. A number costs %3$d points and pays %4$d if it comes up.</string>
    <string name="points">Points: %1$d</string>
    <string name="last_roll">Last roll</string>
    <string name="first_roll">Place your first bet!</string>
    <string name="won">You won %1$d points!</string>
    <string name="lost">You lost %1$d point(s).</string>
    <string name="bet_higher">Bet that the next roll is higher</string>
    <string name="bet_lower">Bet that the next roll is lower</string>
    <string name="game_over">GAME OVER</string>
    <string name="restart">Play again</string>
</resources>
```
```xml [values-es/strings.xml]
<resources>
    <string name="game_title">¿Más, menos o clavado?</string>
    <string name="rules">Adivina la siguiente tirada. Una flecha cuesta %1$d punto y paga %2$d si sale mayor (▲) o menor (▼) que la anterior. Un número cuesta %3$d puntos y paga %4$d si sale.</string>
    <string name="points">Puntos: %1$d</string>
    <string name="last_roll">Última tirada</string>
    <string name="first_roll">¡Haz tu primera apuesta!</string>
    <string name="won">¡Has ganado %1$d puntos!</string>
    <string name="lost">Has perdido %1$d punto(s).</string>
    <string name="bet_higher">Apostar a que la siguiente tirada es mayor</string>
    <string name="bet_lower">Apostar a que la siguiente tirada es menor</string>
    <string name="game_over">FIN DE LA PARTIDA</string>
    <string name="restart">Volver a empezar</string>
</resources>
```
```xml [values-ca/strings.xml]
<resources>
    <string name="game_title">Més, menys o clavat?</string>
    <string name="rules">Endevina la següent tirada. Una fletxa costa %1$d punt i paga %2$d si ix major (▲) o menor (▼) que l\'anterior. Un número costa %3$d punts i paga %4$d si ix.</string>
    <string name="points">Punts: %1$d</string>
    <string name="last_roll">Última tirada</string>
    <string name="first_roll">Fes la teua primera aposta!</string>
    <string name="won">Has guanyat %1$d punts!</string>
    <string name="lost">Has perdut %1$d punt(s).</string>
    <string name="bet_higher">Apostar que la següent tirada és major</string>
    <string name="bet_lower">Apostar que la següent tirada és menor</string>
    <string name="game_over">FI DE LA PARTIDA</string>
    <string name="restart">Tornar a començar</string>
</resources>
```
```xml [values/dimens.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="title_text">24sp</dimen>
    <dimen name="dice_text">64sp</dimen>
    <dimen name="screen_padding">16dp</dimen>
    <dimen name="card_padding">12dp</dimen>
    <dimen name="spacing">12dp</dimen>
    <dimen name="arrow_button">72dp</dimen>
    <dimen name="number_button">64dp</dimen>
</resources>
```
:::
