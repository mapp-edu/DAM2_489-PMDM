---
layout: doc
title: Fase 1 · App Compose (1.ª evaluación)
sidebar: true
outline: [2, 3]
aside: true
---

# 📦 Fase 1 — *Explora* con Jetpack Compose

**Evaluación**: 1.ª · **Unidades**: [UP01](../../up01/) – [UP05](../../up05/) · **Entrega**: semana 13 · **Defensa**: en emulador

## Objetivo

Construir la **primera versión funcional** de *Explora* con Jetpack Compose: varias pantallas conectadas por navegación, un tema de Material 3 propio, animaciones y diseño adaptativo, con **datos de ejemplo en memoria**. Además, analizarás y modificarás una **aplicación existente** para aprender de su estructura.

## Hitos

| Hito | Semana | Qué se revisa |
|:---|:---:|:---|
| **H1.1** · Propuesta | 4 | Temática elegida, público objetivo, lista de 8-10 lugares de ejemplo con sus retos y **bocetos** (papel o Figma) de las pantallas principales. Repositorio creado y compartido. |
| **H1.2** · Seguimiento | 9 | Catálogo y detalle funcionando con datos de ejemplo; estructura de paquetes; diario al día. |
| **Entrega Fase 1** | 13 | Todo lo que se indica en esta página, con la etiqueta Git `fase-1`. |

## Requisitos funcionales

### Obligatorios

| # | Requisito | Unidad |
|:---:|:---|:---:|
| F1.1 | **Splash Screen** con Jetpack Compose y transición al onboarding o al catálogo. | UP05 |
| F1.2 | **Onboarding** de 2-3 páginas (`HorizontalPager` o pantallas) que pida el **nombre del usuario** con validación. Debe poder saltarse. | UP04 · UP05 |
| F1.3 | **Catálogo** de al menos **10 lugares** en `LazyColumn` o `LazyVerticalGrid`, con imagen, nombre, categoría e indicador de favorito. | UP04 |
| F1.4 | **Búsqueda** por nombre y **filtros** por categoría con `FilterChip`. | UP04 |
| F1.5 | **Detalle** del lugar: imagen (local o de internet con Coil), descripción, lista de retos y botón de favorito. | UP04 |
| F1.6 | **Formulario «Proponer lugar»** con al menos 5 campos de distinto tipo (texto, desplegable, chips, fecha, interruptor), **validación** y **diálogo** de confirmación. | UP04 |
| F1.7 | **Perfil** con el nombre del usuario, puntos e insignias (datos de ejemplo). | UP04 |
| F1.8 | **Ajustes**: tema claro/oscuro/sistema (en memoria durante esta fase). | UP05 |
| F1.9 | **Acerca de**: tu nombre y foto, versión y créditos. | UP04 |
| F1.10 | **Navegación** con Navigation 3: rutas en `Routes.kt`, paso de parámetros (id del lugar), barra inferior (`NavigationBar`) o menú lateral y gestión correcta de la pila (atrás no vuelve al splash ni al onboarding). | UP05 |
| F1.11 | **Tema de Material 3 propio** generado con Material Theme Builder (colores, tipografía y formas) y sin colores *hardcoded*. | UP05 |
| F1.12 | **Animaciones**: al menos **tres** de tipos distintos (por ejemplo, `AnimatedVisibility` en los filtros, `animateContentSize` en la descripción, transiciones entre pantallas, Lottie en el splash). | UP05 |
| F1.13 | **Diseño adaptativo**: en pantallas anchas o en horizontal, el catálogo y el detalle se muestran en **dos paneles** (maestro-detalle). | UP05 |
| F1.14 | Textos en **tres idiomas** (castellano, valenciano e inglés) y recursos centralizados. | UP03 |

### Ampliaciones (suben la valoración del nivel 4)

- Pantalla de **rutas** que agrupe lugares.
- Componente propio reutilizable con **previsualizaciones** (`@Preview`) para varios estados.
- **Accesibilidad** revisada con el *Accessibility Scanner*.

## Requisitos técnicos

