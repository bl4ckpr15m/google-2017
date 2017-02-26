import java.util.ArrayList;

/**
 * Created by jorge on 2/23/17.
 */
public class Cache {
  private int id;
  private int t;
  private int mb;
  private boolean used;
  private ArrayList<Integer> videos;

  Cache(int id, int t, int mb) {
    this(id, t, mb, false);
  }

  public Cache(int id, int t, int mb, boolean used) {
    this.id = id;
    this.t = t;
    this.mb = mb;
    this.used = used;
    this.videos = new ArrayList();
  }

  public boolean isUsed() {
    return used;
  }

  public void setUsed(boolean used) {
    this.used = used;
  }

  public ArrayList<Integer> getVideos() {
    return videos;
  }

  public void setVideos(ArrayList<Integer> videos) {
    this.videos = videos;
  }

  public int getId() {
    return id;
  }

  public int getMb() {
    return mb;
  }

  public void setMb(int mb) {
    this.mb = mb;
  }
}
