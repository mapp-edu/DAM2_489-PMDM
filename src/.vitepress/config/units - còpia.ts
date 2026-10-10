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
// 1. NAVBARS Y SIDEBARS
// ============================================================================
// Define aquí los ítems de navegación de cada unidad.
// Puedes definirlos inline dentro de UNITS o como constantes separadas
// (recomendado cuando el listado es largo).

// --- Navbar de la unidad principal ---
const baseNavbar: DefaultTheme.NavItem[] = [
  { text: '🏠 Inicio', link: '/' },
  {
    text: '📚 Unidades',
    items: [
      { text: 'UP01: Introducción a la programación móvil', link: '/contenidos/01-UP01' },
      { text: 'UP02: Fundamentos de Kotlin', link: '/contenidos/02-UP02' },
	  { text: 'UP03: Introducción a Android', link: '/contenidos/03-UP03' },
      { text: 'UP04: Componentes Jetpack Compose', link: '/contenidos/04-UP04' },
      { text: 'UP05: Temas y navegación con Jetpack Compose', link: '/contenidos/05-UP05' },
	  { text: 'UP06: Corrutinas, Side Effects y MVVM', link: '/contenidos/06-UP06' },
      { text: 'UP07: Persistencia - Firebase', link: '/contenidos/07-UP07' },
	  { text: 'UP08: API\'s', link: '/contenidos/08-UP08' },
      { text: 'UP09: Desarrollo de videojuegos', link: '/contenidos/09-UP09' },
      { text: 'UP10: Compilación y distribución de aplicaciones móviles y videojuegos', link: '/contenidos/10-UP10' },
    ]
  },
]

// --- Sidebar de la unidad principal ---
const baseSidebar: DefaultTheme.SidebarItem[] = [
  {
    text: '📚 Contenidos',
    collapsed: false,
    items: [
      { text: 'UP01: Introducción a la programación móvil', link: '/contenidos/01-UP01' },
      { text: 'UP02: Fundamentos de Kotlin', link: '/contenidos/02-UP02' },
	  { text: 'UP03: Introducción a Android', link: '/contenidos/03-UP03' },
      { text: 'UP04: Componentes Jetpack Compose', link: '/contenidos/04-UP04' },
      { text: 'UP05: Temas y navegación con Jetpack Compose', link: '/contenidos/05-UP05' },
	  { text: 'UP06: Corrutinas, Side Effects y MVVM', link: '/contenidos/06-UP06' },
      { text: 'UP07: Persistencia - Firebase', link: '/contenidos/07-UP07' },
	  { text: 'UP08: API\'s', link: '/contenidos/08-UP08' },
      { text: 'UP09: Desarrollo de videojuegos', link: '/contenidos/09-UP09' },
      { text: 'UP10: Compilación y distribución de aplicaciones móviles y videojuegos', link: '/contenidos/10-UP10' },
    ]
  },
  /* {
    text: '🛠️ Configura tu curso',
    collapsed: false,
    items: [
      { text: 'Curso único', link: '/contenidos/4-curso-unico' },
      { text: 'Curso modular', link: '/contenidos/5-curso-modular' },
      { text: 'Configuración e Identidad', link: '/contenidos/6-configuracion' },
    ]
  },
  {
    text: '✍️ Crea contenidos',
    collapsed: false,
    items: [
      { text: 'Crear Contenidos', link: '/contenidos/7-crear-contenidos' },
      { text: 'Catálogo de Componentes', link: '/contenidos/8-componentes' },
    ]
  },
  {
    text: '🎞️ Diapositivas e Imágenes',
    collapsed: true,
    items: [
      { text: 'Crear Diapositivas', link: '/contenidos/9-diapositivas' },
      { text: 'Catálogo de Patrones', link: '/contenidos/10-diapositivas-demo' },
      { text: 'Imágenes para Contenido', link: '/contenidos/11-imagenes' },
    ]
  },
  {
    text: '🌐 Publicación',
    collapsed: true,
    items: [
      { text: 'Despliegue', link: '/contenidos/12-despliegue' },
      { text: 'Privacidad y Acceso', link: '/contenidos/14-privacidad-acceso' },
    ]
  },
  {
    text: '📖Ejemplos',
    collapsed: true,
    items: [
      { text: 'Guia del curso', link: '/contenidos/13-ejemplo-guia' },
    ]
  }, */
  {
    text: '🗂️ Ejercicios',
    collapsed: true,
    items: [
      { text: 'Primera App Android', link: '/ejercicios/CodelabPrimeraAppAndroid' }
    ]
  }
]

