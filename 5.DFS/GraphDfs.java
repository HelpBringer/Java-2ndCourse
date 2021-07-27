import java.io.*;
import java.util.*;

class GraphDfs implements Runnable{
    private int V; // No. of vertices
    private static int[] vertices;
    private static int n;
    private static int counter=1;
    private LinkedList<Integer>[] adj;

    // Constructor
    GraphDfs(int v)
    {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }
    GraphDfs()
    {
        V = -1;
        adj = null;
    }
    void addEdge(int v, int w)
    {
        adj[v].add(w);
    }

    void DFSUtil(int v, boolean[] visited)
    {
        visited[v] = true;
        vertices[v]=counter;
        counter++;
        for (int n : adj[v]) {
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    void DFS(int v)
    {

        boolean[] visited = new boolean[n];
        vertices= new int[n];
        DFSUtil(v, visited);
    }

    public void run() {
        try {
            File file = new File("input.txt");
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            //n
            String line = reader.readLine();
            n=Integer.parseInt(line);
            GraphDfs g = new GraphDfs(n);
            //matrix
            for(int i=0; i<n;i++){
                line = reader.readLine();
                for (int j=0; j<n;j++){
                    if(line.charAt(2*j)=='1'){
                        g.addEdge(i+1, j+1);
                    }
                }
            }
            g.DFS(0);
            String write="";
            StringBuilder builder= new StringBuilder(write);
            for(int i=0; i<n;i++){
                if(Integer.toString(vertices[i]).equals("0")){
                    builder.append(counter);
                    counter++;
                }
                else {
                    builder.append(Integer.toString(vertices[i]));
                }
            }
            write=builder.toString();
            StringTokenizer out=new StringTokenizer(write);
            FileWriter writer = new FileWriter("output.txt", false);
            // запись всей строки
            for(int i=0;i<n;i++) {
                writer.write(write.charAt(i));
                writer.write(" ");
            }
            writer.append('\n');
            writer.flush();

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String args[])
    {
        new Thread(null, new GraphDfs(),"",64*1024*1024).start();

    }
}
