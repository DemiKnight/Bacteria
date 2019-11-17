package uk.co.alexknight.bacteria;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        BacteriaSim sim = new BacteriaSim();

//        sim.inputBac();

        ArrayList<int[]> temp = new ArrayList<>();

//        for(int indexY = 0; indexY <= 100; indexY++)
//        {
//            for (int indexX = 0;indexX <= 100;indexX++)
//            {
//                sim.inputBac(new int[]{indexX, indexY});
//            }
//        }
        sim.inputBac(new int[][] {
                {1,2},
                {2,2},
                {3,2},
                {1000000003 ,1000000002},
                {1000000002 ,1000000002},
                {1000000001 ,1000000002}
        });
//
        sim.SimGenerations(1);
//
        sim.output();
    }
}
