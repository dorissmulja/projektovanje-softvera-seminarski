/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.kategorija;

import domen.KategorijaClana;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class UcitajKategorijeSO extends ApstraktnaGenerickaOperacija {

    List<KategorijaClana> kategorije;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        kategorije=broker.getAll(new KategorijaClana(), "");
    }

    public List<KategorijaClana> getKategorije() {
        return kategorije;
    }
    
    
    
}
