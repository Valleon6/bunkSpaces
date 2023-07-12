package com.valleon.bunkspaces.repositories;

import com.valleon.bunkspaces.exception.HostelManagementException;
import com.valleon.bunkspaces.exception.NoAvailableBedSpaceException;
import com.valleon.bunkspaces.models.BedSpace;
import com.valleon.bunkspaces.models.Gender;
import com.valleon.bunkspaces.models.Hostel;
import com.valleon.bunkspaces.models.HostelName;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;

public class HostelRepository {

    private static final Hostel[] hostels = new Hostel[4];
    private final Queue<BedSpace> availableBedSpacesForMales = new PriorityQueue<>(160);
    private final Queue<BedSpace> availableBedSpacesForFemales = new PriorityQueue<>(160);


    static {
        hostels[0] = new Hostel("HALL1", Gender.FEMALE);
        hostels[1] = new Hostel("HALL2", Gender.FEMALE);
        hostels[2] = new Hostel("HALL3", Gender.MALE);
        hostels[3] = new Hostel("HALL4", Gender.MALE);

    }

    public HostelRepository(){
        findAllAvailableBedSpaces();
    }

    private void findAllAvailableBedSpaces() {
        for(Hostel hostel : hostels){
            if (hostel.getHostelCategory() == Gender.FEMALE){
                availableBedSpacesForFemales.addAll(hostel.findAllAvailableBedSpace());
            }
            else{
                availableBedSpacesForMales.addAll(hostel.findAllAvailableBedSpace());
            }
        }
    }

    public static Hostel[] getHostels(){
        return hostels;
    }

    public Queue<BedSpace> getAvailableBedSpacesForMales(){
        return availableBedSpacesForMales;
    }

    public Queue<BedSpace> getAvailableBedSpacesForFemales(){
        return availableBedSpacesForFemales;
    }

    public int getTotalNumberOfAvailableBedSpaces(){
        return availableBedSpacesForFemales.size() + availableBedSpacesForMales.size();
    }

    public BedSpace returnAvailableFemaleSpace() throws NoAvailableBedSpaceException{
        try{
            return availableBedSpacesForFemales.poll();
        }
        catch (NoSuchElementException exception){
            throw new NoAvailableBedSpaceException(exception.getMessage());
        }
    }

    public BedSpace returnAvailableMaleSpace() throws NoAvailableBedSpaceException{
        try {
            return availableBedSpacesForMales.poll();
        }catch (NoSuchElementException exception){
            throw new NoAvailableBedSpaceException(exception.getMessage());
        }
    }

    public Hostel[] getAllHostels(){
        return hostels;
    }

    public Hostel findHostelByName(String hostelName) throws HostelManagementException{
        for(Hostel hostel : hostels){
            if(hostel.getName() == HostelName.valueOf(hostelName)){
                return hostel;
            }
        }
        throw new HostelManagementException("specified hostel name does not exist");
    }


}
