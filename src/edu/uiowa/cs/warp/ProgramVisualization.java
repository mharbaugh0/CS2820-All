/**
 * 
 */
package edu.uiowa.cs.warp;

/**
 * ProgramVisualization.java 
 * Creates a visual representation of a given program schedule, 
 * including title, footers, rows, columns, and the schedule data. 
 * The visualization is handled by a superclass (VisualizationObject),
 * which provides specific implementations for creating the necessary
 * components based on the program's information.
 * 
 * Created by Steve Goddard
 * Modified by Dell Harbaugh and Sixuan Liu 02/10/2024
 * @author sgoddard
 * @version 1.5
 * 
 */
public class ProgramVisualization extends VisualizationObject {

  private static final String SOURCE_SUFFIX = ".dsl";
  private ProgramSchedule sourceCode;
  private Program program;
  private Boolean deadlinesMet;

  /**
   * sets program, sourceCode, and deadlinesMet using information obtained
   * from warp
   * @param warp provides specifications for visualization 
   */
  ProgramVisualization(WarpInterface warp) {
    super(new FileManager(), warp, SOURCE_SUFFIX);
    this.program = warp.toProgram();
    this.sourceCode = program.getSchedule();
    this.deadlinesMet = warp.deadlinesMet();
  }

  /**
   * @return visualization with title, column header, data added
   */
  @Override
  public GuiVisualization displayVisualization() {
    return new GuiVisualization(createTitle(), createColumnHeader(), createVisualizationData());
  }

  /**
   * creates visualization header by retrieving and adding 
   * title, scheduler name, and m value, E2E value, and number of channels.  
   * @return header is a Description header for the visualization
   */
  @Override
  protected Description createHeader() {
    Description header = new Description();

    header.add(createTitle());
    header.add(String.format("Scheduler Name: %s\n", program.getSchedulerName()));

    /* The following parameters are output based on a special schedule or the fault model */
    if (program.getNumFaults() > 0) { // only specify when deterministic fault model is assumed
      header.add(String.format("numFaults: %d\n", program.getNumFaults()));
    }
    header.add(String.format("M: %s\n", String.valueOf(program.getMinPacketReceptionRate())));
    header.add(String.format("E2E: %s\n", String.valueOf(program.getE2e())));
    header.add(String.format("nChannels: %d\n", program.getNumChannels()));
    return header;
  }

  /**
   * creates visualization footer by retrieving and adding deadline status message  
   * @return footer is a Description containing the relevant deadline status message
   */
  @Override
  protected Description createFooter() {
    Description footer = new Description();
    String deadlineMsg = null;

    if (deadlinesMet) {
      deadlineMsg = "All flows meet their deadlines\n";
    } else {
      deadlineMsg = "WARNING: NOT all flows meet their deadlines. See deadline analysis report.\n";
    }
    footer.add(String.format("// %s", deadlineMsg));
    return footer;
  }

  /**
   * creates visualization column header, calls method to alphabetize node names,
   * adds "Time Slot" to first index of column header, then node names in order 
   * to subsequent indices
   * @return columnNames is a String Array containing "Time Slot" and node names
   */
  @Override
  protected String[] createColumnHeader() {
    var orderedNodes = program.toWorkLoad().getNodeNamesOrderedAlphabetically();
    String[] columnNames = new String[orderedNodes.length + 1];
    columnNames[0] = "Time Slot"; // add the Time Slot column header first
    /* loop through the node names, adding each to the header */
    for (int i = 0; i < orderedNodes.length; i++) {
      columnNames[i + 1] = orderedNodes[i];
    }
    return columnNames;
  }

  /**
   * creates visualization by retrieving and placing column and row data
   * in a String matrix
   * @return visualizationData is a String matrix containing visualization data
   */
  @Override
  protected String[][] createVisualizationData() {
    if (visualizationData == null) {
      int numRows = sourceCode.getNumRows();
      int numColumns = sourceCode.getNumColumns();
      visualizationData = new String[numRows][numColumns + 1];

      for (int row = 0; row < numRows; row++) {
        visualizationData[row][0] = String.format("%s", row);
        for (int column = 0; column < numColumns; column++) {
          visualizationData[row][column + 1] = sourceCode.get(row, column);
        }
      }
    }
    return visualizationData;
  }

  /**
   * creates title for visualization 
   * @return String containing program title 
   */
  private String createTitle() {
    return String.format("WARP program for graph %s\n", program.getName());
  }
}
