
public class Wypozyczenie {
	Ksiazka ksiazka;
	Czytelnik czytelnik;
	public Wypozyczenie(Ksiazka ksiazka, Czytelnik czytelnik, Biblioteka biblio) {
		super();
		this.ksiazka = ksiazka;
		this.czytelnik = czytelnik;
		biblio.wypozyczenia.add(this);
	}
	public Ksiazka getKsiazka() {
		return ksiazka;
	}
	public void setKsiazka(Ksiazka ksiazka) {
		this.ksiazka = ksiazka;
	}
	public Czytelnik getCzytelnik() {
		return czytelnik;
	}
	public void setCzytelnik(Czytelnik czytelnik) {
		this.czytelnik = czytelnik;
	}
	public String toString() {
		return "Wypozyczenie [Ksiazka="+ksiazka+",\nCzytelnik="+czytelnik+"]";
	}

}
