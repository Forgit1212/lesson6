package org.example.metod;
//Потоки-демоны

public class DaemonExample {
    public static void main(String[ ] args)   {
        Thread tTimer = new Thread(() -> {       //работа потока-демона
            int time = 0;
            while (true){
                try {
                    Thread.sleep(1000);
                time++;
                System.out.println("time: "+time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        tTimer.setDaemon(true);
        tTimer.start();                             //работа основного потока
        // как только завершается работа всех основных потоков,
        // завершается работа потоков-демонов
        System.out.println("main -> sleep");
        try {
            Thread.sleep(5000) ;
        } catch (InterruptedException e)   {
            e.printStackTrace( );
        }
        System.out.println("main -> end");
    }
}
