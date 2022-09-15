package hr.mlinx.sorting_algorithms;

import hr.mlinx.components.SortVisualiser;

public class CycleSort extends SortingAlgorithm {

	public CycleSort(SortVisualiser sv) {
		super(sv);
	}

	@Override
	public void sort() {
        for (int cycle_start = 0; cycle_start <= len - 2; cycle_start++) {
        	stats.addAccesses(1);
            int item = array[cycle_start];
 
            int pos = cycle_start;
            for (int i = cycle_start + 1; i < len; i++) {
            	stats.addComps(1);
            	stats.addAccesses(1);
                if (array[i] < item)
                    pos++;
            }
 
            if (pos == cycle_start)
                continue;
            
            boolean passedOnce = false;
            stats.addComps(1);
            stats.addAccesses(1);
            while (item == array[pos]) {
            	if (passedOnce) {
            		stats.addComps(1);
            		stats.addAccesses(1);
            	} else {
            		passedOnce = true;
            	}
                pos += 1;
            }
 
            if (pos != cycle_start) {
                int temp = item;
                stats.addAccesses(1);
                item = array[pos];
                sv.setSingle(pos, temp);
            }
 
            while (pos != cycle_start) {
                pos = cycle_start;
 
                for (int i = cycle_start + 1; i < len; i++) {
                	stats.addComps(1);
                	stats.addAccesses(1);
                    if (array[i] < item)
                        pos += 1;
                }

                passedOnce = false;
                stats.addComps(1);
            	stats.addAccesses(1);
                while (item == array[pos]) {
                	if (passedOnce) {
                		stats.addComps(1);
                    	stats.addAccesses(1);
                	} else {
                		passedOnce = true;
                	}
                    pos += 1;
                }
 
                if (item != array[pos]) {
                    int temp = item;
                	stats.addAccesses(1);
                    item = array[pos];
                    sv.setSingle(pos, temp);
                }
            }
        }
    }

	@Override
	public String getName() {
		return "Cycle Sort";
	}
}
