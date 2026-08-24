/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package komunikacija;

import domen.ClanBiblioteke;
import domen.Iznajmljivanje;
import domen.KategorijaClana;
import domen.Knjiga;
import domen.StavkaIznajmljivanja;
import domen.TerminDezurstva;
import domen.Zaposleni;
import domen.ZaposleniTermin;
import glavniKontroler.Koordinator;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author smulj
 */
public class Komunikacija {
    private Socket soket;
    private Posiljalac posiljalac;
    private Primalac primalac;
    private static Komunikacija instance;

    public Komunikacija() {
    }

    public static Komunikacija getInstance() {
        if(instance==null)
            instance=new Komunikacija();
        return instance;
    }
    
    public void konekcija(){
        try {
            soket=new Socket("localhost",9000);
            primalac=new Primalac(soket);
            posiljalac=new Posiljalac(soket);
        } catch (IOException ex) {
            System.out.println("SERVER NIJE POVEZAN");
        }
    }

    public Zaposleni login(String ki, String sifra) {
        Zaposleni z=new Zaposleni();
        z.setKorisnickoIme(ki);
        z.setSifra(sifra);
        Zahtev zahtev=new Zahtev(Operacija.LOGIN, z);
        
        posiljalac.posalji(zahtev);
        System.out.println("zahtev je poslat");
        Odgovor odgovor=(Odgovor) primalac.primi();
        z=(Zaposleni) odgovor.getOdgovor();
        System.out.println("odgovor je primljen");
        return z;
    }

