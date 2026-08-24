/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.ClanBiblioteke;
import domen.KategorijaClana;
import forme.clan.PrikazSvihClanovaForma;
import forme.model.ModelTabeleClanovi;
import forme.model.ModelTabeleKategorije;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SpringLayout;

/**
 *
 * @author smulj
 */
public class PrikazClanovaController {
    private PrikazSvihClanovaForma pcf;

    public PrikazClanovaController(PrikazSvihClanovaForma prikazClanovaForma) {
        this.pcf = prikazClanovaForma;
        addActionListener();
        //addMouseListener();
    }

    public void otvoriFormu() {
        pripremiFormu();
        pcf.setVisible(true);
    }

    private void pripremiFormu() {
        List<ClanBiblioteke> clanovi= komunikacija.Komunikacija.getInstance().ucitajClanove();
        ModelTabeleClanovi mtc=new ModelTabeleClanovi(clanovi);
        pcf.getjTableClanovi().setModel(mtc);
        
        List<KategorijaClana> kategorije= komunikacija.Komunikacija.getInstance().ucitajKategorije();
        ModelTabeleKategorije mtk=new ModelTabeleKategorije(kategorije);
        pcf.getjTableKategorije().setModel(mtk);
    }

    private void addActionListener() {
        pcf.addBtnObrisiActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pcf.getjTableClanovi().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pcf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleClanovi mtc=(ModelTabeleClanovi) pcf.getjTableClanovi().getModel();
                  ClanBiblioteke c=mtc.getLista().get(red);
                  try {
                      komunikacija.Komunikacija.getInstance().obrisiClana(c);
                      JOptionPane.showMessageDialog(pcf, "Sistem je uspesno obrisao pacijenta");                  
                      pripremiFormu();
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pcf, "Sistem ne moze da obrise pacijenta");
                  }
              }  
          }
      });
        
        
      pcf.addBtnAzurirajActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pcf.getjTableClanovi().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pcf, "Nije izabran red");
                  return;
              }else{
                  ModelTabeleClanovi mtc=(ModelTabeleClanovi) pcf.getjTableClanovi().getModel();
                  ClanBiblioteke c=mtc.getLista().get(red);
                  Koordinator.getInstance().dodajParam("clan_biblioteke", c);
                  Koordinator.getInstance().otvoriAzurirajClanaFormu();
                  
              }
              
          }
      });
        pcf.addBtnPretraziActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String ime=pcf.getjTextFieldIme().getText().trim();
              String prezime=pcf.getjTextFieldPrezime().getText().trim();
              int red=pcf.getjTableKategorije().getSelectedRow();
              ModelTabeleKategorije mtk=(ModelTabeleKategorije) pcf.getjTableKategorije().getModel();
              String nazivKat=mtk.getLista().get(red).getNaziv();
              
              ModelTabeleClanovi mtc=(ModelTabeleClanovi) pcf.getjTableClanovi().getModel();
              mtc.pretrazi(ime, prezime, nazivKat);
              
          }
      });
        pcf.addBtnResetujActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              osveziFormu();
          }
      });
        
        pcf.addBtnNazadActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              pcf.dispose();
          }
      });
    }

//    private void addMouseListener() {
//        pcf.getjTableClanovi().addMouseListener(new MouseAdapter(){
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                int red=pcf.getjTableClanovi().getSelectedRow();
//                if(red!=-1){
//                    ModelTabeleClanovi mtc=(ModelTabeleClanovi) pcf.getjTableClanovi().getModel();
//                    ClanBiblioteke clan=mtc.getLista().get(red);
//                    KategorijaClana kategorija=clan.getKategorijaClana();
//                }
//            }
//            
//        });   
//    }
    
    public void osveziFormu() {
        pripremiFormu();
    }
    
    
}
