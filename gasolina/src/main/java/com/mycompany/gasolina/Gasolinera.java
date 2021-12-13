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
public class Gasolinera {

    public static final int nCoches = 10;
    public static final int T = 20;//Intervalo
    public static final int nBombas = 5;

    public static void main(String args[]){

        Usuario coches[] = new Usuario[nCoches];
        Bomba bombas[] = new Bomba[nBombas];
        Semaphore s1[] = new Semaphore[nBombas];
        Semaphore pago = new Semaphore(1);
        Thread t[] = new Thread[nCoches];
        for (int i = 0; i < nBombas; i++) {
            s1[i] = new Semaphore(1);
            bombas[i] = new Bomba(i, 50000, 50000, s1[i]);
        }

        for (int i = 0; i < nCoches; i++) {
            coches[i] = new Usuario(i, 100, bombas[i], pago);
            t[i] = new Thread(coches[i]);
            t[i].start();
        }
        try {
            Thread.sleep(200000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gasolinera.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < nCoches; i++) {
            t[i].interrupt();
        }

    }
}
