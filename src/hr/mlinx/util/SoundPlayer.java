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
	private int midiIdx;
	
	public SoundPlayer() {
		super();
		
		try {
			Synthesizer midiSynth = MidiSystem.getSynthesizer();
			midiSynth.open();
			instruments = midiSynth.getAvailableInstruments();
			mc = midiSynth.getChannels();
			
			volume = DEFAULT_VOLUME;
			midiIdx = 0;
			
			sounds = new String[instruments.length];
			String name;
			for (int i = 0; i < instruments.length; ++i) {
				name = instruments[i].getName();
				sounds[i] = i + ". " + name;
				
				if (name.toLowerCase().contains("808 tom")
						|| midiIdx <= 0 && name.toLowerCase().contains("sine"))
					midiIdx = i;
			}
			mc[0].programChange(instruments[midiIdx].getPatch().getProgram());
		} catch (MidiUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void play(int idx, int arraySize) {
		if (mc != null) {
			double noteRatio = (double) NOTE_RANGE / arraySize;
			mc[0].noteOn((int) (idx * noteRatio), volume);
		}
	}
	
	public void notesOff() {
		if (mc != null) {
			for (int i = 0; i < NOTE_RANGE; ++i) {
				mc[0].noteOff(i);
			}
		}
	}
	
	public void changeMidi(int midiIdx) {
		if (mc != null) {
			this.midiIdx = midiIdx;
			mc[0].programChange(instruments[midiIdx].getPatch().getProgram());
		}
	}
	
	public int getMidiIdx() {
		return midiIdx;
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
