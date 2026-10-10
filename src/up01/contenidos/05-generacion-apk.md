---
layout: doc
title: 5. Generación de archivos .apk
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 5. Generación de archivos .apk

Una vez desarrollada una aplicación hay que generar el archivo **`.apk`**, que es el que se distribuye, ya sea a través de Google Play o directamente por internet.

Existen dos tipos de APK:

| Tipo | Uso |
|:---|:---|
| **APK de depuración** (*debug*) | Para pruebas. No se puede poner a disposición del público. |
| **APK firmado** (*release*) | Aplicación ya probada y depurada, lista para publicarse y distribuirse. |

::: info-box APK y AAB
Para publicar en Google Play, Google exige hoy el formato **Android App Bundle** (`.aab`), que también se genera desde el mismo asistente. El `.apk` firmado sigue siendo el formato para distribuir la aplicación fuera de la tienda o instalarla directamente en un dispositivo.
:::

## 5.1. APK de depuración

Con el proyecto abierto, basta con ir a **Build → Build Bundle(s) / APK(s) → Build APK(s)**.

![Menú Build → Build Bundle(s) / APK(s) → Build APK(s) de Android Studio](/img/contenidos/up01/up01-p55-1.webp)

Al terminar, Android Studio muestra una notificación en la esquina inferior derecha. Pulsando en **locate** se abre la carpeta que contiene el APK de depuración. Si pierdes la notificación, lo encontrarás dentro del proyecto, en `app/build/outputs/apk/debug`.

![Notificación de APK generado y carpeta con app-debug.apk](/img/contenidos/up01/up01-p56-1.webp)

## 5.2. APK firmado

Generar un APK firmado también es sencillo, aunque la primera vez hay que crear un **almacén de claves** (*keystore*). Se accede desde **Build → Generate Signed Bundle / APK…**

![Menú Build → Generate Signed Bundle / APK…](/img/contenidos/up01/up01-p57-1.webp)

**1. Elegir el formato.** En la ventana que se abre se marca la opción **APK**.

![Asistente de firma: opción APK seleccionada](/img/contenidos/up01/up01-p58-1.webp)

**2. Indicar el almacén y la clave.** Se indica la ruta y la contraseña del almacén de claves, y un alias y una contraseña para la clave.

![Asistente de firma: ruta y contraseñas del almacén de claves](/img/contenidos/up01/up01-p59-1.webp)

::: danger-box ¡No pierdas la clave!
Las claves identifican al autor de la aplicación una vez publicada: se usan, por ejemplo, para **publicar actualizaciones**. Si se pierde la clave, no podrás actualizar la aplicación publicada.
:::

**3. Crear el almacén de claves (si no existe).** Al crear el almacén se puede crear también la clave para la aplicación actual. Para cada clave se genera un **certificado** con los datos del desarrollador.

![Creación de un nuevo almacén de claves y de su certificado](/img/contenidos/up01/up01-p61-1.webp)

Tras crearlo, los campos de la ventana anterior se rellenan automáticamente.

![Asistente de firma con los datos del almacén rellenados](/img/contenidos/up01/up01-p62-1.webp)

**4. Elegir la variante.** En la última ventana se elige la variante que se quiere firmar: **release**, que es la versión que se publicará.

![Selección de la variante release](/img/contenidos/up01/up01-p63-1.webp)

Al terminar aparece de nuevo una notificación; **locate** abre la carpeta con el APK firmado (`app/release`).

![Notificación de APK firmado y carpeta con app-release.apk](/img/contenidos/up01/up01-p64-1.webp)

## 5.3. Nombre del archivo

Tanto el APK de depuración como el firmado se generan con un nombre genérico: `app-debug.apk` y `app-release.apk`. Puedes renombrarlos, por ejemplo, como `HelloThere-debug.apk` y `HelloThere.apk`.

::: tip-box Recuerda
Para probar la aplicación en un emulador no hace falta que el `.apk` esté firmado.
:::
