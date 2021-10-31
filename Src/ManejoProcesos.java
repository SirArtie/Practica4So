
/**
 * @autor Tere
 * @author Artie
 *
 */

import java.util.ArrayList;


//--------------------------------------------------------------------------------------------------------------------------------



  public class ManejoProcesos {
    ArrayList<Proceso> Preparados = new ArrayList();
    ArrayList<String> Finalizados = new ArrayList();
    ArrayList<Proceso> Eliminados = new ArrayList();
    int id = 0;

    /*Nota de Axel: Esta parte, la cambie porque creo que era conveniente
    usar la misma funcion de la gestion de procesos para poder llevar una cuenta de
    un id para cada proceso, y el parametro de gestion de memoria, porque es
    mucho mas sencillo pasar el objeto de gestion de memoria para añadir
    a los elementos a la lista de memoria*/
    public void crearProceso(String nombreP, gestionMemoria m){
        id ++;
        int instrucciones, localidades = 0, seleccionar = 0;

        instrucciones = (int)Math.floor(Math.random()*(30-10+1)+10);
        seleccionar = (int)Math.floor(Math.random()*4+1);

        //Con el número que se genere se asignará el número de localidades
        switch(seleccionar){
            case 1:
                localidades = 64;
                break;
            case 2:
                localidades = 128;
                break;
            case 3:
                localidades = 256;
                break;
            case 4:
                localidades = 512;
                break;
        }

        if(m.buscarEspacio() == true){
            Proceso nuevoProceso = new Proceso(nombreP, instrucciones, localidades, id);

            System.out.println("\n*** Creando Proceso ***\n");//Nota Axel: Cambie esto a creando proceso para que tenga mas coherencia
            System.out.println("Nombre del proceso: " + nombreP);
            System.out.println("Número de instrucciones: " + instrucciones);
            System.out.println("Número de localidades: " + localidades);
            System.out.println("Id de proceso: " + id);
            /*Se termina de verificar que el espacio en memoria sea suficiente para poder crearlo*/
            if (m.buscarSegmentoVacio(nuevoProceso)){
                this.mandarAColaPreparados(nuevoProceso);
            }
        }else{
        System.out.println("\nError: No se puede crear el proceso porque no hay espacio en memoria");
        }
    }

    void mandarAColaPreparados(Proceso proceso){
        System.out.println("\nEl proceso está preparado para ser ejecutado\n");
        Preparados.add(proceso);
    }

    void imprimirInformacionPreparados(){
        int faltan;
        for (int i = 0; i < Preparados.size(); i++) {

            if(i == 0){
                System.out.println("**Proceso activo: ");
            }
            else if(i == 1){
                System.out.println("\n**Los proceso que están preparados para ejecutarse son: ");
            }
            Proceso proc;
            proc = Preparados.get(i);
            System.out.println("PID = "+ proc.idP);
            System.out.println("Nombre Proceso: "+ proc.nombre);
            faltan = proc.instruccionesTotales - proc.instruccionesEjecutadas;
            System.out.println("Número de instrucciones que faltan por ejecutar: "+faltan);
        }
    }

    void imprimirProcesoActual(gestionMemoria memoria){
        if(!Preparados.isEmpty()){
            int num;
            Proceso proc;
            proc = Preparados.get(0);
            System.out.println("PID = "+ proc.idP);
            System.out.println("Nombre Proceso: "+ proc.nombre);
            System.out.println("Número de instrucciones totales: "+proc.instruccionesTotales);
            System.out.println("Número de instrucciones ejecutadas:" + proc.instruccionesEjecutadas);
            System.out.println("Direcciones de memoria asignada: ");

            for (int i = 0; i < memoria.memoriaPrincipal.size(); i++) {
                if(memoria.memoriaPrincipal.get(i).P.idP == proc.idP){
                    System.out.println("    Base: "+memoria.memoriaPrincipal.get(i).base);
                    System.out.println("    Límite: "+memoria.memoriaPrincipal.get(i).limite);
                }
            }

            //System.out.println("\nProceso al final de la ejecución: ");
            //this.imprimirProceso(proc);
        }else{
            System.out.println("\nERROR: No hay procesos preparados para su ejecución\n");
        }
    }

    void imprimirProcesoActualSencillo(){
        if(!Preparados.isEmpty()){
            int num;
            Proceso proc;
            proc = Preparados.get(0);
            System.out.println("PID = "+ proc.idP);
            System.out.println("Nombre Proceso: "+ proc.nombre);
            System.out.println("Número de instrucciones totales: "+proc.instruccionesTotales);
            System.out.println("Número de instrucciones ejecutadas: " + proc.instruccionesEjecutadas);
        }else{
            System.out.println("\nERROR: No hay procesos preparados para su ejecución\n");
        }
    }

    void PasarProcesoSiguiente(){
        if(!Preparados.isEmpty()){
            Proceso proc;
            System.out.println("Proceso actual:");
            this.imprimirProcesoActualSencillo();
            proc = Preparados.remove(0);
            Preparados.add(proc);
            System.out.println("\nNuevo proceso actual:");
            this.imprimirProcesoActualSencillo();
        }else{
            System.out.println("\nERROR: No hay procesos preparados para su ejecución\n");
        }
    }

    void ejecutarProceso(gestionMemoria memoria){
        if(!Preparados.isEmpty()){
            System.out.println("\nEjecución...\n");
            int contador = 0, faltan;
            Proceso proceso = Preparados.remove(0);
            for (int i = 0; i < 5; i++) {
                proceso.instruccionesEjecutadas++;
                System.out.println("Instrucción "+proceso.instruccionesEjecutadas);
                contador++;
                faltan = proceso.instruccionesTotales - proceso.instruccionesEjecutadas;
                if (contador == 5 || faltan == 0) {
                    if (faltan == 0) {
                        System.out.println("\nEl proceso a finalizado su ejecución");
                        String informacion;
                        informacion = "PID: "+proceso.idP+"   Nombre proceso: "+proceso.nombre;
                        Finalizados.add(informacion);

                        memoria.procesoAEliminar(proceso.idP,this,2);//codigo 1 = eliminar, 2 = finalizo

                    }else{
                        Preparados.add(proceso);
                    }
                    break;
                }
            }
        }else{
            System.out.println("ERROR: No hay procesos preparados para su ejecución\n");
        }
    }

    void imprimirFinalizados(){
        if(!Finalizados.isEmpty()){
            for (int i = 0; i < Finalizados.size(); i++) {
                System.out.println(Finalizados.get(i));
            }
        }else{
            System.out.println("No ha finalizado ningún proceso");
        }
    }

    int numeroProcesosPreparados(){
        int num;
        num = Preparados.size();
        return num;
    }


    void imprimirInformacionEliminados(){
        if (!Eliminados.isEmpty()) {
            int faltan;
            for (int i = 0; i < Eliminados.size(); i++) {
                Proceso proc;
                proc = Eliminados.get(i);
                System.out.println("PID = "+ proc.idP);
                faltan = proc.instruccionesTotales - proc.instruccionesEjecutadas;
                System.out.println("Número de instrucciones que faltaban por ejecutar: "+faltan);
            }
        }else{
            System.out.println("No se eliminaron procesos");
        }
    }
}
