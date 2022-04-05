package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.sun.speech.freetts.VoiceManager;

import commands.DocumentToSpeech;
import commands.OpenDocument;
import commands.ReplayManager;
import model.Document;
import model.FakeTTSFacade;

class ReplayManagerTest {

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
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		VoiceManager vm=VoiceManager.getInstance();
		FakeTTSFacade faketts = new FakeTTSFacade(vm,vm.getVoice("kevin16"));
		JTextArea textarea = new JTextArea();
		Document document = new Document(path,faketts,textarea);
		OpenDocument opendoc = new OpenDocument(document,rm);
		opendoc.actionPerformed(null);
		TimeUnit.SECONDS.sleep(1);
		ActionListener startrec = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rm.startRecording();
			}
		};
		ActionListener stoprec = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rm.endRecording();
			}
		};
		startrec.actionPerformed(null);
		DocumentToSpeech doc2speech = new DocumentToSpeech(document, rm);
		doc2speech.actionPerformed(null);
		String contents1=faketts.getPlayedContents();
		stoprec.actionPerformed(null);
		ActionListener replay = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rm.replay();
			}
		};
		replay.actionPerformed(null);
		String contents = faketts.getPlayedContents();
		assertEquals(true,contents.contains(contents1));
		assertEquals(true,contents.contains(contents1+contents1));
	}
}
