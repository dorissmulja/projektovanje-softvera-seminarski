/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.ClanBiblioteke;
import domen.KategorijaClana;
import forme.FormaMod;
import forme.clan.DodajIzmeniClanForma;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author smulj
 */
public class DodajIzmeniClanaController {
    private DodajIzmeniClanForma dcf;

    public DodajIzmeniClanaController(DodajIzmeniClanForma dcf) {
        this.dcf = dcf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dcf.setVisible(true);
    }

    private void addActionListener() {
        dcf.dodajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                
                String ime=dcf.getjTextFieldIme().getText().trim();
                String prezime=dcf.getjTextFieldPrezime().getText().trim();
                String email=dcf.getjTextFieldEmail().getText().trim();
                String datumUclanjenjaText = dcf.getjTextFielDatumUclanjenja().getText().trim();
                String datumIstekaText = dcf.getjTextFieldDatumIsteka().getText().trim();

                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                //sdf.setLenient(false); // stroga provjera datuma
                KategorijaClana kategorijaClana=(KategorijaClana) dcf.getjComboBoxKategorija().getSelectedItem();

                try {
                    Date datumUclanjenja = sdf.parse(datumUclanjenjaText);
                    Date datumIsteka = sdf.parse(datumIstekaText);

                    ClanBiblioteke c= new ClanBiblioteke(-1,ime, prezime, email, datumUclanjenja, datumIsteka,  kategorijaClana);
                
                    try{
                    Komunikacija.getInstance().dodajClana(c);
                        JOptionPane.showMessageDialog(dcf, "Sistem je dodao knjigu");
                        dcf.dispose();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(dcf, "Sistem ne može da doda knjigu");
                        ex.printStackTrace();
                    }
                } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy", "Greška", JOptionPane.ERROR_MESSAGE);
                }   
            }
            
        });
        dcf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                
                String ime=dcf.getjTextFieldIme().getText().trim();
                String prezime=dcf.getjTextFieldPrezime().getText().trim();
                String email=dcf.getjTextFieldEmail().getText().trim();
                String datumUclanjenjaText = dcf.getjTextFielDatumUclanjenja().getText().trim();
                String datumIstekaText = dcf.getjTextFieldDatumIsteka().getText().trim();
                int id=Integer.parseInt(dcf.getjTextFieldID().getText());
                
                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
                //sdf.setLenient(false); // stroga provjera datuma
                KategorijaClana kategorijaClana=(KategorijaClana) dcf.getjComboBoxKategorija().getSelectedItem();

                try {
                    Date datumUclanjenja = sdf.parse(datumUclanjenjaText);
                    Date datumIsteka = sdf.parse(datumIstekaText);

                    ClanBiblioteke c= new ClanBiblioteke(id,ime, prezime, email, datumUclanjenja, datumIsteka,  kategorijaClana);
                
                    try{
                    Komunikacija.getInstance().azurirajClana(c);
                        JOptionPane.showMessageDialog(dcf, "Sistem je zapamtio knjigu");
                        dcf.dispose();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(dcf, "Sistem ne može da zapamti knjigu");
                        ex.printStackTrace();
                    }
                } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                 
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch(mod){
            case DODAJ:
                dcf.getjButtonIzmeni().setVisible(false);
                dcf.getjButtonDodaj().setVisible(true);
                dcf.getjButtonDodaj().setEnabled(true);
                dcf.getjTextFieldID().setEnabled(false);
                
                List<KategorijaClana> kategorije= komunikacija.Komunikacija.getInstance().ucitajKategorije();
                for(KategorijaClana kateg:kategorije){
                    dcf.getjComboBoxKategorija().addItem(kateg);
                }
                break;
            case IZMENI:
                dcf.getjTextFieldID().setEnabled(false);
                dcf.getjButtonIzmeni().setVisible(true);
                dcf.getjButtonDodaj().setVisible(false);
                dcf.getjButtonIzmeni().setEnabled(true);
                
                ClanBiblioteke c=(ClanBiblioteke) Koordinator.getInstance().vratiParam("clan_biblioteke");
                
                dcf.getjTextFieldID().setText(c.getIdClan()+"");
                dcf.getjTextFieldIme().setText(c.getIme()+"");
                dcf.getjTextFieldPrezime().setText(c.getPrezime()+"");
                dcf.getjTextFieldEmail().setText(c.getEmail()+"");
                dcf.getjTextFielDatumUclanjenja().setText(c.getDatumUclanjenja()+"");
                dcf.getjTextFieldDatumIsteka().setText(c.getDatumIsteka()+"");
                List<KategorijaClana> kategorije2= komunikacija.Komunikacija.getInstance().ucitajKategorije();
                for(KategorijaClana kateg:kategorije2){
                    if(kateg==c.getKategorijaClana()){
                        dcf.getjComboBoxKategorija().addItem(kateg);
                    }
                }
                for(KategorijaClana kat:kategorije2){
                    if(kat!=c.getKategorijaClana()){
                        dcf.getjComboBoxKategorija().addItem(kat);
                    }    
                }
                break;
            
            }   
    }
}
