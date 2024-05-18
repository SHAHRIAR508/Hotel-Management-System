import java.util.*;
import java.io.*;

public class InvoiceIO{
	
	public static boolean checkRoomExists(String id){
		boolean found = false;
		try{
			Scanner sc = new Scanner(new File("./availableRooms.txt"));
			while(sc.hasNextLine()){
				String data[] = sc.nextLine().split(";");
				if(id.equals(data[0])){
					found = true;
					break;
				}
			}
			sc.close();
		}
		catch(Exception e){
			System.out.println("File Not Found");
		}
		return found;	
		
	}
	
	public static boolean checkRoomStatus(String id){
		
		boolean booked = false;
		try{
			Scanner sc = new Scanner(new File("./bookings.txt"));
			while(sc.hasNextLine()){
				String data[] = sc.nextLine().split(";");
				if(id.equals(data[0])){
					booked= true;
					break;                    
				
				}
			}
			sc.close();
		}
		catch(Exception e){
			System.out.println("File Not Found");
		}	
		return booked;
		
	}
	
	
	public static boolean checkPayment(String id) {
        boolean payment = false;

        try {
            Scanner sc = new Scanner(new File("./bookings.txt"));
            while (sc.hasNextLine()) {
                String data[] = sc.nextLine().split(";");
                if (id.equals(data[0])) {
                    String checkPayment = data[8];
                    if (checkPayment.equals("Paid")) {
                        payment = true;
                    }
                    break;
                }
            }
            sc.close();
        } catch (Exception e) {
            System.out.println("File Not Found");
        }

        return payment;
    }
	
	
}