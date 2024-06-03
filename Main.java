import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");

        int userAccount = 0;
        while (!(userAccount == 1 || userAccount == 2)) {
            System.out.println("1. Login\n" +
                    "2. Create New Account");
            userAccount = scanner.nextInt();
            scanner.nextLine();
            switch (userAccount) {
                case 1:
                    System.out.println("1. Seller\n" +
                            "2. Customer");
                    int userRole = scanner.nextInt();


                    break;
                case 2:
                    System.out.println("1. Seller\n" +
                            "2. Customer");
                    int usersRole = scanner.nextInt();
                    break;
                default:
                    System.out.println("Please enter a valid number!");

            }
        }



//        Seller s = new Seller("jpalkar@purdue.edu", "password", "janhavi");
//        Store store = new Store("MangoMango", s);
//        Store store2 = new Store("Apple", s);
//        Store store3 = new Store("Google", s);
//        Customer w = new Customer("janhaviipalkar@gmail.com", "abcdef");
    }
}
