/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author smulj
 */
public class TerminDezurstva implements ApstraktniDomenskiObjekat{
    private int idTerminDezurstva;
    private String danUNedelji;
    private int smena;

    public TerminDezurstva() {
    }

    public TerminDezurstva(int idTerminDezurstva, String danUNedelji, int smena) {
        this.idTerminDezurstva = idTerminDezurstva;
        this.danUNedelji = danUNedelji;
        this.smena = smena;
    }

    public int getIdTerminDezurstva() {
        return idTerminDezurstva;
    }

    public void setIdTerminDezurstva(int idTerminDezurstva) {
        this.idTerminDezurstva = idTerminDezurstva;
    }

    public String getDanUNedelji() {
        return danUNedelji;
    }

    public void setDanUNedelji(String danUNedelji) {
        this.danUNedelji = danUNedelji;
    }

    public int getSmena() {
        return smena;
    }

    public void setSmena(int smena) {
        this.smena = smena;
    }


    @Override
    public String toString() {
        return  danUNedelji + ", smena: " + smena + '.';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TerminDezurstva other = (TerminDezurstva) obj;
        return this.idTerminDezurstva == other.idTerminDezurstva;
    }


    @Override
    public String vratiNazivTabele() {
        return "termin_dezurstva";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idTerminDezurstva=rs.getInt("termin_dezurstva.idTerminDezurstva");
            String danUNedelji=rs.getString("termin_dezurstva.danUNedelji");
            int smena=rs.getInt("termin_dezurstva.smena");
            
            TerminDezurstva td=new TerminDezurstva(idTerminDezurstva, danUNedelji, smena);
            lista.add(td);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "danUNedelji, smena";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+danUNedelji+"', "+smena;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "termin_dezurstva.idTerminDezurstva="+idTerminDezurstva;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "danUNedelji='"+danUNedelji+"', trajanjeDezurstva="+smena;
    }
    
    
}
