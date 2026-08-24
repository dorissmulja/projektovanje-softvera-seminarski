/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.ClanBiblioteke;
import domen.KategorijaClana;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smulj
 */
public class ModelTabeleClanovi extends AbstractTableModel{
    
    List<ClanBiblioteke> lista;
    String[] kolone={"ID", "Ime", "Prezime", "Email", "Datum uclanjenja", "Datum isteka clanarine", "Kategorija clanstva"};

    public ModelTabeleClanovi(List<ClanBiblioteke> lista) {
        this.lista = lista;
    }
    
    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClanBiblioteke clan=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return clan.getIdClan();
            case 1: return clan.getIme();
            case 2: return clan.getPrezime();
            case 3: return clan.getEmail();
            //SIMPLE DATE FORMAT!
            case 4: return clan.getDatumUclanjenja();
            case 5: return clan.getDatumIsteka();
            case 6: return clan.getKategorijaClana().getNaziv();
            default: return "n/a";   
        }
         

    }

    public List<ClanBiblioteke> getLista() {
        return lista;
    }

    public void setLista(List<ClanBiblioteke> lista) {
        this.lista = lista;
    }
    
    public void pretrazi(String ime, String prezime, String nazivKategorije) {
        List<ClanBiblioteke> filteredList=(List<ClanBiblioteke>) lista.stream()
                .filter(c-> (ime==null || ime.isEmpty() || c.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(c-> (prezime==null || prezime.isEmpty() || c.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(c-> (nazivKategorije==null || nazivKategorije.isEmpty() || c.getKategorijaClana().getNaziv().toLowerCase().contains(nazivKategorije.toLowerCase())))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
        
    }
    
}
