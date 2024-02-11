package edu.uiowa.cs.warp;


import java.util.ArrayList;
import java.util.HashSet;

/**
 * This class represents a set of channels available for communication at different time slots.
 * It provides methods to manage and manipulate these channels.
 * @author sgoddard
 * @version 1.4
 *
 */
public class Channels {
	/**
	 * A set of channels available in a specific time slot.
	 */

  private class ChannelSet extends HashSet<String> {
    private static final long serialVersionUID = 6725256944325470867L;

    // default constructor
    private ChannelSet() {
      super();
    }
    /**
     * Constructs a ChannelSet with a specific number of channels.
     * @param nChannels The number of channels to include in the set.
     */
    private ChannelSet(Integer nChannels) {
      super();
      for (int i = 0; i < nChannels; i++) { // ASSUMES channels range from 0 to nChannels-1
        this.add(String.valueOf(i));
      }
    }
  }


  Integer nChannels; // size of the full set of channels
  Boolean verbose;
  ArrayList<ChannelSet> channelsAvailable; // ArrayList to hold channels available in each time slot

  /**
   * Constructs a Channels object with a specific number of channels and verbosity setting.
   * @param nChannels The number of channels available.
   * @param verbose Indicates if verbose logging is enabled or not.
   */
  Channels(Integer nChannels, Boolean verbose) {
    this.nChannels = nChannels;
    this.verbose = verbose;
    this.channelsAvailable = new ArrayList<ChannelSet>();
  }
  /**
   * return the set of channels available at a specific time slot.
   * @param timeSlot The time slot 
   * @return A HashSet containing the channels available at the timeslot.
   */
  public HashSet<String> getChannelSet(Integer timeSlot) {
    /* get the channel set for this timeSlot */
    HashSet<String> channelSet = new HashSet<String>(channelsAvailable.get(timeSlot));
    return channelSet;
  }
  /**
   * adds a new empty channel set for a new time slot.
   */

  public void addNewChannelSet() {
    var channels = new ChannelSet(nChannels);
    channelsAvailable.add(channels);
  }

  /**
   * Checks if the channel set at a specific time slot is empty.
   * @param timeSlot The time slot to check.
   * @return True if the channel at the specific time slot is empty, false otherwise.
   */
  public Boolean isEmpty(int timeSlot) {
    ChannelSet channelSet = channelsAvailable.get(timeSlot); // get the channel set for this
                                                             // timeSlot
    return channelSet.isEmpty(); // returns true channel set is empty and false if not
  }
  /**
   * Removes a channel from the set of available channels at a specific time slot.
   * 
   * @param timeSlot The time slot
   * @param channel The channel to be remove.
   * @return True if channel is remove and false otherwise.
   */

  public Boolean removeChannel(int timeSlot, String channel) {
    Boolean result;
    ChannelSet channels = channelsAvailable.get(timeSlot);
    result = channels.remove(channel);
    return result;
  }
  /**
   * Adds a channel to the channel set of a specific time slot.
   * 
   * @param timeSlot The time slot to add the channel.
   * @param channel The channel to add.
   * @return True if the channel was successfully added, false otherwise.
   */
  public Boolean addChannel(int timeSlot, String channel) {
    Boolean result;
    ChannelSet channels = channelsAvailable.get(timeSlot); // get a pointer to the channel set
    result = channels.add(channel); // remove the channel, returning the result
    return result;
  }
  
  /**
   * Retrieves the total number of channels available.
   * 
   * @return The number of channels.
   */
  public Integer getNumChannels() {
    return nChannels;
  }

}
