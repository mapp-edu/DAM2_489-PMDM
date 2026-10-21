/**
 * Proyecto **DiezCuadrados** — UP02 · Actividad 1.
 *
 * Muestra los cuadrados de los diez primeros números naturales.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: muestra n² para n de 1 a 10. */
fun main() {
    for (n in 1..10) {
        println("$n² = ${n * n}")
    }
}
