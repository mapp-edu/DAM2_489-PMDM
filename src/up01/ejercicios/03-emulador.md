---
layout: doc
title: Actividad 3 — Usando el emulador
sidebar: true
outline: [2, 3]
aside: true
---

# Actividad 3 — Usando el emulador

En esta actividad vas a crear un dispositivo virtual (AVD) para ejecutar en el emulador la aplicación de la actividad anterior.

## Abrir el Device Manager

Abre el proyecto **Hello there** y, a continuación, el **Device Manager** (gestor de dispositivos virtuales). Puedes hacerlo de cualquiera de estas formas:

![Formas de abrir el Device Manager](/img/contenidos/up01/act1-p14-1.webp)

Por defecto el Device Manager se abre integrado en la ventana de Android Studio:

![Device Manager integrado](/img/contenidos/up01/act1-p14-2.webp)

Si lo prefieres, puedes configurarlo para que se abra en una ventana independiente:

![Opción para abrir el Device Manager en una ventana](/img/contenidos/up01/act1-p14-3.webp)

## Crear el dispositivo virtual

Todavía no hay ningún dispositivo. Pulsa **Add a new device… → Create Virtual Device** y elige el dispositivo.

::: tip-box Investiga
Aprovecha para revisar las opciones de los dispositivos predefinidos y las que aparecen al crear un nuevo perfil de hardware.
:::

1. Para trabajar todos en un entorno controlado, usaremos el mismo dispositivo: el **Pixel 6a**, que incluye la **Play Store** (la necesitaremos más adelante).

   ![Selección del dispositivo Pixel 6a](/img/contenidos/up01/act1-p15-1.webp)

2. Elige el nivel de API del dispositivo: **API 35**. Si no la tienes descargada, descárgala con el icono de descarga y espera a que termine.

   ![Selección de la imagen del sistema con API 35](/img/contenidos/up01/act1-p15-2.webp)

   ![Icono de descarga de la imagen del sistema](/img/contenidos/up01/act1-p15-3.webp)

3. Revisa la configuración y modifícala si lo necesitas. Pulsa **Show Advanced Settings** para ver todas las opciones: RAM, emulación de la cámara, red…

   ![Verificación de la configuración del AVD](/img/contenidos/up01/act1-p16-1.webp)

4. El dispositivo aparecerá en el Device Manager, desde donde podrás modificarlo, borrarlo o iniciarlo.

   ![AVD creado en el Device Manager](/img/contenidos/up01/act1-p16-2.webp)

En la barra de herramientas verás que ya hay un dispositivo seleccionado.

| Antes | Ahora |
|:---:|:---:|
| ![Barra de herramientas sin dispositivos](/img/contenidos/up01/act1-p16-3.webp) | ![Barra de herramientas con el Pixel 6a seleccionado](/img/contenidos/up01/act1-p16-4.webp) |

## Ejecutar la aplicación

Puedes iniciar el emulador en cualquier momento. La primera vez tarda un poco más porque termina de configurarse.

![Emulador en ejecución](/img/contenidos/up01/act1-p17-2.webp)

Desde la barra de herramientas:

![Botones Run y Debug](/img/contenidos/up01/act1-p17-3.webp)

1. **Run**: ejecuta la aplicación en el dispositivo seleccionado.
2. **Debug**: ejecuta la aplicación en modo depuración.

Para depurar, crea **puntos de ruptura** (*breakpoints*) haciendo clic junto al número de línea. Aparecerá un círculo rojo y la ejecución se detendrá en esa línea.

![Punto de ruptura en el editor](/img/contenidos/up01/act1-p17-4.webp)

### Si aparece un error de versiones

La primera vez que ejecutes una aplicación pueden aparecer errores de incompatibilidad entre la versión de la API y las librerías. Se muestran en la pestaña **Build**, en la parte inferior:

![Error de compilación por incompatibilidad de versiones](/img/contenidos/up01/act1-p18-1.webp)

Para solucionarlo, abre `build.gradle.kts (Module :app)` y actualiza la versión indicada. Después de modificar los archivos de Gradle, **sincroniza** el proyecto (**Sync Now**).

![Archivo build.gradle.kts del módulo app](/img/contenidos/up01/act1-p18-2.webp)

Cuando no haya errores, la aplicación se abrirá en el emulador.

![Hello there! en el emulador](/img/contenidos/up01/act1-p18-3.webp)

## Gestión de los AVD

Los dispositivos virtuales son un conjunto de archivos de configuración (un archivo `.ini` y un directorio con el mismo nombre) que puedes copiar como copia de seguridad o para compartirlos:

| Sistema | Ubicación |
|:---|:---|
| Windows | `C:\Users\NOMBRE_USUARIO\.android\avd` |
| Linux | `/home/NOMBRE_USUARIO/.android/avd` |

Consulta la [documentación oficial sobre los AVD](https://developer.android.com/studio/run/managing-avds?hl=es).

::: warning-box Si el emulador falla
A veces un dispositivo virtual empieza a funcionar mal. Con el emulador parado, haz un **Cold Boot** desde el Device Manager para reiniciarlo desde cero.

![Opción Cold Boot en el Device Manager](/img/contenidos/up01/act1-p19-1.webp)
:::

[Siguiente: Actividad 4 — Probando en dispositivos reales →](./04-dispositivo-real)
