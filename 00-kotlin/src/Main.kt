import java.util.*
import kotlin.collections.ArrayList

fun main(){
    println("Hola mundo"); // ; -> no es requerido
    //Tipo nombre = valor;
    //Int edad = 12;

    //INMUTABLES
    val inmutables:String = "Adrian"
    //inmutable = "Vicente"; // X

    //MUTABLES (var)
    var mutable: String = "Vicente"
    mutable = "Adrian"

    // preferir val > var

    //Sintaxis y Duck Typing

    val ejemploVariable = "Nombre"
    var edadEjemplo = 12
    //edadEjemplo = 12.2

    //Tipos de variables de JAVA
    val nombreProfesor: String = "Adrian Eguez"
    val sueldo: Double = 1.2
    val estadoCivil: Char = 'S'
    val fechaNacimiento: Date = Date()

    //Condicionales

    if(true){
        //verdadero
    }else{
        //False
    }

    //switch Estado -> S -> C -> :::::
    val estadoCivilWhen: String = "S"

    when(estadoCivilWhen){
        ("S") -> {
            println("Acercarse")
        }
        "UN" -> println("Hablar")
        else -> println("No reconocido")
    }

    val coqueteo = if(estadoCivilWhen == "S") "SI" else "NO"
    //condicion ? bloque-true: bloque-false

    //imprimirNombre("Adrian")

    //Named Parameters
    calcularSueldo(
        bonoEspecial = 15.00,
        //tasa = 12.00,
    sueldo = 150.00,
    )
    calcularSueldo(
        tasa = 14.00,
        bonoEspecial = 30.00,
        sueldo = 1000.00
    )

    //Tipos de arreglos

    //Arreglo Estatico
    val arregloEstatico: Array<Int> = arrayOf(1,2,3)

    //Arreglo Dinamico
    val arregloDinamico:ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11)
    arregloDinamico.add(12)
    println(arregloDinamico)

    //OPERADORES

    //FOR EACH -> Unit
    //Iterar un arreglo

    val respuestaForEach: Unit = arregloDinamico
        .forEach{ valorActual: Int ->
            println("Valor actual: ${valorActual}" )
        }
    arregloDinamico
        .forEachIndexed { indice: Int, valorActual: Int ->
            println("Valor ${valorActual} Indice: ${indice}")
        }
    println(respuestaForEach)

    //MAP -> muta el arreglo (Cambia el arreglo
    //1) Enviamos el nuevo valor de la iteracion
    //2)Nos devuelve es un NUEVO ARREGLO con los valores modificados
    val respuestaMap: List<Double> = arregloDinamico
        .map{ valorActual: Int ->
            return@map valorActual.toDouble()+100.00
        }
    println(respuestaMap)

    val respuestaMapDos = arregloDinamico.map { it + 15 }
    //      .map { valorActual : Int ->
    //          return@map valorActual + 15
    //      }

    println(respuestaMapDos)
}

fun imprimirNombre(nombre: String): Unit{
    println("Nombre: ${nombre}")
}

fun calcularSueldo(
    sueldo: Double,
    tasa: Double = 12.00,
    bonoEspecial: Double? = null,
): Double{
    //String -> String?
    //Int -> Int?
    //Date -> Date?
    if (bonoEspecial == null){
        return sueldo * (100/tasa)
    }else{
        return sueldo * (100/tasa) * bonoEspecial
    }
}