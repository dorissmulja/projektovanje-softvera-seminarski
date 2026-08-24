/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class ObrisiIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Iznajmljivanje)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da obrise iznajmljivanje");
        }
        //dodatna ogranicenja
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Iznajmljivanje i=(Iznajmljivanje) objekat;
        
        List<StavkaIznajmljivanja> stavke=i.getStavke();
        
        for(StavkaIznajmljivanja st: stavke){
            broker.delete(st);
        }
                
        broker.delete((Iznajmljivanje)objekat);
    }
    
}
