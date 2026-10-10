---
layout: doc
title: P1 · Análisis de motores y de un juego
sidebar: true
outline: [2, 3]
aside: true
---

# 📋 Práctica 1 — Análisis de motores y de un juego existente

**Tipo**: informe individual · **Peso**: 15 % · **Entrega**: semana 2

## Objetivos

- Identificar los elementos que componen la arquitectura de un juego 2D y de uno 3D.
- Analizar los componentes, entornos de desarrollo y características de varios motores.
- Reconocer los bloques funcionales y la representación lógica y espacial de la escena de un juego existente.

Contenidos de referencia: [apartado 1](../contenidos/01-arquitectura-motores) y [apartado 2](../contenidos/02-unity-escena).

## Enunciado

Elabora un **informe** (PDF o documento compartido, entre 6 y 10 páginas) con las cuatro partes siguientes.

### Parte A — Arquitectura de dos juegos móviles

Elige **un juego 2D y un juego 3D** publicados en Google Play que conozcas o que puedas probar. Para cada uno:

1. Ficha: título, estudio, género, año y **motor** con el que está hecho (indica cómo lo has averiguado).
2. **Bucle de juego principal** (*core loop*) en dos o tres frases.
3. **Tabla de bloques funcionales**: entrada, lógica de juego, física, gráficos y animación, audio, UI, IA, persistencia y red. Para cada bloque, describe cómo lo resuelve el juego con ejemplos concretos.
4. Diferencias de arquitectura entre ambos derivadas de que uno sea 2D y otro 3D (cámara, física, recursos, rendimiento).

### Parte B — Componentes de un motor

Dibuja un **diagrama** de los componentes de un motor de juegos (render, física, audio, animación, entrada, scripting, gestor de escenas, gestor de recursos, editor) y explica en una línea la función de cada uno. Indica, para **Unity**, qué tecnología o sistema concreto implementa cada componente (por ejemplo, física 3D: NVIDIA PhysX).

### Parte C — Comparativa de motores y entornos de desarrollo

Compara **cuatro motores** (Unity y otros tres a tu elección) en una tabla con, al menos: lenguajes, licencia y coste, plataformas de destino, soporte 2D/3D, entorno de desarrollo (editor, IDE recomendado, sistemas operativos), curva de aprendizaje, comunidad y documentación, y dos juegos conocidos hechos con él.

Termina con una **recomendación justificada**: qué motor elegirías para un juego 2D sencillo para Android de un equipo de dos personas y por qué.

### Parte D — Análisis de un juego en Unity

Descarga en Unity 6 un proyecto de ejemplo completo (por ejemplo, un **Microgame** o el **2D Game Kit** de [Unity Learn](https://learn.unity.com/)) y analiza su escena principal:

1. **Representación lógica**: captura de la *Hierarchy* y explicación de cómo está organizada (objetos padre, objetos vacíos, gestores, UI). Indica qué *tags* y *layers* se usan.
2. **Componentes**: para el jugador, un enemigo y la cámara, lista sus componentes y explica la función de cada uno.
3. **Representación espacial**: tipo de proyección de la cámara, sistema de coordenadas, posición del jugador y de dos objetos más, orden de dibujo (si es 2D) o iluminación (si es 3D), y forma de los colliders (actívalos en la vista *Scene* con los *Gizmos*).
4. **Bloques funcionales**: relaciona cada bloque de la Parte A con los scripts y componentes concretos del proyecto.

## Entregable

- Informe en PDF con portada, índice, capturas propias y **bibliografía** (cita todas las fuentes consultadas).

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA4.a** Identifica los elementos de la arquitectura 2D y 3D<br>*Parte A* | Falta un juego o la tabla de bloques está incompleta | Ambos juegos analizados con todos los bloques y el bucle de juego | Ejemplos concretos y diferencias 2D/3D razonadas | Análisis profundo, relacionado con el rendimiento en móvil |
| **RA4.b** Analiza los componentes de un motor<br>*Parte B* | Diagrama incompleto o erróneo | Diagrama completo con la función de cada componente | Cada componente se asocia a la tecnología concreta de Unity | Diagrama claro, propio y bien explicado |
| **RA4.c/d** Analiza entornos y motores<br>*Parte C* | Menos de cuatro motores o criterios incompletos | Comparativa completa con todos los criterios | Recomendación final justificada con los datos de la tabla | Fuentes oficiales actualizadas y citadas; conclusiones bien argumentadas |
| **RA4.e** Identifica los bloques funcionales de un juego existente<br>*Partes A y D* | Bloques genéricos sin relación con el juego | Bloques identificados en los juegos y en el proyecto Unity | Cada bloque se relaciona con scripts y componentes concretos | Análisis coherente entre las partes A y D |
| **RA4.f** Reconoce la representación lógica y espacial de la escena<br>*Parte D* | Solo capturas, sin explicación | Jerarquía, componentes y espacio descritos | Se explican la organización, las coordenadas, la cámara y los colliders | Explicación precisa con capturas anotadas |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
