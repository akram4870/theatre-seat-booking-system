//Mohamed Akram Gazali
//Java coursework 2023 SD2
//ID - W1956088 / 20221038


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

//Task1
public class theatre {
    static Scanner input = new Scanner(System.in); // Create a Scanner object to read user input
    static boolean condition = true;

    public static void main(String[] args) {
        //declare 3 arrays for each row
        int[] row1 = new int[12];
        int[] row2 = new int[16];
        int[] row3 = new int[20];

        ArrayList<ticket> ticketArrayList = new ArrayList<>();

        //Task 2
        while (condition == true) {
            System.out.println("\nWelcome the New Theatre: ");
            System.out.println("----------------------------------------------------");
            System.out.println("""
                    Please select an option: 
                    1) Buy a ticket
                    2) Print seating area
                    3) Cancel ticket
                    4) List available seats
                    5) Save to file
                    6) Load from file
                    7) Print ticket information and total price
                    8) Sort tickets by price
                        0) Quit""");
            System.out.println("----------------------------------------------------");


            System.out.println("Enter option: ");
            int option = input.nextInt(); //store the input to the option variable

            switch (option) {
                case 0:
                    System.out.println("Goodbye");
                    condition = false;
                    break;

                case 1:
                    buy_ticket(row1, row2, row3, ticketArrayList);
                    break;
                case 2:
                    System.out.println("     ***********\n     *  STAGE  *\n     ***********\n");
                    System.out.print("    ");  //indentation to print the space accordingly
                    print_seating_area(row1);
                    System.out.print("  ");
                    print_seating_area(row2);
                    print_seating_area(row3);
                    break;
                case 3:
                    cancel_ticket(row1, row2, row3, ticketArrayList);
                    break;
                case 4:
                    System.out.print("Seats available in row 1: ");
                    show_available(row1);
                    System.out.print("Seats available in row 2: ");
                    show_available(row2);
                    System.out.print("Seats available in row 3: ");
                    show_available(row3);
                    break;
                case 5:
                    save(row1, row2, row3);
                    break;
                case 6:
                    load(row1, row2, row3);
                    break;
                case 7:
                    show_ticket_info(ticketArrayList);
                    break;
                case 8:
                    sort_tickets(ticketArrayList);
                    System.out.println("The array is successfully sorted");
                    System.out.println("Sorted Ticket Information: ");
                    show_ticket_info(ticketArrayList); //use case 7 print method to print the sorted list
                    break;
                default:
                    System.out.println("Invalid option!\n please try again");

            }
        }
    }

