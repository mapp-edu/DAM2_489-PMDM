---
layout: doc
title: Programación Multimedia y Dispositivos Móviles
description: Módulo profesional 489 correspondiente al segundo curso del CFGS Desarrollo de Aplicaciones Multiplataforma.
sidebar: true
outline: [2, 3]
aside: true
---

# Guía Didáctica PMDM

## Programación Multimedia y Dispositivos Móviles

::: tip Información del Curso

**Profesor:** Miquel Àngel París i Peñaranda

**Ciclo Formativo:** Desarrollo de Aplicaciones Multiplataforma (DAM)

**Familia Profesional:** Informática y Comunicaciones

**Curso:** Segundo

**Horas semanales:** 3 hora (modalidad semipresencial)

**Horas totales:** 133 horas (de las 2000 del ciclo formativo)

**Año Académico:** 2026-2027

**Centro:** IES Serpis

<table>
<tr>
<td><img src="/img/logo-centro.png" alt="Logo Centro" width="150"/></td>
<td><img src="/img/logo-gva.png" alt="Logo GVA" width="150"/></td>
</tr>
</table>

:::

::: info Descripción del Curso

Las orientaciones pedagógicas del título sitúan la función profesional en tres ejes —aplicaciones multimedia, aplicaciones móviles con persistencia y comunicaciones, y videojuegos 2D/3D—, y esa es exactamente la estructura en tres bloques que adopta esta programación.

  > Aunque las líneas generales se mantendrán durante todo el curso, esta guía es un documento vivo que puede ir actualizándose (básicamente aclaraciones) a lo largo del curso. Todas las modificaciones serán notificadas a través del foro de _Novedades_ del aula del módulo.
:::

::: details Vocabulario del módulo

Este módulo llama a las cosas por el nombre que reciben **fuera del aula**, en una empresa. Puede que no te suenen todavía, así que aquí están todas juntas:

