package org.example.metod;

public class AnonymousExampleClass {
    public static void main(String[ ] args)   {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++)  {
                    System.out.println(i) ;
                    try {
                        Thread.sleep(100) ;
                    } catch (InterruptedException e)   {
                        e.printStackTrace( );
                    }
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e)   {
            e.printStackTrace( );
        }
        System.out.println("END") ;
    }
}
