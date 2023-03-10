package com.example.mapbuddy;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;


public class pathfinder {
    //(1) Data structure
    String[] names_of_nodes = new String[]{"","Main Gate" , "Junc1" , "Boys Hostel" , "Library" , "Shakuntalam Hall" , "Canteen" , "Royal Enfiled Workshop" ,
            "Acoounts Department" , "India Post Office" , "Mother Dairy" ,"CV Raman Block" , "Junc3" , "Junc4" , "Civil Engineering Department"
            ,"Reception" , "Lal Chowk" , "Health Despensary" ,"Basket Ball Court" , "Main Ground" ,"VC Office" ,"Management Department" , "Girls Hostel",
            "Electrical Department" , "Computer Department" , "MBA Park" , "Electronics Department" , "Accounts Section" , "Auditorium" , "Computer Lab" ,
            "Student Window " , "Administrative Block" ,"" ,"" ,"" ,"" ,"" ,"" ,"" ,"" ,"" ,"" ,"" } ;

    //(2) Data structure
    HashMap< String , Integer > nodeNumber = new HashMap<>();
    //(3) Data structure
    String[][] pathname = new String[40][40] ;
    //(4) Data Structure
    int dist[][] = new int[40][40] ;
    int[] distance =new int[40] ;
    int[] parent =new int[40] ;

    //------------------------------------------
    static class Edge {
        int src;
        int nbr;
        int wt;

        Edge(int src, int nbr, int wt) {
            this.src = src;
            this.nbr = nbr;
            this.wt = wt;
        }
    }

    //(4) For graph Data structure / adjancency list
    int vtces = 40;

    ArrayList<Edge>[] graph = new ArrayList[vtces];

    public void connectEgde( int u , int v , int dis , String utov , String vtou ){
        graph[u].add(new Edge(u, v, dis));
        graph[v].add(new Edge(v, u, dis));
        pathname[u][v] = utov ;
        pathname[v][u] = vtou ;
        dist[u][v] = dist[v][u] = dis;
    }

    public pathfinder(){

        for(int i=1; i<=35; i++){
            nodeNumber.put(names_of_nodes[i] , i ) ;
            graph[i] = new ArrayList<>();
            distance[i]=100000000;
            parent[i]=i;
        }
        connectEgde(1,2,50 ,"Go straight" , "Go straight");
        connectEgde(2,3,30 ,"Turn right" , "Go straight");
        connectEgde(3,4,20 ,"Turn left" , "Turn Right" );
        connectEgde(4,5,20,"take right" , "Go right");
        connectEgde(4,6,20,"take right" , "Go left");
        connectEgde(5,6,20,"take left" , "Go right");
        connectEgde(7,8 , 60 , "take right " , "take left");
        connectEgde(8,9 ,20 , " take right " , "take right");
        connectEgde(9,10 ,10 , " take right " , "take left");
        connectEgde(1,10,100, "turn right" , "turn left" );
        connectEgde(7,11,5, "Go Straigt" , "Turn left");
        connectEgde(11,12,60, "Head right -> take right" , "");
        connectEgde(11,13,10,"take right" , "then take left" );
        connectEgde(13,14,60 ,"Go straight" , "Go straight beside Shakuntalam");
        connectEgde(14,15,15 ,"Go straight" , "Go straight beside Shakuntalam");
        connectEgde(15,16,20 ,"Go left " , "Go right beside boys washroom");
        connectEgde(16,17 ,15 ,"Head Out" , "Go inside Administrative block");
        connectEgde(17,18,5,"Take left ","Turn right");
        connectEgde(17,19,10,"Take left go straight ","Turn right");
        connectEgde(17,20,20,"Go straight turn right" , "Go straight take left");
        connectEgde(2,20,2,"Turn left" , "Go straight ");
        connectEgde(17,21,10,"turn right" , "turn left");
        connectEgde(17,22 , 10 ,"Turn left" , "Take right" );
        connectEgde(17,23,100,"Head Straight take right" , "Head Straight take left");
        connectEgde(2,24,10,"head Staight take right" , "take left" );
        connectEgde(24,25,10,"take right" , "take left" );
        connectEgde(25,26,10,"take right" , "take left" );

        connectEgde(27,8,10,"take stairs go down" , "take stairs to 2nd floor turn right" );
        connectEgde(22,28,10,"Take stairs , On first floor " , "Take stairs,down ");
        connectEgde(25,29,15,"Take lift/stairs , first floor turn left" , "go straight take stairs , down");
        connectEgde(8,30,2,"On the left besides president office" , "Take right , go straight");
        connectEgde(17,31,5,"in right" , "go out straight");

    }

    public ArrayList<Integer> dijkstra_bfs(int src , int end ){

        Log.i("selected" , src+" "+ end ) ;
        PriorityQueue<Pair> queue = new PriorityQueue<>();

        queue.add(new Pair(src, 0));
        boolean[] visited = new boolean[vtces];

        while(queue.size() > 0){
            Pair curr = queue.remove();

//            System.out.println(rem.v + " via " + rem.psf.toString() + " @ " + rem.wsf);

            for (Edge e : graph[curr.node]) {
                if (curr.dis + e.wt < distance[e.nbr]) {
                    distance[e.nbr] = curr.dis + e.wt ;
                    parent[e.nbr] = curr.node ;
                    queue.add( new Pair(e.nbr , distance[e.nbr] ) ) ;
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>() ;
        while(parent[end]!=src){
            res.add(end) ;
            end = parent[end];
        }
        res.add(end) ;
        res.add(src) ;
        Collections.reverse(res);
        return res ;
    }

    static class Pair implements Comparable<Pair> {
        int node;
        int dis;

        Pair(int node, int currdis){
            this.node = node;
            this.dis = currdis;
        }

        public int compareTo(Pair o){
            return this.dis - o.dis;
        }
    }

    public ArrayList<String> givePath( String start , String end ) {
        int pt = 1 ;
        ArrayList<String> shortestPath = new ArrayList<>() ;

        if(start.equals(end)) {
            shortestPath.add("You are here only !!");
            return shortestPath;
        }
        
        int ids , ide ;
        
        try{
            ids = nodeNumber.get(start)  ;
            ide = nodeNumber.get(end)  ;
        }
        catch (Exception  e){
            e.printStackTrace() ;
            return shortestPath ;
        }

        ArrayList<Integer> pathInNode =  dijkstra_bfs(ids , ide );

        for(int i =0 ; i<pathInNode.size()-1; i++){
            shortestPath.add( (pt++) + ". "+ pathname[pathInNode.get(i)][pathInNode.get(i+1)]
                    +" to " + names_of_nodes[pathInNode.get(i+1)]  + " -" + dist[pathInNode.get(i)][pathInNode.get(i+1)]+"ms" ) ;
        }
        return shortestPath ;
    }

}
