---
layout: doc
title: Actividades UP07
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP07 — Persistencia

::: danger-box Trabajo personal
El uso de cualquier IA, generador de código o la copia de cualquier fuente supondrá una calificación de **cero** en la actividad.
:::

## Actividad 1 — Onboarding con usuario {#actividad-1}

Crea un proyecto llamado **PreferencesTuNombre** con los siguientes elementos:

**Splash Screen**

- Una **foto tuya** tipo carné (actual) en el centro y, debajo, tu **nombre y apellido**.
- Debe durar **5 segundos**.
- Si **no hay datos de usuario** guardados en DataStore, se navega al **Onboarding**.
- Si **hay datos** guardados, se recuperan y se navega a la **pantalla principal**.

**Onboarding** (una única pantalla)

- Pide al usuario su **nombre** (solo letras y longitud mínima 3) y su **número de teléfono** (solo números y longitud 9).
- Contiene tu **foto** tipo carné junto a tu **nombre**.
- Un botón que solo estará **activo** cuando los dos campos sean correctos. Al pulsarlo, se **guardan los datos en DataStore** y se navega a la pantalla principal.

**Pantalla principal**

- Tu **foto** tipo carné junto a tu **nombre**.
- El **nombre** y el **número de teléfono** del usuario.
- Un botón para **cerrar sesión**: borra los datos de DataStore y navega al Onboarding.

Requisitos técnicos:

- Se debe usar **navegación**, **ViewModel**, **StateFlow** y **DataStore**.
- La interfaz debe tener **colores diferentes** a los colores por defecto (tema de Material 3 personalizado).
- Al pulsar atrás no deben volver a aparecer la Splash Screen ni el Onboarding.

::: info-box LiveData o StateFlow
El enunciado original habla de `LiveData`. En los apuntes se usa **`StateFlow`**, la opción recomendada actualmente con Jetpack Compose.
:::

## Entrega

1. Graba un **vídeo** (máximo 5 minutos y 50 MB) en el que expliques el código del **guardado, lectura y borrado de los datos en DataStore** y muestres la ejecución: primer arranque, cierre y reapertura de la aplicación con los datos guardados, y cierre de sesión.
2. Antes de comprimir el proyecto: detén la aplicación, ejecuta **Build → Clean Project** y cierra Android Studio.
3. Comprime **la carpeta** del proyecto (no su contenido) en un `.zip`.
4. Adjunta en el aula virtual el vídeo y el archivo comprimido.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA2.f** Garantiza la persistencia de los datos<br>*DataStore* | Los datos no se guardan o se pierden al cerrar la app | Se guardan, se leen al arrancar y se borran al cerrar sesión | Acceso a DataStore encapsulado (repositorio o clase propia) y uso de corrutinas/`Flow` justificado | Gestión de errores y datos coherentes en todas las pantallas |
| **RA2.a** Genera la estructura de clases necesaria<br>*MVVM, navegación* | Lógica en las pantallas o sin ViewModel | ViewModel con `StateFlow`, rutas y pantallas separadas | La Splash decide la ruta según los datos guardados y se explica el flujo | Arquitectura limpia y reutilizable |
| **RA2.b** Utiliza las clases de interfaz y controles<br>*Formulario, validación y tema* | Faltan pantallas o elementos pedidos | Pantallas completas; botón activo solo con datos válidos; tema personalizado | Validaciones con mensajes de ayuda y teclado adecuado a cada campo | Interfaz cuidada, accesible y coherente con Material 3 |
| **RA2.g** Prueba la interacción en el emulador<br>*Ciclo completo de uso* | No se prueba el reinicio de la aplicación | Se muestra el ciclo completo en el emulador | Se prueban casos límite (datos incorrectos, botón atrás, giro) | Comportamiento robusto y fluido |
| **RA2.i** Documenta el desarrollo<br>*Vídeo* | Vídeo ausente | Vídeo dentro de los límites | El vídeo justifica las decisiones técnicas | Vídeo claro y bien estructurado; código con KDoc |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->

## Actividades propuestas (no evaluables)

Para practicar **Room** y el **sistema de archivos**, puedes ampliar el caso de estudio del gestor de tareas:

- Añade a las tareas una **fecha límite** y una **prioridad**, haciendo la **migración** de la base de datos en lugar de borrarla.
- Crea una segunda tabla de **categorías** y relaciona cada tarea con una categoría.
- Añade un botón que **exporte** las tareas a un archivo de texto en el almacenamiento específico de la aplicación y compruébalo con el **Device Explorer**.
