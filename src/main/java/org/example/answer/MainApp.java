package org.example.answer;

import java.util.Arrays;

public class MainApp {
    public static void main(String[] args) {
        //oneThreadTask();
        twoThreadTask();
    }

    public static void oneThreadTask(){
        final int ARRAY_LENGHT = 10000000;
        float[] arr = new float[ARRAY_LENGHT];
        Arrays.fill(arr, 1.0f);
        long time = System.currentTimeMillis();
        for (int i = 0; i<arr.length; i++){
            arr[i] = (float)(arr[ i ] * Math. sin(0.2f + i/5) * Math.cos(0.2f + i/5) * Math.cos(0.4f + i/2 ));
        }
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }

    public static void twoThreadTask(){
        final int ARRAY_LENGHT = 10000000;
        final int HALF_ARRAY_LENGHT = ARRAY_LENGHT/2;
        float[] arr = new float[ARRAY_LENGHT];
        Arrays.fill(arr, 1.0f);
        long time = System.currentTimeMillis();
        float[] leftArray = new float[HALF_ARRAY_LENGHT];
        float[] rightArray = new float[HALF_ARRAY_LENGHT];
        System.arraycopy(arr, 0, leftArray, 0, HALF_ARRAY_LENGHT);//разбиваем массив на два массива
        System.arraycopy(arr, HALF_ARRAY_LENGHT, rightArray, 0, HALF_ARRAY_LENGHT);

        Thread threadLeft = new Thread(() -> {
            for (int i = 0; i < leftArray.length; i++) {
                leftArray[i] = (float) (leftArray[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        });

        Thread threadRight = new Thread(() -> {
            for (int i = 0, j=HALF_ARRAY_LENGHT; i < rightArray.length; i++, j++) {
                rightArray[i] = (float) (rightArray[i] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
            }
        });

        threadLeft.start();
        threadRight.start();

        try {
            threadLeft.join();
            threadRight.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.arraycopy(leftArray, 0, arr, 0, HALF_ARRAY_LENGHT);
        System.arraycopy(rightArray, 0, arr, HALF_ARRAY_LENGHT, HALF_ARRAY_LENGHT);
        System.out.println("time: " + (System.currentTimeMillis() - time));
    }

}
