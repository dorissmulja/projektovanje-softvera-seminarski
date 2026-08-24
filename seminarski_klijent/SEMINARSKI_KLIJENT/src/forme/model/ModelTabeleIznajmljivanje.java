/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.Iznajmljivanje;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smulj
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel{
    List<Iznajmljivanje> lista;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    String[] kolone={"ID", "Zaposleni", "Clan", "Datum iznajmljivanja","Rok vracanja","Napomena"};

    public ModelTabeleIznajmljivanje(List<Iznajmljivanje> lista) {
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
        Iznajmljivanje iz=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return iz.getIdIznajmljivanje();
            case 1: return iz.getZaposleni();
            case 2: return iz.getClanBiblioteke();
            case 3: return sdf.format(iz.getDatumIznajmljivanja());
            case 4: return sdf.format(iz.getRokVracanja());
            case 5: return iz.getNapomena();
            
            default: return "n/a";   
        }
    }

    public List<Iznajmljivanje> getLista() {
        return lista;
    }

    public void setLista(List<Iznajmljivanje> lista) {
        this.lista = lista;
    }

    public void pretrazi(Integer ID, String imeZap, String prezimeZap, String imeClana, String prezimeClana, Date datumIznajmljivanja) {
        List<Iznajmljivanje> filteredList=lista.stream()
                .filter(iz-> (ID==null || iz.getIdIznajmljivanje()==ID))                
                .filter(iz-> (imeZap==null || imeZap.isEmpty() || iz.getZaposleni().getIme().toLowerCase().contains(imeZap.toLowerCase())))
                .filter(iz-> (prezimeZap==null || prezimeZap.isEmpty() || iz.getZaposleni().getPrezime().toLowerCase().contains(prezimeZap.toLowerCase())))
                .filter(iz-> (imeClana==null || imeClana.isEmpty() || iz.getClanBiblioteke().getIme().toLowerCase().contains(imeClana.toLowerCase())))
                .filter(iz-> (prezimeClana==null || prezimeClana.isEmpty() || iz.getClanBiblioteke().getPrezime().toLowerCase().contains(prezimeClana.toLowerCase())))
                .filter(iz -> (datumIznajmljivanja == null || iz.getDatumIznajmljivanja().equals(datumIznajmljivanja)))
                .collect(Collectors.toList());
        this.lista=filteredList;
        fireTableDataChanged();
        
    }
}
