/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.iznajmljivanje;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import domen.ZaposleniTermin;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class DodajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object objekat) throws Exception {
        if(objekat==null || !(objekat instanceof Iznajmljivanje)){
            throw new Exception("Poruka sa dijagrama sekvenci: sistem nije mogao da doda");
        }
        //dodatna ogranicenja
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        Iznajmljivanje iz=(Iznajmljivanje) objekat;
        int idIznajmljivanje=broker.addReturnKey(iz);
        
        List<StavkaIznajmljivanja> stavke=iz.getStavke();
        for(StavkaIznajmljivanja st:stavke){
            st.setIznajmljivanje(idIznajmljivanje);
            broker.add(st);
        }
    }
    
}
