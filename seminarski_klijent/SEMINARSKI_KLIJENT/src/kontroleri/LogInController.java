/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.Zaposleni;
import forme.LogInForma;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import komunikacija.Komunikacija;

/**
 *
 * @author smulj
 */
public class LogInController {
    private final LogInForma lf;

    public LogInController(LogInForma lf) {
        this.lf = lf;
        addActionListeners();
    }

    private void addActionListeners() {
        
        lf.loginAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prijava(e);
            }

            private void prijava(ActionEvent e) {
                String ki=lf.getjTextFieldKorisnickoIme().getText().trim();
                String sifra=String.valueOf(lf.getjPasswordField().getPassword());
                System.out.println("Stiglo do ovdje 1");
                Komunikacija.getInstance().konekcija();
                Zaposleni ulogovani=Komunikacija.getInstance().login(ki,sifra);
                
                if(ulogovani==null){
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra nisu ispravni", "Log in", JOptionPane.INFORMATION_MESSAGE);
                }else{
                    Koordinator.getInstance().setUlogovani(ulogovani);
                    JOptionPane.showMessageDialog(lf, "Korisničko ime i šifra su ispravni", "Log in", JOptionPane.INFORMATION_MESSAGE);
                    Koordinator.getInstance().otvoriGlavnuFormu();
                    lf.dispose();
                }
            }
        });
        
    }

    public void otvoriFormu() {
        lf.setVisible(true);
    }
    
    
}
