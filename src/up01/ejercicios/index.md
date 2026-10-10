---
layout: doc
title: Actividades UP01
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP01 — Preparando el entorno

En este boletín vas a dejar listo el entorno de trabajo del curso y comprobarás que funciona de principio a fin: desde crear un proyecto hasta ejecutarlo en el emulador y en un dispositivo real.

| Actividad | Contenido |
|:---|:---|
| [Actividad 0 — Repaso de programación](./00-repaso-programacion) | Cursos de Kotlin y Java para refrescar conceptos |
| [Actividad 1 — Instalación de Android Studio](./01-instalacion-android-studio) | Instalación en Windows o LliureX/Ubuntu y configuración inicial |
| [Actividad 2 — Hello there!](./02-hello-there) | Primer proyecto: estructura y modificación del texto |
| [Actividad 3 — Usando el emulador](./03-emulador) | Creación de un AVD y ejecución de la aplicación |
| [Actividad 4 — Probando en dispositivos reales](./04-dispositivo-real) | Modo desarrollador, depuración USB y controladores |
| [Codelab: tu primera app Android](./CodelabPrimeraAppAndroid) | Actividad complementaria (no evaluable) |

## Entrega

Entrega en el aula virtual **un único documento PDF** (máximo 6 páginas) que recoja las evidencias de las actividades 1 a 4:

1. **Entorno instalado.** Captura de la ventana *About Android Studio* con la versión instalada y del *SDK Manager* con la plataforma y las herramientas descargadas.
2. **Proyecto HelloThere.** Captura del asistente con el nombre de paquete siguiendo la convención `com.tunombretuapellido.hellothere`, y de la vista previa mostrando *Hello there!*.
3. **Emulador.** Captura del *Device Manager* con el Pixel 6a (API 35) y de la aplicación ejecutándose en él.
4. **Dispositivo real.** Captura o fotografía de la aplicación ejecutándose en tu dispositivo, indicando su modelo y versión de Android. Si no dispones de un dispositivo Android, explica qué has hecho en su lugar (por ejemplo, instalar el `.apk` en BlueStacks).
5. **APK.** Captura de la carpeta con el `.apk` de depuración renombrado como `HelloThere-debug.apk`.
6. **Reflexión.** Un párrafo sobre los problemas encontrados y cómo los has resuelto, y otro justificando el SDK mínimo elegido a partir de la distribución acumulativa.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA1.c** Instala, configura y utiliza el entorno de trabajo<br>*Evidencias 1 y 2* | Falta la instalación o el proyecto no compila | Android Studio y SDK instalados; proyecto creado con el paquete correcto | Explica los componentes instalados y la estructura del proyecto | Documenta la configuración de forma clara y reutilizable por otra persona |
| **RA1.e** Describe perfiles que relacionan dispositivo y aplicación<br>*Evidencia 6 (SDK mínimo)* | No indica el SDK mínimo o lo elige sin relación con la actividad | Indica el SDK mínimo y el nivel de API utilizados | Justifica la elección con la distribución acumulativa | Relaciona la elección con el perfil de dispositivos destinatario de la aplicación |
| **RA1.h** Utiliza emuladores para comprobar el funcionamiento<br>*Evidencia 3* | No hay AVD o la aplicación no se ejecuta | AVD Pixel 6a (API 35) creado y aplicación ejecutándose | Explica las opciones de configuración del AVD elegidas | Resuelve y documenta incidencias (errores de compilación, *Cold Boot*…) |
| **RA2.h** Empaqueta y despliega en dispositivos reales<br>*Evidencias 4 y 5* | No hay despliegue ni APK | Aplicación ejecutada en un dispositivo real (o alternativa justificada) y APK generado | Explica el proceso: modo desarrollador, depuración USB, controladores | Compara la ejecución en emulador y dispositivo real y extrae conclusiones |
| **RA2.i** Documenta los procesos de desarrollo<br>*Documento completo* | Documento desordenado o sin capturas significativas | Incluye todas las evidencias solicitadas | Las capturas están explicadas y en orden | Documento claro, bien maquetado y con la reflexión final bien argumentada |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
