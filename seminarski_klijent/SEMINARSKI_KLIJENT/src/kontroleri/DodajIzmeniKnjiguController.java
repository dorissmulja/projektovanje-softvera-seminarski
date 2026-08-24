/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Knjiga;
import domen.Zaposleni;
import forme.FormaMod;
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
public class DodajIzmeniKnjiguController {
    private DodajIzmeniKnjiguForma dkf;

    public DodajIzmeniKnjiguController(DodajIzmeniKnjiguForma dkf) {
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
                String izdavackaKuca=dkf.getjTextFieldIzdavackaKuca().getText().trim();
                String autor=dkf.getjTextFieldAutor().getText().trim();

                Knjiga k= new Knjiga(-1, naziv, autor, izdavackaKuca);
                
                try{
                Komunikacija.getInstance().dodajKnjigu(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je dodao knjigu: "+k.toString());
                    dkf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da doda knjigu");
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
                String autor=dkf.getjTextFieldAutor().getText().trim();
                String izdavackaKuca=dkf.getjTextFieldIzdavackaKuca().getText().trim();
                int id=Integer.parseInt(dkf.getjTextFieldID().getText());
                Knjiga k=new Knjiga(id, naziv, autor, izdavackaKuca);
                
                try{
                Komunikacija.getInstance().azurirajKnjigu(k);
                    JOptionPane.showMessageDialog(dkf, "Sistem je zapamtio člana biblioteke");
                    dkf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dkf, "Sistem ne može da zapamti člana biblioteke");
                    ex.printStackTrace();
                }
                 
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch(mod){
            case DODAJ:
                dkf.getjButtonIzmeni().setVisible(false);
                dkf.getjButtonDodaj().setVisible(true);
                dkf.getjButtonDodaj().setEnabled(true);
                dkf.getjTextFieldID().setEnabled(false);
                break;
            case IZMENI:
                dkf.getjTextFieldID().setEnabled(false);
                dkf.getjButtonIzmeni().setVisible(true);
                dkf.getjButtonDodaj().setVisible(false);
                dkf.getjButtonIzmeni().setEnabled(true);
                
                Knjiga k=(Knjiga) Koordinator.getInstance().vratiParam("knjiga");
                
                dkf.getjTextFieldID().setText(k.getIdKnjiga()+"");
                dkf.getjTextFieldNaziv().setText(k.getNaziv());
                dkf.getjTextFieldAutor().setText(k.getAutor());
                dkf.getjTextFieldIzdavackaKuca().setText(k.getIzdavackaKuca());
                break;
        }    
    }
    
    
}
