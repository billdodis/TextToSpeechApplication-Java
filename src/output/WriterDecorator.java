package output;
import java.util.ArrayList;

public class WriterDecorator implements DocumentWriter{
	protected DocumentWriter docwriter;
	public WriterDecorator(DocumentWriter doc) {
		this.docwriter = doc;
	}
	public void write(ArrayList<String> arrayz){
		docwriter.write(arrayz);
	}
}
