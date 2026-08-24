/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.knjiga;

import domen.Knjiga;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class UcitajKnjigeSO extends ApstraktnaGenerickaOperacija {

    List<Knjiga> knjige;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        knjige=broker.getAll(new Knjiga(), "");
    }

    public List<Knjiga> getKnjige() {
        return knjige;
    }
     
}
