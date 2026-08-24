/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package kontroleri;


import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import forme.FormaMod;
import forme.model.ModelTabeleKnjige;
import forme.stavka.DodajIzmeniStavkuForma;
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
public class DodajIzmeniStavkaController {
   private DodajIzmeniStavkuForma dsf;
   SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
   private Knjiga izabranaKnjiga;

    public DodajIzmeniStavkaController(DodajIzmeniStavkuForma dsf) {
        this.dsf = dsf;
        addActionListener();
    }

    public void otvoriFormu(FormaMod mod) {
        pripremiFormu(mod);
        dsf.setVisible(true);
    }

    private void addActionListener() {
//        dsf.dodajAddActionListener(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                dodaj(e);
//            }
//
//            private void dodaj(ActionEvent e) {
//                
//                String ime=dcf.getjTextFieldIme().getText().trim();
//                String prezime=dcf.getjTextFieldPrezime().getText().trim();
//                String email=dcf.getjTextFieldEmail().getText().trim();
//                String datumUclanjenjaText = dcf.getjTextFielDatumUclanjenja().getText().trim();
//                String datumIstekaText = dcf.getjTextFieldDatumIsteka().getText().trim();
//
//                SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
//                //sdf.setLenient(false); // stroga provjera datuma
//                KategorijaClana kategorijaClana=(KategorijaClana) dcf.getjComboBoxKategorija().getSelectedItem();
//
//                try {
//                    Date datumUclanjenja = sdf.parse(datumUclanjenjaText);
//                    Date datumIsteka = sdf.parse(datumIstekaText);
//
//                    ClanBiblioteke c= new ClanBiblioteke(-1,ime, prezime, email, datumUclanjenja, datumIsteka,  kategorijaClana);
//                
//                    try{
//                    Komunikacija.getInstance().dodajClana(c);
//                        JOptionPane.showMessageDialog(dcf, "Sistem je dodao knjigu");
//                        dcf.dispose();
//                    }catch(Exception ex){
//                        JOptionPane.showMessageDialog(dcf, "Sistem ne može da doda knjigu");
//                        ex.printStackTrace();
//                    }
//                } catch (ParseException pe) {
//                    JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy", "Greška", JOptionPane.ERROR_MESSAGE);
//                }   
//            }
//            
//        });
        dsf.azurirajAddActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                izmeni(e);
            }

