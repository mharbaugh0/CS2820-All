package edu.uiowa.cs.warp;

/**
 * ChannelVisualization creates the visualizations for the channel analysis of the WARP program.
 * <p>
 * 
 * CS2820 Spring 2024 Project: Implement this class to create the file visualization that is requested
 * in Warp.
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
   * the warp program instance
   */
  private Program program;
  /**
   * boolean flag to see if deadlines were met
   */
  private Boolean deadlinesMet;
  /**
   * constructor for new instance of ProgramVisualization
   * @param Warp interface that give the information needed for ProgramVisualization
   */
  private ProgramSchedule schedule;
  

  ChannelVisualization(WarpInterface warp) {
    super(new FileManager(), warp, SOURCE_SUFFIX);
    this.warp = warp;
    this.ca = warp.toChannelAnalysis();
    this.deadlinesMet = warp.deadlinesMet();
    this.program = warp.toProgram();
    this.schedule = program.getSchedule();
    
    
    
  }
  @Override
  public GuiVisualization displayVisualization() {
      return new GuiVisualization(createTitle(), createColumnHeader(), createVisualizationData());
  }
      @Override
      
      
  protected Description createHeader() {
    	  Description header = new Description();
    	  header.add(createTitle());
          header.add(String.format("Scheduler Name: %s\n", program.getSchedulerName()));
          // creating header
          return header;
      }
      
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
   @Override
   protected String[] createColumnHeader() {
       
       int numChannels = program.getNumChannels();

       
       String[] headers = new String[numChannels + 1 ];  
       headers[0] = "Time Slot";
       for (int i = 1; i <= numChannels; i++) {
           headers[i] = "Channel " + i;
       }
       return headers;
   }

   
   
   
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

   private String createTitle() {
	   return String.format("Channel Analysis for graph: %s\n", program.getName());    
   }
}

