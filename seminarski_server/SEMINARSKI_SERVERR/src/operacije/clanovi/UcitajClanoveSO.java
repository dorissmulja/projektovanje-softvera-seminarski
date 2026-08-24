/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package operacije.clanovi;

import domen.ClanBiblioteke;
import java.util.List;
import operacije.ApstraktnaGenerickaOperacija;
import operacije.ApstraktnaGenerickaOperacija;

/**
 *
 * @author smulj
 */
public class UcitajClanoveSO extends ApstraktnaGenerickaOperacija {
    
    List<ClanBiblioteke> clanovi;
    
    @Override
    protected void preduslovi(Object objekat) throws Exception {
    }

    @Override
    protected void izvrsiOperaciju(Object objekat, String kljuc) throws Exception {
        String uslov="JOIN KATEGORIJA_CLANA ON CLAN_BIBLIOTEKE.kategorijaClana=kategorija_clana.idKategorijaClana"; 
        clanovi=broker.getAll(new ClanBiblioteke(), uslov);
    }

    public List<ClanBiblioteke> getClanovi() {
        return clanovi;
    }

    
    
    
    
}
