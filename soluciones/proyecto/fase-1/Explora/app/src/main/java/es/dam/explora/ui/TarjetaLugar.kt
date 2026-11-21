package es.dam.explora.ui

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.dam.explora.R
import es.dam.explora.data.Lugar
import es.dam.explora.data.lugaresEjemplo
import es.dam.explora.ui.theme.ExploraTheme

/**
 * Tarjeta de un lugar. No guarda estado (state hoisting): recibe el lugar
 * y avisa con onVisitadoChange cuando el usuario marca la casilla.
 */
@Composable
fun TarjetaLugar(
    lugar: Lugar,
    onVisitadoChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImagenLugar(id = lugar.imagen, descripcion = lugar.nombre)
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 12.dp)
            ) {
                Text(text = lugar.nombre, style = MaterialTheme.typography.titleMedium)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(R.drawable.ic_lugar),
                        contentDescription = null, // decorativo: el texto de al lado ya informa
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(text = lugar.ciudad, style = MaterialTheme.typography.bodyMedium)
                }
                Text(
                    text = pluralStringResource(
                        R.plurals.retos_disponibles,
                        lugar.retos.size,
                        lugar.retos.size,
                        lugar.puntos
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Checkbox(checked = lugar.visitado, onCheckedChange = onVisitadoChange)
        }
    }
}

@Composable
fun ImagenLugar(
    @DrawableRes id: Int,
    descripcion: String,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id),
        contentDescription = descripcion,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .size(72.dp)
            .clip(RoundedCornerShape(12.dp))
    )
}

@Preview(name = "Claro", showBackground = true)
@Preview(name = "Oscuro", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun TarjetaLugarPreview() {
    ExploraTheme {
        TarjetaLugar(lugar = lugaresEjemplo[0], onVisitadoChange = {})
    }
}