    public List<ClanBiblioteke> ucitajClanove() {
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_CLANOVE, null);
        List<ClanBiblioteke> clanovi=new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        ///
        Odgovor odg=(Odgovor) primalac.primi();
        clanovi=(List<ClanBiblioteke>) odg.getOdgovor();
        return clanovi;
    }

    public List<Zaposleni> ucitajZaposlene() {
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_ZAPOSLENE, null);
        List<Zaposleni> zaposleni=new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        ///
        Odgovor odg=(Odgovor) primalac.primi();
        zaposleni=(List<Zaposleni>) odg.getOdgovor();
        
        return zaposleni;
    }

    public void obrisiZaposlenog(Zaposleni z) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
            
        }
    }

    public void dodajZaposlenog(Zaposleni z) {
        Zahtev zahtev=new Zahtev(Operacija.DODAJ_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");  
        }
    }

    public void azurirajZaposlenog(Zaposleni z) {
        Zahtev zahtev=new Zahtev(Operacija.AZURIRAJ_ZAPOSLENOG, z);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
            Koordinator.getInstance().osveziFormu();
        }else{
            System.out.println("Greska");  
        }
    }

    public void dodajKnjigu(Knjiga k) {
       Zahtev zahtev=new Zahtev(Operacija.DODAJ_KNJIGU, k);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");  
         
        }
    }

    public void obrisiKnjigu(Knjiga k) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_KNJIGU, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
            
        }
    }

    public List<Knjiga> ucitajKnjige() {
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_KNJIGE, null);
        List<Knjiga> knjige=new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        ///
        Odgovor odg=(Odgovor) primalac.primi();
        knjige=(List<Knjiga>) odg.getOdgovor();
        
        return knjige;
    }

    public void azurirajKnjigu(Knjiga k) {
        Zahtev zahtev=new Zahtev(Operacija.AZURIRAJ_KNJIGU, k);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
            Koordinator.getInstance().osveziFormuKnjige();
        }else{
            System.out.println("Greska");  
        }
    }

    public void obrisiKategoriju(KategorijaClana k) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_KATEGORIJU, k);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
            
        }
    }

    public List<KategorijaClana> ucitajKategorije() {
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_KATEGORIJE, null);
        List<KategorijaClana> kategorije=new ArrayList<>();
        
        posiljalac.posalji(zahtev);
  
        Odgovor odg=(Odgovor) primalac.primi();
        kategorije=(List<KategorijaClana>) odg.getOdgovor();
        
        return kategorije;
    }

    public void dodajKategoriju(KategorijaClana k) {
        Zahtev zahtev=new Zahtev(Operacija.DODAJ_KATEGORIJU, k);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");  
         
        }
    }

    public void azurirajKategoriju(KategorijaClana k) {
        Zahtev zahtev=new Zahtev(Operacija.AZURIRAJ_KATEGORIJU, k);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
            Koordinator.getInstance().osveziFormuKategorije();
        }else{
            System.out.println("Greska");  
        }
    }

    public List<TerminDezurstva> ucitajTermine() {
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_TERMINE, null);
        List<TerminDezurstva> termini=new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        ///
        Odgovor odg=(Odgovor) primalac.primi();
        termini=(List<TerminDezurstva>) odg.getOdgovor();
        
        return termini;
    }

    public void obrisiTermin(TerminDezurstva t) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_TERMIN, t);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
            
        }
    }

    public void dodajTermin(TerminDezurstva t) {
        Zahtev zahtev=new Zahtev(Operacija.DODAJ_TERMIN, t);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");  
         
        }
    }

    public void azurirajTermin(TerminDezurstva t) {
        Zahtev zahtev=new Zahtev(Operacija.AZURIRAJ_TERMIN, t);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
            Koordinator.getInstance().osveziFormuTermin();
        }else{
            System.out.println("Greska");  
        }
    }

    public void obrisiClana(ClanBiblioteke c) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_CLANA, c);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
            
        }
    }

    public void dodajClana(ClanBiblioteke c) {
        Zahtev zahtev=new Zahtev(Operacija.DODAJ_CLANA, c);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");  
         
        }
    }

    public void azurirajClana(ClanBiblioteke c) {
        Zahtev zahtev=new Zahtev(Operacija.AZURIRAJ_CLANA, c);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
            Koordinator.getInstance().osveziFormuClan();
        }else{
            System.out.println("Greska");  
        }
    }

    public List<ZaposleniTermin> ucitajDezurstva() {
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_DEZURSTVA, null);
        List<ZaposleniTermin> dezurstva=new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        ///
        Odgovor odg=(Odgovor) primalac.primi();
        dezurstva=(List<ZaposleniTermin>) odg.getOdgovor();
        
        return dezurstva;
    }

    public void obrisiDezurstvo(ZaposleniTermin zt) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_DEZURSTVO, zt);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
            
        }
    }

    public void dodajDezurstvo(ZaposleniTermin zt) {
        Zahtev zahtev=new Zahtev(Operacija.DODAJ_DEZURSTVO, zt);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");  
         
        }
    }

    public void azurirajDezurstvo(ZaposleniTermin zt) {
        Zahtev zahtev=new Zahtev(Operacija.AZURIRAJ_DEZURSTVO, zt);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
            Koordinator.getInstance().osveziFormuDezurstvo();
        }else{
            System.out.println("Greska");  
        }
    }

    public List<Iznajmljivanje> ucitajIznajmljivanja() {
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_IZNAJMLJIVANJA, null);
        List<Iznajmljivanje> iznajmljivanja=new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        ///
        Odgovor odg=(Odgovor) primalac.primi();
        iznajmljivanja=(List<Iznajmljivanje>) odg.getOdgovor();
        
        return iznajmljivanja;
    }

    public void obrisiIznajmljivanje(Iznajmljivanje iz) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_IZNAJMLJIVANJE, iz);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
            
        }
    }

    public List<StavkaIznajmljivanja> ucitajStavke(int id) {
        Zahtev zahtev=new Zahtev(Operacija.UCITAJ_STAVKE, id);
        List<StavkaIznajmljivanja> stavke=new ArrayList<>();
        
        posiljalac.posalji(zahtev);
        ///
        Odgovor odg=(Odgovor) primalac.primi();
        stavke=(List<StavkaIznajmljivanja>) odg.getOdgovor();
        
        return stavke;
    }

    public void azurirajIznajmljivanje(Iznajmljivanje iz) {
        Zahtev zahtev=new Zahtev(Operacija.AZURIRAJ_IZNAJMLJIVANJE, iz);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
           // Koordinator.getInstance().osveziFormuIznajmljivanje();
        }else{
            System.out.println("Greska");  
        }
    }

    public void obrisiStavku(StavkaIznajmljivanja st) throws Exception {
        Zahtev zahtev=new Zahtev(Operacija.OBRISI_STAVKU, st);
        posiljalac.posalji(zahtev);
        
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");
            ((Exception)odg.getOdgovor()).printStackTrace();
            throw new Exception("GRESKA");
            
        }
    }

    public void azurirajStavku(StavkaIznajmljivanja st) {
        Zahtev zahtev=new Zahtev(Operacija.AZURIRAJ_STAVKU, st);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
            Koordinator.getInstance().osveziFormuIznajmljivanjeStavke();
        }else{
            System.out.println("Greska");  
        }
    }

    public void dodajIznajmljivanje(Iznajmljivanje novoIznajmljivanje) {
        Zahtev zahtev=new Zahtev(Operacija.DODAJ_IZNAJMLJIVANJE, novoIznajmljivanje);
        posiljalac.posalji(zahtev);
        Odgovor odg= (Odgovor) primalac.primi();
        if(odg.getOdgovor()==null){
            System.out.println("Uspeh");
        }else{
            System.out.println("Greska");  
         
        }
    }
    
    
}
