---
layout: doc
title: 1. Integración, Auth y notificaciones
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 1. Firebase: integración, autenticación y notificaciones

::: warning-box Plataforma en constante cambio
Firebase evoluciona continuamente: es posible que alguna pantalla de la consola, versión de librería o API haya cambiado. Antes de implementar cualquier característica, **consulta la [documentación oficial](https://firebase.google.com/docs/android/setup)**. Aquí verás pequeñas muestras de algunos servicios, no todas sus capacidades.
:::

## 1.1. Qué es Firebase

[**Firebase**](https://firebase.google.com/) es una plataforma creada por Google en 2014 para ayudar en la creación de aplicaciones. Está disponible para aplicaciones Android e iOS, aplicaciones web, juegos y Unity, y se integra con iOS, Android, Web, C++ y Unity.

| Característica | Qué aporta |
|:---|:---|
| **Desarrollo** | Funcionalidades listas para usar que reducen el tiempo de desarrollo y optimización. |
| **Analítica** | Métricas para un análisis exhaustivo de la aplicación. |
| **Crecimiento** | Gestión de usuarios, invitaciones, notificaciones… |
| **Monetización** | Elementos para monetizar la aplicación. |
| **Rapidez** | API intuitiva y sencilla de usar. |
| **Agilidad** | Herramientas para distintas plataformas desde un mismo lugar. |

Ofrece servicios como **autenticación** (Google, Apple, usuarios propios…), **almacenamiento** en la nube, **hosting** o **bases de datos**. Es **gratuito con ciertos límites** ([precios y límites](https://firebase.google.com/pricing)).

En esta unidad verás ejemplos de: **Analytics**, **autenticación**, **notificaciones push**, **Crashlytics**, **configuración remota** y **bases de datos**.

::: info-box Permiso de internet
Toda aplicación que use Firebase necesita el permiso de acceso a internet en `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```
:::

## 1.2. Crear un proyecto en Firebase

1. Entra en la [consola de Firebase](https://console.firebase.google.com/) con una cuenta de Google.
2. Crea un **proyecto nuevo** con un nombre que no exista (la plataforma te avisará si ya existe).
3. Decide si usar **Google Analytics** y, en ese caso, crea o elige una cuenta de Analytics.
4. Al terminar tendrás disponible la **consola del proyecto**.

## 1.3. Integrar Firebase en la aplicación

Tras crear el proyecto en Android Studio, en la consola de Firebase elige el tipo de aplicación (**Android**) y sigue los pasos.

### Paso 1. Registrar la aplicación

Hay que indicar el **nombre del paquete**, un **alias** (nombre de la app) y el **certificado SHA-1**.

![Registro de la app en Firebase: nombre del paquete, sobrenombre y certificado SHA-1](/img/contenidos/up09/up09-1-registrar-app.webp){width=420px}

Para obtener la **firma SHA-1** (huella digital), ejecuta la tarea de Gradle **`signingReport`**:

1. Abre el panel **Gradle** (lateral derecho) y pulsa **Execute Gradle Task**.
2. Escribe `gradle signingReport` y pulsa <kbd>Enter</kbd>.
3. En la pestaña **Run** aparecen las claves de firma (MD5, SHA1, SHA-256) de cada variante. Copia la **SHA1** de la variante `debug`.

Si tienes Gradle en el *path* del sistema, también puedes ejecutar `gradle signingReport` desde la terminal.

::: warning-box Vuelve a seleccionar la app
Tras ejecutar la tarea, la configuración de ejecución queda en `signingReport`. Vuelve a seleccionar **app** en el desplegable de la barra superior; si no, al pulsar *Play* se volverá a ejecutar el comando de Gradle.
:::

### Paso 2. Archivo de configuración

Descarga **`google-services.json`** y muévelo a la carpeta **`app`** del proyecto (cambia a la vista **Project** para verla).

![Descarga de google-services.json y ubicación en la carpeta app del proyecto](/img/contenidos/up09/up09-1-google-services.webp)

### Paso 3. Añadir el SDK

::: tabs

== build.gradle.kts (Project)

```kotlin [build.gradle.kts]
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    id("com.google.gms.google-services") version "4.4.1" apply false
}
```

== build.gradle.kts (Module: app)

```kotlin [app/build.gradle.kts]
plugins {
    id("com.android.application")
    // Plugin de Google services
    id("com.google.gms.google-services")
    // …
}

dependencies {
    // Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.7.4"))

    // Con el BoM no se indican versiones en las dependencias de Firebase
    implementation("com.google.firebase:firebase-analytics")

    // Resto de productos: https://firebase.google.com/docs/android/setup#available-libraries
}
```

:::

Sincroniza Gradle para descargar las dependencias. Al terminar, la aplicación ya aparece en la consola de Firebase.

::: info-box Firebase BoM
El **BoM** (*Bill of Materials*) fija versiones compatibles entre sí de todas las librerías de Firebase, por eso en las dependencias no se indica la versión. Las versiones de los ejemplos son las del curso pasado: usa las que indique el asistente de la consola. Si tu proyecto usa el catálogo `libs.versions.toml`, puedes declararlas allí como en unidades anteriores.
:::

### Asistente de Android Studio

En **Tools → Firebase** hay un asistente que configura el proyecto automáticamente para cada servicio (Analytics, Authentication, Realtime Database, Firestore, Crashlytics, Cloud Messaging, Remote Config…) y muestra ejemplos de código. Algunos pasos siguen siendo manuales (por ejemplo, añadir la SHA-1 en la consola).

En clase los ejemplos se hacen **a mano** para entender todos los pasos.

En el panel lateral de la consola están todas las herramientas; al usar una, se crea un **acceso directo** en la parte superior.

## 1.4. Analytics

Al integrar la aplicación se añade la dependencia `firebase-analytics`. Con ella la aplicación envía a Firebase los datos para calcular métricas: **número de usuarios**, **ubicación**, **plataforma de acceso**… Por defecto se registran los [eventos automáticos](https://support.google.com/analytics/answer/9234069).

Basta con iniciar la aplicación en el emulador para ver datos en **Realtime Analytics**:

![Panel Realtime Analytics de Firebase con los usuarios activos en los últimos 30 minutos](/img/contenidos/up09/up09-1-analytics-realtime.webp)

### Eventos personalizados

Además de los [eventos automáticos](https://support.google.com/analytics/topic/13367566), puedes enviar **eventos personalizados**. Cada evento tiene un **nombre** y un bloque con **al menos un par clave-valor**:

```kotlin
// Creación de una instancia de FirebaseAnalytics
val analytics = Firebase.analytics
// Envío de evento a Analytics
analytics.logEvent(
    name = "configuration",
    block = {
        param("configuration", "Integración con Firebase completada")
        param("main_activity", "Cargada la pantalla principal")
    }
)
```

También puedes usar los **nombres de eventos y parámetros predefinidos** de las clases `FirebaseAnalytics.Event` y `FirebaseAnalytics.Param`, por ejemplo `FirebaseAnalytics.Event.SCREEN_VIEW` para registrar la pantalla a la que se accede.

::: info-box Hasta 24 horas
Según la documentación, los eventos pueden tardar **hasta 24 horas** en aparecer en el apartado **Events**. Mientras tanto, puedes usar **DebugView** para verlos en tiempo real desde los dispositivos de desarrollo.
:::

## 1.5. Autenticación

Firebase ofrece varias opciones para crear rápidamente un sistema de **registro e inicio de sesión**, despreocupándote del almacenamiento de las credenciales:

| Tipo | Ejemplos |
|:---|:---|
| Métodos nativos | Correo electrónico y contraseña, número de teléfono, anónimo. |
| Métodos de terceros | Google, Apple, Facebook, GitHub, Microsoft, Twitter… |
| Métodos personalizados | Requieren desarrollar el sistema de autenticación en otra plataforma. |

En **Authentication** de la consola, la pestaña **Users** muestra los usuarios autenticados y **Sign-in method** permite habilitar los proveedores. En el curso verás **correo y contraseña** y **Google**.

![Proveedores de acceso de Firebase Authentication: nativos, adicionales y personalizados](/img/contenidos/up09/up09-1-proveedores-auth.webp)

::: warning-box En segundo plano
Haz el registro y la autenticación **en segundo plano** (corrutinas y funciones de suspensión): así la aplicación no se bloquea si internet va lento o hay algún problema.
:::

## 1.6. Autenticación con correo y contraseña

1. En **Authentication → Sign-in method**, elige **Correo electrónico/contraseña**, actívalo y pulsa **Guardar**.
2. Añade la dependencia y sincroniza:

   ```kotlin [app/build.gradle.kts]
   implementation("com.google.firebase:firebase-auth")
   ```

3. Diseña las pantallas de **registro** y de **inicio de sesión**.

### Registro de usuario

Con los datos de los campos de la aplicación (en el ejemplo, literales), se registra al usuario. La contraseña debe tener **al menos 6 caracteres**:

```kotlin
val auth = Firebase.auth
auth.createUserWithEmailAndPassword("rick@mail.com", "123456")
```

Con **`addOnCompleteListener`** sabes si el registro ha sido correcto; por ejemplo, falla si el email ya está registrado:

```kotlin
val auth = Firebase.auth
auth.createUserWithEmailAndPassword(
    "morty@mail.com",
    "123456"
).addOnCompleteListener {
    if (it.isSuccessful) {
        // Registro correcto
    } else {
        // Registro incorrecto
    }
}
```

El usuario registrado aparece en la pestaña **Users** de la consola.

### Inicio de sesión

```kotlin
val auth = Firebase.auth
auth.signInWithEmailAndPassword(
    "rick@mail.com",
    "123456"
).addOnCompleteListener {
    if (it.isSuccessful) {
        // Acceso correcto
    } else {
        // Acceso incorrecto
    }
}
```

### Datos del usuario y cierre de sesión

```kotlin
// Leer datos del usuario autenticado
val message = "Bienvenido: ${auth.currentUser?.email}"
auth.currentUser?.photoUrl

// Modificarlos
auth.currentUser?.updatePassword("654321")

// Cerrar la sesión
auth.signOut()
```

## 1.7. Autenticación con una cuenta de Google

La autenticación con Google permite **usar las credenciales guardadas** en el dispositivo, **añadir** las de una cuenta existente o **crear** una cuenta nueva. Se abre una ventana del sistema para elegir la opción.

1. En **Sign-in method**, habilita el proveedor **Google** (indica el correo de asistencia del proyecto) y guarda.
2. **Vuelve a descargar `google-services.json`** desde **Configuración del proyecto** y sustituye el del proyecto.
3. Añade la dependencia y sincroniza:

   ```kotlin [app/build.gradle.kts]
   implementation("com.google.android.gms:play-services-auth:21.0.0")
   ```

4. Necesitas el **identificador de cliente web** (`client_id` del archivo `google-services.json` o de la consola). Centralízalo en `strings.xml`:

   ```xml [res/values/strings.xml]
   <resources>
       <string name="app_name">FirebaseExample</string>
       <string name="default_web_client_id">TU_ID_DE_CLIENTE.apps.googleusercontent.com</string>
   </resources>
   ```

### Registro e inicio de sesión

El *launcher* recoge el resultado de la ventana de Google y, si todo va bien, autentica en Firebase con esa credencial:

```kotlin
// Código que mostrará la ventana de registro/login de Google (con las credenciales ya almacenadas en el dispositivo)
//  Se capturará el resultado del proceso registro/login
val googleSignInLauncher = rememberLauncherForActivityResult(
    contract = ActivityResultContracts.StartActivityForResult()
) { result ->
    if (result.resultCode == Activity.RESULT_OK) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
        try {
            val account = task.getResult(ApiException::class.java)
            if (account != null) {
                val credential = GoogleAuthProvider.getCredential(account.idToken, null)
                FirebaseAuth.getInstance().signInWithCredential(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        // Autenticación correcta
                        // Con la variable account se puede acceder a la información del usuario
                        Log.i("LOGIN Google", "Correcto")
                    } else {
                        // Autenticación errónea
                        Log.i("LOGIN Google", "Incorrecto")
                    }
                }
            }
        } catch (apiException: ApiException) {
            // Error durante la autenticación
            Log.i("LOGIN Google", "Excepción")
        }
    }
}
```

El botón configura el cliente de Google y lanza la ventana:

```kotlin
val context = LocalContext.current

Button(onClick = {
    // Se obtiene la configuración del cliente de Google
    val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(context.getString(R.string.default_web_client_id))
        .requestEmail()
        .build()
    // Se obtiene el cliente de Google para realizar las acciones necesarias
    val googleClient = GoogleSignIn.getClient(context, googleConf)
    // Se crea el mensaje para la ventana que mostrará el registro/login de Google
    val signInIntent = googleClient.signInIntent
    // Se lanza la ventana del registro/login de Google
    googleSignInLauncher.launch(signInIntent)
}) {
    Text("Login con Google")
}
```

El dispositivo **recuerda** al usuario aunque cierre la aplicación. Para cerrar la sesión por completo:

```kotlin
// Cerrar la sesión de Firebase
Firebase.auth.signOut()
// Cerrar la sesión de Google
Identity.getSignInClient(context).signOut()
```

::: info-box Credential Manager
La API `GoogleSignIn` de *play-services-auth* está **obsoleta**. Google recomienda usar **[Credential Manager](https://developer.android.com/identity/sign-in/credential-manager-siwg)** con `GetGoogleIdOption` para el inicio de sesión con Google. El flujo con Firebase es el mismo: obtener el `idToken`, crear la credencial con `GoogleAuthProvider.getCredential` y llamar a `signInWithCredential`.
:::

## 1.8. Notificaciones con Firebase Cloud Messaging

**Messaging** (FCM) permite enviar **notificaciones** desde la consola a los dispositivos con la aplicación instalada.

### Configuración

1. Añade la dependencia y sincroniza:

   ```kotlin [app/build.gradle.kts]
   implementation("com.google.firebase:firebase-messaging")
   ```

2. Registra el **servicio** en el manifiesto, dentro de `<application>`, para recibir notificaciones en segundo plano:

   ```xml [AndroidManifest.xml]
   <service
       android:name=".MyFirebaseMessagingService"
       android:exported="false">
       <intent-filter>
           <action android:name="com.google.firebase.MESSAGING_EVENT" />
       </intent-filter>
   </service>
   ```

3. Crea la clase **`MyFirebaseMessagingService`**:
   - **`onNewToken`**: recibe el *token* del dispositivo, que sirve para enviarle notificaciones concretas.
   - **`onMessageReceived`**: recibe las notificaciones y **crea la notificación** que se muestra en el dispositivo.

```kotlin [MyFirebaseMessagingService.kt]
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        // Tratar la notificación Firebase recibida
        remoteMessage.notification?.let { message ->
            // A través de la variable message se puede acceder a toda la información de la notificación recibida

            // Se indica que al pulsar la notificación se navegue a la MainActivity de la aplicación
            val intent = Intent(this, MainActivity::class.java).apply {
                addFlags(FLAG_ACTIVITY_CLEAR_TOP)
            }

            // Variable necesaria para que se muestre la notificación sin estar en el contexto de la aplicación
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

            // Construcción de la notificación de Android
            val channelID = "FirebaseExample"
            val channelName = "Example notification"
            val notificationBuilder = NotificationCompat.Builder(this, channelID)
                .setContentTitle(message.title)
                .setContentText(message.body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Icono vectorizado en carpeta drawable
                .setAutoCancel(false)
                .setContentIntent(pendingIntent)

            // Obtención del administrador de notificaciones del sistema Android
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            // Si el dispositivo tiene una versión de Android superior a Oreo se debe crear un canal de notificaciones
            //  En caso contrario no es necesario
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
                manager.createNotificationChannel(channel)
            }

            // Se indica a Android que genere la notificación en el dispositivo.
            // Al crear la notificación se necesita un identificador, en el ejemplo se genera de manera aleatoria
            manager.notify(Random.nextInt(), notificationBuilder.build())
        }
    }
}
```

::: info-box FLAG_IMMUTABLE
El original usaba `PendingIntent.FLAG_MUTABLE`. Si el `PendingIntent` no necesita modificarse, la recomendación de Android es usar **`FLAG_IMMUTABLE`**, que es más seguro (desde Android 12 es obligatorio indicar una de las dos).
:::

### Enviar notificaciones desde la consola

En **Messaging** pulsa **Crear la primera campaña** y elige el tipo de mensaje:

| Tipo | Descripción |
|:---|:---|
| **Mensajes de Firebase Notifications** | Notificación push: llega aunque el usuario esté fuera de la aplicación. |
| **Mensajes desde la app de Firebase** (In-App) | Se muestra dentro de la aplicación cuando el usuario la usa. |

## 1.9. Notificaciones push generales

Para enviar una notificación a **todos los dispositivos**, crea una campaña de **Firebase Notifications** y completa los pasos: **título y texto** de la notificación, **orientación** (la app destino), **programación** (por ejemplo, *Ahora*) y opciones adicionales como el sonido. Al publicarla, aparece en el dispositivo y en Logcat.

### Permiso en Android 13 o superior

El ejemplo anterior funciona en versiones **anteriores a Android 13**. A partir de Android 13, el usuario debe **conceder el permiso** de notificaciones **en tiempo de ejecución**. Cambios necesarios:

1. Crea una clase `Application` que configure el **canal** de notificaciones:

   ```kotlin [AndroidFirebaseApp.kt]
   class AndroidFirebaseApp : Application() {
       override fun onCreate() {
           super.onCreate()

           val channelID = "FirebaseExample"
           val channelName = "Example notification"
           val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
               val channel = NotificationChannel(channelID, channelName, NotificationManager.IMPORTANCE_HIGH)
               manager.createNotificationChannel(channel)
           }
       }
   }
   ```

2. En el manifiesto, añade el **permiso** y registra la clase:

   ```xml [AndroidManifest.xml]
   <uses-permission android:name="android.permission.INTERNET" />
   <uses-permission android:name="android.permission.POST_NOTIFICATIONS" />

   <application
       android:name=".AndroidFirebaseApp"
       android:allowBackup="true"
       …>
   ```

3. La documentación aconseja unificar la apariencia de las notificaciones con estos `meta-data` dentro de `<application>`, usando recursos de tu aplicación (icono en `drawable`, color y nombre de canal centralizados):

   ```xml [AndroidManifest.xml]
   <meta-data
       android:name="com.google.firebase.messaging.default_notification_icon"
       android:resource="@drawable/ic_stat_ic_notification" />
   <meta-data
       android:name="com.google.firebase.messaging.default_notification_color"
       android:resource="@color/colorAccent" />
   <meta-data
       android:name="com.google.firebase.messaging.default_notification_channel_id"
       android:value="@string/default_notification_channel_id" />
   ```

4. **Pide el permiso** al iniciar la aplicación, en `MainActivity`:

   ```kotlin [MainActivity.kt]
   class MainActivity : ComponentActivity() {
       override fun onCreate(savedInstanceState: Bundle?) {
           super.onCreate(savedInstanceState)

           // Variable para crear la ventana de petición de permisos
           val requestPermissionLauncher = registerForActivityResult(
               ActivityResultContracts.RequestPermission()
           ) { isGranted ->
               // Si se necesita se pueden realizar acciones cuando el usuario pulsa otorga o deniega los permisos
               if (isGranted) { } else { }
           }

           // Solo se piden los permisos si la versión de Android es 13 o superior
           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
               if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) ==
                   PackageManager.PERMISSION_GRANTED
               ) {
                   // FCM SDK (and your app) can post notifications.
                   // No es necesario realizar nada, el sistema guarda la información automáticamente
               } else if (shouldShowRequestPermissionRationale(Manifest.permission.POST_NOTIFICATIONS)) {
                   // Se puede mostrar un mensaje al usuario indicando por qué es buena idea aceptar los permisos.
                   //  Es habitual añadir los botones tipo "Aceptar" y "No gracias"
               } else {
                   // Se lanza la ventana de petición de permisos
                   requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
               }
           }

           setContent {
               // …
           }
       }
   }
   ```

Al ejecutar la aplicación aparece la petición del sistema; si el usuario acepta, recibirá notificaciones en Android 13 o superior.

![Diálogo del sistema: Allow FirebaseExample to send you notifications?](/img/contenidos/up09/up09-1-permiso-notificaciones.webp){width=320px}

## 1.10. Notificaciones a un único dispositivo y por temas

### Un único dispositivo

`onNewToken` se ejecuta la primera vez que se usa la aplicación (porque el servicio está registrado en el manifiesto) y genera un **token único por dispositivo**. Para enviarle una notificación desde la consola, al crear la campaña pulsa **Enviar mensaje de prueba** y añade el token.

En una aplicación real, el token se guardaría en una preferencia y, al autenticarse el usuario, junto a sus datos; así, otra aplicación podría enviar notificaciones a usuarios concretos. Esta funcionalidad es más avanzada y no se ve en el curso.

### Por temas (*topics*)

Puedes enviar notificaciones solo a los dispositivos **suscritos a un tema**:

```kotlin
Firebase.messaging.subscribeToTopic("DAM2627")
```

Los temas se pueden crear con la técnica que quieras: versión de Android, ubicación… Al crear la campaña, en **Orientación** elige **Tema** e indica su nombre.

## 1.11. Notificaciones In-App

Las notificaciones **In-App** se muestran **dentro de la aplicación** a todos los dispositivos que la tengan instalada.

1. Añade las dependencias y sincroniza:

   ```kotlin [app/build.gradle.kts]
   implementation("com.google.firebase:firebase-inappmessaging-display")
   implementation("com.google.firebase:firebase-analytics")
   ```

2. En **Messaging**, crea una campaña de tipo **Mensajes desde la app de Firebase** (o **Campaña nueva → In-App Messaging** si ya tienes alguna).
3. Configura el **estilo y contenido** (tarjeta, modal, imagen o banner; colores, texto, imagen y botones), el **destino**, la **programación** (eventos que la activan, como `on_foreground` o `app_launch`, y frecuencia) y publica.

Al abrir la aplicación, si hay una notificación pendiente, se muestra con la configuración indicada.

::: warning-box Una vez al día
Para ahorrar energía, In-App Messaging **solo recupera mensajes del servidor una vez al día**. Para probar, la documentación explica cómo [enviar mensajes a un dispositivo de prueba](https://firebase.google.com/docs/in-app-messaging/get-started?platform=android#test_message). Además, debes **habilitar la API** del proyecto en [Google Cloud](https://console.cloud.google.com/apis/api/firebaseinappmessaging.googleapis.com) con la misma cuenta de la consola de Firebase.
:::

## 1.12. Datos extra en la notificación

Al crear la notificación, en **Opciones adicionales → Datos personalizados** puedes añadir **pares clave-valor** (por ejemplo, `url` → `https://midominio.com`). Se reciben junto con la notificación:

```kotlin
override fun onMessageReceived(remoteMessage: RemoteMessage) {
    super.onMessageReceived(remoteMessage)

    val data: Map<String, String> = remoteMessage.data
    val url = data["url"]

    // Tratar la notificación Firebase recibida
    remoteMessage.notification?.let { message ->
        // …
    }
}
```

Si la aplicación está **abierta**, puedes usar los datos directamente. Si está **cerrada**, añádelos al `Intent` de la notificación para tenerlos al abrir la aplicación:

::: tabs

== MyFirebaseMessagingService.kt

```kotlin
val intent = Intent(this, MainActivity::class.java).apply {
    addFlags(FLAG_ACTIVITY_CLEAR_TOP)
    // Se añade el dato recibido en la notificación Firebase para poder usarlo tras pulsar la notificación
    putExtra("url", url)
}
```

== MainActivity.kt

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        if (intent.hasExtra("url")) {
            val url = intent.getStringExtra("url")
            Log.i("NOTIFICATION", "url: $url")
        }

        super.onCreate(savedInstanceState)
        // …
    }
}
```

:::

Con estos datos puedes, por ejemplo, **navegar a una pantalla u otra**, mostrar **unos botones u otros** en la notificación o cualquier otra funcionalidad que necesites.
