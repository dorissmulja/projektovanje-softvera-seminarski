/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.knjiga;

import domen.Knjiga;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class AzurirajKnjiguSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Knjiga)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da doda");
        }
        Knjiga k=(Knjiga) objekat;
        //ovdje dodajemo ogranicenja iz dokumentacije
        if(k.getNaziv()==null || k.getNaziv().isEmpty() || k.getNaziv().length()<3){
            throw new Exception("Poruka sa dijagrama sekvenci: greska ime");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.edit(objekat);
    }
    
}
