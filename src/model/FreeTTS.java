package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FreeTTS implements Facade{
	VoiceManager vm;
	Voice voice;
	public FreeTTS(Voice voicee,VoiceManager vmm){
		this.voice=voicee;
		this.vm=vmm;
	}
	
	public void play(String speech) {
		voice.allocate();
		voice.speak(speech);
	}

	public void setVolume(float volume) {
		voice.setVolume(volume);
	}
	
	public void setPitch(float pitchnum) {
		voice.setPitch(pitchnum);
	}

	public void setRate(float ratenm) {
		voice.setRate(ratenm);
	}
}
