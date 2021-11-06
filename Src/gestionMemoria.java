import java.util.ArrayList;
import java.util.List;

//---------------------------------------------------------------------------------------------------------------



  public class gestionMemoria {
    //ManejoProcesos controlador = new ManejoProcesos();
    /*En esta implementacion usa la idea de cola de memoria, por lo cual
    cada segmento sera un pedazo de memoria en el cual hay un proceso y
    esta delimitado por un registro base y limite, en donde demarca
    las localidades abarcadas por el segmento, independientemente
    de si este vacio o tenga algun segmento*/

    List<Segmento> memoriaPrincipal = new ArrayList<Segmento>();

    //Aqui se puede observar el objeto segmento, el cual sera la unidad basica
    //de nuestra estructura memoriaPrincipal, la cual es una lista
    public class Segmento{
        Proceso P;
        int base;
        int limite;
    }

    //Esta funcion inicializa la lista con el segmento 0 a 2048 que representa la memoria
    //Esta funcion se modifica para la practica 5, ahora se va a dividir la memoria en partes iguales
    void memoriaVacia(){

      for(int i = 0; i<64; i++){

        Segmento auxiliar = new Segmento();
        Proceso vacio = new Proceso("Vacio",0,0,0);
        auxiliar.P = vacio;
        auxiliar.base = 16*i;
        auxiliar.limite = 16;
        memoriaPrincipal.add(auxiliar);
        }
    }

    /*Insertar es un poco laborioso, al manejar como tal una lista, se debe asegurar
    que los elementos sean coherentes en todo momento, y especialmente como estamos
    tratando con una lista que funge como la abstraccion de memoria hay que tener cuidado
    a la hora de manejar los registros base, limite y las separaciones*/
    void insertarProceso(Proceso aInsertar){
      int npaginas = aInsertar.r_localidad / 16;
      int contador = 0;
      aInsertar.inicializarTP();
      //Ciclo for para buscar los indices de memoria en los cuales se va a insertar las paginas
      for (int i = 0; i < memoriaPrincipal.size(); i++){
        if(memoriaPrincipal.get(i).P.nombre == "Vacio"){
          memoriaPrincipal.get(i).P = aInsertar;
          aInsertar.localidadesTP[contador] = i;
          //memoriaPrincipal.get(i).P.r_localidad = 16;
          contador++;
        }
        if(contador == npaginas)
          break;
      }
    }

    /*Esta funcion recorre la lista de memoria e imprime los nombres
    del proceso de cada segmento asi como su valor de base y limite*/
    void imprimirListaMemoria(){
        for( int i = 0 ; i<memoriaPrincipal.size(); i++){
            System.out.println("Segmento numero: " + (i+1) );
            System.out.println("Nombre del proceso: " + memoriaPrincipal.get(i).P.nombre);
            System.out.println("Base: " +memoriaPrincipal.get(i).base);
            System.out.println("Limite: " +memoriaPrincipal.get(i).limite+"\n");
        }
    }

    void estadoProcesos(ManejoProcesos controlador){
        System.out.println("Número de procesos preparados para ejecutarse: "+controlador.Preparados.size()+"\n");
        System.out.println("Proceso finalizados exitosamente: ");
        controlador.imprimirFinalizados();
        System.out.println("\nProcesos eliminados antes de terminar su ejecución");
        controlador.imprimirInformacionEliminados();
        //System.out.println("\nEstado de la memoria:");//Ya no se usa
        //this.imprimirListaMemoria();
    }

    /*Esta función ayuda a verificar si hay espacio suficiente para todo el proceso, ya que antes de
    empezar a asignar en memoria las páginas se debe de confirmar que hay espacio para todo*/
    boolean buscarEspacioProcesoTotal(int tamanio){
        boolean hayEspacio = false;
        int contadorLocalidades = 0;
        for (int i = 0; i < memoriaPrincipal.size(); i++) {
            if (memoriaPrincipal.get(i).P.nombre == "Vacio") {
              contadorLocalidades = contadorLocalidades + 16;
                if(contadorLocalidades >= tamanio){
                    hayEspacio = true;
                    return hayEspacio;
                }
            }
        }
        return hayEspacio;
    }


    boolean quitarProcesoDeMemoria(Proceso p, ManejoProcesos controlador){
        boolean liberada = false;
        int contadorMarcos = 0;

        for( int i = 0; i<memoriaPrincipal.size(); i++){
            if(memoriaPrincipal.get(i).P.idP == p.idP){
                memoriaPrincipal.get(i).P.nombre = "Vacio";
                contadorMarcos = contadorMarcos + 1;
                if (contadorMarcos == p.num_paginas) {
                    liberada = true;
                    break;
                }
            }
        }
        return liberada;
    }

    boolean procesoAEliminar(int id, ManejoProcesos controlador, int codigo){
        boolean eliminado = false;
        Proceso procesoAEliminar;
        for( int i = 0; i<memoriaPrincipal.size(); i++){
            //Verifico si el número ingresado corresponde al pid de un proceso
            if(memoriaPrincipal.get(i).P.idP == id){
                //Si es así el proceso a eliminar será ese
                procesoAEliminar = memoriaPrincipal.get(i).P;
                //Voy a quitarlo de la memoria y devuelve si fue eliminado
                eliminado = this.quitarProcesoDeMemoria(procesoAEliminar, controlador);
                //Si fue eliminado
                if(eliminado == true){
                    if (codigo == 1) {
                        //Agrego al proceso a la lista de Eliminados
                        controlador.Eliminados.add(procesoAEliminar);
                    }
                    //Lo busco en la lista de preparados para eliminarlo
                    for (int j = 0; j < controlador.Preparados.size(); j++) {
                        //Si está en la lista de preparados lo elimino
                        if(controlador.Preparados.get(j).idP == procesoAEliminar.idP){
                            controlador.Preparados.remove(j);
                        }
                    }
                    break;
                }
            }
        }
        if(eliminado != true){
            System.out.println("El PID ingresado no corresponde a ninguno de los procesos\n");
        }
        return eliminado;
    }


//Lista ligada de la memoria
void estadoMemoria(){
        int indice = 0;
        ArrayList<Integer> baseProcesos = new ArrayList();
        for (int i = 0; i<memoriaPrincipal.size(); i++) {
            if (i == 0) {
                if (memoriaPrincipal.get(i).P.nombre == "Vacio") {
                    System.out.println("Vacio");
                    System.out.println("    Base: "+memoriaPrincipal.get(i).base);
                    baseProcesos.add(memoriaPrincipal.get(i).base);
                }else{
                    System.out.println("Id Proceso: "+memoriaPrincipal.get(i).P.idP);
                    System.out.println("    Base: "+memoriaPrincipal.get(i).base);
                    baseProcesos.add(memoriaPrincipal.get(i).base);
                }
            }else{
                if (memoriaPrincipal.get(i).P.nombre == "Vacio") {
                    if (memoriaPrincipal.get(i-1).P.nombre != "Vacio") {
                        System.out.println("Vacio");
                        System.out.println("    Base: "+memoriaPrincipal.get(i).base);
                        baseProcesos.add(memoriaPrincipal.get(i).base);
                    }else{
                        if (i != memoriaPrincipal.size()-1) {
                            if (memoriaPrincipal.get(i+1).P.nombre != "Vacio") {
                                indice = baseProcesos.size()-1;
                                System.out.println("    Límite: "+(memoriaPrincipal.get(i+1).base-baseProcesos.get(indice)));
                            }
                        }else{
                            indice = baseProcesos.size()-1;
                            System.out.println("    Límite: " + (memoriaPrincipal.get(i).base-baseProcesos.get(indice)+16) );
                        }
                    }
                }else{
                    if (memoriaPrincipal.get(i).P.idP != memoriaPrincipal.get(i-1).P.idP) {
                        System.out.println("Id Proceso: "+memoriaPrincipal.get(i).P.idP);
                        System.out.println("    Base: "+memoriaPrincipal.get(i).base);
                        baseProcesos.add(memoriaPrincipal.get(i).base);
                    }else{
                        if (i != memoriaPrincipal.size()-1) {
                            if (memoriaPrincipal.get(i).P.idP != memoriaPrincipal.get(i+1).P.idP) {
                                indice = baseProcesos.size()-1;
                                System.out.println("    Límite: "+(memoriaPrincipal.get(i+1).base-baseProcesos.get(indice)));
                            }
                        }else{
                            indice = baseProcesos.size()-1;
                            System.out.println("    Límite: " + (memoriaPrincipal.get(i).base-baseProcesos.get(indice)+16)  );
                        }
                    }
                }
            }
        }
    }

    void desfragmentarMemoria(){
      System.out.println("No esta implementado favor de volver luego");
    }
  }
