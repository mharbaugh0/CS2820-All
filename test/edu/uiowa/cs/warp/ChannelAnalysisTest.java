package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import argparser.*;
import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;
import edu.uiowa.cs.warp.WarpDSL.InstructionParameters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

class ChannelAnalysisTest {

	Integer numFaults = 1;
	Double e2e =  0.9;
	Double minLQ =  0.9;
	String inputFile = "ExampleX.txt";
	Integer nChannels = 16;
	ScheduleChoices choice;
    Program program;
    WarpDSL warpDSL;
    ProgramSchedule programSchedule;


	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateChannelAnalysisObject() {
		WorkLoad workLoad = new WorkLoad(numFaults, minLQ, e2e, inputFile);
		for (SystemAttributes.ScheduleChoices choice : SystemAttributes.ScheduleChoices.values()) {
			WarpInterface warp = new WarpSystem(workLoad, nChannels, choice);
			ChannelAnalysis channelAnalysis = new ChannelAnalysis(warp);
			checkChannelAnalysis(channelAnalysis);
		}
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateChannelAnalysisObject2() {
		WorkLoad workLoad = new WorkLoad(numFaults, minLQ, e2e, inputFile);
		for (SystemAttributes.ScheduleChoices choice : SystemAttributes.ScheduleChoices.values()) {
			WarpInterface warp = new WarpSystem(workLoad, nChannels, choice);
			ChannelAnalysis channelAnalysis = new ChannelAnalysis(warp);
			checkChannelAnalysis(channelAnalysis);
		}
	}
	
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testGetChannelAnalysisTable() {
        ChannelAnalysis ca = new ChannelAnalysis(program, warpDSL);
        var temp = ca.getChannelAnalysisTable();
		assertNotNull(temp);
	}
	
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void testBuildTable() {
        ChannelAnalysis ca = new ChannelAnalysis(program, warpDSL);
        ca.buildTable(warpDSL);
        ProgramSchedule caTable = ca.getChannelAnalysisTable();
        String data = caTable.get(0, 0);
        assertNotNull(data, "ChannelAnalysis table is empty");
    }
    
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void testBuildTableRows() {
        ChannelAnalysis ca = new ChannelAnalysis(program, warpDSL);
        ca.buildTable(warpDSL);
        ProgramSchedule caTable = ca.getChannelAnalysisTable();
        ProgramSchedule programTable = program.getSchedule();
        var temp1 = programTable.getNumRows();
        var temp2 = caTable.getNumRows();
        assertEquals(temp1, temp2);
    }
    
    @Test
    @Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
    public void testBuildTableCols() {
        ChannelAnalysis ca = new ChannelAnalysis(program, warpDSL);
        ca.buildTable(warpDSL);
        ProgramSchedule caTable = ca.getChannelAnalysisTable();
        ProgramSchedule programTable = program.getSchedule();
        var temp1A = programTable.getNumColumns();
        var temp2A = caTable.getNumColumns();
        assertEquals(temp1A, temp2A);
    }
    
    @Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
    public void testBuildEmptyTable() {
        WarpDSL emptyWarpDSL = new WarpDSL();
        ChannelAnalysis ca = new ChannelAnalysis(program, emptyWarpDSL);
        ca.buildTable(emptyWarpDSL);
        assertFalse(ca.isChannelConflict());
    }
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void checkChannelAnalysis(ChannelAnalysis channelAnalysis){
		assertNotNull(channelAnalysis);
		assertInstanceOf(Boolean.class,channelAnalysis.isChannelConflict(), "");
		assertInstanceOf(ProgramSchedule.class,channelAnalysis.getChannelAnalysisTable(), "");
		assertInstanceOf(Boolean.class,channelAnalysis.isSameCoordinator("", null));

	}
}
