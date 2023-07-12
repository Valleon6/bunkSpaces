package com.valleon.bunkspaces.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hostel {

    private HostelName name;

    private Room[] rooms;

    private Gender hostelCategory;

    public Hostel(String hostelName, Gender hostelCategory){
        name = HostelName.valueOf(hostelName);
        rooms = new Room[20];
        for(int i = 1; i <= rooms.length; i++){
            rooms[i-1] = new Room(hostelName + "Room " + +i);
            }
        this.hostelCategory = hostelCategory;
        }

    public List<BedSpace> findAllAvailableBedSpace() {
        List<BedSpace> bedspaces = new ArrayList<>();
        for (Room room : rooms){
            bedspaces.addAll(Arrays.asList(room.getBedSpaces()));
        }
        return bedspaces;
    }

    public Gender getHostelCategory(){
        return hostelCategory;
    }

    public Room[] getRooms(){
        return rooms;
    }

    public HostelName getName() {
        return name;
    }
}
