/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerchat;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Elli Matteo
 */
public class ThreadGrafica extends Thread{
    DatiCondivisi dati;

    public ThreadGrafica(DatiCondivisi dati) {
        this.dati = dati;
    }
    
    @Override
    public void run() {
        int i=0;
        String ip = dati.frame.getTXTIP();
        dati.setIP(ip);
        while(true)
        {
            i++;
            dati.frame.setTXTChat(i+"");
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadGrafica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
