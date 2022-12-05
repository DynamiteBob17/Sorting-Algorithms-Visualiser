package hr.mlinx.util;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

import hr.mlinx.components.SortVisualiser;
import hr.mlinx.sorting_algorithms.BitonicSort;
import hr.mlinx.sorting_algorithms.BubbleSort;
import hr.mlinx.sorting_algorithms.CocktailSort;
import hr.mlinx.sorting_algorithms.CombSort;
import hr.mlinx.sorting_algorithms.CountingSort;
import hr.mlinx.sorting_algorithms.CycleSort;
import hr.mlinx.sorting_algorithms.DoubleSelectionSort;
import hr.mlinx.sorting_algorithms.GravitySort;
import hr.mlinx.sorting_algorithms.InsertionSort;
import hr.mlinx.sorting_algorithms.LRQuickSortParallel;
import hr.mlinx.sorting_algorithms.MaxHeapSort;
import hr.mlinx.sorting_algorithms.MergeSort;
import hr.mlinx.sorting_algorithms.MergeSortInPlace;
import hr.mlinx.sorting_algorithms.MergeSortIterative;
import hr.mlinx.sorting_algorithms.MinHeapSort;
import hr.mlinx.sorting_algorithms.OddEvenMergeSort;
import hr.mlinx.sorting_algorithms.OddEvenSort;
import hr.mlinx.sorting_algorithms.QuickSort;
import hr.mlinx.sorting_algorithms.QuickSortIterative;
import hr.mlinx.sorting_algorithms.RadixSort;
import hr.mlinx.sorting_algorithms.RadixSortInPlace;
import hr.mlinx.sorting_algorithms.SelectionSort;
import hr.mlinx.sorting_algorithms.ShellSort;
import hr.mlinx.sorting_algorithms.SortingAlgorithm;
import hr.mlinx.sorting_algorithms.WeaveMergeSort;

public class Util {
	
	public static final Dimension RES = Toolkit.getDefaultToolkit().getScreenSize();
	public static final double SCALE = RES.getWidth() / 1920.0;
	public static final Random R = new Random();
	
	public static final float FONT_SIZE = 15f * (float) SCALE;
	
	public static void setUIPresets() {
		Font messageFont = new Font("Verdana", Font.PLAIN, (int) FONT_SIZE);
		Font componentFont = new Font("Segoe", Font.PLAIN, (int) FONT_SIZE);
		
		UIManager.put("OptionPane.messageFont", messageFont);
		UIManager.put("OptionPane.buttonFont", messageFont);
		UIManager.put("TextField.font", messageFont);
		UIManager.put("ComboBox.font", componentFont);
		UIManager.put("Label.font", componentFont);
		UIManager.put("Button.font", componentFont);
		UIManager.put("Spinner.font", componentFont);
		
		try {
			UIManager.setLookAndFeel(new FlatDarkLaf());
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	
	public static SortingAlgorithm[] fillSorts(SortVisualiser sv) {
		return new SortingAlgorithm[] {
				new SelectionSort(sv),
				new DoubleSelectionSort(sv),
				new BubbleSort(sv),
				new CocktailSort(sv),
				new OddEvenSort(sv),
				new InsertionSort(sv),
				new CycleSort(sv),
				new GravitySort(sv),
				new MergeSort(sv),
				new MergeSortIterative(sv),
				new QuickSort(sv),
				new QuickSortIterative(sv),
				new LRQuickSortParallel(sv),
				new MaxHeapSort(sv),
				new MinHeapSort(sv),
				new CountingSort(sv),
				new RadixSort(sv, 2),
				new RadixSort(sv, 10),
				new RadixSortInPlace(sv, 2),
				new RadixSortInPlace(sv, 10),
				new CombSort(sv),
				new ShellSort(sv),
				new BitonicSort(sv, 1),
				new OddEvenMergeSort(sv),
				new MergeSortInPlace(sv),
				new WeaveMergeSort(sv),	
		};
	}
	
	public static boolean isUnix() {
		return System.getProperty("os.name").startsWith("Linux");
	}
	
}
