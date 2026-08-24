/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import forme.FormaMod;
import forme.zaposleni_termin.DodajIzmeniDezurstvoForma;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author smulj
 */
public class DodajIzmeniDezurstvoController {
    private DodajIzmeniDezurstvoForma ddf;

    public DodajIzmeniDezurstvoController(DodajIzmeniDezurstvoForma ddf) {
        this.ddf = ddf;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        ddf.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        switch(mod){
            case DODAJ:
                ddf.getjButtonAzuriraj().setVisible(false);
                ddf.getjButtonDodaj().setVisible(true);
                ddf.getjButtonDodaj().setEnabled(true);
                
                List<TerminDezurstva> termini=komunikacija.Komunikacija.getInstance().ucitajTermine();
                List<Zaposleni> zaposleni=komunikacija.Komunikacija.getInstance().ucitajZaposlene();
                ddf.getjComboBoxTermini().removeAllItems();
//                ddf.getjComboBoxZaposleni().removeAllItems();
                
                for(TerminDezurstva t:termini){
                    ddf.getjComboBoxTermini().addItem(t);
                }
                ddf.getjTextFieldZaposleni().setText(Koordinator.getInstance().getUlogovani().toString());
                ddf.getjTextFieldZaposleni().setEnabled(false);
//                for(Zaposleni z:zaposleni){
//                    ddf.getjComboBoxZaposleni().addItem(z);
//                }
                Date datum=new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String formatiraniDatum = sdf.format(datum); // npr. "30.06.2025"
                ddf.getjTextFieldDatum().setText(formatiraniDatum);
                ddf.getjTextFieldDatum().setEnabled(false);
                break;
//            case IZMENI:
//                ddf.getjButtonAzuriraj().setVisible(true);
//                ddf.getjButtonDodaj().setVisible(false);
//                ddf.getjButtonAzuriraj().setEnabled(true);
//                
//                ZaposleniTermin zt=(ZaposleniTermin) Koordinator.getInstance().vratiParam("dezurstvo");
//                
//                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//                Date datum = zt.getDatumDezurstva(); // ili neki drugi izvor datuma
//                String formatiraniDatum = sdf.format(datum); // npr. "30.06.2025"
//                ddf.getjTextFieldDatum().setText(formatiraniDatum);
//                
//                List<TerminDezurstva> termini1=komunikacija.Komunikacija.getInstance().ucitajTermine();
//                List<Zaposleni> zaposleni1=komunikacija.Komunikacija.getInstance().ucitajZaposlene();  
//                ddf.getjComboBoxTermini().removeAllItems();
//                ddf.getjComboBoxZaposleni().removeAllItems();
//                
//                ddf.getjComboBoxTermini().addItem(zt.getTermin());
//                ddf.getjComboBoxZaposleni().addItem(zt.getZaposleni());
//                
//                for(TerminDezurstva t:termini1){
//                    if(t!=zt.getTermin())
//                        ddf.getjComboBoxTermini().addItem(t);
//                }
//                for(Zaposleni z:zaposleni1){
//                    if(z!=zt.getZaposleni())
//                        ddf.getjComboBoxZaposleni().addItem(z);
//                }
//                break;
                
        }
    }

    private void addActionListener() {
        ddf.dodajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                Zaposleni z=Koordinator.getInstance().getUlogovani();
                TerminDezurstva t=(TerminDezurstva) ddf.getjComboBoxTermini().getSelectedItem();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String datS=ddf.getjTextFieldDatum().getText().trim();
                Date datum;
                try {
                    datum = sdf.parse(datS);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniDezurstvoController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(ddf, "Niste dobro upisali datum");
                    return;
                }
                
                ZaposleniTermin zt=new ZaposleniTermin(z,  t,  datum);
                try{
                    Komunikacija.getInstance().dodajDezurstvo(zt);
                    JOptionPane.showMessageDialog(ddf, "uspeh");
                    ddf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(ddf, "greska");
                    ex.printStackTrace();
                }
                
            }
            
        });
        ddf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //izmeni(e);
            }

//            private void izmeni(ActionEvent e) {
//                Zaposleni z=(Zaposleni) ddf.getjComboBoxZaposleni().getSelectedItem();
//                TerminDezurstva t=(TerminDezurstva) ddf.getjComboBoxTermini().getSelectedItem();
//                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
//                String datS=ddf.getjTextFieldDatum().getText().trim();
//                Date datum;
//                try {
//                    datum = sdf.parse(datS);
//                } catch (ParseException ex) {
//                    Logger.getLogger(DodajIzmeniDezurstvoController.class.getName()).log(Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(ddf, "Niste dobro upisali datum");
//                    return;
//                }
//                
//                ZaposleniTermin zt=new ZaposleniTermin(z,  t,  datum);
//                try{
//                    Komunikacija.getInstance().azurirajDezurstvo(zt);
//                    JOptionPane.showMessageDialog(ddf, "uspeh");
//                    ddf.dispose();
//                }catch(Exception ex){
//                    JOptionPane.showMessageDialog(ddf, "greska");
//                    ex.printStackTrace();
//                }
//                 
//            }
        });
    }
    
}
