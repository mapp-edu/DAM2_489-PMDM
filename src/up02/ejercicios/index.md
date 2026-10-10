---
layout: doc
title: Actividades UP02
sidebar: true
outline: [2, 3]
aside: false
---

# 🛠️ Actividades UP02 — Fundamentos de Kotlin

Realiza **cada ejercicio en un proyecto Kotlin de IntelliJ IDEA** con el nombre indicado. Recuerda las normas del curso: identificadores en inglés, comentario KDoc con `@author` y `@version` en cada archivo y KDoc en cada clase y función.

## Actividad 1 — Variables, constantes y control de flujo {#actividad-1}

| Proyecto | Funcionalidad |
|:---|:---|
| `Votar` | Programa que pida la edad al usuario y que le indique si puede votar o no. |
| `MayorDeDos` | Programa que pida dos números decimales al usuario e indique cuál es el mayor de ellos. |
| `RangoNumerico` | Programa que pida al usuario un número entero entre 1 y 20 y que indique si se ha introducido un número dentro de los válidos o no. |
| `Intercambio` | Programa que pida al usuario dos números enteros, a continuación, intercambiará el valor de las variables y, por último, mostrará el valor de las variables. |
| `GrandeDeTres` | Programa que pida tres números decimales al usuario y los muestre cuál es el más grande. |
| `TresAscendente` | Programa que pida tres números decimales al usuario y los muestre en orden ascendente. |
| `Operaciones` | Programa que pida dos números decimales al usuario y que calcule la suma, resta, producto y división de dichos números. Se debe tener cuidado de no dividir entre cero. |
| `NumeroALetras` | Programa que pida un número entero entre 1 y 5 al usuario y muestre por pantalla dicho número en letras, si se introduce un número no válido se mostrará el mensaje “Número no válido”. |
| `OperacionesMenu` | Programa que pida dos números decimales al usuario, a continuación, se mostrará un menú con siguientes opciones: 1-sumar 2-restar 3-multiplicar 4-dividir 0-salir Se debe pedir al usuario que introduzca una opción y se realizará la operación indicada. Se debe tener cuidado de no dividir entre cero. En caso de introducir la opción cero en el menú no se realizará ninguna operación. Por último, el programa se despedirá del usuario. |
| `ParImpar` | Programa que pida un número entero e indique si es par o impar. |
| `MesALetra` | Programa que pida al usuario la fecha actual pidiendo el número de día, el número de mes y el número de año. El programa mostrará un mensaje como el siguiente: "7 de diciembre de 2019" |
| `VocalConsonante` | Programa que pida al usuario una letra y diga si es una vocal o una consonante. Se debe realizar con la instrucción `when`. |
| `Division` | Programa que pida dos números enteros al usuario y muestre la división del mayor entre el menor. Si el menor es cero no debe realizarse la división. |
| `Bisiesto` | Programa que pida un año al usuario e indique si ese año es bisiesto o no. Un año bisiesto es aquel que es divisible por 4 es bisiesto y no debe ser divisible entre 100. También es bisiesto si un año es divisible entre 100 y además es divisible entre 400. |

| Proyecto | Funcionalidad |
|:---|:---|
| `QuieroPar` | Programa que pida un número hasta que dicho número sea par. Si el número no es par se indicará antes de volver a pedir otro número. Finalmente se mostrará un mensaje indicando que el número introducido es par. |
| `IntroduceMes` | Programa que pida al usuario un número de mes, si el número no está entre 1 y 12 incluidos se volverá a pedir. Finalmente, con el número introducido se mostrará el mes en letras. |
| `AcumulandoDiez` | Programa que pida 10 números al usuario y finalmente muestre la suma de todos los números introducidos. |
| `Primos` | Programa que pida números e indique si son primos o no, el programa parará cuando se introduzca el número cero. |
| `DiezCuadrados` | Programa que muestre los cuadrados de los primeros diez números naturales. |
| `TablaMultiplicar` | Programa que pida un número entero entre 1 y 10 y mostrará la tabla de multiplicar de dicho número. |
| `AcumulandoN` | Programa que pida un número al usuario (N), a continuación, pedirá N números y finalmente mostrará la suma de esos N números. |
| `CienAscendente` | Programa que muestre los primeros 100 números naturales en orden ascendente. |
| `CienDescendente` | Programa que muestre los primeros 100 números naturales en orden descendente. |
| `DeTresEnTres` | Programa que muestre números de tres en tres desde el uno hasta el 100. |
| `Factorial` | Programa que pida un número entero al usuario y calcule y muestre su factorial. |
| `TablasMultiplicar` | Programa que muestre las tablas de multiplicar de los números 1 a 10 incluidos. |
| `MayorMenorMedia` | Programa que pida números hasta que se introduzca el cero. Al final el programa indicará el mayor y el menor de los introducidos y la media de todos los números. |
| `MayoresCero` | Programa que pida 10 números al usuario y finalmente muestre cuántos son mayores que cero. |
| `NumerosRango` | Programa que pida dos números enteros y muestre los números que hay entre esos dos introducidos, incluidos esos dos números. |
| `SumaParesImpares` | Programa que recorra los 100 primeros números naturales y que muestre la suma de los que sean pares por un lado y la suma de los que sean impares por otro |

