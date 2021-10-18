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

  void buscarSegmentoVacio(Proceso p){
    Proceso prueba = new Proceso("Proceso prueba",30,64,100);
    System.out.println("Si funciono, no me mates por favor");
    for( int i = 0; i<memoriaPrincipal.size(); i++){
      int espacio=memoriaPrincipal.get(i).limite - memoriaPrincipal.get(i).base;
      if(memoriaPrincipal.get(i).P.nombre == "Vacio" && prueba.r_localidad<=espacio){
        System.out.println("Te encontre");
        insertarSegmento(prueba,i);
        break;
      }
    }
  }

  void insertarSegmento(Proceso aInsertar, int n){
    //Necesario numero de lista
    if (memoriaPrincipal.get(n).limite - memoriaPrincipal.get(n).base == aInsertar.r_localidad){
      memoriaPrincipal.get(n).P = aInsertar;
    } else{
      Segmento S = new Segmento();
      S.P = aInsertar;
      S.base = memoriaPrincipal.get(n).base;
      S.limite = S.base + aInsertar.r_localidad;
      memoriaPrincipal.get(n).base = S.limite;
      if (n == 0)
        memoriaPrincipal.add(n,S);
      else
        memoriaPrincipal.add(n-1,S);
    }
  }
  void imprimirListaMemoria(){
    for( int i = 0 ; i<memoriaPrincipal.size(); i++){
      System.out.println("Segmento numero: " + (i+1) );
      System.out.println("Nombre del proceso: " + memoriaPrincipal.get(i).P.nombre);
      System.out.println("Base: " +memoriaPrincipal.get(i).base);
      System.out.println("Limite: " +memoriaPrincipal.get(i).limite);
    }
  }
}
