/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package peertopeerchat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author elli_matteo
 */
public class DatiCondivisi {
    //va beh questi sono i dati niente da dire   
    DatagramSocket Server;
    DatagramPacket p;
    String[] campi;
    myFrame frame;
    int porta=0;
    String ip ="";
    List<String> listaRicevi = new ArrayList<String>();
    List<String> listaInvia = new ArrayList<String>();
    
    public DatiCondivisi(){
        //boh
    }
    
    public void setServer() throws SocketException{
        //Server = new DatagramSocket(666); 
        InetSocketAddress address = new InetSocketAddress(getIP(), 12345);
        Server.bind(address);
    }
    
    public void setPorta(int p){
        porta = p;
    }
    
    public int getPorta(){
        return porta;
    }
    
    public DatagramSocket getServer(){
        return Server;
    }
    
    public DatagramPacket getPacket(){
        return p;
    }
    
    public void setByte(){
        byte[] Bytedata = new byte[1024];
        p = new DatagramPacket(Bytedata, Bytedata.length);
    }
    
    public void ricevi() throws IOException{
        Server.receive(p);
    }
    
    public void aggiungiListaRicevi(String s){
        listaRicevi.add(s);
    }
    
    public void aggiungiListaInvia(String s){
        listaInvia.add(s);
    }
    
    public void setCampi(){
        String dati = new String(p.getData(), 0, p.getLength()); //dati del pacchetto
        campi = dati.split(";");        
    }
    
    public String trovaScelta(){
        String scelta = campi[0];
        return scelta;
    }
    
    public String[] trovaCampi(){
        return campi;
    }
    
    public void setIP(String s){
        ip = s;
    }
    
    public String getIP(){
        return ip;
    }
    
    public List<String> getListaRicevi() {
        return listaRicevi;
    }

    public void setListaRicevi(List<String> listaRicevi) {
        this.listaRicevi = listaRicevi;
    }

    public List<String> getListaInvia() {
        return listaInvia;
    }
    
    public void setListaInvia(List<String> listaInvia) {
        this.listaInvia = listaInvia;
    }
    
    public void InviaPacchetto(DatagramSocket Server, InetAddress address, int port, String ris) throws IOException {
        byte[] dati = ris.getBytes(); //trasforma la stringa in byte
        DatagramPacket pacchetto = new DatagramPacket(dati, dati.length, address, port);
        Server.send(pacchetto);
    }
}
