package menjacnica;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;

import menjacnica.sistemskeoperacije.SODodajValutu;
import menjacnica.sistemskeoperacije.SOIzvrsiTransakciju;
import menjacnica.sistemskeoperacije.SOObrisiValutu;
import menjacnica.sistemskeoperacije.SOSacuvajUFajl;
import menjacnica.sistemskeoperacije.SOUcitajIzFajla;
import menjacnica.sistemskeoperacije.SOVratiKursnuListu;

public class Menjacnica implements MenjacnicaInterface{
	
	private LinkedList<Valuta> kursnaLista = new LinkedList<Valuta>();

	@Override
	public void dodajValutu(Valuta valuta) {
		SODodajValutu.izvrsi(kursnaLista, valuta);		
	}

	@Override
	public void obrisiValutu(Valuta valuta) {
		SOObrisiValutu.izvrsi(kursnaLista, valuta);
	}

	@Override
	public double izvrsiTransakciju(Valuta valuta, boolean prodaja, double iznos) {
		return SOIzvrsiTransakciju.izvrsi(valuta, prodaja, iznos);
	}

	@Override
	public LinkedList<Valuta> vratiKursnuListu() {
		return SOVratiKursnuListu.izvrsi(kursnaLista);
	}

	@Override
	public void ucitajIzFajla(String putanja) {
		SOUcitajIzFajla.izvrsi(kursnaLista, putanja);
	}

	@Override
	public void sacuvajUFajl(String putanja) {
		SOSacuvajUFajl.izvrsi(kursnaLista, putanja);
	}

	
}
