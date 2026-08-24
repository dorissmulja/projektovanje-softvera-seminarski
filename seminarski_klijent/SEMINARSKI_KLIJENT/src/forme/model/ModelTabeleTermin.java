/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.TerminDezurstva;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smulj
 */
public class ModelTabeleTermin extends AbstractTableModel {
  List<TerminDezurstva> lista;
    String[] kolone={"id", "dan", "smena"};

    public ModelTabeleTermin(List<TerminDezurstva> lista) {
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
        TerminDezurstva t=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return t.getIdTerminDezurstva()+"";
            case 1: return t.getDanUNedelji();
            case 2: return t.getSmena()+"";
            
            default: return "n/a";   
        }
    }

    public List<TerminDezurstva> getLista() {
        return lista;
    }

    public void setLista(List<TerminDezurstva> lista) {
        this.lista = lista;
    }

    public void pretrazi(String dan) {
        List<TerminDezurstva> filteredList=(List<TerminDezurstva>) lista.stream()
                .filter(t-> (dan==null || dan.isEmpty() || t.getDanUNedelji().toLowerCase().contains(dan.toLowerCase())))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
        
    }  
}