| Palabra | Qué es aquí |
|:---|:---|
| **Activity** (f.) | Representa una única pantalla con una interfaz de usuario en Android. |
| **Actor** (m.) | Objeto de libGDX que representa un nodo de un Stage que tiene propiedades y sobre el que podemos realizar acciones. |
| **Adaptador** (m.) | Objeto que recibe un conjunto de elementos y les da un formato adecuado para usarlos en otro contexto. |
| **ADT** (f.) | Android Development Tools. Herramientas para el desarrollo de aplicaciones en Android. |
| **API** (Application Program Interface) (f.) | Protocolo utilizado como interfaz por los componentes de software para comunicarse entre sí. |
| **ART** (m.) | Nuevo runtime de Android a partir de la versión 5.0 (Lollipop) que sustituye al anterior, Dalvik. |
| **Arquitectura de un juego** (f.) | La arquitectura de un juego define la estructura lógica del mismo. |
| **Callback** (m.) | Método que se pasa como argumento de otro método para que pueda ser llamado en un momento dado (por ejemplo, cuando ha terminado una tarea en concreto). |
| **Códec** (compresor-despresor) (m.) | Método que recibe un flujo de información (típicamente audio o vídeo) y lo comprime para ahorrar memoria; cuando las aplicaciones quieren reproducir este contenido, el códec descomprime la información para que se pueda volver a reproducir. |
| **Compresión de datos** (f.) | Método por el cual se puede representar una misma información con menor cantidad de memoria. En el caso del audio, imagen y vídeo, se puede comprimir con pérdida, lo que significa que la información se comprime mucho más pero se pierde algo de calidad en el proceso. |
| **Contenedor** (m.) | Referido a formatos multimedia, es un formato de archivo que permite almacenar algún tipo de contenido (generalmente audio o vídeo) con diferentes calidades, características y métodos de compresión. |
| **Llamada asíncrona** (f.) | Llamada a un método u operación que retorna enseguida pero cuyo resultado no está disponible inmediatamente, sino pasado un tiempo. |
| **Cursor** (m.) | A menudo las búsquedas de información dan como resultado una tabla donde cada fila corresponde a uno de los elementos encontrados. Un cursor es como una flecha que señala una de las filas de la tabla y permite recorrerla, accediendo a las filas una por una. |
| **Dalvik** | Proceso de la máquina virtual del sistema operativo de Android. |
| **Delta** (m.) | Valor que representa la diferencia de tiempo que ha pasado entre dos llamadas del método render en LibGDX. La división 1/delta nos permite obtener los fps (frames por segundo) de la aplicación. |
| **Densidad de píxeles** (f.) | Cantidad de píxeles por cada pulgada. Se mide en ppi (píxeles por pulgada). |
| **Depuración** (debugging) (f.) | Proceso con el cual se intenta eliminar los posibles errores de una aplicación, generalmente utilizando herramientas específicas que muestran los contenidos de las aplicaciones y las ejecutan de manera controlada. |
| **Direct3D** (n.) | API de gráficos propiedad de Microsoft para los sistemas operativos Windows y videoconsolas Xbox. |
| **Oyente** (listener) (m.) | Objeto que recibe actualizaciones automáticamente de algún otro elemento y, por tanto, vigila su estado y puede responder a sus cambios. |
| **Event** (m.) | Evento. Clase del sistema que envuelve la información generada por un evento del sistema. |
| **Event listener** (m.) | Interfaz de la clase View que contiene un único método de callback. Estos métodos serán llamados por el sistema Android cuando el View (al cual está registrado el event listener) se active por la interacción del usuario. |
| **Excepción** (f.) | Error de ejecución que se puede producir en una aplicación y que se puede capturar para darle un tratamiento adecuado y evitar que la aplicación falle. |
| **Hilos de ejecución** (m.) | Un hilo de ejecución es la unidad de procesamiento más pequeña que se puede programar en un sistema operativo. Múltiples hilos pueden existir dentro de un proceso y comparten recursos (como memoria, código y contexto), cosa que no sucede entre diferentes procesos. |
| **Filtros de intención** (intent filters) (m.) | Definidos en el AndroidManifest.xml, sirven para definir las intenciones a las que responderá la actividad. |
| **Fotograma** (frame) (m.) | Cada una de las imágenes que se ven en un vídeo y que, al cambiar en una secuencia rápida (típicamente entre 15 y 60 por segundo), dan la sensación de movimiento. |
| **FPS** (frames per second) (m.) | Medida de la velocidad de ejecución de una aplicación gráfica que se actualiza continuamente. |
| **Frame** | Véase fotograma. |
| **Frame rate** | Véase FPS. |
| **GPS** (Global Positioning System) (m.) | Servicio que, mediante un conjunto de satélites, permite a los dispositivos determinar su posición geográfica. |
| **Group** (m.) | Objeto de libGDX que representa un conjunto de objetos Actor de LibGDX. |
| **Handler** (m.) | Controlador. Método que se ejecuta para tratar un evento ocurrido en el sistema. |
| **Identificador de vista** (m.) | Número con el cual Android identifica cada vista de una aplicación, y que permite al programador acceder y cambiar sus propiedades. |
| **Intent** (m.) | Las intenciones ligan los componentes individuales entre sí en tiempo de ejecución, tanto si los componentes pertenecen a la misma aplicación como si no. |
| **Interpolación** (f.) | Estimación de un valor desconocido a partir de otros valores conocidos de la misma secuencia. |
| **JDK** (Java Development Kit) | Kit de desarrollo de Java, es un producto desarrollado por Oracle dirigido a la programación en Java. |
| **Lienzo** (canvas) (m.) | Referido a la programación de gráficos, el lienzo es una representación de una región de la pantalla en la cual se puede dibujar con pinceles y formas geométricas, por analogía con un lienzo físico. |
| **Método de llamada demorada** (callback method) (m.) | Se trata de un método al cual se le pasa otro objeto para que lo llame cuando lo necesite. Habitualmente se usa cuando el otro objeto puede tardar un tiempo en completar su tarea y se desea establecer algún mecanismo para que pueda avisar a nuestra aplicación. |
| **MIME** (Multipurpose Internet Mail Extensions) | Estándar de Internet que permite extender el formato de correo electrónico para añadir contenidos no textuales. |
| **Motor de juegos** (m.) | Herramientas para el desarrollo rápido de videojuegos. |
| **Multimedia** (m.) | Sistema que permite reproducir o capturar información con diferentes medios de expresión, tales como sonido, imagen, vídeo, animación, etc. |
| **MVC** (Modelo-Vista-Controlador) | Arquitectura que separa la representación de la información de la interacción del usuario con ella. La idea principal de MVC es la reusabilidad del código y la separación de conceptos. |
| **Obturador** (shutter) (m.) | Dispositivo electromecánico a modo de cortina que solo deja pasar la luz durante un tiempo generalmente pequeño (fracciones de segundo) para que las cámaras de fotos puedan tomar las imágenes. Es el responsable del “clic” que se escucha cuando se hace una foto con una cámara. |
| **OpenGL** (Open Graphics Library) | API de gráficos multiplataforma y multilenguaje que sirve para dibujar gráficos 2D y 3D. |
| **Orthographic camera** (f.) | Cámara para representar objetos en 2D. |
| **PDU** (Protocol Description Unit) (f.) | Unidad de descripción de protocolo. Es uno de los métodos definidos por la industria para enviar y recibir mensajes SMS. |
| **Píxel** (m.) | Cada uno de los puntos que forman una imagen en la pantalla de un dispositivo digital. |
| **Polling** | Con la técnica de polling el programador consulta los dispositivos de entrada regularmente para conocer su estado. |
| **Profiler** (m.) | Programa de análisis dinámico que mide diferentes aspectos de la ejecución de un programa informático, como la cantidad de memoria que consume o la frecuencia y duración con que se llaman los métodos. |
| **Proveedor de contenidos** (m.) | Son interfaces para compartir datos entre las aplicaciones. Son adecuados para compartir conjuntos de datos entre aplicaciones. |
| **Receptor broadcast** (m.) | Componente que responde a anuncios de sistema que van dirigidos a todas las aplicaciones del dispositivo. |
| **Resolución** (f.) | Referido a imágenes o vídeo, número de píxeles de ancho y alto que posee. |
| **Shared preferences** | Sistema para almacenar una pequeña cantidad de información, como la configuración de la aplicación. |
| **Servicio** (m.) | Es un componente que se ejecuta en segundo plano (background) para realizar tareas o trabajos remotos. |
| **Smartphone** | Véase teléfono inteligente. |
| **SMS** (Short Message Service) | Servicio de mensajes cortos. |
| **Sprite** (m.) | Imagen o animación en dos dimensiones integrada en una escena más grande. |
| **Stage** (n.) | Objeto de libGDX que representa una escena en 2D. |
| **SQLite** | Sistema de bases de datos relacional sencillo y de pequeño tamaño. |
| **Teléfono inteligente** (m.) | Teléfono programable con características avanzadas como conexión a red, pantalla táctil, acelerómetros, cámara, etc. |
| **Thread** | Véase hilo de ejecución. |
| **UI** (user interface) | Interfaz de usuario. |
| **URI** (Universal Resource Identifier) | Identificador de recursos universal, es una cadena de caracteres que sirve para referenciar recursos en el sistema. |
| **Vista** (f.) | En la programación en Android, cada uno de los elementos que componen la interfaz gráfica de la aplicación, como botones, cajas de texto, listas, etc. |

