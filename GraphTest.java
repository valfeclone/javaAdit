import java.util.*;

public class GraphTest{
    public static void main(String[] args){
        Graphs fooGraph= new Graphs(7);
        fooGraph.addVertex("JOG");
        fooGraph.addVertex("SUB");
        fooGraph.addVertex("CGK");
        fooGraph.addVertex("MKS");
        fooGraph.addVertex("DPS");
        fooGraph.addEdge("JOG","SUB");
        fooGraph.addEdge("JOG","CGK");
        fooGraph.addEdge("SUB","CGK");
        fooGraph.addEdge("SUB","MKS");
        fooGraph.addEdge("CGK","MKS");
        fooGraph.addEdge("CGK","DPS");
        fooGraph.showVertex();
        fooGraph.showEdges();
        fooGraph.traverseBFS("MKS");
        fooGraph.resetVisit();
        System.out.println();
        fooGraph.traverseDFS("MKS");
        fooGraph.resetVisit();
        System.out.println();
    }
}

class Graphs extends GraphTest{
    private String[] inputVertex;
    private boolean[] visitStatus;
    private int[][] adjGraph;
    private Queue<String> bfs=new LinkedList<String>();
    private Stack<String> dfs=new Stack<String>();
    private int length;
    private int counter=0;

    public Graphs(int length){
        this.length=length;
        inputVertex=new String[length];
        visitStatus=new boolean[length];
        adjGraph=new int[length][length];
    }

    public void checkArray(){
        for(String e: inputVertex){
            System.out.print(e+" ");
        }
        System.out.println();
        for(boolean e: visitStatus){
            System.out.print(e+" ");
        }
        System.out.println();
        for(int row=0;row<length;row++){
            for(int col=0;col<length;col++){
                System.out.print(adjGraph[row][col]);
            }
            System.out.println();
        }
    }

    private int getIndex(String query){
        int index=0;
        while(index<counter){
            if(inputVertex[index]==query){
                break;
            }
            ++index;
        }
        return index;
    }

    private String getNode(int query){
        String node=inputVertex[query];
        return node;
    }

    public void resetVisit(){
        int index=0;
        while(index<counter){
            visitStatus[index]=false;
            ++index;
        }
    }

    private boolean isAllVisited(){
        boolean stats=true;
        int index=0;
        while(index<counter){
            if(!visitStatus[index++]){
                stats=false;
                break;
            }
        }
        return stats;
    }

    public void addVertex(String vertex){
        inputVertex[counter++]=vertex;
    }

    public void addEdge(String node1,String node2){
        int index1=getIndex(node1);
        int index2=getIndex(node2);
        adjGraph[index1][index2]=1;
        adjGraph[index2][index1]=1;
    }

    public void showVertex(){
        for(String e: inputVertex){
            if(e!=null){
                System.out.print(e+" ");
            }
        }
        System.out.println();
    }

    public void showEdges(){
        int[][] temp=new int[length][length];
        for(int row=0;row<counter;row++){
            for(int col=0;col<counter;col++){
                temp[row][col]=adjGraph[row][col];
            }
        }
        for(int row=0;row<counter;row++){
            for(int col=0;col<counter;col++){
                if(temp[row][col]==1){
                    temp[col][row]=0;
                    System.out.print(getNode(row)+"-"+getNode(col)+" ");
                }
            }
        }
        System.out.println();
    }

    public void traverseBFS(String node){
        int start=getIndex(node);
        if(!visitStatus[start]){
            bfs.add(node);
            visitStatus[start]=true;
        }
        System.out.print(bfs.remove()+" ");
        for(int col=0;col<counter;col++){
            if(adjGraph[start][col]==1 && !visitStatus[col]){
                bfs.add(getNode(col));
                visitStatus[col]=true;
            }
        }
        if(!bfs.isEmpty() || !isAllVisited()){
            traverseBFS(bfs.element());
        }
    }

    public void traverseDFS(String node){
        int start=getIndex(node);
        int col=0;
        if(!visitStatus[start]){
            dfs.push(node);
            visitStatus[start]=true;
        }
        System.out.print(dfs.pop()+" ");
        while(col<counter && !isAllVisited()){
            if(adjGraph[start][col]==1 && !visitStatus[col]){
                dfs.push(getNode(col));
                visitStatus[col]=true;
                traverseDFS(getNode(col));
            }
            ++col;
        }
    }
}