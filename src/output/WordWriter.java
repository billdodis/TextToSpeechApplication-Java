package output;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class WordWriter implements DocumentWriter{
	private String path;
	public WordWriter(String filepath,ArrayList<String> array) {
		this.path = filepath;
	}
	public void write(ArrayList<String> arrayz) {
		try(XWPFDocument docx = new XWPFDocument()){
			XWPFParagraph par = docx.createParagraph();
			XWPFRun run = par.createRun();
			String wordscombined="";
			for(int i=0;i<arrayz.size();i++) {
				wordscombined+=arrayz.get(i);
				if(i!=arrayz.size()-1) {
					wordscombined+="\n";
				}
			}
			String[] words = wordscombined.split("\n");
			for(int i=0;i<words.length;i++) {
				run.setText(words[i],i);
				run.addBreak();
			}
			try (FileOutputStream out = new FileOutputStream(this.path)) {
				docx.write(out);
				out.close();
				docx.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

