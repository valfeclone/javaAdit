import java.util.*;
import java.util.concurrent.TimeUnit;
import java.security.*;

public class GraphTask{
    public static void main(String[] args) throws NoSuchAlgorithmException,NoSuchProviderException{
        System.gc();
        Graph foo;
        SecureRandom randomEdge=SecureRandom.getInstance("SHA1PRNG","SUN");
        long start,end;
        Scanner input1=new Scanner(System.in);
        int vertex=input1.nextInt();
        Scanner input2=new Scanner(System.in);
        long edge=input2.nextLong();
        foo=new Graph(vertex);
        for(long length=0;length<edge;length++){
            int row=randomEdge.nextInt(999999999)%vertex;
            int col=randomEdge.nextInt(999999999)%vertex;
            if(row==col){
                row=randomEdge.nextInt(999999999)%vertex;
                col=randomEdge.nextInt(999999999)%vertex;
            }
            foo.addEdge(row,col);
        }
        System.gc();
        start=System.nanoTime();
        foo.floydWarshall();
        end=System.nanoTime();
        System.out.println("Floyd-Warshall: "+TimeUnit.NANOSECONDS.toMillis(end-start)+" ms");
        System.gc();
        start=System.nanoTime();
        foo.dijkstra();
        end=System.nanoTime();
        System.out.println("Dijkstra      : "+TimeUnit.NANOSECONDS.toMillis(end-start)+" ms");
        input1.close();
        input2.close();
    }
}

class Graph extends GraphTask{
    private int[][] adjGraph;
    private SecureRandom randomer=new SecureRandom();
    private int length;

    public Graph(int length){
        this.length=length;
        adjGraph=new int[length][length];
        for(int row=0;row<length;row++){
            for(int col=0;col<length;col++){
                if(row!=col){
                    adjGraph[row][col]=10000000;
                }
            }
        }
    }

    public void checkArray(){
        for(int row=0;row<length;row++){
            for(int col=0;col<length;col++){
                System.out.print(adjGraph[row][col]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public void addEdge(int row,int col){
        int range=randomer.nextInt(21);
        if(range==0){
            range=randomer.nextInt(21);
        }
        adjGraph[row][col]=range;
        adjGraph[col][row]=range;
    }

    public void floydWarshall(){
        int[][] temp=new int[length][length];
        for(int row=0;row<length;row++){
            for(int col=0;col<length;col++){
                temp[row][col]=adjGraph[row][col];
            }
        }
        for(int row=0;row<length;row++){
            for(int col=0;col<length;col++){
                for(int end=0;end<length;end++){
                    if(temp[row][col]+temp[col][end]<temp[row][end]){
                        temp[row][end]=temp[row][col]+temp[col][end];
                    }
                }
            }
        }
        /*for(int row=0;row<length;row++){
            for(int col=0;col<length;col++){
                System.out.print(temp[row][col]+" ");
            }
            System.out.println();
        }*/
    }

    private int minDist(int[] result,boolean[] visit){
        int minValue=10000000;
        int minIndex=-1;
        for(int counter=0;counter<length;counter++){
            if(!visit[counter] && result[counter]<=minValue){
                minValue=result[counter];
                minIndex=counter;
            }
        }
        return minIndex;
    }

    public void dijkstra(){
        int[] store=new int[length];
        int[][] temp=new int[length][length];
        boolean[] isVisited=new boolean[length];
        for(int index=0;index<length;index++){
            int x=0;
            for(int counter=0;counter<length;counter++){
                store[counter]=10000000;
                isVisited[counter]=false;
            }
            store[index]=0;
            for(int counter=0;counter<length;counter++){
                int row=minDist(store,isVisited);
                isVisited[row]=true;
                for(int col=0;col<length;col++){
                    if(store[row]+adjGraph[row][col]<store[col] && store[row]!=10000000 && !isVisited[col] && adjGraph[row][col]!=0){
                        store[col]=store[row]+adjGraph[row][col];
                    }
                }
            }
            for(int e: store){
                temp[index][x++]=e;
            }
        }
        /*for(int row=0;row<length;row++){
            //System.out.print(store[row]+" ");
            for(int col=0;col<length;col++){
                System.out.print(temp[row][col]+" ");
            }
            System.out.println();
        }*/
    }
}