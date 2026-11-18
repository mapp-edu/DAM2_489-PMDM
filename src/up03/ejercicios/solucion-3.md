---
layout: doc
title: "Solución Actividad 3 — Basket 3x3"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 3 — Basket 3x3

Solución de referencia de la [Actividad 3](./#actividad-3) del boletín. Hay muchas soluciones correctas: úsala para **comparar** con la tuya después de intentarlo, no para copiarla.

::: tip-box Descarga
Proyecto completo: <a :href="withBase('/descargas/up03/soluciones-up03-actividad-3.zip')" download>soluciones-up03-actividad-3.zip</a>. Descomprímelo y, en Android Studio, elige **File → Open** y selecciona la carpeta `Basket3x3TuNombre`. Al sincronizar, Android Studio descarga Gradle; si tu versión es más reciente, acepta la actualización que propone el *AGP Upgrade Assistant*.
:::

Paquete: `com.tunombretuapellido.basket3x3` · SDK mínimo: API 28.

## Claves de la solución

- `AndroidManifest.xml`: `android:screenOrientation="landscape"`. Icono vectorial de una pelota de baloncesto.
- `model/Match.kt`: `TeamState(score, fouls)` y `MatchState(home, away)` **inmutables**. Cada acción (`addPoints`, `addFoul`, `removeFoul`) devuelve un estado nuevo.
  - Faltas entre 0 y 7; con 7, `inBonus` muestra **BONUS** en rojo.
  - Con 21 o más puntos hay `winner`: el panel ganador se resalta con **¡GANADOR!** y los botones de juego se desactivan.
  - Solo existen canastas de 1 y 2 puntos (`require`).
- `ui/TeamPanel.kt`: un panel reutilizable por equipo (marcador, faltas y una cuadrícula de 2×2 jugadores con +1 y +2).
- **Opcional — deshacer:** el estado es un **historial** (`List<MatchState>`). Deshacer quita el último elemento y resetear vuelve a una lista con un estado inicial. Como un `Bundle` no puede guardar `MatchState`, `ui/HistorySaver.kt` lo convierte a una lista de enteros para `rememberSaveable`.
- Decisión: **Deshacer** sigue activo después de la victoria para poder corregir una última canasta mal anotada. Si se aplica el enunciado al pie de la letra («todos salvo resetear»), basta con añadir `&& !match.isFinished` a su `enabled`.

## Comprueba tu versión

| Prueba | Resultado esperado |
|:---|:---|
| Octava falta | No pasa de 7 y se indica el bonus |
| Anotar hasta 21 o más | Ganador indicado y botones desactivados salvo resetear |
| Girar el dispositivo | Sigue en horizontal |

## Código

### `MainActivity.kt`

```kotlin [MainActivity.kt]
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
```

### `model/Match.kt`

```kotlin [model/Match.kt]
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
```

### `ui/HistorySaver.kt`

```kotlin [ui/HistorySaver.kt]
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
```

### `ui/TeamPanel.kt`

```kotlin [ui/TeamPanel.kt]
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
```

## Tests unitarios

La lógica del paquete `model` no depende de Android, así que se prueba con JUnit sin emulador: clic derecho sobre la carpeta `test` → **Run 'Tests in ...'**.

```kotlin [MatchStateTest.kt]
package com.tunombretuapellido.basket3x3.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

/** Tests de las reglas del marcador. */
class MatchStateTest {

    @Test
    fun pointsAreAddedToTheRightTeam() {
        val match = MatchState().addPoints(Team.HOME, 2).addPoints(Team.AWAY, 1)
        assertEquals(2, match.home.score)
        assertEquals(1, match.away.score)
    }

    @Test
    fun foulsStopAtSevenAndBonusStarts() {
        var match = MatchState()
        repeat(6) { match = match.addFoul(Team.AWAY) }
        assertFalse(match.away.inBonus)
        repeat(3) { match = match.addFoul(Team.AWAY) }
        assertEquals(MatchRules.MAX_FOULS, match.away.fouls)
        assertTrue(match.away.inBonus)
    }

    @Test
    fun foulsNeverNegative() {
        assertEquals(0, MatchState().removeFoul(Team.HOME).home.fouls)
    }

    @Test
    fun reachingTwentyOneWinsAndFreezesTheMatch() {
        var match = MatchState()
        repeat(10) { match = match.addPoints(Team.HOME, 2) }
        assertNull(match.winner)
        match = match.addPoints(Team.HOME, 2)          // 22 ≥ 21
        assertEquals(Team.HOME, match.winner)
        assertTrue(match.isFinished)
        assertEquals(match, match.addPoints(Team.AWAY, 1))
        assertEquals(match, match.addFoul(Team.AWAY))
    }

    @Test(expected = IllegalArgumentException::class)
    fun threePointersDoNotExist() {
        MatchState().addPoints(Team.HOME, 3)
    }

    @Test
    fun intListRoundTrip() {
        val match = MatchState(TeamState(15, 7), TeamState(9, 2))
        assertEquals(match, MatchState.fromIntList(match.toIntList()))
    }
}
```

## Recursos

::: code-group
```xml [values/strings.xml]
<resources>
    <string name="app_name" translatable="false">Basket 3x3 TuNombre</string>
    <string name="home">Home</string>
    <string name="away">Away</string>
    <string name="player">Player %1$d</string>
    <string name="one_point" translatable="false">+1</string>
    <string name="two_points" translatable="false">+2</string>
    <string name="fouls">Fouls: %1$d/%2$d</string>
    <string name="add_foul" translatable="false">+</string>
    <string name="remove_foul" translatable="false">−</string>
    <string name="bonus">BONUS</string>
    <string name="winner">WINNER!</string>
    <string name="reset">Reset</string>
    <string name="undo">Undo</string>
</resources>
```
```xml [values-es/strings.xml]
<resources>
    <string name="home">Local</string>
    <string name="away">Visitante</string>
    <string name="player">Jugador %1$d</string>
    <string name="fouls">Faltas: %1$d/%2$d</string>
    <string name="bonus">BONUS</string>
    <string name="winner">¡GANADOR!</string>
    <string name="reset">Resetear</string>
    <string name="undo">Deshacer</string>
</resources>
```
```xml [values/dimens.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="team_text">22sp</dimen>
    <dimen name="score_text">48sp</dimen>
    <dimen name="screen_padding">8dp</dimen>
    <dimen name="panel_padding">12dp</dimen>
    <dimen name="small_spacing">8dp</dimen>
</resources>
```
```xml [values/colors.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="home">#FF1565C0</color>
    <color name="away">#FFC62828</color>
</resources>
```
:::

## Manifiesto e icono

::: code-group
```xml [AndroidManifest.xml]
<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.Basket3x3">
        <activity
            android:name=".MainActivity"
            android:exported="true"
    android:screenOrientation="landscape"
            android:label="@string/app_name"
            android:theme="@style/Theme.Basket3x3">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>
```
```xml [drawable/ic_launcher_foreground.xml]
<?xml version="1.0" encoding="utf-8"?>
<!-- Pelota de baloncesto -->
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path
        android:fillColor="#FFF57C00"
        android:pathData="M54,28a26,26 0,1 1,0 52a26,26 0,1 1,0 -52z" />
    <path
        android:pathData="M54,28L54,80M28,54L80,54M37,34C47,44 47,64 37,74M71,34C61,44 61,64 71,74"
        android:strokeWidth="2.5"
        android:strokeColor="#FF212121"
        android:fillColor="#00000000" />
    <path
        android:pathData="M54,28a26,26 0,1 1,0 52a26,26 0,1 1,0 -52z"
        android:strokeWidth="2.5"
        android:strokeColor="#FF212121"
        android:fillColor="#00000000" />
</vector>
```
```xml [drawable/ic_launcher_background.xml]
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="108dp"
    android:height="108dp"
    android:viewportWidth="108"
    android:viewportHeight="108">
    <path
        android:fillColor="#FF263238"
        android:pathData="M0,0h108v108h-108z" />
</vector>
```
:::
