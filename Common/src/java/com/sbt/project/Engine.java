package com.sbt.project;

/**
 * Created by vsshm_000 on 02.11.2016.
 */

public class Engine {
    public static void main(String[] args) {
        try {
            MessageReceiver messageReceiver = new MessageReceiver();
            System.out.printf("To processing=" + messageReceiver.recciev());
        }  catch (Exception e) {
            e.printStackTrace();
        }



    }
}
