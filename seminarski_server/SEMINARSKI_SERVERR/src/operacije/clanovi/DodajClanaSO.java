/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.clanovi;

import domen.ClanBiblioteke;
import domen.KategorijaClana;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class DodajClanaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof ClanBiblioteke)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da doda");
        }
        ClanBiblioteke c=(ClanBiblioteke) objekat;
        //ovdje dodajemo ogranicenja iz dokumentacije
       
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((ClanBiblioteke) objekat);
    }
    
}
