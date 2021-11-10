package DBManager;

import bpack.Train;

import java.sql.*;
import java.util.ArrayList;

public class dbcon {
    public ArrayList<Train> tr=new ArrayList<>();

    public void datacon() throws ClassNotFoundException, SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/trainsdb", "root",
                "Serengeti2948$$");
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("Select * from trains");
        System.out.println("Train_No | Train_Name | Boarding | Destination | Ticket_Price");
        System.out.println("---------------------------------------------------------------------------");

        while (rs.next()) {
            tr.add(new Train(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5)));
        }
    }
    public void printtraindetails()
    {
        System.out.println(tr.get(0).getTrainNo());
        for (int i=0;i<tr.size();i++){
            System.out.println(tr.get(i).getTrainNo()+" | "+tr.get(i).getTrainName()+" | "+tr.get(i).getSource()+" | "+tr.get(i).getDestination()+" | "+tr.get(i).getTicketPrice());
        }
    }


//    public ArrayList<Train> getTr() {
//        return tr;
//    }

//    public void printEle(){
//        for (Train i:dbcon.tr)
//    }
    public boolean tr_no(int tn){
        for (Train i:tr){
            if(i.getTrainNo()==tn){
                return true;
            }
        }
        return false;
    }
    public int tr_index(int tn){
        int in=0;
        for (Train i:tr){
            if(i.getTrainNo()==tn){
                in=tr.indexOf(i);
            }
        }
        return in;
    }
}
