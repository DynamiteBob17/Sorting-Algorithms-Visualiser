package hr.mlinx.animations;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;
import hr.mlinx.util.Util;

public class Shuffling extends Animation {

	public Shuffling(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super(tp, sv, bp);
	}

	@Override
	public void run() {
		int len = sv.getVisualController().getCurrentVisual().getSortSize();
		for (int i = 0; i < len; ++i) {
			int rand = Util.R.nextInt(len);
			int temp = sv.getArray()[i];
			sv.setArrayElement(i, sv.getArray()[rand]);
			sv.setSingle(rand, temp); // only play sound and highlight for rand
		}
		
		sv.finishAnimation();
		
		if (!sv.getAnimator().getWatching().isAlive()) {
			tp.toggle();
			bp.toggle();
		}
	}

}