    //task 3
    public static void buy_ticket(int[] row1, int[] row2, int[] row3, ArrayList<ticket> arrayList) {
        Scanner input = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Please enter the row number: ");
                int row_number = input.nextInt();

                int[] row; // Declare an integer array variable to represent the selected row
                int max_seat_number; // Declare an integer variable to represent the maximum seat number in the selected row
                switch (row_number) { // Assign the appropriate row and maximum seat number based on the entered row number
                    case 1:
                        row = row1;
                        max_seat_number = row.length;
                        break;
                    case 2:
                        row = row2;
                        max_seat_number = row.length;
                        break;
                    case 3:
                        row = row3;
                        max_seat_number = row.length;
                        break;
                    default:
                        System.out.println("Invalid row number entered, please try again");
                        continue;
                }

                System.out.println("Enter seat number (1-" + max_seat_number + "): ");
                int seat_number = input.nextInt();

                if (seat_number < 1 || seat_number > max_seat_number) {
                    System.out.println("Invalid seat number , please try again");
                    continue;
                } else if (row[seat_number - 1] == 1) {
                    System.out.println("Seat is already booked");
                    continue;
                }

                System.out.println("Enter first name: ");
                String name = input.next();
                System.out.println("Enter surname: ");
                String surname = input.next();
                System.out.println("Enter email: ");
                String email = input.next();
                System.out.println("Enter price: ");
                int price = input.nextInt();

                person Person = new person(name, surname, email); // Create a new "person" object with the entered name, surname, and email
                ticket Ticket = new ticket(row_number, seat_number, price, Person);
                arrayList.add(Ticket); // add the detials taken from the user to the arraylist ticketDetails
                row[seat_number - 1] = 1;

                System.out.println("Thank you for your purchase. Your row number is "+ row_number + " and seat number is " + seat_number);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid characters. Please try again.");
                input.next(); //to stop the infinity loop
            }
        }
    }

    //Task 4
    // Static method that takes an integer array "seatingArray" representing a seating area and prints it to the console.
    public static void print_seating_area(int[] seatingArray) {
        for (int seatIndex = 0; seatIndex < seatingArray.length; seatIndex++) {
            if (seatIndex == seatingArray.length / 2) { // If the index "seatIndex" is equal to the middle index of the array, print a space to separate the seating area.
                System.out.print(" ");
            }
            if (seatingArray[seatIndex] == 0) {
                System.out.print("O");
            } else {
                System.out.print("X");
            }
        }
        System.out.println();
    }

    //Task 5
    // This method cancels a ticket by taking input from the user for the row and seat number of the ticket to be cancelled
    public static void cancel_ticket(int[] row1, int[] row2, int[] row3, ArrayList<ticket> arrayList) {
        Scanner input = new Scanner(System.in);

        System.out.println("Please enter the row number: ");
        int row_number = input.nextInt();

        int[] selectedRow = null; // It initializes an integer array variable to null
        int maxSeatNumber = 0; //and an integer variable to 0 to store the selected row and maximum seat number

        switch (row_number) { // It then uses a switch statement to set the selectedRow and maxSeatNumber variables based on the user's row input
            case 1:
                selectedRow = row1;
                maxSeatNumber = 12;
                break;
            case 2:
                selectedRow = row2;
                maxSeatNumber = 16;
                break;
            case 3:
                selectedRow = row3;
                maxSeatNumber = 20;
                break;
            default:
                System.out.println("Invalid row number");
                return;
        }

        System.out.println("Enter seat number (1-" + maxSeatNumber + "): ");
        int seat_number = input.nextInt();

        if (seat_number < 1 || seat_number > maxSeatNumber) {
            System.out.println("Invalid seat number");
            return;
        }

        if (selectedRow[seat_number - 1] == 0) {
            System.out.println("Seat is already available");
            return;
        }

        for (int i = 0; i < arrayList.size(); i++) {
            ticket Ticket = arrayList.get(i);
            if (Ticket.getRow() == row_number && Ticket.getSeat() == seat_number) {
                arrayList.remove(i);
            }
        }

        selectedRow[seat_number - 1] = 0;
        System.out.println("Your cancellation is successfully ");
    }

    //Task 6
    //Displays the available seats in the seatingArray.
    public static void show_available(int[] seatingArray) {
        for (int i = 0; i < seatingArray.length; i++) {
            if (seatingArray[i] == 0) {
                System.out.print((i + 1) + ", ");
            }
        }
        System.out.println();

    }
    //Task 7
    //This method saves the current state of the seating arrangement to a file named "seatdetails.txt"
    public static void save(int[] row1, int[] row2, int[] row3) {
        try {
            // This line creates a FileWriter object that will write data to a file named "seatdetails.txt".
            // The "try" block is used because this operation can throw an IOException.
            FileWriter writer = new FileWriter("seatdetails.txt");
            for (int i = 0; i < row1.length; i++) {
                writer.write(row1[i] + " ");
            }
            writer.write("\n");

            for (int i = 0; i < row2.length; i++) {
                writer.write(row2[i] + " ");
            }
            writer.write("\n");

            for (int i = 0; i < row3.length; i++) {
                writer.write(row3[i] + " ");
            }
            writer.write("\n");

            writer.close();
            System.out.println("The data has been saved to seatdetails.txt file.");
        } catch (IOException e) {
            System.out.println("Error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
//Task 8
    public static void load(int[] row1, int[] row2, int[] row3) {
        try {
            File file = new File("seatdetails.txt");
            Scanner scanner = new Scanner(file);

            for (int i = 0; i < row1.length; i++) {
                row1[i] = Integer.parseInt(scanner.next());
            }

            for (int i = 0; i < row2.length; i++) {
                row2[i] = Integer.parseInt(scanner.next());
            }

            for (int i = 0; i < row3.length; i++) {
                row3[i] = Integer.parseInt(scanner.next());
            }

            scanner.close();
            System.out.println("The data has been loaded from seatdetails.txt file");
        } catch (FileNotFoundException e) {
            System.out.println("Error occurred while loading the file");
            e.printStackTrace();
        }


    }
    //Task 13
    public static void show_ticket_info(ArrayList<ticket> arrayList){
        System.out.println("Ticket details");
        double totalPrice = 0;
        for (ticket i : arrayList) {
            System.out.println("-------------------------------------------------");
            totalPrice+= i.getPrice();
            i.print_ticket();
        }
        System.out.println("The sum of the prices of all tickets is £ " + totalPrice);
    }

    //Task 14
    // This method sorts an ArrayList of tickets based on ticket price in ascending order
    public static void sort_tickets(ArrayList<ticket> ticketArrayList) {
        // Loop through each ticket in the list
        for (int i = 0; i < ticketArrayList.size() - 1; i++) {
            int minIndex = i; // Set the minimum index to the current index
            for (int j = i + 1; j < ticketArrayList.size(); j++) { // Loop through the rest of the list to find the minimum ticket price
                // Check if the current ticket price is less than the minimum ticket price
                if (ticketArrayList.get(j).getPrice() < ticketArrayList.get(minIndex).getPrice()) {
                    minIndex = j; // If so, set the minimum index to the current index
                }
            }
            // Swap the current ticket with the minimum ticket
            //https://stackoverflow.com/questions/40118518/how-two-swap-two-elements-in-a-array-list-without-using-collections-swap-method
            ticket temp = ticketArrayList.get(i);
            ticketArrayList.set(i, ticketArrayList.get(minIndex));
            ticketArrayList.set(minIndex, temp);
        }
        System.out.println();
    }

}
