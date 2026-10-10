---
layout: doc
title: Actividad 4 — Probando en dispositivos reales
sidebar: true
outline: [2, 3]
aside: true
---

# Actividad 4 — Probando en dispositivos reales

## Activar el modo desarrollador

1. Entra en **Ajustes**.
2. Ve a **Información del teléfono**.
3. Pulsa **siete veces seguidas** sobre **Número de compilación**.

![Activación de las opciones de desarrollador](/img/contenidos/up01/act1-p20-1.webp)

Aparecerá un aviso indicando que se han activado las opciones de desarrollador.

## Activar la depuración USB

Entra en el nuevo menú **Opciones para desarrolladores** (su ubicación cambia según la versión de Android; normalmente **Ajustes → Sistema → Opciones para desarrolladores**) y activa **Depuración por USB**.

![Activación de la depuración por USB](/img/contenidos/up01/act1-p20-2.webp)

Al conectar el dispositivo al ordenador por USB, acepta el uso de la depuración USB.

![Diálogo para permitir la depuración por USB](/img/contenidos/up01/act1-p20-3.webp)

## Instalar los controladores

Es posible que el controlador del dispositivo se instale automáticamente al conectarlo: Windows suele reconocer bien los dispositivos modernos.

Aunque tengas instalados los controladores del fabricante, se recomienda instalar también los **controladores genéricos de Google**: en Android Studio ve a **Tools → SDK Manager**, pestaña **SDK Tools**, y activa **Google USB Driver**.

![SDK Manager: pestaña SDK Tools con Google USB Driver](/img/contenidos/up01/act1-p21-1.webp)

![Instalación de Google USB Driver](/img/contenidos/up01/act1-p21-2.webp)

Con el dispositivo configurado, aparecerá en Android Studio como destino disponible para ejecutar las aplicaciones.

![Dispositivo real disponible en Android Studio](/img/contenidos/up01/act1-p21-3.webp)

::: tip-box Pruébalo
Ejecuta la aplicación **Hello there!** en tu dispositivo real.
:::

## Controladores de los fabricantes

Estas son las páginas de soporte de los principales fabricantes para descargar el controlador oficial. Es posible que algún enlace haya cambiado.

| Fabricante | Página de soporte |
|:---|:---|
| Acer | <http://www.acer.com/worldwide/support/> |
| Alcatel | <https://www.alcatelmobile.com/support/> |
| Asus | <https://www.asus.com/support/Download-Center/> |
| Dell | <http://support.dell.com/support/downloads/> |
| Fujitsu | <http://www.fmworld.net/product/phone/sp/android/develop/> |
| HTC | <http://www.htc.com/support> |
| Huawei | <http://consumer.huawei.com/en/support/index.htm> |
| Intel | <http://www.intel.com/software/android> |
| Lenovo | <http://support.lenovo.com/us/en/GlobalProductSelector> |
| LG | <http://www.lg.com/us/support/software-firmware> |
| Motorola | <https://motorola-global-portal.custhelp.com/app/answers/detail/a_id/88481/> |
| Samsung | <http://developer.samsung.com/galaxy/others/android-usb-driver-for-windows> |
| Sharp | <http://k-tai.sharp.co.jp/support/> |
| Sony | <http://developer.sonymobile.com/downloads/drivers/> |
| Toshiba | <http://support.toshiba.com/sscontent?docId=4001814> |
| Xiaomi | <http://www.xiaomi.com/c/driver/index.html> |
| ZTE | <http://support.zte.com.cn/support/news/NewsDetail.aspx?newsId=1000442> |

[Volver al índice de actividades y a la entrega](./)
