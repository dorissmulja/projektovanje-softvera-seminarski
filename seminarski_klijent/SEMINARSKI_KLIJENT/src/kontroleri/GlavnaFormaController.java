/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;

import domen.ClanBiblioteke;
import domen.Iznajmljivanje;
import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import domen.Zaposleni;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.model.ModelTabeleKnjige;
import forme.model.ModelTabeleStavkaIznajmljivanja;
import glavniKontroler.Koordinator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import komunikacija.Komunikacija;

/**
 *
 * @author smulj
 */
public class GlavnaFormaController {
    private final GlavnaForma gf;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    public GlavnaFormaController(GlavnaForma gf) {
        this.gf = gf;
        addActionListeners();       
    }

    private void addActionListeners() {
        gf.dodaStavkuAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String datS=gf.getjTextFieldDatumVracanja().getText().trim();
                Date datumVracanja;
                if(datS!=null && !datS.isEmpty() && !datS.equals("")){
                    try {
                        datumVracanja = sdf.parse(datS);
                    } catch (ParseException ex) {
                        Logger.getLogger(DodajIzmeniDezurstvoController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(gf, "Niste dobro upisali datum vracanja stavke");
                        return;
                    }
                }else{
                    datumVracanja=null;
                }
                    double cenaKazne;
                    try{
                    cenaKazne=Double.parseDouble(gf.getjTextFieldCenaKazne().getText());
                    }catch(NumberFormatException exc){
                        cenaKazne=0;
                    }
                    Knjiga k= (Knjiga) gf.getjComboBoxKnjiga().getSelectedItem();
                    
                    StavkaIznajmljivanja stavkaDodaj=new StavkaIznajmljivanja();
                    stavkaDodaj.setCenaKazne(cenaKazne);
                    stavkaDodaj.setDatumVracanja(datumVracanja);
                    stavkaDodaj.setKnjiga(k);
                    
                    System.out.println(stavkaDodaj);
                    ModelTabeleStavkaIznajmljivanja mts=(ModelTabeleStavkaIznajmljivanja) gf.getjTableStavkeIznajmljivanja().getModel();
                    mts.dodajStavku(stavkaDodaj);
             
                
            }
            
        });
        
        gf.addBtnObrisiStavkuActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              obrisi(e);
          }

            private void obrisi(ActionEvent e) {
                
                int red=gf.getjTableStavkeIznajmljivanja().getSelectedRow();
                if(red==-1){
                    JOptionPane.showMessageDialog(gf, "Nije izabran red iz tabele stavke ");
                    return;
                }else{
                    ModelTabeleStavkaIznajmljivanja mts=(ModelTabeleStavkaIznajmljivanja) gf.getjTableStavkeIznajmljivanja().getModel();
                    StavkaIznajmljivanja st=mts.getLista().get(red);
                    mts.obrisiStavku(st);
                }
                
            }
      });
        
        gf.dodajIznajmljivanjeAddActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dodaj(e);
            }

            private void dodaj(ActionEvent e) {
                try{
                Iznajmljivanje novoIznajmljivanje=new Iznajmljivanje();
                ModelTabeleStavkaIznajmljivanja mts=(ModelTabeleStavkaIznajmljivanja) gf.getjTableStavkeIznajmljivanja().getModel();
                List<StavkaIznajmljivanja> stavkeIznajmljivanja=mts.getLista();
                novoIznajmljivanje.setStavke(stavkeIznajmljivanja);
                
                novoIznajmljivanje.setZaposleni(Koordinator.getInstance().getUlogovani());
                novoIznajmljivanje.setClanBiblioteke((ClanBiblioteke) gf.getjComboBoxClanBiblioteke().getSelectedItem());
                
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String datS=gf.getjTextFieldDatumIznajmljivanja().getText().trim();
                Date datumIznajmljivanja;
                    try {
                        datumIznajmljivanja = sdf.parse(datS);
                    } catch (ParseException ex) {
                        Logger.getLogger(DodajIzmeniDezurstvoController.class.getName()).log(Level.SEVERE, null, ex);
                        JOptionPane.showMessageDialog(gf, "Niste dobro upisali datum vracanja stavke");
                        return;
                    }
                Date danas=new Date();
                // Koristi Calendar za dodavanje 20 dana
                Calendar cal = Calendar.getInstance();
                cal.setTime(danas);
                cal.add(Calendar.DAY_OF_YEAR, 20);
                Date rok = cal.getTime();
                novoIznajmljivanje.setRokVracanja(rok);
                novoIznajmljivanje.setDatumIznajmljivanja(datumIznajmljivanja);
                novoIznajmljivanje.setNapomena(gf.getjTextAreaNapomena().getText());
                
                Komunikacija.getInstance().dodajIznajmljivanje(novoIznajmljivanje);
                
                JOptionPane.showMessageDialog(gf, "Sistem je uspesno dodao iznajmljivanje");
                }catch(Exception exc){
                    exc.printStackTrace();
                    JOptionPane.showMessageDialog(gf, "Sistem ne moze da doda iznajmljivanje");
                }
            }
            
        });
        
        gf.azurirajIznajmljivanjeAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
                Zaposleni z=(Zaposleni) gf.getjComboBoxZaposleni().getSelectedItem();
                ClanBiblioteke clan=(ClanBiblioteke) gf.getjComboBoxClanBiblioteke().getSelectedItem();
                int idIznajm=Integer.parseInt(gf.getjTextFieldID().getText());
                
                String datIzStr=gf.getjTextFieldDatumIznajmljivanja().getText().trim();
                Date datumIznajmljivanja;
                try {
                    datumIznajmljivanja = sdf.parse(datIzStr);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniIznajmljivanjeController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(gf, "Niste dobro upisali datum iznajmljivanja");
                    return;
                }
                
                String datRokVracanjaStr=gf.getjTextFieldRokVracanja().getText().trim();
                Date datumRokVracanja;
                try {
                    datumRokVracanja = sdf.parse(datRokVracanjaStr);
                } catch (ParseException ex) {
                    Logger.getLogger(DodajIzmeniIznajmljivanjeController.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(gf, "Niste dobro upisali rok vracanja");
                    return;
                }

                String napomena=gf.getjTextAreaNapomena().getText();
                ModelTabeleStavkaIznajmljivanja mts=(ModelTabeleStavkaIznajmljivanja) gf.getjTableStavkeIznajmljivanja().getModel();
                List<StavkaIznajmljivanja> stavke=mts.getLista();
     
                Iznajmljivanje iz=new Iznajmljivanje(idIznajm, z, clan, napomena, datumIznajmljivanja, datumRokVracanja, stavke);
                
                try{
                    Komunikacija.getInstance().azurirajIznajmljivanje(iz);
                    JOptionPane.showMessageDialog(gf, "uspeh");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(gf, "greska");
                    ex.printStackTrace();
                }
                 
            }
        });
        
        
    }

    public void otvoriFormu() {
        gf.setVisible(true);
        Zaposleni ulogovani=Koordinator.getInstance().getUlogovani();
        gf.getjLabelUlogovani().setText(ulogovani.getIme()+" "+ulogovani.getPrezime());
        
        ModelTabeleStavkaIznajmljivanja mts=new ModelTabeleStavkaIznajmljivanja(new ArrayList<>());
        gf.getjTableStavkeIznajmljivanja().setModel(mts);
        
        popuniComboBoxove();
        gf.getjButtonAzurirajIznajmljivanje().setVisible(false);
        gf.getjTextFieldID().setEnabled(false);
    }

    private void popuniComboBoxove() {
        List<Knjiga> sveKnjige=komunikacija.Komunikacija.getInstance().ucitajKnjige();
        gf.getjComboBoxKnjiga().removeAllItems();
        for(Knjiga k:sveKnjige){
            gf.getjComboBoxKnjiga().addItem(k);
        }
//        List<Zaposleni> sviZaposleni=komunikacija.Komunikacija.getInstance().ucitajZaposlene();
//        gf.getjComboBoxZaposleni().removeAllItems();
//        for(Zaposleni z:sviZaposleni){
//            gf.getjComboBoxZaposleni().addItem(z);
//        }
        
        List<ClanBiblioteke> sviClanovi=komunikacija.Komunikacija.getInstance().ucitajClanove();
        gf.getjComboBoxClanBiblioteke().removeAllItems();
        for(ClanBiblioteke cl: sviClanovi){
            gf.getjComboBoxClanBiblioteke().addItem(cl);
        }
        
    }

    public void otvoriFormu(FormaMod formaMod) {
        gf.setVisible(true);
        Zaposleni ulogovani=Koordinator.getInstance().getUlogovani();
        gf.getjLabelUlogovani().setText(ulogovani.getIme()+" "+ulogovani.getPrezime());
        
        List<StavkaIznajmljivanja> praznaLista=new ArrayList<>();
        ModelTabeleStavkaIznajmljivanja mts=new ModelTabeleStavkaIznajmljivanja(praznaLista);
        gf.getjTableStavkeIznajmljivanja().setModel(mts);

        gf.getjTextFieldID().setEnabled(false);
        if(formaMod==FormaMod.IZMENI){
            Iznajmljivanje iz=(Iznajmljivanje) Koordinator.getInstance().vratiParam("iznajmljivanje");
                mts.setLista(iz.getStavke());
            
                Date datumIznajmljivanja=iz.getDatumIznajmljivanja();
                Date datumRokVracanja=iz.getRokVracanja();
                
                
                String datumIznajmStr1=sdf.format(datumIznajmljivanja);
                String rokVracanjaStr1=sdf.format(datumRokVracanja);
                
                gf.getjTextFieldDatumIznajmljivanja().setText(datumIznajmStr1);
                gf.getjTextFieldRokVracanja().setText(rokVracanjaStr1);
                
                gf.getjTextFieldID().setText(iz.getIdIznajmljivanje()+"");
                gf.getjTextFieldID().setEnabled(false);

                List<ClanBiblioteke> clanovi1=komunikacija.Komunikacija.getInstance().ucitajClanove();
                List<Zaposleni> zaposleni1=komunikacija.Komunikacija.getInstance().ucitajZaposlene();
                gf.getjComboBoxClanBiblioteke().removeAllItems();
                gf.getjComboBoxZaposleni().removeAllItems();
                
                for(ClanBiblioteke clan:clanovi1){
                    if(clan==iz.getClanBiblioteke()){
                        gf.getjComboBoxClanBiblioteke().addItem(clan);
                    }
                }
                for(ClanBiblioteke clan:clanovi1){
                    if(clan!=iz.getClanBiblioteke()){
                        gf.getjComboBoxClanBiblioteke().addItem(clan);
                    }    
                }
                
                for(Zaposleni zap:zaposleni1){
                    if(zap==iz.getZaposleni()){
                        gf.getjComboBoxZaposleni().addItem(zap);
                    }
                }
                for(Zaposleni zap:zaposleni1){
                    if(zap!=iz.getZaposleni()){
                        gf.getjComboBoxZaposleni().addItem(zap);
                    }    
                }
                
                gf.getjTextAreaNapomena().setText(iz.getNapomena());
                gf.getjButtonPrijaviDezurstvo().setVisible(false);
                gf.getjButtonDodajIznajmljivanje().setVisible(false);
                
            
        }
    }

    public void osveziFormu() {
    }
    
    
}
