package org.example.metod;

public class MainClass1 {
    static class MyThread extends Thread  {
        @Override
        public void run( )  {
            for (int i = 0; i < 10; i++)  {
                try {
                    Thread.sleep(100) ;
                    System.out.println(" new thread: "  +   i) ;
                } catch (InterruptedException e)   {
                    e.printStackTrace( );
                }
            }
        }
    }

    public static void main(String[ ] args)   {
        new MyThread( ).start( );
    }
}
