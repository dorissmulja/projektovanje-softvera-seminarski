/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.termin;

import domen.TerminDezurstva;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class DodajTerminSO extends ApstraktnaGenerickaOperacija {
    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof TerminDezurstva)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da doda");
        }
        TerminDezurstva t=(TerminDezurstva) objekat;
        //ovdje dodajemo ogranicenja iz dokumentacije
        if(t.getDanUNedelji()==null || t.getDanUNedelji().isEmpty() || t.getDanUNedelji().length()<3){
            throw new Exception("Poruka sa dijagrama sekvenci: greska ime");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.add((TerminDezurstva) objekat);
    }
}
