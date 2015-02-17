package bluehberger_zainziger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
/**
 * Startet den Controller und nimmt Argumente auf die dem Control mitgeliefert werden. 
 * Argumentenliste
 * -h server
 * -u benutzer
 * -p passwort 
 * -d datenbank
 */
import java.util.ArrayList;

/**
 * Start/Main Klasse des Rückwärtssalto Beispiels.
 * Stellt Verbindung zur Datenbank her und erstellt ein RM.
 * @author lukaszainzinger
 * @version 2015-01-11
 */
public class Start {
	public static void main (String[] args){
		   if(args.length==0){ 

			}
			String server = "localhost", benutzer="root", passwort="Abc1234", datenbank = "agentenspiel"; //Hardcoded muss noch geändert werden
			for(int i = 0; i < args.length; i++){
				switch(args[i]){
				case("-h"):
					server = args[i+1];
				i++;
				break;
				case("-u"):
					benutzer = args[i+1];
				i++;
				break;
				case("-p"):
					passwort = args[i+1];
				i++;
				break;
				case("-d"):
					datenbank = args[i+1];
				i++;
				break;

				}
			}
			JDBCController control = new JDBCController(server,benutzer,passwort,datenbank); //Erstellen des JDBC Controllers für die Verbindung
			ArrayList<Table> tables= control.getData(); // Daten Abfragen
			String rm = control.getRM(tables); //RM erstellen
			try {
				control.write(rm);//Schreibt/Erstellt das RM als Textfile
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(rm); //Ausgabe des RMs in der Konsole
			Zeichnen z = new Zeichnen(); 
			File table = z.makeDotFile(tables); //Erstellen .dot Files zum Erstellen des Diagramms 
			z.zeichnen(table); //Diagramm Zeichnen
			System.out.println("Programm Ende.");
			System.out.println("Bei einem permission-Fehler führen Sie bitte den Befehl \n /usr/local/bin/neato -Tpng tables.dot -o EER.png \n im Verzichnis des erstellten .dot Files aus!");
			
	   }
	}

