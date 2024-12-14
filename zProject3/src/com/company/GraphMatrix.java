package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

public class GraphMatrix {

    static ArrayList<String> names = new ArrayList<>();
    static LL table;

    int edges[][];
    int numV;
    int numE;

    public GraphMatrix(int V) {
        this.numV = V;
        edges = new int[V][V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                edges[i][j] = 0;
            }
        }
    }

    public void addEdge(int from, int to, int weight) {
        edges[from][to] = weight;
    }

    public boolean isAdjacent(int v1, int v2) {
        return (edges[v1][v2] != 0);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("");
        for (int i = 0; i < numV; i++) {
            for (int j = 0; j < numV; j++) {
                s.append(edges[i][j] + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static GraphMatrix readGraphFromFile(String f) {

        try {
            Scanner reader = new Scanner(new File(f));
            GraphMatrix g1 = new GraphMatrix(names.size());
           
            while (reader.hasNextLine()) {
                String str = reader.nextLine();
                String[] item = str.split(",");
                Edge newName = new Edge(item[0], item[1], Integer.parseInt(item[2]));
                int v1 = table.hashing(newName.v1);
                int v2 = table.hashing(newName.v2);
                int weight = newName.weight;
                g1.addEdge(v1, v2, weight);                
            }
            return g1;
            
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
            return null;
        }
    }
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        File file = new File("AllCharacters.txt");
        //File file = new File("ToyExample.txt");

        try {
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String str = reader.nextLine();
                String[] item = str.split(",");
                Edge newName = new Edge(item[0], item[1], Integer.parseInt(item[2]));
                if (!names.contains(newName.v1)) {
                    names.add(newName.v1);
                }
                if (!names.contains(newName.v2)) {
                    names.add(newName.v2);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(names);
        table = new LL(names.size());
        table.addToHash();
        System.out.println(table.toString());
        GraphMatrix g2 = readGraphFromFile("AllCharacters.txt");
        //GraphMatrix g2 = readGraphFromFile("ToyExample.txt");
        
        System.out.println(g2);
        Paths e = new Paths(g2);
        
        System.out.println("Game of Thrones Network");
        System.out.println("MENU");
        int h = 0;

        while (h != 8) {
            System.out.println("Please choose one of the options below that you want to choose");
            System.out.println("1> Check if there is a path between two characters (Method B)");
            System.out.println("2> Find all paths of given length and number (Method C)");
            System.out.println("3> Find shortest path between two characters (Method D)");
            System.out.println("4> Find the number of paths between two characters (Method E)");
            System.out.println("5> Show the BFS order between two characters (Method F)");
            System.out.println("6> Show the DFS order between two characters (Method G)");
            System.out.println("7> Find the number of characters in the same component (Method H)");
            System.out.println("8> Exit the program");
            System.out.println("Enter a number:");
            h = input.nextInt();

            switch (h) {
                case 1: {
                    System.out.println("Check if there is a path between two characters (Method B)");
                    System.out.print("Start from: ");
                    String from = input.next();
                    System.out.print("Until to: ");
                    String to = input.next();
                    System.out.println(e.IsThereAPath(g2, from, to));
                    break;
                }
                case 2: {
                    
                    break;
                }
                case 3: {
                    System.out.println("Find shortest path between two characters (Method D)");
                    System.out.print("Start from: ");
                    String from = input.next();
                    System.out.print("Until to: ");
                    String to = input.next();
                    e.ShortestPathLengthFromTo(g2, from, to);
                    break;
                }
                case 4: {
                    System.out.println("Find the number of paths between two characters (Method E)");
                    System.out.print("Start from: ");
                    String from = input.next();
                    System.out.print("Until to: ");
                    String to = input.next();
                    System.out.println(e.NoOfPathsFromTo(g2, from, to));
                    Paths.numOfPaths=0;
                    break;
                }
                case 5: {
                    System.out.println("Show the BFS order between two characters (Method F)");
                    System.out.print("Start from: ");
                    String from = input.next();
                    System.out.print("Until to: ");
                    String to = input.next();
                    System.out.println(e.BFS(g2, from, to));
                    break;
                }
                case 6: {
                    System.out.println("Show the DFS order between two characters (Method G)");
                    System.out.print("Start from: ");
                    String from = input.next();
                    System.out.print("Until to: ");
                    String to = input.next();
                    System.out.println(e.DFS(g2, from, to, " "));                   
                    break;
                }
                case 7: {
                    System.out.println("Find the number of characters in the same component (Method H)");
                    System.out.print("The component that contains name: ");
                    String from = input.next();
                    System.out.println(e.NoOfVerticesInComponent(g2, from));
                    break;
                }
                case 8:
                    System.out.println("Exited the program,Thank you.");
                default:
                    break;
            }
        }
    }
}
