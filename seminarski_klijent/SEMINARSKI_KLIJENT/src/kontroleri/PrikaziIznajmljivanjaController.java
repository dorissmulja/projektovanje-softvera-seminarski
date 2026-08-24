/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import forme.FormaMod;
import forme.iznajmljivanje.PrikaziIznajmljivanjaForma;
import forme.model.ModelTabeleIznajmljivanje;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author smulj
 */
public class PrikaziIznajmljivanjaController {
    private PrikaziIznajmljivanjaForma pif;

    public PrikaziIznajmljivanjaController(PrikaziIznajmljivanjaForma pif) {
        this.pif = pif;
        addActionListener();
        addMouseListener(); 
    }
    
    public void pripremiFormu() {
        List<Iznajmljivanje> iznajmljivanja= komunikacija.Komunikacija.getInstance().ucitajIznajmljivanja();
        ModelTabeleIznajmljivanje mti=new ModelTabeleIznajmljivanje(iznajmljivanja);
        pif.getjTableIznajmljivanja().setModel(mti);
    
        List<StavkaIznajmljivanja> stavke=new ArrayList<>();
        ModelTabeleStavkaIznajmljivanja mts=new ModelTabeleStavkaIznajmljivanja(stavke);
        pif.getjTableStavkeIznajmljivanja().setModel(mts);
    }
    
    public void otvoriFormu() {
        pripremiFormu();
        pif.setVisible(true);
        
    }
    

