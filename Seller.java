
import com.sun.jdi.InvalidTypeException;

import java.io.BufferedReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


/*
    TODO probably find a way if given a empty storelist or null for the code not to commit itself *and the customer search method
    One of the two kinds of users

    Important methods:
     toStringStores chains a lot of toStirng methods to print stores name and the list of Appointments that can be made there
     Aprove prints to terminal all Approved appointments at all stores connted to seller NOTE: gross way of doing this probably needs to change in the future
     AproveCurrentPendingAppointments begins the process of Approving the pending AprovedAppointments in all stores connted to this seller

     getStores,newStore do what name says

     REAL IMPORTANT methods
        filePrintin using a file auto makes stores and Appointments EX: exampletextimport.txt NOTE: try catch needs to put in
        AproveCurrentPendingAppointments goes though each store and pending appointment and askes if Seller want to approve at the end sets all AprovedAppointments object pending bool to false NOTE: like approve this is gross way
 */


public class Seller extends User {
    private ArrayList<Stores> stores;
    public Seller(String email,String password,ArrayList<Stores> stores) {
        super(email,password,role.SELLER);
        this.stores = stores;
    }

    public String toStringStores() {
        //prints the name of a store and the Appointments with it right now repeats until out of stores tied to the seller
        String yup = "";
        for (int i = 0; i < stores.size(); i++) {
            yup = yup + this.stores.get(i).getName() + "\n"+this.stores.get(i).getAppointments() + "\n";
        }
        return yup;
    }

    public void ApproveCurrentPendingAppointments(){
        String check = "";
        ArrayList<ApprovedAppointments> checker = new ArrayList();
        Scanner scanner = new Scanner(System.in);


        for(int i = 0; i < this.stores.size(); i++) {

            checker = this.stores.get(i).getPendingAppointments();
            if(checker.size() != 0) {
                for (int j = 0; j < checker.size(); j++) {
                    while (true) {
                        System.out.println("Do you want to approve this appointment?\nY/N");
                        System.out.println(checker.get(j));
                        check = scanner.nextLine();
                        if (check.equals("Y")) {

                            this.stores.get(i).getPendingAppointments().get(j).setApproved(true);

                            break;
                        } else if (check.equals("N")) {

                            break;
                        } else {
                            System.out.println("Invalid Input");
                        }
                    }
                }
            } else {
                System.out.println("No pending Appointments at "+this.stores.get(i).getName());
            }
            this.stores.get(i).clearPend();
        }

    }
    public void Aproved() {
        for(int i = 0; i < this.stores.size(); i++) {
            System.out.println(this.stores.get(i).getName());
            System.out.println(this.stores.get(i).getAprovedAppointments());
        }
    }

    public ArrayList<Stores> getStores() {
        return stores;
    }

    public void newStore(Stores newStores) {
        this.stores.add(newStores);
    }

    public ArrayList<ApprovedAppointments> seachCust(String fliter){
        ArrayList<ApprovedAppointments> p = new ArrayList<>();
        for(int i = 0; i < this.stores.size(); i++) {
            for(int j=0;j<this.stores.get(i).getAprovedAppointments().size();i++)
                if(this.stores.get(i).getAprovedAppointments().get(j).getCustomers().contains(fliter)){
                    p.add(this.stores.get(i).getAprovedAppointments().get(j));
            }

        }
        return p;
    }
    public ApprovedAppointments seachCustPartTwo(String fliter,int number){
        ArrayList<ApprovedAppointments> p = new ArrayList<>();
        for(int i = 0; i < this.stores.size(); i++) {
            for(int j=0;j<this.stores.get(i).getAprovedAppointments().size();i++)
                if(this.stores.get(i).getAprovedAppointments().get(j).getCustomers().contains(fliter)){
                    p.add(this.stores.get(i).getAprovedAppointments().get(j));
                }

        }
        return p.get(number);
    }