No hace falta que te las aprendas ahora: cada una se explica en su sesión.

:::

## Objetivos de Aprendizaje {.animate-title}

En este módulo trabajarás competencias profesionales y para la empleabilidad que te preparan para tu futura vida laboral.

::: details Objetivos Generales del Curso

Los objetivos generales de este módulo formativo son los siguientes:

* Instalar y configurar módulos y complementos, evaluando su funcionalidad, para gestionar entornos de desarrollo.

* Seleccionar y emplear lenguajes, herramientas y librerías, interpretando las especificaciones para desarrollar aplicaciones multiplataforma con acceso a bases de datos.

* Seleccionar y utilizar herramientas específicas, lenguajes y librerías, evaluando sus posibilidades y siguiendo un manual de estilo, para manipular e integrar en aplicaciones multiplataforma contenidos gráficos y componentes multimedia.

* Emplear herramientas de desarrollo, lenguajes y componentes visuales, siguiendo las especificaciones y verificando interactividad y usabilidad, para desarrollar interfaces gráficos de usuario en aplicaciones multiplataforma.

* Seleccionar y emplear técnicas, motores y entornos de desarrollo, evaluando sus posibilidades, para participar en el desarrollo de juegos y aplicaciones en el ámbito del entretenimiento.

* Seleccionar y emplear técnicas, lenguajes y entornos de desarrollo, evaluando sus posibilidades, para desarrollar aplicaciones en teléfonos móviles, tabletas y otros dispositivos inteligentes.

