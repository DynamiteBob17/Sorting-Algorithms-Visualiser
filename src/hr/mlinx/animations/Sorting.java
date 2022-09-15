package hr.mlinx.animations;

import hr.mlinx.components.BottomPanel;
import hr.mlinx.components.SortVisualiser;
import hr.mlinx.components.TopPanel;
import hr.mlinx.visuals.Visual;

public class Sorting extends Animation {
	
	private int confirmIdx;
	
	public Sorting(TopPanel tp, SortVisualiser sv, BottomPanel bp) {
		super(tp, sv, bp);
		
		confirmIdx = -1;
	}

	@Override
	public void run() {
		sv.getStats().resetStats();
		
		sv.getSorts()[tp.getAlgoBox().getSelectedIndex()].setLen(sv.getVc().get().getSortSize());
		sv.getSorts()[tp.getAlgoBox().getSelectedIndex()].sort();
		
		sv.finishAnimation();
		sv.fillArray();
		
		confirm();
		
		if (!sv.getAnimator().getWatching().isAlive()) {
			tp.toggle();
			bp.toggle();
		}
	}
	
	private void confirm() {
		confirmIdx = 0;
		sv.getVc().get().setColorSubtractionStep(0);
		
		for (int i = 0; i < sv.getVc().get().getSortSize(); ++i) {
			confirmIdx = i;
			sv.highlight(i);
		}
		
		confirmIdx = -1;
		sv.getVc().get().setColorSubtractionStep(Visual.DEFAULT_COLOR_SUBTRACTION_STEP);
		sv.finishAnimation();
	}
	
	public int getConfirmIdx() {
		return confirmIdx;
	}
	
}
