/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package glavniKontroler;

import domen.Zaposleni;
import forme.zaposleni.DodajIzmeniZaposlenogForma;
import forme.FormaMod;
import forme.GlavnaForma;
import forme.LogInForma;
import forme.clan.DodajIzmeniClanForma;
import forme.clan.PrikazSvihClanovaForma;
import forme.iznajmljivanje.DodajIzmeniIznajmljivanjeForma;
import forme.iznajmljivanje.PrikaziIznajmljivanjaForma;
import forme.kategorijaclana.DodajIzmeniKategorijuForma;
import forme.kategorijaclana.PrikaziSveKategorijeForma;
import forme.knjiga.DodajIzmeniKnjiguForma;
import forme.knjiga.PrikaziSveKnjigeForma;
import forme.stavka.DodajIzmeniStavkuForma;
import forme.termin.DodajIzmeniTerminForma;
import forme.termin.PrikaziTermineForma;
import forme.zaposleni.PrikazSvihZaposlenihForma;
import forme.zaposleni_termin.DodajIzmeniDezurstvoForma;
import forme.zaposleni_termin.PrikaziZaposleniTerminForma;
import java.util.HashMap;
import java.util.Map;
import kontroleri.DodajIzmeniClanaController;
import kontroleri.DodajIzmeniDezurstvoController;
import kontroleri.DodajIzmeniIznajmljivanjeController;
import kontroleri.DodajIzmeniKategorijuController;
import kontroleri.DodajIzmeniKnjiguController;
import kontroleri.DodajIzmeniStavkaController;
import kontroleri.DodajIzmeniTerminController;
import kontroleri.DodajIzmeniZaposlenogController;
import kontroleri.GlavnaFormaController;
import kontroleri.LogInController;
import kontroleri.PrikazClanovaController;
import kontroleri.PrikazKnjigaController;
import kontroleri.PrikazZaposlenihController;
import kontroleri.PrikaziDezurstvaController;
import kontroleri.PrikaziIznajmljivanjaController;
import kontroleri.PrikaziKategorijeController;
import kontroleri.PrikaziTermineController;

/**
 *
 * @author smulj
 */
public class Koordinator {
    private static Koordinator instance;
    private Zaposleni ulogovani;
    private LogInController loginController;
    private GlavnaFormaController glavnaFormaController;
    private Map<String, Object> parametri;
    
    private PrikazClanovaController prikazClanovaController;
    private PrikazZaposlenihController prikazZaposlenihController;
    private PrikazKnjigaController prikaziKnjigeController;
    private PrikaziKategorijeController prikaziKategorijeController;
    private PrikaziTermineController prikaziTermineController;
    private PrikaziDezurstvaController prikaziDezurstvaController;
    private PrikaziIznajmljivanjaController prikaziIznajmljivanjaController;

    private DodajIzmeniZaposlenogController dodajZaposlenogController;
    private DodajIzmeniKnjiguController dodajKnjiguController;
    private DodajIzmeniKategorijuController dodajKategorijuController;
    private DodajIzmeniTerminController dodajTerminController;
    private DodajIzmeniClanaController dodajClanaController;
    private DodajIzmeniDezurstvoController dodajDezurstvoController;
    private DodajIzmeniIznajmljivanjeController dodajIznajmljivanjeController;
    private DodajIzmeniStavkaController dodajStavkaController;
    
    public Koordinator() {
        parametri=new HashMap<>();
    }

    public static Koordinator getInstance() {
        if(instance==null)
            instance=new Koordinator();
        return instance;
    }

    public void otvoriLogInFormu() {
        loginController=new LogInController(new LogInForma());
        loginController.otvoriFormu();
    }

    public void otvoriGlavnuFormu() {
        glavnaFormaController= new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu();
    }

