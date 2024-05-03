package edu.uiowa.cs.warp;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ChannelVisualizationTest {

	private static final Double MIN_LQ = 0.9; // default minimum Link Quality in system (command line
	// option)
	private static final Double E2E = 0.99; // default end-to-end reliability for all flows (command
	// line option)
	private static final Integer DEFAULT_FAULTS_TOLERATED = 0;



	private WorkLoad initWorkLoad(){
		Integer numFaults = 1;
		Double e2e =  0.9;
		Double minLQ =  0.9;
		String inputFile = "ExampleX.txt";
		return new WorkLoad(numFaults, minLQ, e2e, inputFile);
	}


	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void testCreateChannelVisualization(){
		Integer nChannels = 16;
		for (SystemAttributes.ScheduleChoices choice : SystemAttributes.ScheduleChoices.values()) {
			WarpInterface wrap = new WarpSystem(initWorkLoad(), nChannels, choice);
			ChannelVisualization channelVisualization = new ChannelVisualization(wrap);
			assertNotNull(channelVisualization);
		}
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void testDisplayVisualization(){
		Integer nChannels = 16;
		for (SystemAttributes.ScheduleChoices choice : SystemAttributes.ScheduleChoices.values()) {
			WarpInterface wrap = new WarpSystem(initWorkLoad(), nChannels, choice);
			ChannelVisualization channelVisualization = new ChannelVisualization(wrap);
			GuiVisualization guiVisualization = channelVisualization.displayVisualization();
			assertNotNull(guiVisualization);
		}
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void testCreateHeader(){
		Integer nChannels = 16;
		for (SystemAttributes.ScheduleChoices choice : SystemAttributes.ScheduleChoices.values()) {
			WarpInterface wrap = new WarpSystem(initWorkLoad(), nChannels, choice);
			ChannelVisualization channelVisualization = new ChannelVisualization(wrap);
			Description headDescription = channelVisualization.createHeader();
			assertNotNull(headDescription);
		}
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void testCreateFooter(){
		Integer nChannels = 16;
		for (SystemAttributes.ScheduleChoices choice : SystemAttributes.ScheduleChoices.values()) {
			WarpInterface wrap = new WarpSystem(initWorkLoad(), nChannels, choice);
			ChannelVisualization channelVisualization = new ChannelVisualization(wrap);
			Description footDescription = channelVisualization.createFooter();
			assertNotNull(footDescription);
		}
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void testCreateColumnHeader(){
		Integer nChannels = 16;
		for (SystemAttributes.ScheduleChoices choice : SystemAttributes.ScheduleChoices.values()) {
			WarpInterface wrap = new WarpSystem(initWorkLoad(), nChannels, choice);
			ChannelVisualization channelVisualization = new ChannelVisualization(wrap);
			String[] columnHeaders = channelVisualization.createColumnHeader();
			assert columnHeaders.length > 0;
		}
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	public void testCreateVisualizationData(){
		Integer nChannels = 16;
		for (SystemAttributes.ScheduleChoices choice : SystemAttributes.ScheduleChoices.values()) {
			WarpInterface wrap = new WarpSystem(initWorkLoad(), nChannels, choice);
			ChannelVisualization channelVisualization = new ChannelVisualization(wrap);
			String[][] visualizationData = channelVisualization.createVisualizationData();
			assert visualizationData.length > 0;

			for(int i = 0; i < visualizationData.length; i++){
				assert visualizationData[i].length > 0;
			}

		}
	}





}

