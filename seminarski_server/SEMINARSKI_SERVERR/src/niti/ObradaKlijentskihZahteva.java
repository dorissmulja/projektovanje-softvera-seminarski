/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package niti;

import controller.Controller;
import domen.ClanBiblioteke;
import domen.Iznajmljivanje;
import domen.KategorijaClana;
import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import komunikacija.Odgovor;
import komunikacija.Posiljalac;
import komunikacija.Primalac;
import komunikacija.Zahtev;
import komunikacija.Operacija;

/**
 *
 * @author smulj
 */
public class ObradaKlijentskihZahteva extends Thread {
    Socket socket;
    Posiljalac posiljalac;
    Primalac primalac;
    boolean kraj=false;

    public ObradaKlijentskihZahteva(Socket s) {
        try{
        this.socket = s;
        posiljalac=new Posiljalac(socket);
        primalac=new Primalac(socket);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    @Override
    public void run() {
        while(!kraj){
            try {
                Zahtev zahtev=(Zahtev) primalac.primi();
                Odgovor odgovor = new Odgovor();

                switch(zahtev.getOperacija()){
                    case LOGIN:
                        Zaposleni z= (Zaposleni) zahtev.getParametar();
                        z =Controller.getInstance().login(z);
                        odgovor.setOdgovor(z);
                        break;
                    case UCITAJ_CLANOVE:
                        List<ClanBiblioteke> clanovi= Controller.getInstance().ucitajClanove();
                        System.out.println(clanovi);
                        odgovor.setOdgovor(clanovi);
                        break;
                    case UCITAJ_ZAPOSLENE:
                        List<Zaposleni> zaposleni= Controller.getInstance().ucitajZaposlene();
                        odgovor.setOdgovor(zaposleni);    
                        break;
                    case OBRISI_ZAPOSLENOG:
                        try{
                        Zaposleni zaposleniBrisanje= (Zaposleni) zahtev.getParametar();
                        Controller.getInstance().obrisiZaposlenog(zaposleniBrisanje);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_ZAPOSLENOG:
                        Zaposleni zaposleniDodaj=(Zaposleni) zahtev.getParametar();
                        Controller.getInstance().dodajZaposlenog(zaposleniDodaj);
                        odgovor.setOdgovor(null);
                        break;
                    case AZURIRAJ_ZAPOSLENOG:
                        Zaposleni zaposleniAzuriraj=(Zaposleni) zahtev.getParametar();
                        Controller.getInstance().azurirajZaposlenog(zaposleniAzuriraj);
                        odgovor.setOdgovor(null);
                        break;
                    case DODAJ_KNJIGU:
                        Knjiga knjigaDodaj=(Knjiga) zahtev.getParametar();
                        Controller.getInstance().dodajKnjigu(knjigaDodaj);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_KNJIGE:
                        List<Knjiga> knjige= Controller.getInstance().ucitajKnjige();
                        odgovor.setOdgovor(knjige);    
                        break;  
                    case OBRISI_KNJIGU:
                        try{
                        Knjiga knjigaBrisanje= (Knjiga) zahtev.getParametar();
                        Controller.getInstance().obrisiKnjigu(knjigaBrisanje);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case AZURIRAJ_KNJIGU:
                        Knjiga knjigaAzuriraj=(Knjiga) zahtev.getParametar();
                        Controller.getInstance().azurirajKnjigu(knjigaAzuriraj);
                        odgovor.setOdgovor(null);
                        break; 
                    case UCITAJ_KATEGORIJE:
                        List<KategorijaClana> kategorije= Controller.getInstance().ucitajKategorije();
                        odgovor.setOdgovor(kategorije);    
                        break;
                    case DODAJ_KATEGORIJU:
                        KategorijaClana kategorijaDodaj=(KategorijaClana) zahtev.getParametar();
                        Controller.getInstance().dodajKategoriju(kategorijaDodaj);
                        odgovor.setOdgovor(null);
                        break;    
                    case OBRISI_KATEGORIJU:
                        try{
                        KategorijaClana kategorijaBrisanje= (KategorijaClana) zahtev.getParametar();
                        Controller.getInstance().obrisiKategoriju(kategorijaBrisanje);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case AZURIRAJ_KATEGORIJU:
                        KategorijaClana kategorijaAzuriraj=(KategorijaClana) zahtev.getParametar();
                        Controller.getInstance().azurirajKategoriju(kategorijaAzuriraj);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_TERMINE:
                        List<TerminDezurstva> termini= Controller.getInstance().ucitajTermine();
                        odgovor.setOdgovor(termini);    
                        break;
                    case DODAJ_TERMIN:
                        TerminDezurstva terminDodaj=(TerminDezurstva) zahtev.getParametar();
                        Controller.getInstance().dodajTermin(terminDodaj);
                        odgovor.setOdgovor(null);
                        break;    
                    case OBRISI_TERMIN:
                        try{
                        TerminDezurstva terminBrisanje= (TerminDezurstva) zahtev.getParametar();
                        Controller.getInstance().obrisiTermin(terminBrisanje);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case AZURIRAJ_TERMIN:
                        TerminDezurstva terminAzuriraj=(TerminDezurstva) zahtev.getParametar();
                        Controller.getInstance().azurirajTermin(terminAzuriraj);
                        odgovor.setOdgovor(null);
                        break; 
                    case OBRISI_CLANA:
                        try{
                        ClanBiblioteke clanZaBrisanje= (ClanBiblioteke) zahtev.getParametar();
                        Controller.getInstance().obrisiClana(clanZaBrisanje);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case DODAJ_CLANA:
                        ClanBiblioteke clanDodaj=(ClanBiblioteke) zahtev.getParametar();
                        Controller.getInstance().dodajClana(clanDodaj);
                        odgovor.setOdgovor(null);
                        break;
                    case AZURIRAJ_CLANA:
                        ClanBiblioteke clanAzuriraj=(ClanBiblioteke) zahtev.getParametar();
                        Controller.getInstance().azurirajClana(clanAzuriraj);
                        odgovor.setOdgovor(null);
                        break; 
                    case DODAJ_DEZURSTVO:
                        ZaposleniTermin zt=(ZaposleniTermin) zahtev.getParametar();
                        Controller.getInstance().dodajDezurstvo(zt);
                        odgovor.setOdgovor(null);
                        break;
                    case OBRISI_DEZURSTVO:
                        try{
                        ZaposleniTermin dezurstvoZaBrisanje= (ZaposleniTermin) zahtev.getParametar();
                        Controller.getInstance().obrisiDezurstvo(dezurstvoZaBrisanje);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case AZURIRAJ_DEZURSTVO:
                        ZaposleniTermin dezurstvoAzuriraj=(ZaposleniTermin) zahtev.getParametar();
                        Controller.getInstance().azurirajDezurstvo(dezurstvoAzuriraj);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_DEZURSTVA:
                        List<ZaposleniTermin> dezurstva= Controller.getInstance().ucitajDezurstva();
                        System.out.println(dezurstva);
                        odgovor.setOdgovor(dezurstva);
                        break;
                    case UCITAJ_IZNAJMLJIVANJA:
                        List<Iznajmljivanje> iznajmljivanja= Controller.getInstance().ucitajIznajmljivanja();
                        System.out.println(iznajmljivanja);
                        odgovor.setOdgovor(iznajmljivanja);
                        break;
                    case DODAJ_IZNAJMLJIVANJE:
                        Iznajmljivanje iz=(Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstance().dodajIznajmljivanje(iz);
                        odgovor.setOdgovor(null);
                        break;
                    case OBRISI_IZNAJMLJIVANJE:
                        try{
                        Iznajmljivanje iznajmljivanjeZaBrisanje= (Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstance().obrisiIznajmljivanje(iznajmljivanjeZaBrisanje);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case AZURIRAJ_IZNAJMLJIVANJE:
                        Iznajmljivanje iznajmljivanjeAzuriraj=(Iznajmljivanje) zahtev.getParametar();
                        Controller.getInstance().azurirajIznajmljivanje(iznajmljivanjeAzuriraj);
                        odgovor.setOdgovor(null);
                        break;
                    case UCITAJ_STAVKE:
                        int id=(int) zahtev.getParametar();
                        List<StavkaIznajmljivanja> stavke= Controller.getInstance().ucitajStavke(id);
                        System.out.println(stavke);
                        odgovor.setOdgovor(stavke);
                        break;
                    case OBRISI_STAVKU:
                        try{
                        StavkaIznajmljivanja stavkaZaBrisanje= (StavkaIznajmljivanja) zahtev.getParametar();
                        Controller.getInstance().obrisiStavku(stavkaZaBrisanje);
                        }catch(Exception e){
                            odgovor.setOdgovor(e);
                        }
                        break;
                    case AZURIRAJ_STAVKU:
                        StavkaIznajmljivanja stavkaAzuriraj=(StavkaIznajmljivanja) zahtev.getParametar();
                        Controller.getInstance().azurirajStavku(stavkaAzuriraj);
                        odgovor.setOdgovor(null);
                        break;
                    default:
                        System.out.println("Greska, ta operacija ne postoji");  
                }
                posiljalac.posalji(odgovor);
            } catch (Exception ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void prekiniNit(){
        kraj=true;
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ObradaKlijentskihZahteva.class.getName()).log(Level.SEVERE, null, ex);
        }
        interrupt();
    }
    
    
}
