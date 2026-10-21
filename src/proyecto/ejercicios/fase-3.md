---
layout: doc
title: Fase 3 · Minijuego (3.ª evaluación)
sidebar: true
outline: [2, 3]
aside: true
---

# 📦 Fase 3 — *Explora Games*: el minijuego

**Evaluación**: 3.ª · **Unidad**: [UP10](../../up10/) · **Entrega**: semana 30 · **Defensa**: en un móvil real

## Objetivo

Analizar motores y juegos existentes para **diseñar y desarrollar un minijuego** en **Unity 6** con la temática y la estética de *Explora*. El juego se desbloquea al completar una ruta, se **lanza desde la app** y le **devuelve la puntuación** obtenida. Se publica como APK propio en un **móvil Android**.

::: tip-box Ejemplos de minijuego según la temática
- Patrimonio: **puzle** que recompone la fachada de un monumento con piezas físicas.
- Senderismo: ***endless runner*** por un sendero esquivando obstáculos.
- Gastronomía: ***arcade*** de recoger ingredientes que caen.
- Deporte: juego de **habilidad con física** (canasta, penaltis, minigolf).
- Museo: **plataformas 2D** por las salas recogiendo obras.
:::

## Hitos

| Hito | Semana | Qué se revisa |
|:---|:---:|:---|
| Corrección de la Fase 2 | 25 | Incorporar las mejoras indicadas en la defensa de la Fase 2. |
| **H3.1** · Análisis y diseño | 26 | Análisis de motores y del juego de referencia, y **GDD** del minijuego (aprobado por el profesor). |
| **H3.2** · Seguimiento | 28 | Versión **jugable** de principio a fin (aunque sin arte final) ejecutándose en el móvil. |
| **Entrega Fase 3** | 30 | Todo lo que se indica en esta página, con la etiqueta Git `fase-3`. |

## Parte A — Análisis (RA4)

Incluye en `docs/MEMORIA_JUEGO.md`:

1. **Arquitectura de juegos 2D y 3D**: bucle de juego y bloques funcionales, y diferencias entre ambos tipos aplicadas a tu minijuego ([UP10 · 1](../../up10/contenidos/01-arquitectura-motores)).
2. **Componentes del motor**: diagrama de los componentes de Unity que usará tu juego (render, física, audio, animación, entrada, scripting, escenas) con la tecnología concreta de cada uno.
3. **Selección de motor y entorno**: comparativa de **tres motores** (Unity y otros dos) con los criterios de licencia, lenguaje, soporte 2D/3D, exportación a Android, rendimiento en móvil y entorno de desarrollo; conclusión justificada.
4. **Juego de referencia**: elige un juego publicado parecido al tuyo y analiza sus **bloques funcionales** y la **representación lógica y espacial** de una de sus escenas (objetos, jerarquía probable, cámara, coordenadas, colisiones). Si es un proyecto de Unity accesible (por ejemplo, un *Microgame*), analízalo directamente en el editor.

## Parte B — Desarrollo del minijuego (RA5)

### Requisitos funcionales

