/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.clanovi;

import domen.ClanBiblioteke;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class AzurirajClanaSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof ClanBiblioteke)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da doda");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit(objekat);
    }
    
}