    public void setUlogovani(Zaposleni ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Zaposleni getUlogovani() {
        return ulogovani;
    }

    public void otvoriPrikazClanovaFormu() {
        prikazClanovaController= new PrikazClanovaController(new PrikazSvihClanovaForma());
        prikazClanovaController.otvoriFormu();
    }

    public void otvoriPrikazZaposlenihFormu() {
        prikazZaposlenihController=new PrikazZaposlenihController(new PrikazSvihZaposlenihForma());
        prikazZaposlenihController.otvoriFormu();
    }

    public void otvoriDodajZaposlenogFormu() {
        dodajZaposlenogController = new DodajIzmeniZaposlenogController(new DodajIzmeniZaposlenogForma());
        dodajZaposlenogController.otvoriFormu(FormaMod.DODAJ);
    }

    
    public void dodajParam(String s, Object o){
        parametri.put(s, o);
    }
    
    public Object vratiParam(String s){
        return parametri.get(s);
    }

    public void otvoriAzurirajZaposlenogFormu() {
        dodajZaposlenogController = new DodajIzmeniZaposlenogController(new DodajIzmeniZaposlenogForma());
        dodajZaposlenogController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormu() {
        prikazZaposlenihController.osveziFormu();
    }

    public void otvoriDodajKnjiguFormu() {
        dodajKnjiguController=new DodajIzmeniKnjiguController(new DodajIzmeniKnjiguForma());
        dodajKnjiguController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikazKnjigaFormu() {
        prikaziKnjigeController= new PrikazKnjigaController(new PrikaziSveKnjigeForma());
        prikaziKnjigeController.otvoriFormu();
    }

    public void otvoriAzurirajKnjiguFormu() {
        dodajKnjiguController=new DodajIzmeniKnjiguController(new DodajIzmeniKnjiguForma());
        dodajKnjiguController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuKnjige() {
        prikaziKnjigeController.osveziFormu();
    }

    public void otvoriPrikaziKategorijeFormu() {
        prikaziKategorijeController=new PrikaziKategorijeController(new PrikaziSveKategorijeForma());
        prikaziKategorijeController.otvoriFormu();
    }

    public void otvoriAzurirajKategorijuFormu() {
        dodajKategorijuController=new DodajIzmeniKategorijuController(new DodajIzmeniKategorijuForma());
        dodajKategorijuController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuKategorije() {
        prikaziKategorijeController.osveziFormu();
    }

    public void otvoriDodajKategorijaFormu() {
        dodajKategorijuController=new DodajIzmeniKategorijuController(new DodajIzmeniKategorijuForma());
        dodajKategorijuController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriDodajTerminFormu() {
        dodajTerminController=new DodajIzmeniTerminController(new DodajIzmeniTerminForma());
        dodajTerminController.otvoriFormu(FormaMod.DODAJ);
    }

    public void otvoriPrikaziTerminFormu() {
        prikaziTermineController=new PrikaziTermineController(new PrikaziTermineForma());
        prikaziTermineController.otvoriFormu();
    }

    public void otvoriAzurirajTerminFormu() {
        dodajTerminController=new DodajIzmeniTerminController(new DodajIzmeniTerminForma());
        dodajTerminController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuTermin() {
        prikaziTermineController.osveziFormu();
    }

    public void otvoriAzurirajClanaFormu() {
        dodajClanaController=new DodajIzmeniClanaController(new DodajIzmeniClanForma());
        dodajClanaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void otvoriDodajClanaFormu() {
        dodajClanaController=new DodajIzmeniClanaController(new DodajIzmeniClanForma());
        dodajClanaController.otvoriFormu(FormaMod.DODAJ);
    }

    public void osveziFormuClan() {
        prikazClanovaController.osveziFormu();
    }

    public void otvoriDodajZaposleniTerminFormu() {
        dodajDezurstvoController=new DodajIzmeniDezurstvoController(new DodajIzmeniDezurstvoForma());
        dodajDezurstvoController.otvoriFormu(FormaMod.DODAJ);    }

    public void otvoriPrikaziZaposleniTerminFormu() {
        prikaziDezurstvaController=new PrikaziDezurstvaController(new PrikaziZaposleniTerminForma());
        prikaziDezurstvaController.otvoriFormu();
    }

    public void otvoriAzurirajDezurstvoFormu() {
        dodajDezurstvoController=new DodajIzmeniDezurstvoController(new DodajIzmeniDezurstvoForma());
        dodajDezurstvoController.otvoriFormu(FormaMod.IZMENI);
    }
    
    public void osveziFormuDezurstvo() {
        prikaziDezurstvaController.osveziFormu();
    }

    public void otvoriPrikaziIznajmljivanjaFormu() {
        prikaziIznajmljivanjaController=new PrikaziIznajmljivanjaController(new PrikaziIznajmljivanjaForma());
        prikaziIznajmljivanjaController.otvoriFormu();
    }

    public void otvoriAzurirajIznajmljivanjeFormu() {
        dodajIznajmljivanjeController=new DodajIzmeniIznajmljivanjeController(new DodajIzmeniIznajmljivanjeForma());
        dodajIznajmljivanjeController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuIznajmljivanje() {
        glavnaFormaController.osveziFormu();
    }

    public void otvoriAzurirajStavkuForma() {
        dodajStavkaController=new DodajIzmeniStavkaController(new DodajIzmeniStavkuForma());
        dodajStavkaController.otvoriFormu(FormaMod.IZMENI);
    }

    public void osveziFormuIznajmljivanjeStavke() {
        prikaziIznajmljivanjaController.osveziTabeluStavki();
    }

    public void otvoriGlavnuFormu(FormaMod formaMod) {
        glavnaFormaController= new GlavnaFormaController(new GlavnaForma());
        glavnaFormaController.otvoriFormu(formaMod);
    }

    
    
    
    
   
}
