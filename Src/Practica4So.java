
/**
 *
 * @author Artie
 */
public class Practica4So {
    public static void main(String[] args) {
        // TODO code application logic here
        String m = "1.-Crear Proceso Nuevo\n2.-Ver estado actual del Sistema\n3.-Imprimir Cola de Procesos\n4.-Ver proceso actual\n5.-Ejecutar proceso actual\n6.-Pasar al proceso siguiente\n7.-Matar proceso actual\n8.-Salir del programa\n";
        int opcion;
        do{
            opcion = Utilidades.rMenu(m,8);
        }while(opcion!=8);
        
    }
    
}
