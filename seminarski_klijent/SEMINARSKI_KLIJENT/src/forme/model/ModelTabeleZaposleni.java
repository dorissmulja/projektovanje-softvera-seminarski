/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.ClanBiblioteke;
import domen.Zaposleni;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smulj
 */
public class ModelTabeleZaposleni extends AbstractTableModel {

    List<Zaposleni> lista;
    String[] kolone={"id", "ime", "prezime", "email"};

    public ModelTabeleZaposleni(List<Zaposleni> lista) {
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
        Zaposleni z=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return z.getIdZaposlen();
            case 1: return z.getIme();
            case 2: return z.getPrezime();
            case 3: return z.getEmail();
            
            default: return "n/a";   
        }
    }

    public List<Zaposleni> getLista() {
        return lista;
    }

    public void setLista(List<Zaposleni> lista) {
        this.lista = lista;
    }

    public void pretrazi(String ime, String prezime) {
        List<Zaposleni> filteredList=lista.stream()
                .filter(z-> (ime==null || ime.isEmpty() || z.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(z-> (prezime==null || prezime.isEmpty() || z.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
        
    }
    
    
    
}
