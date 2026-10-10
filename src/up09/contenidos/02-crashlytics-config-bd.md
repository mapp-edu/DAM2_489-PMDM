---
layout: doc
title: 2. Crashlytics, Remote Config y BD
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 2. Crashlytics, configuración remota y bases de datos

## 2.1. Crashlytics

**Crashlytics** recoge información y estadísticas de los **errores** que se producen durante la ejecución de la aplicación.

En la consola, entra en **Crashlytics** y pulsa **Agregar SDK** para ver las instrucciones:

::: tabs

== build.gradle.kts (Project)

```kotlin [build.gradle.kts]
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false

    alias(libs.plugins.google.services) apply false

    id("com.google.firebase.crashlytics") version "2.9.9" apply false
}
```

== build.gradle.kts (Module: app)

```kotlin [app/build.gradle.kts]
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.google.services)

    id("com.google.firebase.crashlytics")
}

dependencies {
    // …
    implementation("com.google.firebase:firebase-crashlytics")
}
```

:::

Para **terminar la configuración**, inicia la aplicación y **fuerza un error**, por ejemplo lanzando una excepción:

```kotlin
throw RuntimeException("Error Forzado")
```

Desde ese momento, en la consola verás todos los informes de error, incluido el **archivo y la línea** donde se produjo el fallo. Al pulsar un error se muestra más información, como la **pila de llamadas**, igual que en Android Studio.

### Información extra en los informes

```kotlin
// Se obtiene el mail del usuario registrado
val email = FirebaseAuth.getInstance().currentUser?.email ?: ""
// Se añade el mail como ID del usuario al informe de Crashlytics
Firebase.crashlytics.setUserId(email)
// Se añade un par clave-valor al informe de Crashlytics
Firebase.crashlytics.setCustomKey("usuario", "rick")
// Se añade información extra al informe de Crashlytics
Firebase.crashlytics.log("Información del error")
```

Puedes añadir tantos pares clave-valor como quieras con `setCustomKey` o todos a la vez con `setCustomKeys`:

```kotlin
Firebase.crashlytics.setCustomKeys {
    key("user", "rick")
    key("vip", true)
    key("id", 27)
    key("credit", 261.85)
}
```

## 2.2. Configuración remota (Remote Config)

Normalmente, para cambiar algo en una aplicación móvil hay que **publicar una nueva versión** en la tienda. Con **Remote Config** creas **parámetros** en la consola que la aplicación consulta, de modo que puedes **cambiar su comportamiento sin publicar una nueva versión**: el texto de un elemento, si un elemento se muestra o no…

### Crear los parámetros

En **Remote Config → Crear configuración**, cada parámetro es un **par clave-valor** con un tipo de datos (String, Booleano, Número o JSON) y un valor por defecto.

![Parámetro show_google_signup_button de tipo booleano con valor false y aviso de publicación pendiente](/img/contenidos/up09/up09-2-remote-config.webp)

::: warning-box Publica los cambios
Tras crear o modificar parámetros, pulsa **Publicar cambios**; si no, la aplicación no los verá.
:::

### Leer la configuración desde la aplicación

Añade la dependencia y usa la clase **`FirebaseRemoteConfig`**:

```kotlin [app/build.gradle.kts]
implementation("com.google.firebase:firebase-config")
```

```kotlin
// Se obtiene el objeto singleton de Remote Config
val remoteConfig: FirebaseRemoteConfig = Firebase.remoteConfig
```

Ejemplo: mostrar u ocultar el botón de registro con Google según el parámetro `show_google_signup_button`:

```kotlin
// Variable de estado para mostrar o no el botón de registro con Google
var showGoogleSignupButton by rememberSaveable { mutableStateOf(false) }
// Se obtiene la instancia de Firebase Remote Config
val remoteConfig = Firebase.remoteConfig
// Se configura el tiempo entre actualizaciones
val configSettings = remoteConfigSettings {
    minimumFetchIntervalInSeconds = 60
}
remoteConfig.setConfigSettingsAsync(configSettings)
// Se crea un valor por defecto por si la lectura de Firebase da error
remoteConfig.setDefaultsAsync(mapOf("show_google_signup_button" to false))
// Se accede a los datos de configuración almacenados en Firebase Remote Config
remoteConfig.fetchAndActivate().addOnCompleteListener { task ->
    // Si se accede correctamente se actualiza la variable de estado con el valor
    if (task.isSuccessful) {
        showGoogleSignupButton = remoteConfig.getBoolean("show_google_signup_button")
    }
}

// Según la variable de estado se mostrará o no el botón de registro con Google
if (showGoogleSignupButton) {
    GoogleButton()
    HorizontalDivider(modifier = Modifier.padding(bottom = 4.dp))
}
```