* Valorar y emplear herramientas específicas, atendiendo a la estructura de los contenidos, para crear tutoriales, manuales de usuario y otros documentos asociados a una aplicación.

* Seleccionar y emplear técnicas y herramientas, evaluando la utilidad de los asistentes de instalación generados, para empaquetar aplicaciones.

* Analizar y aplicar técnicas y librerías específicas, simulando diferentes escenarios, para desarrollar aplicaciones capaces de ofrecer servicios en red.

* Analizar y aplicar técnicas y librerías de programación, evaluando su funcionalidad para desarrollar aplicaciones multiproceso y multihilo.

* Establecer procedimientos, verificando su funcionalidad, para desplegar y distribuir aplicaciones.

* Describir los roles de cada uno de los componentes del grupo de trabajo, identificando en cada caso la responsabilidad asociada, para establecer las relaciones profesionales más convenientes.

* Identificar los cambios tecnológicos, organizativos, económicos y laborales en su actividad, analizando sus implicaciones en el ámbito de trabajo, para mantener el espíritu de innovación.

:::

### Competencias a Desarrollar

El módulo desarrolla dos tipos de competencias: las **transversales**, que sirven en cualquier trabajo, y las **técnicas**, propias de la informática.

---

### Competencias Transversales

Son las que se te van a pedir en cualquier empresa, trabajes de lo que trabajes.

::: details Competencias Transversales

* **Análisis y Resolución de Problemas:** Detectar qué problema hay que resolver y **proponer una solución técnica realista, explicando por qué es esa y no otra**.
* **Trabajo en Equipo y Comunicación Profesional:** **Explicar decisiones técnicas con claridad**, por escrito y en voz alta, y saber cambiar el mensaje según hables con alguien técnico o con alguien que no lo es. Tiene su propia sesión y se practica en cada píldora.
* **Adaptación y Aprendizaje Continuo:** **Aprender herramientas nuevas por tu cuenta** —control de versiones, tableros de proyecto, metodologías ágiles— y mantenerte al día.
* **Autonomía, Iniciativa y Gestión de Proyectos:** **Planificar tus tareas**, organizar tu tiempo y resolver los imprevistos sin esperar a que alguien te lo diga.
:::

### Competencias Técnicas del Módulo

Estás en **primer curso**, y todavía no dominas las tecnologías de desarrollo. Por eso el
proyecto no te pide programar: te pide *entender, dibujar, organizar, documentar y
explicar* un proyecto informático. Son las mismas competencias para **DAW y DAM**.

::: details Competencias Técnicas

* Gestionar entornos de desarrollo adaptando su configuración en cada caso para permitir el desarrollo y despliegue de aplicaciones.

* Desarrollar aplicaciones multiplataforma con acceso a bases de datos utilizando lenguajes, librerías y herramientas adecuados a las especificaciones.

* Integrar contenidos gráficos y componentes multimedia en aplicaciones multiplataforma, empleando herramientas específicas y cumpliendo los requerimientos establecidos.

* Desarrollar interfaces gráficos de usuario interactivos y con la usabilidad adecuada, empleando componentes visuales estándar o implementando componentes visuales específicos.

* Participar en el desarrollo de juegos y aplicaciones en el ámbito del entretenimiento y la educación empleando técnicas, motores y entornos de desarrollo específicos.

* Desarrollar aplicaciones para teléfonos móviles, tabletas y otros dispositivos inteligentes empleando técnicas y entornos de desarrollo específicos.

* Crear tutoriales, manuales de usuario, de instalación, de configuración y de administración, empleando herramientas específicas.

