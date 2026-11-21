"""Genera la página de la solución de referencia de la fase 1 de Explora
(src/proyecto/ejercicios/fase-1-solucion.md), su .zip de descarga y la imagen de la maqueta.

Uso, desde la raíz del repositorio: python3 soluciones/build_solution_explora.py .

El texto sale del README.md del proyecto (soluciones/proyecto/fase-1/Explora) y el código,
de sus archivos fuente. El .zip contiene la carpeta Explora completa, sin archivos del IDE.
"""
import os, re, shutil, sys, zipfile

ROOT = sys.argv[1] if len(sys.argv) > 1 else "."
SRC = os.path.join(ROOT, "soluciones", "proyecto", "fase-1", "Explora")
PAGE = os.path.join(ROOT, "src", "proyecto", "ejercicios", "fase-1-solucion.md")
DL_DIR = os.path.join(ROOT, "src", "public", "descargas", "proyecto")
IMG_DIR = os.path.join(ROOT, "src", "public", "img", "proyecto")
ZIP_NAME = "explora-fase1-solucion.zip"
IMG_NAME = "explora-fase1-maqueta.png"
SKIP_DIRS = {".gradle", ".idea", "build", ".kotlin", ".cxx"}
SKIP_FILES = {"local.properties", ".DS_Store"}
PKG = os.path.join(SRC, "app", "src", "main", "java", "es", "dam", "explora")
RES = os.path.join(SRC, "app", "src", "main", "res")

os.makedirs(DL_DIR, exist_ok=True)
os.makedirs(IMG_DIR, exist_ok=True)

# --- .zip con la carpeta Explora ---
with zipfile.ZipFile(os.path.join(DL_DIR, ZIP_NAME), "w", zipfile.ZIP_DEFLATED) as z:
    for r, ds, fs in os.walk(SRC):
        ds[:] = sorted(d for d in ds if d not in SKIP_DIRS)
        for f in sorted(fs):
            if f in SKIP_FILES:
                continue
            p = os.path.join(r, f)
            arc = os.path.join("Explora", os.path.relpath(p, SRC)).replace(os.sep, "/")
            info = zipfile.ZipInfo.from_file(p, arc)
            if f == "gradlew":
                info.external_attr = 0o100755 << 16  # ejecutable en Linux y macOS
            with open(p, "rb") as fh:
                z.writestr(info, fh.read(), zipfile.ZIP_DEFLATED)

shutil.copyfile(os.path.join(SRC, "docs", "maqueta-pantalla-principal.png"), os.path.join(IMG_DIR, IMG_NAME))

# --- Texto a partir del README ---
readme = open(os.path.join(SRC, "README.md"), encoding="utf-8").read()

def section(title):
    m = re.search(rf"^## {re.escape(title)}\s*$", readme, re.M)
    rest = readme[m.end():]
    n = re.search(r"^## ", rest, re.M)
    return (rest[:n.start()] if n else rest).strip()

def block(rel, lang, label=None):
    code = open(os.path.join(SRC, rel), encoding="utf-8").read().rstrip("\n")
    return f"```{lang} [{label or os.path.basename(rel)}]\n{code}\n```"

def rel(*parts):
    return os.path.join(*parts)

J = rel("app", "src", "main", "java", "es", "dam", "explora")
R = rel("app", "src", "main", "res")