Al cambiar el valor a `true` en la consola y publicar, el botón **Iniciar sesión con Google** aparece en la aplicación sin reinstalarla.

::: tip-box Mejor fuera de la composición
En el ejemplo todo está dentro de un `@Composable` para simplificar, pero así se volvería a ejecutar en cada recomposición. En una aplicación real, haz la lectura en el **ViewModel** (o en un `LaunchedEffect`) y expón el valor con un `StateFlow`.
:::

### Valores por defecto

Si hay **varios parámetros**, añade **todas las claves** al mapa de valores por defecto:

```kotlin
remoteConfig.setDefaultsAsync(
    mapOf(
        "show_google_signup_button" to false,
        "google_signup_button_text" to "Iniciar sesión con Google"
    )
)
```

Si al crear los parámetros marcas **Usar la configuración predeterminada en la app**, puedes descargar un **XML** con los valores por defecto (menú ⋮ → **Descargar valores predeterminados**), copiarlo en `res/xml` y usarlo en lugar del mapa:

```kotlin
remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
```

## 2.3. Bases de datos en Firebase

Firebase ofrece dos bases de datos **NoSQL** (no relacionales):

| Base de datos | Características |
|:---|:---|
| **Realtime Database** | Versión clásica. Cada base de datos es **un archivo JSON**. El acceso puede ser lento cuando el volumen de datos es alto. |
| **Cloud Firestore** | Versión optimizada. Guarda los datos en varios JSON organizados en **colecciones, documentos y datos**; cada elemento puede contener cualquiera de los otros (un dato puede ser una colección). |

