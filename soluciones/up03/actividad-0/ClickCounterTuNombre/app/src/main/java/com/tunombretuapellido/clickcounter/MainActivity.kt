/**
 * Actividad 0 — Contador de clics (UP03).
 *
 * Muestra cuántas veces se ha pulsado un botón. El contador se guarda con
 * `rememberSaveable`, así que sobrevive a la recreación de la Activity
 * (giro de pantalla, modo oscuro, cambio de idioma…). Todos los textos están
 * en `strings.xml` (inglés por defecto y español) y las medidas en `dimens.xml`.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */
package com.tunombretuapellido.clickcounter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.tunombretuapellido.clickcounter.ui.theme.ClickCounterTheme

/** Única pantalla de la aplicación. */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClickCounterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Content(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

/**
 * Contenido de la pantalla: texto con el número de clics, botón para sumar
 * y botón para reiniciar.
 *
 * @param modifier modificador que recibe el *padding* calculado por el `Scaffold`.
 */
@Composable
fun Content(modifier: Modifier = Modifier) {
    var times by rememberSaveable { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .then(modifier),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.counter_text, times),
            fontSize = dimensionResource(id = R.dimen.counter_text).value.sp
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing)))
        Button(onClick = {
            times++
        }) {
            Text(
                text = stringResource(id = R.string.clickme),
                fontSize = dimensionResource(id = R.dimen.button_text).value.sp,
                modifier = Modifier.padding(dimensionResource(id = R.dimen.button_padding))
            )
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacing)))
        OutlinedButton(
            onClick = {
                times = 0
            },
            enabled = times > 0
        ) {
            Text(text = stringResource(id = R.string.reset))
        }
    }
}