lines = [
    "---", "layout: doc", 'title: "Fase 1 · Solución de referencia"', "sidebar: true",
    "outline: [2, 3]", "aside: true", "---", "",
    "<script setup>", "import { withBase } from 'vitepress'", "</script>", "",
    "# ✅ Fase 1 · Solución de referencia",
    "",
    "Solución de referencia de la **entrega de la 1.ª evaluación** del proyecto *Explora*: la pantalla principal "
    "que se construye en el vídeo 2 de la UP03, adaptada a seis lugares del patrimonio de la Comunitat Valenciana, "
    "con los recursos, idiomas y pruebas de los vídeos 1 y 3. Cubre lo que se puede hacer con las unidades 1 a 3; "
    "el resto de la [fase 1](./fase-1) se completa con las unidades siguientes.",
    "",
    "Hay muchas soluciones correctas: úsala para **comparar** con tu proyecto, no para copiarla. "
    "Tu temática, tus datos y tus decisiones tienen que ser tuyos.",
    "",
    "::: tip-box Descarga",
    f"Proyecto completo: <a :href=\"withBase('/descargas/proyecto/{ZIP_NAME}')\" download>{ZIP_NAME}</a>. "
    "Descomprímelo y, en Android Studio, elige **File → Open** y la carpeta `Explora`.",
    ":::",
    "",
    f'<img :src="withBase(\'/img/proyecto/{IMG_NAME}\')" alt="Maqueta aproximada de la pantalla principal de Explora" style="max-width: 360px; width: 100%;" />',
    "",
    "*Maqueta aproximada de la pantalla principal, no una captura del emulador.*",
    "",
    "## Qué hace la app", "", section("Qué hace la app"), "",
    "## Cómo abrirlo", "", section("Cómo abrirlo"), "",
    "## Estructura", "", section("Estructura"), "",
    "## Correspondencia con la entrega", "", section("Correspondencia con la entrega"), "",
    "## Ejemplo de propuesta y análisis", "", section("Ejemplo de propuesta y análisis"), "",
    "## Plan de pruebas", "", section("Plan de pruebas"), "",
    "## Limitaciones conocidas", "", section("Limitaciones conocidas"), "",
    "## Código", "",
    "### Actividad y modelo", "",
    "::: code-group",
    block(rel(J, "MainActivity.kt"), "kotlin"),
    block(rel(J, "data", "Modelo.kt"), "kotlin", "data/Modelo.kt"),
    block(rel(J, "data", "DatosEjemplo.kt"), "kotlin", "data/DatosEjemplo.kt"),
    ":::", "",
    "### Interfaz", "",
    "::: code-group",
    block(rel(J, "ui", "PantallaLugares.kt"), "kotlin", "ui/PantallaLugares.kt"),
    block(rel(J, "ui", "TarjetaLugar.kt"), "kotlin", "ui/TarjetaLugar.kt"),
    block(rel(J, "ui", "theme", "Color.kt"), "kotlin", "ui/theme/Color.kt"),
    block(rel(J, "ui", "theme", "Theme.kt"), "kotlin", "ui/theme/Theme.kt"),
    ":::", "",
    "### Recursos e idiomas", "",
    "::: code-group",
    block(rel(R, "values", "strings.xml"), "xml", "values/strings.xml"),
    block(rel(R, "values-en", "strings.xml"), "xml", "values-en/strings.xml"),
    block(rel(R, "values-ca", "strings.xml"), "xml", "values-ca/strings.xml"),
    block(rel(R, "resources.properties"), "properties", "resources.properties"),
    ":::", "",
    "### Configuración e icono", "",
    "::: code-group",
    block(rel("app", "build.gradle.kts"), "kotlin", "app/build.gradle.kts"),
    block(rel("gradle", "libs.versions.toml"), "toml", "gradle/libs.versions.toml"),
    block(rel("app", "src", "main", "AndroidManifest.xml"), "xml", "AndroidManifest.xml"),
    block(rel(R, "mipmap-anydpi", "ic_launcher.xml"), "xml", "mipmap-anydpi/ic_launcher.xml"),
    block(rel(R, "drawable", "ic_launcher_foreground.xml"), "xml", "drawable/ic_launcher_foreground.xml"),
    ":::", "",
    "## Créditos", "", section("Créditos"), "",
]

open(PAGE, "w", encoding="utf-8", newline="\n").write("\n".join(lines).rstrip() + "\n")
print("ok", PAGE, ZIP_NAME, IMG_NAME)
