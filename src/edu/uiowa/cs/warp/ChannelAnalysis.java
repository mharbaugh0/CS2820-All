package edu.uiowa.cs.warp;

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
public class ChannelAnalysis {

  private Program program;
  private ProgramSchedule programTable;
  private Boolean conflictExists;
  
/**
 * Invokes current program to warp and programTable 
 * with schedule from program.
 * 
 * @param warp is a warp element from WarpInterface
 */
  ChannelAnalysis(WarpInterface warp) {
    this.program = warp.toProgram();
    this.programTable = program.getSchedule();
    conflictExists = false;
  }
 /**
  * Invokes current program with program and programTable
  * with schedule method from program.
  * @param program is the program class for channel 
  */
  ChannelAnalysis(Program program) {
    this.program = program;
    this.programTable = program.getSchedule();
    conflictExists = false;
  }

  ProgramSchedule getChannelAnalysisTable () {
    throw NotImplementedException;
  }

  /**
   * Checks for program channel conflicts 
   * @return boolean indicating if conflict exists 
   */
  public Boolean isChannelConflict() {
    return conflictExists;
  }
}
