/**
 * Proyecto **SumaParesImpares** — UP02 · Actividad 1.
 *
 * Recorre los 100 primeros números naturales y muestra, por separado, la suma de los pares y
 * la de los impares.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: suma pares e impares del 1 al 100. */
fun main() {
    var evenSum = 0
    var oddSum = 0
    for (n in 1..100) {
        if (n % 2 == 0) evenSum += n else oddSum += n
    }
    println("Suma de los pares: $evenSum")
    println("Suma de los impares: $oddSum")
}
