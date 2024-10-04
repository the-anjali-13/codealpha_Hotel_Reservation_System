import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
public class Hotel_reservation_System {

    public static void main(String args[])
    {
        Hotelrooms hotelroom = new  Hotelrooms();

        Hotel_reservation_System obj = new Hotel_reservation_System();
        obj.welcome(hotelroom);
    }

    public void welcome(Hotelrooms hotelroom) {

        System.out.println("\n\n*** Hotel Resevation System *** \n");
        System.out.println("press the following number and let us know what you want:");
        System.out.println("1.) Search Available Rooms ");
        System.out.println("2.) Make Reservation ");
        System.out.println("3.) Booking Details");
        System.out.println("4.) Exit\n");

        Scanner scan = new Scanner(System.in);
        int option = scan.nextInt();

        switch (option) {
            case 1:
                System.out.println("\nTHE ROOMS ARE :");
                hotelroom.searchroom();
                welcome(hotelroom);
                break;
            case 2:
                hotelroom.roomreserve();
                welcome(hotelroom);
                break;
            case 3:
                userdetails ud = new userdetails();
                ud.userdetails();
                welcome(hotelroom);
                break;
            case 4:
                System.out.println("----THANK YOU FOR VISITING US----\n");
                break;
            default:
                System.out.println("ERROR : PLEASE ENTER A VALID NUMBER\n");
                welcome(hotelroom);
                break;
        }
    }
}

class rooms
{
    int roomnum;
    String roomtype;
    int roomprice;
    private boolean isavailabe;

    rooms(int roomnum , String roomtype , int roomprice , boolean isavailabe)
    {
        this.roomnum =roomnum;
        this.roomtype=roomtype;
        this.roomprice =roomprice;
        this.isavailabe=isavailabe;
    }

    int getnum()
    {
        return roomnum;
    }
    String gettype()
    {
        return roomtype;
    }
    int getprice()
    {
        return roomprice;
    }
    boolean getavailabe()
    {
        return isavailabe;
    }

    void setAvailability(boolean availability) {
        this.isavailabe = availability;
    }

}

class Hotelrooms
{
     ArrayList<rooms> roomlist;
    Hotelrooms()
    {
        roomlist =new ArrayList<>();
        roomlist.add( new rooms(101,"double",30,true));
        roomlist.add( new rooms(102,"Single",20,true));
        roomlist.add( new rooms(103,"Suite",10,true));

    }



    void searchroom()
    {
        for(rooms one : roomlist)
        {
            System.out.println("ROOM NUMBER :"+one.getnum()+ "|| ROOM TYPE :"+one.gettype()+"|| ROOM PRICE :"+one.getprice()+"$"+" || Availability :"+ ((one.getavailabe()==true)?"Available":"Not Available"));
        }
    }

    void roomreserve()
    {
        System.out.print("ENTER THE NUMBER OF ROOM YOU WANT TO RESERVE :");
        Scanner scan = new Scanner(System.in);
        int roomnumberbyuser =scan.nextInt();

        boolean roomflag = false;
        for(rooms itr : roomlist)
        {
            if(itr.getnum()==roomnumberbyuser)
            {
                roomflag = true;
                if(itr.getavailabe()==true)
                {
                    System.out.println("YOUR DESIRED ROOM NUMBER IS AVAILABLE WE ARE RESERVING IT FOR YOU .\n");
                    reserve res = new reserve(roomnumberbyuser);
                    //boolean bool =res.reserve(roomnumberbyuser);
                    itr.setAvailability(false);

                }
                else
                {
                    System.out.println("\nTHIS ROOM IS ALREADY RESERVED.\n");
                }
                break;
            }
        }

        if(!roomflag)
        {
            System.out.println("\nROOM NUMBER IS NOT FOUND\n");
        }
    }
}



class userdetails
{
    UUID userid;
    String username;
    int userphone;
    String useremail;
    int stayin_roomnum;

    userdetails(){}

    userdetails(UUID userid,String username, int userphone, String useremail,  int stayin_roomnum)
    {
        this.userid =userid;
        this.username = username;
        this.userphone = userphone;
        this.useremail = useremail;
        this.stayin_roomnum = stayin_roomnum;
    }

    UUID getuserid()
    {
        return userid;
    }
    String getusername()
    {
        return username;
    }

    int getuserphone()
    {
        return userphone;
    }

    String getuseremail()
    {
        return useremail;
    }

    int getstayin_roomnum()
    {
        return stayin_roomnum;
    }

    void userdetails()
    {
        System.out.println("1.) FOR VIEWING YOUR DETAILS");
        System.out.println("2.) FOR VIEWING DETAILS FOR ALL");

        Scanner scan = new Scanner(System.in);
        int ip = scan.nextInt();

        reserve reserveobj = new reserve();

        if(ip==1)
        {
            scan.nextLine();
            System.out.print("ENTER YOUR ID : ");
            String idip = scan.nextLine();

            boolean userFound = false;
            for(userdetails det : reserveobj.userlist)
            {
                if(det.getuserid().toString().equals(idip))
                {
                    System.out.println("\nName: " + det.getusername());
                    System.out.println("Phone: " + det.getuserphone());
                    System.out.println("Email: " + det.getuseremail());
                    System.out.println("Room Number: " + det.getstayin_roomnum());
                    userFound = true;
                    break;
                }

            }
            if(!userFound)
            {
                    System.out.println("\nUSER NOT FOUND!!!\n");
            }
        }

        else if(ip==2)
        {
            System.out.print("\nONLY HOTEL STAFF CAN ACCESS IT . PLEASE ENTER PASSWORD : ");
            int pass = scan.nextInt();

            if(pass==12345)
            {
                System.out.println("\nDETAILS OF ALL USER ARE :");
                for(userdetails det : reserveobj.userlist)
                {
                    System.out.println("Name: " + det.getusername()+"| Phone: " + det.getuserphone()+"| Email: " + det.getuseremail()+"|  Room Number: " + det.getstayin_roomnum());

                }
            }
            else{
                System.out.println("\nWRONG PASSWORD !!\n");
            }
        }

        else
        {
            System.out.println("\nYOUR INPUT WAS NOT VALID !!!\n");
        }
    }

}
class reserve
{
   reserve(){}
    static ArrayList<userdetails> userlist = new ArrayList<>();

     reserve(int num)
    {

        Scanner scan = new Scanner(System.in);

        System.out.println("\nENTER THE DETAILS FOR MAKE ROOM RESERVED :");

        UUID id = UUID.randomUUID();
        System.out.println("YOUR UNIQUE ID IS : "+id);

        System.out.print("ENTER YOUR NAME :");
        String name = scan.nextLine();

        System.out.print("ENTER YOUR PHONE NUMBER :");
        int phone = scan.nextInt();
        scan.nextLine();

        System.out.print("ENTER YOUR EMAIL :");
        String email = scan.nextLine();

        userlist.add(new userdetails(id,name,phone,email,num));
        System.out.println("\nTHIS ROOM IS RESERVED FOR YOU !!!\n");


    }

}
