/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.TerminDezurstva;
import forme.FormaMod;
import forme.termin.DodajIzmeniTerminForma;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author smulj
 */
public class DodajIzmeniTerminController {
    private DodajIzmeniTerminForma dtf;

    public DodajIzmeniTerminController(DodajIzmeniTerminForma dtf) {
        this.dtf = dtf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dtf.setVisible(true);
    }

    private void addActionListener() {
        dtf.dodajAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                String dan=dtf.getjTextFieldDan().getText().trim();
                int trajanje=Integer.parseInt(dtf.getjTextFieldTrajanje().getText());

                TerminDezurstva t= new TerminDezurstva(-1, dan, trajanje);
                
                try{
                Komunikacija.getInstance().dodajTermin(t);
                    JOptionPane.showMessageDialog(dtf, "uspeh");
                    dtf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dtf, "greska");
                    ex.printStackTrace();
                }
            }
            
        });
        dtf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                String dan=dtf.getjTextFieldDan().getText().trim();
                int trajanje=Integer.parseInt(dtf.getjTextFieldTrajanje().getText());
                int id=Integer.parseInt(dtf.getjTextFieldID().getText());
                TerminDezurstva t= new TerminDezurstva(id, dan, trajanje);
                
                try{
                Komunikacija.getInstance().azurirajTermin(t);
                    JOptionPane.showMessageDialog(dtf, "uspeh");
                    dtf.dispose();
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(dtf, "greska");
                    ex.printStackTrace();
                }
                 
            }
        });
    }

    private void pripremiFormu(FormaMod mod) {
        switch(mod){
            case DODAJ:
                dtf.getjButtonAzuriraj().setVisible(false);
                dtf.getjButtonDodaj().setVisible(true);
                dtf.getjButtonDodaj().setEnabled(true);
                dtf.getjTextFieldID().setEnabled(false);
                break;
            case IZMENI:
                dtf.getjButtonAzuriraj().setVisible(true);
                dtf.getjButtonDodaj().setVisible(false);
                dtf.getjButtonAzuriraj().setEnabled(true);
                
                TerminDezurstva t=(TerminDezurstva) Koordinator.getInstance().vratiParam("termin");
                
                dtf.getjTextFieldID().setText(t.getIdTerminDezurstva()+"");
                dtf.getjTextFieldDan().setText(t.getDanUNedelji());
                dtf.getjTextFieldTrajanje().setText(t.getSmena()+"");
                break;
        }    
    }
}
