---
layout: doc
title: P2 · Modificar un juego existente
sidebar: true
outline: [2, 3]
aside: true
---

# 📋 Práctica 2 — Modificaciones sobre un juego existente

**Tipo**: proyecto Unity + vídeo · **Peso**: 10 % · **Entrega**: semana 3

## Objetivos

- Identificar en el código y en el editor los bloques funcionales de un juego existente.
- Modificar objetos, propiedades físicas y lógica de un juego sin romper su funcionamiento.
- Documentar las modificaciones realizadas.

Contenidos de referencia: [apartados 2](../contenidos/02-unity-escena) y [3](../contenidos/03-scripting-csharp).

## Enunciado

Parte del **mismo proyecto de ejemplo** que analizaste en la Parte D de la [Práctica 1](./01-analisis-motores) (un Microgame o el 2D Game Kit) y renómbralo **ModGameTuNombre**. Realiza estas modificaciones:

### 1. Ajustes de jugabilidad
Cambia al menos **tres parámetros** del jugador o del mundo (velocidad, fuerza de salto, gravedad, vida, daño…) desde el **Inspector**. Explica qué script o componente controla cada uno.

### 2. Propiedades físicas
- Crea un **Physics Material** (o *Physics Material 2D*) para una superficie **resbaladiza** y otro para una **rebotadora**, y aplícalos a dos objetos o zonas del nivel.
- Añade un objeto dinámico nuevo (caja, barril, pelota) con **Rigidbody** y masa adecuada con el que el jugador pueda interactuar.

### 3. Un objeto nuevo con lógica propia
Crea un **prefab nuevo** que no exista en el juego, con su **script en C#**. Elige uno:
- Un **coleccionable** que sume puntos o tiempo y reproduzca un sonido al recogerlo.
- Una **plataforma móvil** que se desplace entre dos puntos.
- Una **zona trampa** que reste vida o reinicie al jugador.

Coloca **al menos tres instancias** en el nivel, con algún valor sobrescrito (*override*) en alguna de ellas.

### 4. Un cambio en el nivel
Modifica la escena: añade una zona nueva, cambia la distribución o crea un atajo. Organiza los objetos nuevos en la jerarquía con un **objeto vacío** contenedor.

### 5. Registro de cambios
Escribe un archivo **CAMBIOS.md** en `Assets` con una tabla: *qué se ha cambiado*, *dónde* (objeto, componente o script) y *por qué*.

## Entregable

Proyecto comprimido según las [normas comunes](./#normas-de-entrega-comunes) y **vídeo** en el que muestres cada modificación funcionando y expliques el script del objeto nuevo.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA4.e** Identifica los bloques funcionales del juego<br>*Ajustes y CAMBIOS.md* | No se sabe qué controla cada parámetro | Los parámetros modificados se asocian a su script o componente | Se explica cómo encaja cada cambio en el bloque funcional correspondiente | El registro de cambios es claro y completo |
| **RA4.f** Representación lógica y espacial<br>*Cambio en el nivel* | Objetos desordenados o mal colocados | Zona nueva integrada en la jerarquía y en el espacio del nivel | Uso adecuado de contenedores, capas y orden de dibujo | El nivel modificado mantiene el estilo y la coherencia del original |
| **RA5.b** Crea objetos y define sus características<br>*Prefab nuevo* | El prefab no funciona o no es un prefab | Prefab con script funcional y tres instancias | Campos configurables con `[SerializeField]` y *overrides* justificados | Código limpio, comentado y reutilizable |
| **RA5.e** Establece las propiedades físicas<br>*Materiales físicos y objeto dinámico* | No se aprecian los cambios físicos | Superficie resbaladiza, rebotadora y objeto dinámico funcionan | Valores de masa, fricción y rebote razonados | La física mejora la jugabilidad sin errores |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
