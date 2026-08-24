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
public class AzurirajIznajmljivanjeSO extends ApstraktnaGenerickaOperacija {

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
        
        //prvo sve stavke azuriramo
        //prvo brisemo sve postojece stavke i dodajemo "nove"
                String uslov=" JOIN knjiga ON stavka_iznajmljivanja.knjiga=knjiga.idKnjiga"
                +" WHERE stavka_iznajmljivanja.iznajmljivanje="+iz.getIdIznajmljivanje();
        System.out.println(uslov);
        List<StavkaIznajmljivanja> stareStavke=broker.getAll(new StavkaIznajmljivanja(), uslov);
        for(StavkaIznajmljivanja st:stareStavke){
            st.setIznajmljivanje(iz.getIdIznajmljivanje());
            broker.delete(st);
        }
        //sad dodajemo novu listu svaki
        List<StavkaIznajmljivanja> noveStavke=iz.getStavke();
        for(StavkaIznajmljivanja st:stareStavke){
            st.setIznajmljivanje(iz.getIdIznajmljivanje());
            broker.add(st);
        }
        //sad dodajemo i racun
        broker.edit(iz);
    }
    
}
