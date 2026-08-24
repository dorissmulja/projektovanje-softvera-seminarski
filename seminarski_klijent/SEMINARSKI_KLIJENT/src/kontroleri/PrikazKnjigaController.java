/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Knjiga;
import forme.knjiga.PrikaziSveKnjigeForma;
import forme.model.ModelTabeleKnjige;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author smulj
 */
public class PrikazKnjigaController {
    private PrikaziSveKnjigeForma pkf;

    public PrikazKnjigaController(PrikaziSveKnjigeForma pkf) {
        this.pkf = pkf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pkf.setVisible(true);
    }

    private void pripremiFormu() {
        List<Knjiga> knjige= komunikacija.Komunikacija.getInstance().ucitajKnjige();
        ModelTabeleKnjige mtk=new ModelTabeleKnjige(knjige);
        pkf.getjTableKnjige().setModel(mtk);
    }

    private void addActionListener() {
        pkf.addBtnObrisiActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pkf.getjTableKnjige().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pkf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleKnjige mtk=(ModelTabeleKnjige) pkf.getjTableKnjige().getModel();
                  Knjiga k=mtk.getLista().get(red);
                  try {
                      komunikacija.Komunikacija.getInstance().obrisiKnjigu(k);
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
              int red=pkf.getjTableKnjige().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pkf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleKnjige mtk=(ModelTabeleKnjige) pkf.getjTableKnjige().getModel();
                  Knjiga k=mtk.getLista().get(red);
                  Koordinator.getInstance().dodajParam("knjiga", k);
                  Koordinator.getInstance().otvoriAzurirajKnjiguFormu();
                  
              }
              
          }
      });
        pkf.addBtnPretraziActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String naziv=pkf.getjTextFieldNaziv().getText().trim();
              String autor=pkf.getjTextFieldAutor().getText().trim();
              ModelTabeleKnjige mtk=(ModelTabeleKnjige) pkf.getjTableKnjige().getModel();
              mtk.pretrazi(naziv,autor);
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
