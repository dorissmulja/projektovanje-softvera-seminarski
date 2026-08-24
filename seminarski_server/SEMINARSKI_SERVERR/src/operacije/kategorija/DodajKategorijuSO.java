/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kategorija;

import domen.KategorijaClana;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class DodajKategorijuSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof KategorijaClana)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da doda");
        }
        KategorijaClana k=(KategorijaClana) objekat;
        //ovdje dodajemo ogranicenja iz dokumentacije
        if(k.getNaziv()==null || k.getNaziv().isEmpty() || k.getNaziv().length()<3){
            throw new Exception("Poruka sa dijagrama sekvenci: greska naziv");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((KategorijaClana) objekat);
    }
}
