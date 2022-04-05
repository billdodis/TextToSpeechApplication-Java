package input;
import java.util.ArrayList;

public class ReaderDecorator implements DocumentReader {
	protected DocumentReader docreader;
	public ReaderDecorator(DocumentReader docreadere){
		this.docreader=docreadere;
	}
	public ArrayList<String> read(){
		ArrayList<String> lista = new ArrayList<String>();
		lista = docreader.read();
		return lista;
	}
}
