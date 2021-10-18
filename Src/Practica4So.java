
/**
 *
 * @author Artie
 * @author: Tere
 */
public class Practica4So {
    public static void main(String[] args) {
        // TODO code application logic here
        String m = "1.-Crear Proceso Nuevo\n2.-Ver estado actual del Sistema\n3.-Imprimir Cola de Procesos\n4.-Ver proceso actual\n5.-Ejecutar proceso actual\n6.-Pasar al proceso siguiente\n7.-Matar proceso actual\n8.-Salir del programa\n";
        int opcion;
        manejoProcesos controlador = new manejoProcesos();
        do{
            opcion = Utilidades.rMenu(m,8);
            switch(opcion){
              case 1:
                    System.out.println("********** Crear Proceso *******************");
                    String mensajeProceso = "Ingresa el nombre del proceso";
                    //objetoProceso P = new objetoProceso();
                    controlador.crearProceso(Utilidades.giveAString(mensajeProceso));
                    break;
              case 2:
                    System.out.println("");
                    break;
              case 3:
                    System.out.println("************** Imprimir cola de procesos *****************");
                    controlador.imprimirInformacionPreparados();
                    break;
            }
            gestionMemoria memoria = new gestionMemoria();
            memoria.memoriaVacia();
            memoria.buscarSegmentoVacio();
        }while(opcion!=8);

    }

}
