---
layout: doc
title: Fase 2 · App conectada (2.ª evaluación)
sidebar: true
outline: [2, 3]
aside: true
---

# 📦 Fase 2 — *Explora* conectada

**Evaluación**: 2.ª · **Unidades**: [UP06](../../up06/) – [UP09](../../up09/) y la [guía técnica](../contenidos/02-guia-tecnica) · **Entrega**: semana 24 · **Defensa**: en un móvil real

## Objetivo

Convertir *Explora* en una aplicación **real**: arquitectura **MVVM**, datos **persistentes**, información de una **API REST**, cuentas y sincronización con **Firebase**, y uso de las capacidades del móvil: **ubicación y mapa**, **sensores**, **conectividad**, **cámara** y **audio**. La versión final se **instala en un dispositivo real**.

## Hitos

| Hito | Semana | Qué se revisa |
|:---|:---:|:---|
| Corrección de la Fase 1 | 14 | Incorporar las mejoras indicadas en la defensa de la Fase 1. |
| **H2.1** · Seguimiento | 17 | ViewModels con `StateFlow`; lugares, favoritos y retos completados en **Room**; preferencias en **DataStore**. |
| **H2.2** · Seguimiento | 23 | Versión instalada en un **móvil real** con API, Firebase, mapa y al menos un reto multimedia. |
| **Entrega Fase 2** | 24 | Todo lo que se indica en esta página, con la etiqueta Git `fase-2`. |

## Requisitos funcionales

### Arquitectura y datos

| # | Requisito | Unidad |
|:---:|:---|:---:|
| F2.1 | **MVVM**: cada pantalla con su `ViewModel`, estado expuesto con `StateFlow` (`UiState`) y eventos hacia el ViewModel. Ninguna lógica de datos en los `@Composable`. | UP06 |
| F2.2 | **Repositorio** único como fuente de datos para los ViewModels (patrón *single source of truth*). | UP06 · UP07 |
| F2.3 | **Room**: lugares, retos, favoritos y retos completados, con al menos **dos entidades relacionadas** y consultas que devuelvan `Flow`. | UP07 |
| F2.4 | **DataStore**: tema, idioma, onboarding visto y preferencias de notificaciones. | UP07 |
| F2.5 | Las **evidencias** de los retos (fotos y audios) se guardan en el **almacenamiento específico de la app**. | UP07 |

### Servicios en red