* Empaquetar aplicaciones para su distribución preparando paquetes auto instalables con asistentes incorporados.

* Desarrollar aplicaciones multiproceso y multihilo empleando librerías y técnicas de programación específicas.

* Desarrollar aplicaciones capaces de ofrecer servicios en red empleando mecanismos de comunicación.

* Desplegar y distribuir aplicaciones en distintos ámbitos de implantación verificando su comportamiento y realizando las modificaciones necesarias.

* Establecer vidas eficaces de relación profesional y comunicación con sus superiores, compañeros y subordinados, respetando la autonomía y competencias de las distintas personas.

* Mantener el espíritu de innovación y actualización en el ámbito de su trabajo para adaptarse a los cambios tecnológicos y organizativos de su entorno profesional.

:::

### Resultados de aprendizaje

Los resultados de aprendizaje y los criterios de evaluación de este módulo son los que fijan el real decreto del título: el **RD 405/2023**.

::: details RA1 ·Aplica tecnologías de desarrollo para dispositivos móviles evaluando sus características y capacidades.

| CE | Texto literal |
| :---: | :--- |
| a | Se han analizado las limitaciones que plantea la ejecución de aplicaciones en los dispositivos móviles. |
| b | Se han identificado las tecnologías de desarrollo de aplicaciones para dispositivos móviles. |
| c | Se han instalado, configurado y utilizado entornos de trabajo para el desarrollo de aplicaciones para dispositivos móviles. |
| d | Se han identificado configuraciones que clasifican los dispositivos móviles en base a sus características. |
| e | Se han descrito perfiles que establecen la relación entre el dispositivo y la aplicación. |
| f | Se ha analizado la estructura de aplicaciones existentes para dispositivos móviles identificando las clases utilizadas. |
| g | Se han realizado modificaciones sobre aplicaciones existentes. |
| h | Se han utilizado emuladores para comprobar el funcionamiento de las aplicaciones. |

:::

::: details RA2 · Desarrolla aplicaciones para dispositivos móviles analizando y empleando las tecnologías y librerías específicas.

| CE | Texto literal |
| :---: | :--- |
| a | Se ha generado la estructura de clases necesaria para la aplicación. |
| b | Se han analizado y utilizado las clases que modelan ventanas, menús, alertas y controles para el desarrollo de aplicaciones gráficas sencillas. |
| c | Se han utilizado las clases necesarias para la conexión y comunicación con dispositivos inalámbricos. |
| d | Se han desarrollado aplicaciones que hacen uso de las funcionalidades proporcionadas por los sensores. |
| e | Se han utilizado las clases necesarias para establecer conexiones y comunicaciones HTTP y HTTPS. |
| f | Se han utilizado las clases necesarias para establecer conexiones con almacenes de datos garantizando la persistencia. |
| g | Se han realizado pruebas de interacción usuario-aplicación para optimizar las aplicaciones desarrolladas a partir de emuladores. |
| h | Se han empaquetado y desplegado las aplicaciones desarrolladas en dispositivos móviles reales. |
| i | Se han documentado los procesos necesarios para el desarrollo de las aplicaciones. |
| j | Se han establecido los permisos requeridos para el funcionamiento de las aplicaciones. |

:::

::: details RA3 · Desarrolla programas que integran contenidos multimedia analizando y empleando las tecnologías y librerías específicas.

| CE | Texto literal |
| :---: | :--- |
| a | Se han analizado entornos de desarrollo multimedia. |
| b | Se han reconocido las clases que permiten la captura, procesamiento y almacenamiento de datos multimedia. |
| c | Se han utilizado clases para la conversión de datos multimedia de un formato a otro. |
| d | Se han utilizado clases para procesar datos multimedia. |
| e | Se han utilizado clases para el control de eventos, tipos de media y excepciones, entre otros. |
| f | Se han utilizado clases para la creación y control de animaciones. |
| g | Se han utilizado clases para reproducir contenidos multimedia. |
| h | Se han depurado y documentado los programas desarrollados. |

:::

::: details RA4 ·Selecciona y prueba motores de juegos analizando la arquitectura de juegos 2D y 3D.

