/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Knjiga;
import domen.Zaposleni;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smulj
 */
public class ModelTabeleKnjige extends AbstractTableModel {
    List<Knjiga> lista;
    String[] kolone={"id", "naziv", "autor", "izdavacka kuca"};

    public ModelTabeleKnjige(List<Knjiga> lista) {
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
        Knjiga k=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return k.getIdKnjiga();
            case 1: return k.getNaziv();
            case 2: return k.getAutor();
            case 3: return k.getIzdavackaKuca();
            
            default: return "n/a";   
        }
    }

    public List<Knjiga> getLista() {
        return lista;
    }

    public void setLista(List<Knjiga> lista) {
        this.lista = lista;
    }

    public void pretrazi(String naziv, String autor) {
        List<Knjiga> filteredList=(List<Knjiga>) lista.stream()
                .filter(z-> (naziv==null || naziv.isEmpty() || z.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .filter(z-> (autor==null || autor.isEmpty() || z.getAutor().toLowerCase().contains(autor.toLowerCase())))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
        
    }
}
