# Solucionario UP03 — Introducción a Android

Soluciones de referencia del boletín de la UP03 (`src/up03/ejercicios/index.md`). **Se publican en la web del curso**: el script `soluciones/build_solutions_up03.py` genera a partir de estos proyectos las páginas `src/up03/ejercicios/solucion-0.md` a `solucion-3.md` y los `.zip` de descarga en `src/public/descargas/up03/`.

```bash
# desde la raíz del repositorio, cada vez que cambies algo en soluciones/up03
python3 soluciones/build_solutions_up03.py .
```

Las páginas toman las claves de cada actividad de las secciones `## Actividad N — …` de este README y las pruebas de la tabla «Comprobaciones para corregir». Los `.zip` contienen la carpeta del proyecto sin `.gradle`, `.idea`, `build` ni `local.properties`.

## Estructura

```txt
soluciones/up03/
├── actividad-0/ClickCounterTuNombre/   Contador de clics con recursos e idiomas (en, es)
├── actividad-1/StatisticsTuNombre/     Contadores de calle con porcentajes (en, es)
├── actividad-2/DiceGameTuNombre/       Juego de dados con clase Dice en model (en, es, ca)
└── actividad-3/Basket3x3TuNombre/      Marcador 3x3 en horizontal con deshacer (en, es)
```

Cada carpeta es un **proyecto completo de Android Studio** (plantilla *Empty Activity*, Compose, paquete `com.tunombretuapellido.<app>`, minSdk 28). Para abrirlo: **File → Open** y selecciona la carpeta del proyecto. No incluye `gradlew` ni `gradle-wrapper.jar`: Android Studio descarga Gradle 8.9 al sincronizar. Si tu Android Studio es más reciente, el *AGP Upgrade Assistant* propondrá actualizar las versiones de `gradle/libs.versions.toml` (AGP 8.7.3, Kotlin 2.0.21, Compose BOM 2024.12.01); puedes aceptarlo.

### Qué está comprobado y qué no

- **Lógica (`model`) y tests:** compilados con Kotlin y con los 19 tests de `app/src/test` superados (contadores, porcentajes, reglas del dado, faltas, bonus y victoria a 21).
- **Recursos:** XML válidos; todas las referencias `R.string`, `R.dimen` y `R.color` existen y las traducciones tienen los mismos parámetros que el idioma por defecto.
- **Interfaces Compose:** comprobadas solo a nivel de tipos (el código de `MainActivity` y `ui/`, compilado contra *stubs* de las APIs de Compose y Material 3 y un `R` generado desde `res`), porque el entorno donde se prepararon no tiene el SDK de Android. Eso garantiza nombres, parámetros y tipos, pero no el aspecto ni el comportamiento en pantalla: **antes de enseñarlas, ábrelas una vez en Android Studio y ejecútalas en el emulador.**

## Decisiones comunes

| Decisión | Por qué | Criterio |
|:---|:---|:---|
| Lógica en un paquete `model` sin dependencias de Android | Se puede probar con tests unitarios sin emulador y la interfaz solo pinta y llama a funciones | RA2.a, RA2.g |
| Estado con `rememberSaveable` | El estado sobrevive al giro, al modo oscuro y al cambio de idioma (apartado 2.5) | RA2.b |
| Componentes `@Composable` propios (`CounterCard`, `TeamPanel`, `NumberButton`…) | Se escriben una vez y se reutilizan para cada contador, equipo o número | RA2.a |
| Textos en `strings.xml`, tamaños en `dimens.xml`, colores en `colors.xml` | Nada *hardcoded*; traducir o cambiar el aspecto no exige tocar el código. Los símbolos (`+1`, `▲`…) y el nombre de la app se marcan `translatable="false"` | RA2.i |
| Botones desactivados cuando la acción no tiene sentido | Evita estados imposibles (contadores negativos, apostar sin puntos, más de 7 faltas) en lugar de corregirlos después | RA2.g |
| `verticalScroll` en las pantallas largas | El contenido sigue siendo accesible en pantallas pequeñas o con letra grande | RA2.b |
| Icono adaptativo vectorial (`mipmap-anydpi`) | Con minSdk 28 no hacen falta PNG por densidad | RA2.i |

## Prácticas guiadas

- **Práctica A:** es la plantilla *Empty Activity* sin cambios; debe mostrar *Hello Android!*. Comprueba en `build.gradle.kts (Module :app)` el `applicationId` y `minSdk = 28`.
- **Práctica B:** en **Device Manager**, borrar el dispositivo por defecto y crear un **Pixel 9a** con la imagen del sistema más reciente. Si el emulador no arranca, lo habitual es que falte la virtualización por hardware (VT-x/AMD-V, o Hyper-V/WHPX en Windows).