| CE | Texto literal |
| :---: | :--- |
| a | Se han identificado los elementos que componen la arquitectura de un juego 2D y 3D. |
| b | Se han analizado los componentes de un motor de juegos. |
| c | Se han analizado entornos de desarrollo de juegos. |
| d | Se han analizado diferentes motores de juegos, sus características y funcionalidades. |
| e | Se han identificado los bloques funcionales de un juego existente. |
| f | Se ha reconocido la representación lógica y espacial de una escena gráfica sobre un juego existente. |

:::

::: details RA5 · Desarrolla juegos 2D y 3D sencillos utilizando motores de juegos.

| CE | Texto literal |
| :---: | :--- |
| a | Se ha establecido la lógica de un nuevo juego. |
| b | Se han creado los objetos necesarios para el juego y definido sus características. |
| c | Se han creado las escenas del juego y distribuido los objetos en las mismas. |
| d | Se han creado materiales para determinar las propiedades finales de la superficie de un objeto. |
| e | Se han establecido las propiedades físicas de los objetos. |
| f | Se ha incorporado sonido a los diferentes eventos del juego. |
| g | Se han utilizado cámaras y configurado la iluminación. |
| h | Se han desarrollado e implantado juegos para dispositivos móviles. |
| i | Se han realizado pruebas de funcionamiento y optimización de los juegos desarrollados. |
| j | Se han documentado las fases de diseño y desarrollo de los juegos creados. |

:::

## Estructura del Temario {.animate-title}

### Contenidos

::: details Bloque 1: Análisis de tecnologías para aplicaciones en dispositivos móviles

- Dispositivos móviles. Evolución. Tipos. Características.
- Hardware para dispositivos móviles: pantalla, procesador, memoria, cámara, batería, sensores, conectividad, entre otros. Limitaciones.
- Tecnologías de desarrollo. Nativas y multiplataforma. Entornos de trabajo. Módulos y librerías. Lenguajes.
- Emuladores. Configuraciones. Perfiles. Dispositivos soportados.
- Aplicaciones móviles. Estructura. Jerarquía de clases.
- Modelo de estados de una aplicación móvil: activo, pausa y destruido.
- Ciclo de vida de una aplicación: descubrimiento, instalación, ejecución, actualización y borrado.
- Modificación de aplicaciones existentes.
- Utilización del entorno de ejecución del administrador de aplicaciones.

:::

::: details Bloque 2: Desarrollo de aplicaciones para dispositivos móviles

- Herramientas. Flujo de trabajo.
- Componentes de una aplicación. Recursos.
- Interfaces de usuario. Clases asociadas.
- Contexto gráfico. Imágenes.
- Métodos de entrada. Eventos.
- Gestión de las preferencias de la aplicación.
- Bases de datos y almacenamiento.
- Persistencia.
- Tareas en segundo plano. Servicios.
- Seguridad y permisos.
- Conectividad. Tipos. Clases asociadas. Gestión de las comunicaciones.
- Manejo de conexiones HTTP y HTTPS. Acceso a servicios web.
- Sensores.
- Posicionamiento. Localización. Mapas.

:::

::: details Bloque 3: Utilización de librerías multimedia integradas

- Conceptos sobre aplicaciones multimedia.
- Arquitectura del API utilizado.
- Fuentes de datos multimedia. Clases.
- Procesamiento de objetos multimedia. Clases. Estados, métodos y eventos.
- Reproducción de objetos multimedia. Clases. Estados, métodos y eventos.
- Animación de objetos.

:::

::: details Bloque 4: Análisis de motores de juegos

- Animación 2D y 3D.
- Arquitectura del juego. Componentes.
- Motores de juegos: Tipos y utilización.
- Áreas de especialización, librerías utilizadas y lenguajes de programación.
- Componentes de un motor de juegos.
- Librerías que proporcionan las funciones básicas de un Motor 2D/3D. Clases.
- Estudio de juegos existentes.
- Aplicación de modificaciones sobre juegos existentes.

:::

::: details Bloque 5: Desarrollo de juegos 2D y 3D

