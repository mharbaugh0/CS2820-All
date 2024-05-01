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
		this.schedule = program.getSchedule();
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

		int numChannels = program.getNumChannels();

		// ADD NUMFAULTS
		String[] headers = new String[numChannels + 1];
		headers[0] = "Time Slot";
		for (int i = 1; i <= numChannels; i++) {
			headers[i] = ""+(char)('A'+i-1);
		}
		return headers;
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
			int numRows = program.getNumChannels();

			int numColumns = schedule.getNumRows();
			visualizationData = new String[numRows][numColumns + 1];

			for (int row = 0; row < numRows; row++) {
				visualizationData[row][0] = String.format("%s", row);
				for (int column = 0; column < numColumns; column++) {
					visualizationData[row][column + 1] = schedule.get(row, column);

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
