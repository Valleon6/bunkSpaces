package com.valleon.bunkspaces.models;

import lombok.Data;

@Data
public class Room {

    private String id;
    private BedSpace[] bedSpaces;


    public Room(String roomId){
        id = roomId;
        bedSpaces = new BedSpace[4];
        for (int i = 1; 1 <=bedSpaces.length; i++){
            bedSpaces[i-1] = new BedSpace( id + "Bedspace " + i);
        }
    }

}
