// ============================================================================
// CONFIGURACIÓN DE UNIDADES — Navegación y Contenidos
// ============================================================================
//
// 👤 EDITA ESTE ARCHIVO para definir los módulos/unidades del curso
//    y su navegación (navbar y sidebar).
//
// CONCEPTOS CLAVE:
//   Unidad (unit) — Un bloque de contenido con su propia navegación.
//                   Puede ser una sesión, un tema, un capítulo, etc.
//   Navbar        — Menú horizontal en la barra superior.
//   Sidebar       — Panel de navegación lateral izquierdo.
//   code          — Prefijo de URL de los archivos de esta unidad.
//                   Ejemplo: code = 'general' → los links apuntan a /general/...
//
// FLUJO RÁPIDO:
//   1. Crea los archivos .md en src/<code>/contenidos/ y src/<code>/ejercicios/
//   2. Añade los links aquí (en navbar y sidebar)
//   3. El sistema detecta todas las unidades automáticamente:
//        /<code>/ → carga el sidebar de esa unidad
//        /        → raíz sin sidebar (página de inicio)
//
// ============================================================================

import { DefaultTheme } from 'vitepress'
import type { NavGroup } from '../shared/navigation'

// ============================================================================
// 1. DEFINICIÓN DE LAS UNIDADES
// ============================================================================
// Cada unidad declara sus páginas de contenidos y de actividades. A partir de
// esta lista se generan automáticamente el sidebar, la entrada del menú
// «Unidades» y el desplegable «Esta unidad».
//   - Los links son relativos a la unidad: '/contenidos/01-xxx' → '/upNN/contenidos/01-xxx'

type Page = [text: string, link: string]

interface UnitDef {
  code: string
  title: string
  icon: string
  contenidos: Page[]
  actividades: Page[]
}

