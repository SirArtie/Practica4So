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
    static int rMenu(String n,int m){
        int op;
        do{
           System.out.println(n);
           Scanner read = new Scanner(System.in);
           op = read.nextInt();
        }while(op<0 && op>m);
        return op;
    }
    
    static int giveAInt(){
        System.out.println("Dame el dato:");
        Scanner read = new Scanner(System.in);
        int i = read.nextInt();
        return i;
    }
    
    static String giveAString(){
        System.out.println("Dame el dato:");
        Scanner read = new Scanner(System.in);
        String s = read.nextLine();
        return s;
    }
    
    static boolean isSort(String nF){
        double elemento , validar=0;
        File f = new File(nF); 
        try(Scanner read = new Scanner(f).useDelimiter(",")){
            while(read.hasNext()){
                if(read.hasNextDouble()){
                    elemento = read.nextDouble();
                    if(validar<elemento)
                        validar=elemento;
                    else if(validar>elemento)
                        return false;
                }
            }
        }
        catch (FileNotFoundException e){
          System.out.println(e.toString());
        } 
        catch (Exception e){
          System.out.println(e.toString());
        }
        return true;
   }
    
    static void printArray(ArrayList arr){
            int n = arr.size();
            for (int i=0; i < n; ++i)
                System.out.print(arr.get(i)+" ");
            System.out.println();
        }
    
    
    }