            private void izmeni(ActionEvent e) {
               
                int rbStavke=Integer.parseInt(dsf.getjTextFieldRbStavke().getText());
                int idIznajmljivanja=Integer.parseInt(dsf.getjTextFieldIdIznajmljivanja().getText());
                double cenaKazne=Double.parseDouble(dsf.getjTextFieldCenaKazne().getText());
                
                int red=dsf.getjTableKnjige().getSelectedRow();
                if(red!=-1){
                ModelTabeleKnjige mtk=(ModelTabeleKnjige) dsf.getjTableKnjige().getModel();
                Knjiga knjiga=mtk.getLista().get(red);
                izabranaKnjiga=knjiga;
                }
                Date datumVracanja=null;
                String datumVracanjaText = dsf.getjTextFieldDatumVracanja().getText().trim();
                if(datumVracanjaText=="" || datumVracanjaText.isBlank() || datumVracanjaText.isEmpty() || datumVracanjaText.length()==0){
                    datumVracanja=null;
                }
                else{
                    try{
                    datumVracanja = sdf.parse(datumVracanjaText);
                    } catch (ParseException pe) {
                    JOptionPane.showMessageDialog(null, "Datum mora biti u formatu dd.MM.yyyy", "Greška", JOptionPane.ERROR_MESSAGE);
                }
                }
                    try{
                        StavkaIznajmljivanja st=new StavkaIznajmljivanja(rbStavke,idIznajmljivanja, datumVracanja, cenaKazne, izabranaKnjiga);
                        Komunikacija.getInstance().azurirajStavku(st);
                        JOptionPane.showMessageDialog(dsf, "Sistem je zapamtio stavku");
                        dsf.dispose();
                    }catch(Exception ex){
                        JOptionPane.showMessageDialog(dsf, "Sistem ne može da zapamti stavku");
                        ex.printStackTrace();
                    }
                
                 
            }
        });
        
        dsf.addBtnPretraziActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              String naziv=dsf.getjTextFieldNazivPretrazi().getText().trim();
              String autor=dsf.getjTextFieldAutorPretrazi().getText().trim();
              ModelTabeleKnjige mtk=(ModelTabeleKnjige) dsf.getjTableKnjige().getModel();
              mtk.pretrazi(naziv,autor);
          }
      });
        dsf.addBtnResetujActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              osveziFormu();
          }

            private void osveziFormu() {
                List<Knjiga> knjige= komunikacija.Komunikacija.getInstance().ucitajKnjige();
                ModelTabeleKnjige mtk=new ModelTabeleKnjige(knjige);
                dsf.getjTableKnjige().setModel(mtk);
            }
      });
        
        dsf.addBtnIzaberiKnjiguActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int red=dsf.getjTableKnjige().getSelectedRow();
                if(red!=-1){
                    ModelTabeleKnjige mtk=(ModelTabeleKnjige) dsf.getjTableKnjige().getModel();
                    Knjiga knjiga=mtk.getLista().get(red);
                    izabranaKnjiga=knjiga;
                    
                    dsf.getjTextFieldNazivKnjige().setText(izabranaKnjiga.toString());
                }else{
                    JOptionPane.showMessageDialog(dsf, "Niste izabrali red iz tabele knjiga");
                }
            }

        
        });
    }

    private void pripremiFormu(FormaMod mod) {
        
        List<Knjiga> sveKnjige=Komunikacija.getInstance().ucitajKnjige();
        ModelTabeleKnjige mtk=new ModelTabeleKnjige(sveKnjige);
        dsf.getjTableKnjige().setModel(mtk);
                
        switch(mod){
//            case DODAJ:
//                dcf.getjButtonIzmeni().setVisible(false);
//                dcf.getjButtonDodaj().setVisible(true);
//                dcf.getjButtonDodaj().setEnabled(true);
//                dcf.getjTextFieldID().setEnabled(false);
//                
//                List<KategorijaClana> kategorije= komunikacija.Komunikacija.getInstance().ucitajKategorije();
//                for(KategorijaClana kateg:kategorije){
//                    dcf.getjComboBoxKategorija().addItem(kateg);
//                }
//                break;
            case IZMENI:
                dsf.getjTextFieldRbStavke().setEnabled(false);
                dsf.getjTextFieldIdIznajmljivanja().setEnabled(false);
                dsf.getjButtonIzmeni().setVisible(true);
                dsf.getjButtonDodaj().setVisible(false);
                dsf.getjButtonIzmeni().setEnabled(true);
                
                StavkaIznajmljivanja st=(StavkaIznajmljivanja) Koordinator.getInstance().vratiParam("stavka");
                
                dsf.getjTextFieldRbStavke().setText(st.getRb()+"");
                dsf.getjTextFieldIdIznajmljivanja().setText(st.getIznajmljivanje()+"");
                
                Date datumVracanja=st.getDatumVracanja();
                String datumVracStr;
                if(datumVracanja==null){
                    datumVracStr="";
                }else{
                    datumVracStr=sdf.format(datumVracanja);
                }
                dsf.getjTextFieldDatumVracanja().setText(datumVracStr);
                dsf.getjTextFieldCenaKazne().setText(st.getCenaKazne()+"");
                
                Knjiga knjiga=st.getKnjiga();
                dsf.getjTextFieldNazivKnjige().setText(knjiga.toString());
                this.izabranaKnjiga=knjiga;
                
                
                break;
            
            }   
    } 
}
