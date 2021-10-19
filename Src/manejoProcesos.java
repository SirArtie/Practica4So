
/**
 * @autor Tere
 * @author Artie
 *
 */

import java.util.ArrayList;
public class manejoProcesos {
  int id = 0;
  ArrayList<Proceso> Preparados = new ArrayList();

  /*Nota de Axel: Esta parte, la cambie porque creo que era conveniente
  usar la misma funcion de la gestion de procesos para poder llevar una cuenta de
  un id para cada proceso, y el parametro de gestion de memoria, porque es
  mucho mas sencillo pasar el objeto de gestion de memoria para añadir
  a los elementos a la lista de memoria*/
  public void crearProceso(String nombreP, gestionMemoria m){
    id ++;
    int instrucciones, localidades = 0, seleccionar = 0;
    boolean hayEspacio;
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

    hayEspacio = this.revisarEspacioMemoria();

    if(hayEspacio == true){
        Proceso nuevoProceso = new Proceso(nombreP, instrucciones, localidades, id);

        System.out.println("\n----------------------------------------------------------------\n");
        System.out.println("*** Proceso creado ***\n");
        System.out.println("Nombre del proceso: " + nombreP);
        System.out.println("Número de instrucciones: " + instrucciones);
        System.out.println("Número de localidades: " + localidades);
        System.out.println("Id de proceso: " + id);
        System.out.println("\n----------------------------------------------------------------\n");

        this.mandarAColaPreparados(nuevoProceso);
        //Aqui es donde se usa la funcion para buscar y posteriormente insertar el proceso en un segmento
        m.buscarSegmentoVacio(nuevoProceso);
    }else{
        System.out.println("Error: No se puede crear proceso porque no hay espacio en memoria\n");
    }
  }

  public boolean revisarEspacioMemoria(){

    boolean verificar = true;//Debe estar en false
    //Falta desarrollar
    return verificar;
    }

  void mandarAColaPreparados(Proceso proceso){
    System.out.println("El proceso está preparado para ser ejecutado\n");
    Preparados.add(proceso);
  }

  void imprimirInformacionPreparados(){
    int faltan;
    System.out.println("Los proceso que están preparados para ejecutarse son: \n");
    for (int i = 0; i < Preparados.size(); i++) {
        Proceso proc;
        proc = Preparados.get(i);
        System.out.println("PID = "+ proc.idP);
        System.out.println("Nombre Proceso: "+ proc.nombre);
        faltan = proc.instruccionesTotales - proc.instruccionesEjecutadas;
        System.out.println("Número de instrucciones que faltan por ejecutar: "+faltan+"\n");
    }
  }

  void imprimirProcesoActual(){
    System.out.println("Proceso Actual");
    Proceso proc;
    proc = Preparados.get(0);
    System.out.println("PID = "+ proc.idP);
    System.out.println("Nombre Proceso: "+ proc.nombre);
    System.out.println("Número de instrucciones totales: "+proc.instruccionesTotales);
    System.out.println("Número de instrucciones ejecutadas" + proc.instruccionesEjecutadas);
    System.out.println("Direcciones de memoria asignada");
  }

  void PasarProcesoSiguiente(){
    Proceso proc;
    proc = Preparados.remove(0);
    Preparados.add(proc);
  }
}
