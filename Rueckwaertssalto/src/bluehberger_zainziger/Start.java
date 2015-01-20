package bluehberger_zainziger;

import java.io.File;
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
			String server = "localhost", benutzer="root", passwort="Abc1234", datenbank = "agentenspiel";
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
			JDBCController control = new JDBCController(server,benutzer,passwort,datenbank);
			ArrayList<Table> tables= control.getData();
			String rm = control.getRM(tables);
			System.out.println(rm);
			
	   }
	}

