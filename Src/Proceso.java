/*
Autor: Axel Arturo Bautista
Autor: Tere
Fecha: 15/10/2021

*/

//La siguiente funcion tiene como objetivo controlar los metodos necesarios
//para el control de procesos

//Advertencia: Esta clase es de prueba, aun hay elementos a desarrollar

public class Proceso{

  //Estas son las variables que se van a manejar para los procesos
  String nombre = null;
  int instruccionesTotales = 0;
  int instruccionesEjecutadas = 0;//Agregué atributo
  int r_localidad = 0;
  int idP = 0;

  //Constructor para inicializar los procesos segun los valores dados
  public Proceso(String nombre, int numInstrucciones, int numLocalidades, int idProceso){
    this.nombre = nombre;
    this.instruccionesTotales = numInstrucciones;
    this.r_localidad = numLocalidades;
    this.idP = idProceso;
  }
}
