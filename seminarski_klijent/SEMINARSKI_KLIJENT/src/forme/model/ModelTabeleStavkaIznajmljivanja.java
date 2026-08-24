/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.model;

import domen.StavkaIznajmljivanja;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author smulj
 */
public class ModelTabeleStavkaIznajmljivanja extends AbstractTableModel{
   List<StavkaIznajmljivanja> lista;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
    String[] kolone={"RB", "Knjiga", "Datum vracanja", "Cena kazne"};

    public ModelTabeleStavkaIznajmljivanja(List<StavkaIznajmljivanja> lista) {
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
        StavkaIznajmljivanja st=lista.get(rowIndex);
        switch(columnIndex){
            case 0: return st.getRb();
            case 1: return st.getKnjiga();
            case 2:
                if(st.getDatumVracanja()==null){
                    return "Nije jos vracena";
                }
                return sdf.format(st.getDatumVracanja());
            case 3: return st.getCenaKazne();
            
            default: return "n/a";   
        }
    }

    public List<StavkaIznajmljivanja> getLista() {
        return lista;
    }

    public void setLista(List<StavkaIznajmljivanja> lista) {
        this.lista = lista;
    }

//    public void pretrazi(String ime, String prezime, String danUNedelji, Integer smena, Date datum) {
//        List<ZaposleniTermin> filteredList=lista.stream()
//                .filter(zt-> (ime==null || ime.isEmpty() || zt.getZaposleni().getIme().toLowerCase().contains(ime.toLowerCase())))
//                .filter(zt-> (prezime==null || prezime.isEmpty() || zt.getZaposleni().getPrezime().toLowerCase().contains(prezime.toLowerCase())))
//                .filter(zt-> (danUNedelji==null || danUNedelji.isEmpty() || zt.getTermin().getDanUNedelji().toLowerCase().contains(danUNedelji.toLowerCase())))
//                .filter(zt-> (smena==null || zt.getTermin().getSmena()==smena))
//                .filter(zt -> (datum == null || zt.getDatumDezurstva().equals(datum)))
//                .collect(Collectors.toList());
//        this.lista=filteredList;
//        fireTableDataChanged();
//        
//    } 

    public void dodajStavku(StavkaIznajmljivanja stavkaDodaj) {
        int trenutniRB=lista.size()+1;
        stavkaDodaj.setRb(trenutniRB);
        lista.add(stavkaDodaj);
        fireTableDataChanged();
    }

    public void obrisiStavku(StavkaIznajmljivanja st) {
        lista.remove(st);
        fireTableDataChanged();
    }
}
