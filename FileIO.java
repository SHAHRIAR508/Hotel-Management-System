
import java.util.*;
import java.io.*;

public class FileIO{
	public static boolean checkUser(String uname, String upass){
		boolean valid = false;
		try{
			Scanner sc = new Scanner(new File("./users.txt"));
			while(sc.hasNextLine()){
				String data[] = sc.nextLine().split(";");
				if(uname.equals(data[0]) && upass.equals(data[1])){
					valid = true;
					break;
				}
			}
			sc.close();
		}
		catch(Exception e){
			System.out.println("File Not Found");
		}
		return valid;
	}
	
	public static void addUser(String uname, String upass){
		try{
			FileWriter fw = new FileWriter(new File("./users.txt"),true);
			fw.write(uname+";"+upass+"\n");
			fw.flush();
			fw.close();
		}
		catch(Exception e){
			System.out.println("File Not Found");
		}
	}
	
	
}