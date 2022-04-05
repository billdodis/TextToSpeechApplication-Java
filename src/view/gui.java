package view;

//import java.awt.EventQueue;      // EINIA SE SXOLIA h main 

import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import javax.swing.JScrollPane;
//import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
//import javax.swing.JTree;
//import javax.swing.ScrollPaneConstants;

//import java.awt.FlowLayout;
//import java.awt.Font;
import java.awt.BorderLayout;
//import java.awt.Color;
//import java.awt.Dimension;

//import javax.swing.BorderFactory;
//import javax.swing.BoxLayout;
//import javax.swing.JPanel;
//import commands.DocumentToSpeech;
import commands.ReplayManager;
import model.Document;
import model.FreeTTS;
//import model.FreeTTS;
import model.TTSFacade;
import commands.CommandsFactory;
import javax.swing.JTextArea;
import javax.swing.*;

import com.sun.speech.freetts.VoiceManager;

public class gui {

	JFrame frame;
	JFrame frame2;
	JButton filepath;
	public String path;
	public String filetype;
	public String decoding;
	Document document;
	JTextArea textarea;
	TTSFacade audiomanager;
	/**
	 * Launch the application.
	 */
	//public static void main(String[] args) {
	//	EventQueue.invokeLater(new Runnable() {
	//		public void run() {
	//			try {
	//				gui window = new gui();
	//				window.frame.setVisible(true);
	//			} catch (Exception e) {
	//				e.printStackTrace();
	//			}
	//		}
	//	});
	//}

	/**
	 * Create the application.
	 */
	public gui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JFrame frame = new JFrame();
		frame.setBounds(100, 100, 527, 487);
		frame.setResizable(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("AdvancedText2Speech Program");
		filepath = new JButton("New File");
		filepath.setBounds(71, 140, 360, 155);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(filepath);
		frame.setVisible(true);
		filepath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser filechooser = new JFileChooser(".");
				filechooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int value = filechooser.showOpenDialog(null);
				if(value==JFileChooser.APPROVE_OPTION) {
					File file = filechooser.getSelectedFile();
					path = file.getAbsolutePath();
					frame22(path);
				}
				else if(value == JFileChooser.CANCEL_OPTION){
					JOptionPane.showMessageDialog(null, "you closed with out selecting file, restarting the program.");
					System.exit(0);
		        }
				frame.setVisible(false);
				//killing the frame
			}
		});
	}
	private void frame22(String pathaaaa) {
		JFrame frame2 = new JFrame();
		frame2.setBounds(100, 100, 783, 487);
		frame2.setResizable(true);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setTitle("AdvancedText2Speech Program");
		frame2.getContentPane().setLayout(null);
		System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
		VoiceManager vm=VoiceManager.getInstance();
		audiomanager = new TTSFacade(vm,vm.getVoice("kevin16"));
		document = new Document(pathaaaa,audiomanager,textarea);/////////////////////////////////////////////////////////mporei na einai lathos 
		ReplayManager replaymanager = new ReplayManager();
		CommandsFactory commfactor = new CommandsFactory(document,replaymanager);
		JButton doc2speech = new JButton("Document To Speech");
		doc2speech.setBounds(282, 117, 191, 97);
		frame2.getContentPane().add(doc2speech, BorderLayout.NORTH);
		doc2speech.addActionListener(commfactor.createCommand("doc2speeeeeeeeech"));
		JButton savedoc = new JButton("Save Document");
		savedoc.setBounds(282, 10, 191, 97);
		frame2.getContentPane().add(savedoc, BorderLayout.EAST);
		savedoc.addActionListener(commfactor.createCommand("save"));
		JButton opendoc = new JButton("Open Document");
		opendoc.setBounds(28, 10, 191, 97);
		frame2.getContentPane().add(opendoc);
		opendoc.addActionListener(commfactor.createCommand("open"));
		JButton editdoc = new JButton("Edit Document");
		editdoc.setBounds(28, 117, 191, 97);
		frame2.getContentPane().add(editdoc);
		editdoc.addActionListener(commfactor.createCommand("edit"));
		JButton lineplayer = new JButton("Play Line");
		lineplayer.setBounds(282, 224, 191, 97);
		frame2.getContentPane().add(lineplayer);
		lineplayer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringline = JOptionPane.showInputDialog("Insert the number of the starting line you want to be played.");
				int intline = Integer.parseInt(stringline);
				String stringline2 = JOptionPane.showInputDialog("Insert the number of the ending line you want to be played.");
				int intline2 = Integer.parseInt(stringline2);
				document.playLine(intline,intline2);
				if(replaymanager.isActiveRecording()) {
					replaymanager.addtolist(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							document.playLine(intline,intline2);
						}});
				}
			}
		});
		JButton setvolume = new JButton("Set Volume");
		setvolume.setBounds(282, 331, 191, 97);
		frame2.getContentPane().add(setvolume);
		setvolume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringvolume = JOptionPane.showInputDialog("Insert the volume.");
				float volume = Float.parseFloat(stringvolume);
				document.setVolume(volume);
				if(replaymanager.isActiveRecording()) {
					replaymanager.addtolist(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							document.setVolume(volume);
						}
					});
				}
			}
		});
		JButton setpitch = new JButton("Set Pitch");
		setpitch.setBounds(28, 224, 191, 97);
		frame2.getContentPane().add(setpitch);
		setpitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringpitch = JOptionPane.showInputDialog("Insert the pitch.");
				float pitch = Float.parseFloat(stringpitch);
				document.setPitch(pitch);
				if(replaymanager.isActiveRecording()) {
					replaymanager.addtolist(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							document.setPitch(pitch);
						}
					});
				}
			}
		});
		JButton setrate = new JButton("Set Rate");
		setrate.setBounds(28, 331, 191, 97);
		frame2.getContentPane().add(setrate);
		setrate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stringrate = JOptionPane.showInputDialog("Insert the rate.");
				float rate = Float.parseFloat(stringrate);
				document.setRate(rate);
				if(replaymanager.isActiveRecording()) {
					replaymanager.addtolist(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							document.setRate(rate);
						}
					});
				}
			}
		});
		JButton startrecord = new JButton("Start Recording");
		startrecord.setBounds(540, 10, 191, 97);
		frame2.getContentPane().add(startrecord);
		startrecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				replaymanager.startRecording();
			}
		});
		JButton stoprecord = new JButton("Stop Recording");
		stoprecord.setBounds(540, 117, 191, 97);
		frame2.getContentPane().add(stoprecord);
		stoprecord.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				replaymanager.endRecording();
			}
		});
		JButton replayactions = new JButton("Replay Actions");
		replayactions.setBounds(540, 224, 191, 97);
		frame2.getContentPane().add(replayactions);
		replayactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				replaymanager.replay();
			}
		});
		JButton clearactions = new JButton("Clear Actions");
		clearactions.setBounds(540, 331, 191, 97);
		frame2.getContentPane().add(clearactions);
		clearactions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				replaymanager.clear();
			}
		});
		frame2.setVisible(true);
		}
}

