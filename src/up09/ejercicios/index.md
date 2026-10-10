---
layout: doc
title: Actividades UP09
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP09 — Firebase

::: danger-box Trabajo personal
El uso de cualquier IA, generador de código o la copia de cualquier fuente supondrá una calificación de **cero** en la actividad.
:::

::: info-box Actividades propuestas
Las presentaciones originales de esta unidad no incluyen boletín. Estas actividades se proponen a partir de los contenidos y los criterios de evaluación de la unidad.
:::

## Actividad 1 — Integración y analítica (práctica, no evaluable) {#actividad-1}

1. Crea un proyecto llamado **FirebaseTuNombre** y un proyecto en la consola de Firebase.
2. Integra la aplicación siguiendo el [apartado 1.3](../contenidos/01-firebase-auth-notificaciones#_1-3-integrar-firebase-en-la-aplicacion): SHA-1, `google-services.json` y SDK.
3. Envía un **evento personalizado** de Analytics al pulsar un botón y comprueba que aparece en **Realtime Analytics** o en **DebugView**.
4. Fuerza un error con **Crashlytics** y localiza en la consola el archivo y la línea del fallo.

## Actividad 2 — Agenda en la nube {#actividad-2}

Crea un proyecto llamado **CloudAgendaTuNombre**: una agenda de contactos guardada en **Cloud Firestore** y protegida con **Firebase Authentication**.

**Pantalla de acceso**

- **Registro** e **inicio de sesión** con **correo y contraseña**, con validación de los campos (formato de email y contraseña de al menos 6 caracteres) y mensajes de error claros (email ya registrado, credenciales incorrectas…).
- Un botón **Iniciar sesión con Google** que **solo se muestre** si el parámetro `show_google_signup_button` de **Remote Config** vale `true`. La implementación del inicio de sesión con Google es **opcional**.
- Si el usuario ya tiene sesión iniciada, se navega directamente a la agenda.
- En la parte inferior, una **foto tuya** tipo carné y tu **nombre y apellido**.

**Agenda**

- La `TopAppBar` muestra el **email del usuario** y un botón para **cerrar sesión**.
- **Listado** de los contactos del usuario, que se actualiza **en tiempo real** al añadir, modificar o borrar.
- **Formulario** (en otra pantalla o en un diálogo) para **añadir y editar** contactos con nombre, teléfono y email.
- Opción para **eliminar** un contacto con **confirmación**.

**Requisitos técnicos**

- Cada usuario solo ve **sus** contactos: guárdalos en una colección por usuario, por ejemplo `users/{uid}/contacts`.
- **ViewModel** con `StateFlow`; el acceso a Firebase se hace fuera de los `@Composable` (ViewModel o repositorio).
- **Navegación** entre pantallas gestionando la pila (al cerrar sesión no se puede volver a la agenda con el botón atrás).
- Tema de Material 3 **personalizado**.

::: details Ampliación opcional: reglas de seguridad
Sustituye las reglas del modo de prueba por unas que solo permitan a cada usuario autenticado leer y escribir sus propios documentos:

```js
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    match /users/{userId}/{document=**} {
      allow read, write: if request.auth != null && request.auth.uid == userId;
    }
  }
}
```
:::

::: warning-box No compartas tus claves
No subas `google-services.json` a repositorios públicos. Para la entrega, incluye el proyecto comprimido tal cual en el aula virtual.
:::

## Entrega

Se entrega la **actividad 2**:

1. Graba un **vídeo** (máximo 5 minutos y 50 MB) en el que expliques el código de la **autenticación**, el acceso a **Firestore** y **Remote Config**, y muestres la ejecución junto a la **consola de Firebase** (usuarios, documentos y cambio del parámetro).
2. Antes de comprimir el proyecto: detén la aplicación, ejecuta **Build → Clean Project** y cierra Android Studio.
3. Comprime **la carpeta** del proyecto (no su contenido) en un `.zip`.
4. Adjunta en el aula virtual el vídeo y el archivo comprimido.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA2.f** Garantiza la persistencia en un almacén de datos<br>*Cloud Firestore* | No se guardan o no se leen los contactos | CRUD completo con actualización en tiempo real | Datos separados por usuario y acceso desde ViewModel/repositorio, justificado | Reglas de seguridad configuradas y gestión de errores de red |
| **RA2.e** Accede a servicios en la nube por HTTPS<br>*Firebase Auth y Remote Config* | La autenticación no funciona | Registro, inicio y cierre de sesión con correo; el botón de Google depende de Remote Config | Validaciones y mensajes de error de Firebase traducidos al usuario | Sesión persistente y flujo de acceso coherente (y, opcionalmente, Google) |
| **RA2.j** Establece los permisos y la configuración<br>*Manifiesto, SHA-1, `google-services.json`* | La app no se conecta a Firebase | Integración completa y permiso `INTERNET` | Se explica el papel de cada paso de la integración | Configuración limpia (BoM, catálogo de versiones) y claves no expuestas |
| **RA2.a/b** Estructura e interfaz<br>*MVVM, navegación, componentes* | Lógica en las pantallas o faltan pantallas | ViewModel con `StateFlow`, navegación y todas las pantallas pedidas | La pila se gestiona bien al iniciar y cerrar sesión; diálogo de confirmación | Interfaz cuidada, accesible y coherente con Material 3 |
| **RA2.i** Documenta el desarrollo<br>*Vídeo* | Vídeo ausente | Vídeo dentro de los límites | El vídeo muestra la consola y justifica las decisiones técnicas | Vídeo claro y bien estructurado; código con KDoc |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
