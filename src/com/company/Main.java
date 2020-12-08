package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main  extends Thread{
    static float [][] jordanThread(float a1[][]) throws InterruptedException {
        float a[][] = a1;
        int n = a[0].length - 1;
        long startTime = System.currentTimeMillis();

        for (int k = 0; k < n; k++) {
            if (Math.abs(a[k][k]) == 0) {
                for (int i = k + 1; i < n; i++) {
                    if (Math.abs(a[i][k]) > 0) {
                        for (int j = k; j < n + 1; j++) {
                            float t = a[i][j];
                            a[i][j] = a[k][j];
                            a[k][j] = t;
                        }
                        break;
                    }
                }
            }
            float pivot = a[k][k];
            for (int j = 0; j < n + 1; j++) {
                a[k][j] /= pivot;
            }
            float factor = 0;
            List<MyThread> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if(i == k || a[i][k] == 0) {
                    continue;
                }
                factor = a[i][k];
                list.add(new MyThread(k, n, a[i], a[k], factor));
            }
            for (MyThread myThread : list) {
                myThread.start();
            }
            for (MyThread myThread : list) {
                myThread.join();
            }
        }
        System.out.println("Thread: " + (System.currentTimeMillis() - startTime));
        return a;
    }
    static float [][] jordanNoThread(float a1[][]) {
        float a[][] = a1;
        int n = a[0].length - 1;
        long startTime = System.currentTimeMillis();

        for (int k = 0; k < n; k++) {
            if (Math.abs(a[k][k]) == 0) {
                for (int i = k + 1; i < n; i++) {
                    if (Math.abs(a[i][k]) > 0) {
                        for (int j = k; j < n + 1; j++) {
                            float t = a[i][j];
                            a[i][j] = a[k][j];
                            a[k][j] = t;
                        }
                        break;
                    }
                }
            }
            float pivot = a[k][k];
            for (int j = 0; j < n + 1; j++) {
                a[k][j] /= pivot;
            }
            for (int i = 0; i < n; i++) {
                if (i == k || a[i][k] == 0) continue;
                float factor = a[i][k];
                for (int j = k; j < n + 1; j++) {
                    a[i][j] -= factor * a[k][j];
                }
            }
        }
        System.out.println("No Thread: " + (System.currentTimeMillis() - startTime));
        return a;
    }

    static void print (float a[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                //System.out.print(Math.round(a[i][j] * 100) / 100D + "  ");
            }
            //System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 100;
        int m = n+1;
        float a[][] =new float[n][m];
        //Random rnd  = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                a[i][j] = (float) (Math.random()*100);

            }
        }
        print(jordanThread(a));
        print(jordanNoThread(a));

    }
}
