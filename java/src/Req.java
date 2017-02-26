import java.util.ArrayList;

/**
 * Created by jorge on 2/23/17.
 */
public class Req {
  private int t;
  private ArrayList<Cache> cache;

  Req(String t, ArrayList cache){
    this.t = Integer.parseInt(t);
    this.cache = cache;
  }

  public ArrayList<Cache> getCache() {
    return cache;
  }
}
