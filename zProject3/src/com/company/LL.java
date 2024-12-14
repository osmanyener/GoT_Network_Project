package com.company;

import java.util.LinkedList;

public class LL {

    int M;
    public LinkedList[] table;

    public LL(int M) {
        this.M = M;
        table = new LinkedList[M];
        for (int ix = 0; ix < M; ix++) {
            table[ix] = new LinkedList();
        }
    }

    public int hashing(String x) {
        int key;
        for (int i = 0; i < GraphMatrix.names.size(); i++) {
            if (x.equals(GraphMatrix.names.get(i))) {
                key = i;
                return key;
            }
        }
        return -1;
    }

    void insert(String t) {
        int ix = hashing(t);
        table[ix].addFirst(t);
    }

    public void addToHash() {
        for (int j = 0; j < GraphMatrix.names.size(); j++) {
            GraphMatrix.table.insert(GraphMatrix.names.get(j));
        }
    }

    @Override
    public String toString() {

        for (int i = 0; i < M; i++) {
            if (table[i] != null) {
                System.out.println(i + "-->" + table[i].toString() + "]");
            }
        }
        return "";
    }

}
