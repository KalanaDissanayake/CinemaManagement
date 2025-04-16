import java.util.Arrays;
import java.util.Scanner;

public class CinemaManagement {
    private static final int[][] seats = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
    };
    private static Ticket[] tickets = new Ticket[48];
    //To track the tickets in  tickets array.
    private static int count = 0;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        boolean bool = true;
        while (bool) {
            System.out.println();
            System.out.println("Welcome to The London Lumiere");

            System.out.println("\n" +
                    "----------------------------------\n" +
                    "Please select an option\t\n" +
                    "1)\tBuy a ticket\n" +
                    "2)\tCancel ticket\n" +
                    "3)\tSee seating plan\n" +
                    "4)\tFind first seat available\n" +
                    "5)\tPrint tickets information and total price\n" +
                    "6)\tSearch ticket\n" +
                    "7)\tSort tickets by price\n" +
                    "8)\tExit\n" +
                    "-----------------------------------\n" +
                    "Select option: ");
            String option = scanner.next();
            switch (option) {
                case "1":
                    buy_ticket();
                    break;
                case "2":
                    cancel_ticket();
                    break;
                case "3":
                    print_seating_area();
                    break;
                case "4":
                    find_first_available();
                    break;
                case "5":
                    print_tickets_info();
                    break;
                case "6":
                    search_ticket();
                    break;
                case "7":
                    sort_tickets();
                    break;
                case "8":
                    bool = false;
                    break;
                default:
                    System.out.println("Please select a valid option.");
            }
        }

    }

    public static int[] getInput() {
        int[] seatNumberArray = new int[2];
        while (true) {
            System.out.println("Enter row number: ");
            int rowNumber = scanner.nextInt() - 1;
            if (rowNumber >= 0 && rowNumber <= 2) {
                System.out.println("Valid row number.");
                seatNumberArray[0] = rowNumber;

                break;
            } else {
                System.out.println("Invalid row number. Please enter a valid row number.");
            }
        }

        while (true) {
            System.out.println("Enter seat number: ");
            int seatNumber = scanner.nextInt() - 1;
            if (seatNumber >= 0 && seatNumber <= 15) {
                System.out.println("Valid seatNumber.");
                seatNumberArray[1] = seatNumber;
                break;
            } else {
                System.out.println("Invalid seat number. Please enter a valid seat number.");
            }
        }
        return seatNumberArray;

    }


    public static Person createPerson() {
        System.out.println("Name: ");
        String name = scanner.next();
        System.out.println("Surname: ");
        String surname = scanner.next();
        System.out.println("Email: ");
        String email = scanner.next();
        return new Person(name, surname, email);
    }

    public static int seatPrice(int rowNumber) {
        if (rowNumber == 0) {
            return 12;
        } else if (rowNumber == 1) {
            return 10;
        } else if (rowNumber == 2) {
            return 8;
        }
        return 0;
    }

    public static void buy_ticket() {
        int[] seatNumberArray = getInput();
        int rowNumber = seatNumberArray[0];
        int seatNumber = seatNumberArray[1];

        if (isSeatAvailable(rowNumber, seatNumber)) {

            //Seat is booked.
            seats[rowNumber][seatNumber] = 1;

            //A new ticket is created.
            Ticket boughtTicket = new Ticket(seatNumberArray[0], seatNumberArray[1], seatPrice(seatNumberArray[0]), createPerson());

            //Adding created ticket into ticket array.
            tickets[count++] = boughtTicket;
            System.out.println("The seat has been booked.");
        }

    }

    public static void cancel_ticket() {
        int[] seatNumberArray = getInput();
        int rowNumber = seatNumberArray[0];
        int seatNumber = seatNumberArray[1];

        for (int i = 0; i < tickets.length; i++) {

            //If tiket is not null
            if (tickets[i] != null) {

                //If ticket is same the user input one
                if (tickets[i].getRow() == rowNumber && tickets[i].getSeat() == seatNumber) {
                    //set 0 to seat
                    seats[rowNumber][seatNumber] = 0;
                    //remove the ticket from array
                    System.out.println("The seat has been cancelled {" + (tickets[i].getRow() + 1) + "," + (tickets[i].getSeat() + 1) + "}");
                    tickets[i] = null;


                }
            }

        }

    }

    public static void search_ticket() {
        int[] seatsNumbers = getInput();
        int row = seatsNumbers[0];
        int seat = seatsNumbers[1];

        if (seats[row][seat] == 1) {
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i] != null) {
                    print_tickets_info();
                }

            }
        } else {
            System.out.println("This seat is available");
        }
    }

    public static void sort_tickets() {

    }

    public static void print_tickets_info() {
        int totalPrice = 0;
        for (int i = 0; i < tickets.length; i++) {
            if (tickets[i] != null) {
                totalPrice += tickets[i].getPrice();

                //Print ticket information
                tickets[i].ticketInformation();
                //Print person information
                tickets[i].getPerson().personInformation();
            }
        }
        System.out.println("Total price is " + totalPrice + "£");
    }

    public static boolean isSeatAvailable(int rowNumber, int seatNumber) {
        if (seats[rowNumber][seatNumber] == 1) {
            System.out.println("This seat is not available");
            return false;
        } else {
            return true;
        }

    }

    public static void print_seating_area() {
        System.out.println("\n" + "**********************************\n" + "*\t\t\tSCREEN\t\t\t\t *\n" + "**********************************");
        for (int rowNumber = 0; rowNumber < seats.length; rowNumber++) {
            for (int seatNumber = 0; seatNumber < seats[rowNumber].length; seatNumber++) {
                if (seatNumber == 8) {
                    System.out.print(" ");
                }
                if (seats[rowNumber][seatNumber] == 0) {

                    System.out.print("O ");
                } else {
                    System.out.print("X ");
                }


            }
            System.out.println();
        }

    }

    public static void find_first_available() {
        for (int rowNumber = 0; rowNumber < seats.length; rowNumber++   ) {
            for (int seatNumber = 0; seatNumber < seats[rowNumber].length; seatNumber++) {
                if (seats[rowNumber][seatNumber] == 0) {
                    System.out.println("First available is: " + "{" + (rowNumber + 1) + "," + (seatNumber + 1) + "}");
                    break;
                }
            }
            break;
        }
    }


}