    public boolean filePrintin(String fileName) {


        // cvs file format
        // name
        // starttime , endtime , maxpeople (repeat for more appointments )
        // !
        // repeat

        ArrayList<Appointments> f = new ArrayList<Appointments>();

        ArrayList<String> list = new ArrayList<>();
        BufferedReader bfr = null;
        try {
            FileReader fr = new FileReader(fileName);
            bfr = new BufferedReader(fr);
            String line = bfr.readLine();

            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
        } catch (FileNotFoundException e){
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        String name = "";
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).equals("!")) {

                Stores stores1 = null;
                try {
                    stores1 = new Stores(new ArrayList<Appointments>(),name);
                } catch (IOException e) {
                    return false;
                }
                for (int r = 0; r < f.size(); r++) {
                    stores1.addAppointment(f.get(r));
                }
                f.clear();
                this.stores.add(stores1);

                try {
                    i++;
                } catch (IndexOutOfBoundsException e) {
                    return true;
                }
            }

            for(int p = 0; p < list.size(); p++) {
                try {
                    Integer.parseInt(list.get(i).replace(",",""));
                } catch (NumberFormatException e) {
                    name=list.get(i);
                    i++;
                    p=list.size()+2;
                } catch (IndexOutOfBoundsException e) {
                    return true;
                }
            }

            int end = 0;
            int start = 0;
            int max = 0;
            String line = list.get(i) + ",!";
            int linecounter = 0;
            int j = 0;
            for (int k = 0; k < line.length(); k++){
                if (line.charAt(k) == ',') {
                    switch (j) {
                        case 0:
                            start = Integer.parseInt(line.substring(linecounter, k));
                            linecounter = k;
                            j++;
                            break;
                        case 1:
                            end = Integer.parseInt(line.substring(linecounter + 1, k));
                            linecounter = k;
                            j++;
                            break;
                        case 2:
                            max = Integer.parseInt(line.substring(linecounter + 1, k));
                            linecounter = k;
                            j++;
                            break;
                    }
                }
            }
            Appointments appointments=new Appointments("",start,end,max);
            f.add(appointments);

        }


    return true;
    }

    public boolean filePrintin2(String fileName) {


        // cvs file format
        // name
        // starttime , endtime , maxpeople (repeat for more appointments )
        // !
        // repeat

        ArrayList<Appointments> f = new ArrayList<Appointments>();

        ArrayList<String> list = new ArrayList<>();
        BufferedReader bfr = null;
        try {
            FileReader fr = new FileReader(fileName);
            bfr = new BufferedReader(fr);
            String line = bfr.readLine();

            while (line != null) {
                list.add(line);
                line = bfr.readLine();
            }
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (bfr != null) {
                try {
                    bfr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        int store;
        int Start;
        int end;
        int max;
        String name;
        boolean approve;
        boolean pending;
        for (int i=0;i<list.size();i++){

             store=Integer.parseInt(list.get(i).substring(0,list.get(i).indexOf("<")));
             Start=Integer.parseInt(list.get(i).substring(list.get(i).indexOf("|")+1,list.get(i).indexOf("/")));
             end=Integer.parseInt(list.get(i).substring(list.get(i).indexOf("/")+1,list.get(i).indexOf("\\")));
             max=Integer.parseInt(list.get(i).substring(list.get(i).indexOf("\\")+1,list.get(i).indexOf("?")));
             name=list.get(i).substring(list.get(i).indexOf("?")+1,list.get(i).indexOf("}"));
             approve=Boolean.parseBoolean(list.get(i).substring(list.get(i).indexOf("}")+1,list.get(i).indexOf("{")));
             pending=Boolean.parseBoolean(list.get(i).substring(list.get(i).indexOf("}")+1,list.get(i).indexOf("{")));
             ApprovedAppointments approvedAppointments=new ApprovedAppointments("",Start,end,max,name);
             approvedAppointments.setApproved(approve);
             approvedAppointments.setPending(pending);
             this.stores.get(store).getCustomerAppointments().add(approvedAppointments);

         }




        return true;
    }
}
