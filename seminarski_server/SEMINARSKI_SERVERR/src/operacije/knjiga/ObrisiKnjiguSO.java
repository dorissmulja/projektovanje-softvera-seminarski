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
public class ObrisiKnjiguSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Knjiga)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da orbise");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        broker.delete((Knjiga)objekat);
    }
    
}
