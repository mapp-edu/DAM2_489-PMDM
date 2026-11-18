"""Genera las páginas de soluciones de la UP03 (src/up03/ejercicios/solucion-N.md) y los zip de
descarga (src/public/descargas/up03/) a partir de los proyectos de soluciones/up03.

Uso, desde la raíz del repositorio: python3 soluciones/build_solutions_up03.py .

Los .zip contienen la carpeta completa del proyecto de Android Studio, sin los archivos que genera
el propio IDE (.gradle, .idea, build, local.properties) ni copias comprimidas.
"""
import os, re, sys, zipfile

ROOT = sys.argv[1] if len(sys.argv) > 1 else "."
SOL = os.path.join(ROOT, "soluciones", "up03")
OUT = os.path.join(ROOT, "src", "up03", "ejercicios")
DL = os.path.join(ROOT, "src", "public", "descargas", "up03")
os.makedirs(DL, exist_ok=True)

# actividad: (título, proyecto)
ACTIVITIES = {
    0: ("Contador de clics", "ClickCounterTuNombre"),
    1: ("Estadísticas", "StatisticsTuNombre"),
    2: ("Dice Game", "DiceGameTuNombre"),
    3: ("Basket 3x3", "Basket3x3TuNombre"),
}
SKIP_DIRS = {".gradle", ".idea", "build", ".kotlin", ".cxx"}
SKIP_FILES = {"local.properties", ".DS_Store"}

readme = open(os.path.join(SOL, "README.md"), encoding="utf-8").read()


def section(text, start_pat):
    """Contenido de una sección de nivel 2 del README (hasta el siguiente título de nivel 1-2)."""
    m = re.search(start_pat, text, re.M)
    if not m:
        return ""
    rest = text[m.end():]
    n = re.search(r"^#{1,2} ", rest, re.M)
    return (rest[:n.start()] if n else rest).strip()


common = section(readme, r"^## Decisiones comunes$")
checks = section(readme, r"^## Comprobaciones para corregir.*$")


def checks_for(a):
    rows = [l for l in checks.splitlines() if re.match(r"^\| %d \|" % a, l)]
    if not rows:
        return ""
    table = ["| Prueba | Resultado esperado |", "|:---|:---|"]
    table += ["| " + " | ".join(c.strip() for c in r.strip("|").split("|")[1:]) + " |" for r in rows]
    return "\n".join(table)


def project_files(base):
    for r, ds, fs in os.walk(base):
        ds[:] = sorted(d for d in ds if d not in SKIP_DIRS)
        for f in sorted(fs):
            if f in SKIP_FILES or f.endswith(".zip"):
                continue
            yield os.path.join(r, f)


def zip_activity(a, project):
    name = f"soluciones-up03-actividad-{a}.zip"
    parent = os.path.join(SOL, f"actividad-{a}")
    with zipfile.ZipFile(os.path.join(DL, name), "w", zipfile.ZIP_DEFLATED) as z:
        for p in project_files(os.path.join(parent, project)):
            z.write(p, os.path.relpath(p, parent).replace(os.sep, "/"))
    return name


def block(path, lang, label):
    code = open(path, encoding="utf-8").read().rstrip("\n")
    return f"```{lang} [{label}]\n{code}\n```"


for a, (title, project) in ACTIVITIES.items():
    zname = zip_activity(a, project)
    app = os.path.join(SOL, f"actividad-{a}", project, "app")
    main = os.path.join(app, "src", "main")
    java = os.path.join(main, "java")
    pkg_dir = next(r for r, _, fs in os.walk(java) if "MainActivity.kt" in fs)
    pkg = os.path.relpath(pkg_dir, java).replace(os.sep, ".")

    lines = [
        "---", "layout: doc", f"title: \"Solución Actividad {a} — {title}\"", "sidebar: true",
        "outline: [2, 3]", "aside: true", "---", "",
        "<script setup>", "import { withBase } from 'vitepress'", "</script>", "",
        f"# ✅ Solución Actividad {a} — {title}", "",
        f"Solución de referencia de la [Actividad {a}](./#actividad-{a}) del boletín. "
        "Hay muchas soluciones correctas: úsala para **comparar** con la tuya después de intentarlo, no para copiarla.", "",
        "::: tip-box Descarga",
        f"Proyecto completo: <a :href=\"withBase('/descargas/up03/{zname}')\" download>{zname}</a>. "
        f"Descomprímelo y, en Android Studio, elige **File → Open** y selecciona la carpeta `{project}`. "
        "Al sincronizar, Android Studio descarga Gradle; si tu versión es más reciente, acepta la "
        "actualización que propone el *AGP Upgrade Assistant*.",
        ":::", "",
        f"Paquete: `{pkg}` · SDK mínimo: API 28.", "",
    ]
    if a == 0:
        lines += ["## Decisiones comunes", "", "Se aplican a las cuatro soluciones de la unidad.", "", common, ""]
    notes = section(readme, r"^## Actividad %d\b.*$" % a)
    lines += ["## Claves de la solución", "", notes, ""]
    table = checks_for(a)
    if table:
        lines += ["## Comprueba tu versión", "", table, ""]

    lines += ["## Código", ""]
    kt = [os.path.join(pkg_dir, "MainActivity.kt")]
    for sub in ("model", "ui"):
        d = os.path.join(pkg_dir, sub)
        if os.path.isdir(d):
            kt += [os.path.join(d, f) for f in sorted(os.listdir(d)) if f.endswith(".kt")]
    for path in kt:
        rel = os.path.relpath(path, pkg_dir).replace(os.sep, "/")
        lines += [f"### `{rel}`", "", block(path, "kotlin", rel), ""]

    tests = [p for p in project_files(os.path.join(app, "src", "test")) if p.endswith(".kt")]
    if tests:
        lines += ["## Tests unitarios", "",
                  "La lógica del paquete `model` no depende de Android, así que se prueba con JUnit sin emulador: "
                  "clic derecho sobre la carpeta `test` → **Run 'Tests in ...'**.", ""]
        for path in tests:
            name = os.path.basename(path)
            lines += [block(path, "kotlin", name), ""]

    lines += ["## Recursos", "", "::: code-group"]
    res = os.path.join(main, "res")
    for rel in ["values/strings.xml", "values-es/strings.xml", "values-ca/strings.xml",
                "values/dimens.xml", "values/colors.xml"]:
        path = os.path.join(res, rel)
        if os.path.exists(path):
            lines.append(block(path, "xml", rel))
    lines += [":::", ""]

    if a == 3:
        lines += ["## Manifiesto e icono", "", "::: code-group",
                  block(os.path.join(main, "AndroidManifest.xml"), "xml", "AndroidManifest.xml")]
        for rel in ["drawable/ic_launcher_foreground.xml", "drawable/ic_launcher_background.xml"]:
            lines.append(block(os.path.join(res, rel), "xml", rel))
        lines += [":::", ""]

    out = os.path.join(OUT, f"solucion-{a}.md")
    open(out, "w", encoding="utf-8", newline="\n").write("\n".join(lines).rstrip() + "\n")
    print("ok", out, zname)
