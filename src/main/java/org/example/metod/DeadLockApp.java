package org.example.metod;

public class DeadLockApp {
    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();

    public static void main(String[] args) {
        DeadThreadOne threadOne = new DeadThreadOne();
        DeadThreadTwo threadTwo = new DeadThreadTwo();
        threadOne.start();
        threadTwo.start();
    }

    private static class DeadThreadOne extends Thread {
        public void run(){
            synchronized (lock1){
                System.out.println("DeadThreadOne is holding LOCK1...");
                try {
                    Thread.sleep( 100) ;
                } catch (InterruptedException e)   {
                    e.printStackTrace( );
                }
                System.out.println("DeadThreadOne is waiting LOCK2...");
                synchronized (lock2){
                    System.out.println("DeadThreadOne is holding LOCK1 and LOCK2...");
                }
            }
        }
    }

    private static class DeadThreadTwo extends Thread {
        public void run(){
            synchronized (lock2){ //чтобы не было дэдлока, меняем на lock1
                System.out.println("DeadThreadTwo is holding LOCK2...");
                try {
                    Thread.sleep( 100) ;
                } catch (InterruptedException e)   {
                    e.printStackTrace( );
                }
                System.out.println("DeadThreadTwo is waiting LOCK1...");
                synchronized (lock1){ //чтобы не было дэдлока, меняем на lock2
                    System.out.println("DeadThreadTwo is holding LOCK1 and LOCK2...");
                }
            }
        }
    }
}
