import java.io.*;
import java.util.*;

public class Bibliotekarz {
	public Bibliotekarz() {};
	
	/*POWRÓT DO GŁÓWNEGO MENU*/
	public void escape(Object object, Biblioteka biblioteka) {
	if (Objects.isNull(object)) {
		menu(biblioteka);
	}}
	
	/*POBIERZ STRING*/
	public String pobierzTekst(String string, Biblioteka biblioteka) {
		String tekst, escape;
		BufferedReader im = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				System.out.println("\nPodaj " + string + ".");
				tekst = im.readLine();
				return tekst;
			}
			catch (IOException e) {
				do {
					try{
						System.out.println("\nPodano niepoprawne dane. Spróbuj jeszcze raz lub naciśnij Q, aby wrócić do głównego menu.");
						escape = im.readLine();
						break;
					}
					catch (IOException f) {
						System.out.println("\nPodano niepoprawne dane. Spróbuj jeszcze raz");
					}
				}
				while (true);
			}
		} while (!escape.equals("Q"));
		return null;
	}
	
	/*POBIERZ NUMER*/
	public Long pobierzLong(String string, Biblioteka biblioteka) {
		String escape;
		Long numer;
		BufferedReader im = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				System.out.println("\nPodaj " + string + ".");
				numer = Long.parseLong(im.readLine());
				return numer;
			}
			catch (IOException | NumberFormatException e) {
				do {
					try{
						System.out.println("\nPodano niepoprawne dane. Spróbuj jeszcze raz lub naciśnij Q, aby wrócić do głównego menu.");
						escape = im.readLine();
						break;
					}
					catch (IOException | NumberFormatException f) {
						System.out.println("\nPodano niepoprawne dane. Spróbuj jeszcze raz");
					}
				}
				while (true);
			}
		} while (!escape.equals("Q"));
		return null;
	}
	
	/*LISTA CZYTELNIKÓW*/
	public void wypiszCzytelnikow(Biblioteka biblioteka) {
		int num = 1;
		for (Czytelnik c : biblioteka.czytelnicy) {
			System.out.println(num +"." + c + "\n");
			num++;
		}
	}
	
	/*LISTA KSIĄŻEK*/
	public void wypiszKsiazki(Biblioteka biblioteka) {
		int num = 1;
		for (Ksiazka c : biblioteka.ksiazki) {
			System.out.println(num +"." + c + "\n");
			num++;
		}
	}
	
	/*LISTA WYPOŻYCZEŃ*/
	public void wypiszWypozyczenia(Biblioteka biblioteka) {
		int num = 1;
		for (Wypozyczenie c : biblioteka.wypozyczenia) {
			System.out.println(num +"." + c + "\n");
			num++;
		}
	}
	
	/*LISTA WYPOŻYCZEŃ CZYTELNIKA*/
	public void listaKsiazekCzytelnika(Biblioteka biblioteka) {
		Czytelnik czytelnik = findReaderByName(biblioteka);
		escape(czytelnik, biblioteka);
		System.out.println("Czytelnik numer " + czytelnik.numer_karty + " ma wypożyczone poniższe książki:");
		for (Wypozyczenie w: biblioteka.wypozyczenia) {
			if (w.czytelnik == czytelnik) {
				System.out.println(w.ksiazka);
			}
		}
	}
	
	/*DOSTĘPNOŚĆ*/
	public void dostepnosc(Biblioteka biblioteka) {
		Ksiazka ksiazka = findBookByName(biblioteka);
		for (Ksiazka k : biblioteka.ksiazki) {
			if (k == ksiazka) {
				System.out.println("Dostępne jest " + (k.liczba_egzemplarzy - k.liczba_wypozyczonych_egzemplarzy) + " egzemplarzy.\n");
			}
		}}
	
	/*DODAWANIE CZYTELNIKA*/
	public void dodajCzytelnik(Biblioteka biblioteka) {
		String imie, nazwisko;
		long numer_karty;
		numer_karty = pobierzLong("numer karty czytelnika", biblioteka);
		escape(numer_karty, biblioteka);
		for (Czytelnik i : biblioteka.czytelnicy) {
				if (i.numer_karty == numer_karty) {
					System.out.println("Czytelnik z taką kartą już istnieje. Spróbuj jeszcze raz.");
					dodajCzytelnik(biblioteka);
				}
		}
		imie = pobierzTekst("imię czytelnika", biblioteka);
		escape(imie, biblioteka);
		nazwisko = pobierzTekst("nazwisko czytelnika", biblioteka);
		escape(nazwisko, biblioteka);
		Czytelnik nowy = new Czytelnik(imie, nazwisko, numer_karty, biblioteka);
		System.out.println("Czytelnik "+nowy.imie+" "+nowy.nazwisko + " został dodany.\n");
	}
	
	/*USUWANIE CZYTELNIKA*/
	public void usunCzytelnik(Biblioteka biblioteka) {
		Czytelnik czytelnik = findReaderByCard(biblioteka);
		if (czytelnik == null) {
			System.out.println("Nie ma takiego czytelnika. Spróbuj jeszcze raz.");
			usunCzytelnik(biblioteka);
		}
		else {
			biblioteka.czytelnicy.remove(czytelnik);
			System.out.println("Czytelnik " + czytelnik.imie + " " + czytelnik.nazwisko + " został usunięty.\n");
		}
	}

	/*DODAWANIE KSIĄŻKI*/
	public void dodajKsiazka(Biblioteka biblioteka) {
		Ksiazka exists = null;
		String isbn = pobierzTekst("isbn", biblioteka);
		String autor, tytul;
		for (Ksiazka i : biblioteka.ksiazki) {
			if (i.isbn.equals(isbn)) {
				System.out.println("Podana książka już istnieje. Możesz dodać nowe egzemplarze.");
				exists = i;}
		}
		int liczba_egzemplarzy = pobierzLong("liczba egzemplarzy", biblioteka).intValue();
		if (Objects.isNull(exists)) {
			autor = pobierzTekst("autora", biblioteka);
			tytul = pobierzTekst("tytuł", biblioteka);
			Ksiazka nowa = new Ksiazka(autor, tytul, isbn, liczba_egzemplarzy, 0, biblioteka);
			System.out.println("Książka "+nowa.tytul+" została dodana.\n");
		}
		else {
			exists.setLiczba_egzemplarzy(exists.liczba_egzemplarzy+liczba_egzemplarzy);
		}
	}
	
	/*USUWANIE KSIAZKI*/
	public void usunKsiazka(Biblioteka biblioteka) {
		Ksiazka ksiazka = findBookByName(biblioteka);
		int usun_egz = pobierzLong("liczb� egzemplarzy do usunięcia", biblioteka).intValue();
		ksiazka.setLiczba_egzemplarzy(ksiazka.getLiczba_egzemplarzy()-usun_egz);
		if (ksiazka.liczba_egzemplarzy <= 0) {
				System.out.println("Liczba egzemplarzy tej ksiazki "+ksiazka.tytul+" wynosi " + ksiazka.liczba_egzemplarzy +"\n Wci�nij R aby ca�kowicie j� usun�� z rejestru.");
				String operation = pobierzTekst("komend�", biblioteka);
				if (operation.equalsIgnoreCase("R")) {
					biblioteka.ksiazki.remove(ksiazka);
					System.out.println("Ksi��ka "+ksiazka.tytul+" zosta�a ca�kowicie usuni�ta z biblioteki\n");
				}
		}
		System.out.println("Usuni�to ksi��k� "+ksiazka.tytul+" w liczbie egzemplarzy "+usun_egz);
	}
	
	/*DODAJ WYPO�YCZENIE*/
	public void dodajWypozyczenie(Biblioteka biblioteka) {
		Ksiazka ksiazka = findBookByName(biblioteka);
		Czytelnik czytelnik = findReaderByName(biblioteka);
		Wypozyczenie nowe = new Wypozyczenie(ksiazka, czytelnik, biblioteka);
		biblioteka.wypozyczenia.add(nowe);
		ksiazka.setLiczba_wypozyczonych_egzemplarzy(ksiazka.getLiczba_wypozyczonych_egzemplarzy()+1);
		System.out.println("Dodano do katalogu wypo�yczenie ksi��ki " +ksiazka+" przez czytelnika "+czytelnik+".\n");
	}
	
	/*USUWANIE WYPO�YCZENIA*/
	public void usunWypozyczenie(Biblioteka biblioteka) {
		Ksiazka ksiazka = findBookByName(biblioteka);
		Czytelnik czytelnik = findReaderByName(biblioteka);
		for (Wypozyczenie i : biblioteka.wypozyczenia) {
				if (i.ksiazka == ksiazka && i.czytelnik == czytelnik) {
					biblioteka.wypozyczenia.remove(i);
				}
		}
	}

	/*ZNAJD� KSI��K� PO AUTORZE*/
	public Ksiazka findBookByName(Biblioteka biblioteka) {
		String name;
		int result = 0;
		ArrayList<Ksiazka> wyniki = new ArrayList<Ksiazka>();
		name = pobierzTekst("autora lub tytu�", biblioteka);
		escape(name, biblioteka);
		for (Ksiazka i : biblioteka.ksiazki) {
			if (i.autor.contains(name) ||  i.tytul.contains(name)) {
				wyniki.add(i);
				result++;
			}
		}
		if (result == 0) {
			System.out.println("Brak wynik�w. Wci�nij B, aby spr�bowa� jeszcze raz po nazwie, I aby spr�bowa� po ISBN lub Q aby wyj�� do menu g��wnego.");
			String operation = pobierzTekst("komend�", biblioteka);
			escape(operation, biblioteka);
			switch (operation) {
				case ("B") :
					return findBookByName(biblioteka);
				case ("I") :
					return findBookByISBN(biblioteka);
				case ("Q") :
					return null;
				default :
					System.out.println("Podano nieznan� komend�. Spr�buj jeszcze raz.");
					return findBookByName(biblioteka);
			}
		}
		else if (result == 1) {
			return wyniki.get(0);
		}
		else {
			System.out.println("Znaleziono " +result+ " wynik�w.");
			for (Ksiazka i : wyniki) {
				System.out.println(i);
			}
			return findBookByISBN(biblioteka);
		}
	}
	
	/*ZNAJD� KSI��K� PO ISBN*/
	public Ksiazka findBookByISBN(Biblioteka biblioteka) {
		String isbn = pobierzTekst("numer ISBN", biblioteka);
		escape(isbn, biblioteka);
		Ksiazka result = null;
		int results = 0;
		for (Ksiazka i : biblioteka.ksiazki) {
			if (i.isbn.equals(isbn)) {
				results++;
				result = i;
			}
		}
		if (results == 0) {
			System.out.println("Podano niestniej�cy isbn. Spr�buj jeszcze raz");
			return findBookByISBN(biblioteka);
		}
		else {
			return result;
		}
	}
	
	/*ZNAJD� CZYTELNIKA PO KARCIE*/
	public Czytelnik findReaderByCard(Biblioteka biblioteka) {
		Long numer_karty;
		int result = 0;
		numer_karty = pobierzLong("numer karty czytelnika", biblioteka);
		escape(numer_karty, biblioteka);
				for (Czytelnik i : biblioteka.czytelnicy) {
					if (i.numer_karty == numer_karty) {
						result++;
						return i;
					}
			}
		
		if (result == 0) {
			System.out.println("Podano niestniejący numer karty. Spróbuj jeszcze raz.");
			return findReaderByCard(biblioteka);
		}
		else {
			return null;
		}
	}
	/*ZNAJDZ CZYTELNIKA PO NAZWISKU*/
	public Czytelnik findReaderByName(Biblioteka biblioteka) {
		int result = 0;
		ArrayList<Czytelnik> wyniki = new ArrayList<Czytelnik>();
		String name = pobierzTekst("nazwisko", biblioteka);
		escape(name, biblioteka);
		for (Czytelnik i : biblioteka.czytelnicy) {
			if (i.nazwisko.contains(name)) {
				wyniki.add(i);
				result++;
			}
		}
		if (result == 0) {
			System.out.println("Brak wyników. Wciśnij N, aby spróbować po karcie czytelnika lub dowolny inny znaku, aby spróbować jeszcze raz po nazwisku.");
			String operation = pobierzTekst("komendę", biblioteka);
			escape(operation, biblioteka);
			switch (operation) {
				case ("N") :
					return findReaderByCard(biblioteka);
				default :
					return findReaderByName(biblioteka);
			}
		}
		else if (result == 1) {
			return wyniki.get(0);
		}
		else {
			System.out.println("Znaleziono " +result+ " wyników.");
			for (Czytelnik i : wyniki) {
				System.out.println(i);
			}
			return findReaderByCard(biblioteka);
		}
	}
	
	
	public void menu(Biblioteka biblioteka) {
		String operation;
		do {
			System.out.println("Witaj w aplikacji bibliotecznej.\n\n"
								+ "Wybierz operację lub naciśnij Q aby zakończyć.\n"
								+ "A Szukaj czytelnika po nazwisku\n"
								+ "B Szukaj czytelnika po numerze karty \n"
								+ "C Dodaj czytelnika\n"
								+ "D Usuń czytelnika\n"
								+ "E Lista czytelników\n"
								+ "F Znajdz książkę po tytule lub autorze\n"
								+ "G Znajdz książkę po numerze ISBN\n"
								+ "H Dodaj książkę\n"
								+ "I Usuń książkę\n"
								+ "J Lista książek\n"
								+ "K Dodaj wypozyczenie\n"
								+ "L Usuń wypożyczenie\n"
								+ "M Lista wypożyczeń\n"
								+ "N Lista książek czytelnika\n"
								+ "O Sprawdz dostępność książki");
			
			operation = pobierzTekst("komendę", biblioteka);
			
			if (operation.equals("Q")) {
				break;
			}
			else if (operation.equalsIgnoreCase("A")) {
				System.out.println(findReaderByName(biblioteka)+"\n");
			}
			else if (operation.equalsIgnoreCase("B")) {
				System.out.println(findReaderByCard(biblioteka)+"\n");
			}
			else if (operation.equalsIgnoreCase("C")) {
				dodajCzytelnik(biblioteka);
			}
			else if (operation.equalsIgnoreCase("D")) {
				usunCzytelnik(biblioteka);
			}
			else if (operation.equalsIgnoreCase("E")) {
				wypiszCzytelnikow(biblioteka);
			}
			else if (operation.equalsIgnoreCase("F")) {
				System.out.println(findBookByName(biblioteka)+"\n");
			}
			else if (operation.equalsIgnoreCase("G")) {
				System.out.println(findBookByISBN(biblioteka)+"\n");
			}
			else if (operation.equalsIgnoreCase("H")) {
				dodajKsiazka(biblioteka);
			}
			else if (operation.equalsIgnoreCase("I")) {
				usunKsiazka(biblioteka);
			}
			else if (operation.equalsIgnoreCase("J")) {
				wypiszKsiazki(biblioteka);
			}
			else if (operation.equalsIgnoreCase("K")) {
				dodajWypozyczenie(biblioteka);
			}
			else if (operation.equalsIgnoreCase("L")) {
				usunWypozyczenie(biblioteka);
			}
			else if (operation.equalsIgnoreCase("M")) {
				wypiszWypozyczenia(biblioteka);
			}
			else if (operation.equalsIgnoreCase("N")) {
				listaKsiazekCzytelnika(biblioteka);
			}
			else if (operation.equalsIgnoreCase("O")) {
				dostepnosc(biblioteka);
			}
			else {
				continue;
			}
		}
		while(true);
		}
	}
	
