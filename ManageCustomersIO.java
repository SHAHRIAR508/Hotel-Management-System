import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class ManageCustomersIO{
	
	
	public static void AddData(String id, String name, String age, String gender, String number) { 
	  
	    try {
            FileWriter fw = new FileWriter(new File("./customers.txt"), true);
            fw.write(name + ";" + id + ";" + age + ";" + gender + ";" + number + ";"+"\n");
            fw.flush();
            fw.close();

        } catch (Exception e) {
            System.out.println("File Not Found");
        }
    }
	
	
	public static void deleteData(String id) {
		
       try {
        List<String> lines = Files.readAllLines(Paths.get("./customers.txt"));
        List<String> updatedLines = new ArrayList<>();
        for (String line : lines) {
            String[] data = line.split(";");
            if (!data[1].equals(id)) {
                updatedLines.add(line);
            }
        }

        Path tempFile = Files.createTempFile("temp", ".txt");
        Files.write(tempFile, updatedLines, StandardCharsets.UTF_8);

        Files.delete(Paths.get("./customers.txt"));
        Files.move(tempFile, Paths.get("./customers.txt"), StandardCopyOption.REPLACE_EXISTING);
		
    } catch (Exception e) {
         System.out.println("File Not Found");
    }
}

	public static void UpdateData(String id, String name, String age, String gender, String number){
		
		deleteData(id);
		AddData(id, name, age, gender, number);
		
	}

	
	public static int GenerateUserId(){
        int count = 0;
        try{
            Scanner sc = new Scanner(new File("./customers.txt"));
           while(sc.hasNextLine()){
            sc.nextLine(); 
            count++;
        }
            sc.close();
    }
        catch(Exception e){
            System.out.println("File Not Found");
    }
	  while(checkUserExists(String.valueOf(count))){
		count++;	
	}
	
    return count;
    }

	
	public static boolean checkUserExists(String id){
		boolean found = false;
		try{
			Scanner sc = new Scanner(new File("./customers.txt"));
			while(sc.hasNextLine()){
				String data[] = sc.nextLine().split(";");
				if(id.equals(data[1])){
					found = true;
					break;
				}else if(id.equals(data[4])){
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
	
}