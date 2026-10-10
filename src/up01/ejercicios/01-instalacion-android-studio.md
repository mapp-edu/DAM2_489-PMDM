---
layout: doc
title: Actividad 1 — Instalación de Android Studio
sidebar: true
outline: [2, 3]
aside: true
---

# Actividad 1 — Instalación de Android Studio

Descarga la versión de [Android Studio](https://developer.android.com/studio#downloads) correspondiente a tu sistema operativo.

::: tip-box Descarga la última versión
Cambia la página al idioma **inglés** para asegurarte de que descargas la última versión: la página en castellano a veces tarda en actualizarse.
:::

::: warning-box Conexión a internet
Durante la instalación necesitarás conexión a internet: Android Studio descarga componentes en la instalación y, después, al crear y ejecutar proyectos.
:::

Sigue la instalación correspondiente a tu sistema y continúa en [Primera ejecución](#primera-ejecucion-de-android-studio).

::: tabs

== Windows

1. Ejecuta el instalador **como administrador**.

   ![Ejecutar el instalador como administrador y aceptar el control de cuentas de usuario](/img/contenidos/up01/act1-p02-1.webp)

2. Pulsa **Next**, elige los componentes que se van a instalar y vuelve a pulsar **Next**.

   ![Bienvenida del instalador y selección de componentes](/img/contenidos/up01/act1-p02-2.webp)

3. Indica el directorio de instalación y si quieres crear accesos en el menú de inicio.

   ![Directorio de instalación y carpeta del menú de inicio](/img/contenidos/up01/act1-p02-3.webp)

4. Al terminar la instalación, el asistente pregunta si quieres ejecutar Android Studio.

   ![Fin de la instalación](/img/contenidos/up01/act1-p03-1.webp)

== LliureX / Ubuntu

1. Mueve el archivo `.tar.gz` descargado a tu carpeta personal.
2. Extráelo en la carpeta personal: se creará el directorio `android-studio`.
3. Dentro de ese directorio encontrarás el archivo `Install-Linux-tar.txt` con las instrucciones de instalación (las tienes resumidas aquí).
4. Abre una terminal (en LliureX, **Konsole**) y ejecuta:

   ```bash
   cd ~/android-studio/bin
   ./studio.sh
   ```

5. Comenzará la instalación y configuración de Android Studio. **No cierres la terminal** hasta terminar el siguiente apartado.

:::

## Primera ejecución de Android Studio

La primera vez que se ejecuta Android Studio hay que elegir una serie de opciones de configuración inicial.

::: info-box Datos de uso
Si durante el proceso se te pide enviar información de diagnóstico a Google, es preferible indicar que no.
:::

1. Android Studio pregunta si quieres importar una configuración anterior. Como es la primera vez, elige **Do not import settings**.

   ![Importar configuración: no importar](/img/contenidos/up01/act1-p05-1.webp)

2. Se inicia el asistente de configuración inicial.

   ![Asistente de configuración inicial](/img/contenidos/up01/act1-p05-2.webp)

3. Elige el asistente **personalizado** (*Custom*) para escoger la configuración y la ubicación del JDK.

   ![Tipo de instalación personalizada y ubicación del JDK](/img/contenidos/up01/act1-p05-3.webp)

4. Elige el tema de la interfaz, oscuro o claro. Podrás cambiarlo más adelante.

   ![Selección del tema de la interfaz](/img/contenidos/up01/act1-p05-4.webp)

5. Los componentes que aparecen dependen de tu equipo. Como mínimo debes instalar **Android SDK**, una **plataforma (API)** y **Android Virtual Device**. Según el sistema operativo aparecerá también el componente de aceleración del emulador.

   ![Componentes del SDK](/img/contenidos/up01/act1-p06-1.webp)

6. Si el equipo permite ejecutar el emulador en modo acelerado, podrás elegir la RAM que se le asigna.

   ![Configuración del emulador](/img/contenidos/up01/act1-p06-2.webp)

7. Revisa los componentes que se van a descargar y **acepta todas las licencias**.

   ![Verificación de componentes y aceptación de licencias](/img/contenidos/up01/act1-p06-3.webp)

8. Comienza la descarga e instalación de componentes. Su duración depende de la conexión a internet.

   ![Descarga de componentes](/img/contenidos/up01/act1-p07-1.webp)

9. Al terminar aparece la pantalla inicial de Android Studio, desde la que puedes crear o abrir proyectos, cambiar la configuración o instalar *plugins*.

   ![Pantalla de bienvenida de Android Studio](/img/contenidos/up01/act1-p07-2.webp)

## Ejecutar Android Studio

::: tabs

== Windows

Usa el acceso directo del menú de inicio. Si lo prefieres, crea también un acceso directo en el escritorio.

![Android Studio en el menú de inicio de Windows](/img/contenidos/up01/act1-p08-1.webp)

== LliureX / Ubuntu

Tienes dos opciones:

- Ir al directorio `android-studio/bin`, hacer doble clic en `studio.sh` y elegir **Ejecutar**.
- Abrir una terminal, ir al directorio `android-studio/bin` y ejecutar `./studio.sh`.

Para facilitar la tarea, crea en el escritorio un archivo llamado `Android-Studio.desktop` con el siguiente contenido, sustituyendo `USUARIO` por tu nombre de usuario:

```ini
#!/usr/bin/env xdg-open
[Desktop Entry]
Name=Android-Studio
Comment=Android Studio
Exec=/home/USUARIO/android-studio/bin/studio.sh
Icon=/home/USUARIO/android-studio/bin/studio.png
Terminal=false
Type=Application
```

:::

[Siguiente: Actividad 2 — Hello there! →](./02-hello-there)
