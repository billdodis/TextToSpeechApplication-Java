package commands;
import model.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class SaveDocument implements ActionListener,Prototype {
		private Document document;
		private ReplayManager replayManagerr;
		public String path;
		public String filetype;
		public String decoding;
		public SaveDocument(Document documentt, ReplayManager rm) {
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
			path = JOptionPane.showInputDialog("Insert the path you want the file to be saved to, with the .xlsx or .docx at the end of the path.");
			filetype = JOptionPane.showInputDialog("Insert the saved type of the file: 'excel' or 'word'");
			decoding = JOptionPane.showInputDialog("Insert the saved decoding of the file: 'Atbash','Rot13' or something else.");
			this.document.save(path,filetype,decoding);
			JOptionPane.showMessageDialog(null, "Document saved succesfully!");
		}
		@Override
		public void clonee() {
			replayManagerr.addtolist(new SaveDocument(this.document,this.replayManagerr));	
		}

}
