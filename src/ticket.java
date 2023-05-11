import java.util.ArrayList;

public class ticket {

    // Defining private static variables "row" and "seat" of type int.
    private static int row;
    private static int seat;
    // Defining a private variable "price" of type int and an object of the class "person".
    private int price;

    private person person;

    // Defining a constructor for the class "ticket" with four parameters: row, seat, price, and person.
    public ticket(int row, int seat, int price, person person) {
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;

    }

    public static int getRow() { // Defining a static method "getRow()" to get the value of the "row" variable.

        return row; // Returning the value of the "row" variable.
    }

    // This defines a public static method called getSeat, which returns the value of the private static variable seat.
    public static int getSeat() {
        return seat;
    }

    // This defines a public method called getPrice, which returns the value of the private variable price.
    public int getPrice() {
        return price;
    }

    public person getPerson() {
        return person;
    }

    // This defines a public method called print_ticket,
    // which prints out the details of the ticket object and person details .
    public void print_ticket() {
                System.out.println("Person name: " + person.getName());
                System.out.println("Person surname: " + person.getSurname());
                System.out.println("Person email: " + person.getEmail());
                System.out.println("Row Number: " + this.row);
                System.out.println("Seat Number: " + this.seat);
                System.out.println("Ticket Price: " + this.price);
                System.out.println();
        }

    }