## Actividad 2 — Colecciones {#actividad-2}

| Proyecto | Funcionalidad |
|:---|:---|
| `MayorMenor` | Programa que pida 10 números enteros y los almacene en un array, a continuación, el programa indicará qué números son el mayor y el menor. Por último, mostrará el array por pantalla. |
| `ArrayCifras` | Programa que pida 10 números enteros y los almacene en un array, a continuación, el programa dirá cuántos números hay de una cifra, cuántos hay de dos y cuántos hay de más de dos. |
| `Array10` | Programa que pida 10 números enteros y los almacene en un array, a continuación, los mostrará por pantalla en el orden en el que se han introducido, a continuación, dirá cuántos números pares e impares hay y, por último, mostrará todos los números en orden inverso al que se han introducido. |
| `ArrayCapicua` | Programa que pida 6 números enteros y los almacene en un array, a continuación, los mostrará por pantalla en el orden en el que se han introducido, e indicará si el array es capicúa. |
| `ArrayPosicion` | Programa que rellene automáticamente un array con 100 decimales aleatorios entre 0 y 1000 incluidos. A continuación, se pedirá una posición al usuario y se mostrará por pantalla el elemento que se encuentra en dicha posición del array. |
| `ArrayPersonalizado` | Programa que pida un número entre 3 y 8 al usuario, a continuación, creará un array de caracteres del tamaño introducido y pedirá al usuario las letras para rellenarlo. Por último, mostrará el array por pantalla. |
| `Aleatorios50` | Programa que rellene automáticamente un array con 50 enteros aleatorios entre 1 y 20 incluidos. A continuación, se pedirá un número al usuario y se indicará si el número está en el array. Por último, se mostrará el array por pantalla. |
| `Aleatorios50Plus` | Modificar el ejercicio anterior para que muestre cuántas veces aparece el número buscado. |
| `AleatoriosUnicos` | Programa que rellene automáticamente un array con 10 enteros aleatorios entre 1 y 50 incluidos, no se debe repetir ningún número. |
| `ArrayPosiciones` | Programa que rellene automáticamente un array con 100 números enteros aleatorios entre 0 y 1000. A continuación, pedirá dos posiciones al usuario y mostrará todos los números que hay en el array entre esas posiciones. |
| `OrdenarBurbuja` | Programa que rellene automáticamente un array con 10 números enteros aleatorios entre 0 y 100. A continuación, mostrará el array por pantalla, luego lo ordenará mediante el método de la burbuja (visto en el módulo de Programación) y finalmente volverá a mostrar el array por pantalla. |
| `Matriz3x3` | Programa que rellene automáticamente una matriz de 3x3 con enteros aleatorios entre 0 y 9 incluidos. A continuación, mostrará la matriz. |
| `MatrizTamanyos` | Programa que pida un número entero entre 2 y 5 al usuario y cree una matriz de NxN siendo N el número introducido por el usuario. A continuación, pedirá números enteros al usuario para rellenar la matriz y por último se mostrará la matriz. |
| `Matriz4x4Aleatoria` | Programa que rellene automáticamente una matriz de 4x4 con enteros aleatorios entre 0 y 9 incluidos. A continuación, mostrará la matriz y la media de cada una de las filas. |
| `MatrizLoca` | Programa que pida dos números al usuario (N y M) y que cree una matriz de NxM que rellenará automáticamente con números aleatorios entre 1 y 9. Al finalizar mostrará la matriz por pantalla. |
| `Diagonal3x3` | Programa que rellene una matriz de 3x3 con enteros aleatorios entre 1 y 100 incluidos. A continuación, mostrará la matriz, luego su diagonal y por último la traspuesta de la matriz. |
| `ArrayYMatriz` | Programa que rellene automáticamente un array con 3 números aleatorios entre 1 y 5, también rellenará automáticamente una matriz de 10x3 con números aleatorios entre 1 y 5. Por último, mostrará si alguna fila es igual al array. |
| `Matriz5x5` | Programa con una matriz de 5x5 que muestre un menú y realice la acción elegida (ver detalle más abajo). |
| `Matriz5x5Plus` | Modificar el ejercicio anterior para realizar las acciones desde funciones. |

