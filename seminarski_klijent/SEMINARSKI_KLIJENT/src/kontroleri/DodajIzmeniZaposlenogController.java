/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Zaposleni;
import forme.zaposleni.DodajIzmeniZaposlenogForma;
import forme.FormaMod;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author smulj
 */
public class DodajIzmeniZaposlenogController {
    private DodajIzmeniZaposlenogForma dzf;

    public DodajIzmeniZaposlenogController(DodajIzmeniZaposlenogForma dzf) {
        this.dzf = dzf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dzf.setVisible(true);
    }

    private void addActionListener() {
        dzf.dodajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String ki=dzf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra=String.valueOf(dzf.getjPasswordField1().getPassword());
                String ime=dzf.getjTextFieldIme().getText().trim();
                String prezime=dzf.getjTextFieldPrezime().getText().trim();
                String email=dzf.getjTextFieldEmail().getText().trim();

                Zaposleni z=new Zaposleni(-1, ime, prezime, email, ki, sifra);
                
                try{
                Komunikacija.getInstance().dodajZaposlenog(z);
                    JOptionPane.showMessageDialog(dzf, "Sistem je dodao člana biblioteke");
                    dzf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dzf, "Sistem ne može da doda člana biblioteke");
                    ex.printStackTrace();
                }
                 
            }
        });
            dzf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                String ki=dzf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra=String.valueOf(dzf.getjPasswordField1().getPassword());
                String ime=dzf.getjTextFieldIme().getText().trim();
                String prezime=dzf.getjTextFieldPrezime().getText().trim();
                String email=dzf.getjTextFieldEmail().getText().trim();
                int id=Integer.parseInt(dzf.getjTextFieldID().getText());
                Zaposleni z=new Zaposleni(id, ime, prezime, email, ki, sifra);
                
                try{
                Komunikacija.getInstance().azurirajZaposlenog(z);
                    JOptionPane.showMessageDialog(dzf, "Sistem je zapamtio člana biblioteke");
                    dzf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dzf, "Sistem ne može da zapamti člana biblioteke");
                    ex.printStackTrace();
                }
                 
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch(mod){
            case DODAJ:
                dzf.getjButtonAzuriraj().setVisible(false);
                dzf.getjButtonDodaj().setVisible(true);
                dzf.getjButtonDodaj().setEnabled(true);
                dzf.getjTextFieldID().setEnabled(false);
                break;
            case IZMENI:
                dzf.getjButtonAzuriraj().setVisible(true);
                dzf.getjButtonDodaj().setVisible(false);
                dzf.getjButtonAzuriraj().setEnabled(true);
                
                Zaposleni z=(Zaposleni) Koordinator.getInstance().vratiParam("zaposleni");
                dzf.getjTextFieldIme().setText(z.getIme());
                dzf.getjTextFieldPrezime().setText(z.getPrezime());
                dzf.getjTextFieldEmail().setText(z.getEmail());
                dzf.getjTextFieldKorisnickoIme().setText(z.getKorisnickoIme());
                dzf.getjPasswordField1().setText(z.getSifra());
                dzf.getjTextFieldID().setText(z.getIdZaposlen()+"");
                
                break;
        }
    }
}
