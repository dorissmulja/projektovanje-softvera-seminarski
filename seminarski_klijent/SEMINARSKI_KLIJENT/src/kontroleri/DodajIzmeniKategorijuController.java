/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.KategorijaClana;
import domen.Knjiga;
import forme.FormaMod;
import forme.kategorijaclana.DodajIzmeniKategorijuForma;
import forme.knjiga.DodajIzmeniKnjiguForma;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author smulj
 */
public class DodajIzmeniKategorijuController {
    private DodajIzmeniKategorijuForma dkf;

    public DodajIzmeniKategorijuController(DodajIzmeniKategorijuForma dkf) {
        this.dkf = dkf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dkf.setVisible(true);
    }

    private void addActionListener() {
        dkf.dodajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String naziv=dkf.getjTextFieldNaziv().getText().trim();
                double cena=Double.parseDouble(dkf.getjTextFieldCena().getText().trim());

                KategorijaClana k=new KategorijaClana(-1, naziv, cena);
                
                try{
                Komunikacija.getInstance().dodajKategoriju(k);
                    JOptionPane.showMessageDialog(dkf, "uspeh");
                    dkf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dkf, "greska");
                    ex.printStackTrace();
                }
            }
            
        });
        dkf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                String naziv=dkf.getjTextFieldNaziv().getText().trim();
                double cena=Double.parseDouble(dkf.getjTextFieldCena().getText().trim());
                int id=Integer.parseInt(dkf.getjTextFieldID().getText());
                KategorijaClana k=new KategorijaClana(id, naziv, cena);
                
                try{
                Komunikacija.getInstance().azurirajKategoriju(k);
                    JOptionPane.showMessageDialog(dkf, "uspeh");
                    dkf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dkf, "greska");
                    ex.printStackTrace();
                }
                 
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch(mod){
            case DODAJ:
                dkf.getjButtonAzuriraj().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonDodaj().setEnabled(true);
                dkf.getjTextFieldID().setEnabled(false);
                break;
            case IZMENI:
                dkf.getjButtonAzuriraj().setVisible(true);
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonAzuriraj().setEnabled(true);
                
                KategorijaClana k=(KategorijaClana) Koordinator.getInstance().vratiParam("kategorija");
                
                dkf.getjTextFieldID().setText(k.getIdKategorijaClana()+"");
                dkf.getjTextFieldNaziv().setText(k.getNaziv());
                dkf.getjTextFieldCena().setText(k.getCenaClanarine()+"");
                break;
        }    
    }
}
