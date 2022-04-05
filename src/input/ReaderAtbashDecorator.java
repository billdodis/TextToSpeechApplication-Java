package input;

import java.util.ArrayList;

public class ReaderAtbashDecorator extends ReaderDecorator {

	public ReaderAtbashDecorator(DocumentReader docreader) {
		super(docreader);
	}
	public ArrayList<String> read(){
		ArrayList<String> lista = new ArrayList<String>();
		lista = docreader.read();
		lista = decoderA(lista);
		return lista;
	}
	private ArrayList<String> decoderA(ArrayList<String> array){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<array.size();i++) {
			String word = atbashdecoder(array.get(i));
			list.add(word);
		}
		return list;
	}
	private String atbashdecoder(String str) {
		String x="";
		String alphabet="abcdefghijklmnopqrstuvwxyz";
		String reversedalphabet="zyxwvutsrqponmlkjihgfedcba";
		String capsalphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String reversedcapsalphabet="ZYXWVUTSRQPONMLKJIHGFEDCBA";
		for(int y=0;y<str.length();y++) {
			char c = str.charAt(y);
			String kappa=getdecodedchar(c,alphabet,reversedalphabet);
			String cappa=getdecodedchar(c,capsalphabet,reversedcapsalphabet);
			if(kappa!="") {
				x+=kappa;
			}
			else if(cappa!="") {
				x+=cappa;
			}
			else {
				x+=c;
			}
		}
		return x;
	}
	private String getdecodedchar(char c,String alphabet , String reversedalphabet) {
		String z="";
		int count=-1;
		for(int i=0;i<alphabet.length();i++) {
			if(c==alphabet.charAt(i)) {
				count=i;
				char xi=reversedalphabet.charAt(count);
				z+=xi;
			}
		}
		return z;
	}
}
