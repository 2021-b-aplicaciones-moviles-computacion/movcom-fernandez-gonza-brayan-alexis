import java.io.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.cos

fun main(){
    val scanner = Scanner(System.`in`)
    Arquitecto.leerArchivo()
    Proyecto.leerArchivoProyecto()
    do{
        print(
            "Bienvenidos\n" +
                    "Seleccione e ingrese el número de la opción\n"+
                    "1: Arquitecto\n"+
                    "2: Proyecto\n"+
                    "0: Salir\n"+
                    "Ingreso:"
        )
        var opcionMenu = scanner.nextLine().toInt()
        when(opcionMenu){
            1 -> {
                Arquitecto.mostrarArquitecto(Arquitecto.listadoArquitectos)
                do{
                    print("Seleccione e ingrese el número de la acción a realizar\n"+
                    "1: Ingresar arquitecto nuevo\n"+
                            "2: Buscar un arquitecto\n"+
                            "3: Actualizar datos de un arquitecto\n"+
                            "4: Eliminar un arquitecto\n"+
                            "0: Salir\n"+
                            "Ingreso:"
                    )
                    var opcionArquitecto = scanner.nextLine().toInt()
                    when (opcionArquitecto){
                        1 -> {
                            println("Ingrese la cedula del arquitecto")
                            var cedula = scanner.nextLine().toInt()
                            println("Ingrese el nombre del arquitecto")
                            var nombre = scanner.nextLine()
                            println("Ingrese el salario")
                            var salario = scanner.nextLine().toDouble()
                            var fechaNacimiento = Date()
                            var afiliado = true
                            do {
                                print("¿Esta afilidad@?\n"+
                                        "1. Si\n"+
                                        "2. No\n"+
                                        "Ingrese:"
                                )
                                var esAfiliado = scanner.nextLine().toInt()
                                if (esAfiliado == 1){
                                    afiliado = true
                                }else if (esAfiliado == 2){
                                    afiliado = false
                                }else{
                                    println("Ingreso incorrecto")
                                }
                            }while (esAfiliado != 1 && esAfiliado != 2)
                            var nuevoArqui = Arquitecto(cedula, nombre,salario,afiliado,fechaNacimiento)
                            Arquitecto.agregarArquitecto(nuevoArqui)
                            break
                        }
                        2 -> {
                            do{
                                print("Elija el tipo de consulta\n"+
                                        "1. Por cedula\n"+
                                        "2. Por nombre\n"+
                                        "0. Salir\n"+
                                        "Ingreso:"
                                )
                                var opcionConsulta = scanner.nextLine().toInt()
                                when(opcionConsulta){
                                    1 -> {
                                        print("Ingrese el número de cedula:")
                                        var numCedula = scanner.nextLine().toInt()
                                        Arquitecto.mostrarArquitecto(Arquitecto.getCedula(numCedula))
                                        break
                                    }
                                    2 -> {
                                        print("Ingrese el nombre:")
                                        var nombreArqui = scanner.nextLine()
                                        Arquitecto.mostrarArquitecto(Arquitecto.getNombre(nombreArqui))
                                        break
                                    }
                                    0 -> {
                                        break
                                    }
                                    else -> {
                                        println("Ingreso de datos incorrecto")
                                    }
                                }
                            }while (opcionConsulta != 0)
                        }
                        3 -> {
                            do {
                                print("Ingrese el dato a actualizar\n"+
                                        "1. Actualizar nombre\n"+
                                        "2. Actualizar salario\n"+
                                        "3. Actualizar estado de afiliación\n"+
                                        "4. Actualizar fecha de nacimiento\n"+
                                        "0. Salir\n"+
                                        "Ingreso:"
                                )
                                var opcionActualizar = scanner.nextLine().toInt()
                                when(opcionActualizar){
                                    1 -> {
                                        print("Ingrese la cedula del arquitecto:")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el nombre:")
                                        var newNombre = scanner.nextLine().toString()
                                        Arquitecto.setNombre(newNombre, id)
                                        break
                                    }
                                    2 -> {
                                        print("Ingrese la cedula del arquitecto")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el salario:")
                                        var newSalario = scanner.nextLine().toDouble()
                                        Arquitecto.setSalario(newSalario, id)
                                        break
                                    }
                                    3 -> {
                                        print("Ingrese la cedula del arquitecto")
                                        var id = scanner.nextLine().toInt()
                                        do{
                                            print("¿Esta actualmente afiliad@?\n"+
                                                    "1. Si\n"+
                                                    "2. No\n"+
                                                    "0. Salir\n"+
                                                    "Ingreso:"
                                            )
                                            var estadoAfiliado = scanner.nextLine().toInt()
                                            if(estadoAfiliado == 1){
                                                Arquitecto.setAfiliado(true, id)
                                                break
                                            }else if (estadoAfiliado == 2){
                                                Arquitecto.setAfiliado(false, id)
                                                break
                                            }else{
                                                println("Ingreso de datos incorrecto")
                                            }
                                        }while (estadoAfiliado != 0)
                                        break
                                    }
                                    4 -> {
                                        print("Ingrese la cedula del arquitecto")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese la fecha:")
                                        var newFecha = Date()
                                        Arquitecto.setFechaNacimiento(newFecha, id)
                                        break
                                    }
                                    0 -> {
                                        break
                                    }
                                }
                            }while (opcionActualizar != 0)
                        }
                        4 -> {
                            println("Ingrese la cedula del arquitecto a eliminar")
                            var id = scanner.nextLine().toInt()
                            Arquitecto.eliminarArquitecto(id)
                            break
                        }
                        0 -> {
                            break
                        }
                        else -> println("Datos ingresados incorrectamente")
                    }
                }while (opcionMenu != 0)
            }
            2 -> {
                Proyecto.mostrarProyecto(Proyecto.listadoProyectos)
                do{
                    print("Seleccione e ingrese el numero de la acción a realizar:\n"+
                            "1. Ingresar proyecto nuevo\n"+
                            "2. Buscar un proyecto\n"+
                            "3. Actualizar un proyecto\n"+
                            "4. Eliminar un proyecto\n"+
                            "0. Salir\n"+
                            "Ingresar:"
                    )
                    var opcionProyecto = scanner.nextLine().toInt()
                    when(opcionProyecto){
                        1 -> {
                            println("Ingrese el numero del proyecto:")
                            var numeroProy = scanner.nextLine().toInt()
                            println("Ingrese el nombre del proyecto:")
                            var nombreProy = scanner.nextLine()
                            println("Ingrese el costo del proyecto:")
                            var costoProy = scanner.nextLine().toDouble()
                            var fechaEntregaProy = Date()
                            var rentabilidad = true
                            do {
                                print("¿Es rentable el proyecto?\n"+
                                        "1. Si\n"+
                                        "2. No\n"+
                                        "Ingreso:"
                                )
                                var opcionRentabilidad = scanner.nextLine().toInt()
                                if (opcionRentabilidad == 1){
                                    rentabilidad = true
                                }else if (opcionRentabilidad == 2){
                                    rentabilidad = false
                                }else{
                                    println("Datos incorrectos")
                                }
                            }while (opcionRentabilidad != 1 && opcionRentabilidad != 2)
                            var idProy: Int
                            do {
                                println("Seleccione la cedula del arquitecto encargado del proyecto")
                                Arquitecto.listadoArquitectos.forEach { println("${it.nombre}: " + "${it.cedula}") }
                                idProy = scanner.nextLine().toInt()
                                var flag = false
                                Arquitecto.listadoArquitectos.forEach {
                                    if(it.cedula == idProy){
                                        flag = true
                                    }
                                }
                            }while (!flag)
                            var newProy = Proyecto(numeroProy,nombreProy, costoProy,Date(),rentabilidad,idProy)
                            Proyecto.agregarProyecto(newProy)
                            break
                        }
                        2 -> {
                            do {
                                print("Elija la opción de consulta\n"+
                                        "1. Por numero de proyecto\n"+
                                        "2. Por nombre de proyecto\n"+
                                        "0. Salir\n"+
                                        "Ingreso:"
                                )
                                var opcionConsulta = scanner.nextLine().toInt()
                                when (opcionConsulta){
                                    1 -> {
                                        print("Ingrese el numero del proyecto:")
                                        var id = scanner.nextLine().toInt()
                                        Proyecto.mostrarProyecto(Proyecto.getNumProyecto(id))
                                        break
                                    }
                                    2 -> {
                                        print("Ingrese el nombre del proyecto:")
                                        var nombreProy = scanner.nextLine()
                                        Proyecto.mostrarProyecto(Proyecto.getNombre(nombreProy))
                                        break
                                    }
                                    0 -> {
                                        break
                                    }
                                    else -> println("Datos ingresados incorrectamente")
                                }
                            }while (opcionConsulta != 0)
                        }
                        3 -> {
                            do{
                                print("Escoja el dato del proyecto a actualizar\n"+
                                        "1. Actualizar nombre\n"+
                                        "2. Actualizar costo\n"+
                                        "3. Actualizar fecha de entrega\n"+
                                        "4. Actualizar rentabilidad\n"+
                                        "5. Actualizar arquitecto a cargo\n"+
                                        "0. Salir\n"+
                                        "Ingreso:"
                                )
                                var opcionActualizar = scanner.nextLine().toInt()
                                when(opcionActualizar){
                                    1 -> {
                                        print("Ingrese el numero del proyecto a actualizar:")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el nuevo nombre:")
                                        var newNombre = scanner.nextLine().toString()
                                        Proyecto.setNombre(newNombre, id)
                                        break
                                    }
                                    2 -> {
                                        print("Ingrese el numero del proyecto a actualizar:")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese el nuevo costo:")
                                        var newCosto = scanner.nextLine().toDouble()
                                        Proyecto.setCosto(newCosto, id)
                                        break
                                    }
                                    3 -> {
                                        print("Ingrese el numero del proyecto a actualizar:")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese la nueva fecha de entrega:")
                                        var newFechaEntrega = Date()
                                        Proyecto.setFechaEntrega(newFechaEntrega, id)
                                        break
                                    }
                                    4 -> {
                                        print("Ingrese el numero del proyecto a actualizar:")
                                        var id = scanner.nextLine().toInt()
                                        do {
                                            print("¿Es rentable actualmente el proyecto?\n"+
                                                    "1. Si\n"+
                                                    "2. No\n"+
                                                    "0. Salir\n"+
                                                    "Ingreso:"
                                            )
                                            var opcionRentabilidad = scanner.nextLine().toInt()
                                            if (opcionRentabilidad == 1){
                                                Proyecto.setRentable(true, id)
                                                break
                                            }else if (opcionRentabilidad == 2){
                                                Proyecto.setRentable(false, id)
                                                break
                                            }else{
                                                println("Datos ingresados incorrectamente")
                                            }
                                        }while (opcionRentabilidad != 0)
                                    }
                                    5 -> {
                                        print("Ingrese el numero del proyecto a actualizar:")
                                        var id = scanner.nextLine().toInt()
                                        print("Ingrese la cedula del arquitecto a cargo:")
                                        var cedulaArqui = scanner.nextLine().toInt()
                                        Proyecto.setCedulaArquitecto(cedulaArqui, id)
                                        break
                                    }
                                    0 -> {
                                        break
                                    }
                                    else -> println("Datos ingresados incorrectamente")
                                }
                            }while (opcionActualizar != 0)
                        }
                        4 -> {
                            println("Ingrese el numero del proyecto a eliminar")
                            var numProyecto = scanner.nextLine().toInt()
                            Proyecto.eliminarProyecto(numProyecto)
                            break
                        }
                        0 -> {
                            break
                        }
                        else -> println("Escoja la opcion correcta")
                    }
                }while (opcionProyecto != 0)
            }
            0 -> {
                break
            }
            else -> println("Ingreso incorrecto de datos. Ingrese nuevamente")
        }
    }while (opcionMenu != 0)
}

