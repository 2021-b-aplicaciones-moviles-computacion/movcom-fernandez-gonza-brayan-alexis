package com.example.examen_ib

class BBaseDatosMemoria {
    companion object{
        var arregloArquitectos = arrayListOf<BArquitecto>()
        var arregloProyectos = arrayListOf<BProyecto>()
        var arregloArquitecto_Proyecto = arrayListOf<BArquitecto_Proyecto>()

        init {
            //Arquitectos
            arregloArquitectos.add(
                BArquitecto(1713990602,"Miguel Cardenas","100.0","true")
            )
            arregloArquitectos.add(
                BArquitecto(1729482119,"Diana Gales","2500.0","true")
            )
            arregloArquitectos.add(
                BArquitecto(1711299584,"Samantha Galarza","1850.0","false")
            )
            arregloArquitectos.add(
                BArquitecto(1799433221,"Esteban Guerrero","1000.0","false")
            )
            arregloArquitectos.add(
                BArquitecto(1872237463,"Julian Venegas","1399.0","true")
            )

            //Proyectos
            arregloProyectos.add(
                BProyecto(1,"SOHO NY","17800200.0")
            )
            arregloProyectos.add(
                BProyecto(2,"YOO UIO","8600590.40")
            )
            arregloProyectos.add(
                BProyecto(3,"Calibur LIM","239000.12")
            )
            arregloProyectos.add(
                BProyecto(4,"One Tower NY","37000000.0")
            )
            arregloProyectos.add(
                BProyecto(5,"Square NY","68900400.0")
            )
            arregloProyectos.add(
                BProyecto(6,"Flex LIM","3200380.0")
            )
            arregloProyectos.add(
                BProyecto(7,"Unique UIO","4000230.0")
            )
            arregloProyectos.add(
                BProyecto(8,"Torre Bellavista BOG","2000370.16")
            )
            arregloProyectos.add(
                BProyecto(9,"Torre Centenario STG","22890000.0")
            )
            arregloProyectos.add(
                BProyecto(10,"La Bordadora LAX", "5900345.12")
            )

            //Arquitecto con sus proyectos
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1713990602,1)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1713990602,2)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1729482119,3)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1729482119,4)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1711299584,5)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1711299584,6)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1799433221,7)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1799433221,8)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1872237463,9)
            )
            arregloArquitecto_Proyecto.add(
                BArquitecto_Proyecto(1872237463,10)
            )
        }
    }
}