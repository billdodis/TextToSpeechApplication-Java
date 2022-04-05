package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSFacade {
	VoiceManager vm;
	Voice voice;
	FreeTTS tts;
	public TTSFacade(VoiceManager vmm, Voice voicee){
		this.vm=vmm;
		this.voice=voicee;
		tts = new FreeTTS(vm.getVoice("kevin16"),vm);
		// DES APO MAIL voice kai voicemanager ,prepei mallon na arxikopoiithoun edw gia na ginei h grammh 12 (new ths FreeTTS).
	}
	public void play(String stringg) {
		tts.play(stringg);
	}
	
	public void setVolume(float volume) {
		tts.setVolume(volume);
	}
	
	public void setPitch(float pitchnum) {
		tts.setPitch(pitchnum);
	}
	
	public void setRate(float ratenm) {
		tts.setRate(ratenm);
	}
}
