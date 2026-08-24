/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domen;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author smulj
 */
public class Knjiga implements ApstraktniDomenskiObjekat{
    private int idKnjiga;
    private String naziv;
    private String autor;
    private String izdavackaKuca;

    public Knjiga() {
    }

    public Knjiga(int idKnjiga, String naziv, String autor, String izdavackaKuca) {
        this.idKnjiga = idKnjiga;
        this.naziv = naziv;
        this.autor = autor;
        this.izdavackaKuca = izdavackaKuca;
    }

    public long getIdKnjiga() {
        return idKnjiga;
    }

    public void setIdKnjiga(int idKnjiga) {
        this.idKnjiga = idKnjiga;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIzdavackaKuca() {
        return izdavackaKuca;
    }

    public void setIzdavackaKuca(String izdavackaKuca) {
        this.izdavackaKuca = izdavackaKuca;
    }

    @Override
    public String toString() {
        return "'"+naziv+"', "+autor+", "+izdavackaKuca;
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
        final Knjiga other = (Knjiga) obj;
        return this.idKnjiga == other.idKnjiga;
    }

    @Override
    public String vratiNazivTabele() {
        return "knjiga";
    }

    @Override
    public List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception {
        List<ApstraktniDomenskiObjekat> lista=new ArrayList<>();
            while(rs.next()){
                int idKnjiga=rs.getInt("knjiga.idKnjiga");
                String naziv=rs.getString("knjiga.naziv");
                String autor=rs.getString("knjiga.autor");
                String izdavackaKuca=rs.getString("knjiga.izdavackaKuca");

                Knjiga k=new Knjiga(idKnjiga, naziv, autor, izdavackaKuca);
                lista.add(k);
            }
        return lista;    
    }

    @Override
    public String vratiKoloneZaUbacivanje() {
        return "naziv, autor, izdavackaKuca";
    }

    @Override
    public String vratiVrednostiZaUbacivanje() {
        return "'"+naziv+"', '"+autor+"', '"+izdavackaKuca+"'";
    }

    @Override
    public String vratiPrimarniKljuc() {
        return "knjiga.idKnjiga="+idKnjiga;
    }

    @Override
    public ApstraktniDomenskiObjekat vratiObjekatIzRS(ResultSet rs) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String vratiVrednostiZaIzmenu() {
        return "naziv='"+naziv+"', autor='"+autor+"', izdavackaKuca='"+izdavackaKuca+"'";
    }
    
    
    
    
}
