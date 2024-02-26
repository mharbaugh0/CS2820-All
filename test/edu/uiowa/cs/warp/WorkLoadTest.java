package edu.uiowa.cs.warp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

class WorkLoadTest {
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNodeNamesOrderedAlphabetically() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		String[] expectedNodeName = {"F1", "F4", "AF5", "F6"};
		String[] actualNodeName = workload.getNodeNamesOrderedAlphabetically();
		assertArrayEquals(expectedNodeName, actualNodeName, "sort flow name alphabetically");
	}
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNodeNamesOrderedAlphabetically_NoNodes() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		assertThrows(NullPointerException.class,() -> {
			workload.getNodeNamesOrderedAlphabetically();
		}, "NullPointerException when there is no node");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNodeNamesOrderedAlphabetically_NoInputFileName() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, null);
		assertThrows(NullPointerException.class,() -> {
			workload.getNodeNamesOrderedAlphabetically();
		}, "NullPointerException when there is no inputfile");
	}
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetFlowDeadline() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		Integer expectedDeadline = 20;
		Integer actualDeadline = workload.getFlowDeadline("F1");
		assertEquals(expectedDeadline, actualDeadline, "flow deadline should match the expected value");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetFlowDeadline_NotExistingFlow() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		Integer actualDeadline = workload.getFlowDeadline("");
		assertNull(actualDeadline, "Deadline is null for non-existing flow");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetFlowDeadline_EmptyFlowName() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		Integer actualDeadline = workload.getFlowDeadline("");
		assertNull(actualDeadline, "");
	}


	
	
	
	
	
}