::: details Detalle de `Matriz5x5`
Programa que, usando una matriz de 5x5, muestre un menú y realice la acción elegida. Después de cada acción volverá a mostrar el menú, y el programa solo acabará al elegir la opción de salir.

```txt
1.- Rellenar matriz (números aleatorios entre 1 y 50)
2.- Mostrar (muestra la matriz por pantalla)
3.- Total fila (pide el número al usuario hasta que exista)
4.- Total columna (pide el número al usuario hasta que exista)
5.- Buscar número (pide número e indica si está o no)
6.- Media (muestra la media de los números de la matriz)
7.- Traspuesta (muestra la traspuesta de la matriz)
0.- Salir
```

- Si la matriz aún no se ha rellenado, las opciones 2 a 7 deben mostrar un mensaje indicando que está vacía.
- Si se introduce una opción no válida, se mostrará un error antes de volver a mostrar el menú.
- Si se cambia el tamaño de la matriz, no debe hacer falta cambiar nada más en el código.
:::

## Actividad 3 — Cadenas {#actividad-3}

| Proyecto | Funcionalidad |
|:---|:---|
| `Saludo` | Programa que pida al usuario su nombre y apellidos en variables diferentes y salude al usuario con esos datos. |
| `DosNombres` | Programa que pida al usuario dos nombres e indique si son iguales o no. Si son diferentes mostrará la longitud de cada uno. |
| `EmpiezaAcaba` | Programa que pida al usuario una frase e indique si empiezan y acaban con la misma letra. Usa startsWith y endsWith. |
| `LetraNombre` | Programa que pida al usuario su nombre completo y lo almacene en una cadena. A continuación, mostrará la longitud del nombre y pedirá al usuario una posición (máximo la longitud) e indicará la letra que se encuentra en esa posición. |
| `PalabraE` | Programa que pida al usuario una palabra que empiece por E y de longitud mínima 5. El programa acabará cuando el usuario introduzca una palabra válida. |
| `Palabras` | Programa que pida al usuario una frase e indique cuántas palabras tiene. |
| `Digrafos` | Programa que pida al usuario una frase e indique si aparece o no cada uno de los dígrafos: ch, rr, ll, gu o qu. |
| `DatosPersonales` | Programa que pida al usuario los siguientes datos y los almacene en variables, presta atención al tipo de variable necesario para cada caso. Datos a pedir: nombre, apellidos, país de nacimiento, día de nacimiento, mes de nacimiento, año de nacimiento. |
| `Password` | Programa que pida el nombre de usuario, también se pedirá la contraseña un máximo de tres veces, si la contraseña introducida coincide con "2D4m" se mostrará un mensaje de bienvenida. En caso de introducir una contraseña incorrecta se mostrará un mensaje indicándolo. Si se introducen 3 contraseñas incorrectas se mostrará un mensaje como el siguiente "Contacte con el administrador para reiniciar su contraseña". |
| `CantidadA` | Programa que pida una frase y muestre cuántas 'a' contiene. |
| `SinVocales` | Programa que pida una frase y la muestre sin vocales. |
| `CantidadVocales` | Programa que pida una frase y muestre la cantidad de cada vocal y el total de vocales. |
| `ListaNombres` | Programa que pida 10 nombres al usuario y los almacene en un array de cadenas. |
| `ListaNombresPlus` | Modifica el programa anterior para que compruebe si algún nombre se repite en el array. |

