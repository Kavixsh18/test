/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bstt;
import java.util.*;

/**
 *
 * @author readi
 */
public class Bstt {
    static double[] tree = new double[100];
    static int maxIndex = 0;

    // check duplicate
    public static boolean exists(double value) {
        for (int i = 0; i <= maxIndex; i++) {
            if (tree[i] == value) return true;
        }
        return false;
    }

    // insert BST
    public static void insert(double value, int index) {

        if (index >= tree.length) {
            System.out.println("Tree is full.");
            return;
        }

        if (tree[index] == 0) {
            tree[index] = value;

            if (index > maxIndex)
                maxIndex = index;

            return;
        }

        if (value < tree[index]) {
            insert(value, 2 * index + 1);
        }

        else if (value > tree[index]) {
            insert(value, 2 * index + 2);
        }
    }

    // remove node
    public static void remove(double value) {

        if (value == 0) {
            System.out.println("Invalid: cannot remove empty node.");
            return;
        }

        int index = -1;

        for (int i = 0; i <= maxIndex; i++) {
            if (tree[i] == value) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Invalid: value not found.");
            return;
        }

        int left = 2 * index + 1;
        int right = 2 * index + 2;

        boolean hasLeft = left <= maxIndex && tree[left] != 0;
        boolean hasRight = right <= maxIndex && tree[right] != 0;

        if (hasLeft || hasRight) {
            System.out.println("Invalid: node has child nodes.");
            return;
        }

        tree[index] = 0;
        System.out.println("Node removed successfully.");
    }

    // replace node
    public static void replace(double target, double newValue) {

    if (target == 0) {
        System.out.println("Invalid: cannot replace empty node.");
        return;
    }

    int index = -1;

    for (int i = 0; i <= maxIndex; i++) {
        if (tree[i] == target) {
            index = i;
            break;
        }
    }

    if (index == -1) {
        System.out.println("Invalid: target node not found.");
        return;
    }

    if (exists(newValue)) {
        System.out.println("Invalid: duplicate value.");
        return;
    }

    // CHECK PARENT RULE
    if (index != 0) {

        int parent = (index - 1) / 2;

        if (index == 2 * parent + 1) { // left child
            if (newValue >= tree[parent]) {
                System.out.println("Invalid: left child must be less than parent.");
                return;
            }
        }

        if (index == 2 * parent + 2) { // right child
            if (newValue <= tree[parent]) {
                System.out.println("Invalid: right child must be greater than parent.");
                return;
            }
        }
    }

    // CHECK CHILDREN
    int left = 2 * index + 1;
    int right = 2 * index + 2;

    if (left <= maxIndex && tree[left] != 0 && newValue <= tree[left]) {
        System.out.println("Invalid: must be greater than left child.");
        return;
    }

    if (right <= maxIndex && tree[right] != 0 && newValue >= tree[right]) {
        System.out.println("Invalid: must be less than right child.");
        return;
    }

    tree[index] = newValue;

    System.out.println("Node replaced successfully.");
}

    // compute tree size
    public static int getTreeSize() {

        int k = (int)(Math.log(maxIndex + 1) / Math.log(2)) + 1;

        return (int)Math.pow(2, k) - 1;
    }

    // find element
    public static void find(double value) {

        for (int i = 0; i <= maxIndex; i++) {

            if (tree[i] == value) {

                System.out.println("Element found at index: " + i);
                System.out.println("Array size: " + getTreeSize());
                return;
            }
        }

        System.out.println("Element not found.");
    }

    // print tree
    public static void printTree() {

        int size = getTreeSize();

        System.out.print("TREE = {");

        for (int i = 0; i < size; i++) {

            if (tree[i] == (int)tree[i])
                System.out.print((int)tree[i]);
            else
                System.out.print(tree[i]);

            if (i < size - 1)
                System.out.print(",");
        }

        System.out.println("}");
    }

    // menu
    public static void menu() {

        System.out.println();
        System.out.println("[A] ADD NODE");
        System.out.println("[B] REMOVE NODE");
        System.out.println("[C] REPLACE NODE");
        System.out.println("[D] FIND ELEMENT");
        System.out.println("[E] EXIT");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("ENTER ROOT: ");
        double root = sc.nextDouble();
        sc.nextLine();

        insert(root,0);

        System.out.println("\nROOT = " + root);
        printTree();

        while(true) {

            menu();

            System.out.print("ENTER CHOICE: ");
            String choice = sc.nextLine().toUpperCase();

            switch(choice) {

                case "A":

                    System.out.print("ENTER VALUE(S) separated by comma: ");

                    String input = sc.nextLine();

                    String[] values = input.split(",");

                    for(String v : values) {

                        try {

                            double val = Double.parseDouble(v.trim());

                            if(exists(val)) {
                                System.out.println("Duplicate value skipped: " + val);
                                continue;
                            }

                            insert(val,0);

                        } catch(Exception e) {

                            System.out.println("Invalid input skipped: " + v);
                        }
                    }

                    printTree();
                    break;

                case "B":

                    System.out.print("ENTER VALUE: ");
                    double del = sc.nextDouble();
                    sc.nextLine();

                    remove(del);
                    printTree();
                    break;

                case "C":

                    System.out.print("TARGET NODE: ");
                    double target = sc.nextDouble();

                    System.out.print("REPLACE WITH: ");
                    double rep = sc.nextDouble();
                    sc.nextLine();

                    replace(target,rep);
                    printTree();
                    break;

                case "D":

                    System.out.print("ENTER VALUE TO FIND: ");
                    double find = sc.nextDouble();
                    sc.nextLine();

                    find(find);
                    break;

                case "E":

                    System.out.println("\nFINAL TREE:");
                    printTree();
                    return;

                default:
                    System.out.println("Invalid menu option.");
            }
        }
    }
}