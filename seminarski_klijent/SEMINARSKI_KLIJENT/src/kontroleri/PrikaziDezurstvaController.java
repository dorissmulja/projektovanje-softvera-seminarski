/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Zaposleni;
import domen.ZaposleniTermin;
import forme.model.ModelTabeleDezurstva;
import forme.model.ModelTabeleZaposleni;
import forme.zaposleni_termin.PrikaziZaposleniTerminForma;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author smulj
 */
public class PrikaziDezurstvaController {
    private PrikaziZaposleniTerminForma pztf;

    public PrikaziDezurstvaController(PrikaziZaposleniTerminForma pztf) {
        this.pztf = pztf;
        addActionListener();
    }
    
    public void pripremiFormu() {
        List<ZaposleniTermin> dezurstva= komunikacija.Komunikacija.getInstance().ucitajDezurstva();
        ModelTabeleDezurstva mtd=new ModelTabeleDezurstva(dezurstva);
        pztf.getjTableDezurstva().setModel(mtd);
    }
    
    public void otvoriFormu() {
        pripremiFormu();
        pztf.setVisible(true);
    }
    

    private void addActionListener() {
        pztf.addBtnObrisiActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pztf.getjTableDezurstva().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pztf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleDezurstva mtd=(ModelTabeleDezurstva) pztf.getjTableDezurstva().getModel();
                  ZaposleniTermin zt=mtd.getLista().get(red);
                 
                  try {
                      komunikacija.Komunikacija.getInstance().obrisiDezurstvo(zt);
                      JOptionPane.showMessageDialog(pztf, "Sistem je uspesno obrisao zaposlenog");                  
                      pripremiFormu();
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pztf, "Sistem ne moze da obrise zaposlenog");
                  }
              }
              
          }
      });
//        pztf.addBtnAzurirajActionListener(new ActionListener() {
//          @Override
//          public void actionPerformed(ActionEvent e) {
//              int red=pztf.getjTableDezurstva().getSelectedRow();
//              if(red==-1){
//                  JOptionPane.showMessageDialog(pztf, "Nije izabran red");
//                  return;
//              }else{
//                  ModelTabeleDezurstva mtd=(ModelTabeleDezurstva) pztf.getjTableDezurstva().getModel();
//                  ZaposleniTermin zt=mtd.getLista().get(red);
//                  Koordinator.getInstance().dodajParam("dezurstvo", zt);
//                  Koordinator.getInstance().otvoriAzurirajDezurstvoFormu();
//                  
//              }
//              
//          }
//      });
        pztf.addBtnPretraziActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String ime=pztf.getjTextFieldIme().getText().trim();
              String prezime=pztf.getjTextFieldPrezime().getText().trim();
              String danUNedelji=pztf.getjTextFieldDanUNedelji().getText().trim();
              Integer smena = null;
              if (!pztf.getjTextFieldSmena().getText().trim().isEmpty()) {
                smena = Integer.parseInt(pztf.getjTextFieldSmena().getText().trim());
              }
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                sdf.setLenient(false); // Ne dozvoljava pogrešne datume

                Date datum = null; // podrazumevano null, ako korisnik ne unese ništa

                String tekst = pztf.getjTextFieldDatum().getText().trim();
                if (!tekst.isEmpty()) {
                    try {
                        datum = sdf.parse(tekst);
                    } catch (ParseException esdf) {
                        JOptionPane.showMessageDialog(pztf, "Unesite ispravan datum u formatu dd.MM.yyyy", "Greška", JOptionPane.ERROR_MESSAGE);
                        return; // prekida izvršavanje ako je datum pogrešan
                    }
                }
                ModelTabeleDezurstva mtd=(ModelTabeleDezurstva) pztf.getjTableDezurstva().getModel();
                mtd.pretrazi(ime,prezime,danUNedelji, smena, datum);
            }
      });
      
        pztf.addBtnResetujActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              osveziFormu();
          }
      });
        
        pztf.addBtnNazadActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              pztf.dispose();
          }
      });
    }
    
    public void osveziFormu() {
        pripremiFormu();
    }

    
}
