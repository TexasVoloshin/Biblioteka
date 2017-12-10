
public class Ksiazka {
	String autor, tytul, isbn;
	int liczba_egzemplarzy, liczba_wypozyczonych_egzemplarzy;
	public Ksiazka(String autor, String tytul, String isbn, int liczba_egzemplarzy,
			int liczba_wypozyczonych_egzemplarzy, Biblioteka biblio) {
		super();
		this.autor = autor;
		this.tytul = tytul;
		this.isbn = isbn;
		this.liczba_egzemplarzy = liczba_egzemplarzy;
		this.liczba_wypozyczonych_egzemplarzy = liczba_wypozyczonych_egzemplarzy;
		biblio.ksiazki.add(this);
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public String getTytul() {
		return tytul;
	}
	public void setTytul(String tytul) {
		this.tytul = tytul;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public int getLiczba_egzemplarzy() {
		return liczba_egzemplarzy;
	}
	public void setLiczba_egzemplarzy(int liczba_egzemplarzy) {
		this.liczba_egzemplarzy = liczba_egzemplarzy;
	}
	public int getLiczba_wypozyczonych_egzemplarzy() {
		return liczba_wypozyczonych_egzemplarzy;
	}
	public void setLiczba_wypozyczonych_egzemplarzy(int liczba_wypozyczonych_egzemplarzy) {
		this.liczba_wypozyczonych_egzemplarzy = liczba_wypozyczonych_egzemplarzy;
	}
	
	public String toString() {
		return "Ksiazka [Autor="+autor+", Tytul="+tytul+", ISBN="+isbn+", Egzemplarze="+liczba_egzemplarzy+", Egzemplarze Wypozyczone="+liczba_wypozyczonych_egzemplarzy+"]";
	}
	
}