const UNIT_DEFS: UnitDef[] = [
  {
    code: 'up01', icon: '📱', title: 'Introducción a la programación móvil',
    contenidos: [
      ['1. Dispositivos móviles', '/contenidos/01-dispositivos-moviles'],
      ['2. Tipos de aplicaciones', '/contenidos/02-tipos-aplicaciones'],
      ['3. Android', '/contenidos/03-android'],
      ['4. Entorno de desarrollo', '/contenidos/04-entorno-desarrollo'],
      ['5. Generación de archivos .apk', '/contenidos/05-generacion-apk'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
      ['A0 · Repaso de programación', '/ejercicios/00-repaso-programacion'],
      ['A1 · Instalación de Android Studio', '/ejercicios/01-instalacion-android-studio'],
      ['A2 · Hello there!', '/ejercicios/02-hello-there'],
      ['A3 · Usando el emulador', '/ejercicios/03-emulador'],
      ['A4 · Dispositivos reales', '/ejercicios/04-dispositivo-real'],
      ['Codelab: primera app', '/ejercicios/CodelabPrimeraAppAndroid'],
    ],
  },
  {
    code: 'up02', icon: '🟣', title: 'Fundamentos de Kotlin',
    contenidos: [
      ['1. Sintaxis, tipos y null safety', '/contenidos/01-sintaxis-tipos'],
      ['2. Control de flujo, colecciones y funciones', '/contenidos/02-control-colecciones-funciones'],
      ['3. Programación orientada a objetos', '/contenidos/03-poo'],
      ['4. Funciones de alcance y lambdas', '/contenidos/04-funciones-alcance-lambdas'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
      ['Solución A1 · Control de flujo', '/ejercicios/solucion-1'],
      ['Solución A2 · Colecciones', '/ejercicios/solucion-2'],
      ['Solución A3 · Cadenas', '/ejercicios/solucion-3'],
      ['Solución A4 · Map', '/ejercicios/solucion-4'],
      ['Solución A5 · Agenda', '/ejercicios/solucion-5'],
    ],
  },
  {
    code: 'up03', icon: '🤖', title: 'Introducción a Android',
    contenidos: [
      ['1. Activities, Compose y proyecto', '/contenidos/01-introduccion-android'],
      ['2. Primera app con Jetpack Compose', '/contenidos/02-primera-app-compose'],
      ['3. Recursos, idiomas y depuración', '/contenidos/03-recursos-idiomas'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
    ],
  },
  {
    code: 'up04', icon: '🧱', title: 'Componentes Jetpack Compose',
    contenidos: [
      ['1. Modifier y componentes básicos', '/contenidos/01-modifier-componentes-basicos'],
      ['2. Estados y entrada de datos', '/contenidos/02-estados-entrada-datos'],
      ['3. Layouts, listas y Scaffold', '/contenidos/03-layouts-listas'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
    ],
  },
  {
    code: 'up05', icon: '🧭', title: 'Navegación y temas',
    contenidos: [
      ['1. Navegación con Navigation 3', '/contenidos/01-navegacion'],
      ['2. Diseño adaptativo, Splash y Onboarding', '/contenidos/02-adaptativo-splash-onboarding'],
      ['3. Temas de Material 3', '/contenidos/03-temas'],
      ['4. Animaciones', '/contenidos/04-animaciones'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
    ],
  },
  {
    code: 'up06', icon: '⚙️', title: 'Corrutinas, side effects y MVVM',
    contenidos: [
      ['1. Corrutinas y Side Effects', '/contenidos/01-corrutinas-side-effects'],
      ['2. MVVM y StateFlow', '/contenidos/02-mvvm-stateflow'],
      ['3. Caso práctico: libros', '/contenidos/03-caso-practico-libros'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
    ],
  },
  {
    code: 'up07', icon: '💾', title: 'Persistencia',
    contenidos: [
      ['1. DataStore', '/contenidos/01-datastore'],
      ['2. Room: entidades y DAO', '/contenidos/02-room-entidades-dao'],
      ['3. Room: ViewModel e interfaz', '/contenidos/03-room-viewmodel-interfaz'],
      ['4. Sistema de archivos', '/contenidos/04-sistema-archivos'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
    ],
  },
  {
    code: 'up08', icon: '🌐', title: 'Consumo de APIs',
    contenidos: [
      ['1. Servicios web y API REST', '/contenidos/01-servicios-web-api-rest'],
      ['2. Retrofit', '/contenidos/02-retrofit'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
    ],
  },
  {
    code: 'up09', icon: '🔥', title: 'Firebase',
    contenidos: [
      ['1. Integración, Auth y notificaciones', '/contenidos/01-firebase-auth-notificaciones'],
      ['2. Crashlytics, Remote Config y BD', '/contenidos/02-crashlytics-config-bd'],
    ],
    actividades: [
      ['Boletín y rúbrica', '/ejercicios/'],
    ],
  },
  {
    code: 'up10', icon: '🎮', title: 'Videojuegos 2D y 3D con Unity',
    contenidos: [
      ['1. Arquitectura de un juego y motores', '/contenidos/01-arquitectura-motores'],
      ['2. Unity 6, escenas y GameObjects', '/contenidos/02-unity-escena'],
      ['3. Programación en C# con Unity', '/contenidos/03-scripting-csharp'],
      ['4. Desarrollo de juegos 2D', '/contenidos/04-juegos-2d'],
      ['5. Desarrollo de juegos 3D', '/contenidos/05-juegos-3d'],
      ['6. Fases, optimización y Android', '/contenidos/06-fases-optimizacion-android'],
      ['7. Fuentes de consulta', '/contenidos/07-fuentes'],
    ],
    actividades: [
      ['Plan de evaluación', '/ejercicios/'],
      ['P1 · Análisis de motores', '/ejercicios/01-analisis-motores'],
      ['P2 · Modificar un juego', '/ejercicios/02-modificar-juego'],
      ['P3 · Plataformas 2D', '/ejercicios/03-juego-2d'],
      ['P4 · Escena 3D', '/ejercicios/04-escena-3d'],
      ['PF · Juego para Android', '/ejercicios/05-proyecto-final'],
    ],
  },
]

const unitLabel = (u: UnitDef) => `${u.code.toUpperCase()} — ${u.title}`

function buildSidebar(u: UnitDef): DefaultTheme.SidebarItem[] {
  const toItems = (pages: Page[]) => pages.map(([text, link]) => ({ text, link }))
  // Los links se prefijan con /<code> al registrar la unidad: '/' → portada de la unidad.
  const sidebar: DefaultTheme.SidebarItem[] = [
    { text: `${u.icon} ${unitLabel(u)}`, collapsed: false, items: [{ text: '🏠 Presentación', link: '/' }] },
  ]
  if (u.contenidos.length) sidebar.push({ text: '📚 Contenidos', collapsed: false, items: toItems(u.contenidos) })
  if (u.actividades.length) sidebar.push({ text: '🛠️ Actividades', collapsed: false, items: toItems(u.actividades) })
  return sidebar
}

// ============================================================================
// 2. REGISTRO DE UNIDADES
// ============================================================================
// Propiedades de cada unidad:
//   id        — Identificador único
//   code      — Prefijo de URL. Los links del sidebar se prefijan con /<code>/
//   title     — Nombre corto (menú superior)
//   fullTitle — Nombre completo (pestaña del navegador)
//   siteTitle — Nombre en la cabecera (admite </br>)
//   icon      — Emoji decorativo
//   navbar    — Ítems del menú superior de esta unidad
//   sidebar   — Ítems del panel lateral de esta unidad

export interface UnitConfig {
  id: string
  code: string
  title: string
  fullTitle: string
  siteTitle: string
  icon: string
  navbar: DefaultTheme.NavItem[]
  sidebar: DefaultTheme.SidebarItem[]
}

export const UNITS: Record<string, UnitConfig> = {

  // Guía didáctica (src/index.md) — sin sidebar de curso.
  root: {
    id: 'root',
    code: 'root',
    title: 'PMDM',
    fullTitle: 'Módulo Profesional - Programación Multimedia y Dispositivos Móviles',
    siteTitle: 'Programación Multimedia</br>y Dispositivos Móviles',
    icon: '📐',
    navbar: [{ text: '🏠 Guía Didáctica', link: '/' }],
    sidebar: []
  },

  ...Object.fromEntries(UNIT_DEFS.map(u => [u.code.toUpperCase(), {
    id: u.code,
    code: u.code,
    title: u.code.toUpperCase(),
    fullTitle: unitLabel(u),
    siteTitle: 'Programación Multimedia</br>y Dispositivos Móviles',
    icon: u.icon,
    navbar: [],
    sidebar: buildSidebar(u),
  } satisfies UnitConfig])),
}

// ============================================================================
// FUNCIONES DE ACCESO — No modificar
// ============================================================================

export function getAllUnitsArray(): UnitConfig[] {
  return Object.values(UNITS)
}

export function getUnitByCode(code: string): UnitConfig | undefined {
  return UNITS[code]
}

// ── Menú «📚 Unidades» del navbar global ─────────────────────────────────
export const unitsMenu: DefaultTheme.NavItem = {
  text: '📚 Unidades',
  items: UNIT_DEFS.map(u => ({ text: `${u.icon} ${unitLabel(u)}`, link: `/${u.code}/` })),
}

// ── Navbar dinámico por unidad ───────────────────────────────────────────
// Desplegable «Esta unidad» que aparece en la barra superior solo cuando
// estás dentro de una unidad.
export const unitNavbars: Record<string, NavGroup[]> = Object.fromEntries(
  UNIT_DEFS.map(u => [u.code, [{
    text: `${u.icon} ${u.code.toUpperCase()}`,
    items: [
      { text: 'Presentación', link: `/${u.code}/` },
      ...(u.contenidos[0] ? [{ text: 'Contenidos', link: `/${u.code}${u.contenidos[0][1]}` }] : []),
      ...(u.actividades[0] ? [{ text: 'Actividades', link: `/${u.code}${u.actividades[0][1]}` }] : []),
    ],
  }]])
)
