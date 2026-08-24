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
public class KategorijaClana implements ApstraktniDomenskiObjekat{
    private int idKategorijaClana;
    private String naziv;
    private double cenaClanarine;

    public KategorijaClana() {
    }

    public KategorijaClana(int idKategorijaClana, String naziv, double cenaClanarine) {
        this.idKategorijaClana = idKategorijaClana;
        this.naziv = naziv;
        this.cenaClanarine = cenaClanarine;
    }

    public int getIdKategorijaClana() {
        return idKategorijaClana;
    }

    public void setIdKategorijaClana(int idKategorijaClana) {
        this.idKategorijaClana = idKategorijaClana;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public double getCenaClanarine() {
        return cenaClanarine;
    }

    public void setCenaClanarine(double cenaClanarine) {
        this.cenaClanarine = cenaClanarine;
    }

    @Override
    public String toString() {
        return naziv;
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
        final KategorijaClana other = (KategorijaClana) obj;
        return Objects.equals(this.naziv, other.naziv);
    }

    

    @Override
    public String vratiNazivTabele() {
        return "kategorija_clana";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
            while(rs.next()){
                int idKategorijaClana=rs.getInt("kategorija_clana.idKategorijaClana");
                String naziv=rs.getString("kategorija_clana.naziv");
                double cenaClanarine=rs.getDouble("kategorija_clana.cenaClanarine");
                
                KategorijaClana kc=new KategorijaClana(idKategorijaClana, naziv, cenaClanarine);
                lista.add(kc);    
            }
        return lista;
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, cenaClanarine";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"', "+cenaClanarine;
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "kategorija_clana.idKategorijaClana="+idKategorijaClana;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"', cenaClanarine="+cenaClanarine;
    }
    
    
    
}
