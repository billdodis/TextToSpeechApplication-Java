package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JTextArea;

import model.Document;
import commands.OpenDocument;
import commands.ReplayManager;
import model.TTSFacade;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;
import com.sun.speech.freetts.VoiceManager;

class OpenDocumentTest {
	String pathaaaa="";
	OpenDocument opendoc;
	VoiceManager vm;
	Document document;
	ReplayManager rm;
	TTSFacade audiomanager;
	JTextArea textarea;
	//XWPFDocument docx;
	public OpenDocumentTest() {
		this.pathaaaa="";
		this.rm = new ReplayManager();
		//this.vm=VoiceManager.getInstance();
		//this.audiomanager = new TTSFacade(null,null);
		this.textarea = new JTextArea();
		this.document=new Document("",null,textarea);
		this.opendoc = new OpenDocument(document,rm);
	}
	@Test
	void test1() {
		//THIS IS A NORMAL TEST.
		try(XWPFDocument docx= new XWPFDocument()){
			XWPFParagraph par = docx.createParagraph();
			XWPFRun run = par.createRun();
			String wordscombined="test test test test";
			run.setText(wordscombined);
			File yourFile = new File("test1.txt");
			yourFile.createNewFile();
			pathaaaa= yourFile.getAbsolutePath();
			try (FileOutputStream out = new FileOutputStream(pathaaaa)) {
				docx.write(out);
				out.close();
				docx.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		Document document2 = new Document(pathaaaa,audiomanager,textarea);
		opendoc.setDocument(document2);
		opendoc.setReplayManager(rm);
		opendoc.actionPerformed(null);
		assertEquals("test test test test",document2.getSpeech());	
	}
	@Test
	void test2() {
		//THIS IS A ROT13 TEST.
		try(XWPFDocument docxx= new XWPFDocument()){
			XWPFParagraph par = docxx.createParagraph();
			XWPFRun run = par.createRun();
			String wordscombined="grfg grfg grfg grfg";
			run.setText(wordscombined);
			File yourFilee = new File("test2.txt");
			yourFilee.createNewFile();
			pathaaaa= yourFilee.getAbsolutePath();
			try (FileOutputStream out = new FileOutputStream(pathaaaa)) {
				docxx.write(out);
				out.close();
				docxx.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
		Document document2 = new Document(pathaaaa,audiomanager,textarea);
		opendoc.setDocument(document2);
		opendoc.setReplayManager(rm);
		opendoc.actionPerformed(null);
		assertEquals("test test test test",document2.getSpeech());	
	}
}
