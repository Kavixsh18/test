package com.mycompany.binarysearchtree;

import java.util.*;

public class Binarysearchtree {

    static int[] tree = new int[15];

    static void addNode(int value) {

        int i = 0;

        while (i < tree.length) {

            if (tree[i] == 0) {
                tree[i] = value;
                return;
            }

            if (value < tree[i]) {
                i = 2 * i + 1;
            } 
            else if (value > tree[i]) {
                i = 2 * i + 2;
            } 
            else {
                System.out.println("Duplicate value not allowed.");
                return;
            }
        }

        System.out.println("Tree full.");
    }

    static void removeNode(int value) {

        for (int i = 0; i < tree.length; i++) {

            if (tree[i] == value) {

                int right = 2 * i + 2;

                if (right < tree.length && tree[right] != 0) {
                    tree[i] = tree[right];
                    tree[right] = 0;
                } 
                else {
                    tree[i] = 0;
                }

                return;
            }
        }

        System.out.println("Value not found.");
    }

    static void replaceNode(int target, int newVal){

        for(int i = 0; i < tree.length; i++){

            if(tree[i] == target){
                tree[i] = newVal;
                return;
            }
        }

        System.out.println("Target not found.");
    }

    static void printTree(){

        System.out.print("TREE = {");

        boolean onlyRoot = true;

        for(int i = 1; i <= 6; i++){
            if(tree[i] != 0){
                onlyRoot = false;
                break;
            }
        }

        if(onlyRoot){
            System.out.println(tree[0] + "}");
            return;
        }

        for(int i = 0; i <= 6; i++){
            System.out.print(tree[i]);

            if(i < 6){
                System.out.print(",");
            }
        }

        System.out.println("}");
    }

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.print("ENTER A ROOT: ");
        int root = sc.nextInt();

        tree[0] = root;

        System.out.println("\nROOT = " + root);
        printTree();

        while(true){

            System.out.println("\n[A] PRESS A TO ADD NODE");
            System.out.println("[B] PRESS B TO REMOVE NODE");
            System.out.println("[C] REPLACE NODE");
            System.out.println("[D] EXIT");

            System.out.print("ENTER YOUR OUTPUT: ");
            String choice = sc.next().toUpperCase();

            if(choice.equals("A")){

                System.out.print("\nENTER A VALUE: ");
                int val = sc.nextInt();

                addNode(val);
                printTree();
            }

            else if(choice.equals("B")){

                System.out.print("\nENTER A VALUE: ");
                int val = sc.nextInt();

                removeNode(val);
                printTree();
            }

            else if(choice.equals("C")){

                System.out.print("\nTARGET NODE: ");
                int target = sc.nextInt();

                System.out.print("REPLACE IT WITH: ");
                int newVal = sc.nextInt();

                replaceNode(target, newVal);
                printTree();
            }

            else if(choice.equals("D")){
                break;
            }

        }

        sc.close();
    }
}