Firebase ofrece una [comparativa con encuesta](https://firebase.google.com/docs/database/rtdb-vs-firestore) para ayudarte a elegir.

## 2.4. Realtime Database

### Crear la base de datos

1. En **Realtime Database → Crear una base de datos**, elige la **ubicación** y las **reglas de seguridad**. Las reglas indican, por ejemplo, si el usuario debe estar autenticado para usar la base de datos. Para los ejemplos, elige el **modo de prueba** (lo puedes cambiar después; tienes más información sobre [reglas de seguridad](https://firebase.google.com/docs/database/security) en la documentación).
2. La primera base de datos toma el nombre del proyecto; las siguientes necesitan un nombre.
3. Para los ejemplos puede que tengas que poner las reglas `.read` y `.write` a `true` (usuario no autenticado).

::: danger-box Solo para pruebas
Las reglas abiertas (`true`) permiten que **cualquiera** lea y escriba en la base de datos. Úsalas solo durante el desarrollo.
:::

En el ejemplo se **importan los datos** desde un archivo `usuarios.json` (menú ⋮ → **Importar JSON**). Se crea la colección `users` con los registros `0`, `1`, `2`…

![Datos importados en Realtime Database: colección users con la dirección, email, nombre y teléfono de cada usuario](/img/contenidos/up09/up09-2-realtime-datos.webp){width=480px}

### Modelo de datos

```kotlin [User.kt]
data class User(
    val key: String? = null,
    val email: String = "",
    val name: String = "",
    val address: String = "",
    val phone: String = "",
)
```

La propiedad `key` guarda la **clave del registro** (en el ejemplo, `0`). Todas las propiedades tienen valor por defecto porque Firebase necesita un constructor sin parámetros para crear los objetos.

### Conexión

```kotlin [app/build.gradle.kts]
implementation("com.google.firebase:firebase-database")
```

```kotlin
private val db: FirebaseDatabase = Firebase.database

// Si la base de datos no es la de por defecto, se indica su URL completa
val dbRealTime = Firebase.database("https://dam-ejemplo-default-rtdb.europe-west1.firebasedatabase.app/")
```

Todas las operaciones se hacen sobre una **referencia a la colección**. Si la colección no existe, se crea al guardar el primer elemento:

```kotlin
// Se obtiene una referencia al nodo donde se encuentran los datos
//val dbReference = db.reference.child("users")
val dbReference = db.getReference("users")
```

### Añadir o modificar elementos

| Método | Clave del elemento |
|:---|:---|
| `child(clave)` | La indicas tú. |
| `push()` | Se genera una **clave aleatoria**, que puedes recuperar después. |

Si la clave **no existe**, se guarda el elemento; si **ya existe**, se **modifica**.

::: tabs

== child

```kotlin
val newUser = User(
    email = "morty@mail.com",
    name = "Morty Smith",
    address = "Smith Residence",
    phone = "111222333"
)
dbReference.child("3").setValue(newUser)
    .addOnSuccessListener {
        // Se almacena/modifica correctamente
    }
    .addOnFailureListener {
        // Se produce un error al almacenar/modificar
    }
```

== push

```kotlin
dbReference.push().setValue(newUser)
    .addOnSuccessListener {
        // Se almacena/modifica correctamente
    }
    .addOnFailureListener {
        // Se produce un error al almacenar/modificar
    }
```

== Clave aleatoria con child

```kotlin
// Obtener una clave aleatoria y usarla
val key = dbReference.push().key ?: "-1"
dbReference.child(key).setValue(newUser)
    .addOnSuccessListener {
        // Se almacena/modifica correctamente
    }
    .addOnFailureListener {
        // Se produce un error al almacenar/modificar
    }
```

:::

### Leer datos

::: tabs

== Una sola vez

`addListenerForSingleValueEvent` recupera **toda la colección una vez**; útil al cargar la aplicación.

```kotlin
val users = remember { mutableStateListOf<User>() }
dbReference.addListenerForSingleValueEvent(object : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        for (data in snapshot.children) {
            val user: User? = data.getValue(User::class.java)
            user?.let {
                users.add(it)
            }
        }
        // Se dispone de la lista de usuarios en la variable users
        //  se puede usar por ejemplo para rellenar una LazyColumn
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
})
```

== En cada cambio

`addValueEventListener` recupera **toda la colección** la primera vez y **cada vez que cambian los datos**.

```kotlin
val users = remember { mutableStateListOf<User>() }
dbReference.addValueEventListener(object : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) {
        users.removeAll(users)
        for (data in snapshot.children) {
            val user: User? = data.getValue(User::class.java)
            user?.let {
                users.add(it)
            }
        }
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
})
```

== Con la clave

En cualquiera de los anteriores puedes obtener la **clave** de cada elemento y guardarla en el objeto:

```kotlin
for (data in snapshot.children) {
    val user: User? = data.getValue(User::class.java)
    user?.copy(key = data.key)?.let {
        users.add(it)
    }
}
```

== Por elemento

`addChildEventListener` recupera **solo el elemento** añadido, modificado, eliminado o movido, la primera vez y en cada cambio.

```kotlin
dbReference.addChildEventListener(object : ChildEventListener {
    override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
        var user = snapshot.getValue(User::class.java)
        user = user?.copy(key = snapshot.key)
    }

    override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
        var user = snapshot.getValue(User::class.java)
        user = user?.copy(key = snapshot.key)
    }

    override fun onChildRemoved(snapshot: DataSnapshot) {
        var user = snapshot.getValue(User::class.java)
        user = user?.copy(key = snapshot.key)
    }

    override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
        TODO("Not yet implemented")
    }

    override fun onCancelled(error: DatabaseError) {
        TODO("Not yet implemented")
    }
})
```

:::

Para mostrar los usuarios:

```kotlin
Column {
    Text(text = "Usuarios")
    LazyColumn {
        items(users) {
            Text(text = it.name)
        }
    }
}
```

::: tip-box Recuerda MVVM
Igual que con Remote Config, en una aplicación real los *listeners* se registran en el **ViewModel** (o en un repositorio) y no dentro de un `@Composable`; de lo contrario se añadiría un *listener* nuevo en cada recomposición.
:::

### Eliminar un elemento

Se necesita su **clave**:

```kotlin
dbReference.child(key).removeValue()
```

## 2.5. Cloud Firestore

### Crear la base de datos

En **Firestore Database → Crear base de datos**, elige la **ubicación** y las **reglas de seguridad** (modo de prueba para los ejemplos).

![Creación de Cloud Firestore: elección del modo de producción o del modo de prueba con sus reglas](/img/contenidos/up09/up09-2-firestore-reglas.webp){width=480px}

Desde la consola puedes crear colecciones y documentos. En el ejemplo se crean, editan y eliminan **desde el código**.

### Conexión

```kotlin [app/build.gradle.kts]
implementation("com.google.firebase:firebase-firestore")
```

```kotlin
val db = Firebase.firestore
```

### Guardar datos

Se encadenan tres métodos:

| Método | Qué indica |
|:---|:---|
| `collection` | La **colección** donde se guardan los datos. |
| `document` | El **nombre del documento**; en el ejemplo, el email introducido. |
| `set` | Un **mapa** con los pares clave-valor que se guardan. |

Si el documento no existe, se crea; si existe, se actualiza.

![Formulario Guardar datos con email, nombre, dirección y teléfono, y botones Guardar y Borrar datos](/img/contenidos/up09/up09-2-firestore-formulario.webp){width=280px}

```kotlin
Column(
    modifier = Modifier.padding(16.dp)
) {
    Text(
        text = "Guardar datos",
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    TextField(
        value = email,
        onValueChange = { email = it },
        placeholder = { Text(text = "Correo electrónico") },
        modifier = Modifier.padding(bottom = 4.dp).fillMaxWidth()
    )
    TextField(
        value = name,
        onValueChange = { name = it },
        placeholder = { Text(text = "Nombre") },
        modifier = Modifier.padding(bottom = 4.dp).fillMaxWidth()
    )
    TextField(
        value = address,
        onValueChange = { address = it },
        placeholder = { Text(text = "Dirección") },
        modifier = Modifier.padding(bottom = 4.dp).fillMaxWidth()
    )
    TextField(
        value = phone,
        onValueChange = { phone = it },
        placeholder = { Text(text = "Número de teléfono") },
        modifier = Modifier.padding(bottom = 4.dp).fillMaxWidth()
    )

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            db.collection("users").document(email).set(
                hashMapOf(
                    "name" to name,
                    "address" to address,
                    "phone" to phone,
                )
            )
        }
    ) {
        Text(text = "GUARDAR")
    }
}
```

Se crea la colección **`users`**, con un documento por **correo electrónico** que contiene el resto de valores.

### Leer datos

::: tabs

== Un documento

```kotlin
db.collection("users").document("rick@mail.com").get().addOnSuccessListener {
    val user = User(
        email = "rick@mail.com",
        name = it.get("name").toString(),
        address = it.get("address").toString(),
        phone = it.get("phone").toString()
    )
}
```

Con `addOnSuccessListener` actúas cuando se obtiene el documento; las variables podrían ser los estados que usan los `Text` de la interfaz.

== Toda la colección

```kotlin
val users = remember { mutableStateListOf<User>() }
db.collection("users").get().addOnSuccessListener {
    users.removeAll(users)
    for (document in it.documents) {
        users.add(
            User(
                email = document.get("email").toString(),
                name = document.get("name").toString(),
                address = document.get("address").toString(),
                phone = document.get("phone").toString()
            )
        )
    }
}
```

:::

::: info-box El email es el id del documento
En el ejemplo el email no se guarda como campo, sino como **nombre del documento**: para recuperarlo al leer la colección usa `document.id` en lugar de `document.get("email")`.
:::

### Eliminar datos

Se usa `delete` sobre el documento:

```kotlin
Button(
    modifier = Modifier.weight(1f),
    onClick = {
        db.collection("users").document(email).delete()
        email = ""
        name = ""
        address = ""
        phone = ""
    }
) {
    Text(text = "BORRAR DATOS")
}
```

::: tip-box Practica
Ya puedes hacer las [actividades de la unidad](../ejercicios/).
:::
