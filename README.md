# 📚 Softverski sistem za evidenciju iznajmljivanja knjiga

Seminarski rad iz predmeta **Projektovanje softvera**, Univerzitet u Beogradu — Fakultet organizacionih nauka, Katedra za softversko inženjerstvo.

> **Mentor:** prof. dr Siniša Vlajić
> **Student:** Doris Šmulja
> **Godina:** 2024.

---

## 📖 O projektu

Sistem je razvijen kao rešenje za evidenciju izdavanja knjiga u biblioteci — praćenje članova, knjiga, iznajmljivanja i rasporeda dežurstava zaposlenih. Projekat je rađen po metodologiji **Larman** (Craig Larman), kroz sve faze softverskog inženjerstva:

- Prikupljanje korisničkih zahteva (verbalni opis, slučajevi korišćenja)
- Analiza (sistemske operacije, sekvencni dijagrami, ugovori o operacijama, konceptualni i relacioni model)
- Projektovanje (arhitektura, korisnički interfejs, aplikaciona logika, skladište podataka)
- Implementacija
- Testiranje

Detaljan opis celog procesa dostupan je u priloženom radu: [`SEMINARSKI.pdf`](./SEMINARSKI.pdf).

## 🏗️ Arhitektura

Sistem je realizovan kao **klijent-server aplikacija** prema **MVC (trosloJnoj)** arhitekturi, sa komunikacijom preko mrežnih soketa (razmena serijalizovanih Java objekata `Zahtev` / `Odgovor`).

```
├── seminarski_klijent/     → Klijentski projekat (korisnički interfejs, forme, kontroleri)
├── seminarski_server/      → Serverski projekat (aplikaciona logika, sistemske operacije, rad sa bazom)
└── seminarski_zajednicki/  → Zajednički projekat (domenske klase, klase za komunikaciju)
```

- **Korisnički interfejs** — ulazno-izlazna reprezentacija sistema (Swing forme)
- **Aplikaciona logika** — kontroler, poslovna logika (sistemske operacije) i broker baze podataka
- **Skladište podataka** — relaciona baza podataka

## 🧩 Glavni koncepti sistema

| Koncept | Funkcionalnosti |
|---|---|
| Iznajmljivanje | Kreiraj, Pretraži, Promeni |
| Član biblioteke | Kreiraj, Pretraži, Promeni, Obriši |
| Zaposleni | Prijavi, Kreiraj, Pretraži, Promeni, Obriši |
| Knjiga | Kreiraj, Pretraži, Promeni, Obriši |
| Kategorija člana | Kreiraj, Pretraži, Promeni, Obriši |
| Termin dežurstva | Ubaci, Pretraži, Promeni, Obriši |

## 🛠️ Tehnologije

- **Java 17**
- **MySQL** (MariaDB 10.4.32) — relaciona baza podataka
- **SQLyog** — alat za rad sa bazom
- **NetBeans IDE 15** — razvojno okruženje
- Komunikacija preko **Java soketa** (`ObjectOutputStream` / `ObjectInputStream`)

## 📂 Struktura projekta

**Serverski projekat** sadrži: kontroler aplikacione logike, sistemske operacije za sve entitete (član, iznajmljivanje, knjiga, kategorija, zaposleni, dežurstvo), broker baze podataka (`DbRepositoryGeneric`) i konfiguracione forme (baza, port).

**Klijentski projekat** sadrži: sve ekranske forme (prijava, evidencija članova, knjiga, iznajmljivanja, dežurstava), kontrolere korisničkog interfejsa i koordinator koji upravlja komunikacijom sa serverom.

**Zajednički projekat** sadrži: domenske klase (`Zaposleni`, `ClanBiblioteke`, `Iznajmljivanje`, `Knjiga`, `KategorijaClana`, `TerminDezurstva`...) i klase za mrežnu komunikaciju (`Zahtev`, `Odgovor`, `Posiljalac`, `Primalac`).

## 📄 Dokumentacija

Kompletan seminarski rad sa detaljnim opisom analize, projektovanja i implementacije nalazi se u fajlu [`SEMINARSKI.pdf`](./SEMINARSKI.pdf).
