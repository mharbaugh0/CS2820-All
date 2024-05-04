package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import argparser.*;
import edu.uiowa.cs.warp.SystemAttributes.ScheduleChoices;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

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
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void checkChannelAnalysis(ChannelAnalysis channelAnalysis){
		assertNotNull(channelAnalysis);
		assertInstanceOf(Boolean.class,channelAnalysis.isChannelConflict(), "");
	}
}
