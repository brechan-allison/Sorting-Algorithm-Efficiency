import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project6 extends Frame implements ActionListener {
	SortAlgorithms s = new SortAlgorithms();
	// Retrieved command code
	String command = "";

	public static void main(String[] args) {
		Frame frame = new Project6();

		frame.setResizable(false);
		frame.setSize(675, 650);
		frame.setVisible(true);

	}

	public Project6() {
		setTitle("Sort Algorithms");

		// Create Menu Bar

		MenuBar mb = new MenuBar();
		setMenuBar(mb);

		// Create Menu Group Labeled "File"

		Menu FileMenu = new Menu("File");

		// Add it to Menu Bar

		mb.add(FileMenu);

		MenuItem miAbout = new MenuItem("About");
		miAbout.addActionListener(this);
		FileMenu.add(miAbout);

		MenuItem miOpen = new MenuItem("Open");
		miOpen.addActionListener(this);
		FileMenu.add(miOpen);

		MenuItem miExit = new MenuItem("Exit");
		miExit.addActionListener(this);
		FileMenu.add(miExit);

		// Create Menu Group Labeled "Search"

		Menu sortMenu = new Menu("Sort");

		// Add it to Menu Bar

		mb.add(sortMenu);

		// Create Menu Items
		// Add action Listener
		// Add to "Search" Menu Group

		MenuItem miInsertionSort = new MenuItem("Insertion Sort");
		miInsertionSort.addActionListener(this);
		sortMenu.add(miInsertionSort);

		MenuItem miQuickSort = new MenuItem("Quick Sort");
		miQuickSort.addActionListener(this);
		sortMenu.add(miQuickSort);

		MenuItem miMergeSort = new MenuItem("Merge Sort");
		miMergeSort.addActionListener(this);
		sortMenu.add(miMergeSort);

		MenuItem miJavaSort = new MenuItem("Java Sort");
		miJavaSort.addActionListener(this);
		sortMenu.add(miJavaSort);

		MenuItem miJavaParallelSort = new MenuItem("Java Parallel Sort");
		miJavaParallelSort.addActionListener(this);
		sortMenu.add(miJavaParallelSort);

		Menu mRadixSort = new Menu("Radix Sort");
		mRadixSort.addActionListener(this);
		sortMenu.add(mRadixSort);

		MenuItem miRadix10 = new MenuItem("Radix 10");
		miRadix10.addActionListener(this);
		mRadixSort.add(miRadix10);

		MenuItem miRadix2 = new MenuItem("Radix 2");
		miRadix2.addActionListener(this);
		mRadixSort.add(miRadix2);

		MenuItem miRadix16 = new MenuItem("Radix 16");
		miRadix16.addActionListener(this);
		mRadixSort.add(miRadix16);

		MenuItem miRadix256 = new MenuItem("Radix 256");
		miRadix256.addActionListener(this);
		mRadixSort.add(miRadix256);
		// Create Menu Group Labeled "Search"

		Menu ChartMenu = new Menu("Charts");

		// Add it to Menu Bar

		// mb.add(ChartMenu);

		// Create Menu Items
		// Add action Listener
		// Add to "Search" Menu Group

		/*
		 * MenuItem miAAT = new MenuItem("Average Access Time");
		 * miAAT.addActionListener(this); ChartMenu.add(miAAT);
		 * 
		 * MenuItem miACS = new
		 * MenuItem("Average Number of Comparisons for Successful Search");
		 * miACS.addActionListener(this); ChartMenu.add(miACS);
		 * 
		 * MenuItem miACF = new
		 * MenuItem("Average Number of Comparisons for Unsuccessful Search");
		 * miACF.addActionListener(this); ChartMenu.add(miACF);
		 */

		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}

			public void windowActivated(WindowEvent ev) {
				repaint();
			}

			public void windowStateChanged(WindowEvent ev) {
				repaint();
			}
		};

		ComponentListener k = new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				repaint();
			}
		};

		// register listeners
		this.addWindowListener(l);
		this.addComponentListener(k);
	}

	// ******************************************************************************
	// called by windows manager whenever the application window performs an action
	// (select a menu item, close, resize, ....
	// ******************************************************************************

	public void actionPerformed(ActionEvent ev) {
		// figure out which command was issued

		command = ev.getActionCommand();

		// take action accordingly

		if ("Open".equals(command)) {

			s.openDataFile();
//			

			repaint();
		}

		else if ("Exit".equals(command)) {
			System.exit(0);
		} else if ("Insertion Sort".equals(command)) {
			s.insertionSort();
			repaint();
		} else if ("Quick Sort".equals(command)) {
			s.quickSort();
			repaint();
		} else if ("Merge Sort".equals(command)) {
			s.mergeSort();
			repaint();
		} else if ("Java Sort".equals(command)) {
			s.javaSort();
			repaint();
		} else if ("Java Parallel Sort".equals(command)) {
			s.javaParallelSort();
			repaint();
		} else if ("Radix 10".equals(command)) {
			s.radixSort(10);
			repaint();
		} else if ("Radix 2".equals(command)) {
			s.radixSort(2);
			repaint();
		} else if ("Radix 16".equals(command)) {
			s.radixSort(16);
			repaint();
		} else if ("Radix 256".equals(command)) {
			s.radixSort(256);
			repaint();
		} else {
			repaint();
		}
	}
	// ********************************************************
	// called by repaint() to redraw the screen
	// ********************************************************

	public void paint(Graphics g) {

		if ("Open".equals(command)) {
			// Acknowledge that file was opened
			if (s.dataFileName != null) {
				g.drawString("File --  " + s.dataFileName + "  -- was successfully opened", 200, 200);
				g.drawString("Number of Data Items = " + Integer.toString(s.n), 230, 250);
			} else {
				g.drawString("NO Data File is Open", 300, 200);
			}
		}

		if ("Insertion Sort".equals(command) || "Quick Sort".equals(command) || "Merge Sort".equals(command)
				|| "Java Sort".equals(command) || "Java Parallel Sort".equals(command) || "Radix 10".equals(command)
				|| "Radix 2".equals(command) || "Radix 16".equals(command) || "Radix 256".equals(command)) {
			Font f = new Font(Font.SANS_SERIF, Font.BOLD, 12);
			g.setFont(f);
			// vertical lines
			g.drawLine(90, 560, 90, 155);
			g.drawLine(245, 560, 245, 155);
			g.drawLine(355, 560, 355, 230);
			g.drawLine(465, 560, 465, 230);
			g.drawLine(575, 560, 575, 155);
			// g.drawLine(685, 510, 685, 255);
			// g.drawLine(795, 510, 795, 155);
			// horizontal lines
			g.drawLine(90, 155, 575, 155);
			g.drawLine(245, 210, 575, 210);
			g.drawLine(245, 230, 575, 230);
			// g.drawLine(355, 255, 795, 255);
			g.drawLine(90, 310, 575, 310);
			g.drawLine(90, 335, 575, 335);
			g.drawLine(90, 360, 575, 360);
			g.drawLine(90, 385, 575, 385);
			g.drawLine(90, 410, 575, 410);
			g.drawLine(90, 435, 575, 435);
			g.drawLine(90, 460, 575, 460);
			g.drawLine(90, 485, 575, 485);
			g.drawLine(90, 510, 575, 510);
			g.drawLine(90, 535, 575, 535);
			g.drawLine(90, 560, 575, 560);
			// titles
			g.setColor(Color.RED);
			g.drawString("Data File Size -- " + Integer.toString(s.n), 320, 180);
			g.setColor(Color.BLACK);
			g.drawString("Experiment", 125, 230);
			g.drawString("Measured Criteria", 340, 225);

			g.drawString("Elapsed", 280, 270);
			g.drawString("Time", 290, 285);
			g.drawString("Nano Seconds", 260, 300);

			g.drawString("Number", 390, 270);
			g.drawString("Of", 400, 285);
			g.drawString("Data Movements", 357, 300);

			g.drawString("Number", 490, 270);
			g.drawString("Of", 500, 285);
			g.drawString("Comparisons", 480, 300);

			// data
			g.drawString("Insertion Sort", 100, 325);
			g.drawString(Long.toString(s.elapsedTime[0]), 250, 325);
			g.drawString(Long.toString(s.nDataMovements[0]), 360, 325);
			g.drawString(Long.toString(s.nComparisons[0]), 470, 325);

			g.drawString("Quick Sort", 100, 350);
			g.drawString(Long.toString(s.elapsedTime[1]), 270, 350);
			g.drawString(Long.toString(s.nDataMovements[1]), 380, 350);
			g.drawString(Long.toString(s.nComparisons[1]), 490, 350);

			g.drawString("Merge Sort", 100, 375);
			g.drawString(Long.toString(s.elapsedTime[2]), 270, 375);
			g.drawString(Long.toString(s.nDataMovements[2]), 380, 375);
			g.drawString(Long.toString(s.nComparisons[2]), 490, 375);

			g.drawString("Radix Sort", 100, 400);

			g.drawString("Radix 10", 180, 425);
			g.drawString(Long.toString(s.elapsedTime[3]), 270, 425);
			g.drawString(Long.toString(s.nDataMovements[3]), 380, 425);
			g.drawString(Long.toString(s.nComparisons[3]), 490, 425);

			g.drawString("Radix 2", 180, 450);
			g.drawString(Long.toString(s.elapsedTime[4]), 270, 450);
			g.drawString(Long.toString(s.nDataMovements[4]), 380, 450);
			g.drawString(Long.toString(s.nComparisons[4]), 490, 450);

			g.drawString("Radix 16", 180, 475);
			g.drawString(Long.toString(s.elapsedTime[5]), 270, 475);
			g.drawString(Long.toString(s.nDataMovements[5]), 380, 475);
			g.drawString(Long.toString(s.nComparisons[5]), 490, 475);

			g.drawString("Radix 256", 180, 500);
			g.drawString(Long.toString(s.elapsedTime[6]), 270, 500);
			g.drawString(Long.toString(s.nDataMovements[6]), 380, 500);
			g.drawString(Long.toString(s.nComparisons[6]), 490, 500);

			g.drawString("Java Sort", 100, 525);
			g.drawString(Long.toString(s.elapsedTime[7]), 270, 525);
			g.drawString(Long.toString(s.nDataMovements[7]), 380, 525);
			g.drawString(Long.toString(s.nComparisons[7]), 490, 525);

			g.drawString("Java Parallel Sort", 100, 550);
			g.drawString(Long.toString(s.elapsedTime[8]), 270, 550);
			g.drawString(Long.toString(s.nDataMovements[8]), 380, 550);
			g.drawString(Long.toString(s.nComparisons[8]), 490, 550);

		}

	}
}