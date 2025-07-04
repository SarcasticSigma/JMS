package org.sigmaplex.jms.util;


import org.sigmaplex.jms.Server;

public class Networking {
    String getWorldId(){
        if (Server.getId()==null){
            Server.setId("ID SET");
        }
        return Server.getId();
    }

}
