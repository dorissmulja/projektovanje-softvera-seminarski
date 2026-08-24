/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.KategorijaClana;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smulj
 */
public class ModelTabeleKategorije extends AbstractTableModel{
    List<KategorijaClana> lista;
    String[] kolone={"id", "naziv", "cena"};

    public ModelTabeleKategorije(List<KategorijaClana> lista) {
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
        KategorijaClana k=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return k.getIdKategorijaClana();
            case 1: return k.getNaziv();
            case 2: return k.getCenaClanarine()+"";
            
            default: return "n/a";   
        }
    }

    public List<KategorijaClana> getLista() {
        return lista;
    }

    public void setLista(List<KategorijaClana> lista) {
        this.lista = lista;
    }

    public void pretrazi(String naziv) {
        List<KategorijaClana> filteredList=(List<KategorijaClana>) lista.stream()
                .filter(k-> (naziv==null || naziv.isEmpty() || k.getNaziv().toLowerCase().contains(naziv.toLowerCase())))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
        
    }
}
