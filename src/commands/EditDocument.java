package commands;
import model.Document;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class EditDocument implements ActionListener,Prototype {
		private Document document;
		private ReplayManager replayManagerr;
		public EditDocument(Document documentt, ReplayManager rm) {
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
			if(document.getSpeech()!=null) {
				JFrame frame3 = new JFrame();
				frame3.setBounds(100, 100, 783, 487);
				frame3.setResizable(true);
				frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame3.setTitle("Edit your Document");
				frame3.getContentPane().setLayout(null);
				JTextArea textarea = new JTextArea(this.document.getSpeech());
				textarea.setBounds(50, 50, 700, 300);
				textarea.setBackground(new Color(200,200,200));
				textarea.setBorder(BorderFactory.createBevelBorder(1));
				textarea.setForeground(new Color(55,55,55));
				textarea.setFont(new Font("Comic Sans",Font.ITALIC,15));
				textarea.setLineWrap(true);
				textarea.setEditable(true);   ////////////////////////////////////// MAKE IT TRUE IN EDITDOCUMENT , BE CAREFUL THE SCROLLBAR
				JScrollPane scrollbar = new JScrollPane();
				scrollbar.setBounds(20,20,700,300);
				scrollbar.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollbar.getViewport().add(textarea);
				frame3.add(scrollbar);
				JButton okbutton = new JButton("Ok");
				okbutton.setBounds(284, 407, 191, 33);    //VALTO SE SWSTO SHMEIO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
				frame3.getContentPane().add(okbutton);
				okbutton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) { ////////////SOSOSOSSOSOSOSSSOSOSOSOSOSOOSOSOSOSOSOSOSOSOSOSOSOSOSOSO
						document.setJTextArea(textarea); ///////////////MPOREI NA MHN PAIRNEI TO TEXTAREA SWSTA GIATI KRUVETAI PISW APO TO SCROLLBAR , AN NAI SVHSE TO SCROLLBAR
						frame3.dispose();                // mporei na thelei setVisible(false); anti gia kill edw.  
					}
				});
				frame3.setVisible(true);
			}
			else {
				JOptionPane.showMessageDialog(null, "You have to open the document first!!!");
			}
		}
		public void clonee() {
			replayManagerr.addtolist(new EditDocument(this.document,this.replayManagerr));      //EDW PAIZEI TO THIS.DOCUMENT NA SUNEXIZEI NA DEIXNEI SE AUTO POU DOULEUOUME ARA NA PHGAINE LATHOS STO REPLAYMANAGERH EDW KAINOURGIO H EKTOS KAINOURGIO
		}
}