class Arquitecto(
    cedula: Int,
    nombre: String,
    salario: Double,
    afiliado: Boolean,
    fechaNacimiento: Date,

){
    var cedula: Int = cedula
    var nombre: String = nombre
    var salario: Double = salario
    var afiliado: Boolean = afiliado
    var fechaNacimiento: Date = fechaNacimiento


    companion object{
        var listadoArquitectos = ArrayList<Arquitecto>()

        fun agregarArquitecto(nuevoArquitecto: Arquitecto){
            listadoArquitectos.add(nuevoArquitecto)
            actualizarRegistroArquitecto()
            mostrarArquitecto(listadoArquitectos)
        }

        //Getters
        fun getNombre(nombre: String):ArrayList<Arquitecto>{
            return listadoArquitectos.filter { it.nombre == nombre } as ArrayList<Arquitecto>
        }

        fun getSalario(salario: Double):ArrayList<Arquitecto>{
            return listadoArquitectos.filter { it.salario == salario } as ArrayList<Arquitecto>
        }

        fun getAfiliado(afiliado: Boolean):ArrayList<Arquitecto>{
            return listadoArquitectos.filter { it.afiliado == afiliado } as ArrayList<Arquitecto>
        }

        fun getFechaNacimiento(fechaNacimiento: Date): ArrayList<Arquitecto>{
            return listadoArquitectos.filter { it.fechaNacimiento == fechaNacimiento} as ArrayList<Arquitecto>
        }

        fun getCedula(cedula: Int): ArrayList<Arquitecto>{
            return listadoArquitectos.filter { it.cedula == cedula } as ArrayList<Arquitecto>
        }

        //Setters
        fun setNombre(nombre: String, cedula: Int){
            listadoArquitectos.filter { it.cedula == cedula }.map { it.nombre = nombre }
            actualizarRegistroArquitecto()
            mostrarArquitecto(listadoArquitectos)
        }

        fun setSalario(salario: Double, cedula: Int){
            listadoArquitectos.filter { it.cedula == cedula }.map { it.salario = salario }
            actualizarRegistroArquitecto()
            mostrarArquitecto(listadoArquitectos)
        }

        fun setAfiliado(afiliado: Boolean, cedula: Int){
            listadoArquitectos.filter { it.cedula == cedula }.map { it.afiliado = afiliado }
            actualizarRegistroArquitecto()
            mostrarArquitecto(listadoArquitectos)
        }

        fun setFechaNacimiento(fechaNacimiento: Date, cedula: Int){
            listadoArquitectos.filter { it.cedula == cedula }.map { it.fechaNacimiento = fechaNacimiento }
            actualizarRegistroArquitecto()
            mostrarArquitecto(listadoArquitectos)
        }

        //Delete

        fun eliminarArquitecto(cedula: Int){
            listadoArquitectos = listadoArquitectos.filter { it.cedula != cedula } as ArrayList<Arquitecto>
            actualizarRegistroArquitecto()
            mostrarArquitecto(listadoArquitectos)
        }

        fun mostrarArquitecto(arregloArquitecto: ArrayList<Arquitecto>){
            println("Cedula, Nombre, Salario, Afiliado, Fecha de Nacimiento")
            arregloArquitecto.forEach{arquitectActual: Arquitecto ->
            println(
                "${arquitectActual.cedula},"+
                        "${arquitectActual.nombre},"+
                        "${arquitectActual.salario},"+
                        "${arquitectActual.afiliado},"+
                        "${arquitectActual.fechaNacimiento}"
                )
            }
            println("")
        }

        fun actualizarRegistroArquitecto(){
            val path = "src/arquitecto.csv"
            try{
                FileWriter(path, false).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            out.print("Cedula, Nombre, Salario, Afiliado, Fecha de Nacimiento")
                        }
                    }
                }
            }catch (e: IOException){
            }
            try {
                FileWriter(path, true).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            listadoArquitectos.forEach{ arquitectActual: Arquitecto -> out.print("${arquitectActual.cedula},${arquitectActual.nombre},${arquitectActual.salario},${arquitectActual.afiliado},${arquitectActual.fechaNacimiento}\n")}
                        }
                    }
                }
            }catch (e: IOException){
            }
        }

        fun leerArchivo(){
            var miLector = Scanner(File("src/arquitecto.csv"))
            var cabecera = miLector.hasNextLine()
            var fila = miLector.nextLine()
            var datos = StringTokenizer(fila, ",")
            while (datos.hasMoreTokens()){
                datos.nextToken()
            }
            while (miLector.hasNextLine()){
                fila = miLector.nextLine()
                datos = StringTokenizer(fila, ",")
                while (datos.hasMoreTokens()){
                    var cedula = datos.nextToken().toInt()
                    var nombre = datos.nextToken()
                    var salario = datos.nextToken().toDouble()
                    var afiliado = datos.nextToken().toBoolean()
                    var fechaNacimiento = datos.nextToken()
                    var miArquitecto = Arquitecto(cedula, nombre, salario, afiliado, Date())
                    listadoArquitectos.add(miArquitecto)
                }
            }
        }

    }
}