    private void addActionListener() {
        pif.addBtnObrisiIznajmljivanjeActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pif.getjTableIznajmljivanja().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pif, "Nije izabran red iz tabele iznajmljivanja obrisi izn");
                  return;
              }else{
                  ModelTabeleIznajmljivanje mti=(ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                  Iznajmljivanje iz=mti.getLista().get(red);
                  List<StavkaIznajmljivanja> stavke= komunikacija.Komunikacija.getInstance().ucitajStavke(iz.getIdIznajmljivanje());
                  iz.setStavke(stavke);
                  
                  try {
                      komunikacija.Komunikacija.getInstance().obrisiIznajmljivanje(iz);
                      JOptionPane.showMessageDialog(pif, "Sistem je uspesno obrisao iznajmljivanje");                  
                      pripremiFormu();
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pif, "Sistem ne moze da obrise iznajmljivanje");
                  }
              }
              
          }
      });
        
        pif.addBtnObrisiStavkuActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pif.getjTableStavkeIznajmljivanja().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pif, "Nije izabran red iz tabele stavka obrisi st");
                  return;
              }else{
                  ModelTabeleStavkaIznajmljivanja mts=(ModelTabeleStavkaIznajmljivanja) pif.getjTableStavkeIznajmljivanja().getModel();
                  StavkaIznajmljivanja st=mts.getLista().get(red);
                 
                  try {
                      komunikacija.Komunikacija.getInstance().obrisiStavku(st);
                      JOptionPane.showMessageDialog(pif, "Sistem je uspesno obrisao stavku iznajmljivanja");                  
                      mts.obrisiStavku(st);
                  } catch (Exception ex) {
                      JOptionPane.showMessageDialog(pif, "Sistem ne moze da obrise stavku ");
                  }
              }
              
          }
      });

        
        pif.addBtnAzurirajIznajmljivanjeActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pif.getjTableIznajmljivanja().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pif, "Nije izabran red iz tabele iznajmljivanja azuri izn");
                  return;
              }else{
                  ModelTabeleIznajmljivanje mti=(ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                  Iznajmljivanje iz=mti.getLista().get(red);
                  Koordinator.getInstance().dodajParam("iznajmljivanje", iz);
                  Koordinator.getInstance().otvoriGlavnuFormu(FormaMod.IZMENI);
                  
              }
              
          }
      });
        
        pif.addBtnAzurirajStavkuActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int red=pif.getjTableStavkeIznajmljivanja().getSelectedRow();
              if(red==-1){
                  JOptionPane.showMessageDialog(pif, "Nije izabran red iz tabele stavka azur st");
                  return;
              }else{
                  ModelTabeleStavkaIznajmljivanja mts=(ModelTabeleStavkaIznajmljivanja) pif.getjTableStavkeIznajmljivanja().getModel();
                  StavkaIznajmljivanja st=mts.getLista().get(red);
                  Koordinator.getInstance().dodajParam("stavka", st);
                  Koordinator.getInstance().otvoriAzurirajStavkuForma();
                  
              }
              
          }
      });
        
        
        
        pif.addBtnPretraziActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String imeZap=pif.getjTextFieldImeZap().getText().trim();
              String prezimeZap=pif.getjTextFieldPrezimeZap().getText().trim();
              String imeClna=pif.getjTextFieldImeClana().getText().trim();
              String prezimeClana=pif.getjTextFieldPrezimeClana().getText().trim();
              Integer ID = null;
              if (!pif.getjTextFieldID().getText().trim().isEmpty()) {
                ID = Integer.parseInt(pif.getjTextFieldID().getText().trim());
              }
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                sdf.setLenient(false); // Ne dozvoljava pogrešne datume

                Date datum = null; // podrazumevano null, ako korisnik ne unese ništa

                String tekst = pif.getjTextFieldDatum().getText().trim();
                if (!tekst.isEmpty()) {
                    try {
                        datum = sdf.parse(tekst);
                    } catch (ParseException esdf) {
                        JOptionPane.showMessageDialog(pif, "Unesite ispravan datum u formatu dd.MM.yyyy", "Greška", JOptionPane.ERROR_MESSAGE);
                        return; // prekida izvršavanje ako je datum pogrešan
                    }
                }
                ModelTabeleIznajmljivanje mti=(ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                mti.pretrazi(ID, imeZap, prezimeZap, imeClna, prezimeClana, datum);
            }
      });
      
        pif.addBtnResetujActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              osveziFormu();
          }
      });
        
        pif.addBtnNazadActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              pif.dispose();
          }
      });
    }
    
        private void addMouseListener() {
        pif.getjTableIznajmljivanja().addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int red=pif.getjTableIznajmljivanja().getSelectedRow();
                if(red!=-1){
                    ModelTabeleIznajmljivanje mti=(ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                    Iznajmljivanje iznajmljivanje=mti.getLista().get(red);
                    //sad treba da ucitam stavke i stavim ih u onu drugu tabelu
                    int id=iznajmljivanje.getIdIznajmljivanje();
                    List<StavkaIznajmljivanja> stavke= komunikacija.Komunikacija.getInstance().ucitajStavke(id);
                    ModelTabeleStavkaIznajmljivanja mts=new ModelTabeleStavkaIznajmljivanja(stavke);
                    pif.getjTableStavkeIznajmljivanja().setModel(mts);
                }
            }
            
        });   
    }
    
    public void osveziFormu() {
        pripremiFormu();
    }

    public void osveziTabeluStavki() {
        int red=pif.getjTableIznajmljivanja().getSelectedRow();
                if(red!=-1){
                    ModelTabeleIznajmljivanje mti=(ModelTabeleIznajmljivanje) pif.getjTableIznajmljivanja().getModel();
                    Iznajmljivanje iznajmljivanje=mti.getLista().get(red);
                    //sad treba da ucitam stavke i stavim ih u onu drugu tabelu
                    int id=iznajmljivanje.getIdIznajmljivanje();
                    List<StavkaIznajmljivanja> stavke= komunikacija.Komunikacija.getInstance().ucitajStavke(id);
                    iznajmljivanje.setStavke(stavke);
                    
                    ModelTabeleStavkaIznajmljivanja mts=new ModelTabeleStavkaIznajmljivanja(stavke);
                    pif.getjTableStavkeIznajmljivanja().setModel(mts);
                }else{
                    ModelTabeleStavkaIznajmljivanja mts=new ModelTabeleStavkaIznajmljivanja(new ArrayList<>());
                    pif.getjTableStavkeIznajmljivanja().setModel(mts);
                }
                        
    }
}
