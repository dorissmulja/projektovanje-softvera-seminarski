/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.zaposleni;

import domen.Zaposleni;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class KreirajZaposlenogSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Zaposleni)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da doda");
        }
        Zaposleni z=(Zaposleni) objekat;
        //ovdje dodajemo ogranicenja iz dokumentacije
        if(z.getIme()==null || z.getIme().isEmpty() || z.getIme().length()<3){
            throw new Exception("Poruka sa dijagrama sekvenci: greska ime");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((Zaposleni) objekat);
    }
    
}
