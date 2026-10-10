---
layout: doc
title: Actividades UP04
sidebar: true
outline: [2, 3]
aside: false
---

<script setup>
import { withBase } from 'vitepress'
</script>

# 🛠️ Actividades UP04 — Componentes Jetpack Compose

::: danger-box Trabajo personal
El uso de cualquier IA, generador de código o la copia de cualquier fuente supondrá una calificación de **cero** en la actividad.
:::

En todas las actividades, organiza el código como se explica en el [apartado 1.2](../contenidos/01-modifier-componentes-basicos#_1-2-organizar-el-codigo): `MainActivity` sin componentes, pantallas en `ui/screens` y componentes propios pequeños y con nombres semánticos. Centraliza textos, dimensiones y colores en los recursos.

## Actividad 1 — Perfil personal {#actividad-1}

Crea un proyecto llamado **ProfileTuNombre**. Con los componentes del [apartado 1](../contenidos/01-modifier-componentes-basicos), diseña una pantalla a modo de **perfil de usuario de una red social** en la que aparezca:

- Tu **nombre**.
- Una **foto tuya** (tipo carné).
- Un botón para **seguir o dejar de seguir**.
- Una lista de **5 hobbies o gustos**, cada uno con un **icono** delante.
- **Dos imágenes pequeñas** de lo que quieras.
- En cada imagen, un **botón con icono** para dar o quitar el **me gusta**.
- Un **`BadgedBox`** con el icono de un **sobre** que indique la cantidad de **mensajes pendientes**.
- Abajo del todo, un botón con un **+** que **aumente** la cantidad de mensajes pendientes.

## Actividad 2 — Conversor {#actividad-2}

Crea un proyecto llamado **ConverterTuNombre**. La aplicación tendrá:

- Tu **nombre** y una **foto tuya** (tipo carné).
- Un **campo de texto** en el que el usuario escribirá un número para convertir a binario o a decimal.
- Un **interruptor** (`Switch`) para decidir si se convierte **a decimal o a binario**.
- Un **texto** con el resultado de la conversión.
- Un botón para **realizar la conversión**.
- Un botón para **borrar** tanto el campo de texto como el resultado.

::: tip-box Piensa en el usuario
¿Qué pasa si escribe letras, o un `2` cuando quiere convertir de binario a decimal? Usa `keyboardOptions`, `isError` o `supportingText` para guiarle.
:::

## Actividad 3 — Registro {#actividad-3}

Crea un proyecto llamado **SignUpTuNombre** con un **formulario de registro** que pida:

- Nombre, apellidos, email, número de teléfono y fecha de nacimiento.
- Una serie de **6 elementos seleccionables** mediante **`FilterChip`** (animes, deportes, películas, actores o actrices… lo que quieras).
- Dos botones: uno para **borrar todos los campos** y otro para **enviar** el formulario.
- En la parte inferior, tu **nombre** y una **foto tuya** (tipo carné).

Los datos se deben **validar** para que sigan el formato que corresponde a la información que almacenan. Al pulsar *Enviar*, si todo es correcto, aparecerá un **diálogo** con un **resumen** de los datos introducidos y se **borrará el formulario**.

## Actividad 4 — Dragon Ball {#actividad-4}

Crea un proyecto llamado **DragonBallTuNombre**. La aplicación estará **bloqueada en horizontal** (*landscape*) y contendrá un **`Scaffold`** con:

- **`topBar`**: un icono de una bola de dragón, el logo de Dragon Ball y el nombre *Akira Toriyama*.
- **`floatingActionButton`**: al pulsarlo, muestra un cuadro con tu **foto actual** (tipo carné) y tu **nombre**.

En el cuerpo del `Scaffold` habrá dos secciones:

- **A la izquierda**: una columna con **todos los personajes**, con **`stickyHeader`**.
- **A la derecha**: la **información de un personaje**.

Al pulsar un personaje de la columna se mostrará su información y aparecerá un **icono junto al nombre** pulsado.

- Si el año de nacimiento es **cero**, el dato es desconocido y así se debe indicar.
- Si el personaje **no tiene otros nombres**, también se debe indicar.

### Archivos de partida

| Archivo | Uso |
|:---|:---|
| <a :href="withBase('/descargas/up04/DragonBallCharacter.kt')" download>DragonBallCharacter.kt</a> | Modelo de datos. **Solo** se puede modificar el `package` del principio del archivo. |
| [DragonBall.png](/descargas/up04/DragonBall.png) | Logo para la `topBar`. |
| [fourstarball.webp](/descargas/up04/fourstarball.webp) | Icono de la bola de dragón. |

`DragonBallCharacter.kt` contiene una `data class` con todas las propiedades de un personaje y estos **métodos estáticos públicos**, que debes usar para obtener la información:

| Método | Devuelve |
|:---|:---|
| `DragonBallCharacter.sorted()` | La lista de personajes ordenada alfabéticamente. |
| `DragonBallCharacter.getCharacterById(id)` | El personaje con ese `id`. |
| `DragonBallCharacter.getCharacterFirstID()` | El `id` del primer personaje (útil como selección inicial). |

Centraliza todos los valores posibles en los recursos. La información de los personajes **no**, porque se simula que llega de una fuente externa.

## Entrega

Se entregan las **cuatro actividades**. Para cada una:

1. Graba un **vídeo** (máximo 5 minutos y 50 MB) en el que expliques las funcionalidades más importantes del código y muestres la ejecución en el emulador.
2. Antes de comprimir el proyecto: detén la aplicación, ejecuta **Build → Clean Project** y cierra Android Studio.
3. Comprime **la carpeta** del proyecto (no su contenido) en un `.zip`.
4. Adjunta en el aula virtual el vídeo y el archivo comprimido.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA2.a** Genera la estructura de clases necesaria<br>*Paquetes, pantallas y componentes propios* | Todo en `MainActivity` o en un único componente | Pantallas en `ui/screens` y componentes propios | Componentes pequeños, semánticos y reutilizados; se justifica la división en el vídeo | Estructura coherente en las cuatro aplicaciones; uso correcto de `DragonBallCharacter` sin modificarlo |
| **RA2.b** Utiliza las clases de interfaz y controles<br>*Componentes pedidos en cada actividad* | Faltan componentes obligatorios (`BadgedBox`, `Switch`, `FilterChip`, `stickyHeader`…) | Están todos los componentes pedidos y funcionan | Se eligen variantes y parámetros adecuados (`keyboardOptions`, `LazyColumn`, `Scaffold`…) y se explica por qué | Interfaz cuidada, accesible (`contentDescription`, `dp`/`sp`) y coherente con Material Design |
| **RA2.a/b** Gestiona el estado<br>*Estados y State Hoisting* | Los datos se pierden o la interfaz no se actualiza | Estados con `rememberSaveable`; la interfaz se recompone correctamente | Componentes *stateless* con State Hoisting donde corresponde | El estado se conserva al girar y no hay estados innecesarios |
| **RA2.g** Prueba la interacción en el emulador<br>*Validaciones y casos límite* | Fallan las funciones principales (conversión, validación, selección de personaje…) | Todas las funciones son correctas en el emulador | Se controlan entradas erróneas, campos vacíos, año 0 y personajes sin otros nombres | Mensajes claros al usuario y comportamiento fluido |
| **RA2.i** Documenta el desarrollo<br>*Recursos y vídeo* | Textos *hardcoded* o vídeo ausente | Recursos centralizados (`strings.xml`, `dimens.xml`…); vídeo dentro de los límites | El vídeo justifica las decisiones técnicas | Vídeo claro y bien estructurado; código con KDoc |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
