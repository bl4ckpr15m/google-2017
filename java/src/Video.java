import java.util.*;

/**
 * Created by dexras on 23/02/17.
 */
public class Video {
  private ArrayList<String> video;
  private ArrayList<Req> ep;
  private ArrayList<String[]>req;
  private Map<Integer,Cache> listCache= new HashMap<Integer,Cache>();

  public Video(ArrayList video, ArrayList ep, ArrayList req) {
    this.video = video;
    this.ep = ep;
    this.req = req;
  }

  public void videosACache(){
    for (String [] s: this.req){
      ArrayList<Cache> listCache = this.ep.get(Integer.parseInt(s[1])).getCache();
      if(!listCache.isEmpty()){
        boolean ananido = false;
        for (int i = 0; i < listCache.size() && !ananido; i++) {
          if(listCache.get(i).getMb() - Integer.parseInt(this.video.get(Integer.parseInt(s[0]))) >= 0){
            ananido = true;
            listCache.get(i).setMb(listCache.get(i).getMb()-Integer.parseInt(this.video.get(Integer.parseInt(s[0]))));

            listCache.get(i).getVideos().add(Integer.parseInt(s[0]));
            listCache.get(i).setUsed(true);
          }
        }
      }
    }
  }

  public int numberUsedCache(){
    for (String [] s: this.req) {
      for (Cache cache : this.ep.get(Integer.parseInt(s[1])).getCache()) {
        if (cache.isUsed()){
          try {
            this.listCache.get(cache.getId()).getVideos().addAll(cache.getVideos());
            this.listCache.get(cache.getId()).setVideos(new ArrayList<Integer>(new HashSet<>(this.listCache.get(cache.getId()).getVideos())));
          } catch (NullPointerException e) {
            this.listCache.put(cache.getId(), cache);
          }
        }
      }
    }

    return this.listCache.size();
  }

  public void getVideosInCache(){
    for (Cache cache: this.listCache.values()) {
      System.out.print(cache.getId()+" ");
      for (Integer i : cache.getVideos()){
        System.out.print(i+" ");
      }
      System.out.println();
    }
  }
}
