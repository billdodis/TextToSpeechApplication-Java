package output;

import java.util.ArrayList;

public class WriterAtbashDecorator extends WriterDecorator{

	public WriterAtbashDecorator(DocumentWriter docwriter) {
		super(docwriter);
	}
	public void write(ArrayList<String> arrayz){
		ArrayList<String> lista = new ArrayList<String>();
		lista = encoderA(arrayz);
		docwriter.write(lista);
	}
	private ArrayList<String> encoderA(ArrayList<String> array){
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<array.size();i++) {
			String word = atbashencoder(array.get(i));
			list.add(word);
		}
		return list;
	}
	private String atbashencoder(String str) {
		String x="";
		String alphabet="abcdefghijklmnopqrstuvwxyz";
		String reversedalphabet="zyxwvutsrqponmlkjihgfedcba";
		String capsalphabet="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String reversedcapsalphabet="ZYXWVUTSRQPONMLKJIHGFEDCBA";
		for(int y=0;y<str.length();y++) {
			char c = str.charAt(y);
			String kappa=getencodedchar(c,alphabet,reversedalphabet);
			String cappa=getencodedchar(c,capsalphabet,reversedcapsalphabet);
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
	private String getencodedchar(char c,String alphabet , String reversedalphabet) {
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