class Proyecto(
    numProyecto: Int,
    nombre: String,
    costo: Double,
    fechaEntrega: Date,
    rentable: Boolean,
    idArquitecto: Int
){
    var numProyecto: Int = numProyecto
    var nombre: String = nombre
    var costo: Double = costo
    var fechaEntrega: Date = fechaEntrega
    var rentable: Boolean = rentable
    var idArquitecto: Int = idArquitecto

    companion object{
        var listadoProyectos = ArrayList<Proyecto>()

        fun agregarProyecto(nuevoProyecto: Proyecto){
            listadoProyectos.add(nuevoProyecto)
            actualizarArchivoProyecto()
            mostrarProyecto(listadoProyectos)
        }

        //Getters
        fun getNombre(nombre: String):ArrayList<Proyecto>{
            return listadoProyectos.filter { it.nombre == nombre } as ArrayList<Proyecto>
        }

        fun getCosto(costo: Double):ArrayList<Proyecto>{
            return  listadoProyectos.filter { it.costo == costo } as ArrayList<Proyecto>
        }

        fun getFechaEntrega(fechaEntrega: Date):ArrayList<Proyecto>{
            return  listadoProyectos.filter { it.fechaEntrega == fechaEntrega } as ArrayList<Proyecto>
        }

        fun getNumProyecto(numProyecto: Int):ArrayList<Proyecto>{
            return listadoProyectos.filter { it.numProyecto == numProyecto } as ArrayList<Proyecto>
        }

        fun getRentable(rentable: Boolean):ArrayList<Proyecto>{
            return listadoProyectos.filter { it.rentable == rentable } as ArrayList<Proyecto>
        }

        //Setters
        fun setNombre(nombre: String, numProyecto: Int){
            listadoProyectos.filter { it.numProyecto == numProyecto }.map { it.nombre = nombre }
            actualizarArchivoProyecto()
            mostrarProyecto(listadoProyectos)
        }

        fun setCosto(costo: Double, numProyecto: Int){
            listadoProyectos.filter { it.numProyecto == numProyecto }.map { it.costo = costo }
            actualizarArchivoProyecto()
            mostrarProyecto(listadoProyectos)
        }

        fun setFechaEntrega(fechaEntrega: Date, numProyecto: Int){
            listadoProyectos.filter { it.numProyecto == numProyecto }.map { it.fechaEntrega = fechaEntrega }
            actualizarArchivoProyecto()
            mostrarProyecto(listadoProyectos)
        }

        fun setRentable(rentable: Boolean, numProyecto: Int){
            listadoProyectos.filter { it.numProyecto == numProyecto }.map { it.rentable = rentable }
            actualizarArchivoProyecto()
            mostrarProyecto(listadoProyectos)
        }

        fun setCedulaArquitecto(cedula: Int, numProyecto: Int){
            listadoProyectos.filter { it.numProyecto == numProyecto }.map { it.idArquitecto = cedula }
            actualizarArchivoProyecto()
            mostrarProyecto(listadoProyectos)
        }

        //Delete
        fun eliminarProyecto(numProyecto: Int){
            listadoProyectos = listadoProyectos.filter { it.numProyecto != numProyecto } as ArrayList<Proyecto>
            actualizarArchivoProyecto()
            mostrarProyecto(listadoProyectos)
        }

        fun mostrarProyecto(arregloProyectos: ArrayList<Proyecto>){
            println("Numero de proyecto, Nombre, Costo, Fecha de entrega, Rentable, Cedula arquitecto")
            arregloProyectos.forEach{ proyectoActual: Proyecto ->
            println(
                "${proyectoActual.numProyecto}," +
                        "${proyectoActual.nombre}," +
                        "${proyectoActual.costo}," +
                        "${proyectoActual.fechaEntrega}," +
                        "${proyectoActual.rentable},"+
                        "${proyectoActual.idArquitecto}"
            )}
            println("")
        }

        fun actualizarArchivoProyecto(){
            val path = "src/proyecto.csv"
            try {
                FileWriter(path, false).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            out.print("Numero de proyecto, Nombre, Costo, Fecha de entrega, Rentable, Cedula Arquitecto\n")
                        }
                    }
                }
            }catch (e: IOException){
            }
            try {
                FileWriter(path, true).use { fw ->
                    BufferedWriter(fw).use { bw ->
                        PrintWriter(bw).use { out ->
                            listadoProyectos.forEach { proyectoActual ->
                                out.print(
                                    "${proyectoActual.numProyecto},"+
                                            "${proyectoActual.nombre},"+
                                            "${proyectoActual.costo},"+
                                            "${proyectoActual.fechaEntrega},"+
                                            "${proyectoActual.rentable}," +
                                            "${proyectoActual.idArquitecto}\n"
                                )
                            }
                        }
                    }
                }
            }catch (e: IOException){
            }
        }

        fun leerArchivoProyecto(){
            var miLector = Scanner(File("src/proyecto.csv"))
            var cabecera = miLector.hasNextLine()
            var fila = miLector.nextLine()
            var datos = StringTokenizer(fila, ",")
            while (datos.hasMoreTokens()){
                datos.nextToken()
            }
            while (miLector.hasNextLine()){
                fila = miLector.nextLine()
                datos = StringTokenizer(fila, ",")
                while (datos.hasMoreTokens()){
                    var numProyecto = datos.nextToken().toInt()
                    var nombre = datos.nextToken()
                    var costo = datos.nextToken().toDouble()
                    var fechaEntrega = datos.nextToken()
                    var rentable = datos.nextToken().toBoolean()
                    var idArquitecto = datos.nextToken().toInt()
                    var miProyecto = Proyecto(numProyecto, nombre, costo, Date(), rentable,idArquitecto)
                    listadoProyectos.add(miProyecto)
                }
            }
        }
    }




}