| # | Requisito | UP10 |
|:---:|:---|:---:|
| F3.1 | **GDD** con lógica, reglas, estados, controles táctiles, objetos y sus propiedades, escenas y planificación; actualizado al final. | [§6](../../up10/contenidos/06-fases-optimizacion-android) |
| F3.2 | **Escenas**: menú, juego (al menos **dos niveles** o un modo infinito con dificultad creciente) y fin de partida. Jerarquía ordenada y **prefabs**. | [§2](../../up10/contenidos/02-unity-escena) |
| F3.3 | **Objetos** con scripts en C# propios: jugador, al menos **dos tipos** de elementos interactivos (enemigos, obstáculos, coleccionables) y un **GameManager** con puntuación y estados. | [§3](../../up10/contenidos/03-scripting-csharp) |
| F3.4 | **Materiales y texturas**: en 3D, al menos cuatro materiales URP/Lit con propiedades distintas (metálico, rugoso con *normal map*, emisivo, transparente); en 2D, sprites propios o con licencia, **materiales Sprite-Lit** y al menos un material personalizado (Shader Graph o efecto). | [§4](../../up10/contenidos/04-juegos-2d) · [§5](../../up10/contenidos/05-juegos-3d) |
| F3.5 | **Física**: Rigidbody y colliders configurados, al menos dos **Physics Materials** con fricción o rebote diferentes y una mecánica que dependa de la física (masa, gravedad, fuerzas o colisiones). | [§4](../../up10/contenidos/04-juegos-2d) · [§5](../../up10/contenidos/05-juegos-3d) |
| F3.6 | **Sonido**: música en bucle y efectos en al menos **cinco eventos** del juego; **Audio Mixer** con volumen de música y efectos ajustable desde un menú. | [§5.6](../../up10/contenidos/05-juegos-3d#_5-6-fuentes-de-audio) |
| F3.7 | **Cámaras e iluminación**: cámara con **Cinemachine** (seguimiento, límites o cambio de cámara) e iluminación configurada (luces 2D o 3D; en 3D, iluminación horneada). | [§4](../../up10/contenidos/04-juegos-2d) · [§5](../../up10/contenidos/05-juegos-3d) |
| F3.8 | **Controles táctiles** (botones o joystick en pantalla, gestos o acelerómetro) y UI adaptada al **área segura**. | [§3](../../up10/contenidos/03-scripting-csharp) · [§4](../../up10/contenidos/04-juegos-2d) |
| F3.9 | **Persistencia** del récord (`PlayerPrefs`). | [§3](../../up10/contenidos/03-scripting-csharp) |
| F3.10 | **Integración con *Explora***: la app lanza el juego desde la pantalla *Juegos* cuando el usuario ha completado una ruta, y el juego **devuelve la puntuación** con un enlace profundo; la app la suma al progreso del usuario (Room y Firestore). | [Guía §7](../contenidos/02-guia-tecnica#_7-conectar-la-app-con-el-minijuego-fase-3) |
| F3.11 | **Identidad común**: colores, tipografía e icono coherentes con el tema de *Explora*. | — |

### Requisitos técnicos

- **Unity 6.3 LTS**, plantilla URP (*Universal 2D* o *Universal 3D*), Input System.
- Paquete `com.tunombretuapellido.exploragames`, **IL2CPP** y **ARM64**, APK firmado.
- Repositorio del juego con el [`.gitignore` de Unity](https://github.com/github/gitignore/blob/main/Unity.gitignore) (puede ser una carpeta `game/` del mismo repositorio).

## Parte C — Pruebas, optimización y documentación

1. **Lista de comprobación** de las mecánicas en el editor, en el *Device Simulator* y en el **móvil real**.
2. **Prueba de jugabilidad** con al menos dos personas y cambios realizados a partir de ella.
3. **Análisis con el Profiler** en el móvil (*Development Build*): FPS, CPU, *batches* y GC Alloc **antes y después** de al menos **dos optimizaciones**.
4. **Documentación** de las fases de diseño y desarrollo: GDD inicial y final, diario, memoria del juego y créditos de todos los assets.

## Entregables

1. Repositorio con la etiqueta **`fase-3`** (app actualizada y juego).
2. Proyecto de Unity comprimido (`Assets`, `Packages`, `ProjectSettings`), **APK del juego** y **APK de *Explora*** actualizado.
3. **Vídeo** (máximo 6 minutos) grabado en el móvil: completar una ruta en *Explora*, lanzar el juego, jugar una partida y ver la puntuación sumada en el perfil.
4. **Defensa final** (15 minutos): recorrido por las tres fases del proyecto.

## Relación con los criterios de evaluación

| RA | CE | Dónde se evidencia |
|:---|:---|:---|
| **RA4** | a, b | Parte A, apartados 1 y 2. |
| | c, d | Parte A, apartado 3. |
| | e, f | Parte A, apartado 4. |
| **RA5** | a | GDD y GameManager (F3.1, F3.3). |
| | b, c | Objetos, prefabs y escenas (F3.2, F3.3). |
| | d | Materiales y texturas (F3.4). |
| | e | Física (F3.5). |
| | f | Sonido (F3.6). |
| | g | Cámaras e iluminación (F3.7). |
| | h | APK en móvil real e integración con *Explora* (F3.8, F3.10). |
| | i | Pruebas y optimización (Parte C, 1-3). |
| | j | Documentación de las fases (Parte C, 4). |

## Rúbrica de la Fase 3

| Elemento evaluable | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA4.a/b** Arquitectura y componentes del motor<br>*Parte A 1-2* | Descripción genérica o incompleta | Bucle, bloques y componentes del motor descritos | Aplicados a su minijuego con la tecnología concreta de Unity | Diagramas propios, claros y precisos |
| **RA4.c/d** Selección de motor y entorno<br>*Parte A 3* | Menos de tres motores o sin criterios | Comparativa completa | Conclusión justificada con los datos y las necesidades del proyecto | Fuentes oficiales actualizadas y citadas |
| **RA4.e/f** Juego de referencia<br>*Parte A 4* | Análisis superficial | Bloques funcionales y representación lógica y espacial identificados | Relación explicada entre el juego de referencia y el propio | Análisis con capturas anotadas que inspira decisiones del GDD |
| **RA5.a** Lógica del juego<br>*GDD, GameManager* | GDD incompleto o juego sin objetivo | GDD completo e implementado | Decisiones de diseño (dificultad, controles, duración) justificadas | Juego equilibrado y divertido; GDD actualizado |
| **RA5.b/c** Objetos y escenas<br>*F3.2, F3.3* | Faltan escenas u objetos | Escenas, objetos, prefabs y scripts funcionando | Arquitectura de componentes ordenada; jerarquía clara | Código limpio y comentado; niveles bien diseñados |
| **RA5.d/g** Materiales, cámaras e iluminación<br>*F3.4, F3.7* | Materiales e iluminación por defecto | Materiales, cámara Cinemachine e iluminación configurados | Propiedades de materiales, tipos de luz y cámara justificados | Estética coherente con *Explora* y buen rendimiento |
| **RA5.e** Propiedades físicas<br>*F3.5* | Física inestable o ausente | Rigidbody, colliders y materiales físicos funcionando | Valores de masa, gravedad, fricción y rebote justificados | La física es parte esencial y divertida de la mecánica |
| **RA5.f** Sonido<br>*F3.6* | Sin efectos o sin música | Música, cinco efectos y Audio Mixer | Configuración de las fuentes y la mezcla explicada | Sonido que refuerza cada evento y la identidad del juego |
| **RA5.h** Implantación en móvil e integración<br>*F3.8, F3.10, F3.11* | El juego no se instala o no se integra con la app | APK en móvil real con controles táctiles; ida y vuelta con *Explora* | Ajustes de Android e integración (intent y enlace profundo) explicados | Experiencia unificada entre app y juego |
| **RA5.i** Pruebas y optimización<br>*Parte C 1-3* | Sin pruebas ni medidas | Lista de comprobación, prueba con usuarios y medidas del Profiler | Dos optimizaciones con mejoras demostradas | Rendimiento estable y cambios derivados de las pruebas |
| **RA5.j** Documentación y defensa final<br>*Parte C 4, defensa* | Documentación incompleta o defensa insuficiente | GDD, memoria, diario y créditos entregados; defensa correcta | La documentación refleja la evolución real del proyecto | Defensa final que relaciona con solvencia las tres fases |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
