/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import domen.ClanBiblioteke;
import domen.Iznajmljivanje;
import domen.KategorijaClana;
import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import java.util.List;
import operacije.clanovi.AzurirajClanaSO;
import operacije.clanovi.DodajClanaSO;
import operacije.clanovi.ObrisiClanaSO;
import operacije.login.LogInSO;
import operacije.clanovi.UcitajClanoveSO;
import operacije.dezurstva.AzurirajDezurstvoSO;
import operacije.dezurstva.DodajDezurstvoSO;
import operacije.dezurstva.ObrisiDezurstvoSO;
import operacije.dezurstva.UcitajDezurstvaSO;
import operacije.iznajmljivanje.AzurirajIznajmljivanjeSO;
import operacije.iznajmljivanje.DodajIznajmljivanjeSO;
import operacije.iznajmljivanje.ObrisiIznajmljivanjeSO;
import operacije.iznajmljivanje.UcitajIznajmljivanjaSO;
import operacije.kategorija.AzurirajKategorijuSO;
import operacije.kategorija.DodajKategorijuSO;
import operacije.kategorija.ObrisiKategorijuSO;
import operacije.kategorija.UcitajKategorijeSO;
import operacije.knjiga.AzurirajKnjiguSO;
import operacije.knjiga.DodajKnjiguSO;
import operacije.knjiga.ObrisiKnjiguSO;
import operacije.knjiga.UcitajKnjigeSO;
import operacije.stavke.AzurirajStavkuSO;
import operacije.stavke.ObrisiStavkuSO;
import operacije.stavke.UcitajStavkeSO;
import operacije.termin.AzurirajTerminSO;
import operacije.termin.DodajTerminSO;
import operacije.termin.ObrisiTerminSO;
import operacije.termin.UcitajTermineSO;
import operacije.zaposleni.AzurirajZaposlenogSO;
import operacije.zaposleni.KreirajZaposlenogSO;
import operacije.zaposleni.ObrisiZaposlenogSO;
import operacije.zaposleni.UcitajZaposleneSO;

/**
 *
 * @author smulj
 */
public class Controller {
    public static Controller instance;

    public Controller() {
    }

    public static Controller getInstance() {
        if(instance==null)
            instance=new Controller();
        return instance;
    }

    public Zaposleni login(Zaposleni z) throws Exception {
        LogInSO operacija=new LogInSO();
        operacija.izvrsi(z, null);
        System.out.println("KLASA KONTROLER: "+operacija.getZaposleni());
        return operacija.getZaposleni();
    }

    public List<ClanBiblioteke> ucitajClanove() throws Exception {
        UcitajClanoveSO operacija=new UcitajClanoveSO();
        operacija.izvrsi(null,null);
        System.out.println("KLASA KONTROLER: "+operacija.getClanovi());        
        return operacija.getClanovi();
    }

    public List<Zaposleni> ucitajZaposlene() throws Exception {
        UcitajZaposleneSO operacija=new UcitajZaposleneSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA KONTROLER:"+operacija.getZaposleni());
        return operacija.getZaposleni();
    }

    public void obrisiZaposlenog(Zaposleni zaposleniBrisanje) throws Exception {
        ObrisiZaposlenogSO operacije= new ObrisiZaposlenogSO();
        operacije.izvrsi(zaposleniBrisanje, null);
                
    }

    public void dodajZaposlenog(Zaposleni zaposleniDodaj) throws Exception {
        KreirajZaposlenogSO operacija=new KreirajZaposlenogSO();
        operacija.izvrsi(zaposleniDodaj, null);
    }

    public void azurirajZaposlenog(Zaposleni zaposleniAzuriraj) throws Exception {
        AzurirajZaposlenogSO operacija=new AzurirajZaposlenogSO();
        operacija.izvrsi(zaposleniAzuriraj, null);
    }

    public void dodajKnjigu(Knjiga knjigaDodaj) throws Exception {
        DodajKnjiguSO operacija=new DodajKnjiguSO();
        operacija.izvrsi(knjigaDodaj, null);
    }

    public List<Knjiga> ucitajKnjige() throws Exception {
        UcitajKnjigeSO operacija=new UcitajKnjigeSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA KONTROLER:"+operacija.getKnjige());
        return operacija.getKnjige();
    }

    public void obrisiKnjigu(Knjiga knjigaBrisanje) throws Exception {
        ObrisiKnjiguSO operacija=new ObrisiKnjiguSO();
        operacija.izvrsi(knjigaBrisanje, null);
    }

    public void azurirajKnjigu(Knjiga knjigaAzuriraj) throws Exception {
        AzurirajKnjiguSO operacija=new AzurirajKnjiguSO();
        operacija.izvrsi(knjigaAzuriraj, null);
    }

    public List<KategorijaClana> ucitajKategorije() throws Exception {
        UcitajKategorijeSO operacija=new UcitajKategorijeSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA KONTROLER:"+operacija.getKategorije());
        return operacija.getKategorije();
    }

