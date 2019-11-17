package uk.co.alexknight.bacteria;

import java.util.*;

public class BacteriaSim
{
    public static class Location
    {
        private final int yLocation;
        private final int xLocation;

        /**
         *
         * @param xyLocations First needs to be x Location & Second needs to be Y Location.
         */
        public Location(String[] xyLocations)
        {
            this.xLocation = Integer.parseInt(xyLocations[0]);
            this.yLocation = Integer.parseInt(xyLocations[1]);
        }

        public Location(int xLocation, int yLocation)
        {
            this.xLocation = xLocation;
            this.yLocation = yLocation;
        }

        public int getyLocation() {
            return yLocation;
        }

        public int getxLocation() {
            return xLocation;
        }

        @Override
        public boolean equals(Object newObj)
        {
            if (newObj instanceof Location)
                return ((Location) newObj).xLocation == xLocation && ((Location) newObj).yLocation == yLocation;

            return false;
        }

        @Override
        public int hashCode() {
            return (xLocation + "" + yLocation).hashCode();
        }

        @Override
        public String toString() {
            return xLocation + "," + yLocation;
        }
    }

    //Main bacteria that are currently alive.
    private HashSet<Location> bacStore = new HashSet<>();

    //Used during a generation to save potential locations for bacteria.
    private HashSet<Location> reproductionCheck = new HashSet<>();

    public void input()
    {
        Scanner inputScanner = new Scanner(System.in);

        while (!inputScanner.hasNext("end"))
        {
            bacStore.add
                    (
                        new Location(inputScanner.next().split(","))//Split into String[] (x,y) & create location.
                    );
        }
    }

    public void inputBac(Location[] intputArray)
    {
        Collections.addAll(bacStore, intputArray);
    }


    public void SimGenerations(int numberOfGens)
    {
        for (int index = 0; index < numberOfGens; index++)
        {
            SimGeneration();
        }
    }

    public void SimGeneration()
    {
        //Bacteria that will become dead at the end of this generation.
        ArrayList<Location> removeLIst = new ArrayList<>();

        //Bacteria that will reproduce to become the new generation.
        ArrayList<Location> newBacteria = new ArrayList<>();

        //Kill off currently living bacteria, will take effect after reproduction
        for (Location selectedBac: bacStore)
        {
            if (! willSurvive(selectedBac, false))
            {
                removeLIst.add(selectedBac);
            }
        }

        //Reproduction
        //With the
        for (Location selectedPLace : reproductionCheck)
        {
            if (willSurvive(selectedPLace, true))
                newBacteria.add(selectedPLace);
        }

        bacStore.removeAll(removeLIst); //Remove dead Cells

        bacStore.addAll(newBacteria); //Add reproduced Cells

        reproductionCheck.clear(); //Fresh for next generation
    }

    private boolean willSurvive(Location inputLoc, boolean reproduction)
    {
        boolean returnVal = false; //Most cases cause bacteria to die.

        switch (countNeighbours(inputLoc, reproduction))
        {
            case 2:
                returnVal = !reproduction; //If looking to create a new cell with only 2 neighbours will die/fail.
                break;
            case 3: // Will survive.
                returnVal = true;
                break;
        }

        return returnVal;
    }

    private short countNeighbours(Location inputLoc, boolean reproduction)
    {
        short count = 0;


        // [1,2,3]
        // [4,5,6]
        // [7,8,9]

        //All locations surrounding the bacteria, some already occupied others created.
        Location[] locationSurrounding = {
            new Location(inputLoc.xLocation-1, inputLoc.yLocation-1), //1
            new Location(inputLoc.xLocation, inputLoc.yLocation-1),             //2
            new Location(inputLoc.xLocation+1, inputLoc.yLocation-1), //3

            new Location(inputLoc.xLocation-1, inputLoc.yLocation),             //4
                //5 Not needed, is the existing location.
            new Location(inputLoc.xLocation+1, inputLoc.yLocation),             //6

            new Location(inputLoc.xLocation-1, inputLoc.yLocation+1), //7
            new Location(inputLoc.xLocation, inputLoc.yLocation+1),             //8
            new Location(inputLoc.xLocation+1, inputLoc.yLocation+1), //9
        };

        //Array of all location to check for bacteria.
        for (Location locationIndex: locationSurrounding )
        {
            if (bacStore.contains(locationIndex))
            {
                count++;
            }else if (! reproduction ) reproductionCheck.add(locationIndex); //Don't want to over-reproduce
        }

        return count;
    }

    public void output()
    {
        //Print output
//        bacStore.forEach((loc) -> System.out.println(loc.toString()));
        for (Location loc: bacStore)
        {
            System.out.println(loc.toString());
        }
        System.out.println("end");
    }
}
