package com.company;
import static com.company.GraphMatrix.names;
import static com.company.GraphMatrix.table;

import java.util.*;
import java.util.LinkedList;
import java.util.Arrays;



public class Paths {

    public static LinkedList adj[];
    boolean[] visited;
    int[] edgeTo;
    int[] distTo;
    int from0;
    static int numOfPaths;

    public Paths(GraphMatrix g) {
        edgeTo = new int[g.numV];
        visited = new boolean[g.numV];
        distTo = new int[g.numV];
        numOfPaths = 0;

    }

    // METHOD B
    boolean IsThereAPath(GraphMatrix g, String name1, String name2) {
        int to = table.hashing(name2);
        BFS(g, name1, name2);
        return visited[to];
    }
    //Method C
void AllPathsShorterThanEqualTo (GraphMatrix g, int pathLen, int VertexNo, String name1){
     int from = table.hashing(name1);    
     int totalLen = 0;
        boolean[][] isVisited = new boolean[g.numV][g.numV];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(from);
        ArrayList<Integer> FinalPathList = new ArrayList<>();
        FindAllPathsBtwTwoVertices(g, pathLen, VertexNo, from,isVisited,pathList,totalLen,FinalPathList);
        for (int i = 0; i < FinalPathList.size(); i++) {
            System.out.print(FinalPathList.get(i));
    }
}

    List FindAllPathsBtwTwoVertices(GraphMatrix g, Integer PathLen, int VertexNo, Integer from, boolean[][] isVisited, List<Integer> localPathList, Integer totalLen, List<Integer> FinalPathList) {
        if (VertexNo == localPathList.size()) {
            for (int i = 0; i < g.numV; i++) {
                for (int j = 0; j < g.numV; j++) {
                    isVisited[i][j] = false;
                }
            }
            for (int i = 0; i < localPathList.size(); i++) {
                for (int j = i; j < localPathList.size(); j++) {
                    isVisited[i][j] = true;
                }
            }
            FinalPathList.addAll(localPathList);
        }

        for (int j = 0; j < g.numV; j++) {
            if ((g.edges[from][j] != 0 || g.edges[j][from] != 0) && (!isVisited[from][j])) {
                if (g.edges[from][j] != 0 && (!isVisited[from][j])) {
                    isVisited[from][j] = true;
                    totalLen += g.edges[from][j];
                    if (totalLen <= PathLen) {
                        localPathList.add(j);
                    }
                } else if ((g.edges[j][from] != 0) && (!isVisited[from][j])) {
                    isVisited[j][from] = true;
                    totalLen += g.edges[j][from];
                    if (totalLen <= PathLen) {
                        localPathList.add(from);
                    }
                }
                FindAllPathsBtwTwoVertices(g, PathLen, VertexNo, j, isVisited, localPathList, totalLen, FinalPathList);
                localPathList.removeAll(localPathList);
            }
        }
        return FinalPathList;
    }
    // METHOD D
    void ShortestPathLengthFromTo(GraphMatrix g, String name1, String name2) {
        int from = table.hashing(name1);
        int to = table.hashing(name2);
        int dist[] = new int[g.numV]; 

        Boolean sptSet[] = new Boolean[g.numV];

        for (int i = 0; i < g.numV; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[from] = 0;
        for (int count = 0; count < g.numV - 1; count++) {
            int u = minDistance(g,dist, sptSet);
            sptSet[u] = true;
            for (int v = 0; v < g.numV; v++) 
            {
                if (!sptSet[v] && g.edges[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + g.edges[u][v] < dist[v]) {
                    dist[v] = dist[u] + g.edges[u][v];
                }
            }
        }  
            System.out.println(dist[to]);
    }

    int minDistance(GraphMatrix g, int dist[], Boolean sptSet[]) {
        int min = Integer.MAX_VALUE, min_index = -1;
        
        for (int v = 0; v < g.numV; v++) {
            if (sptSet[v] == false && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }
    
    //Method E
    int NoOfPathsFromTo(GraphMatrix g,String name1, String name2) {
        
        int from = table.hashing(name1);
        int to = table.hashing(name2);
        boolean[] isVisited = new boolean[g.numV];
        ArrayList<Integer> pathList = new ArrayList<>();
        pathList.add(from);       
        FindAllPathsBtwTwoVertices(g, from, to, isVisited, pathList);
        return numOfPaths;
    }

   
     void FindAllPathsBtwTwoVertices(GraphMatrix g, Integer from, Integer to, boolean[] isVisited, List<Integer> localPathList) {
         if (from.equals(to)) {
          numOfPaths++;   
        }
        isVisited[from] = true;
        for (int j = 0; j < g.numV; j++) {
            if ((g.edges[from][j] != 0 || g.edges[j][from]!=0 ) && (!isVisited[j])) {
                localPathList.add(j);
                FindAllPathsBtwTwoVertices(g, j, to, isVisited, localPathList);
                localPathList.remove(localPathList.size() - 1);
            }
        }
        isVisited[from] = false;       
    }

    // METHOD F
    String BFS(GraphMatrix g, String from1, String to1) {
        int from = table.hashing(from1);
        int to = table.hashing(to1);
        Arrays.fill(visited, false);
        List<Integer> q = new ArrayList<>();
        q.add(from);
        visited[from] = true;
        int vis;
        String s = "";
        while (!q.isEmpty()) {
            vis = q.get(0);
            s += names.get(vis) + " ";
            if (vis == to) {
                return s;
            }
            q.remove(q.get(0));
            for (int i = 0; i < g.numV; i++) {
                if ((g.edges[vis][i] != 0 || g.edges[i][vis] != 0) && (!visited[i])) {
                    q.add(i);
                    visited[i] = true;
                }
            }
        }
        return null;
    }
    // METHOD G
      public String DFS(GraphMatrix g, String name1, String name2, String s) {
          if((s.equals(" "))){
             if(IsThereAPath(g, name1, name2)==false){
             return "No path!";
         }  
             Arrays.fill(visited, false);
          }
        
        s+= name1 +" ";
        visited[table.hashing(name1)] = true;
        for (int i = 0; i < g.edges[table.hashing(name1)].length; i++) {
            if ((g.edges[table.hashing(name1)][i] != 0 || g.edges[i][table.hashing(name1)] != 0) && (!visited[i])) {
                if (i == table.hashing(name2)) {
                    s+= name2;
                    break;
                }
                return DFS(g, names.get(i), name2, s);
            }
        }
        return s;
    }

    // METHOD H
    int NoOfVerticesInComponent(GraphMatrix g, String name1){
       int count = 0;
       if (names.contains(name1)){
           for(int i= 0; i<g.numV; i++){
               if( IsThereAPath(g,name1,names.get(i))){
                   count++;
               }
           }
           return count;
       }
       System.out.println("Name not found!");
       return 0;
    }

}