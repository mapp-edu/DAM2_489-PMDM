---
layout: doc
title: "Solución Actividad 1 — Estadísticas"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 1 — Estadísticas

Solución de referencia de la [Actividad 1](./#actividad-1) del boletín. Hay muchas soluciones correctas: úsala para **comparar** con la tuya después de intentarlo, no para copiarla.

::: tip-box Descarga
Proyecto completo: <a :href="withBase('/descargas/up03/soluciones-up03-actividad-1.zip')" download>soluciones-up03-actividad-1.zip</a>. Descomprímelo y, en Android Studio, elige **File → Open** y selecciona la carpeta `StatisticsTuNombre`. Al sincronizar, Android Studio descarga Gradle; si tu versión es más reciente, acepta la actualización que propone el *AGP Upgrade Assistant*.
:::

Paquete: `com.tunombretuapellido.statistics` · SDK mínimo: API 28.

## Claves de la solución

- `model/Statistics.kt`: `increment`, `decrement` (nunca baja de 0), `percentage` (devuelve 0 si el total es 0, para no dividir entre cero) y `randomCount` (0 a 10).
- `ui/CounterCard.kt`: un único componente para los cuatro contadores, que recibe el nombre, el valor, el porcentaje, el color y una lambda `onCountChange`.
- `MainActivity.kt`: cuatro variables `rememberSaveable` y una lista de `CounterUi` para recorrerlas con `forEach` (el total, **Reiniciar todos** y **Randomize** recorren la misma lista).
- El porcentaje usa `%2$.1f %%`: en español sale con coma decimal porque el formato respeta el idioma del dispositivo.
- Colores propios en `colors.xml`, distintos del ejemplo del enunciado.

## Comprueba tu versión

| Prueba | Resultado esperado |
|:---|:---|
| Restar con el contador a 0 | No baja de 0 (botón desactivado o sin efecto) |
| Porcentajes con todo a 0 | 0 % (sin `NaN` ni excepción) |
| Girar la pantalla | Los contadores se conservan |

## Código

### `MainActivity.kt`

```kotlin [MainActivity.kt]
/**
 * Pantalla principal (UP03 · Actividad 1 — Estadísticas).
 *
 * Lleva la cuenta de las personas, patinetes, bicicletas y coches que pasan por una calle.
 * Cada contador es una variable de estado con `rememberSaveable`, de modo que los datos
 * se conservan al girar el dispositivo.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.statistics

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
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
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.tunombretuapellido.statistics.model.Statistics
import com.tunombretuapellido.statistics.ui.CounterCard
import com.tunombretuapellido.statistics.ui.theme.StatisticsTheme

/** Única pantalla de la aplicación. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            StatisticsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    StatisticsScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Datos que necesita la interfaz para pintar un contador.
 *
 * @property label id del texto con el nombre del elemento contado.
 * @property color id del color del contador.
 * @property count valor actual.
 * @property onCountChange cambia el valor del contador.
 */
data class CounterUi(
    @StringRes val label: Int,
    @ColorRes val color: Int,
    val count: Int,
    val onCountChange: (Int) -> Unit,
)

/**
 * Pantalla completa: título, los cuatro contadores, el total y los botones generales.
 *
 * @param modifier modificador que recibe el *padding* calculado por el `Scaffold`.
 */
@Composable
fun StatisticsScreen(modifier: Modifier = Modifier) {
    var people by rememberSaveable { mutableIntStateOf(0) }
    var scooters by rememberSaveable { mutableIntStateOf(0) }
    var bikes by rememberSaveable { mutableIntStateOf(0) }
    var cars by rememberSaveable { mutableIntStateOf(0) }

    val counters = listOf(
        CounterUi(R.string.people, R.color.people, people) { people = it },
        CounterUi(R.string.scooters, R.color.scooters, scooters) { scooters = it },
        CounterUi(R.string.bikes, R.color.bikes, bikes) { bikes = it },
        CounterUi(R.string.cars, R.color.cars, cars) { cars = it },
    )
    val total = counters.sumOf { it.count }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier)
            // Con scroll la pantalla también cabe en horizontal o con letra grande
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(id = R.dimen.screen_padding)),
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.spacing)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.title, stringResource(id = R.string.author_name)),
            fontSize = dimensionResource(id = R.dimen.title_text).value.sp,
            fontWeight = FontWeight.Bold
        )

        counters.forEach { counter ->
            CounterCard(
                label = stringResource(id = counter.label),
                count = counter.count,
                percentage = Statistics.percentage(counter.count, total),
                color = colorResource(id = counter.color),
                onCountChange = counter.onCountChange
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.total, total),
                fontSize = dimensionResource(id = R.dimen.title_text).value.sp,
                fontWeight = FontWeight.Bold
            )
            OutlinedButton(
                onClick = { counters.forEach { it.onCountChange(0) } },
                enabled = total > 0
            ) {
                Text(text = stringResource(id = R.string.reset_all))
            }
        }

        Button(
            onClick = { counters.forEach { it.onCountChange(Statistics.randomCount()) } },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.randomize),
                contentColor = Color.White
            )
        ) {
            Text(
                text = stringResource(id = R.string.randomize),
                fontSize = dimensionResource(id = R.dimen.label_text).value.sp
            )
        }
    }
}
```

### `model/Statistics.kt`

```kotlin [model/Statistics.kt]
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
```

### `ui/CounterCard.kt`

```kotlin [ui/CounterCard.kt]
/**
 * Componente reutilizable de un contador (UP03 · Actividad 1 — Estadísticas).
 *
 * Se usa una vez por cada elemento contado (personas, patinetes, bicicletas y coches):
 * así la interfaz de los cuatro es idéntica y solo cambian los datos que recibe.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.statistics.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.tunombretuapellido.statistics.R
import com.tunombretuapellido.statistics.model.Statistics

/**
 * Tarjeta con el nombre, el valor y el porcentaje de un contador, y sus botones
 * para restar uno, sumar uno y reiniciar.
 *
 * @param label nombre del elemento contado.
 * @param count valor actual del contador.
 * @param percentage porcentaje sobre el total.
 * @param color color del contador (fondo de la tarjeta y de los botones).
 * @param onCountChange se llama con el nuevo valor cuando se pulsa un botón.
 * @param modifier modificador externo.
 */
@Composable
fun CounterCard(
    label: String,
    count: Int,
    percentage: Double,
    color: Color,
    onCountChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color.copy(alpha = 0.15f))
    ) {
        Column(modifier = Modifier.padding(dimensionResource(id = R.dimen.card_padding))) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = label,
                    fontSize = dimensionResource(id = R.dimen.label_text).value.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = stringResource(R.string.count_and_percentage, count, percentage),
                    fontSize = dimensionResource(id = R.dimen.label_text).value.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val buttonColors = ButtonDefaults.buttonColors(
                    containerColor = color,
                    contentColor = Color.White
                )
                Button(
                    onClick = { onCountChange(Statistics.decrement(count)) },
                    enabled = count > 0,
                    colors = buttonColors
                ) {
                    Text(text = stringResource(id = R.string.minus_one))
                }
                Button(
                    onClick = { onCountChange(Statistics.increment(count)) },
                    colors = buttonColors
                ) {
                    Text(text = stringResource(id = R.string.plus_one))
                }
                OutlinedButton(
                    onClick = { onCountChange(0) },
                    enabled = count > 0
                ) {
                    Text(text = stringResource(id = R.string.reset))
                }
            }
        }
    }
}
```

## Tests unitarios

La lógica del paquete `model` no depende de Android, así que se prueba con JUnit sin emulador: clic derecho sobre la carpeta `test` → **Run 'Tests in ...'**.

```kotlin [StatisticsTest.kt]
package com.tunombretuapellido.statistics.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/** Tests de la lógica de los contadores. */
class StatisticsTest {

    @Test
    fun decrementNeverGoesBelowZero() {
        assertEquals(0, Statistics.decrement(0))
        assertEquals(4, Statistics.decrement(5))
    }

    @Test
    fun incrementAddsOne() {
        assertEquals(1, Statistics.increment(0))
    }

    @Test
    fun percentageWithZeroTotalIsZero() {
        assertEquals(0.0, Statistics.percentage(0, 0), 0.0001)
    }

    @Test
    fun percentagesAddUpToOneHundred() {
        val counts = listOf(3, 1, 4, 2)
        val total = counts.sum()
        assertEquals(100.0, counts.sumOf { Statistics.percentage(it, total) }, 0.0001)
        assertEquals(30.0, Statistics.percentage(3, total), 0.0001)
    }

    @Test
    fun randomCountIsInRange() {
        repeat(1000) {
            assertTrue(Statistics.randomCount() in 0..Statistics.MAX_RANDOM)
        }
    }
}
```

## Recursos

::: code-group
```xml [values/strings.xml]
<resources>
    <string name="app_name" translatable="false">Statistics TuNombre</string>
    <string name="author_name" translatable="false">TuNombre</string>
    <string name="title">Statistics %1$s</string>
    <string name="people">People</string>
    <string name="scooters">Scooters</string>
    <string name="bikes">Bicycles</string>
    <string name="cars">Cars</string>
    <string name="count_and_percentage">%1$d · %2$.1f %%</string>
    <string name="plus_one" translatable="false">+1</string>
    <string name="minus_one" translatable="false">−1</string>
    <string name="reset">Reset</string>
    <string name="total">Total: %1$d</string>
    <string name="reset_all">Reset all</string>
    <string name="randomize">Randomize</string>
</resources>
```
```xml [values-es/strings.xml]
<resources>
    <string name="title">Estadísticas %1$s</string>
    <string name="people">Personas</string>
    <string name="scooters">Patinetes</string>
    <string name="bikes">Bicicletas</string>
    <string name="cars">Coches</string>
    <string name="count_and_percentage">%1$d · %2$.1f %%</string>
    <string name="reset">Reiniciar</string>
    <string name="total">Total: %1$d</string>
    <string name="reset_all">Reiniciar todos</string>
    <string name="randomize">Aleatorio</string>
</resources>
```
```xml [values/dimens.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="title_text">24sp</dimen>
    <dimen name="label_text">18sp</dimen>
    <dimen name="screen_padding">16dp</dimen>
    <dimen name="card_padding">12dp</dimen>
    <dimen name="spacing">12dp</dimen>
</resources>
```
```xml [values/colors.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="people">#FF00695C</color>
    <color name="scooters">#FFEF6C00</color>
    <color name="bikes">#FF1565C0</color>
    <color name="cars">#FFC62828</color>
    <color name="randomize">#FF4527A0</color>
</resources>
```
:::