    public void obrisiKategoriju(KategorijaClana kategorijaBrisanje) throws Exception {
        ObrisiKategorijuSO operacija=new ObrisiKategorijuSO();
        operacija.izvrsi(kategorijaBrisanje, null);
    }

    public void azurirajKategoriju(KategorijaClana kategorijaAzuriraj) throws Exception {
        AzurirajKategorijuSO operacija=new AzurirajKategorijuSO();
        operacija.izvrsi(kategorijaAzuriraj, null);
    }

    public void dodajKategoriju(KategorijaClana kategorijaDodaj) throws Exception {
        DodajKategorijuSO operacija= new DodajKategorijuSO();
        operacija.izvrsi(kategorijaDodaj, null);
    }

    public List<TerminDezurstva> ucitajTermine() throws Exception {
        UcitajTermineSO operacija=new UcitajTermineSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA KONTROLER:"+operacija.getTermini());
        return operacija.getTermini();
    }

    public void dodajTermin(TerminDezurstva terminDodaj) throws Exception {
        DodajTerminSO operacija=new DodajTerminSO();
        operacija.izvrsi(terminDodaj, null);
    }

    public void azurirajTermin(TerminDezurstva terminAzuriraj) throws Exception {
        AzurirajTerminSO op=new AzurirajTerminSO();
        op.izvrsi(terminAzuriraj, null);
    }

    public void obrisiTermin(TerminDezurstva terminBrisanje) throws Exception {
        ObrisiTerminSO op=new ObrisiTerminSO();
        op.izvrsi(terminBrisanje, null);
    }

    public void obrisiClana(ClanBiblioteke clanZaBrisanje) throws Exception {
        ObrisiClanaSO op=new ObrisiClanaSO();
        op.izvrsi(clanZaBrisanje, null);
    }

    public void dodajClana(ClanBiblioteke clanDodaj) throws Exception {
        DodajClanaSO op=new DodajClanaSO();
        op.izvrsi(clanDodaj, null);
    }

    public void azurirajClana(ClanBiblioteke clanAzuriraj) throws Exception {
        AzurirajClanaSO op=new AzurirajClanaSO();
        op.izvrsi(clanAzuriraj, null);
    }

    public void dodajDezurstvo(ZaposleniTermin zt) throws Exception {
        DodajDezurstvoSO op=new DodajDezurstvoSO();
        op.izvrsi(zt, null);
    }

    public void obrisiDezurstvo(ZaposleniTermin dezurstvoZaBrisanje) throws Exception {
        ObrisiDezurstvoSO op=new ObrisiDezurstvoSO();
        op.izvrsi(dezurstvoZaBrisanje, null);
    }

    public void azurirajDezurstvo(ZaposleniTermin dezurstvoAzuriraj) throws Exception {
        AzurirajDezurstvoSO op=new AzurirajDezurstvoSO();
        op.izvrsi(dezurstvoAzuriraj, null);
    }

    public List<ZaposleniTermin> ucitajDezurstva() throws Exception {
        UcitajDezurstvaSO operacija=new UcitajDezurstvaSO();
        operacija.izvrsi(null, null);
        System.out.println("KLASA KONTROLER:"+operacija.getDezurstva());
        return operacija.getDezurstva();
    }

    public void dodajIznajmljivanje(Iznajmljivanje iz) throws Exception {
        DodajIznajmljivanjeSO op=new DodajIznajmljivanjeSO();
        op.izvrsi(iz, null);
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() throws Exception {
        UcitajIznajmljivanjaSO op=new UcitajIznajmljivanjaSO();
        op.izvrsi(null, null);
        System.out.println("KLASA KONTROLER:"+op.getIznajmljivanja());
        return op.getIznajmljivanja();
    }

    public void obrisiIznajmljivanje(Iznajmljivanje iznajmljivanjeZaBrisanje) throws Exception {
        ObrisiIznajmljivanjeSO op=new ObrisiIznajmljivanjeSO();
        op.izvrsi(iznajmljivanjeZaBrisanje, null);
    }

    public void azurirajIznajmljivanje(Iznajmljivanje iznajmljivanjeAzuriraj) throws Exception {
        AzurirajIznajmljivanjeSO op=new AzurirajIznajmljivanjeSO();
        op.izvrsi(iznajmljivanjeAzuriraj, null);
    }

    public List<StavkaIznajmljivanja> ucitajStavke(int id) throws Exception {
        UcitajStavkeSO op=new UcitajStavkeSO();
        op.izvrsi(id, null);
        System.out.println("KLASA KONTROLER:"+op.getStavke());
        return op.getStavke();
    }

    public void obrisiStavku(StavkaIznajmljivanja stavkaZaBrisanje) throws Exception {
        ObrisiStavkuSO op=new ObrisiStavkuSO();
        op.izvrsi(stavkaZaBrisanje, null);
    }

    public void azurirajStavku(StavkaIznajmljivanja stavkaAzuriraj) throws Exception {
        AzurirajStavkuSO op=new AzurirajStavkuSO();
        op.izvrsi(stavkaAzuriraj, null);
    }
    
    
}