| # | Requisito | Unidad |
|:---:|:---|:---:|
| F2.6 | Consumo de una **API REST** con **Retrofit**: por ejemplo, el **tiempo actual** de cada lugar con [Open-Meteo](https://open-meteo.com/) (sin clave), datos abiertos de tu ayuntamiento o información de Wikipedia. Estados de **carga**, **éxito** y **error**. | UP08 |
| F2.7 | **Firebase Authentication**: registro e inicio de sesión con correo y contraseña (Google opcional); el onboarding y el perfil se adaptan a la sesión. | UP09 |
| F2.8 | **Cloud Firestore**: el **progreso** del usuario (puntos, retos completados, insignias) se sincroniza en `users/{uid}`; los lugares pueden cargarse desde Firestore. **Reglas de seguridad** por usuario. | UP09 |
| F2.9 | **Notificaciones**: FCM (por temas, por ejemplo una ruta nueva) y/o recordatorio local con **WorkManager**. Permiso de notificaciones en Android 13+. | UP09 · guía |
| F2.10 | **Remote Config** o **Crashlytics** (uno de los dos, como mínimo). | UP09 |

### Capacidades del dispositivo

| # | Requisito | Guía |
|:---:|:---|:---:|
| F2.11 | **Ubicación**: distancia a cada lugar en el catálogo y orden por cercanía. | [§4](../contenidos/02-guia-tecnica#_4-ubicacion-y-mapas) |
| F2.12 | **Mapa** con todos los lugares, la posición del usuario y navegación al detalle desde el marcador. | [§4](../contenidos/02-guia-tecnica#_4-ubicacion-y-mapas) |
| F2.13 | **Retos** de al menos **cuatro tipos**: `UBICACION` (estar a menos de X metros), `FOTO`, `AUDIO` y uno basado en **sensores** (`PASOS`, sacudida, brújula…). | [§3, §5](../contenidos/02-guia-tecnica#_3-sensores-ra2-d) |
| F2.14 | **Conectividad**: detección del tipo de red, **modo sin conexión** con los datos de Room y sincronización al recuperar la red. | [§2](../contenidos/02-guia-tecnica#_2-conectividad-e-inalambricos-ra2-c) |
| F2.15 | **Inalámbricos**: un reto o una función que use **Bluetooth LE** (detección de una baliza o de otro móvil) **o NFC** (lectura de una etiqueta). | [§2](../contenidos/02-guia-tecnica#_2-conectividad-e-inalambricos-ra2-c) |

### Multimedia

| # | Requisito | Guía |
|:---:|:---|:---:|
| F2.16 | **Captura** de foto y **grabación** de audio como evidencias de los retos. | [§5](../contenidos/02-guia-tecnica#_5-multimedia-ra3) |
| F2.17 | **Conversión** de las fotos a **WebP** reducido antes de guardarlas y **procesamiento** de al menos un tipo (miniatura, orientación EXIF, filtro o marca con el nombre del lugar). | [§5](../contenidos/02-guia-tecnica#_5-multimedia-ra3) |
| F2.18 | **Reproducción** con **Media3** de los audios grabados y de una **audioguía** o vídeo del lugar, con controles y gestión de errores. | [§5](../contenidos/02-guia-tecnica#_5-multimedia-ra3) |
| F2.19 | **Galería** de evidencias en el perfil. | UP04 |

### Ampliaciones (suben la valoración del nivel 4)

- Inicio de sesión con **Google**.
- Subida de las evidencias a **Firebase Storage**.
- Vista previa de cámara propia con **CameraX**.
- Pruebas unitarias de los ViewModels.

## Requisitos técnicos

- **Permisos** en tiempo de ejecución con explicación previa; la app funciona (con funciones reducidas) si se deniegan.
- Operaciones de red, base de datos y ficheros **fuera del hilo principal** (`viewModelScope`, `Dispatchers.IO`).
- **Ninguna clave** en el repositorio: `google-services.json` y claves de API fuera del control de versiones (o en un repositorio privado).
- **APK de publicación firmado** con tu propio *keystore*, con `versionCode` y `versionName` actualizados.

## Memoria técnica de la Fase 2

Amplía `docs/MEMORIA.md` con:

| Apartado | Contenido |
|:---|:---|
| 10. Arquitectura MVVM | Diagrama de capas y flujo de datos unidireccional de una pantalla completa. |
| 11. Persistencia | Esquema de Room (entidades y relaciones), claves de DataStore y estructura de Firestore con sus reglas. |
| 12. Servicios web | API elegida, *endpoints*, *data classes* y gestión de errores. |
| 13. Dispositivo | Ubicación, mapa, sensores, conectividad e inalámbricos: APIs usadas, permisos y comportamiento si no están disponibles. |
| 14. Multimedia | Comparativa de las APIs multimedia de Android (cámara, grabación, reproducción) y justificación de las elegidas; formatos usados y conversión; eventos y excepciones controlados. |
| 15. Permisos | Tabla de permisos: para qué se usa cada uno, cuándo se pide y qué ocurre si se deniega. |
| 16. Pruebas | Casos de prueba en emulador y en **móvil real**; incidencias encontradas, depuración con Logcat y *breakpoints*, y correcciones. |
| 17. Despliegue | Configuración de la firma, generación del APK/AAB e instalación en el dispositivo. |

## Entregables

1. Repositorio con la etiqueta **`fase-2`** y la documentación actualizada.
2. Proyecto comprimido y **APK firmado** en el aula virtual.
3. **Vídeo** (máximo 6 minutos) **grabado en el móvil real** que muestre todos los requisitos, incluido el modo sin conexión.
4. **Defensa** en un móvil real.

## Relación con los criterios de evaluación

| RA | CE | Dónde se evidencia |
|:---|:---|:---|
| **RA2** | a | MVVM y repositorio (F2.1, F2.2). |
| | c | Conectividad e inalámbricos (F2.14, F2.15). |
| | d | Retos con sensores y ubicación (F2.11, F2.13). |
| | e | API REST y servicios de Firebase (F2.6–F2.10). |
| | f | Room, DataStore, almacenamiento de la app y Firestore (F2.3–F2.5, F2.8). |
| | g | Plan de pruebas en emulador (memoria 16). |
| | h | APK firmado e instalado en un móvil real (memoria 17, vídeo, defensa). |
| | i | Memoria, README, diario, Git. |
| | j | Permisos (memoria 15, requisitos técnicos). |
| **RA3** | a | Comparativa de APIs multimedia (memoria 14). |
| | b | Captura y almacenamiento de fotos y audio (F2.5, F2.16). |
| | c, d | Conversión a WebP y procesamiento de imágenes (F2.17). |
| | e, g | Reproducción con Media3, eventos y excepciones (F2.18). |
| | h | Depuración y documentación de la parte multimedia (memoria 14 y 16). |

## Rúbrica de la Fase 2

| Elemento evaluable | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA2.a** Arquitectura MVVM<br>*F2.1, F2.2, memoria 10* | Lógica de datos en las pantallas | ViewModels con `StateFlow` y repositorio único | Flujo unidireccional explicado con diagrama; estados de carga y error | Arquitectura limpia y homogénea en todas las pantallas |
| **RA2.f** Persistencia<br>*F2.3–F2.5, F2.8, memoria 11* | Los datos se pierden al cerrar la app | Room, DataStore, ficheros y Firestore funcionando | Esquema y relaciones justificados; reglas de Firestore por usuario | Sincronización coherente entre local y nube, sin duplicados |
| **RA2.e** Servicios HTTP/HTTPS<br>*F2.6–F2.10, memoria 12* | La API o Firebase no funcionan | API REST y Firebase (Auth, Firestore, notificaciones, Remote Config o Crashlytics) operativos | Gestión de errores de red y de autenticación explicada | Servicios bien integrados en la experiencia del usuario |
| **RA2.c** Conectividad e inalámbricos<br>*F2.14, F2.15* | Sin detección de red ni función inalámbrica | Detección de red, modo sin conexión y función BLE o NFC | Comportamiento ante cambios de red y ausencia de hardware explicado | Sincronización automática y experiencia sin cortes |
| **RA2.d** Sensores y ubicación<br>*F2.11–F2.13* | Faltan los retos de ubicación o de sensor | Distancias, mapa y retos de ubicación y de sensor funcionando | Umbrales, frecuencia de lectura y consumo de batería justificados | Retos precisos y alternativas si el sensor no existe |
| **RA2.j** Permisos<br>*Memoria 15* | Permisos solo en el manifiesto o pedidos todos al inicio | Permisos pedidos en tiempo de ejecución al usarse | Explicación previa al usuario y app funcional si se deniegan | Solo los permisos imprescindibles, bien documentados |
| **RA3.a/b** Entornos, captura y almacenamiento<br>*F2.5, F2.16, memoria 14* | No se capturan fotos o audio | Foto y audio capturados y guardados | APIs elegidas justificadas frente a alternativas | Captura integrada con los retos y la galería |
| **RA3.c/d** Conversión y procesamiento<br>*F2.17* | Sin conversión ni procesamiento | Conversión a WebP y un procesamiento | Tamaño y calidad elegidos con medidas (antes/después) | Procesamiento en segundo plano sin bloquear la interfaz |
| **RA3.e/g** Reproducción, eventos y excepciones<br>*F2.18* | La reproducción falla o no tiene controles | Reproducción de audios y audioguía con controles | Eventos del reproductor y excepciones controlados e informados | Recursos liberados correctamente; experiencia fluida |
| **RA2.g/h** Pruebas y despliegue<br>*Memoria 16-17, vídeo, defensa* | Sin pruebas o el APK no se instala | Plan de pruebas y APK firmado instalado en móvil real | Incidencias del móvil real analizadas y corregidas | Funcionamiento estable en emulador y dispositivo |
| **RA2.i/RA3.h** Documentación, depuración y defensa<br>*Memoria, README, diario, Git* | Documentación incompleta o defensa insuficiente | Memoria actualizada y defensa correcta | Se documentan el proceso de depuración y las decisiones | Documentación profesional; defensa que demuestra dominio |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
