import java.util.Scanner;

public class CommandMain {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if(line.equalsIgnoreCase("exit")){
				break;
			}
			System.out.println(line);
		}
	}
}
