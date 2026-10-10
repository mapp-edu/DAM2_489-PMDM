---
layout: doc
title: 5. Desarrollo de juegos 3D
sidebar: true
outline: [2, 3]
aside: true
---

<ExportPDF />

# 5. Desarrollo de juegos 3D

Crea el proyecto con la plantilla **Universal 3D**. En este apartado verás cómo dar aspecto a los objetos (materiales y texturas), cómo hacer que se comporten de forma física, cómo iluminarlos, cómo filmarlos con cámaras y cómo añadir sonido.

## 5.1. Objetos 3D y creación de escenas

### Mallas

Un objeto 3D visible tiene una **malla** (*mesh*: vértices, aristas y caras triangulares) en un `MeshFilter` y un `MeshRenderer` que la dibuja con uno o varios **materiales**.

- **Primitivas** (*GameObject → 3D Object*): cubo, esfera, cápsula, cilindro, plano, *quad*. Perfectas para prototipos (***greyboxing***: construir el nivel con cajas grises antes de tener el arte final).
- **Modelos importados**: FBX creados en Blender u otro programa, o descargados con licencia.
- **ProBuilder** (paquete oficial): modelado sencillo de niveles dentro del editor.
- **Terrain**: terrenos con relieve, texturas pintadas, árboles y hierba.

### Organizar la escena

```
Nivel3D
├── --- ENTORNO ---
│   ├── Directional Light
│   ├── Global Volume        (posprocesado)
│   ├── Terrain
│   └── Props                (prefabs de rocas, árboles…)
├── --- JUEGO ---
│   ├── GameManager
│   ├── Player               (Rigidbody/CharacterController, collider, scripts)
│   │   └── Model            (malla y Animator)
│   ├── Enemies
│   └── Pickups
├── --- CÁMARAS ---
│   ├── Main Camera          (CinemachineBrain, AudioListener)
│   └── CM ThirdPerson       (CinemachineCamera)
└── Canvas
```

::: tip-box Escala real
Respeta la escala **1 unidad = 1 metro**: una persona mide unos 1,8 unidades y una puerta unos 2. Si los objetos son gigantes o diminutos, la gravedad y las colisiones parecerán «raras».
:::

## 5.2. Materiales y texturas

Un **material** define **cómo se ve la superficie** de un objeto: color, brillo, rugosidad, relieve… Se basa en un ***shader***, el programa de la GPU que calcula el color de cada píxel.

Crea uno con *Assets → Create → Material* y arrástralo sobre el objeto. En URP, el shader estándar es **Universal Render Pipeline/Lit**, que usa **renderizado basado en la física** (PBR): los materiales reaccionan a la luz como en la realidad.

### Propiedades del material Lit

| Propiedad | Mapa de textura | Efecto |
|:---|:---|:---|
| **Base Map** | Albedo / color | Color base de la superficie, sin luces ni sombras. |
| **Metallic** | Metallic map | `0` = no metálico (plástico, madera); `1` = metal. |
| **Smoothness** | En el canal alfa del mapa metálico | `0` = rugoso y mate; `1` = pulido, con reflejos nítidos. |
| **Normal Map** | Normal map (azulado) | Simula **relieve** (grietas, ladrillos) sin añadir polígonos. |
| **Occlusion** | Ambient occlusion | Oscurece rincones y huecos. |
| **Emission** | Emission map | La superficie **emite luz** propia (neones, pantallas, lava). |
| **Tiling / Offset** | — | Repite y desplaza la textura (suelo de baldosas). |
| **Surface Type** | — | *Opaque* u ***Transparent*** (cristal, agua). Lo transparente es más costoso. |

Otros shaders útiles de URP: **Simple Lit** (más barato, para móvil), **Unlit** (sin iluminación, estilo dibujo) y **Particles**. Con **Shader Graph** puedes crear shaders propios de forma visual (agua, disolución, contornos).

### Texturas

