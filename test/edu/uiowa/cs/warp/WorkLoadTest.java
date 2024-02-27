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
		String[] expectedNodeName= {"A","B","C"};
		String[] actualNodeName = workload.getNodeNamesOrderedAlphabetically();
		assertArrayEquals(expectedNodeName, actualNodeName, "expected is alphabetically sorted");
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

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	void testGetFlowNamesInPriorityOrder() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		ArrayList<String> flowNamesInOriginalOrder = new ArrayList<>();
		flowNamesInOriginalOrder = workload.getFlowNamesInOriginalOrder();

		workload.setFlowNamesInPriorityOrder(flowNamesInOriginalOrder);
		ArrayList<String> arr2 = workload.getFlowNamesInPriorityOrder();
		int arraySize = arr2.size();

		assertEquals(15, arraySize);
		//fails if array is not the same size before and after reordering
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	void testGetMinPeriod() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		int testMin = workload.getMinPeriod();
		assertFalse(testMin < 0);
		assertFalse(testMin > 1000);
		//fails if min period is negative or more than 1000
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	void testSetFlowNamesInOriginalOrder() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		ArrayList<String> flowNamesInOriginalOrder = new ArrayList<>();
		flowNamesInOriginalOrder = workload.getFlowNamesInOriginalOrder();

		workload.setFlowsInDMorder();
		String [] z1 = workload.getFlowNames();
		workload.setFlowNamesInOriginalOrder(flowNamesInOriginalOrder);
		String [] z2 = workload.getFlowNames();
		assertFalse(z1.length != z2.length);
		//fails if length of same set of flows sorted two different ways is unequal
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	void testGetNodeChannel() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		Integer testChannel = workload.getNodeChannel("A");
		assertFalse(testChannel < 0);
		//fails if getNodeChannel returns a negative channel
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	void testSetFlowsInPriorityOrder() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		workload.setFlowsInDMorder();
		String [] w1 = workload.getFlowNames();
		workload.setFlowsInPriorityOrder();
		String [] w2 = workload.getFlowNames();
		assertFalse(w1.length != w2.length);
		//fails if length of same set of flows sorted two different ways is unequal
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	void testSetFlowsInDMorder() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		workload.setFlowsInDMorder();
		String [] x1 = workload.getFlowNames();
		workload.setFlowsInRMorder();
		String [] x2 = workload.getFlowNames();
		assertFalse(x1.length != x2.length);
		//fails if length of same set of flows sorted two different ways is unequal
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	void testGetNodesInFlow() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "Example.txt");
		String [] nodeList = workload.getNodesInFlow("F1");
		assertEquals(3, nodeList.length);
		//fails if F1 nodes are not accurately represented
	}

	@Test
	@Timeout(value = 5000, unit = TimeUnit.MILLISECONDS)
	void testMaxFlowLength() {
		WorkLoad workload = new WorkLoad(1, 0.9, 0.99, "StressTest.txt");
		int testMax = workload.maxFlowLength();
		assertFalse(testMax < 0);
		assertFalse(testMax > 1000);
		//fails if max flow length is negative or greater than 1000
	}
	
	
	
}
