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
 * @version 2015-02-17
 */
public class Zeichnen {

	/**
	 * Methode zum Zeichnen des EER mit Aufruf von neato
	 * PKs werden Hellblau und FKs Grau gezeichnet. Die Key werden zusätzlich mit einem Pfeil angezeigt.
	 * Wichtig! Das Programm muss das Recht haben das Programm im Verzeichnis auszuführen!
	 * @param table table.dot mit allen Infos
	 * @param path der Ort andem sich neato befindet ("/usr/local/bin/neato -Tpng tables.dot -o EER.png")
	 */
	public void zeichnen(File table){
		Process proc;
		try {
			proc=Runtime.getRuntime().exec("/usr/local/bin/neato -Tpng tables.dot -o EER.png");//Ausführen auf Mac bei Linux ohne /local/ ansonst Pfad angeben
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Methode zum Erstellen des .dot Files, welches zum Zeichnen verwendet wird.
	 * PKs werden Hellblau und FKs Grau gezeichnet. Die Key werden zusätzlich mit einem Pfeil angezeigt.
	 * @param tables Alle Informationen des Diagramms in einer Liste
	 * @return table file
	 */
	public File makeDotFile(ArrayList<Table>tables){
		BufferedWriter w;
		File ftable = new File("tables.dot");

		try {
			w = new BufferedWriter(new FileWriter(ftable,false));

			w.write("digraph D {"+System.lineSeparator());
			w.flush();
			for(int i=0;i<tables.size();i++){
				w.write(tables.get(i).getName()+"[shape=box];");
				w.write("\n");
				
				for(int j=0;j<tables.get(i).getPkey().size();j++){
					w.write(tables.get(i).getName()+tables.get(i).getPkey().get(j)+"_PRI"+"[shape=ellipse,style=filled,color=lightblue,dir=none,label=\""+tables.get(i).getPkey().get(j)+"\"];\n");
					w.write(tables.get(i).getName()+"->"+tables.get(i).getName()+tables.get(i).getPkey().get(j)+"_PRI\n");
				}
				
				for(int j=0; j<tables.get(i).getFkey().size();j++){
					w.write(tables.get(i).getName()+tables.get(i).getFkey().get(j)+"_MUL"+"[shape=ellipse,style=filled,color=grey,label=\""+tables.get(i).getFkey().get(j)+"\"];\n");
					w.write(tables.get(i).getName()+"->"+tables.get(i).getName()+tables.get(i).getFkey().get(j)+"_MUL\n");
				}

				for(int j = 0; j < tables.get(i).getAttribute().size();j++){
					w.write(tables.get(i).getName()+"->"+tables.get(i).getName()+tables.get(i).getAttribute().get(j)+"[arrowhead=none]\n");
					w.write("\n");
				}
			} 
			
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
