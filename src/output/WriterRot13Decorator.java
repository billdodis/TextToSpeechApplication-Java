package output;

import java.util.ArrayList;

public class WriterRot13Decorator extends WriterDecorator{

	public WriterRot13Decorator(DocumentWriter docwriter) {
		super(docwriter);
	}
	public void write(ArrayList<String> arrayz){
		ArrayList<String> lista = new ArrayList<String>();
		lista = encoderB(arrayz);
		docwriter.write(lista);
	}
	private ArrayList<String> encoderB(ArrayList<String> array){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<array.size();i++) {
			String word = rot13encoder(array.get(i));
			list.add(word);
		}
		return list;
	}
	private String rot13encoder(String str) {
		String x="";
		 for (int i = 0; i < str.length(); i++) {
	            char c = str.charAt(i);
	            if(c >= 'a' && c <= 'm') {
	            	c += 13;
	            }
	            else if(c >= 'A' && c <= 'M') {
	            	c += 13;
	            }
	            else if(c >= 'n' && c <= 'z') {
	            	c -= 13;
	            }
	            else if(c >= 'N' && c <= 'Z') {
	            	c -= 13;
	            }
	            x+=c;
	        }
		return x;
	}
}
