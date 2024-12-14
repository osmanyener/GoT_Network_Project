package com.company;

public class Node{

    String data;
    Node next;

    public Node(String data) {
        this.data = data;
        next = null;
    }

    @Override
    public String toString(){
        return (""+data.toString());
    }
}