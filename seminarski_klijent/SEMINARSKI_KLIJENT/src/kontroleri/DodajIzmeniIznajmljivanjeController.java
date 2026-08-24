/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.ClanBiblioteke;
import domen.Iznajmljivanje;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import forme.FormaMod;
import forme.iznajmljivanje.DodajIzmeniIznajmljivanjeForma;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import komunikacija.Komunikacija;

/**
 *
 * @author smulj
 */
public class DodajIzmeniIznajmljivanjeController {
  private DodajIzmeniIznajmljivanjeForma dif;

    public DodajIzmeniIznajmljivanjeController(DodajIzmeniIznajmljivanjeForma dif) {
        this.dif = dif;
        addActionListener();
    }
    
    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dif.setVisible(true);
    }

    private void pripremiFormu(FormaMod mod) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        switch(mod){
            case DODAJ:
                System.out.println("otvoreno je u modu dodaj");
                dif.getjButtonAzuriraj().setVisible(false);
                dif.getjButtonDodaj().setVisible(true);
                dif.getjButtonDodaj().setEnabled(true);
                
                dif.getjTextFieldID().setEnabled(false);
                
                List<ClanBiblioteke> clanovi=komunikacija.Komunikacija.getInstance().ucitajClanove();
                List<Zaposleni> zaposleni=komunikacija.Komunikacija.getInstance().ucitajZaposlene();
                dif.getjComboBoxClanBiblioteke().removeAllItems();
                dif.getjComboBoxZaposleni().removeAllItems();
                
                for(ClanBiblioteke c:clanovi){
                    dif.getjComboBoxClanBiblioteke().addItem(c);
                }
                
                dif.getjComboBoxZaposleni().addItem(Koordinator.getInstance().getUlogovani());
                dif.getjComboBoxZaposleni().setEnabled(false);
//                for(Zaposleni z:zaposleni){
//                    dif.getjComboBoxZaposleni().addItem(z);
//                }
                
                Date danas=new Date();
                String datumIznajmStr = sdf.format(danas); // npr. "30.06.2025"
                dif.getjTextFieldDatumIznajmljivanja().setText(datumIznajmStr);
                dif.getjTextFieldDatumIznajmljivanja().setEnabled(false);
                
                // Koristi Calendar za dodavanje 20 dana
                Calendar cal = Calendar.getInstance();
                cal.setTime(danas);
                cal.add(Calendar.DAY_OF_YEAR, 20);

                // Dobij novi datum
                Date rok = cal.getTime();
                String rokVracanjaStr = sdf.format(rok); // npr. "30.06.2025"
                dif.getjTextFieldRokVracanja().setText(rokVracanjaStr);
                dif.getjTextFieldRokVracanja().setEnabled(false);
                
                
                break;
            case IZMENI:
                System.out.println("otvoreno je u modu izmeni");
                dif.getjButtonAzuriraj().setVisible(true);
                dif.getjButtonDodaj().setVisible(false);
                dif.getjButtonAzuriraj().setEnabled(true);
                
                Iznajmljivanje iz=(Iznajmljivanje) Koordinator.getInstance().vratiParam("iznajmljivanje");
                
                Date datumIznajmljivanja=iz.getDatumIznajmljivanja();
                Date datumRokVracanja=iz.getRokVracanja();
                
                String datumIznajmStr1=sdf.format(datumIznajmljivanja);
                String rokVracanjaStr1=sdf.format(datumRokVracanja);
                
                dif.getjTextFieldDatumIznajmljivanja().setText(datumIznajmStr1);
                dif.getjTextFieldRokVracanja().setText(rokVracanjaStr1);
                
                dif.getjTextFieldID().setText(iz.getIdIznajmljivanje()+"");
                dif.getjTextFieldID().setEnabled(false);

                List<ClanBiblioteke> clanovi1=komunikacija.Komunikacija.getInstance().ucitajClanove();
                List<Zaposleni> zaposleni1=komunikacija.Komunikacija.getInstance().ucitajZaposlene();
                dif.getjComboBoxClanBiblioteke().removeAllItems();
                dif.getjComboBoxZaposleni().removeAllItems();
                
                for(ClanBiblioteke clan:clanovi1){
                    if(clan==iz.getClanBiblioteke()){
                        dif.getjComboBoxClanBiblioteke().addItem(clan);
                    }
                }
                for(ClanBiblioteke clan:clanovi1){
                    if(clan!=iz.getClanBiblioteke()){
                        dif.getjComboBoxClanBiblioteke().addItem(clan);
                    }    
                }
                
                for(Zaposleni zap:zaposleni1){
                    if(zap==iz.getZaposleni()){
                        dif.getjComboBoxZaposleni().addItem(zap);
                    }
                }
                for(Zaposleni zap:zaposleni1){
                    if(zap!=iz.getZaposleni()){
                        dif.getjComboBoxZaposleni().addItem(zap);
                    }    
                }
                
                dif.getjTextAreaNapomena().setText(iz.getNapomena());
                break;
                
        }
    }

    private void addActionListener() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        dif.dodajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                Zaposleni z=Koordinator.getInstance().getUlogovani();
                ClanBiblioteke clan=(ClanBiblioteke) dif.getjComboBoxClanBiblioteke().getSelectedItem();
                
                String datIzStr=dif.getjTextFieldDatumIznajmljivanja().getText().trim();
                Date datumIznajmljivanja;
                try {
                    datumIznajmljivanja = sdf.parse(datIzStr);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniIznajmljivanjeController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dif, "Niste dobro upisali datum iznajmljivanja");
                    return;
                }
                
                String datRokVracanjaStr=dif.getjTextFieldRokVracanja().getText().trim();
                Date datumRokVracnja;
                try {
                    datumRokVracnja = sdf.parse(datRokVracanjaStr);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniIznajmljivanjeController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dif, "Niste dobro upisali rok vracanja");
                    return;
                }
                
                String napomena=dif.getjTextAreaNapomena().getText();
                
