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
import java.util.Objects;

/**
 *
 * @author smulj
 */
public class ZaposleniTermin implements ApstraktniDomenskiObjekat{
    private Zaposleni zaposleni;
    private TerminDezurstva termin;
    private Date datumDezurstva;

    public ZaposleniTermin() {
    }

    public ZaposleniTermin(Zaposleni zaposleni, TerminDezurstva termin, Date datumDezurstva) {
        this.zaposleni = zaposleni;
        this.termin = termin;
        this.datumDezurstva = datumDezurstva;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    public TerminDezurstva getTermin() {
        return termin;
    }

    public void setTermin(TerminDezurstva termin) {
        this.termin = termin;
    }

    public Date getDatumDezurstva() {
        return datumDezurstva;
    }

    public void setDatumDezurstva(Date datumDezurstva) {
        this.datumDezurstva = datumDezurstva;
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
        final ZaposleniTermin other = (ZaposleniTermin) obj;
        if (!Objects.equals(this.zaposleni, other.zaposleni)) {
            return false;
        }
        if (!Objects.equals(this.termin, other.termin)) {
            return false;
        }
        return Objects.equals(this.datumDezurstva, other.datumDezurstva);
    }

    @Override
    public String toString() {
        return "ZaposleniTermin{" + "zaposleni=" + zaposleni + ", termin=" + termin + ", datumDezurstva=" + datumDezurstva + '}';
    }

    @Override
    public String vratiNazivTabele() {
        return "zaposleni_termin";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idZaposlen=rs.getInt("zaposleni_termin.zaposleni");
            String ime=rs.getString("zaposleni.ime");
            String prezime=rs.getString("zaposleni.prezime");
            String email=rs.getString("zaposleni.email");
            String korisnickoIme=rs.getString("zaposleni.korisnickoIme");
            String sifra=rs.getString("zaposleni.sifra");
            
            Zaposleni z=new Zaposleni(idZaposlen, ime, prezime, email, korisnickoIme, sifra);
            
            Date datum=rs.getDate("zaposleni_termin.datumDezurstva");
            
            int idTerminDezurstva=rs.getInt("zaposleni_termin.termin");
            String danUNedelji=rs.getString("termin_dezurstva.danUNedelji");
            int smena=rs.getInt("termin_dezurstva.smena");

            TerminDezurstva t=new TerminDezurstva(idTerminDezurstva, danUNedelji, smena);
            
            ZaposleniTermin zt=new ZaposleniTermin(z, t, datum);
            lista.add(zt);
        }
        return lista;
        
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "zaposleni, termin, datumDezurstva";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return zaposleni.getIdZaposlen()+", "+termin.getIdTerminDezurstva()+ ", '" +sdf.format(datumDezurstva) + "'" ;
    }

    @Override
    public String vratiPrimarniKljuc() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "zaposleni_termin.zaposleni="+zaposleni.getIdZaposlen()+" AND zaposleni_termin.termin="+termin.getIdTerminDezurstva()+" AND zaposleni_termin.datumDezurstva= '"+sdf.format(datumDezurstva)+"'";
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return "zaposleni="+zaposleni.getIdZaposlen()+", termin="+termin.getIdTerminDezurstva()+", datumDezurstva='"+sdf.format(datumDezurstva)+"'";
    }
    
    
}
