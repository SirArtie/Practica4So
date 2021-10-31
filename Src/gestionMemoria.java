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
    void memoriaVacia(){
        Proceso vacio = new Proceso("Vacio",0,0,0);
        Segmento primerSegmento = new Segmento();
        primerSegmento.P = vacio;
        primerSegmento.base = 0;
        primerSegmento.limite = 2048;
        memoriaPrincipal.add(primerSegmento);
    }

    /*Para insertar necesitamos buscar espacio en la lista de memoria, por lo cual primero
    debemos localizar un espacio en donde colocar el proceso y posteriormente insertarlo,
    aunque esta funcion en concreto solo se encarga de la parte de la localizacion*/
    /*Agregado valor bool, para poder verificar si un proceso debe terminar de ser creado o no*/
    boolean buscarSegmentoVacio(Proceso p){
        int espacio;
        for( int i = 0; i<memoriaPrincipal.size(); i++){
            espacio = memoriaPrincipal.get(i).limite - memoriaPrincipal.get(i).base;
            //Con el siguiente if, verificamos que el segmento este vacio y ademas
            //pueda caber nuestro proceso, en terminos de algoritmo seria un PRIMER ENCAJE
            if(memoriaPrincipal.get(i).P.nombre == "Vacio" && p.r_localidad<=espacio){
                //Una vez encontrado un segmento, podemos insertar
                insertarSegmento(p,i);
                return true;
            }
        }
        System.out.println("No hay espacio suficiente para el proceso, se debe ejecutar o eliminar un proceso");
        return false;
    }

    /*Insertar es un poco laborioso, al manejar como tal una lista, se debe asegurar
    que los elementos sean coherentes en todo momento, y especialmente como estamos
    tratando con una lista que funge como la abstraccion de memoria hay que tener cuidado
    a la hora de manejar los registros base, limite y las separaciones*/
    void insertarSegmento(Proceso aInsertar, int n){
        /*Con este if, podemos verificar una cosa muy importante y el mejor de los casos, que las
        localidades del proceso justo coincidan con el tamaño del segmento, en este caso, podemos
        insertar el proceso sin mover ningun registro*/
        if (memoriaPrincipal.get(n).limite - memoriaPrincipal.get(n).base == aInsertar.r_localidad){
            memoriaPrincipal.get(n).P = aInsertar;
        } else{
            /*De otra manera, se divide la lista, esto porque tenemos un espacio vacio y otro que
            no lo va a estar, entonces hay que modificar los registros base y limite, el registro
            base es facil, simplemente es el que era antes de dividirse, con el registro limite
            del nuevo segmento debemos sumar el base mas el tamaño de la localidad del proceso,
            por fortuna este mismo valor cuenta como el nuevo valor base del segmento vacio, y el
            registro limite seguira siendo el mismo que era originalmente */
            Segmento S = new Segmento();
            S.P = aInsertar;
            S.base = memoriaPrincipal.get(n).base;
            S.limite = S.base + aInsertar.r_localidad;
            memoriaPrincipal.get(n).base = S.limite;
            memoriaPrincipal.add(n,S);
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

    /*Esta funcion solo se asegura de que haya espacio en memoria, no le interesa cuanto, esto
    es solo para verificar si se podria crear un proceso o no, sin embargo tambien se toma en cuenta
    el caso para cuando el espacio disponible es menor a 64 localidades, en ese caso directamente
    dice que no hay memoria*/
    boolean buscarEspacio(){
        int minimo;
        for( int i = 0; i<memoriaPrincipal.size(); i++){
        minimo = memoriaPrincipal.get(i).limite - memoriaPrincipal.get(i).base;
        if(memoriaPrincipal.get(i).P.nombre == "Vacio" && minimo>=64 )
            return true;
        }
        return false;
    }

    void estadoSistema(ManejoProcesos controlador){
        System.out.println("Número de procesos preparados para ejecutarse: "+controlador.Preparados.size()+"\n");
        System.out.println("Proceso finalizados exitosamente: ");
        controlador.imprimirFinalizados();
        System.out.println("\nProcesos eliminados antes de terminar su ejecución");
        controlador.imprimirInformacionEliminados();
        System.out.println("\nEstado de la memoria:");
        this.imprimirListaMemoria();
    }

    boolean quitarProcesoDeMemoria(Proceso p, ManejoProcesos controlador){
        boolean liberada = false;
        int baseProcesoAEliminar, limiteProcesoAEliminar;

        for( int i = 0; i<memoriaPrincipal.size(); i++){
            int anterior, siguiente;
            //Si se localiza el segmento de memoria del proceso a eliminar
            if(memoriaPrincipal.get(i).P.idP == p.idP){

                limiteProcesoAEliminar = memoriaPrincipal.get(i).limite;
                baseProcesoAEliminar = memoriaPrincipal.get(i).base;
                anterior = i-1;
                siguiente = i+1;
                //Se revisa si es el primer elemento de la lista, pues este no puede tener anterior
                if(i == 0){
                    if(siguiente < memoriaPrincipal.size()){
                        //Tienen elementos siguientes
                        if (memoriaPrincipal.get(siguiente).P.nombre == "Vacio") {
                            //Su elemento siguiente es vacío
                            //Se juntará lo del segmento vacío y lo del segmento del proceso que se eliminará
                            //La base se modifica y el límite se queda igual
                            memoriaPrincipal.get(siguiente).base = baseProcesoAEliminar;
                            memoriaPrincipal.remove(i);
                            liberada = true;
                            break;
                        }else{ //no tiene vacio a los lados se cambia a vacio el nombre
                        //Si no hay ningún espacio vacío junto a este segmento, se cambia el nombre del proceso a vacío
                        memoriaPrincipal.get(i).P.nombre = "Vacio";
                        liberada = true;
                                break;
                        }
                    }
                }else{
                   //Si no es el primer elemento de la lista
                    if (memoriaPrincipal.get(anterior).P.nombre == "Vacio") {
                        //Se juntará lo del segmento vacío y lo del segmento del proceso que se eliminará
                        //La base se queda igual y el límite se modifica
                        memoriaPrincipal.get(anterior).limite = limiteProcesoAEliminar;
                        memoriaPrincipal.remove(i);
                        liberada = true;
                        break;
                     //Si no fue la localidad anterior
                     //Si hay un segmentos después en la memoria
                    }else if(siguiente < memoriaPrincipal.size()){
                        if (memoriaPrincipal.get(siguiente).P.nombre == "Vacio") {
                            //Se juntará lo del segmento vacío y lo del segmento del proceso que se eliminará
                            //La base se modifica y el límite se queda igual
                            memoriaPrincipal.get(siguiente).base = baseProcesoAEliminar;
                            memoriaPrincipal.remove(i);
                            liberada = true;
                            break;
                        }
                        else{//mismo problemaa
                            //Si no hay ningún espacio vacío junto a este segmento, se cambia el nombre del proceso a vacío
                            memoriaPrincipal.get(i).P.nombre = "Vacio";
                            liberada = true;
                            break;
                        }
                    }
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
}