## Actividad 0 — Contador de clics

- Igual que en los apuntes, más un botón **Reiniciar** (desactivado con el contador a 0).
- `counter_text` es una cadena con parámetro (`%1$d`), tal como se ve en el apartado 3.4.
- Idiomas: inglés (por defecto) y español. Medidas en `dimens.xml`.

## Actividad 1 — Estadísticas

- `model/Statistics.kt`: `increment`, `decrement` (nunca baja de 0), `percentage` (devuelve 0 si el total es 0, para no dividir entre cero) y `randomCount` (0 a 10).
- `ui/CounterCard.kt`: un único componente para los cuatro contadores, que recibe el nombre, el valor, el porcentaje, el color y una lambda `onCountChange`.
- `MainActivity.kt`: cuatro variables `rememberSaveable` y una lista de `CounterUi` para recorrerlas con `forEach` (el total, **Reiniciar todos** y **Randomize** recorren la misma lista).
- El porcentaje usa `%2$.1f %%`: en español sale con coma decimal porque el formato respeta el idioma del dispositivo.
- Colores propios en `colors.xml`, distintos del ejemplo del enunciado.

## Actividad 2 — Dice Game

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

## Actividad 3 — Basket 3x3

- `AndroidManifest.xml`: `android:screenOrientation="landscape"`. Icono vectorial de una pelota de baloncesto.
- `model/Match.kt`: `TeamState(score, fouls)` y `MatchState(home, away)` **inmutables**. Cada acción (`addPoints`, `addFoul`, `removeFoul`) devuelve un estado nuevo.
  - Faltas entre 0 y 7; con 7, `inBonus` muestra **BONUS** en rojo.
  - Con 21 o más puntos hay `winner`: el panel ganador se resalta con **¡GANADOR!** y los botones de juego se desactivan.
  - Solo existen canastas de 1 y 2 puntos (`require`).
- `ui/TeamPanel.kt`: un panel reutilizable por equipo (marcador, faltas y una cuadrícula de 2×2 jugadores con +1 y +2).
- **Opcional — deshacer:** el estado es un **historial** (`List<MatchState>`). Deshacer quita el último elemento y resetear vuelve a una lista con un estado inicial. Como un `Bundle` no puede guardar `MatchState`, `ui/HistorySaver.kt` lo convierte a una lista de enteros para `rememberSaveable`.
- Decisión: **Deshacer** sigue activo después de la victoria para poder corregir una última canasta mal anotada. Si se aplica el enunciado al pie de la letra («todos salvo resetear»), basta con añadir `&& !match.isFinished` a su `enabled`.

## Puntos clave para el vídeo (RA1.f)

Lo que debería explicar una entrega de nivel 3-4:

1. `MainActivity` → `onCreate` → `setContent` → tema → `Scaffold` y su `innerPadding`.
2. Por qué el estado es `rememberSaveable` y qué pasa al girar la pantalla (la Activity se recrea).
3. Qué es la **recomposición**: al cambiar una variable de estado se vuelven a dibujar los componentes que la leen.
4. Cómo se dividió la interfaz en componentes y qué recibe cada uno (datos y lambdas).
5. Recursos: `strings.xml` por idioma, `dimens.xml`, `colors.xml` y cómo se leen (`stringResource`, `dimensionResource().value.sp`, `colorResource`).
6. Actividad 2: la clase `Dice` y su `companion object`. Actividad 3: el bloqueo de orientación en el manifiesto y el icono.

## Comprobaciones para corregir (RA2.g)

| Actividad | Prueba | Resultado esperado |
|:---|:---|:---|
| 1 | Restar con el contador a 0 | No baja de 0 (botón desactivado o sin efecto) |
| 1 | Porcentajes con todo a 0 | 0 % (sin `NaN` ni excepción) |
| 1 | Girar la pantalla | Los contadores se conservan |
| 2 | Quedarse con 1-2 puntos | No se puede apostar a un número |
| 2 | Llegar a 0 puntos | No se puede seguir; aparece reiniciar |
| 2 | Tirada igual a la anterior y apuesta con flecha | Se pierde |
| 2 | Cambiar el idioma del emulador a valenciano/catalán | Textos en valenciano |
| 3 | Octava falta | No pasa de 7 y se indica el bonus |
| 3 | Anotar hasta 21 o más | Ganador indicado y botones desactivados salvo resetear |
| 3 | Girar el dispositivo | Sigue en horizontal |
