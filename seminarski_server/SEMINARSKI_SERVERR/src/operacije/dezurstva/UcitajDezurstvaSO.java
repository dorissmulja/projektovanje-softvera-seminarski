/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.dezurstva;

import domen.ClanBiblioteke;
import domen.ZaposleniTermin;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class UcitajDezurstvaSO extends ApstraktnaGenerickaOperacija {
       List<ZaposleniTermin> dezurstva;

    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov="JOIN ZAPOSLENI ON zaposleni_termin.zaposleni=zaposleni.idZaposlen JOIN"
                + " TERMIN_DEZURSTVA ON zaposleni_termin.termin=termin_dezurstva.idTerminDezurstva"; 
        dezurstva=broker.getAll(new ZaposleniTermin(), uslov);
    }

    public List<ZaposleniTermin> getDezurstva() {
        return dezurstva;
    }
    
    
}
