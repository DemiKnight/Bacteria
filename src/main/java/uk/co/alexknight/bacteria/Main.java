package uk.co.alexknight.bacteria;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        BacteriaSim sim = new BacteriaSim();

//        sim.inputBac();

        sim.inputBac(new int[][] {
                {3,2},
//                {1000000002 ,1000000002},
                {1,2},
//                {1000000003 ,1000000002},
                {2,2},
//                {1000000001 ,1000000002}
        });
//
        sim.SimGenerations(1);
//
        sim.output();
    }
}
