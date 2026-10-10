---
layout: doc
title: PF · Juego para Android
sidebar: true
outline: [2, 3]
aside: true
---

# 📋 Proyecto final — Juego para Android

**Tipo**: GDD + proyecto Unity + APK + defensa · **Peso**: 35 % · **Entrega**: semana 6

## Objetivos

- Establecer la lógica de un juego nuevo y documentar sus fases de diseño y desarrollo.
- Desarrollar e implantar el juego en un dispositivo móvil real.
- Realizar pruebas de funcionamiento y optimizar el juego.

Contenido de referencia: [apartado 6](../contenidos/06-fases-optimizacion-android), además de todos los anteriores.

## Enunciado

Diseña y desarrolla un **juego original, 2D o 3D, para Android**, jugable con la pantalla táctil y con una duración de partida de entre 1 y 5 minutos. Puedes partir de lo hecho en las prácticas 3 o 4, pero el juego final debe tener **mecánicas nuevas** respecto a ellas.

Ideas de partida: *endless runner*, puzle de físicas, *arcade* de esquivar, juego de habilidad con el acelerómetro, *tower defense* sencillo, *shooter* cenital.

### Fase 1 — Diseño (semana 5)
Redacta un **GDD** de entre 4 y 8 páginas siguiendo la estructura del [apartado 6.2](../contenidos/06-fases-optimizacion-android#_6-2-establecer-la-logica-de-un-juego-el-gdd), con el diagrama de estados del juego, los controles táctiles y la planificación de tareas. Entrégalo para recibir el visto bueno antes de seguir.

### Fase 2 — Desarrollo
Requisitos mínimos:
- Escenas: **menú**, **juego** (al menos un nivel completo o un modo infinito con dificultad creciente) y **fin de partida**.
- Controles **táctiles** (botones o joystick en pantalla, gestos o acelerómetro), con la UI adaptada al **área segura**.
- **Física**, **animación** y **sonido** (música y efectos en los eventos principales).
- **Persistencia** de la mejor puntuación o del progreso.
- **Pausa** y menú de opciones con, al menos, el volumen de música y efectos (*Audio Mixer*).
- Icono, nombre y pantalla de carga propios; *Package Name* `com.tunombre.nombrejuego`.

### Fase 3 — Pruebas y optimización
Escribe un **informe de pruebas y optimización** (`Assets/PRUEBAS.md` o PDF) con:
1. **Lista de comprobación** de las mecánicas y su resultado en el editor, en el *Device Simulator* y en el **móvil real**.
2. **Prueba de jugabilidad** con al menos **dos personas**: qué observaste y qué cambiaste.
3. **Análisis con el Profiler** conectado al móvil (*Development Build*): FPS, tiempo de CPU, *batches* y GC Alloc **antes y después** de aplicar al menos **dos optimizaciones** (*object pooling*, *sprite atlas*, horneado de luces, reducción de texturas, eliminar `GetComponent` en `Update`…), con capturas.

### Fase 4 — Implantación en Android
- Genera el **APK** (IL2CPP, ARM64) y, opcionalmente, el **AAB**.
- Instálalo en un **dispositivo real** y comprueba los **permisos** que solicita el juego.

### Fase 5 — Documentación y defensa
- **README.md** con versión de Unity, instrucciones, controles, escenas y créditos.
- **Defensa** en clase o por videoconferencia (máximo 10 minutos): demostración en el móvil, recorrido por la arquitectura del proyecto y respuesta a preguntas.

## Entregable

1. GDD (fase 1).
2. Proyecto comprimido según las [normas comunes](./#normas-de-entrega-comunes).
3. **APK** firmado.
4. Informe de pruebas y optimización, y README.
5. **Vídeo** de un máximo de 5 minutos grabado **en el móvil** (grabación de pantalla).

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA5.a** Establece la lógica de un nuevo juego<br>*GDD, mecánicas, estados* | GDD incompleto o juego sin objetivo claro | GDD completo e implementado: objetivo, reglas, estados y progresión | Decisiones de diseño justificadas (público, controles táctiles, dificultad) | Juego original, equilibrado y divertido; GDD actualizado al final |
| **RA5.h** Desarrolla e implanta el juego en un dispositivo móvil<br>*APK, controles táctiles, Player Settings* | El APK no se instala o no se puede jugar en el móvil | APK funcional en un dispositivo real con controles táctiles, icono y nombre propios | Ajustes de Android (IL2CPP, ARM64, orientación, área segura, permisos) explicados | Experiencia móvil pulida en distintas resoluciones |
| **RA5.i** Realiza pruebas de funcionamiento y optimización<br>*Informe y Profiler* | Sin pruebas o sin medidas | Lista de comprobación, prueba de jugabilidad y medidas del Profiler | Dos optimizaciones con medidas antes y después que demuestran la mejora | Rendimiento estable en el móvil y cambios derivados de las pruebas con usuarios |
| **RA5.j** Documenta las fases de diseño y desarrollo<br>*GDD, informe, README, créditos* | Documentación ausente o muy incompleta | Todos los documentos entregados | Los documentos reflejan el proceso real (cambios respecto al diseño inicial) | Documentación clara, bien estructurada y con fuentes y licencias citadas |
| **RA5.b–g** Calidad técnica del juego<br>*Objetos, escenas, física, sonido, cámara* | Errores que impiden terminar una partida | Todas las escenas y sistemas obligatorios funcionan | Arquitectura de componentes ordenada y código comentado | Juego completo y cohesionado; la defensa demuestra dominio del proyecto |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
