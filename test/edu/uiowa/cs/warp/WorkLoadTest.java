package edu.uiowa.cs.warp;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

class WorkLoadTest {
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNodeNamesOrderedAlphabetically() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "Test1.txt");
		String[] expectedNodeName = {"[C1, B1]"};
		String[] actualNodeName = workload.getNodeNamesOrderedAlphabetically();
		assertNotEquals(expectedNodeName, actualNodeName, "expected is not alphabetically sorted");
	}
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNodeNamesOrderedAlphabetically_NoNodes() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "Example.txt");
		String[] expectedNodeName = {"A, B, C"};
		String[] actualNodeName = workload.getNodeNamesOrderedAlphabetically();
		assertEquals(expectedNodeName, actualNodeName, "expected is alphabetically sorted");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNodeNamesOrderedAlphabetically_InvalidInputFileName() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest12.txt");
		assertThrows(NullPointerException.class,() -> {
			workload.getNodeNamesOrderedAlphabetically();
		}, "NullPointerException when there is no inputfile");
	}
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetFlowDeadline_GetF1Deadline() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		Integer expectedDeadline = 20;
		Integer actualDeadline = workload.getFlowDeadline("F1");
		assertEquals(expectedDeadline, actualDeadline, "the deadline of F1 is 20");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetFlowDeadline_EmptyFlowName() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		Integer actualDeadline = workload.getFlowDeadline("");
		assertNotNull(actualDeadline, "Deadline is null for non-existing flow, returns 100");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetFlowDeadline_InvalidFlowName() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		Integer actualDeadline= workload.getFlowDeadline("NonExistingFlow");
		assertNotNull(actualDeadline, "returns 100 when flow name is invalid" );
	}

	
	
	
	
}
