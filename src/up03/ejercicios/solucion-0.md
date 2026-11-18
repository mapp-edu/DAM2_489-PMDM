---
layout: doc
title: "Solución Actividad 0 — Contador de clics"
sidebar: true
outline: [2, 3]
aside: true
---

<script setup>
import { withBase } from 'vitepress'
</script>

# ✅ Solución Actividad 0 — Contador de clics

Solución de referencia de la [Actividad 0](./#actividad-0) del boletín. Hay muchas soluciones correctas: úsala para **comparar** con la tuya después de intentarlo, no para copiarla.

::: tip-box Descarga
Proyecto completo: <a :href="withBase('/descargas/up03/soluciones-up03-actividad-0.zip')" download>soluciones-up03-actividad-0.zip</a>. Descomprímelo y, en Android Studio, elige **File → Open** y selecciona la carpeta `ClickCounterTuNombre`. Al sincronizar, Android Studio descarga Gradle; si tu versión es más reciente, acepta la actualización que propone el *AGP Upgrade Assistant*.
:::

Paquete: `com.tunombretuapellido.clickcounter` · SDK mínimo: API 28.

## Decisiones comunes

Se aplican a las cuatro soluciones de la unidad.

| Decisión | Por qué | Criterio |
|:---|:---|:---|
| Lógica en un paquete `model` sin dependencias de Android | Se puede probar con tests unitarios sin emulador y la interfaz solo pinta y llama a funciones | RA2.a, RA2.g |
| Estado con `rememberSaveable` | El estado sobrevive al giro, al modo oscuro y al cambio de idioma (apartado 2.5) | RA2.b |
| Componentes `@Composable` propios (`CounterCard`, `TeamPanel`, `NumberButton`…) | Se escriben una vez y se reutilizan para cada contador, equipo o número | RA2.a |
| Textos en `strings.xml`, tamaños en `dimens.xml`, colores en `colors.xml` | Nada *hardcoded*; traducir o cambiar el aspecto no exige tocar el código. Los símbolos (`+1`, `▲`…) y el nombre de la app se marcan `translatable="false"` | RA2.i |
| Botones desactivados cuando la acción no tiene sentido | Evita estados imposibles (contadores negativos, apostar sin puntos, más de 7 faltas) en lugar de corregirlos después | RA2.g |
| `verticalScroll` en las pantallas largas | El contenido sigue siendo accesible en pantallas pequeñas o con letra grande | RA2.b |
| Icono adaptativo vectorial (`mipmap-anydpi`) | Con minSdk 28 no hacen falta PNG por densidad | RA2.i |

## Claves de la solución

- Igual que en los apuntes, más un botón **Reiniciar** (desactivado con el contador a 0).
- `counter_text` es una cadena con parámetro (`%1$d`), tal como se ve en el apartado 3.4.
- Idiomas: inglés (por defecto) y español. Medidas en `dimens.xml`.

## Código

### `MainActivity.kt`

```kotlin [MainActivity.kt]
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
```

## Recursos

::: code-group
```xml [values/strings.xml]
<resources>
    <string name="app_name">Clicks counter</string>
    <string name="counter_text">You have clicked %1$d times</string>
    <string name="clickme">CLICK ME!</string>
    <string name="reset">Reset</string>
</resources>
```
```xml [values-es/strings.xml]
<resources>
    <string name="app_name">Contador de clics</string>
    <string name="counter_text">Has hecho clic %1$d veces</string>
    <string name="clickme">¡PÚLSAME!</string>
    <string name="reset">Reiniciar</string>
</resources>
```
```xml [values/dimens.xml]
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <dimen name="counter_text">25sp</dimen>
    <dimen name="button_text">30sp</dimen>
    <dimen name="button_padding">16dp</dimen>
    <dimen name="spacing">20dp</dimen>
</resources>
```
:::
