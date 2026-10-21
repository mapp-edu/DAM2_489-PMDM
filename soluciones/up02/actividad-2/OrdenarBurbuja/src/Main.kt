/**
 * Proyecto **OrdenarBurbuja** — UP02 · Actividad 2.
 *
 * Rellena un array con 10 enteros aleatorios entre 0 y 100, lo muestra, lo ordena con el
 * método de la burbuja y lo vuelve a mostrar.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Tamaño del array. */
const val SIZE = 10

/** Punto de entrada. */
fun main() {
    val numbers = IntArray(SIZE) { (0..100).random() }
    println("Antes:   ${numbers.joinToString(" ")}")
    bubbleSort(numbers)
    println("Después: ${numbers.joinToString(" ")}")
}

/**
 * Ordena el array de menor a mayor con el método de la burbuja. En cada pasada el mayor
 * elemento pendiente «sube» hasta el final; si en una pasada no hay intercambios, el array
 * ya está ordenado y se termina antes.
 *
 * @param array array que se ordena (se modifica in situ).
 */
fun bubbleSort(array: IntArray) {
    for (pass in 0 until array.size - 1) {
        var swapped = false
        for (j in 0 until array.size - 1 - pass) {
            if (array[j] > array[j + 1]) {
                array[j] = array[j + 1].also { array[j + 1] = array[j] }
                swapped = true
            }
        }
        if (!swapped) break
    }
}
