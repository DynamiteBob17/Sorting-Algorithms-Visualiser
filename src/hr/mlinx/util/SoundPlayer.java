package hr.mlinx.util;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

public class SoundPlayer {
	
	private static final int NOTE_RANGE = 128;
	public static final int DEFAULT_VOLUME = 20;
	
	private MidiChannel[] mc;
	private Instrument[] instruments;
	private String[] sounds;
	private int volume;
	
	public SoundPlayer() {
		super();
		
		try {
			Synthesizer midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();
			instruments = midiSynth.getAvailableInstruments();
			mc = midiSynth.getChannels();
			mc[0].programChange(instruments[0].getPatch().getProgram());
			
			volume = DEFAULT_VOLUME;
			
			sounds = new String[instruments.length];
			for (int i = 0; i < instruments.length; ++i) {
				sounds[i] = i + ". " + instruments[i].getName();
			}
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void play(int idx, int arraySize) {
		double noteRatio = (double) NOTE_RANGE / arraySize;
		mc[0].noteOn((int) (idx * noteRatio), volume);
	}
	
	public void notesOff() {
		for (int i = 0; i < NOTE_RANGE; ++i) {
			mc[0].noteOff(i);
		}
	}
	
	public void changeMidi(int midiIdx) {
		mc[0].programChange(instruments[midiIdx].getPatch().getProgram());
	}
	
	public String[] getSounds() {
		return sounds;
	}

	public void setVolume(int volume) {
		this.volume = volume;
	}

	public int getVolume() {
		return volume;
	}
	
}
