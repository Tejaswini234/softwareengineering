package mainUIpack;

import DBManager.dbcon;
import bpack.Passenger;
import bpack.Ticket;
import bpack.Train;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // write your code here
        Scanner sc = new Scanner(System.in);
        dbcon dbc=new dbcon();
        dbc.datacon();
        dbc.printtraindetails();
        System.out.println("Enter Train Number");

        int t_no = sc.nextInt();
        int indx=0;
        Train tr1=new Train();
        if(dbc.tr_no(t_no)) {
            indx=dbc.tr_index(t_no);
            System.out.println("This train is available.");
            Train main_tr=new Train(dbc.tr.get(indx).getTrainNo(),dbc.tr.get(indx).getTrainName(),dbc.tr.get(indx).getSource(),dbc.tr.get(indx).getDestination(),dbc.tr.get(indx).getTicketPrice());
            tr1=main_tr;
        }
        else
            System.out.println("The mentioned train is not available.");
        System.out.println(tr1.getTrainName());


        System.out.println("Enter Travel Date");
        String sDate1 = sc.next();
        Ticket ticket = new Ticket(sDate1, tr1);
        ticket.setDate(sDate1);

        System.out.println("Enter your Passenger Count:");
        int personCount = sc.nextInt();
        //Passenger passengers[] = new Passenger[];
        ArrayList<Passenger> plist = new ArrayList<Passenger>();
        while (personCount != 0) {

            System.out.println("Enter Passenger Name ");
            String p_name = sc.next();
            System.out.println("Enter Age");
            int age = sc.nextInt();
            System.out.println("EnterGender (M/F)");
            String gen = sc.next();
            char gender = gen.charAt(0);
            //plist.add(new Passenger(p_name,age,gender));
            ticket.addPassenger(p_name, age, gender);

            personCount--;

        }
        ticket.writeTicket();
    }
}
