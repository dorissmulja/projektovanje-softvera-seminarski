/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author smulj
 */
public class Zaposleni implements ApstraktniDomenskiObjekat{
   private int idZaposlen;
   private String ime;
   private String prezime;
   private String email;
   private String korisnickoIme;
   private String sifra;

    public Zaposleni() {
    }

    public Zaposleni(int idZaposlen, String ime, String prezime, String email, String korisnickoIme, String sifra) {
        this.idZaposlen = idZaposlen;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
    }

    public int getIdZaposlen() {
        return idZaposlen;
    }

    public void setIdZaposlen(int idZaposlen) {
        this.idZaposlen = idZaposlen;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Zaposleni other = (Zaposleni) obj;
        if (!Objects.equals(this.korisnickoIme, other.korisnickoIme)) {
            return false;
        }
        return Objects.equals(this.sifra, other.sifra);
    }

    

   
  

    @Override
    public String vratiNazivTabele() {
        return "zaposleni";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idZaposlen=rs.getInt("zaposleni.idZaposlen");
            String ime=rs.getString("zaposleni.ime");
            String prezime=rs.getString("zaposleni.prezime");
            String email=rs.getString("zaposleni.email");
            String korisnickoIme=rs.getString("zaposleni.korisnickoIme");
            String sifra=rs.getString("zaposleni.sifra");
            
            Zaposleni z=new Zaposleni(idZaposlen, ime, prezime, email, korisnickoIme, sifra);
            lista.add(z);
        }
        
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "ime, prezime, email, korisnickoIme, sifra";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+ime+"', '"+prezime+"', '"+email+"', '"+korisnickoIme+"', '"+sifra+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "zaposleni.idZaposlen="+idZaposlen;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "ime='"+ime+"', prezime='"+prezime+"', email='"+email+"', korisnickoIme='"+korisnickoIme+"', sifra='"+sifra+"'";
    }
  
    
}
