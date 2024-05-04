package edu.uiowa.cs.warp;

import java.util.ArrayList;
import java.lang.StringBuilder;
import edu.uiowa.cs.warp.WarpDSL.InstructionParameters;

/**
 * ChannelAnalysis.java Analyzes the warp and program channels for conflicts.
 *
 * Created by Steve Goddard Modified by Dell Harbaugh and Sixuan Liu 02/07/2024
 *
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
	public ProgramSchedule caTable;

	/**
	 * Inherits parameters from initialized program method to create constructor
	 *
	 * @param warp is a warp element from WarpInterface
	 */
	public ChannelAnalysis(WarpInterface warp) {
		initializeProgram(warp.toProgram(), warpDSL);
	}

	/**
	 * Inherits parameters from initialized program method to create constructor
	 *
	 * @param program is the program class for channel
	 */
	public ChannelAnalysis(Program program, WarpDSL warpDSL) {
		initializeProgram(program, warpDSL);
	}

	/**
	 * Initializing program with program and overriding the constructor
	 *
	 * @param program is the program class for channel
	 * @param warpDSL
	 */
	private void initializeProgram(Program program, WarpDSL warpDSL) {
		this.program = program;
		this.programTable = program.getSchedule();
		this.conflictExists = false;
		this.warpDSL = warpDSL;
		this.caTable = new ProgramSchedule(programTable.getNumRows(), programTable.getNumColumns());
	}

	/**
	 *
	 * @param warpDSL this is using the class to get parameters from Intruction
	 *                parameters method. So we needed src,snk,coordinator,flow,
	 *                channel. The method below calls src,snk,coordinator,flow,
	 *                channel from warpDSL to be used to make a the format that we
	 *                need. Then it trys to figure out if there is a conflict.
	 */
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
			// Add the formattedData to caTable
			String existingData = caTable.get(programTable.getNumRows(), programTable.getNumColumns());
			if (existingData == null) {
				existingData = "";
			}
			String newData = existingData + formattedData;
			caTable.set(programTable.getNumRows(), programTable.getNumColumns(), newData);
			StringBuilder entry = new StringBuilder();
			for (InstructionParameters params2 : parametersList) {
				if (isSameCoordinator(params2.getCoordinator(), parametersList)) {
					conflictExists = true;
					if (entry.length() > 0) {
						entry.append("; ");
					}
					entry.append(String.format("[%s]::%s:(%s:%s)", params2.getCoordinator(), params2.getFlow(),
							params2.getSrc(), params2.getSnk()));
				}
			}
			caTable.set(programTable.getNumRows(), programTable.getNumColumns(),
					caTable.get(programTable.getNumRows(), programTable.getNumColumns()) + entry.toString());
		}
	}

	/**
	 *
	 * @param coordinator    This takes the src node from the transmission
	 * @param parametersList This is taken from warpDSL. To get
	 *                       src,snk,coordinator,flow, channel.
	 * @return What the method does is check to see if the coordinators are the
	 *         same. If they are the same then we have a conflict. Which is then
	 *         used in build table.
	 */
	public boolean isSameCoordinator(String coordinator, ArrayList<InstructionParameters> parametersList) {
		for (InstructionParameters params : parametersList) {
			if (params.getCoordinator().equals(coordinator)) {
				conflictExists = true;
				return true;
			}
		}
		return false;
	}

	/**
	 * Program Schedule just returns the finished table from channel analysis using
	 * parsed information from it.
	 *
	 * @return
	 */
	ProgramSchedule getChannelAnalysisTable() {
		return caTable;

	}

	/**
	 * Checks for program channel conflicts
	 *
	 * @return boolean indicating if conflict exists
	 */
	public Boolean isChannelConflict() {
		return conflictExists;
	}
}
