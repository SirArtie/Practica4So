import java.util.ArrayList;
import java.util.List;
public class gestionMemoria {
  List<Segmento> memoriaPrincipal = new ArrayList<Segmento>();

  public class Segmento{
    objetoProceso P;
    int base;
    int limite;
  }

  void primerEstado(){
      objetoProceso memoriaVacia = new objetoProceso();
      memoriaVacia.nombre = "Sin proceso";
      Segmento primerSegmento = new Segmento();
      primerSegmento.P = memoriaVacia;
      primerSegmento.base = 0;
      primerSegmento.limite = 2048;
      memoriaPrincipal.add(primerSegmento);
      //Pruebas
      System.out.println(memoriaPrincipal);
      System.out.println(memoriaPrincipal.get(0));
      System.out.println(memoriaPrincipal.get(0).base);
      System.out.println(memoriaPrincipal.get(0).limite);
  }



}