//                ZaposleniTermin zt=new ZaposleniTermin(z,  t,  datum);
//                try{
//                    Komunikacija.getInstance().dodajDezurstvo(zt);
//                    JOptionPane.showMessageDialog(ddf, "uspeh");
//                    ddf.dispose();
//                }catch(Exception ex){
//                    JOptionPane.showMessageDialog(ddf, "greska");
//                    ex.printStackTrace();
//                }
                
            }
            
        });
        dif.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                Zaposleni z=(Zaposleni) dif.getjComboBoxZaposleni().getSelectedItem();
                ClanBiblioteke clan=(ClanBiblioteke) dif.getjComboBoxClanBiblioteke().getSelectedItem();
                int idIznajm=Integer.parseInt(dif.getjTextFieldID().getText());
                
                String datIzStr=dif.getjTextFieldDatumIznajmljivanja().getText().trim();
                Date datumIznajmljivanja;
                try {
                    datumIznajmljivanja = sdf.parse(datIzStr);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniIznajmljivanjeController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dif, "Niste dobro upisali datum iznajmljivanja");
                    return;
                }
                
                String datRokVracanjaStr=dif.getjTextFieldRokVracanja().getText().trim();
                Date datumRokVracanja;
                try {
                    datumRokVracanja = sdf.parse(datRokVracanjaStr);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniIznajmljivanjeController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(dif, "Niste dobro upisali rok vracanja");
                    return;
                }

                String napomena=dif.getjTextAreaNapomena().getText();

                Iznajmljivanje iz=new Iznajmljivanje(idIznajm, z, clan, napomena, datumIznajmljivanja, datumRokVracanja, new ArrayList<StavkaIznajmljivanja>());
                
                try{
                    Komunikacija.getInstance().azurirajIznajmljivanje(iz);
                    JOptionPane.showMessageDialog(dif, "uspeh");
                    dif.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dif, "greska");
                    ex.printStackTrace();
                }
                 
            }
        });
    }  
}