// --- Sidebar de la Unidad 1 ---
const sidebarUP01: DefaultTheme.SidebarItem[] = [
  {
    text: '📚 UP01 — Introducción a la programación móvil',
    collapsed: false,
    items: [
      { text: 'Presentación',    link: '/contenidos/1-presentacion' },
      { text: 'Conceptos clave', link: '/contenidos/2-conceptos' },
      { text: 'Ejercicio 1',     link: '/contenidos/3-ejercicio' },
    ]
  },
]

// --- Sidebar de la Unidad 2 ---
const sidebarUP02: DefaultTheme.SidebarItem[] = [
  {
    text: '📚 UP02 — Fundamentos de Kotlin',
    collapsed: false,
    items: [
      { text: 'Introducción',    link: '/contenidos/1-intro' },
      { text: 'Práctica guiada', link: '/contenidos/2-practica' },
    ]
  },
]


// ============================================================================
// 2. REGISTRO DE UNIDADES
// ============================================================================
// Añade, elimina o modifica entradas para gestionar los módulos del curso.
//
// Propiedades de cada unidad:
//   id        — Identificador único (debe coincidir con la clave del objeto)
//   code      — Prefijo de URL. Los links del navbar/sidebar se prefijarán
//               automáticamente con /<code>/
//               Ejemplo: code='s1' → '/contenidos/intro' → '/s1/contenidos/intro'
//   title     — Nombre corto (usado en migas de pan y referencias internas)
//   fullTitle — Nombre completo (aparece en la pestaña del navegador)
//   siteTitle — Nombre en el sidebar (puede usar </br> para saltos de línea)
//   icon      — Emoji decorativo del módulo
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

  // Página de inicio — sin navegación de curso. No suele necesitar cambios.
  root: {
    id: 'root',
    code: 'root',
    title: 'PMDM',
    fullTitle: 'PMDM',
    siteTitle: 'PMDM',
    icon: '📐',
    navbar: [{ text: '🏠 Inicio', link: '/' }],
    sidebar: baseSidebar
  },

  // ──────────────────────────────────────────────────────────────────────────
  // Módulo / Unidad principal del curso.
  // Personaliza: title, fullTitle, siteTitle, y los ítems de navbar/sidebar.
  // ──────────────────────────────────────────────────────────────────────────
  MODULO: {
    id: 'MODULO',
    code: 'general',    // → archivos en src/general/
    title: 'Programación Multimedia y Dispositivos Móviles',
    fullTitle: 'Modulo Profesional - Programación Multimedia y Dispositivos Móviles',
    siteTitle: 'Programación Multimedia</br>Dispositivos Móviles',
    icon: '📘',
    navbar: baseNavbar,
    sidebar: baseSidebar
  }
  
  /* UP01: {
    id: 'UP01',
    code: 'UP01',           // → archivos en src/mi-curso/
    title: 'UP01 — Introducción a la programación móvil',
    fullTitle: 'UP01 — Introducción a la programación móvil',
    siteTitle: 'UP01 — Introducción</br>programación móvil',
    icon: '📘',
    navbar: baseNavbar,
    sidebar: sidebarUP01
  },

  UD2: {
    id: 'UP02',
    code: 'UP02',        // → archivos en src/mi-modulo-2/
    title: 'UP02 — Fundamentos de Kotlin',
    fullTitle: 'UP02 — Fundamentos de Kotlin',
    siteTitle: 'UP02 — Fundamentos</br>Kotlin',
    icon: '⚙️',
    navbar: baseNavbar,
    sidebar: sidebarUP02
  }, */

  // Para añadir más unidades, consulta la guía completa con ejemplos en:
  // src/general/contenidos/4-modulos.md

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

// ── Navbar dinámico por unidad (opcional) ────────────────────────────────
// Si el curso tiene múltiples sesiones/unidades, define aquí qué dropdown
// mostrar en el navbar según la URL activa.
// Ejemplo:
// export const unitNavbars: Record<string, NavGroup[]> = {
//   'general': [{ text: '📚 Módulos', items: [{ text: 'General', link: '/general/' }] }],
// }
export const unitNavbars: Record<string, NavGroup[]> = {}
