/*
Autor: Axel Arturo Bautista
Fecha: 15/10/2021

*/

//La siguiente funcion tiene como objetivo controlar los metodos necesarios
//para el control de procesos

//Advertencia: Esta clase es de prueba, aun hay elementos a desarrollar

public class Proceso{

  String nombre = null;
  int instruccionesTotales = 0;
  int instruccionesEjecutadas = 0;//Agregué atributo
  int r_localidad = 0;
  int idP = 0;

  public Proceso(String nombre, int numInstrucciones, int numLocalidades, int idProceso){
    this.nombre = nombre;
    this.instruccionesTotales = numInstrucciones;
    this.r_localidad = numLocalidades;
    this.idP = idProceso;
  }

  //public void crearProceso(String nombreP){
    //NECESARIA UNA FUNCION QUE VERIFIQUE LA MEMORIA

    //Una vez verificada la memoria empiezan los demas requerimientos
    /*
    nombre = nombreP;
    instrucciones = (int)Math.floor(Math.random()*(30-10+1)+10);
    r_localidad = (int)Math.floor(Math.random()*(4-1+1)+1);
    idP = 1;
    System.out.println("Nombre del proceso: " + nombre);
    System.out.println("Numero de instrucciones: " + instrucciones);
    System.out.println("Numero de localidades: " + r_localidad);
    System.out.println("Id de proceso: " + idP);
    */
    //NECESARIA FUNCION PARA INTRODUCIR EL PROCESO A LA COLA DE CREADOS
  }
