package bluehberger_zainziger;
import java.util.ArrayList;

/**
 * Klasse zum Verwalten der Tabellennamen, Attribute und Schlüssel (PK sowie FK).
 * 
 * @author lzainzinger
 * @author gbluehberger
 * @version 2015-01-14
 */

public class Table {
	private String name;
	private ArrayList<String> attribute = new ArrayList<String>();
	private ArrayList<String> pkey = new ArrayList<String>(); // Speichert die Namen der Schluessel
	private ArrayList<String> fkey = new ArrayList<String>();
	
	/**
	 * Gibt alle Attribute zurück.
	 * @return the attribute
	 */
	public ArrayList<String> getAttribute() {
		return attribute;
	}
	/**
	 * Setzt die Attribute
	 * @param attribute the attribute to set
	 */
	public void setAttribute(ArrayList<String> attribute) {
		this.attribute = attribute;
	}
	/**
	 * Gibt die PK zurück
	 * @return the pkey
	 */
	public ArrayList<String> getPkey() {
		return pkey;
	}
	/**
	 * Setzt die PK
	 * @param pkey the pkey to set
	 */
	public void setPkey(ArrayList<String> pkey) {
		this.pkey = pkey;
	}
	/**
	 * Gibt die FK zurück
	 * @return the fkey
	 */
	public ArrayList<String> getFkey() {
		return fkey;
	}
	/**
	 * Setzt die FK
	 * @param fkey the fkey to set
	 */
	public void setFkey(ArrayList<String> fkey) {
		this.fkey = fkey;
	}
	
	
	public String getName() {
		return name;
	}
	
	
	public void setName(String name) {
		this.name = name;
	}


}

