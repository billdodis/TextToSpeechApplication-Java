package model;
import input.DocumentReaderFactory;
import input.DocumentReader;
import output.DocumentWriter;
import output.DocumentWriterFactory;
import java.util.ArrayList;

import javax.swing.JTextArea;

public class Document {
		private ArrayList<String> contents;
		//private ArrayList<String> contents2;
		private String path;
		private int opencounter=0;
		TTSFacade audioManager;
		JTextArea textarea;
		String speech=null;
		int settercounter=0;
		private DocumentReader documentReader;
		private DocumentReaderFactory docReaderFactory;
		private DocumentWriter documentWriter;
		private DocumentWriterFactory docWriterFactory;
		//private ArrayList<String> words;
		
		public Document(String patha,TTSFacade audiomanagerrr,JTextArea textareaa) {
			this.path = patha;
			this.audioManager = audiomanagerrr;
			this.textarea = textareaa;
			docReaderFactory = new DocumentReaderFactory();
			docWriterFactory = new DocumentWriterFactory();
			//speech = textarea.getText();
			contents = new ArrayList<String>();
			//contents2 = new ArrayList<String>();
		}

		public void setAudioManager(TTSFacade audiozManager){
			this.audioManager = audiozManager;
		}
		
		public TTSFacade getAudioManager(){
			return this.audioManager;
		}
		
		public void setDocReaderFactory(DocumentReaderFactory doczReaderFactory){
			this.docReaderFactory = doczReaderFactory;
		}

		public void setDocWriterFactory(DocumentWriterFactory doczWriterFactory){
			this.docWriterFactory = doczWriterFactory;
		}
		
		public String getPath() {
			return this.path;
		} 
		
		public void open(String path,String type,String decoding){
			//System.out.println(path);
			if(opencounter==0) {
				documentReader = docReaderFactory.createReader(this.path,type,decoding);
				contents = documentReader.read();
				speech = "";
				opencounter++;
				for(int i=0;i<contents.size();i++) {
					speech+=contents.get(i);
					if(i!=contents.size()-1) {
						speech+="\n";
					}
				}
			}
		}

		public void playContents(){
			audioManager.play(speech);
		}
		public void setVolume(float volume) {
			audioManager.setVolume(volume);
		}
		public void setPitch(float pitchnum) {
			audioManager.setPitch(pitchnum);
		}

		public void setRate(float ratenm) {
			audioManager.setRate(ratenm);
		}
		
		public ArrayList<String> getContents(){
			return contents;
		}
		
		public String getTextFromJText() {
			return this.textarea.getText();
		}
		
		public void setJTextArea(JTextArea newtextarea) {
			this.textarea = newtextarea;
			setSpeech();
			//textarea.setText(newtextarea.getText());
		}
		
		public JTextArea getJTextArea() {
			return this.textarea;
		}
		
		private void setSpeech() {
			contents.clear();
			speech="";
			speech=this.textarea.getText();
			String[] speechsplitted = speech.split("\\r\\n|\\r|\\n|\n|\r|\r\n");
			for(int i=0;i<speechsplitted.length;i++) {
				contents.add(speechsplitted[i]);
			}
			settercounter++;
		}
		
		public String getSpeech() {
			return speech;
		}
		
		public void playLine(int integer,int integer2){
			int line = integer-1;
			String finalspeech="";
			String[] linespeech = speech.split("\\r\\n|\\n|\\r|\n|\r|\r\n");  /////////prohgumenws : contents.get(i) to speechsplit.
			for(int i=line; i<integer2;i++) {
				finalspeech+=linespeech[i];
				finalspeech+="\n";
			}
			audioManager.play(finalspeech);
		}
		
		public String getLines(int integer, int integer2) {
			int line = integer-1;
			String lines="";
			String[] linespeech = speech.split("\\r\\n|\\n|\\r|\n|\r|\r\n");  /////////prohgumenws : contents.get(i) to speechsplit.
			for(int i=line; i<integer2;i++) {
				lines+=linespeech[i];
				lines+="\n";
			}
			return lines;
		}

		public void save(String path,String type,String decoding){
			documentWriter = docWriterFactory.createWriter(path, type, decoding, contents);
			documentWriter.write(contents);
		}

}
