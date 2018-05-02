package menjacnica.sistemskeoperacije;

import java.util.LinkedList;

import menjacnica.Valuta;

public class SODodajValutu {
	public static void izvrsi(LinkedList<Valuta> kursnaLista,Valuta valuta) {
		if (valuta==null)
			throw new RuntimeException("Valuta ne sme biti null");
		
		if (kursnaLista.contains(valuta))
			throw new RuntimeException("Valuta je vec uneta u kursnu listu");
		
		kursnaLista.add(valuta);		
	}
	
}
