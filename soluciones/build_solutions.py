"""Genera las páginas de soluciones de la UP02 (src/up02/ejercicios/solucion-N.md) y los zip de
descarga (src/public/descargas/up02/) a partir de soluciones/up02.

Uso, desde la raíz del repositorio: python3 soluciones/build_solutions.py .
"""
import os, re, sys, zipfile

ROOT = sys.argv[1]
SOL = os.path.join(ROOT, "soluciones", "up02")
OUT = os.path.join(ROOT, "src", "up02", "ejercicios")
DL = os.path.join(ROOT, "src", "public", "descargas", "up02")
os.makedirs(DL, exist_ok=True)

TITLES = {1: "Variables, constantes y control de flujo", 2: "Colecciones", 3: "Cadenas",
          4: "Map", 5: "Funciones: agenda"}

boletin = open(os.path.join(OUT, "index.md"), encoding="utf-8").read()
readme = open(os.path.join(SOL, "README.md"), encoding="utf-8").read()

def section(text, start_pat, level):
    m = re.search(start_pat, text, re.M)
    if not m: return ""
    rest = text[m.end():]
    n = re.search(r"^#{1,%d} " % level, rest, re.M)
    return (rest[:n.start()] if n else rest).strip()

# Orden de los proyectos y enunciado de cada uno, tomados del boletín
statements = {}
for a in range(1, 6):
    block = section(boletin, r"^## Actividad %d\b.*$" % a, 2)
    rows = re.findall(r"^\| `(\w+)` \| (.+?) \|\s*$", block, re.M)
    statements[a] = rows

common = section(readme, r"^## Criterios comunes a todas las soluciones$", 2)

def zip_activity(a):
    name = f"soluciones-up02-actividad-{a}.zip"
    base = os.path.join(SOL, f"actividad-{a}")
    with zipfile.ZipFile(os.path.join(DL, name), "w", zipfile.ZIP_DEFLATED) as z:
        for r, _, fs in os.walk(base):
            for f in sorted(fs):
                p = os.path.join(r, f)
                z.write(p, os.path.relpath(p, base))
    return name

def code_block(path):
    code = open(path, encoding="utf-8").read().rstrip("\n")
    return "```kotlin\n" + code + "\n```"

for a in range(1, 6):
    zname = zip_activity(a)
    lines = [
        "---", "layout: doc", f"title: \"Solución Actividad {a} — {TITLES[a]}\"", "sidebar: true",
        "outline: [2, 3]", "aside: true", "---", "",
        "<script setup>", "import { withBase } from 'vitepress'", "</script>", "",
        f"# ✅ Solución Actividad {a} — {TITLES[a]}", "",
        f"Soluciones de referencia de la [Actividad {a}](./#actividad-{a}) del boletín. "
        "Hay muchas soluciones correctas: úsalas para **comparar** con la tuya después de intentarlo, no para copiarlas.", "",
        "::: tip-box Descarga",
        f"Proyectos de esta actividad: <a :href=\"withBase('/descargas/up02/{zname}')\" download>{zname}</a>. "
        "Cada carpeta contiene el `src/` de un proyecto: crea en IntelliJ IDEA un proyecto Kotlin con ese nombre "
        "(*Build system: IntelliJ*) y sustituye su carpeta `src/` por la descargada.",
        ":::", "",
        "::: warning-box Ejecútalas en IntelliJ IDEA",
        "Estos programas leen datos con `readln()`. **Kotlin Playground no admite entrada por teclado**, así que allí fallan con "
        "`ReadAfterEOFException: EOF has already been reached`. En la consola de ejecución de IntelliJ funcionan sin cambios.",
        ":::", "",
    ]
    if a == 1:
        lines += ["## Criterios comunes", "", common, ""]
    notes = section(readme, r"^### Actividad %d\b.*$" % a, 3)
    if a == 5:
        agenda = open(os.path.join(SOL, "actividad-5", "Agenda", "README.md"), encoding="utf-8").read()
        agenda = agenda.split("\n", 1)[1].strip()                 # quita el título
        agenda = re.sub(r"^## ", "## ", agenda, flags=re.M)
        lines += [agenda, "", "## Código", ""]
        src = os.path.join(SOL, "actividad-5", "Agenda", "src")
        for f in ["Main.kt", "Contact.kt", "Input.kt", "Agenda.kt"]:
            lines += [f"### `{f}`", "", code_block(os.path.join(src, f)), ""]
    else:
        if notes:
            lines += ["## Claves de la solución", "", notes, ""]
        lines += ["## Proyectos", ""]
        rows = statements[a] or []
        if a == 4:
            rows = [("Productos", "Colección de productos en la que cada elemento es un `Map` (nombre, precio y stock).")]
        for project, statement in rows:
            path = os.path.join(SOL, f"actividad-{a}", project, "src", "Main.kt")
            if not os.path.exists(path):
                sys.exit(f"Falta la solución de {project}")
            lines += [f"::: details {project}", f"**Enunciado:** {statement}", "", code_block(path), ":::", ""]
    out = os.path.join(OUT, f"solucion-{a}.md")
    open(out, "w", encoding="utf-8", newline="\n").write("\n".join(lines).rstrip() + "\n")
    print("ok", out, len(rows) if a != 5 else 4)
