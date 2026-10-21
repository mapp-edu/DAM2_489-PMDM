/**
 * Proyecto **TablasMultiplicar** — UP02 · Actividad 1.
 *
 * Muestra las tablas de multiplicar del 1 al 10.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra las diez tablas de multiplicar. */
fun main() {
    for (table in 1..10) {
        println("--- Tabla del $table ---")
        for (i in 1..10) {
            println("$table x $i = ${table * i}")
        }
    }
}
