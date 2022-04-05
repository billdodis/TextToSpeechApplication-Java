package commands;

import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ReplayManager {
		private boolean recordingStatus=false;
		private ArrayList<ActionListener> replays;
		public ReplayManager() {
			replays = new ArrayList<ActionListener>();
		}

		public void replay(){
			for(int i=0;i<replays.size();i++) {
				replays.get(i).actionPerformed(null);
			}
		}
		
		public void addtolist(ActionListener actionlistener) {
			replays.add(actionlistener);
		}
		
		public void startRecording(){
			recordingStatus=true;
		}
		
		public ArrayList<ActionListener> getReplays(){
			return replays;
		}
		
		public int getReplaysSize(){
			return replays.size();
			//FOR TESTING PURPOSES ONLY.
		}
		
		public void endRecording(){
			recordingStatus=false;
		}

		public boolean isActiveRecording(){
			return recordingStatus;
		}

		public void clear() {
			replays.clear();
		}

}
