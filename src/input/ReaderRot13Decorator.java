package input;

import java.util.ArrayList;

public class ReaderRot13Decorator extends ReaderDecorator{

	public ReaderRot13Decorator(DocumentReader docreader) {
		super(docreader);
	}
	public ArrayList<String> read(){
		ArrayList<String> lista = new ArrayList<String>();
		lista = docreader.read();
		lista = decoderB(lista);
		return lista;
	}
	private ArrayList<String> decoderB(ArrayList<String> array){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<array.size();i++) {
			String word = rot13decoder(array.get(i));
			list.add(word);
		}
		return list;
	}
	private String rot13decoder(String str) {
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
