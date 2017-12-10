
public class Czytelnik {
	String imie, nazwisko;
	long numer_karty;
	
	public Czytelnik(String imie, String nazwisko, long numer_karty, Biblioteka biblio) {
		super();
		this.imie = imie;
		this.nazwisko = nazwisko;
		this.numer_karty = numer_karty;
		biblio.czytelnicy.add(this);
	}

	public String getImie() {
		return imie;
	}

	public void setImie(String imie) {
		this.imie = imie;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public long getNumer_karty() {
		return numer_karty;
	}

	public void setNumer_karty(long numer_karty) {
		this.numer_karty = numer_karty;
	}
	
	public String toString() {
		return "Czytelnik [Imie="+imie+", Nazwisko="+nazwisko+", Numer Karty="+numer_karty+"]";
	}
	
}
