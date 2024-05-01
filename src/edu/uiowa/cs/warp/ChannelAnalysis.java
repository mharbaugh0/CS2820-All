package edu.uiowa.cs.warp;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
/**
 * ChannelAnalysis.java 
 * Analyzes the warp and program channels for conflicts.
 * 
 * Created by Steve Goddard
 * Modified by Dell Harbaugh and Sixuan Liu 02/07/2024
 * @author sgoddard
 * @version 1.7
 *
 */

// Use Eclipse to refactor the constructors of the ChannelAnalysis class to reduce code duplication.
// Hint: this is a great example where you can call one constructor from the other using the call
// this(), with the proper parameter. This was done as an example in Lecture 8 Inheritance.
public class ChannelAnalysis {

  private Program program;
  private ProgramSchedule programTable;
  private Boolean conflictExists;
  
/**
 * Inherits parameters from initialized program method to create constructor
 * @param warp is a warp element from WarpInterface
 */
  public ChannelAnalysis(WarpInterface warp) {
      initializeProgram(warp.toProgram());
  }
 /**
  * Inherits parameters from initialized program method to create constructor
  * @param program is the program class for channel 
  */
  public ChannelAnalysis(Program program) {
      initializeProgram(program);
  }
  /**
   * Initializing program with program and overriding the constructor
   * @param program is the program class for channel
   */
  private void initializeProgram(Program program) {
      this.program = program;
      this.programTable = program.getSchedule();
      this.conflictExists = false;
  }
  public void analyzeChannel() {
    Map<ScheduleTime, Map<String, Set<String>>> channelUsage = new HashMap<>();
    WarpDSL warp = new WarpDSL();
    for (InstructionTimeSlot slot : programTable){
      ScheduleTime slot = slot.getElement();

    }

    }


  }

  ProgramSchedule getChannelAnalysisTable () {
	  throw new UnsupportedOperationException("Feature incomplete.");
  }

  /**
   * Checks for program channel conflicts 
   * @return boolean indicating if conflict exists 
   */
  
  public Boolean isChannelConflict() {
    return conflictExists;
  }
}
