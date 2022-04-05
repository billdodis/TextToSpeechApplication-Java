package commands;
import model.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DocumentToSpeech implements ActionListener,Prototype {
		private Document document;
		private ReplayManager replayManagerr;
		public DocumentToSpeech(Document documentt, ReplayManager rm) {
			this.document = documentt;
			this.replayManagerr = rm;
		}
		public void setDocument(Document doc){
			this.document = doc;
		}
		public void setReplayManager(ReplayManager replaymanager){
			this.replayManagerr = replaymanager;
		}
		public void actionPerformed(ActionEvent event){
			if(this.replayManagerr.isActiveRecording()) {
				clonee();
			}
			this.document.playContents();
		}
		public void clonee() {
			replayManagerr.addtolist(new DocumentToSpeech(this.document,this.replayManagerr));
		}
}
