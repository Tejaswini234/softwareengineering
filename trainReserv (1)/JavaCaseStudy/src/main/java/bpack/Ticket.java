package bpack;

import DBManager.dbcon;
import mainUIpack.Main;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private static int counter;
    private String date;
    private Train train = new Train();
    private Main main= new Main();
    private String pnr="";
    File tic= new File("C:\\Users\\user126\\Desktop\\workspace\\aegon12.txt");

    private Map<Passenger, Double> plist = new HashMap<Passenger,Double>();




    public Ticket(String date, Train train) {
        this.date = date;
        this.train = train;
    }

    public Ticket() {

    }

    public int getCounter()
    {
        int count=0;
        try {
//            File f1= new File(tic.getParent()+"\\counter.txt");
//            if(!f1.isFile())
//            f1.createNewFile();
//
            FileReader fr=new FileReader("resource\\counter.txt");
            int i;
            while((i=fr.read())!= -1)
            {
                char ch=(char)i;
                count= (count*10) + Integer.parseInt(Character.toString(ch));
            }
            System.out.println("Count= "+count);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        counter=count;
        return count;

    }

    public void setDate(String date) {
        this.date = date;
    }

    private String generatePNR()
    {

        pnr = Character.toString( train.getSource().charAt(0)) + Character.toString( train.getDestination().charAt(0));
        String[] pnrArr= date.split("/");
        for(String s:pnrArr)
        {
            pnr=pnr+s;
        }
        pnr= pnr+getCounter();
        return pnr;

    }
    private double calcPassengerFare(Passenger p)
    {

        double ticketPrice= train.getTicketPrice();

        //if(p.gender == 'F' || p.age>=60)
        if(p.getGender() =='F')
            ticketPrice = ticketPrice * 0.75;
        else if (p.getAge() >= 60)
            ticketPrice = ticketPrice * 0.6;
        else if(p.getAge()<=12)
            ticketPrice = ticketPrice * 0.5;


        return  ticketPrice;

    }
    public void addPassenger(String name,int age, char gender)
    {

        Passenger p=new Passenger(name,age,gender);
        double fare = calcPassengerFare(p);
        plist.put( new Passenger(name,age,gender), fare);

    }
    private double calcTotalTicketPrice()
    {
        double price=0;

        for(Double fr: plist.values())
        {
            price+= fr;
        }
        return price;

    }
    private StringBuilder generateTicket()
    {

        StringBuilder ticketString =new StringBuilder();
        // System.out.println("pnr = "+generatePNR() );

        ticketString.append("\n  PNR          :"+generatePNR());
        ticketString.append("\n  Train no     :"+ train.getTrainNo());
        ticketString.append("\n  Train Name   :"+train.getTrainName());
        ticketString.append("\n  From         :"+train.getSource());
        ticketString.append("\n  To           :"+train.getDestination());
        ticketString.append("\n  Travel Date  :"+date);
        ticketString.append("\n   ");
        ticketString.append("\n  Passengers:");
        ticketString.append("\n  Name\t\t\tAge\t\t\tGender\t\t\tFare");


        for(Passenger p: plist.keySet())
        {
            ticketString.append ("\n"+p.getName()+"\t\t\t"+p.getAge()+"\t\t\t"+p.getGender()+"\t\t\t"+ plist.get(p));
            //System.out.println(p+" " + plist.get(p));
        }
        ticketString.append("\n  Total Price :"+calcTotalTicketPrice());
        return ticketString;

    }

    public void writeTicket()
    {

        StringBuilder str=  generateTicket();
        String ticketString= str.toString();
        String[] result= ticketString.split("\n");

        try {
            //tic.createNewFile();
//            FileWriter wr= new FileWriter(tic.getParent()+"\\riya.txt");
            FileWriter cwr = new FileWriter("resource\\counter.txt");
            String Tname = pnr+".txt";
            File fticket= new File("tickets\\"+Tname);
//            BufferedWriter bufferedWriter = new BufferedWriter(wr);
//            bufferedWriter.write("HI ");
            if(fticket.isFile())
            {
                System.out.println("The ticket is already booked... Try with another ticket");
            }
            else
            {
//                FileWriter twriter= new FileWriter(tic.getParent()+"\\ticket\\"+Tname);
//                twriter.write(ticketString);
//                twriter.close();
                PrintWriter pr = new PrintWriter("tickets\\"+Tname);
                for(String s: result)
                {
                    pr.println(s);

                }
                pr.close();

            }

//            wr.write(ticketString);
            //wr.write("Hello");
//            wr.close();
            //int ct= getCounter();
            // counter= ct+1;
            int ct= counter+1;
//            System.out.println("Counter = "+counter);
            cwr.write(""+ct);
            cwr.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
