/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import niti.ObradaKlijentskihZahteva;

/**
 *
 * @author smulj
 */
public class Server extends Thread{
    boolean kraj=false;
    ServerSocket serverSoket;
    ObradaKlijentskihZahteva okz;
    List<ObradaKlijentskihZahteva> klijenti=new ArrayList<>();
    
    @Override
    public void run() {
        try {
            serverSoket=new ServerSocket(9000);
            while(!kraj){
                Socket s=serverSoket.accept();
                System.out.println("Klijent je povezan");
                System.out.println("Socket status: " + s.isClosed());

                okz=new ObradaKlijentskihZahteva(s);
                System.out.println("okz je napravljen");
                klijenti.add(okz);
                okz.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void zaustaviServer(){
        kraj=true;
        try {
            for(ObradaKlijentskihZahteva k: klijenti){
                k.prekiniNit();
            }
            serverSoket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    
}
