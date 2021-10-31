
/**
 *
 * @author Artie
 * @author: Tere
 */
 /*

//----------------------------------------------------------------------------------------------------------------------------------


    public class Practica4SO {

    /**
     * @param args the command line arguments
     */
  public class Practica4So{

    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("\n****************************************************************");
        System.out.println("********************** GESTIÓN DE MEMORIA **********************");
        System.out.println("****************************************************************\n");

        String m = "MENÚ DE OPCIONES:\n1.-Crear Proceso Nuevo\n2.-Ver estado actual del Sistema\n3.-Imprimir Cola de Procesos\n4.-Ver proceso actual\n5.-Ejecutar proceso actual\n6.-Pasar al proceso siguiente\n7.-Matar proceso actual\n8.-Salir del programa\n";
        int opcion;

        //Se inicializa el administrador del manejo de procesos
        ManejoProcesos controlador = new ManejoProcesos();

        //Se inicializa el administrador de la memoria
        gestionMemoria memoria = new gestionMemoria();

        memoria.memoriaVacia();
        do{
            //Funcion que permite pedir un meno continuamente
            opcion = Utilidades.rMenu(m,8);
            switch(opcion){
                case 1:
                    System.out.println("\n----------------------------------------------------------------\n");
                    System.out.println("********** Crear Proceso *******************\n");
                    String mensajeProceso = "Ingresa el nombre del proceso";
                    /*Nota de Axel: El parametro de funcion, es basicamente una Funcion
                    que devuelve una cadena, esto es simplemente para simplificar el proceso*/
                    controlador.crearProceso(Utilidades.giveAString(mensajeProceso),memoria);
                    System.out.println("\n----------------------------------------------------------------\n");
                    break;
                case 2:
                    System.out.println("\n----------------------------------------------------------------\n");
                    System.out.println("******************** Estado actual del sistema ********************\n");
                    memoria.estadoSistema(controlador);
                    System.out.println("\n----------------------------------------------------------------\n");
                    break;
                case 3:
                    System.out.println("\n----------------------------------------------------------------\n");
                    System.out.println("************** Imprimir cola de procesos *****************\n");
                    controlador.imprimirInformacionPreparados();
                    System.out.println("\n----------------------------------------------------------------\n");
                    break;
                case 4:
                    System.out.println("\n----------------------------------------------------------------\n");
                    System.out.println("*************** Proceso actual ***************\n");
                    controlador.imprimirProcesoActual(memoria);
                    System.out.println("\n----------------------------------------------------------------\n");
                    break;
                case 5:
                    System.out.println("\n----------------------------------------------------------------\n");
                    System.out.println("*************** Ejecutar proceso actual ***************\n\nAntes de ejecutar:");
                    controlador.imprimirProcesoActual(memoria);
                    controlador.ejecutarProceso(memoria);
                    System.out.println("\n----------------------------------------------------------------\n");
                    break;
                case 6:
                    System.out.println("\n----------------------------------------------------------------\n");
                    System.out.println("******************** Pasar al proceso siguiente ********************\n");
                    controlador.PasarProcesoSiguiente();
                    System.out.println("\n----------------------------------------------------------------\n");
                    break;
                case 7:
                    int numProceso = -1;
                    boolean seElimino = false;
                    System.out.println("\n----------------------------------------------------------------\n");
                    System.out.println("******************** Matar proceso actual ********************");
                    memoria.imprimirListaMemoria();
                    System.out.println("Considerando los datos mostrados de la Memoria ingrese el PID del proceso que desea eliminar");
                    numProceso = Utilidades.giveAInt();
                    seElimino = memoria.procesoAEliminar(numProceso,controlador,1);
                    if (seElimino == true) {
                        System.out.println("El proceso seleccionado se ha eliminado");
                    }
                    else{
                        System.out.println("\"ERROR: Ingrese datos correctos\"");
                    }
                    System.out.println("\n----------------------------------------------------------------\n");
                    break;
                case 8:
                    System.out.println("\n----------------------------------------------------------------\n");
                    System.out.println("El estado final del sistema es: \n");
                    memoria.estadoSistema(controlador);
                    System.out.println("\nAdios, vuelva pronto");
                    System.out.println("\n----------------------------------------------------------------\n");
                    break;
                default:
                    System.out.println("La opción que ha insertado no es valida");
                    break;
            }
            //memoria.buscarSegmentoVacio();
            //Uso esta funcion para ver el estado de la memoria
            //memoria.imprimirListaMemoria();
        }while(opcion!=8);
    }

}
