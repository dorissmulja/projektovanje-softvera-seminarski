/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.stavke;

import domen.StavkaIznajmljivanja;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class UcitajStavkeSO extends ApstraktnaGenerickaOperacija {

    List<StavkaIznajmljivanja> stavke;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        String uslov=" JOIN knjiga ON stavka_iznajmljivanja.knjiga=knjiga.idKnjiga"
                +" WHERE stavka_iznajmljivanja.iznajmljivanje="+(int)param;
        System.out.println(uslov);
        stavke=broker.getAll(new StavkaIznajmljivanja(), uslov);
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }
    
    
    
}
