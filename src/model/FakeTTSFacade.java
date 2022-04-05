package model;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class FakeTTSFacade extends TTSFacade{
	private String playedContents = "";
	private String playedLines = "";
	private float volumee;
	private float ratee;
	private float pitchh;
	VoiceManager vm;
	Voice voice;
	FreeTTS tts;
		// DES APO MAIL voice kai voicemanager ,prepei mallon na arxikopoiithoun edw gia na ginei h grammh 12 (new ths FreeTTS).
	public FakeTTSFacade(VoiceManager vmm, Voice voicee) {
		super(vmm, voicee);
	}
	@Override
	public void play(String stringg) {
		super.play(stringg);
		playedContents+=stringg;
		playedLines+=stringg;
	}
	@Override
	public void setVolume(float volume) {
		super.setVolume(volume);
		volumee=volume;
	}
	@Override
	public void setPitch(float pitchnum) {
		super.setPitch(pitchnum);
		pitchh=pitchnum;
	}
	@Override
	public void setRate(float ratenm) {
		super.setRate(ratenm);
		ratee=ratenm;
	}
	public String getPlayedContents() {
		return playedContents;
	}
	public String getPlayedLines() {
		return playedLines;
	}
	public float getVolume() {
		return volumee;
	}
	public float getPitch() {
		return pitchh;
	}
	public float getRate() {
		return ratee;
	}
}
