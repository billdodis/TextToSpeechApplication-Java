package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.swing.JTextArea;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;

import commands.EditDocument;
import commands.OpenDocument;
import commands.ReplayManager;
import model.Document;

class EditDocumentTest {

	@Test
	void test() throws InterruptedException {
		String path="";
		try(XWPFDocument docx= new XWPFDocument()){
			XWPFParagraph par = docx.createParagraph();
			XWPFRun run = par.createRun();
			String wordscombined="test test test test";
			run.setText(wordscombined);
			File yourFile = new File("test1.txt");
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
		OpenDocument opendoc = new OpenDocument(document,rm);
		opendoc.actionPerformed(null);
		TimeUnit.SECONDS.sleep(1);
		EditDocument editdoc = new EditDocument(document,rm);
		editdoc.actionPerformed(null);
		//WAIT 10 SECONDS SO WE HAVE TIME TO EDIT THE DOCUMENT.
		TimeUnit.SECONDS.sleep(10);
		assertEquals("test edited",document.getSpeech());
	}
}