## Actividad 4 — Map {#actividad-4}

En un proyecto Kotlin de IntelliJ desarrolla un programa que almacene los datos de productos usando una **colección en la que cada elemento sea un `Map`**.

1. El programa pedirá los datos de un producto: **nombre, precio y stock**, y lo almacenará como `Map` en la colección.
2. Después preguntará si se quiere almacenar otro producto o finalizar. Si se continúa, se repite el proceso.
3. Al finalizar, mostrará todos los productos con sus datos y, a continuación, **el producto más barato** y los productos con **stock menor que 3**.

## Actividad 5 — Funciones: agenda {#actividad-5}

Programa una pequeña **agenda** que almacene los datos de tus contactos: **nombre, apellidos, edad, teléfono y correo electrónico**.

- Nada más ejecutarse, pedirá el nombre al usuario y mostrará un menú.
- Pedirá una opción y realizará su funcionalidad; si la opción no existe, volverá a mostrar el menú.
- Tras cada acción volverá a mostrar el menú. El programa solo acabará con la opción de salir.

| Opción | Funcionalidad |
|:---|:---|
| **1.** Mostrar agenda | Mostrará todos los contactos de la agenda. Si no hay ningún contacto indicará que la agenda está vacía |
| **2.** Guardar contacto | Se pedirán los datos de un contacto y si no existe un contacto con esos datos se almacenarán en la agenda. |
| **3.** Buscar contacto | Pedirá el nombre de un contacto y se mostrarán por pantalla todos los contactos junto con sus datos que coincidan con ese nombre. |
| **4.** Borrar contacto | Pedirá el nombre de un contacto y si existe lo eliminará de la agenda. Si hay más de un contacto en la agenda con ese nombre se mostrarán todos y se preguntará al usuario cuál quiere eliminar. |
| **5.** Borrar Agenda | Eliminará todos los contactos de la agenda. |
| **0.** Salir | Finalizará el programa |

Las opciones 3, 4 y 5 no se deben realizar si la agenda está vacía.

::: tip-box Pistas
- Organiza el código en **funciones** con una única responsabilidad.
- Modela los contactos con una **`data class`** (apartado 3 de la unidad) y valida el correo y el teléfono con **expresiones regulares**.
:::

## Entrega

Entrega en el aula virtual un archivo `.zip` (o el enlace a un repositorio) con:

1. Una carpeta por actividad con los proyectos de IntelliJ (sin las carpetas `out/`, `build/` ni `.idea/`).
2. Un documento breve (`README.md` o PDF) que explique, para la Actividad 5, cómo has organizado el código (funciones, clases y colecciones) y **por qué**, e incluya capturas de una ejecución de cada opción del menú.

## Rúbrica de evaluación

| Criterio | 1 · Incompleto | 2 · Correcto | 3 · Justificado | 4 · Coherente y comunicado |
|:---|:---|:---|:---|:---|
| **RA1.c** Utiliza el entorno de trabajo<br>*Proyectos de IntelliJ* | Faltan proyectos o no compilan | Todos los proyectos se crean con el nombre indicado y se ejecutan | Usa el depurador y el formateo del IDE y lo refleja en el documento | Proyectos limpios, uniformes y fáciles de ejecutar por otra persona |
| **RA2.a** Genera la estructura de clases y funciones necesaria<br>*Actividades 4 y 5* | El código está todo en `main` o no funciona | Funciones y estructuras de datos adecuadas; el programa cumple los requisitos | Justifica la elección de colecciones, `data class` y funciones | Diseño coherente y reutilizable (funciones de extensión, *scope functions*, null safety) |
| **RA2.g** Prueba la interacción usuario-aplicación<br>*Validación de entradas* | Entradas no válidas provocan errores | Valida las entradas principales (rangos, conversiones, opciones del menú) | Controla excepciones y casos límite y explica cómo los ha probado | Mensajes al usuario claros y consistentes en todos los programas |
| **RA2.i** Documenta el desarrollo<br>*KDoc y documento* | Sin KDoc o sin documento | KDoc en archivos, clases y funciones; documento con capturas | El documento explica las decisiones de diseño | Código autoexplicativo en inglés y documento claro y bien estructurado |

<!--@include: ../../.vitepress/parts/escala-rubrica.md-->
