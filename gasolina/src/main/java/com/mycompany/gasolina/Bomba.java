/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gasolina;


import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.SynchronousQueue;


/**
 *
 * @author DPDAN
 */
public class Bomba {
    private int id;
    private int lDiesel;
    private int lGasolina;
    private int litros;
    private int type; // Si es igual a 0 es Diesel y si es 1 es gasolina
    private Semaphore sefa;
    private SynchronousQueue cola;
    
    
    

    Bomba(int id, int lDiesel, int lGasolina, Semaphore sefa) {
        this.id = id;
        this.lDiesel = lDiesel;
        this.lGasolina = lGasolina;
        this.sefa = sefa;       
    }

    public int getlDiesel() {
        return lDiesel;
    }

    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public void setlDiesel(int lDiesel) {
        this.lDiesel = lDiesel;
    }

    public int getlGasolina() {
        return lGasolina;
        
    }

    public void setlGasolina(int lGasolina) {
        this.lGasolina = lGasolina;
    }
    public int getId() {
        return id;
    }
    public void recargar(){
        lGasolina = 2000;
        lDiesel = 2000;
    }
    
    public void consumir(Usuario u) throws InterruptedException{
        
        sefa.wait();
        
        boolean ok = false;
        Scanner s = new Scanner(System.in);
        while (!ok) {
            System.out.println("El usuario " + u.getN() + "debe elegir que gasolina echar. Pulse 'D' o 'G'");
            if (s.nextLine() == "D") {
                ok = true;
                type = 0;
            } else if (s.nextLine() == "G") {
                ok = true;
                type = 1;
            } else {
                System.out.println("Error, no se ha introducido el dato esperado");
                type = -1;

            }
            
            Thread.sleep(2000);
        }
        litros = u.getlCapacidadMaxima() - u.getlIntroducido();
        if (type == 0) {
            lDiesel = -litros;
        } else if (type == 1) {
            lGasolina = -litros;
        } else {
            System.out.println("Este mensaje no deberia de salir");
        }
        u.setlIntroducido(u.getlCapacidadMaxima());
        System.out.println("Se ha rellenado el deposito del coche " + litros + "L");

        sefa.notify();
    }
}