- Para que los mapas se interpreten bien: los **Normal Map** se importan con *Texture Type = Normal map*; el resto, como *Default*.
- Usa tamaños **potencia de dos** (512, 1024, 2048) y limita el **Max Size** en móvil.
- Formatos de compresión en Android: **ASTC** (recomendado) o ETC2.
- Los ***mipmaps*** generan versiones reducidas de la textura para objetos lejanos: mejoran el aspecto y el rendimiento.

::: info-box Texturas PBR gratuitas
Sitios como [ambientCG](https://ambientcg.com/) o [Poly Haven](https://polyhaven.com/) ofrecen texturas y modelos PBR con licencia **CC0**.
:::

## 5.3. Propiedades físicas

La física 3D de Unity usa **NVIDIA PhysX**. Para que un objeto participe en ella necesita un **Rigidbody** y un **Collider**.

### Rigidbody

| Propiedad | Significado |
|:---|:---|
| **Mass** | Masa en kg. Influye en los choques y en las fuerzas necesarias para moverlo. **No** hace caer más rápido (como en la realidad, sin rozamiento todos caen igual). |
| **Linear Damping** | Resistencia al movimiento (rozamiento con el aire). |
| **Angular Damping** | Resistencia a la rotación. |
| **Use Gravity** | Si le afecta la gravedad. |
| **Is Kinematic** | Se mueve solo por código o animación; no le afectan fuerzas. |
| **Interpolate** | Suaviza el movimiento visual entre pasos de física (actívalo en el jugador). |
| **Collision Detection** | *Discrete* o *Continuous* (para objetos rápidos que podrían atravesar paredes). |
| **Constraints** | Bloquea movimiento o rotación en algún eje. |

La **gravedad** global se ajusta en *Project Settings → Physics* (por defecto `(0, -9.81, 0)` m/s²).

### Colliders 3D

| Collider | Uso | Coste |
|:---|:---|:---|
| `BoxCollider`, `SphereCollider`, `CapsuleCollider` | Formas aproximadas (la mayoría de objetos y personajes). | Bajo |
| `MeshCollider` | Forma exacta de la malla. Para que colisione con otros `MeshCollider` dinámicos debe ser *Convex*. | Alto |
| `WheelCollider` | Ruedas de vehículos. | — |
| `CharacterController` | Personajes que se mueven por código con colisiones, sin Rigidbody. | — |

### Physics Material: fricción y rebote

Crea un *asset* **Physics Material** y asígnalo al collider:

| Propiedad | Efecto |
|:---|:---|
| **Dynamic Friction** | Rozamiento cuando el objeto **ya se desliza** (hielo ≈ 0,05; goma ≈ 0,8). |
| **Static Friction** | Rozamiento para **empezar** a moverse. |
| **Bounciness** | Rebote (0-1). |
| **Friction / Bounce Combine** | Cómo se combinan los valores de los dos objetos que chocan (*Average*, *Minimum*, *Maximum*, *Multiply*). |

### Fuerzas y rayos

```csharp [Ball.cs]
using UnityEngine;

[RequireComponent(typeof(Rigidbody))]
public class Ball : MonoBehaviour
{
    [SerializeField] private float kickForce = 8f;
    private Rigidbody rb;

    void Awake() => rb = GetComponent<Rigidbody>();

    public void Kick(Vector3 direction)
    {
        // Impulso instantáneo, que depende de la masa
        rb.AddForce(direction.normalized * kickForce, ForceMode.Impulse);
    }
}
```

| `ForceMode` | Tipo |
|:---|:---|
| `Force` | Fuerza continua (usa la masa). Para empujar durante varios pasos. |
| `Impulse` | Golpe instantáneo (usa la masa). Saltos, explosiones. |
| `Acceleration` / `VelocityChange` | Igual que los anteriores, pero ignorando la masa. |

Los **raycasts** lanzan un rayo invisible y devuelven lo que tocan: disparos, detección del suelo, selección con el dedo.

```csharp
if (Physics.Raycast(transform.position, Vector3.down, out RaycastHit hit, 1.1f))
{
    Debug.Log("Pisando: " + hit.collider.name);
}
```

Las **articulaciones** (*Joints*: `HingeJoint` para puertas, `SpringJoint` para muelles, `FixedJoint`…) unen Rigidbodies entre sí.

## 5.4. Cámaras

| Propiedad | Uso |
|:---|:---|
| **Projection** | *Perspective* (3D) u *Orthographic*. |
| **Field of View** | Ángulo de visión vertical (60° es habitual; más amplio = más sensación de velocidad). |
| **Clipping Planes** | Distancia mínima y máxima que se dibuja. Reducir *Far* mejora el rendimiento. |
| **Culling Mask** | Qué capas ve la cámara. |
| **Viewport Rect** | Porción de pantalla (pantalla partida, minimapa). |

### Tipos de cámara en juegos

| Tipo | Ejemplo | Implementación en Unity |
|:---|:---|:---|
| **Primera persona** | *Shooter* | Cámara hija de la cabeza del jugador. |
| **Tercera persona** | Aventura | `CinemachineCamera` con *Orbital Follow* o *Third Person Follow* y *Rotation Composer*. |
| **Cenital / isométrica** | Estrategia, *twin-stick* | Cámara alta y fija con *Follow*. |
| **Fija / cinemática** | Escenas de vídeo | Varias `CinemachineCamera` con **prioridades**: la de mayor prioridad toma el control con una transición suave. |

Con **Cinemachine** también puedes añadir **sacudidas** de cámara (*Impulse*) en impactos y explosiones.

## 5.5. Iluminación

### Tipos de luz

| Luz | Comportamiento | Uso |
|:---|:---|:---|
| **Directional** | Rayos paralelos desde el infinito; su posición no importa, solo la rotación. | Sol, luna. |
| **Point** | Emite en todas direcciones desde un punto, con un alcance (*Range*). | Bombillas, antorchas. |
| **Spot** | Cono de luz con ángulo. | Linternas, focos. |
| **Area** | Rectángulo o disco emisor. **Solo en modo *baked***. | Ventanas, paneles. |

Propiedades clave: **Color**, **Intensity**, **Range**, **Shadow Type** (*No Shadows*, *Hard*, *Soft*).

### Modos de iluminación

| Modo | Cómo funciona | Ventajas | Inconvenientes |
|:---|:---|:---|:---|
| **Realtime** | Se calcula cada fotograma. | Luces que se mueven o cambian. | Costoso, sobre todo con sombras. |
| **Baked** | Se precalcula y se guarda en texturas (***lightmaps***). | Muy barato en ejecución; sombras suaves y luz rebotada. | Solo para objetos y luces **estáticos**. |
| **Mixed** | Combina ambos. | Sombras dinámicas sobre un entorno precalculado. | Configuración más compleja. |

Para hornear la luz: marca los objetos inmóviles como **Static** (casilla arriba a la derecha del Inspector), abre *Window → Rendering → Lighting* y pulsa **Generate Lighting**. Los objetos que se mueven reciben la luz precalculada mediante ***Light Probes***.

### Entorno y posprocesado

- **Skybox** y **luz ambiental**: en la ventana *Lighting → Environment*.
- **Reflection Probes**: capturan el entorno para los reflejos de materiales lisos.
- **Posprocesado**: un **Volume** con efectos como *Bloom* (resplandor), *Color Adjustments*, *Vignette* o *Tonemapping*. Úsalo con moderación en móvil.

::: tip-box Iluminación para móvil
En móvil: una única luz **Directional** en tiempo real (o *Mixed*), el resto **horneadas**, sombras de baja resolución y pocos efectos de posprocesado.
:::

## 5.6. Fuentes de audio

### Componentes

| Componente | Función |
|:---|:---|
| **AudioClip** | El archivo de sonido importado. |
| **AudioSource** | Emisor: reproduce clips en un punto de la escena. |
| **AudioListener** | Receptor: normalmente en la cámara. **Uno por escena**. |
| **Audio Mixer** | Mesa de mezclas: grupos, efectos y volúmenes. |

### Propiedades del AudioSource

| Propiedad | Efecto |
|:---|:---|
| **Volume** / **Pitch** | Volumen y tono (velocidad). Variar ligeramente el *pitch* evita que los efectos repetidos suenen monótonos. |
| **Loop** / **Play On Awake** | Repetición y reproducción automática al empezar. |
| **Spatial Blend** | `0` = 2D (igual en todas partes: música, UI); `1` = **3D** (depende de la posición respecto al *listener*). |
| **Min / Max Distance** | Distancia a la que el sonido está al máximo y a la que deja de atenuarse. |
| **Volume Rolloff** | Curva de atenuación con la distancia (*Logarithmic*, *Linear*, *Custom*). |
| **Doppler Level** | Cambio de tono con la velocidad relativa (sirenas, coches). |
| **Output** | Grupo del Audio Mixer al que se envía. |

### Importación del audio

| Tipo de sonido | *Load Type* | Formato |
|:---|:---|:---|
| Efectos cortos y frecuentes | *Decompress On Load* | ADPCM o PCM |
| Música y ambientes largos | *Streaming* | Vorbis comprimido |
| Efectos medianos | *Compressed In Memory* | Vorbis |

### Sonido asociado a eventos del juego

```csharp [SoundOnEvents.cs]
using UnityEngine;

[RequireComponent(typeof(AudioSource))]
public class SoundOnEvents : MonoBehaviour
{
    [SerializeField] private AudioClip hitClip;
    [SerializeField] private AudioClip[] footstepClips;
    private AudioSource source;

    void Awake() => source = GetComponent<AudioSource>();

    // Sonido de impacto proporcional a la fuerza del choque
    void OnCollisionEnter(Collision collision)
    {
        float strength = Mathf.Clamp01(collision.relativeVelocity.magnitude / 10f);
        source.pitch = Random.Range(0.9f, 1.1f);
        source.PlayOneShot(hitClip, strength);
    }

    // Llamado desde un Animation Event en los fotogramas en que el pie toca el suelo
    public void Footstep()
    {
        var clip = footstepClips[Random.Range(0, footstepClips.Length)];
        source.PlayOneShot(clip);
    }
}
```

::: info-box Animation Events
En la ventana *Animation* puedes añadir **eventos** en un fotograma concreto de una animación que llaman a un método público del script (como `Footstep()`): así los pasos suenan sincronizados con la animación.
:::

Con el **Audio Mixer** crea los grupos `Music` y `SFX`, expón su volumen (*Expose parameter*) y cámbialo desde un menú de opciones con `audioMixer.SetFloat("MusicVolume", dB)`. Los **Snapshots** permiten cambiar toda la mezcla de golpe (por ejemplo, sonido amortiguado al pausar).

## 5.7. Animación 3D e IA básica

- Los modelos FBX con esqueleto se configuran en la pestaña **Rig** como *Humanoid* (permite reutilizar animaciones entre personajes) o *Generic*.
- En el **Animator** se usan **Blend Trees** para mezclar animaciones según un parámetro (andar ↔ correr según `Speed`).
- Para que los enemigos **naveguen** por el escenario evitando obstáculos se usa el paquete **AI Navigation**: se hornea un ***NavMesh*** con un `NavMeshSurface` y cada enemigo lleva un `NavMeshAgent`:

```csharp
using UnityEngine;
using UnityEngine.AI;

public class Chaser : MonoBehaviour
{
    [SerializeField] private Transform target;
    private NavMeshAgent agent;

    void Awake() => agent = GetComponent<NavMeshAgent>();
    void Update() => agent.SetDestination(target.position);
}
```
