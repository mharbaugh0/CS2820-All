package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;


import org.junit.jupiter.api.Test;

class ChannelVisualizationTest {
	  private static final String SOURCE_SUFFIX = ".ch";
	  private static final String OBJECT_NAME = "Channel Analysis";
	  private ChannelAnalysis ca;
	  private ProgramSchedule sourceCode;
	  private Program program;
	  private Boolean deadlinesMet;
	  private ProgramSchedule schedule;
	  private WarpInterface warp;
		
	  

	ChannelVisualization channelVisualization = new ChannelVisualization(warp); 
	ProgramVisualization programVisualization = new ProgramVisualization(warp);

	ChannelVisualizationTest(WarpInterface warp) {
		super();
		this.warp = warp;
		this.ca = warp.toChannelAnalysis();
		this.deadlinesMet = warp.deadlinesMet();
		this.program = warp.toProgram();
		this.schedule = program.getSchedule();
	}

    
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testDisplayVisualization() {
		assertNotNull(channelVisualization.displayVisualization());
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testDisplayVisualization2() {
		assertNotSame(channelVisualization.displayVisualization(), programVisualization.displayVisualization());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateHeader() {
		assertNotNull(channelVisualization.createHeader());
	}
	
    @Test
    public void testCreateHeaderSchedulerName() {
        var header = channelVisualization.createHeader();
        assertTrue(header.toString().contains("Scheduler Name:"));
    }
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateHeaderAndFooter() {
		assertNotSame(channelVisualization.createHeader(),channelVisualization.createFooter());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateFooter() {
		assertNotNull(channelVisualization.createFooter());
	}
	
    @Test
    public void testCreateFooterMessage() {
        Description footer = channelVisualization.createFooter();
        assertTrue(footer.toString().contains("All flows meet their deadlines") || 
                   footer.toString().contains("WARNING: NOT all flows meet their deadlines"));
    }

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateColumnHeader() {
		assertNotNull(channelVisualization.createColumnHeader());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateVisualizationData() {
		assertNotNull(channelVisualization.createVisualizationData());
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateVisualizationData2() {
		assertNotSame(channelVisualization.createVisualizationData(), programVisualization.createVisualizationData());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	public void testCreateTitle() {
		assertNotNull(channelVisualization.createTitle());
	}
	
    @Test
    public void testCreateTitle2() {
        String title = channelVisualization.createTitle();
        assertTrue(title.contains("Channel Analysis"));
    }
}

