package edu.uiowa.cs.warp;

/**
 * ChannelVisualization creates the visualizations for the channel analysis of
 * the WARP program.
 * <p>
 * 
 * CS2820 Spring 2024 Project: Implement this class to create the file
 * visualization that is requested in Warp.
 * 
 * @author sgoddard
 * @version 1.7
 *
 */

public class ChannelVisualization extends VisualizationObject {

	private static final String SOURCE_SUFFIX = ".ch";
	private static final String OBJECT_NAME = "Channel Analysis";
	private WarpInterface warp;
	private ChannelAnalysis ca;

	private ProgramSchedule sourceCode;
	/**
	 * The Schedule of the program source code.This represents the sequence of tasks in program 
	 * that can be visualized in the channel analysis
	 **/
	private Program program;
	/**
	 * boolean flag to see if deadlines were met
	 **/
	private Boolean deadlinesMet;
	/**
	 * constructor for new instance of ProgramVisualization
	 * 
	 * @param Warp interface that give the information needed for
	 *             ProgramVisualization
	 **/
	private ProgramSchedule schedule;

	/**
	 * 
	 * @param warp is a warp element from WarpInterfac
	 **/
	ChannelVisualization(WarpInterface warp) {
		super(new FileManager(), warp, SOURCE_SUFFIX);
		this.warp = warp;
		this.ca = warp.toChannelAnalysis();
		
		this.deadlinesMet = warp.deadlinesMet();
		this.program = warp.toProgram();
		this.schedule = ca.getChannelAnalysisTable();
		// this.sourceCode = ca.getChannelAnalysisTable();
	}

	/**
	 * The method is used to call the value of other methods in this class creating
	 * an object which can be used to display a chart.
	 * 
	 * @return GuiVisualization that can be used to make a chart
	 **/

	@Override
	public GuiVisualization displayVisualization() {
		return new GuiVisualization(createTitle(), createColumnHeader(), createVisualizationData());
	}


	/**
	 * Creates a title for the chart based on the program name.
	 * 
	 * @return createHeader Title of the program name
	 * 
	 **/
	@Override
	protected Description createHeader() {
		Description header = new Description();
		header.add(createTitle());
		header.add(String.format("Scheduler Name: %s\n", program.getSchedulerName()));
		if (program.getNumFaults()> 0) {
			header.add(String.format("numFaults: %d\n", program.getNumFaults()));
		}
		// creating header
		return header;
	}

	
	/**
	 * the method creates the column header for the chart. It dynamically changes
	 * depending on the size.
	 * 
	 * @return createColumnHeader create
	 **/
	@Override
	protected String[] createColumnHeader() {
		var timeSlots = schedule.getNumColumns();
		String [] columnNames = new String[timeSlots+1];
		columnNames [0] = "Time Slot";
		for (int i = 0; i< timeSlots+1; i++) {
			columnNames [i+1]= String.format("%d", i);
			
		}
		return columnNames;
		
	}

	/**
	 * This method deals with the data involved in the chart. It checks if the chart
	 * is empty or not.
	 * 
	 * @return createVisualizationData
	 **/
	@Override
	protected String[][] createVisualizationData() {
		// Generate visualization data from table in Channel analysis
		
		if (visualizationData == null) {
			int numRows = schedule.getNumRows(); // changed numrow

			int numColumns = schedule.getNumColumns(); //changed num
			visualizationData = new String[numRows][numColumns + 1];

			for (int row = 0; row < numRows; row++) {
				visualizationData[row][0] = String.format("%s", row);
				for (int column = 0; column < numColumns; column++) {
					 String value = schedule.get(row, column);
		             if (value == null || value.isEmpty()) {
		            	 
		            	 visualizationData[row][column + 1] = "-";
		             }else {
		            	 visualizationData[row][column + 1] = value;
		             }

				}
			}
		}
		return visualizationData;
	}

	/**
	 * This method creates the title depending on the file name.
	 * 
	 * @return createTitle takes the name of the file
	 */
	String createTitle() {
		return String.format("Channel Analysis for graph: %s" ,program.getName());
		
	}
}
