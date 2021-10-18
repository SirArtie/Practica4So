/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Artie
 */
public class Utilidades {
  /*
  La funcion a continuacion, se utiliza para un menu recursivo, de manera corta,
  pide un numero hasta que el numero coincida con el rango señalado, como parametros
  recibe una cadena n, que es la cadena del menu, y un entero m, que es una constante
  e indica los rangos del menu del 1 al m
  */
    static int rMenu(String n,int m){
        int op;
        do{
           System.out.println(n);
           Scanner read = new Scanner(System.in);
           op = read.nextInt();
        }while(op<0 && op>m);
        return op;
    }
    /*
    Esta funcion es una forma general de pedir un entero y devolverlo, especialmente
    util cuando se necesita pedir muchas veces numeros enteros
    */
    static int giveAInt(){
        System.out.println("Dame el dato:");
        Scanner read = new Scanner(System.in);
        int i = read.nextInt();
        return i;
    }

    static String giveAString(String mensaje){
      System.out.println(mensaje);
      Scanner read = new Scanner(System.in);
      String s = read.nextLine();
      return s;
    }
}
