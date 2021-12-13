/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gasolina;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DPDAN
 */
public class Usuario implements Runnable {

    private int n;
    private final int lCapacidadMaxima;
    private int lIntroducido;
    private Bomba b;
    private Semaphore colaPago;
    private double pago;
    

    Usuario(int n, int c, Bomba b, Semaphore sefa) {
        this.n = n;
        this.lCapacidadMaxima = c;
        this.lIntroducido = c;
        this.b = b;
        this.colaPago = colaPago;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getlCapacidadMaxima() {
        return lCapacidadMaxima;
    }

    public int getlIntroducido() {
        return lIntroducido;
    }

    public void setlIntroducido(int lIntroducido) {
        this.lIntroducido = lIntroducido;
    }

    public Bomba getB() {
        return b;
    }

    public void setB(Bomba b) {
        this.b = b;
    }

    public void run() {
        System.out.println("Al coche " + n + " se le ha sido asignado la bomba " + b.getId());
        try {
            b.consumir(this);
        } catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        pago = (double) b.getLitros()* 2.584; // Precio por Litro de comustible
        try {
            colaPago.wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Usuario "+ n + ", " + pago + " a pagar");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        colaPago.notify();
    }
}
