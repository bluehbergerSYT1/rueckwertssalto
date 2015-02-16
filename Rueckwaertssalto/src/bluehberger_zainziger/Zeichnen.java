package bluehberger_zainziger;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Klasse zum Zeichnen eines EER's.
 * Zur verwendung dieser Methoden muss Graphviz installiert sein.
 * neato -Tpng tables.dot -o EER.png
 * 
 * @author lzainzinger
 * @version 2015-02-16
 */
public class Zeichnen {

	/**
	 * Methode zum Zeichnen des EER mit Aufruf von neato
	 * @param table table.dot mit allen Infos
	 * @param path der Ort andem sich neato befindet ("/usr/local/bin/neato -Tpng tables.dot -o EER.png")
	 */
	public void zeichen(File table, String path){
		Process proc;
		try {
			proc=Runtime.getRuntime().exec(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode zum Erstellen des .dot Files, welches zum Zeichnen verwendet wird
	 * @param tables Alle Informationen des Diagramms in einer Liste
	 * @return table file
	 */
	public File makeDotFile(ArrayList<Table>tables){
		BufferedWriter w;
		File ftable = new File("tables.dot");

		try {
			w = new BufferedWriter(new FileWriter(ftable,false));

			
			w.write("}");
			w.flush();
			w.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ftable;		
	}
}
