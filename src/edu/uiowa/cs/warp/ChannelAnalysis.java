package edu.uiowa.cs.warp;

import java.util.ArrayList;

import java.lang.StringBuilder;

import edu.uiowa.cs.warp.WarpDSL.InstructionParameters;
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
  private WarpDSL warpDSL;
/**
 * Inherits parameters from initialized program method to create constructor
 * @param warp is a warp element from WarpInterface
 */
  public ChannelAnalysis(WarpInterface warp) {
	  
	    initializeProgram(warp.toProgram(), warpDSL);
     ;
  }
 /**
  * Inherits parameters from initialized program method to create constructor
  * @param program is the program class for channel 
  */
  public ChannelAnalysis(Program program,WarpDSL warpDSL) {
      initializeProgram(program,warpDSL);
  }
  /**
   * Initializing program with program and overriding the constructor
   * @param program is the program class for channel
 * @param warpDSL 
   */
  private void initializeProgram(Program program, WarpDSL warpDSL) {
      this.program = program;
      this.programTable = program.getSchedule();
      this.conflictExists = false;
      this.warpDSL = warpDSL;
  }
  public void buildTable(WarpDSL warpDSL) {
	  String instructions = null;
	  ArrayList<InstructionParameters> parametersList = warpDSL.getInstructionParameters(instructions);
	  for (InstructionParameters params : parametersList) {
		  String flow = params.getFlow();
	        String src = params.getSrc();
	        String snk = params.getSnk();
	        String channel = params.getChannel();
	        String coordinator = params.getCoordinator();
	        String formattedData = String.format("[%s] :: \"%s\" : (\"%s\":\"%s\")", coordinator, flow, src, snk);
	        
	  }
  }
  public boolean isSameCoordinator(String coordinator, ArrayList<InstructionParameters> parametersList) {
	  for (InstructionParameters params : parametersList) {
		  if(params.getCoordinator().equals(coordinator)) {
			  return true;
		  }
		  
	  }
	  return false;
  }
  public String formatConflicts (ArrayList<InstructionParameters> parametersList) {
	  StringBuilder entry = new StringBuilder();
	  for (InstructionParameters params : parametersList) {
	        if (isSameCoordinator(params.getCoordinator(), parametersList)) {
	            if (entry.length() > 0) {
	                entry.append("; ");
	            }
	            entry.append(String.format("[%s]::%s:(%s:%s)", params.getCoordinator(), params.getFlow(), params.getSrc(), params.getSnk()));
	        }
	    }
	    return entry.toString();
	}
  
  ProgramSchedule getChannelAnalysisTable () {
	  return programTable;
  }

  /**
   * Checks for program channel conflicts 
   * @return boolean indicating if conflict exists 
   */
  
  public Boolean isChannelConflict() {
	  
    return conflictExists;
  }
}
