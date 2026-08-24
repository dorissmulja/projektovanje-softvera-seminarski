/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.ZaposleniTermin;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smulj
 */
public class ModelTabeleDezurstva extends AbstractTableModel {
    List<ZaposleniTermin> lista;
    String[] kolone={"Zaposleni", "Dan", "Smena", "Datum"};

    public ModelTabeleDezurstva(List<ZaposleniTermin> lista) {
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
        ZaposleniTermin zt=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return zt.getZaposleni().getIme()+" "+zt.getZaposleni().getPrezime();
            case 1: return zt.getTermin().getDanUNedelji();
            case 2: return zt.getTermin().getSmena();
            case 3: return zt.getDatumDezurstva();
            
            default: return "n/a";   
        }
    }

    public List<ZaposleniTermin> getLista() {
        return lista;
    }

    public void setLista(List<ZaposleniTermin> lista) {
        this.lista = lista;
    }

    public void pretrazi(String ime, String prezime, String danUNedelji, Integer smena, Date datum) {
        List<ZaposleniTermin> filteredList=lista.stream()
                .filter(zt-> (ime==null || ime.isEmpty() || zt.getZaposleni().getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(zt-> (prezime==null || prezime.isEmpty() || zt.getZaposleni().getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(zt-> (danUNedelji==null || danUNedelji.isEmpty() || zt.getTermin().getDanUNedelji().toLowerCase().contains(danUNedelji.toLowerCase())))
                .filter(zt-> (smena==null || zt.getTermin().getSmena()==smena))
                .filter(zt -> (datum == null || zt.getDatumDezurstva().equals(datum)))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
        
    }
}
