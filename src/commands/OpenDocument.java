package commands;

import model.Document;
import view.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class OpenDocument implements ActionListener ,Prototype{
		private Document document;
		JFrame frame4;
		private ReplayManager replayManagerr;
		public String path;
		public String filetype;
		public String decoding;
		private int opencounter=0;
		public OpenDocument(Document documentt, ReplayManager rm) {
			this.document = documentt;
			this.replayManagerr = rm;
		}
		
		public void setDocument(Document doc){
			this.document = doc;
		}
		
		public void setReplayManager(ReplayManager replaymanager){
			this.replayManagerr = replaymanager;
		}
		
		public ReplayManager getReplayManager(){
			return this.replayManagerr;
		}
		
		public void actionPerformed(ActionEvent event){
			if(this.replayManagerr.isActiveRecording()) {
				clonee();
			}
			if(opencounter==0) {
				filetype = JOptionPane.showInputDialog("Insert the type of the file: 'excel' or 'word'");
				decoding = JOptionPane.showInputDialog("Insert the decoding of the file: 'Atbash','Rot13' or something else.");
				this.document.open(this.document.getPath(),filetype,decoding);
			}
			frame4 = new JFrame();
			frame4.setBounds(100, 100, 757, 487);
			frame4.setResizable(true);
			frame4.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame4.setTitle("Document");
			frame4.getContentPane().setLayout(null);
			JTextArea textareaa = new JTextArea(this.document.getSpeech());
			textareaa.setBounds(50, 50, 700, 300);
			textareaa.setBackground(new Color(200,200,200));
			textareaa.setBorder(BorderFactory.createBevelBorder(1));
			textareaa.setForeground(new Color(55,55,55));
			textareaa.setFont(new Font("Comic Sans",Font.ITALIC,15));
			textareaa.setLineWrap(true);
			textareaa.setEditable(false);
			JScrollPane scrollbar = new JScrollPane();
			scrollbar.setBounds(20,20,700,300);
			scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
			scrollbar.getViewport().add(textareaa);
			frame4.add(scrollbar);
			frame4.setVisible(true);
			opencounter++;
		}
		@Override
		public void clonee() {
			replayManagerr.addtolist(new OpenDocument(this.document,this.replayManagerr));
		}
}
