/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerchat;

import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author elli_matteo
 */
public class ThredAscolto extends Thread{
    //rimane in ascolto whiletrue credo
    
    DatiCondivisi dati = new DatiCondivisi();
    
    @Override
    public void run(){
        
        String ip = dati.frame.getTXTIP();
        dati.setIP(ip);
            
        while (true) {
            
            dati.setByte();
            try {
                dati.ricevi();
            } catch (IOException ex) {
                Logger.getLogger(ThredAscolto.class.getName()).log(Level.SEVERE, null, ex);
            }

            dati.setCampi();
            String scelta = dati.trovaScelta();
            String[] campi = dati.trovaCampi();
            
            switch (scelta) 
            {
                case "c": 
                    
                    break;
                case "y":
                    
                    break;
                case "n":
                    
                    break;
                case "m":
                    dati.aggiungiListaRicevi(campi[1]);
                    break;
                case "d":
                    
                    break;
            }  
        }
    }
}
