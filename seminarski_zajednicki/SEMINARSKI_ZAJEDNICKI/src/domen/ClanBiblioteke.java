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
public class ClanBiblioteke implements ApstraktniDomenskiObjekat{
   private int idClan;
    private String ime;
    private String prezime;
    private String email;
    private Date datumUclanjenja;
    private Date datumIsteka;
    private KategorijaClana kategorijaClana;

    public ClanBiblioteke() {
    }

    public ClanBiblioteke(int idClan, String ime, String prezime, String email, Date datumUclanjenja, Date datumIsteka, KategorijaClana kategorijaClana) {
        this.idClan = idClan;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.datumUclanjenja = datumUclanjenja;
        this.datumIsteka = datumIsteka;
        this.kategorijaClana= kategorijaClana;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
   
    
    public int getIdClan() {
        return idClan;
    }

    public void setIdClan(int idClan) {
        this.idClan = idClan;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatumUclanjenja() {
        return datumUclanjenja;
    }

    public void setDatumUclanjenja(Date datumUclanjenja) {
        this.datumUclanjenja = datumUclanjenja;
    }

    public Date getDatumIsteka() {
        return datumIsteka;
    }

    public void setDatumIsteka(Date datumIsteka) {
        this.datumIsteka = datumIsteka;
    }

    public KategorijaClana getKategorijaClana() {
        return kategorijaClana;
    }

    public void setKategorijaClana(KategorijaClana kategorijaClana) {
        this.kategorijaClana = kategorijaClana;
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
        final ClanBiblioteke other = (ClanBiblioteke) obj;
        return this.idClan == other.idClan;
    }

    @Override
    public String vratiNazivTabele() {
        return "clan_biblioteke";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int clanId=rs.getInt("clan_biblioteke.idClan");
            String ime=rs.getString("clan_biblioteke.ime");
            String prezime=rs.getString("clan_biblioteke.prezime");
            String email=rs.getString("clan_biblioteke.email");
            Date datumUclanjenja=rs.getDate("clan_biblioteke.datumUclanjenja");
            Date datumIsteka=rs.getDate("clan_biblioteke.datumIsteka");
            int idKategorije=rs.getInt("clan_biblioteke.kategorijaClana");
            String nazivKat=rs.getString("kategorija_clana.naziv");
            double cenaKat=rs.getDouble("kategorija_clana.cenaClanarine");
            KategorijaClana kategorija=new KategorijaClana(idKategorije, nazivKat, cenaKat);
            
            ClanBiblioteke clan=new ClanBiblioteke(clanId, ime, prezime, email, datumUclanjenja, datumIsteka, kategorija);
            lista.add(clan);
        }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, email, datumUclanjenja, datumIsteka, kategorijaClana";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return "'" + ime + "', '" +
                 prezime + "', '" +
                 email + "', '" +
                 sdf.format(datumUclanjenja) + "', '" +
                 sdf.format(datumIsteka) + "', " +
                 kategorijaClana.getIdKategorijaClana();
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "clan_biblioteke.idClan="+idClan;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "ime='"+ime+"', prezime='"+prezime+"', email='"+email+"', datumUclanjenja='"+sdf.format(datumUclanjenja)+"', datumIsteka='"+sdf.format(datumIsteka)+"', kategorijaClana="+kategorijaClana.getIdKategorijaClana();
    }
    
    
    
    
}
