package es.dam.explora.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import es.dam.explora.R
import es.dam.explora.data.Lugar
import es.dam.explora.data.lugaresEjemplo
import es.dam.explora.ui.theme.ExploraTheme

/**
 * Pantalla principal. Guarda el estado (lista y búsqueda) y lo pasa hacia abajo;
 * recibe los eventos de los composables hijos (flujo unidireccional).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaLugares(modifier: Modifier = Modifier) {
    // Estado. Limitación conocida: con remember, las casillas se pierden al girar
    // la pantalla. Se resolverá con un ViewModel en una unidad posterior.
    val lugares = remember { lugaresEjemplo.toMutableStateList() }
    var busqueda by rememberSaveable { mutableStateOf("") }

    // Valores calculados a partir del estado: no se guardan.
    val filtrados = lugares.filter {
        it.nombre.contains(busqueda, ignoreCase = true) ||
            it.ciudad.contains(busqueda, ignoreCase = true)
    }
    val visitados = lugares.count { it.visitado }

    Scaffold(
        modifier = modifier,
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CampoBusqueda(valor = busqueda, onValorChange = { busqueda = it })
            Text(
                text = stringResource(R.string.visitados, visitados, lugares.size),
                style = MaterialTheme.typography.labelLarge
            )
            if (filtrados.isEmpty()) {
                // Caso límite: búsqueda sin resultados.
                Text(
                    text = stringResource(R.string.sin_resultados, busqueda),
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                ListaLugares(
                    lugares = filtrados,
                    onVisitadoChange = { lugar, marcado ->
                        val i = lugares.indexOfFirst { it.id == lugar.id }
                        if (i >= 0) lugares[i] = lugar.copy(visitado = marcado)
                    }
                )
            }
        }
    }
}

@Composable
fun CampoBusqueda(
    valor: String,
    onValorChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = valor,
        onValueChange = onValorChange,
        label = { Text(stringResource(R.string.buscar)) },
        leadingIcon = {
            Icon(painter = painterResource(R.drawable.ic_buscar), contentDescription = null)
        },
        singleLine = true,
        modifier = modifier.fillMaxWidth()
    )
}

@Composable
fun ListaLugares(
    lugares: List<Lugar>,
    onVisitadoChange: (Lugar, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(lugares, key = { it.id }) { lugar ->
            TarjetaLugar(
                lugar = lugar,
                onVisitadoChange = { marcado -> onVisitadoChange(lugar, marcado) }
            )
        }
    }
}

@Preview(name = "Español", locale = "es", showBackground = true)
@Preview(name = "English", locale = "en", showBackground = true)
@Preview(name = "Valencià", locale = "ca", showBackground = true)
@Preview(name = "Fuente grande", fontScale = 1.5f, showBackground = true)
@Preview(name = "Tablet", device = "spec:width=1280dp,height=800dp,dpi=240", showBackground = true)
@Composable
private fun PantallaLugaresPreview() {
    ExploraTheme {
        PantallaLugares()
    }
}
