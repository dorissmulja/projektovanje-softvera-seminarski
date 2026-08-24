/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.TerminDezurstva;
import forme.model.ModelTabeleTermin;
import forme.termin.PrikaziTermineForma;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author smulj
 */
public class PrikaziTermineController {
    private PrikaziTermineForma ptf;

    public PrikaziTermineController(PrikaziTermineForma ptf) {
        this.ptf = ptf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        ptf.setVisible(true);
    }

    private void pripremiFormu() {
        List<TerminDezurstva> termini= komunikacija.Komunikacija.getInstance().ucitajTermine();
        ModelTabeleTermin mtt=new ModelTabeleTermin(termini);
        ptf.getjTableTermin().setModel(mtt);
    }

    private void addActionListener() {
        ptf.addBtnObrisiActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=ptf.getjTableTermin().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(ptf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleTermin mtt=(ModelTabeleTermin) ptf.getjTableTermin().getModel();
                  TerminDezurstva t=mtt.getLista().get(red);
                  try {
                      komunikacija.Komunikacija.getInstance().obrisiTermin(t);
                      JOptionPane.showMessageDialog(ptf, "Sistem je uspesno obrisao pacijenta");                  
                      pripremiFormu();
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(ptf, "Sistem ne moze da obrise pacijenta");
                  }
              }  
          }
      });
        
        
      ptf.addBtnAzurirajActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=ptf.getjTableTermin().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(ptf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleTermin mtt=(ModelTabeleTermin) ptf.getjTableTermin().getModel();
                  TerminDezurstva t=mtt.getLista().get(red);
                  Koordinator.getInstance().dodajParam("termin", t);
                  Koordinator.getInstance().otvoriAzurirajTerminFormu();
                  
              }
              
          }
      });
        ptf.addBtnPretraziActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String dan=ptf.getjTextFieldDan().getText().trim();
              ModelTabeleTermin mtt=(ModelTabeleTermin) ptf.getjTableTermin().getModel();
              mtt.pretrazi(dan);
          }
      });
        ptf.addBtnResetujActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              osveziFormu();
          }
      });  
        
        ptf.addBtnNazadActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              ptf.dispose();
          }
      });
    }
    
    public void osveziFormu() {
        pripremiFormu();
    }
}
