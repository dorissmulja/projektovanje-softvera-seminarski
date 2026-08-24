/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.KategorijaClana;
import domen.Knjiga;
import forme.kategorijaclana.PrikaziSveKategorijeForma;
import forme.model.ModelTabeleKategorije;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author smulj
 */
public class PrikaziKategorijeController {
    private PrikaziSveKategorijeForma pkf;
    
    public PrikaziKategorijeController(PrikaziSveKategorijeForma pkf) {
        this.pkf = pkf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    private void pripremiFormu() {
        List<KategorijaClana> kategorije= komunikacija.Komunikacija.getInstance().ucitajKategorije();
        ModelTabeleKategorije mtk=new ModelTabeleKategorije(kategorije);
        pkf.getjTableKategorijaClana().setModel(mtk);
    }

    private void addActionListener() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pkf.getjTableKategorijaClana().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pkf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleKategorije mtk=(ModelTabeleKategorije) pkf.getjTableKategorijaClana().getModel();
                  KategorijaClana k=mtk.getLista().get(red);
                  try {
                      komunikacija.Komunikacija.getInstance().obrisiKategoriju(k);
                      JOptionPane.showMessageDialog(pkf, "Sistem je uspesno obrisao pacijenta");                  
                      pripremiFormu();
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pkf, "Sistem ne moze da obrise pacijenta");
                  }
              }  
          }
      });
        
        
      pkf.addBtnAzurirajActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pkf.getjTableKategorijaClana().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pkf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleKategorije mtk=(ModelTabeleKategorije) pkf.getjTableKategorijaClana().getModel();
                  KategorijaClana k=mtk.getLista().get(red);
                  Koordinator.getInstance().dodajParam("kategorija", k);
                  Koordinator.getInstance().otvoriAzurirajKategorijuFormu();
                  
              }
              
          }
      });
        pkf.addBtnPretraziActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String naziv=pkf.getjTextFieldNaziv().getText().trim();
              ModelTabeleKategorije mtk=(ModelTabeleKategorije) pkf.getjTableKategorijaClana().getModel();
              mtk.pretrazi(naziv);
          }
      });
        pkf.addBtnResetujActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              osveziFormu();
          }
      });  
        
        pkf.addBtnNazadActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              pkf.dispose();
          }
      });
        
    }
    
    public void osveziFormu() {
        pripremiFormu();
    }
}
