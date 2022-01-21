package com.maryafolabi.bankapp;

public class PerfectSquareCount {
    public static void main(String[] args) {

        System.out.println(new PerfectSquareCount().perfectSquare(5, 5));
    }
    public long perfectSquare(long l, long b){
        long result = 0;
        while(l > 0 && b > 0) {
            result += l * b;
            l--;
            b--;
        }
        return result;
    }
}
