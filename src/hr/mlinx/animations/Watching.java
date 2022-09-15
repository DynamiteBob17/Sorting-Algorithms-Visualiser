package hr.mlinx.animations;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;

public class Watching extends Animation {

	public Watching(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super(tp, sv, bp);
	}

	@Override
	public void run() {
		tp.toggleWatch();
		tp.toggle();
		bp.toggle();
		double prevSleep = sv.getSleep();
		
		boolean again = false; // play radix in-place, bitonic, heap and weave mearge sorts again from an already sorted array
		int start = tp.getAlgoBox().getSelectedIndex();
		for (int i = start;
				i < sv.getSorts().length && !tp.endWatching(); ++i) {
			if (i != start)
				tp.getAlgoBox().setSelectedItem(sv.getSorts()[i].getName());
			
			try {
				if (!again) {
					sv.getAnimator().shuffle();
					sv.getAnimator().getShuffling().join();
					if (!tp.endWatching())
						Thread.sleep(1500);
				}
				sv.getAnimator().sort();
				sv.getAnimator().getSorting().join();
				if (!tp.endWatching())
					Thread.sleep(1500);
			} catch (InterruptedException e) {
			}
			
			String s = sv.getSorts()[i].getName();
			if (again) {
				again = false;
			} else if (s.contains("LSD R.S.") || s.contains("Heap")
					   || s.contains("Bitonic") || s.contains("Weave")) {
				again = true;
				--i;
			}
		}
		
		if (tp.endWatching()) {
			tp.setEndWatching(false);
			sv.setSleep(prevSleep);
			bp.getSleepSpinner().setValue(prevSleep);
		}
		tp.toggle();
		bp.toggle();
		tp.toggleWatch();
		sv.repaint();
	}

}
