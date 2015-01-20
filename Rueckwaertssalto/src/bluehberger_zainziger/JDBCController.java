package bluehberger_zainziger;

import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
/**
 * Diese Klasse ist zum Verbinden auf eine Datenbank mittels JDBC und erstellen eines RMs
 * 
 * @author lukaszainzinger
 * @author gbluehberger
 * @version 2015-01-14
 */


public class JDBCController {
	private String name;
	private String server;
	private String benutzer;
	private String passwort;
	private String datenbank;
	
	/**
	 * Konstruktor von Controller
	 * @param server der Server der verwendet wird
	 * @param benutzer der benutzer mit den amgemeldet wird
	 * @param passwort das passwort des benutzer (leer wenn es keines gibt)
	 * @param datenbank die datenbank die verwendet wird
	 */
	public JDBCController(String server,String benutzer,String passwort,String datenbank){

		this.server = server;
		this.benutzer = benutzer;
		this.passwort = passwort;
		this.datenbank = datenbank;
	}	
	/**
	 * Diese Methode liest die PK,FK, Attribute und die Tabellennamen aus der Datenbank aus. 
	 * @return Liste der PK, FK und Attribute
	 */
	public ArrayList<Table> getData(){
		MysqlDataSource ds = new MysqlDataSource();
		ds.setServerName(server);
		ds.setUser(benutzer);
		ds.setPassword(passwort);
		ds.setDatabaseName(datenbank);
		ArrayList<Table> tables = new ArrayList<Table>(); 

		try {
			Connection con = ds.getConnection();
			// Abfrage vorbereiten und ausfuÌhren
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("show tables;");
	
			while (rs.next()) { // Cursor bewegen
				Table t = new Table();
				t.setName(rs.getString(1));// Name der Tabellen auslesen
				tables.add(t);// in ArrayList speichern
			}
			String befehl ="";
			for(int i = 0; i < tables.size(); i++){
				befehl="desc "+tables.get(i).getName()+";"; // Befehl aendern auf desc TABLENAME; für die Attribute
				rs = st.executeQuery(befehl);
				
				ArrayList<String> atri = new ArrayList<String>();
				ArrayList<String> primary = new ArrayList<String>();
				ArrayList<String> foreign = new ArrayList<String>();//Fremdschlüssel

				while(rs.next()){

					atri.add(rs.getString(1)); // Speichern der Atribute
					if(rs.getString(4).equals("PRI")){ // Ueberpruefen ob PK.
						primary.add(rs.getString(1));
						//rs = meta.getPrimaryKeys(null,null,tabname);
					}
					if(rs.getString(4).equals("MUL")){ // Ueberpruefen ob FK.
						foreign.add(rs.getString(1));
						//rs = meta.getExportedKeys(null, null, tabname);
					}
				}
				
				tables.get(i).setAttribute(atri); // Attribute hinzufüen in den Tabellen.
				tables.get(i).setPkey(primary);
				tables.get(i).setFkey(foreign);
			}
			rs.close(); st.close(); con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tables;
	}
	
	/**
	 * Macht ein Relationenmodel
	 * @param tables die Tabellen die ausgegeben werden sollen
	 * @return sinnvolle Ausgabe der Attribute geordnet nach Tabellen
	 */
	public String getRM(ArrayList<Table> tables){
		String txt = "";
		for(int i=0;i<tables.size();i++) {
			txt+=tables.get(i).getName()+"("; 
			for(int j=0;j<tables.get(i).getAttribute().size();j++){
				if(j==tables.get(i).getAttribute().size()-1){
					txt+=""+tables.get(i).getAttribute().get(j);
					
				}else{
					txt+=""+tables.get(i).getAttribute().get(j)+", ";
				}
			}
			txt+=")\n";
		}
		return txt;
	}
}
