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
