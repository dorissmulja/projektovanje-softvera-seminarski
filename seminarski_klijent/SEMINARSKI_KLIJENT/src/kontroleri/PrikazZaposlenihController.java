/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Zaposleni;
import forme.zaposleni.PrikazSvihZaposlenihForma;
import forme.model.ModelTabeleZaposleni;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author smulj
 */
public class PrikazZaposlenihController {
    private PrikazSvihZaposlenihForma pzf;

    public PrikazZaposlenihController(PrikazSvihZaposlenihForma pzf) {
        this.pzf = pzf;
        addActionListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pzf.setVisible(true);
    }

    public void pripremiFormu() {
        List<Zaposleni> zaposleni= komunikacija.Komunikacija.getInstance().ucitajZaposlene();
        ModelTabeleZaposleni mtz=new ModelTabeleZaposleni(zaposleni);
        pzf.getjTableZaposleni().setModel(mtz);
    }

    private void addActionListener() {
      pzf.addBtnObrisiActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pzf.getjTableZaposleni().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pzf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleZaposleni mtz=(ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
                  Zaposleni z=mtz.getLista().get(red);
                  try {
                      komunikacija.Komunikacija.getInstance().obrisiZaposlenog(z);
                      JOptionPane.showMessageDialog(pzf, "Sistem je uspesno obrisao zaposlenog");                  
                      pripremiFormu();
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pzf, "Sistem ne moze da obrise zaposlenog");
                  }
              }
              
          }
      });
        pzf.addBtnAzurirajActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pzf.getjTableZaposleni().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pzf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleZaposleni mtz=(ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
                  Zaposleni z=mtz.getLista().get(red);
                  Koordinator.getInstance().dodajParam("zaposleni", z);
                  Koordinator.getInstance().otvoriAzurirajZaposlenogFormu();
                  
              }
              
          }
      });
        pzf.addBtnPretraziActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String ime=pzf.getjTextFieldIme().getText().trim();
              String prezime=pzf.getjTextFieldPrezime().getText().trim();
              ModelTabeleZaposleni mtz=(ModelTabeleZaposleni) pzf.getjTableZaposleni().getModel();
              mtz.pretrazi(ime,prezime);
          }
      });
        pzf.addBtnResetujActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              osveziFormu();
          }
      });
        
        pzf.addBtnNazadActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              pzf.dispose();
          }
      });
    }

    public void osveziFormu() {
        pripremiFormu();
    }
    
    
}
