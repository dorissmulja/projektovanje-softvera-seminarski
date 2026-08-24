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
public class StavkaIznajmljivanja implements ApstraktniDomenskiObjekat{
    private int rb;
    private int iznajmljivanje;
    private Date datumVracanja;
    private double cenaKazne;
    private Knjiga knjiga;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(int rb,int iznajmljivanje, Date datumVracanja, double cenaKazne, Knjiga knjiga) {
        this.iznajmljivanje=iznajmljivanje;
        this.rb = rb;
        this.datumVracanja = datumVracanja;
        this.cenaKazne = cenaKazne;
        this.knjiga=knjiga;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(int iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public Date getDatumVracanja() {
        return datumVracanja;
    }

    public void setDatumVracanja(Date datumVracanja) {
        this.datumVracanja = datumVracanja;
    }

    public double getCenaKazne() {
        return cenaKazne;
    }

    public void setCenaKazne(double cenaKazne) {
        this.cenaKazne = cenaKazne;
    }

    public Knjiga getKnjiga() {
        return knjiga;
    }

    public void setKnjiga(Knjiga knjiga) {
        this.knjiga = knjiga;
    }

    @Override
    public String toString() {
        return "StavkaIznajmljivanja{" + "rb=" + rb + ", iznajmljivanje=" + iznajmljivanje + ", knjiga=" + knjiga + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final StavkaIznajmljivanja other = (StavkaIznajmljivanja) obj;
        if (this.rb != other.rb) {
            return false;
        }
        return Objects.equals(this.iznajmljivanje, other.iznajmljivanje);
    }

    @Override
    public String vratiNazivTabele() {
        return "stavka_iznajmljivanja";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {

        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
        while(rs.next()){
            int idIznajmljivanje=rs.getInt("stavka_iznajmljivanja.iznajmljivanje");
            int rb=rs.getInt("stavka_iznajmljivanja.rb");
            Date datumVracanja=rs.getDate("stavka_iznajmljivanja.datumVracanja");
            double cenaKazne=rs.getDouble("stavka_iznajmljivanja.cenaKazne");
            
                int idKnjiga=rs.getInt("knjiga.idKnjiga");
                String naziv=rs.getString("knjiga.naziv");
                String autor=rs.getString("knjiga.autor");
                String izdavackaKuca=rs.getString("knjiga.izdavackaKuca");

                Knjiga k=new Knjiga(idKnjiga, naziv, autor, izdavackaKuca);
                    
            StavkaIznajmljivanja st=new StavkaIznajmljivanja(rb, idIznajmljivanje, datumVracanja, cenaKazne, k);
            lista.add(st);
        }
        
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "rb, iznajmljivanje, datumVracanja, cenaKazne, knjiga";
    }
//return "'"+naziv+"', "+cenaClanarine;
    @Override
    public String vratiVrednostiZaUbacivanje() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumVracanjaStr = datumVracanja != null ? "'" + sdf.format(datumVracanja) + "'" : "NULL";

        return rb+", "+iznajmljivanje+", "+datumVracanjaStr +", "+cenaKazne+", "+knjiga.getIdKnjiga();
    }

    @Override
    public String vratiPrimarniKljuc() {
       return "stavka_iznajmljivanja.rb="+rb+" AND stavka_iznajmljivanja.iznajmljivanje="+iznajmljivanje;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String datumVracanjaStr = datumVracanja != null ? "'" + sdf.format(datumVracanja) + "'" : "NULL";
        return "datumVracanja="+datumVracanjaStr +" ,cenaKazne="+cenaKazne+", knjiga="+knjiga.getIdKnjiga();
    }
    
    
    
}
