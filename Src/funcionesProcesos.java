
/**
 *
 * @author Artie
 */
public class funcionesProcesos {
  int id = 0;

    public void crearProceso(String nombreP,objetoProceso P){
      id++;
      P.nombre = nombreP;
      P.instrucciones = (int)Math.floor(Math.random()*(30-10+1)+10);
      P.r_localidad = (int)Math.floor(Math.random()*(4-1+1)+1);
      P.idP = id;
      System.out.println("Nombre del proceso: " + P.nombre);
      System.out.println("Numero de instrucciones: " + P.instrucciones);
      System.out.println("Numero de localidades: " + P.r_localidad);
      System.out.println("Id de proceso: " + P.idP);
    }

}
