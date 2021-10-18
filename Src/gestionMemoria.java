import java.util.ArrayList;
import java.util.List;
public class gestionMemoria {
  List<Segmento> memoriaPrincipal = new ArrayList<Segmento>();

  public class Segmento{
    Proceso P;
    int base;
    int limite;
  }

  void memoriaVacia(){
    Proceso vacio = new Proceso("Vacio",0,0,0);
    Segmento primerSegmento = new Segmento();
    primerSegmento.P = vacio;
    primerSegmento.base = 0;
    primerSegmento.limite = 2048;
    memoriaPrincipal.add(primerSegmento);
    //Pruebas
    System.out.println(memoriaPrincipal);
    System.out.println(memoriaPrincipal.get(0));
    System.out.println(memoriaPrincipal.get(0).base);
    System.out.println(memoriaPrincipal.get(0).limite);
  }

  void buscarSegmentoVacio(){
    System.out.println("Si funciono, no me mates por favor");
    for( int i = 0; i<memoriaPrincipal.size(); i++){
      if(memoriaPrincipal.get(i).P.nombre == "Vacio"){
        System.out.println("Te encontre");
      }
    }
  }

}