- Técnicas de programación 2D/3D.
- Fases de desarrollo.
- Componentes de los objetos: materiales y texturas. Propiedades físicas (peso, gravedad, fricciones, colisiones, entre otros).
- Fuentes de audio. Propiedades.
- Cámaras e iluminación.
- Creación de escenas. Jerarquía de objetos.
- Análisis de ejecución. Optimización del código.

:::

## Metodología {.animate-title}

En este módulo se usará una metodología activa y que acerque al estudiante a la realidad. Para ello las actividades que se realizarán en base a los siguientes principios metodológicos.

### Principios metodológicos:

- Partir del nivel del estudiante. Teniendo en cuenta el nivel individual en cuanto al uso de las nuevas tecnologías, los conocimientos previos sobre los contenidos del módulo, las edades y motivaciones individuales y grupales.
- Fomentar el aprendizaje significativo. Relacionando los nuevos contenidos con los que el alumnado ya domina poniendo ejemplos de casos ya vistos.
- Facilitar la memorización comprensiva. Mediante el uso de mapas conceptuales, esquemas y comparaciones, para una memorización más fácil.
- Enseñar a aprender. Se motiva al alumnado a resolver problemas por sí mismos. Esto es muy importante ya que las tecnologías cambian rápidamente.
- Desarrollar aprendizajes funcionales. Las actividades serán problemas de la vida real, principalmente, haciendo ver la utilidad de los conceptos. Se guiará hacia el buen uso de todas las fuentes de información motivándolos para que contrasten la información en diferentes fuentes antes de asumirla como correcta.
- Impulsar la participación activa. Se incentivará la participación en clase como actitud positiva durante el proceso de enseñanza-aprendizaje.
- Estimular la transferencia y las conexiones entre contenidos. Cuando sea posible se relacionarán los contenidos con los de otros módulos del mismo ciclo.

### Actividades de enseñanza-aprendizaje

Mediante las actividades se consigue poner en práctica los principios metodológicos y con su realización el alumnado adquirirá las competencias profesionales relacionadas con este módulo. Los tipos de actividades que se realizarán son:

- Actividades de presentación-motivación: se realizan para exponer al alumnado las características de las unidades de trabajo y los proyectos a realizar, para que les parezcan más atractivos y motivarles.
- Actividades de conocimientos previos: su fin es adecuar el inicio del curso a los conocimientos que ya posee el alumnado sobre la materia.
- Actividades de desarrollo de los contenidos: con ellas se explican los contenidos de cada una de las unidades.
- Actividades de documentación y exposición: con ellas los estudiantes trabajarán los contenidos ellos mismos y expondrán los resultados al resto de la clase.
- Actividades de consolidación y síntesis-resumen: mediante las cuales se asientan los contenidos importantes.
- Actividades de evaluación: gracias a estas actividades se consigue evaluar la consecución de los objetivos.
- Actividades de refuerzo: son actividades mediante las cuales aquellos estudiantes que les cueste seguir el ritmo de las clases podrán reforzar sus conocimientos y así no perder el ritmo ni desmotivarse.
- Actividades de ampliación: con estas actividades, los estudiantes más aventajados podrán profundizar más en los contenidos e incluso trabajar otros contenidos relacionados con el módulo que no se tratan en la marcha normal de las clases.

### Orientaciones didácticas

Todas las actividades que se realizarán durante el curso están diseñadas en base a las líneas de actuación que se determinan en el RD 405/2023 para este módulo:

- El análisis de las tecnologías disponibles para dispositivos móviles, sus características y funcionalidad.
- La utilización de emuladores para evaluar el funcionamiento tanto de las aplicaciones para dispositivos móviles desarrolladas como de las modificaciones introducidas en aplicaciones existentes.
- El desarrollo de aplicaciones para dispositivos móviles que garantizan la persistencia de los datos y permiten el establecimiento de conexiones el intercambio de datos.
- El desarrollo de aplicaciones que integran objetos multimedia.
- El análisis de motores de juegos, sus características y funcionalidades.
- El desarrollo de juegos 2D y 3D aplicando técnicas específicas, así como la prueba e implantación en dispositivos móviles.

## Evaluación {.animate-title}

La evaluación es **continua**. Lo que se califica son los **resultados de aprendizaje**: lo que debes ser capaz de hacer cuando acabes el módulo.

