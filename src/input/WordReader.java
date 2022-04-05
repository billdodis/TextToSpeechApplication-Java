package input;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;


public class WordReader implements DocumentReader {
		//private String stateA ="word"; 
	private String path;
	public WordReader(String Filename){
		this.path=Filename;
	}
	public ArrayList<String> read(){
		ArrayList<String> list = new ArrayList<String>();
		try {
			FileInputStream stream = new FileInputStream(path);
			try {
				XWPFDocument docx = new XWPFDocument(stream);
				try (XWPFWordExtractor extractor = new XWPFWordExtractor(docx)) {
					String text = extractor.getText();
					String[] lines = text.split("\\r\\n|\\n|\\r|\n|\r|\r\n");// maybe \\ anti gia \ sto split mesa
					for(int i=0;i<lines.length;i++) {
						list.add(lines[i]);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
}
