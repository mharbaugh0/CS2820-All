package edu.uiowa.cs.warp;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import java.util.concurrent.TimeUnit;

class ChannelsTest {

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetChannelSet_InvalidTimeSlot() {
		Channels channels = new Channels(4, true);
		assertThrows(IndexOutOfBoundsException.class, ()-> {
			channels.getChannelSet(8);
		}, "IndexOutOfBoundsException for an invalid timeslot");
	}
	

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNumChannels_NullChannel() {
		Channels channels = new Channels (null, true);
		assertThrows(NullPointerException.class,() -> {
			channels.getNumChannels();
		}, "NullPointerException when channel is null");	
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testAddNewChannelSet_AddOneChannel() {
		Channels channels = new Channels(4, true);
		channels.addNewChannelSet();
		assertEquals(1, channels.channelsAvailable.size(), "one channel set added");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testAddNewChannelSet_AddMutipleChannels() {
		Channels channels = new Channels(4, true);
		for (int i =0; i<3; i++) {
			channels.addNewChannelSet();
		}
		assertEquals(3, channels.channelsAvailable.size(), "mutiple channel set added");
	}
	
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testAddNewChannelSet_EmptyChannel() {
		Channels channels = new Channels (4, true);
		channels.channelsAvailable=null;
		assertThrows(NullPointerException.class, () -> {
			channels.addNewChannelSet();
		}, "NullPointerException when channelsAvailable is null");
	}
	

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testIsEmpty_Empty() {
		Channels channels = new Channels(4, true);
		channels.addNewChannelSet();
		assertFalse(channels.isEmpty(0), "no channel in the channelSet");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testIsEmpty_NotEmpty() {
		Channels channels = new Channels(4, true);
		channels.addNewChannelSet();
		channels.addChannel(0, "1");
		assertFalse(channels.isEmpty(0), "non-empty channel set");
	}
	
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testIsEmpty_InvalidTimeSlot() {
		Channels channels = new Channels(4, true);
		assertThrows(IndexOutOfBoundsException.class,()->{
			channels.isEmpty(5);
		}, "IndexOutOfBoundsException when checking invalid empty status for invalid time slot 1");
	}

	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testRemoveChannel_RemoveExistChannel() {
		Channels channels= new Channels(4, true);
		channels.addNewChannelSet();
		channels.addChannel(0, "1");
		assertTrue(channels.removeChannel(0, "1"), "remove of channel 1 from timeslot 0");
		
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testRemoveChannel_RemoveNonexistChannel() {
		Channels channels= new Channels(4, true);
		channels.addNewChannelSet();
		assertTrue(channels.removeChannel(0, "1"), "remove a nonexisting channel");
		
	}
	
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testRemoveChannel_InvalidTimeSlot() {
		Channels channels = new Channels(4,true);
		assertThrows(IndexOutOfBoundsException.class,()->{
			channels.removeChannel(5,"1");
		}, "remove channel from an invalid time slot");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testAddChannel_AddOneChannel() {
		Channels channels= new Channels (4, true);
		channels.addNewChannelSet();
		channels.addChannel(0, "1");
		assertFalse(channels.addChannel(0, "1"), "add channel 1 to timeslot 0");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testAddChannel_NullChannelSet() {
		Channels channels = new Channels(4,true);
		assertThrows(IndexOutOfBoundsException.class,()->{
			channels.addChannel(0, "1");
		}, "IndexOutOfBound when add channel with null ChannelSet.");
		
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testAddChannel_InvalidTimeSlot() {
		Channels channels = new Channels(4,true);
		assertThrows(IndexOutOfBoundsException.class,()->{
			channels.addChannel(5, "1");
		}, "IndexOutOfBoundsException for invalid time slot");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNumChannels() {
		Channels channels= new Channels(4, true);
		assertEquals(4, channels.getNumChannels().intValue(), "the number of channels to be equal to 4");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNumChannels_NegetiveChannels() {
		Channels channels = new Channels (-4, true);
		
		Throwable thrown= assertThrows(IllegalArgumentException.class,() -> {
			channels.getNumChannels();
		});	
		String expectedMessage= "IllegalArgumentException for negetive number of channels";
		String actualMessage = thrown.getMessage();
		assertEquals(expectedMessage, actualMessage, "Exception message mismatch");
	}
	
	@Test
	@Timeout(value = 2000, unit = TimeUnit.MILLISECONDS)
	void testGetNumChannels_NullChannel1() {
		Channels channels = new Channels (null, true);
		assertThrows(NullPointerException.class,() -> {
			channels.getNumChannels();
		}, "NullPointerException when channel is null");	
		
	}
	

}