- Kotlin y **Jetpack Compose**; `minSdk` justificado en la memoria.
- Organización en paquetes según la [descripción del proyecto](../contenidos/01-descripcion#arquitectura-evolutiva): `navigation`, `ui/theme`, `ui/components`, `ui/screens`, `data` (modelos y repositorio **en memoria**).
- Estado con `rememberSaveable` y **State Hoisting**: la interfaz no pierde datos al girar el dispositivo.
- Al menos **tres perfiles de dispositivo virtual** (AVD) distintos: un móvil pequeño, un móvil grande y una tableta o plegable.

## Análisis y modificación de una aplicación existente

Elige una de las aplicaciones oficiales de ejemplo de [android/compose-samples](https://github.com/android/compose-samples) (por ejemplo, **Reply**, **Jetsnack** o **JetNews**; licencia Apache 2.0):

1. Ejecútala en el **emulador**.
2. **Analiza su estructura**: paquetes, pantallas, componentes reutilizables, cómo implementa la navegación y el tema, y cómo se adapta a distintos tamaños de pantalla. Incluye un diagrama de su organización.
3. Realiza **dos modificaciones** documentadas (por ejemplo, cambiar el tema, añadir un elemento a una pantalla, una nueva opción de menú) en una rama propia del repositorio del ejemplo.
4. Explica **qué idea has incorporado** a *Explora* a partir del análisis (citando la fuente).

## Memoria técnica de la Fase 1

Apartados de `docs/MEMORIA.md`:

| Apartado | Contenido |
|:---|:---|
| 1. Descripción | Temática, público, funcionalidades y capturas. |
| 2. Limitaciones del dispositivo | Cómo afectan a *Explora* la pantalla, la batería, la memoria, el GPS, la cámara y la conectividad, y cómo las tendrás en cuenta en el diseño. |
| 3. Tecnologías | Por qué una app **nativa** con Kotlin y Compose frente a alternativas multiplataforma (Flutter, React Native, Kotlin Multiplatform). |
| 4. Entorno de desarrollo | Versiones de Android Studio, Kotlin, Gradle y librerías; dispositivos virtuales configurados. |
| 5. Configuraciones y perfiles | `minSdk` y `targetSdk` (con la distribución de versiones), clases de tamaño de ventana, recursos cualificados (`values-night`, `values-es`, `values-ca`…), orientación y elementos del manifiesto que relacionan la app con el dispositivo. |
| 6. Arquitectura | Paquetes, modelo de datos, mapa de navegación (diagrama de rutas) y componentes reutilizables. |
| 7. Análisis de la app de ejemplo | Lo descrito en el apartado anterior. |
| 8. Plan de pruebas | Tabla de casos de prueba de la interfaz y resultado en cada AVD. |
| 9. Conclusiones | Dificultades, decisiones y mejoras previstas para la Fase 2. |

## Entregables

1. Repositorio con la etiqueta **`fase-1`**, README, `docs/MEMORIA.md`, `docs/DIARIO.md` y `docs/CREDITOS.md`.
2. Proyecto comprimido (tras **Build → Clean Project**) en el aula virtual.
3. **Vídeo** (máximo 5 minutos) recorriendo todas las funcionalidades en dos AVD distintos (móvil y tableta).
4. **Defensa** según la [descripción del proyecto](../contenidos/01-descripcion#defensa-de-cada-fase).

## Relación con los criterios de evaluación

| RA | CE | Dónde se evidencia |
|:---|:---|:---|
| **RA1** | a, b | Memoria, apartados 2 y 3. |
| | c, h | Entorno configurado; pruebas en tres AVD (memoria 4 y 8, vídeo). |
| | d, e | Memoria 5; diseño adaptativo (F1.13) y recursos cualificados (F1.14). |
| | f, g | Análisis y modificación de la app de ejemplo. |
| **RA2** | a | Organización de paquetes, modelo y repositorio en memoria. |
| | b | Pantallas, controles, menús, diálogos y navegación (F1.1–F1.11). |
| | g | Plan de pruebas en emuladores. |
| | i | Memoria, README, diario, historial de Git. |
| **RA3** | f | Animaciones (F1.12). |

## Rúbrica de la Fase 1

| Elemento evaluable | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA1.a/b** Limitaciones y tecnologías<br>*Memoria 2-3* | Análisis genérico, sin relación con *Explora* | Limitaciones y tecnologías descritas | Cada limitación se traduce en una decisión de diseño; la elección de tecnología se compara con alternativas | Análisis aplicado y bien documentado con fuentes |
| **RA1.c/h** Entorno y emuladores<br>*Memoria 4, vídeo* | Un solo AVD o entorno sin documentar | Entorno documentado y tres AVD configurados y usados | Se explica qué aporta cada perfil de dispositivo a las pruebas | Pruebas sistemáticas en todos los AVD con resultados registrados |
| **RA1.d/e** Configuraciones y perfiles<br>*Memoria 5, F1.13, F1.14* | `minSdk` sin justificar; sin recursos cualificados | `minSdk` y `targetSdk` justificados, tres idiomas, modo oscuro y diseño adaptativo | Se relacionan las clases de tamaño, cualificadores y elementos del manifiesto con el comportamiento de la app | La app se adapta correctamente a todos los perfiles probados |
| **RA1.f/g** App existente<br>*Análisis y modificaciones* | Análisis superficial o sin modificaciones | Estructura analizada con diagrama y dos modificaciones funcionando | Modificaciones explicadas a partir del análisis de su código | Se incorpora y cita una idea del ejemplo en *Explora* |
| **RA2.a** Estructura de clases<br>*Paquetes, modelo, repositorio* | Código en pocos archivos o sin modelo | Paquetes, modelo de datos y repositorio en memoria según la descripción | Componentes pequeños, reutilizables y con State Hoisting justificado | Estructura preparada para la Fase 2 (fácil sustituir el repositorio) |
| **RA2.b** Interfaz, menús, alertas y controles<br>*F1.1–F1.9, F1.14* | Faltan pantallas o controles obligatorios | Todas las pantallas y controles funcionan, con validación y diálogos | Elección de componentes de Material 3 justificada; sin textos ni colores *hardcoded* | Interfaz cuidada, coherente y accesible |
| **RA2.b** Navegación y tema<br>*F1.10, F1.11* | Navegación incompleta o pila mal gestionada | Rutas con parámetros, barra o menú de navegación, tema propio | Pila gestionada en todos los casos (atrás, onboarding, giro) y explicada | Experiencia de navegación fluida con identidad visual propia |
| **RA3.f** Animaciones<br>*F1.12* | Menos de tres animaciones o sin sentido | Tres animaciones de tipos distintos | Cada animación aporta información al usuario y se explica su API | Animaciones integradas con el tema y sin afectar al rendimiento |
| **RA2.g** Pruebas de interacción<br>*Memoria 8* | Sin plan de pruebas | Plan con casos de prueba y resultados | Los errores encontrados se corrigen y se documentan | Pruebas que incluyen giro, idiomas, tema oscuro y accesibilidad |
| **RA2.i** Documentación y defensa<br>*README, memoria, diario, Git, defensa* | Documentación ausente o defensa insuficiente | Todos los documentos entregados; defensa correcta | Diario y commits reflejan el proceso real; responde con solvencia | Documentación clara y profesional; defensa bien estructurada |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
