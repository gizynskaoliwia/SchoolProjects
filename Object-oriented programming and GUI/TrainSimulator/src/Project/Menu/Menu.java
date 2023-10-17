package Project.Menu;

import Project.Cars.*;
import Project.Models.Station;
import Project.Models.Train;
import Project.Other.Logic;
import Project.Route.Route;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    protected ArrayList<Route> routes =new ArrayList<>();
    protected Logic logic = new Logic();
    protected Scanner scanner = new Scanner(System.in);
    protected int i1 = 0;

    public void showInConsole() {
        Random random = new Random();
        for(int i = 0; i < 20; i++)
        {
            Station st1 = new Station("W"+i);
            Station st2 = new Station("P"+i);
            Station st3 = new Station("L"+i);
            Station st4 = new Station("G"+i);
            Station st5 = new Station("V"+i);

            st1.addStation(st2);
            st2.addStation(st1);

            st2.addStation(st3);
            st3.addStation(st2);

            st3.addStation(st4);
            st4.addStation(st3);

            st4.addStation(st5);
            st5.addStation(st4);

            Train t1 = new Train(5,500,10,"TRAIN_" + i);
            t1.setStart(st1);
            t1.setDestination(st2);

            routes.add(new Route(t1));

            logic.addTrain(t1);
            logic.addStation(st1);
            logic.addStation(st2);
            logic.addStation(st3);
            logic.addStation(st4);
            logic.addStation(st5);

            for(int j = 0; j < 10;j++)
            {
                t1.addCar(new CarCargo("s"+i, random.nextBoolean(), random.nextInt(50), random.nextInt(50), random.nextInt(50)));
            }

            if(i%5 == 0)
            {
                Train t2 = new Train(5,500,10,"TRAIN_" + i);
                t2.setStart(st2);
                t2.setDestination(st4);
                routes.add(new Route(t2));
                logic.addTrain(t2);
                for(int j = 0; j < 10;j++)
                {
                    t2.addCar(new CarCargo("s"+j, random.nextBoolean(), random.nextInt(50), random.nextInt(50), random.nextInt(50)));
                }

            }


        }

        while (true) {
            System.out.println("1. Run simulation");
            System.out.println("2. Create new car");
            System.out.println("3. Create new station");
            System.out.println("4. Bind stations");
            System.out.println("5. Bind car to train");
            System.out.println("6. Show trains");
            System.out.println("7. Show stations");
            System.out.println("8. Create new train");
            System.out.println("9. Close program");

            System.out.print("Choose option: ");

            i1 = Integer.parseInt(scanner.nextLine());

            if (i1 == 1) {
                logic.run(routes);
            }
            if (i1 == 2) {
                System.out.println("Type weight:");
                int weight = Integer.parseInt(scanner.nextLine());

                System.out.println("Type sender:");
                String sender = scanner.nextLine();

                System.out.println("Security (y/n):");
                String security = scanner.nextLine();

                System.out.println("Car kinds:");
                System.out.println(Arrays.stream(Car.CarKinds.values()).toList());

                System.out.println("Type car kind:");
                Car.CarKinds choosen_type = Car.CarKinds.valueOf(scanner.nextLine());

                Car car = null;
                boolean security_b = security.equals("y");

                switch (choosen_type) {
                    case PORTEL_POSTAL: {
                        int maxBaggageNumber, maxDeliveryNumber;

                        System.out.println("Max baggage number:");
                        maxBaggageNumber = Integer.parseInt(scanner.nextLine());

                        System.out.println("Max delivery number:");
                        maxDeliveryNumber = Integer.parseInt(scanner.nextLine());

                        car = new CarPorterPostal(sender, security_b, weight, maxBaggageNumber, maxDeliveryNumber);
                        break;
                    }
                    case CARGO: {
                        int width, height, temp;
                        String content;
                        System.out.println("Width:");
                        width = Integer.parseInt(scanner.nextLine());

                        System.out.println("Height:");
                        height = Integer.parseInt(scanner.nextLine());

                        System.out.println("Temperature:");
                        temp = Integer.parseInt(scanner.nextLine());

                        System.out.println("Content:");
                        content = scanner.nextLine();

                        car = new CarRefrigirator(sender, security_b, weight, width, height, temp, content);
                        break;
                    }
                }

                if(car== null)
                {
                    car = new CarRefrigirator(sender, security_b, weight, 22, 22, 22, "CONTENT");
                }

                logic.addCar(car);
                System.out.println("Created new car id = " + car.getId());
            }
            if (i1 == 3) {
                System.out.println("Type station name:");
                String stationName = scanner.nextLine();
                Station station = new Station(stationName);
                logic.addStation(station);
                System.out.println("Created station with name = " + station.getStationName());
            }
            if (i1 == 4) {
                String name1, name2;
                System.out.println("Type first station name:");
                name1 = scanner.nextLine();
                System.out.println("Type second station name:");
                name2 = scanner.nextLine();

                Station station1 = logic.getStationByName(name1);
                Station station2 = logic.getStationByName(name2);

                station1.addStation(station2);

                station2.addStation(station1);

                System.out.println("Binded " + name1 + " to " + name2);
            }
            if (i1 == 5) {
                System.out.println("Type train id:");
                int id = Integer.parseInt(scanner.nextLine());

                System.out.println("Type cargo id:");
                int car_id = Integer.parseInt(scanner.nextLine());

                logic.useCar(id, car_id);
                System.out.println("Binded car to train!");
            }
            if (i1 == 6) {
                logic.showTrains();
            }
            if (i1 == 7) {
                logic.showStations();
            }
            if (i1 == 8) {
                System.out.println("Type name:");
                String name = scanner.nextLine();

                System.out.println("Type speed:");
                int speed = Integer.parseInt(scanner.nextLine());

                System.out.println("Type max car number:");
                int maxcars = Integer.parseInt(scanner.nextLine());

                System.out.println("Type max cargo weight:");
                int maxcargoweight = Integer.parseInt(scanner.nextLine());

                Train train = new Train(maxcars, speed, maxcargoweight, name);

                logic.addTrain(train);

                System.out.println("Created train id = " + train.getUniqueNumber());

                String ids1, ids2;
                System.out.println("Type start station name:");
                ids1 = scanner.nextLine();
                System.out.println("Type destination station name:");
                ids2 = scanner.nextLine();

                Station sts1 = logic.getStationByName(ids1);
                Station sts2 = logic.getStationByName(ids2);

                train.setStart(sts1);
                train.setDestination(sts2);
            }
            if (i1 == 9) {
                System.out.println("Exit application");
                System.exit(1);
            }
        }
    }
}