### Criterios de evaluación mínimos para superar el módulo

La nota de la evaluación se extraerá de acuerdo con la ponderación de estos criterios:

- **80% Pruebas escrita**: Apartado correspondiente a la prueba de evaluación. Asimismo, se tendrá que obtener al menos 5 puntos sobre 10 en las pruebas para que haga media.
- **20% Trabajos/actividades obligatorias en clase**: Trabajos prácticos, de busqueda, investigación, debates, trabajos en grupo, memorias. Se tendrá que obtener al menos 5 puntos sobre 10 para que haga media. Son las prácticas evaluables que habrá que realizar por cada uno de los bloques de propuestos en el Aules de la asignatura. Este apartado no podrá ser evaluado en el caso de que todas las prácticas no hayan sido entregadas.

### Procedimientos e instrumentos de evaluación

Todos los procedimientos de calificación se calificarán con una nota entre 0 y 10.

- Actividades propuestas: tienen una fecha de entrega, el retraso en la entrega se penalizará restando 2 puntos a la calificación en esa actividad. Todas las actividades tienen el mismo peso.
- Examen: se divide en dos partes que tienen el mismo peso: prueba objetiva con preguntas de respuesta múltiple y un ejercicio práctico.
- Examen ordinario: lo realizan los estudiantes que hayan suspendido alguno de los dos trimestres y los que hayan perdido la evaluación continua por acumulación de faltas. Se realiza al final del segundo trimestre, consta de dos ejercicios prácticos, uno por trimestre, con el mismo peso y se realiza en el ordenador. En el caso de presentarse a un solo trimestre se promediará la nota del examen con la nota del trimestre superado, en caso de presentarse a los dos trimestres, la suma de la nota de los dos ejercicios será la nota final del módulo. Para la realización del examen se podrá pedir que el alumnado entregue previamente las actividades no superadas durante el curso o un proyecto que las aúne.
- Examen extraordinario: se realiza en junio, consta de dos ejercicios prácticos con el mismo peso y se realizan en el ordenador. La suma de la nota de los dos ejercicios será la nota final del módulo. Para la realización del examen se podrá pedir que el alumnado entregue previamente las actividades no superadas durante el curso o un proyecto que las aúne.

En cualquier momento el profesor puede requerir que un estudiante defienda y argumente sobre cualquiera de los trabajos entregados durante el curso escolar.

### Evaluación de la programación y la práctica docente

El proceso de evaluación de la práctica docente se realiza en cada trimestre, junto con el seguimiento de las unidades didácticas que van finalizándose, de forma que el profesor va comprobando si el alumnado va adquiriendo las capacidades de contenidos estudiados previamente de forma que el alumnado reconoce y aplica adecuadamente las mejores soluciones allí donde sean necesarias.

En cuanto a los indicadores de consecución, se utilizará por un lado los resultados académicos obtenidos por los alumnos, comprobando que coincidan con los esperados. Y por otro, usar ejercicios y prácticas que necesitan demostrar capacidades que deberían estar alcanzadas, validando el proceso de enseñanza-aprendizaje aplicado

## Recursos y Herramientas {.animate-title}

Para el correcto desarrollo del módulo se necesitará un aula de informática con dotación de ciclos tal y como se detalla a continuación.

::: details Recursos de aula

- Proyector.
- Pizarra blanca y/o pizarra digital interactiva.
- Ordenadores con:
	- sistema operativo Lliurex.
	- Entorno de desarrollo Android Studio.
	- Entorno de desarrollo IntelliJ Idea.
	- Entorno de desarrollo Unity.
	- Visual Studio Code o VSCodium.
- Plataforma Aules.

:::

::: details Recursos bibliográficos y direcciones web

- Material realizado por el profesor.
- [https://kotlinlang.org/docs/home.html](https://kotlinlang.org/docs/home.html)
- [https://developer.android.com/docs?hl=es-419](https://developer.android.com/docs?hl=es-419)
- [https://docs.unity3d.com/es/530/Manual/UnityManual.html](https://docs.unity3d.com/es/530/Manual/UnityManual.html)
- [https://stackoverflow.com/](https://stackoverflow.com/)

:::
