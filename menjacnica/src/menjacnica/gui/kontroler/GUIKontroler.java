package menjacnica.gui.kontroler;

import java.awt.EventQueue;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import menjacnica.Menjacnica;
import menjacnica.Valuta;
import menjacnica.gui.DodajKursGUI;
import menjacnica.gui.IzvrsiZamenuGUI;
import menjacnica.gui.MenjacnicaGUI;
import menjacnica.gui.ObrisiKursGUI;
import menjacnica.gui.models.MenjacnicaTableModel;

public class GUIKontroler {
		public static Menjacnica sistem=new Menjacnica();
		public static MenjacnicaGUI glavniProzor;

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						GUIKontroler.glavniProzor=new MenjacnicaGUI();
						GUIKontroler.glavniProzor.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		public static void sacuvajUFajl() {
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();

					sistem.sacuvajUFajl(file.getAbsolutePath());
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
		}

		public static void ucitajIzFajla() {
			try {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					sistem.ucitajIzFajla(file.getAbsolutePath());
					prikaziSveValute();
				}	
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		public static void prikaziSveValute() {
			MenjacnicaTableModel model = (MenjacnicaTableModel)(glavniProzor.table.getModel());
			model.staviSveValuteUModel(sistem.vratiKursnuListu());

		}
		
		public static void prikaziDodajKursGUI() {
			DodajKursGUI prozor = new DodajKursGUI();
			prozor.setLocationRelativeTo(glavniProzor);
			prozor.setVisible(true);
		}

		public static void prikaziObrisiKursGUI() {
			
			if (glavniProzor.table.getSelectedRow() != -1) {
				MenjacnicaTableModel model = (MenjacnicaTableModel)(glavniProzor.table.getModel());
				ObrisiKursGUI prozor = new ObrisiKursGUI(model.vratiValutu(glavniProzor
						.table.getSelectedRow()));
				prozor.setLocationRelativeTo(glavniProzor);
				prozor.setVisible(true);
			}
		}
		
		public static void prikaziIzvrsiZamenuGUI() {
			if (glavniProzor.table.getSelectedRow() != -1) {
				MenjacnicaTableModel model = (MenjacnicaTableModel)(glavniProzor.table.getModel());
				IzvrsiZamenuGUI prozor = new IzvrsiZamenuGUI(model.vratiValutu(glavniProzor.table.getSelectedRow()));
				prozor.setLocationRelativeTo(glavniProzor);
				prozor.setVisible(true);
			}
		}

		
		public static void unesiKurs(String naziv,String skraceni,int sifra,double prodajni,double kupovni,double srednji) {
			try {
				Valuta valuta = new Valuta();

				// Punjenje podataka o valuti
				valuta.setNaziv(naziv);
				valuta.setSkraceniNaziv(skraceni);
				valuta.setSifra(sifra);
				valuta.setProdajni(prodajni);
				valuta.setKupovni(kupovni);
				valuta.setSrednji(srednji);
				
				// Dodavanje valute u kursnu listu
				sistem.dodajValutu(valuta);
				// Osvezavanje glavnog prozora
				prikaziSveValute();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
		}

		public static void obrisiValutu(Valuta valuta) {
			try{
				sistem.obrisiValutu(valuta);
				
				prikaziSveValute();
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
						"Greska", JOptionPane.ERROR_MESSAGE);
			}
		}
		public static double izvrsiZamenu(Valuta valuta,boolean cekirano,double iznos){
			try{
				double konacniIznos = 
						sistem.izvrsiTransakciju(valuta,
								cekirano, iznos)
								;
			return konacniIznos;
			} catch (Exception e1) {
			JOptionPane.showMessageDialog(glavniProzor, e1.getMessage(),
					"Greska", JOptionPane.ERROR_MESSAGE);
		}
			return 0;
		}
}
