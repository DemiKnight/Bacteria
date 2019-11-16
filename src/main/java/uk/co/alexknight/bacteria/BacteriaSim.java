package uk.co.alexknight.bacteria;

import java.util.*;

public class BacteriaSim
{
    //Main bacteria that are currently alive.
    private ArrayList<int[]> bacteriaStore = new ArrayList<>();

    //Used during a generation to save potential locations for bacteria.
    private ArrayList<int[]> reproductionCheck = new ArrayList<>();

    public void input()
    {
        Scanner inputScanner = new Scanner(System.in);

        while (!inputScanner.hasNext("end"))
        {
            bacteriaStore.add
                    (
                        Arrays.stream
                            (
                                inputScanner.next().split(",") //Split into String[].
                            )
                            .mapToInt(Integer::parseInt).toArray() //Convert to int[] location.
                    );
        }
        sort();
    }

    public void inputBac(int[][] inputArray)
    {
        Collections.addAll(bacteriaStore, inputArray);
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
        ArrayList<int[]> removeLIst = new ArrayList<>();

        //Bacteria that will reproduce to become the new generation.
        ArrayList<int[]> newBacteria = new ArrayList<>();

        //Kill off
        for (int[] selectedBac: bacteriaStore)
        {
            if (! willSurvive(selectedBac[0], selectedBac[1], false))
            {
                removeLIst.add(selectedBac);
            }
        }

        //Reproduction
        for (int[] selectedPLace : reproductionCheck)
        {
            if (willSurvive(selectedPLace[0], selectedPLace[1], true))
                newBacteria.add(selectedPLace);
        }

        bacteriaStore.removeAll(removeLIst); //Remove dead Cells

        bacteriaStore.addAll(newBacteria); //Add reproduced Cells

        reproductionCheck.clear(); //Fresh for next generation
        sort();
    }

    private boolean willSurvive(int xLoc, int yLoc, boolean reproduction)
    {
        boolean returnVal = false; //Most cases cause bacteria to die.

        switch (countNeighbours(xLoc, yLoc, reproduction))
        {
            case 2:
                returnVal = !reproduction; //If looking to create a new cell with only 2 neighbours will die/fail.
                break;
            case 3: // WIll live
                returnVal = true;
                break;
        }

        return returnVal;
    }

    private short countNeighbours(int xLoc, int yLoc, boolean reproduction)
    {
        short count = 0;

        int locationIndex = bacteriaStore.indexOf(new int[]{xLoc, yLoc});

        //Any limits the potential existing bacteria to those within a feasible radius of potential bacteria.
        int beginningOfSearch = (locationIndex - 4 <= 0                     ? 0                   : locationIndex - 4);
        int endOfSearch       = (locationIndex + 4 >= bacteriaStore.size() ? bacteriaStore.size() : locationIndex + 4);

        //Array of all location to check for bacteria
        int[][] test = new int[][]
                {
                        {xLoc-1, yLoc-1}, {xLoc, yLoc-1}, {xLoc+1, yLoc-1},
                        {xLoc-1, yLoc  }, /*{xLoc, yLoc,*/ { xLoc+1, yLoc },
                        {xLoc-1, yLoc+1}, {xLoc, yLoc+1}, {xLoc+1, yLoc+1},
                };

        //Check if bacteria exists, add to temp check list otherwise
        for (int[] currentlyLivingBac : bacteriaStore.subList(beginningOfSearch, endOfSearch)) //Can be revised to only the 8 needed
        {
            for(int[] locationsAround : test)
            {
                if (currentlyLivingBac[0] == locationsAround[0] && currentlyLivingBac[1] == locationsAround[1])
                    count++; //Bacteria is present around bacteria.
                else
                    if
                    (
                        ! reproduction && //If checking for reproduction don't re-add bacteria.
                        ! compareLocations(reproductionCheck, locationsAround) && //Check that it hasn't already been selected for reproduction
                        ! compareLocations(bacteriaStore    , locationsAround)    //Check if bacteria isn't already present.
                    )
                        reproductionCheck.add(locationsAround); //Potential spot for reproduction,
            }
        }

        return count;
    }

    public void output()
    {
        //Revise
        bacteriaStore.forEach((bac) -> System.out.println(bac[0] + "," + bac[1]));
        System.out.println("end");
    }

    public ArrayList<int[]> outputBac()
    {
        return bacteriaStore;
    }


    private boolean storeContainsLocation(int[] location)
    {
        return compareLocations(bacteriaStore, location);
    }

    /**
     * Lazy but saves space.
     */
    private boolean compareLocations(int[] loc1, int[] loc2)
    {
        return loc1[0] == loc2[0] && loc1[1] == loc2[1];
    }


    private boolean compareLocations(Collection<int[]> locations, int[] loc2)
    {
        //Compare's all locations for a matching set.
        for (int[] selectedLoc : locations)
            if (compareLocations(selectedLoc, loc2)) return true;
        return false;
    }

    private void sort()
    {
        //Sort distance from origin
        bacteriaStore.sort(Comparator.comparingInt(loc -> loc[0] + loc[1]));
    }
}
