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

import commands.OpenDocument;
import commands.ReplayManager;
import model.Document;
import model.FakeTTSFacade;

class AudioCustomizationsTest {

	@Test
	void test() throws InterruptedException {
		String path="";
		float volumef=0.8f;
		float pitchf=50.9f;
		float ratef=5.8f;
		try(XWPFDocument docx= new XWPFDocument()){
			XWPFParagraph par = docx.createParagraph();
			XWPFRun run = par.createRun();
			String wordscombined="test test test test";
			String wordscombined2="even line";
			for(int i=0;i<5;i++) {
				if(i==1||i==3) {
					run.setText(wordscombined2,i);
					run.addBreak();
				}
				else {
					run.setText(wordscombined,i);
					run.addBreak();
				}
			}
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
		System.out.print(path);
		OpenDocument opendoc = new OpenDocument(document,rm);
		opendoc.actionPerformed(null);
		TimeUnit.SECONDS.sleep(1);
		ActionListener volumesetter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				document.setVolume(volumef);
				rm.addtolist(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				document.setVolume(volumef);
			}
		});
			}
		};
		ActionListener pitchsetter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				document.setPitch(pitchf);
				rm.addtolist(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				document.setPitch(pitchf);
			}
		});
			}
		};
		ActionListener ratesetter = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				document.setRate(ratef);
				rm.addtolist(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				document.setRate(ratef);
			}
		});
			}
		};
		volumesetter.actionPerformed(null);
		pitchsetter.actionPerformed(null);
		ratesetter.actionPerformed(null);
		float volumee = faketts.getVolume();
		float pitchh = faketts.getPitch();
		float ratee = faketts.getRate();
		assertEquals(volumee,0.8f);
		assertEquals(pitchh,50.9f);
		assertEquals(ratee,5.8f);
	}
}

