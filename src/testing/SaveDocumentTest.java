package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;

import commands.OpenDocument;
import commands.ReplayManager;
import commands.SaveDocument;
import model.Document;

class SaveDocumentTest {

	@Test
	void test() throws InterruptedException {
		String path="";
		String x="";
		try(XWPFDocument docx= new XWPFDocument()){
			XWPFParagraph par = docx.createParagraph();
			XWPFRun run = par.createRun();
			String wordscombined="test test test test";
			run.setText(wordscombined);
			File yourFile = new File("SaveDocTest.txt");
			yourFile.createNewFile();
			path=yourFile.getAbsolutePath();
			try (FileOutputStream out = new FileOutputStream(path)) {
				docx.write(out);
				out.close();
				docx.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ReplayManager rm = new ReplayManager();
		JTextArea textarea = new JTextArea();
		Document document = new Document(path,null,textarea);
		//OPENING WORD FILE
		OpenDocument opendoc = new OpenDocument(document,rm);
		opendoc.actionPerformed(null);
		TimeUnit.SECONDS.sleep(1);
		SaveDocument savedoc = new SaveDocument(document,rm);
		//SAVING WORD FILE
		savedoc.actionPerformed(null);
		try {
			FileInputStream stream = new FileInputStream(path);
			try {
				XWPFDocument docx = new XWPFDocument(stream);
				try (XWPFWordExtractor extractor = new XWPFWordExtractor(docx)) {
					String text = extractor.getText();
					String[] lines = text.split("\\r\\n|\\n|\\r|\n|\r|\r\n");// maybe \\ anti gia \ sto split mesa
					for(int i=0;i<lines.length;i++) {
						x+=lines[i];
						if(i!=lines.length-1) {
							x+=" ";
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		assertEquals("test test test test",x);
	}

}
