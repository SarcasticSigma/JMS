package org.sigmaplex.jms;


public class Networking {
    String getWorldId(){
        if (Server.getId()==null){
            Server.setId("ID SET");
        }
        return Server.getId();
    }

}
