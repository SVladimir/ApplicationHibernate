package com.sbt.accounting; /**
 * Created by vsshm_000 on 02.11.2016.
 */

import com.sbt.project.MessageReceiver;

public class Engine {
    public static void main(String[] args) throws Exception {

        MessageReceiver messageReceiver=new MessageReceiver();
        System.out.printf("PROCESSED ="+messageReceiver.recciev());

        
    }
}
