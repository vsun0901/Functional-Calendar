

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) {

        AllPeople every =startUP();
        int options;
        User user;
        Scanner scanner=new Scanner(System.in);
        System.out.println("Welcome");
        while (true){
            try{
                System.out.println("0: press 0 to login");
                System.out.println("1: press 1 to make a new account");

                options = scanner.nextInt();
                scanner.nextLine();

                if (options == 0) {

                    user = login(every);
                    break;
                } else if (options == 1) {

                    String email;
                    String password;
                    String role;

                    boolean work = true;
                    while (true) {
                        work = true;
                        System.out.println("please enter a email.");
                        email = scanner.nextLine();
                        System.out.println("please enter a password.");
                        password = scanner.nextLine();
                        System.out.println("are you a seller or customer?");
                        role = scanner.nextLine();
                        user = newUser(every, password, email, work, role);
                        if (user != null) {
                            every.getAll().add(user);
                            break;
                        } else {
                            System.out.println("Email already taken!");
                        }
                    }
                    break;
                } else {
                    System.out.println("Invalid input!");
                }
            }catch (java.util.InputMismatchException e){
                System.out.println("Invalid input!");
                scanner.nextLine();
            }
        }
        if (user instanceof Seller){
            sellermenu((Seller) user);
        } else{
            customermenu((Customer) user);
        }
        Shutdown(every);
        System.out.println("Goodbye.");
    }

    public static User newUser(AllPeople every,String password,String email,boolean work,String role){
        User user;
        for (int i = 0; i < every.getAll().size(); i++) {
            if (every.getAll().get(i).getEmail().equals(email)) {

                work = false;
                break;
            }
        }
        if (work) {
            if (role.equals("seller")) {
                user = new Seller(email, password, new ArrayList<Stores>());
            } else {
                user = new Customer(email,password,every.getAllStore());
            }
            return user;
        }else {
            return null;
        }
    }
    public static User login(AllPeople every){
        Scanner scanner = new Scanner(System.in);
        String email;
        String password;
        while (true) {

            System.out.println("please enter a email.");
            email = scanner.nextLine();
            System.out.println("please enter a password.");
            password = scanner.nextLine();

            for (int i = 0; i < every.getAll().size(); i++) {
                if (every.getAll().get(i).getEmail().equals(email)) {
                    if (every.getAll().get(i).getPassword().equals(password)) {
                        return every.getAll().get(i);
                    }
                }
            }
            System.out.println("User could not be found please try again.");
        }

    }
    public static void sellermenu(Seller user){
        int option;
        Scanner scanner=new Scanner(System.in);
        while (true) {
            try {

                System.out.println("0: see new pending appointments.");
                System.out.println("1: see aprroved appointments.");
                System.out.println("2: modify appointments.");
                System.out.println("3: import file.");
                System.out.println("4: shutdown and logout.");

                option = scanner.nextInt();
                scanner.nextLine();
                if (option == 0) {
                    user.ApproveCurrentPendingAppointments();

                } else if (option == 1) {
                    user.Aproved();


                } else if (option == 2) {
                    while (true) {

                        String fliter;
                        try {

                            System.out.println("0: edit by store.");
                            System.out.println("1: search by customer.");
                            System.out.println("2: return.");
                            option = scanner.nextInt();
                            scanner.nextLine();
                            if (option == 0) {

                                for (int i = 0; i < user.getStores().size(); i++) {
                                    System.out.println(i + ": " + user.getStores().get(i).getName());
                                }
                                System.out.println("what store?");
                                option = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println(user.getStores().get(option).getAppointments());
                                System.out.println("what appointment?");
                                int option2 = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("what is the new title time?");
                                String title = scanner.nextLine();
                                System.out.println("what is the new start time?");
                                int startTime = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("what is the new end time?");
                                int endTime = scanner.nextInt();
                                scanner.nextLine();
                                System.out.println("what is the new max attendees?");
                                int maxattendees = scanner.nextInt();
                                scanner.nextLine();
                                user.getStores().get(option).GetAppointments().get(option2).mod(title, endTime, startTime, maxattendees);


                            } else if (option == 1) {
                                System.out.println("enter a customer name.");
                                fliter = scanner.nextLine();
                                ArrayList<ApprovedAppointments> q = user.seachCust(fliter);
                                if (q.size() == 0) {
                                    System.out.println("no appointments found.");
                                } else {
                                    for (int i = 0; i < q.size(); i++)
                                        System.out.println(i + ": " + q.get(i).toString());
                                    System.out.println("witch would you like to edit?");
                                    option = scanner.nextInt();
                                    scanner.nextLine();
                                    //   try {

                                    boolean pending = false;
                                    System.out.println("do you want to cancel the appointment? true/false");
                                    boolean aproved = scanner.nextBoolean();
                                    scanner.nextLine();
                                    System.out.println("what is the new start tim?");
                                    int startTime = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("what is the new end time?");
                                    int endTime = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.println("what is the new max attendees?");
                                    int maxattendees = scanner.nextInt();
                                    scanner.nextLine();

                                    user.seachCustPartTwo(fliter, option).modify(pending, aproved, startTime, endTime, maxattendees);
                                    System.out.println("changes made.");

                                    // }catch (InvalidTypeException e){
                                    //     System.out.println("failed");
                                    //  }
                                }

                            } else if (option == 2) {
                                break;
                            } else {
                                System.out.println("Invaild input.");
                            }
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("a error has occurred please try again.");
                        } catch (java.util.InputMismatchException e) {
                            System.out.println("a error has occurred please try again.");
                            scanner.nextLine();
                        }
                    }


                } else if (option == 3) {
                    while (true) {
                        System.out.println("Path to file?");
                        String file = scanner.nextLine();

                        if (user.filePrintin(file)) {
                            break;
                        }
                        System.out.println("error try again.");
                    }


                } else if (option == 4) {
                    scanner.close();
                    return;

                } else {
                    System.out.println("invalid input.");
                }
            }catch (java.util.InputMismatchException e){
                System.out.println("invalid input.");
                scanner.nextLine();
            }
        }

    }
    public static void customermenu(User user){
        Scanner scanner = new Scanner(System.in);
        while (true) {

            int option;
            System.out.println("0: view appointments");
            System.out.println("1: make a appointment");
            System.out.println("2: return and logout.");
            try {
                option = scanner.nextInt();
                scanner.nextLine();

                if (option == 0) {
                    System.out.println(((Customer)user).AHHHH());

                } else if (option == 1) {
                    for(int i=0;i<((Customer)user).getAllStores().size();i++){
                        System.out.println(i+": "+((Customer)user).getAllStores().get(i).getName());

                    }
                    System.out.println("witch store?");
                    option= scanner.nextInt();
                    scanner.nextLine();
                    for(int i=0;i<((Customer)user).getAllStores().get(option).GetAppointments().size();i++){
                        System.out.println(i+": "+((Customer)user).getAllStores().get(option).GetAppointments().get(i));

                    }
                    System.out.println("what time window ?");
                   int  options= scanner.nextInt();
                    scanner.nextLine();
                    String name=user.getEmail();


                    ApprovedAppointments approvedAppointments =new ApprovedAppointments(((Customer)user).getAllStores().get(option).GetAppointments().get(options),name);
                    ((Customer)user).getAllStores().get(options).addApprovedAppointments(approvedAppointments);
                } else if (option == 2) {
                    scanner.close();
                    return;
                } else {
                    System.out.println("invaild input");
                }
            }catch (IncompatibleClassChangeError e){
                scanner.nextLine();
                System.out.println("invaild input");
            }
        }


    }
    public static void Shutdown(AllPeople every){
        String names="";
        String name="";
        String real="";
        String finalprint="";
        for (int i=0;i<every.getAll().size();i++){
            if(every.getAll().get(i) instanceof Seller) {
                name = every.getAll().get(i).getEmail() + "," + every.getAll().get(i).getPassword();
                names = names + name + "\n";

                for (int j = 0; j < ((Seller) every.getAll().get(i)).getStores().size(); j++) {
                    real = real + ((Seller) every.getAll().get(i)).getStores().get(j).getName();
                    for (int k = 0; k < ((Seller) every.getAll().get(i)).getStores().get(j).GetAppointments().size(); k++) {
                        real = real + "\n" + ((Seller) every.getAll().get(i)).getStores().get(j).GetAppointments().get(k).getRealStartTime() + "," + ((Seller) every.getAll().get(i)).getStores().get(j).GetAppointments().get(k).getRealEndTime() + "," + ((Seller) every.getAll().get(i)).getStores().get(j).GetAppointments().get(k).getMaxAttendees();

                    }
                    real = real + "\n" + "!" + "\n";
                }
                try {
                    new File("C:\\Users\\micha\\IdeaProjects\\untitled25\\src\\scr\\" + name + ".txt");
                    new File("C:\\Users\\micha\\IdeaProjects\\untitled25\\src\\scr\\" + name + "2.txt");
                    PrintWriter p = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\micha\\IdeaProjects\\untitled25\\src\\scr\\" + name + ".txt")));
                    p.write(real);
                    p.flush();
                    p.close();
                    PrintWriter w=new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\micha\\IdeaProjects\\untitled25\\src\\scr\\" + name + "2.txt")));
                    for (int c=0;c<((Seller)every.getAll().get(i)).getStores().size();c++){
                        for(int v=0;v<((Seller)every.getAll().get(i)).getStores().get(c).getCustomerAppointments().size();v++){
                            finalprint=finalprint+c+"<"+v+"|"+((Seller)every.getAll().get(i)).getStores().get(c).getCustomerAppointments().get(v).getInfo()+"\n";
                            w.write(finalprint);
                        }

                    }
                    finalprint="";
                    w.write(finalprint);
                    w.flush();
                    w.close();
                }catch (IOException e){

                }


            }else {
                name="*"+every.getAll().get(i).getEmail() + "," + every.getAll().get(i).getPassword();
                names = names + name + "\n";
            }

                try {
                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("C:\\Users\\micha\\IdeaProjects\\untitled25\\src\\scr\\Storage.txt")));
                    pw.write(names);
                    pw.flush();
                    pw.close();


                } catch (IOException e) {
                    e.printStackTrace();
                    return;
                }



            real="";
            }

        return ;

    }
    public static AllPeople startUP(){
       AllPeople all= new AllPeople();
        ArrayList<Appointments> f = new ArrayList<Appointments>();
        ArrayList<String> list = new ArrayList<>();
        BufferedReader bfr = null;

        try {
            FileReader fr = new FileReader("C:\\Users\\micha\\IdeaProjects\\untitled25\\src\\scr\\Storage.txt");
            bfr = new BufferedReader(fr);
            String line = bfr.readLine();

            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        if(list.get(0).equals("NEW")){

            AllPeople users = new AllPeople();
            return users;
        }

        String name="";
        String password="";
        for(int i=0;i<list.size();i++) {
            if (list.get(i).charAt(0)=='*'){
        }else{
                name = list.get(i).substring(0, list.get(i).indexOf(","));
                password = list.get(i).substring(list.get(i).indexOf(",") + 1);
                Seller sell = new Seller(name, password, new ArrayList<Stores>());
                sell.filePrintin("C:\\Users\\micha\\IdeaProjects\\untitled25\\src\\scr\\" + name + "," + password + ".txt");

                sell.filePrintin2("C:\\Users\\micha\\IdeaProjects\\untitled25\\src\\scr\\" + name + "," + password + "2.txt");

                for (int j = 0; j < sell.getStores().size(); j++) {
                    all.getAllStore().add(sell.getStores().get(j));
                }

                all.getAll().add(sell);
            }

        }
        for(int i=0;i<list.size();i++) {
            if (list.get(i).charAt(0)=='*') {
                String temp = list.get(i).replace("*", "");
                name = temp.substring(0,temp.indexOf(","));
                password = temp.substring(temp.indexOf(",")+1);
                Customer customer=new Customer(name,password,all.getAllStore());
                all.getAll().add(customer);
            }
            }



        return all;

    }




}
