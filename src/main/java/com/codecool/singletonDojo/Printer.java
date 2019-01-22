package com.codecool.singletonDojo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Printer {

    private int id; // ID of the printer. Unique.
    private LocalTime busyEndTime;
    private static List<Printer> instances = new ArrayList<>();
    private static int count;

    private Printer(int id) {
        this.id = id;
        this.busyEndTime = LocalTime.now();
    }

    // Prints out the given String
    public void print(String toPrint) {
        // Its not needed to actually print with a printer in this exercise
        System.out.println("Printer " + id + " is printing.");
        busyEndTime = LocalTime.now().plusSeconds(5);
    }


    // Returns true if the printer is ready to print now.
    public boolean isAvailable() {
        return LocalTime.now().isAfter(busyEndTime);
    }

    public static Printer getInstance() {
        if (instances.size() == 0) {
            for (int i = 0; i < 10; i++) {
                count++;
                instances.add(new Printer(count));
            }
        }
        List<Printer> availablePrinters = new ArrayList<>();
        for (Printer printer: instances) {
            if (printer.isAvailable()) {
                availablePrinters.add(printer);
            }
        }
        if (availablePrinters.size() > 0) {
            return availablePrinters.get(ThreadLocalRandom.current().nextInt(0, availablePrinters.size()));
        } else {
            System.out.println("Everybody busy");
            return instances.get(ThreadLocalRandom.current().nextInt(0, instances.size()));
        }


    }
}
