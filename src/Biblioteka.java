import java.util.*;

public class Biblioteka {
	public ArrayList<Ksiazka> ksiazki = new ArrayList<Ksiazka>();
	public ArrayList<Czytelnik> czytelnicy = new ArrayList<Czytelnik>();
	public ArrayList<Wypozyczenie> wypozyczenia = new ArrayList<Wypozyczenie>();
	
	public static void boot (Biblioteka biblioteka) {
	Czytelnik aneta = new Czytelnik("Aneta", "Szurek", 123456, biblioteka);
	Czytelnik kasia = new Czytelnik("Kasia", "Siun", 4534545, biblioteka);
	Czytelnik tomek = new Czytelnik("Tomek", "Wojnar", 765765, biblioteka);
	Czytelnik basia = new Czytelnik("Barbara", "Niec", 5435435, biblioteka);
	Ksiazka jadro = new Ksiazka("Conrad Joseph", "Jadro Ciemnosci", "ADB2313123", 17, 2, biblioteka);
	Ksiazka tom = new Ksiazka("Twain Mark", "Tom Sawyer", "AFG62151", 12, 1, biblioteka);		
	Ksiazka lalka = new Ksiazka("Prus Boleslaw", "Lalka", "GHT32932", 10, 1, biblioteka);
	Ksiazka wieza = new Ksiazka("Kantoch Anna", "Wieza", "FHG3256564", 3, 2, biblioteka);
	new Wypozyczenie(jadro, aneta, biblioteka);
	new Wypozyczenie(wieza, aneta, biblioteka);
	new Wypozyczenie(jadro, kasia, biblioteka);
	new Wypozyczenie(wieza, kasia, biblioteka);
	new Wypozyczenie(tom, tomek, biblioteka);
	new Wypozyczenie(lalka, basia, biblioteka);
	}

	public static void main (String[] args) {
		Bibliotekarz bibliotekarz = new Bibliotekarz();
		Biblioteka biblioteka = new Biblioteka();
		boot(biblioteka);
		bibliotekarz.menu(biblioteka);}}