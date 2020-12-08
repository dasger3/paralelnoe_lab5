package com.company;

public class MyThread extends Thread {
    private int k;
    private int n;
    private float a[];
    float[] a_k;
    float factor;

    @Override
    public void run() {
        for (int j = k; j < n + 1; j++) {
            a[j] -= factor * a_k[j];
        }
    }

    public MyThread(int k, int n, float[] a, float[] a_k, float factor) {
        this.k = k;
        this.n = n;
        this.a = a;
        this.a_k = a_k;
        this.factor = factor;
    }
}
