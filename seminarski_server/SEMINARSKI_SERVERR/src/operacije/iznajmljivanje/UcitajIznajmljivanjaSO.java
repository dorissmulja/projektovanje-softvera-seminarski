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
public class UcitajIznajmljivanjaSO extends ApstraktnaGenerickaOperacija {

    List<Iznajmljivanje> iznajmljivanja;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov=" JOIN zaposleni ON iznajmljivanje.zaposleni=zaposleni.idZaposlen JOIN"
                + " clan_biblioteke ON iznajmljivanje.clanBiblioteke=clan_biblioteke.idClan "
                + "JOIN KATEGORIJA_CLANA ON CLAN_BIBLIOTEKE.kategorijaClana=kategorija_clana.idKategorijaClana"; 
        iznajmljivanja=broker.getAll(new Iznajmljivanje(), uslov);
    }

    public List<Iznajmljivanje> getIznajmljivanja() {
        return iznajmljivanja;
    }
    
    
}
