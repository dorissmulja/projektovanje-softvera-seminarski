/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author smulj
 */
public class Iznajmljivanje implements ApstraktniDomenskiObjekat{
    private int idIznajmljivanje;
    private Zaposleni zaposleni;
    private ClanBiblioteke clanBiblioteke;
    private String napomena;
    private Date datumIznajmljivanja;
    private Date rokVracanja;
    private List<StavkaIznajmljivanja> stavke;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, Zaposleni zaposleni, ClanBiblioteke clanBiblioteke, String napomena, Date datumIznajmljivanja, Date rokVracanja, List<StavkaIznajmljivanja> stavke) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.zaposleni = zaposleni;
        this.clanBiblioteke = clanBiblioteke;
        this.napomena = napomena;
        this.datumIznajmljivanja = datumIznajmljivanja;
        this.rokVracanja = rokVracanja;
        this.stavke = stavke;
    }

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public ClanBiblioteke getClanBiblioteke() {
        return clanBiblioteke;
    }

    public void setClanBiblioteke(ClanBiblioteke clanBiblioteke) {
        this.clanBiblioteke = clanBiblioteke;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Date getDatumIznajmljivanja() {
        return datumIznajmljivanja;
    }

    public void setDatumIznajmljivanja(Date datumIznajmljivanja) {
        this.datumIznajmljivanja = datumIznajmljivanja;
    }

    public Date getRokVracanja() {
        return rokVracanja;
    }

    public void setRokVracanja(Date rokVracanja) {
        this.rokVracanja = rokVracanja;
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
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
        final Iznajmljivanje other = (Iznajmljivanje) obj;
        return this.idIznajmljivanje == other.idIznajmljivanje;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanje=" + idIznajmljivanje + ", zaposleni=" + zaposleni + ", clanBiblioteke=" + clanBiblioteke + ", napomena=" + napomena + ", datumIznajmljivanja=" + datumIznajmljivanja + ", rokVracanja=" + rokVracanja + ", stavke=" + stavke + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "iznajmljivanje";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idIznajmljivanje=rs.getInt("iznajmljivanje.idIznajmljivanje");
            int idZaposlen=rs.getInt("iznajmljivanje.zaposleni");
            String imeZap=rs.getString("zaposleni.ime");
            String prezimeZap=rs.getString("zaposleni.prezime");
            String emailZap=rs.getString("zaposleni.email");
            String korisnickoImeZap=rs.getString("zaposleni.korisnickoIme");
            String sifraZap=rs.getString("zaposleni.sifra");
            
            Zaposleni z=new Zaposleni(idZaposlen, imeZap, prezimeZap, emailZap, korisnickoImeZap, sifraZap);
            
            int idClan=rs.getInt("iznajmljivanje.clanBiblioteke");
            String imeClan=rs.getString("clan_biblioteke.ime");
            String prezimeclan=rs.getString("clan_biblioteke.prezime");
            String emailClan=rs.getString("clan_biblioteke.email");
            Date datumUclanjenja=rs.getDate("clan_biblioteke.datumUclanjenja");
            Date datumIsteka=rs.getDate("clan_biblioteke.datumIsteka");
            
            int idKategorije=rs.getInt("clan_biblioteke.kategorijaClana");
            String nazivKat=rs.getString("kategorija_clana.naziv");
            double cenaKat=rs.getDouble("kategorija_clana.cenaClanarine");
            KategorijaClana kategorija=new KategorijaClana(idKategorije, nazivKat, cenaKat);
            
            ClanBiblioteke clan=new ClanBiblioteke(idClan, imeClan, prezimeclan, emailClan, datumUclanjenja, datumIsteka, kategorija);
                        
            
            Date datumIznajmljivanja=rs.getDate("iznajmljivanje.datumIznajmljivanja");
            Date datumRokVracanja=rs.getDate("iznajmljivanje.rokVracanja");
            
            String napomena=rs.getString("iznajmljivanje.napomena");

            Iznajmljivanje iz=new Iznajmljivanje(idIznajmljivanje, z, clan, napomena, datumIznajmljivanja, datumRokVracanja, new ArrayList<>());

            lista.add(iz);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "zaposleni, clanBiblioteke, napomena, datumIznajmljivanja, rokVracanja";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return zaposleni.getIdZaposlen()+", "+clanBiblioteke.getIdClan()+", '"+napomena+"', '"+sdf.format(datumIznajmljivanja) +"', '"+sdf.format(rokVracanja) +"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "iznajmljivanje.idIznajmljivanje="+idIznajmljivanje;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "zaposleni="+zaposleni.getIdZaposlen()+", clanBiblioteke="+clanBiblioteke.getIdClan()+", napomena='"+napomena+"' ,datumIznajmljivanja='"+sdf.format(datumIznajmljivanja) +"' ,rokVracanja='"+sdf.format(rokVracanja) +"'";
    }
    
    
    
    
}
