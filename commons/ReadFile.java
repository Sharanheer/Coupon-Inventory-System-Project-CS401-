package couponInventorySystem.commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
	private Scanner sc;
	
	public void openFile(String file_name) throws FileNotFoundException{
		//try {
			
			sc = new Scanner(new File(file_name));
		/*} catch (FileNotFoundException e) {
			 TODO Auto-generated catch block
			System.out.println("No file with name emp.txt found");
		}*/
	}
	
	public Coupon readFile() {
		Coupon e = new Coupon(sc.next(),sc.next(),Double.parseDouble(sc.next()),Double.parseDouble(sc.next()),Integer.parseInt(sc.next()),sc.next());
		
		return e;
	}
	
	public void closeFile() {
		sc.close();
	}
	
	public boolean hasData() {
		boolean b = false;
		if(sc.hasNext())
			b = true;
		return b;
	}
}
