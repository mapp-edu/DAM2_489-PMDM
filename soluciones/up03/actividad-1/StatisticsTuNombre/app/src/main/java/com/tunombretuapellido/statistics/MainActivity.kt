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
