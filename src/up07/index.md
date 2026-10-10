---
layout: doc
title: UP07 — Persistencia
sidebar: true
outline: [2, 3]
aside: true
---

# 💾 UP07 — Persistencia

Casi todas las aplicaciones necesitan **guardar información** entre ejecuciones: preferencias del usuario, datos estructurados o archivos. En esta unidad aprenderás a usar **DataStore** para preferencias, la librería **Room** para bases de datos SQLite y el **sistema de archivos** de Android, tanto interno como externo.

## Contenidos

| Apartado | Qué vas a aprender |
|:---|:---|
| [1. DataStore](./contenidos/01-datastore) | Persistencia en Android, Preferences DataStore, lectura y escritura con corrutinas y `Flow`, datos complejos |
| [2. Room: entidades y DAO](./contenidos/02-room-entidades-dao) | Bases de datos en el dispositivo, Room y KSP, entidades, DAO y base de datos con el caso del gestor de tareas |
| [3. Room: ViewModel e interfaz](./contenidos/03-room-viewmodel-interfaz) | Estado de la pantalla, ViewModel con Room, interfaz, Splash Screen, varias tablas y cambios en la base de datos |
| [4. Sistema de archivos](./contenidos/04-sistema-archivos) | Almacenamiento específico de la aplicación, archivos internos, caché, almacenamiento externo y espacio libre |

## Actividades

| Actividad | Descripción |
|:---|:---|
| [Boletín de actividades UP07](./ejercicios/) | Onboarding con usuario (con rúbrica) y actividades propuestas |

## Resultados de aprendizaje trabajados

::: info-box RA1 · Aplica tecnologías de desarrollo para dispositivos móviles evaluando sus características y capacidades
- **a)** Se han analizado las limitaciones que plantea la ejecución de aplicaciones en los dispositivos móviles.
- **f)** Se ha analizado la estructura de aplicaciones existentes para dispositivos móviles identificando las clases utilizadas.
:::

::: info-box RA2 · Desarrolla aplicaciones para dispositivos móviles analizando y empleando las tecnologías y librerías específicas
- **a)** Se ha generado la estructura de clases necesaria para la aplicación.
- **b)** Se han analizado y utilizado las clases que modelan ventanas, menús, alertas y controles para el desarrollo de aplicaciones gráficas sencillas.
- **f)** Se han utilizado las clases necesarias para establecer conexiones con almacenes de datos garantizando la persistencia.
- **g)** Se han realizado pruebas de interacción usuario-aplicación para optimizar las aplicaciones desarrolladas a partir de emuladores.
- **i)** Se han documentado los procesos necesarios para el desarrollo de las aplicaciones.
:::

::: note-box Autoría
Material elaborado, con permiso del autor, a partir de las presentaciones de **Alejandro Torres Zahinos** (licencia original [CC BY-NC-ND 4.0](https://creativecommons.org/licenses/by-nc-nd/4.0/deed.es)).
:::

[Empezar →](./contenidos/01-datastore)
