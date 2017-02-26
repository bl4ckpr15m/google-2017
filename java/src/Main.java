import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Main main = new Main();
    main.readFile();
  }

  public void readFile(){
//    File file = new File("me_at_the_zoo.in");
    File file = new File("../in/kittens.in");
//    File file = new File("example.in");

    try {
      Scanner sc = new Scanner(file);
      ArrayList <Req>rq = new ArrayList<Req>();
      ArrayList video = null;
      ArrayList <String[]>ep = new ArrayList<String[]>();
      int fin_ep = 0;
      int mb = 0;
      int end = 0;
      int l = 0;

      while (sc.hasNextLine()) {
        String i = sc.nextLine();
        if(l == 0){
          String [] l1 = i.split(" ");
          fin_ep = Integer.parseInt(l1[1]);
          mb = Integer.parseInt(l1[l1.length-1]);
        } else if(l == 1){
          video = new ArrayList<>(
                  Arrays.asList(i.split(" ")));
        } else {
          String [] aux = i.split(" ");
          if(aux.length == 2 && end < fin_ep){
            end++;
            ArrayList <Cache>cache_aux = new ArrayList();
            for(int j = 0; j < Integer.parseInt(aux[1]); j++){
              String single_cache [] = sc.nextLine().split(" ");
              Cache ch = new Cache(Integer.parseInt(single_cache[0]), Integer.parseInt(single_cache[1]), mb);
              cache_aux.add(ch);
            }
            Req req = new Req(aux[0], cache_aux);
            rq.add(req);
          } else{
            ep.add(i.split(" "));
          }

        }
        l++;
      }
      sc.close();

      Video v = new Video(video,rq,ep);
      v.videosACache();
      System.out.println(v.numberUsedCache());
      v.getVideosInCache();
    }
    catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  void salida(ArrayList<ArrayList<Integer>> v) {
    try{
      PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
      writer.println("The first line");

      for(int i = 0; i < v.size(); i++){
        for(int k = 0; k < v.get(i).size(); k++){
          writer.print(v.get(i).get(k));
        }
        writer.print("\n");
      }
      writer.close();

    } catch (IOException e) {
      // do something
    }


  }
}
