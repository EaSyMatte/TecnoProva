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
        try {
            dati.getIP();
            //List<Stazione> ListaStazione = new ArrayList<Stazione>();
        } catch (SocketException ex) {
            Logger.getLogger(ThredAscolto.class.getName()).log(Level.SEVERE, null, ex);
        }

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
                case "1": 
                    Stazione stazione = new Stazione(campi[1]);
                    dati.aggiungi(stazione);
                    break;

                case "2":
                    Stazione stazione1 = new Stazione();
                    int tempMedia = stazione1.calcoloTempMedia(dati.getLista());
                    int umiditaMedia = stazione1.calcoloUmiditaMedia(dati.getLista());
                    int tempMax = stazione1.findMax(dati.getLista());
                    int tempMin = stazione1.findMin(dati.getLista());
                    int temp5 = stazione1.checkTempSottoMedia(dati.getLista(), tempMedia);
                    String s = Integer.toString(tempMedia) + ";" + Integer.toString(umiditaMedia) + ";" + Integer.toString(tempMax) + ";" + Integer.toString(tempMin) + ";" + Integer.toString(temp5);
                {
                    try {
                        dati.InviaPacchetto(dati.getServer(), dati.getPacket().getAddress(), dati.getPacket().getPort(), s); //invio il pacchetto con dentro il risultato e la lista
                    } catch (IOException ex) {
                        Logger.getLogger(ThreadServer.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                    
            }  
        }
    }
}
