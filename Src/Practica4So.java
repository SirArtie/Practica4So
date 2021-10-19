
/**
 *
 * @author Artie
 * @author: Tere
 */
public class Practica4So {
    public static void main(String[] args) {
        String m = "1.-Crear Proceso Nuevo\n2.-Ver estado actual del Sistema\n3.-Imprimir Cola de Procesos\n4.-Ver proceso actual\n5.-Ejecutar proceso actual\n6.-Pasar al proceso siguiente\n7.-Matar proceso actual\n8.-Salir del programa\n";
        int opcion;
        //Se inicializa el administrador del manejo de procesos
        manejoProcesos controlador = new manejoProcesos();
        //Se inicializa el administrador de la memoria
        gestionMemoria memoria = new gestionMemoria();
        //Se inicializa la memoria, el segmento vacio
        memoria.memoriaVacia();
        do{
            //Funcion que permite pedir un meno continuamente
            opcion = Utilidades.rMenu(m,8);
            switch(opcion){
              case 1:
                    System.out.println("********** Crear Proceso *******************");
                    String mensajeProceso = "Ingresa el nombre del proceso";
                    /*Nota de Axel: El parametro de funcion, es basicamente una Funcion
                    que devuelve una cadena, esto es simplemente para simplificar el proceso*/
                    controlador.crearProceso(Utilidades.giveAString(mensajeProceso),memoria);
                    break;
              case 2:
                    System.out.println("");
                    break;
              case 3:
                    System.out.println("************** Imprimir cola de procesos *****************");
                    controlador.imprimirInformacionPreparados();
                    break;
              case 8:
                    System.out.println("Adios, vuelva pronto");
                    break;
              default:
                    System.out.println("La opción que ha insertado no es valida");
                    break;
            }

            //memoria.buscarSegmentoVacio();
            //Uso esta funcion para ver el estado de la memoria
            memoria.imprimirListaMemoria();
        }while(opcion!=8);

    }

}
