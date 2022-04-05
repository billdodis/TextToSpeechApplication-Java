package commands;
//import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.Document;

public class CommandsFactory {
		private Document document;
		private ReplayManager replayManagerr;
		//private ActionEvent ae;
		public CommandsFactory(Document documentt, ReplayManager rm) {
			this.document = documentt;
			this.replayManagerr = rm;
		}
		public ActionListener createCommand(String string){
			if(string=="save") {
				SaveDocument savedoc = new SaveDocument(this.document,this.replayManagerr);
				return savedoc;
			}
			else if(string=="open") {
				OpenDocument opendoc = new OpenDocument(this.document,this.replayManagerr);
				return opendoc;
			}
			else if(string=="edit") {
				EditDocument editdoc = new EditDocument(this.document,this.replayManagerr);
				return editdoc;
			}
			else{
				DocumentToSpeech doc2speech = new DocumentToSpeech(this.document,this.replayManagerr);
				return doc2speech;
			}
		}
}
