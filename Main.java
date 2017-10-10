package com.company;
import java.util.Objects;
import java.util.Scanner;



public class Main {


    public static void main(String[] args) {

        System.out.println("Insert the number of floors ");
        Scanner input = new Scanner(System.in);
        int floors = input.nextInt();
        floor_class[] Floor = new floor_class[floors];
        int tot = 0;


        for (int i = 0; i < floors; i++) {

            Floor[i] = new floor_class();                              //to eliminate NULL
            Floor[i].number_floor = i;
            System.out.println("\nFor the floor " + i + " : ");

            System.out.println("Insert the total number of spots ");
            Floor[i].total_spots = input.nextInt();
            System.out.println("Insert the number of cars ");
            Floor[i].number_cars = input.nextInt();

            System.out.println("Insert the number of tracks ");
            Floor[i].number_tracks = input.nextInt();
            if (Floor[i].number_tracks > 0) {
                System.out.println("Insert the number of little, medium and big tracks ");       //like LittleVan, Autobus, Tir
                Floor[i].number_tracks_little = input.nextInt();
                Floor[i].number_tracks_medium = input.nextInt();
                Floor[i].number_tracks_big = input.nextInt();
            }

            System.out.println("Insert the number of bikes ");
            Floor[i].number_bikes = input.nextInt();
            System.out.println("Insert the number of exits ");
            Floor[i].exit = input.nextInt();
            System.out.println("Say me if it has an elevator (true or false) ");
            Floor[i].elevator = input.next();
            System.out.println("Say me if it has an electric charger (true or false) ");
            Floor[i].electric_charger = input.next();
            System.out.println("Insert the height in cm of the floor ");
            Floor[i].height = input.nextDouble();
            System.out.println("Insert the width in cm of the floor ");
            Floor[i].width = input.nextDouble();

            tot += Floor[i].total_spots;
            //System.out.println(tot);
        }




        int j = 0;
        int c = 0;
        int t = 0;
        int b = 0;
        String decision;
        car_class[] cars = new car_class[10000];                          //MAX of car for my default
        track_class[] tracks = new track_class[10000];
        bike_class[] bikes = new bike_class[10000];



        while (j < tot) {
            System.out.println("\nFREE SPOTS: " + (tot-j));
            System.out.println("Car, Track or Bike? ");
            String word = input.next();
            int control = 0;



            //ENTER-EXIT A CAR
            if (Objects.equals(word, "car") || Objects.equals(word, "Car")) {
                System.out.println("In or out?");
                decision = input.next();

                if(Objects.equals(decision, "In") || Objects.equals(decision, "in")) {
                    cars[c] = new car_class();
                    cars[c].vehicle = word;
                    System.out.println("Insert the model of the car");
                    cars[c].model = input.next();
                    System.out.println("Insert the fuel of the car");
                    cars[c].fuel = input.next();
                    System.out.println("Insert the number of the wheels of the car");
                    cars[c].number_wheels = input.nextInt();
                    System.out.println("Insert the number of the people in that car");
                    cars[c].number_people = input.nextInt();
                    System.out.println("There are disabled people? (true or false)");
                    cars[c].disabled_people = input.next();

                    System.out.println("Is it an electric car? (true or false)");
                    cars[c].electric_machine = input.next();
                    System.out.println("Is it a supercar? (true or false)");
                    cars[c].supercar = input.next();
                    System.out.println("What kind of traction it has?");
                    cars[c].traction = input.next();

                    for (int i = 0; i < floors; i++) {
                        if (Floor[i].number_cars > 0) {
                            if (Objects.equals(cars[c].disabled_people, "true")) {
                                if (Objects.equals(Floor[i].elevator, "true")) {
                                    Floor[i].number_cars --;
                                    break;
                                }
                                else if (floors == 1)
                                    control = 100;
                                else i++;
                            }
                            else {
                                Floor[i].number_cars --;
                                break;
                            }
                        } else if (i == floors - 1)
                            control = 100;
                        else i++;
                    }                                                                            //Same process if we want to controll the ELECTRICAL CHARGER

                    if (control == 100) {
                        System.out.println("You can't put your vehicle in this Parking");
                        continue;
                    }

                    c++;
                    j++;
                    continue;
                }

                else{
                    System.out.println("What was your floor?");
                    int free_floor = input.nextInt();
                    Floor[free_floor].number_cars ++;
                    j--;

                }
            }



            //ENTER A TRACK
            if (Objects.equals(word, "track") || Objects.equals(word, "Track")) {
                System.out.println("In or out?");
                decision = input.next();

                if(Objects.equals(decision, "In") || Objects.equals(decision, "in")) {
                    tracks[t] = new track_class();
                    tracks[t].vehicle = word;
                    System.out.println("Insert the model of the track");
                    tracks[t].model = input.next();
                    System.out.println("Insert the fuel of the track");
                    tracks[t].fuel = input.next();
                    System.out.println("Insert the number of the wheels of the track");
                    tracks[t].number_wheels = input.nextInt();
                    System.out.println("Insert the number of the people in that track");
                    tracks[t].number_people = input.nextInt();
                    System.out.println("There are disabled people? (true or false)");
                    tracks[t].disabled_people = input.next();

                    System.out.println("What kind of track is? Little, medium or big?");
                    tracks[t].dimension = input.next();
                    System.out.println("Insert the height in cm");
                    tracks[t].height = input.nextDouble();
                    System.out.println("Insert the width in cm");
                    tracks[t].width = input.nextDouble();
                    System.out.println("It has a protruding load? (true or false)");
                    tracks[t].protruding_load = input.next();


                    for (int i = 0; i < floors; i++) {

                        control = 0;
                        if (Objects.equals(tracks[t].protruding_load, "true")) {
                            control = 100;
                            break;
                        }

                        if (tracks[t].height > Floor[i].height) {
                            control = 100;
                            break;
                        }

                        if (tracks[t].width > Floor[i].width) {
                            control = 100;
                            break;
                        }

                        if (Objects.equals(tracks[t].dimension, "little")) {
                            if (Floor[i].number_tracks_little > 0) {
                                Floor[i].number_tracks_little --;
                                break;
                            }
                            else if (i == floors - 1)
                                control = 100;
                            else i++;
                        }

                        if (Objects.equals(tracks[t].dimension, "medium")) {
                            if (Floor[i].number_tracks_medium > 0) {
                                Floor[i].number_tracks_medium --;
                                break;
                            }
                            else if (i == floors - 1)
                                control = 100;
                            else i++;
                        }

                        if (Objects.equals(tracks[t].dimension, "big")) {
                            if (Floor[i].number_tracks_big > 0) {
                                Floor[i].number_tracks_big --;
                                break;
                            }
                            else if (i == floors - 1)
                                control = 100;
                            else i++;
                        }

                    }

                    if (control == 100) {
                        System.out.println("You can't put your vehicle in this Parking");
                        continue;
                    }

                    t++;
                    j++;
                    continue;
                }

                else{
                    System.out.println("What was your floor?");
                    int free_floor = input.nextInt();
                    System.out.println("What kind of track do you have? Little, medium or big?");
                    String free_track = input.next();
                    if(Objects.equals(free_track, "little"))
                        Floor[free_floor].number_tracks_little ++;
                    if(Objects.equals(free_track, "medium"))
                        Floor[free_floor].number_tracks_medium ++;
                    if(Objects.equals(free_track, "big"))
                        Floor[free_floor].number_tracks_big ++;
                    j--;
                }
            }



            //ENTER-EXIT A BIKE
            if (Objects.equals(word, "bike") || Objects.equals(word, "Bike")) {
                System.out.println("In or out?");
                decision = input.next();

                if(Objects.equals(decision, "In") || Objects.equals(decision, "in")) {
                    bikes[b] = new bike_class();
                    bikes[b].vehicle = word;
                    System.out.println("Insert the model of the bike");
                    bikes[b].model = input.next();
                    System.out.println("Insert the fuel of the bike");
                    bikes[b].fuel = input.next();
                    System.out.println("Insert the number of the wheels of the bike");
                    bikes[b].number_wheels = input.nextInt();
                    System.out.println("Insert the number of the people in that bike");
                    bikes[b].number_people = input.nextInt();
                    System.out.println("There are disabled people? (true or false)");
                    bikes[b].disabled_people = input.next();

                    System.out.println("Is it a motorbike? (true or false)");
                    bikes[b].motorbike = input.next();
                    System.out.println("Is it an electric machine? (true or false)");
                    bikes[b].electric_bike = input.next();


                    for (int i = 0; i < floors; i++) {
                        if (Floor[i].number_bikes > 0) {
                            if (Objects.equals(bikes[b].electric_bike, "true")) {
                                if (Objects.equals(Floor[i].electric_charger, "true")) {
                                    Floor[i].number_bikes --;
                                    break;
                                }
                                else if (floors == 1)
                                    control = 100;
                                else i++;
                            }
                            else {
                                Floor[i].number_bikes --;
                                break;
                            }
                        } else if (i == floors - 1)
                            control = 100;
                        else i++;
                    }                                                                          //Same process if we want to controll the DISABLED PEOPLE

                    if (control == 100) {
                        System.out.println("You can't put your vehicle in this Parking");
                        continue;
                    }

                    b++;
                    j++;
                    continue;
                }

                else{
                    System.out.println("What was your floor?");
                    int free_floor = input.nextInt();
                    Floor[free_floor].number_bikes ++;
                    j--;
                }
            }







        }

        System.out.println("PARKING FULL");

    }
}
