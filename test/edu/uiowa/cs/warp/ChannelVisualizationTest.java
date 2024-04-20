package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;


import org.junit.jupiter.api.Test;

class ChannelVisualizationTest {
    private ChannelVisualization channelVisualization;
    private WarpInterface warp;

    
    @Before
    public void setUp() {
        channelVisualization = new ChannelVisualization(warp);
    }
    
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testDisplayVisualization() {
		assertNotNull(channelVisualization.displayVisualization());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testCreateHeader() {
		assertNotNull(channelVisualization.createHeader());
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testCreateHeaderAndFooter() {
		assertNotSame(channelVisualization.createHeader(),channelVisualization.createFooter());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testCreateFooter() {
		assertNotNull(channelVisualization.createFooter());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testCreateColumnHeader() {
		assertNotNull(channelVisualization.createColumnHeader());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testCreateVisualizationData() {
		assertNotNull(channelVisualization.createVisualizationData());
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testCreateTitle() {
		assertNotNull(channelVisualization.createTitle());
	}

}
