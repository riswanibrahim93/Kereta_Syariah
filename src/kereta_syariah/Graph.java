/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kereta_syariah;

import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author ASUS
 */
class Graph {
    private int Max;
    private Vertex vertexList [];
    private int adjMat [][];
    public int nVerts;
    private Stack theStack;
    
    public Graph()
    {
        Max = 5;
        vertexList = new Vertex[Max];
        adjMat = new int[Max][Max];
        nVerts = 0;
        theStack = new Stack();
        
        for (int i = 0; i < Max; i++) {
            for (int j = 0; j < Max; j++) {
                adjMat[i][j] = 0;
            }
        }
    }
    public void addVertex(String label)
    {
        vertexList[nVerts++] = new Vertex(label);
    }
    public void eddEdge(int start, int end)
    {
        adjMat[start][end] = 1;
//        adjMat[end][start] = 1;
    }
    public void display()
    {
        System.out.println("Adjecency");
        for(int row = 0; row<nVerts; row++)
        {
            for(int col = 0; col<nVerts; col++)
            {
                if(adjMat[row][col] == 1)
                {
                    System.out.println(vertexList[row].label+" -- "+vertexList[col].label);
                }
            }
        }
        System.out.println("");
    }
    public void jalur(String kota)
    {
        int row = tujuan(kota);
        System.out.println("Jalur Kerata");
            
            for(int col = 0; col<nVerts; col++)
            {
                if(adjMat[row][col] == 1)
                {
                    System.out.println(vertexList[row].label+" -- "+vertexList[col].label);
                }
            }
        System.out.println("");
    }
    public int tujuan(String awal)
    {
        int row = 0;
        if(awal == "Banyuangi")
        {
            row = 0;
        }
        else if(awal == "Surabaya")
        {
            row = 1;
        }
        else if(awal == "Madiun")
        {
            row = 2;
        }
        else if(awal == "Malang")
        {
            row = 3;
        }
        else if(awal == "Tulungagung")
        {
            row = 4;
        }
        return row;
    }
    
    public void displayVertex(int v)
    {
        System.out.print(vertexList[v].label+" ");
    }
    public void MST(String kota, String tujuan)
    {
        int awal = tujuan(kota);
        int akhir = tujuan(tujuan);
        vertexList[awal].wasVisited = true;
        theStack.push(awal);
        
        while(!theStack.isEmpty())
        {
            int currentVertex  = (int) theStack.peek();
            System.out.println((int) theStack.peek());
            int v = getAdUnvisitVertex(currentVertex, awal, akhir);
            System.out.println("v = "+v);
            if(v == -1)
            {
                theStack.pop();
            }
            else
            {
                vertexList[v].wasVisited = true;
                theStack.push(v);
                displayVertex(currentVertex);
                System.out.print(" -- ");
                displayVertex(v);
                System.out.println("");
            }
        }
        resetFlags();
    }
    public int getAdUnvisitVertex(int v, int awal, int akhir)
    {
        if(awal < akhir)
        {
            for (int i = awal; i <= akhir; i++) 
            {
                if(adjMat[v][i] == 1 && vertexList[i].wasVisited == false)
                {
                    return i;
                }
            }
        }
        else if(awal > akhir)
        {
            for (int i = akhir; i <= awal; i++) 
            {
                if(adjMat[v][i] == 1 && vertexList[i].wasVisited == false)
                {
                    return i;
                }
            }
        }
        return -1;
    }
    public void resetFlags()
    {
        for (int i = 0; i < nVerts; i++) {
            vertexList[i].wasVisited = false;
        }
    }
    public static void main(String[] args) {
        Graph thegraph = new Graph();
        
        thegraph.addVertex("Banyuangi");//0
        thegraph.addVertex("Surabaya");//1
        thegraph.addVertex("Madiun");//2
        thegraph.addVertex("Malang");//3
        thegraph.addVertex("Tulungagung");//4
        
        thegraph.eddEdge(0, 1);
        thegraph.eddEdge(1, 0);
        thegraph.eddEdge(1, 2);
        thegraph.eddEdge(1, 3);
        thegraph.eddEdge(2, 1);
        thegraph.eddEdge(2, 4);
        thegraph.eddEdge(3, 4);
        thegraph.eddEdge(3, 2);
        thegraph.eddEdge(4, 1);
        thegraph.eddEdge(4, 2);
        thegraph.display();
        
        thegraph.jalur("Surabaya");
        thegraph.MST("Surabaya", "Tulungagung");
    }
}
