package AnnaTools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
name: Anna Denisova
date: March 2024
title: Testing Sorting Algorithms
description: This program runs 100 tests of each sorting
algorithm for a chosen number of elements in an array
 */
public class Tester {
    public static void main(String[] args) throws IOException{ //MAIN FUNCTION
        Scanner cin = new Scanner(System.in); //setup input
        System.out.print("Enter the amount of elements you want to be in the arrays: "); //ask for custom input
        int n = cin.nextInt(); //get initial input
        while(n <= 0){ //ERROR CHECK
            System.out.print("Try again: "); //Error message
            n = cin.nextInt(); //get input again
        }
        testn(n); //SEND OUT THE ARGS WITH THE FUNCTION
    }
    public static void testn(int n) throws IOException{
        int[] arr = new int[n]; //Make an array of size n
        long time1, time2; //these store the time differences
        File file = new File("test"+n+".txt"); //Make a new file for the data
        FileWriter writer = new FileWriter(file); //Setup new file
        //bubble
        writer.write("Bubble Sort\n"); //FOR INSIDE THE FILE
        for(int i = 0; i < 100; ++i){ //100 test cases
            reshuffle(arr); //make the new array each time
            time1 = System.nanoTime();
            Sorting.bubbleSort(arr); //SORT THE ARRAY
            time2 = System.nanoTime();
            writer.write(time2-time1+"\n"); //write delta t into file
        }
        //selection
        writer.write("Selection Sort\n"); //FOR INSIDE THE FILE
        for(int i = 0; i < 100; ++i){ //100 test cases
            reshuffle(arr); //make the new array each time
            time1 = System.nanoTime();
            Sorting.selectSort(arr); //SORT THE ARRAY
            time2 = System.nanoTime();
            writer.write(time2-time1+"\n"); //write delta t into file
        }
        //insertion
        writer.write("Insertion Sort\n"); //FOR INSIDE THE FILE
        for(int i = 0; i < 100; ++i){ //100 test cases
            reshuffle(arr); //make the new array each time
            time1 = System.nanoTime();
            Sorting.insertSort(arr); //SORT THE ARRAY
            time2 = System.nanoTime();
            writer.write(time2-time1+"\n"); //write delta t into file
        }
        //quick
        writer.write("Quick Sort\n"); //FOR INSIDE THE FILE
        for(int i = 0; i < 100; ++i){ //100 test cases
            reshuffle(arr); //make the new array each time
            time1 = System.nanoTime();
            Sorting.quickSort(arr, 0, arr.length-1); //SORT THE ARRAY
            time2 = System.nanoTime();
            writer.write(time2-time1+"\n"); //write delta t into file
        }
        //merge
        writer.write("Merge Sort\n"); //FOR INSIDE THE FILE
        for(int i = 0; i < 100; ++i){ //100 test cases
            reshuffle(arr); //make the new array each time
            time1 = System.nanoTime();
            Sorting.mergeSort(arr); //SORT THE ARRAY
            time2 = System.nanoTime();
            writer.write(time2-time1+"\n"); //write delta t into file
        }
        writer.close(); //close the file
        System.out.println("Data written to file: test"+n+".txt"); //Output 2 user
    }
    public static void reshuffle(int[] a){ //this function reshuffles array a
        for(int i = 0; i < a.length; ++i){ //for each position
            a[i] = (int)(Math.random()*100) + 1; //generate a value between 1 and 100 inclusive
        }
    }
}
