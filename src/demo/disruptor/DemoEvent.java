package demo.disruptor;

import java.util.Date;
import java.util.UUID;

import com.lmax.disruptor.EventFactory;

public class DemoEvent {

  private UUID uuid;
  private String name;
  private Date timestamp;
  private long procId = 0L;    // dummy id to make it easier to watch in the RingBuffer

  public String toString() {
    return String.format("DemoEvent:\n  UUID: %s\n  Name: %s\n  Process Id: %d\n  Timestamp: %s",
                         (uuid == null ? "null" : uuid.toString()), name, procId,
                         (timestamp == null ? "null" : timestamp.toString()));
  }

  public DemoEvent() {}

  public DemoEvent(final UUID uuid, final String name, final long procId) {
    this(uuid, name, procId, new Date());
  }

  public DemoEvent(final UUID uuid, final String name, final long procId, final Date tstamp) {
    this.uuid = uuid;
    this.name = name;
    this.procId = procId;
    this.timestamp = new Date(tstamp.getTime());
  }

  public DemoEvent(DemoEvent ev) {
    this.uuid = ev.uuid;
    this.name = ev.name;
    this.procId = procId;
    if (ev.timestamp == null) {
      this.timestamp = new Date();
    } else {
      this.timestamp = new Date(ev.timestamp.getTime());
    }
  }

  public void copy(DemoEvent ev) {
    this.uuid = ev.uuid;
    this.name = ev.name;
    this.procId = ev.procId;
    if (ev.timestamp == null) {
      this.timestamp = new Date();
    } else {
      this.timestamp = new Date(ev.timestamp.getTime());
    }
  }

  /* ---[ GETTERS and SETTERS ]--- */

  public UUID getUUID() { return uuid; }
  public String getName() { return name; }
  public Date getTimestamp() {
    if (timestamp == null) return timestamp;
    else return new Date(timestamp.getTime());
  }
  public long getProcessId() { return procId; }
  
  public void setUUID(UUID uuid) {
    this.uuid = uuid;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDate(Date tstamp) {
    this.timestamp = new Date(tstamp.getTime());
  }

  public void setProcessId(long procId) {
    this.procId = procId;
  }


  /* ---[ Static EventFactory ]--- */
  public static final EventFactory<DemoEvent> FACTORY = new EventFactory<DemoEvent>() {
    /**
     * Since only intended to be used to populate the RingBuffers 
     * with Dummy empty events, return dummy empty events from here
     */
    public DemoEvent newInstance() {
      return new DemoEvent();
    }
  };
}
