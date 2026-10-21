/**
 * Proyecto **Primos** — UP02 · Actividad 1.
 *
 * Pide números e indica si son primos; el programa termina al introducir el cero.
 *
 * @author Solucionario PMDM
 * @version 1.0
 */

/** Punto de entrada: comprueba números hasta que se introduce el 0. */
fun main() {
    var number = readInt("Introduce un número (0 para salir): ")
    while (number != 0) {
        println(if (isPrime(number)) "$number es primo." else "$number no es primo.")
        number = readInt("Introduce un número (0 para salir): ")
    }
    println("Fin del programa.")
}

/**
 * Indica si un número es primo. Solo se prueban divisores hasta la raíz cuadrada,
 * porque si n = a·b, uno de los dos factores es como mucho √n.
 *
 * @param n número que se comprueba.
 * @return true si n es primo (los números menores que 2 no lo son).
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    var divisor = 2
    while (divisor.toLong() * divisor <= n) {
        if (n % divisor == 0) return false
        divisor++
    }
    return true
}

/**
 * Muestra [prompt] y lee un número entero, repitiendo la petición hasta que la entrada sea válida.
 *
 * @param prompt mensaje que se muestra al usuario.
 * @return el número entero introducido.
 */
fun readInt(prompt: String): Int {
    while (true) {
        print(prompt)
        readln().trim().toIntOrNull()?.let { return it }
        println("Error: debes introducir un número entero.")
    }
}
