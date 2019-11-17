package uk.co.alexknight.bacteria;

import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        BacteriaSim sim = new BacteriaSim();


        sim.input();
//        sim.inputBac();

//        sim.inputBac(new BacteriaSim.Location[] {
//                new BacteriaSim.Location(1,2),
//                new BacteriaSim.Location(2,2),
//                new BacteriaSim.Location(3,2),
//                new BacteriaSim.Location(1000000003 ,1000000002),
//                new BacteriaSim.Location(1000000002 ,1000000002),
//                new BacteriaSim.Location(1000000001 ,1000000002)
//        });
//
        sim.SimGenerations(1);
//
        sim.output();
    }
}
