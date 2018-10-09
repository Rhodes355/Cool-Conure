import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Project01 {
	public static void main(String[] args) throws IOException {
		ArrayList<String> title = new ArrayList<String>();
		ArrayList<String> type = new ArrayList<String>();
		ArrayList<Double> price = new ArrayList<Double>();
		ArrayList<Integer> quantity = new ArrayList<Integer>();
		int count = 0;
		Scanner in = new Scanner(System.in);
		String fileName = "";
		
		System.out.print("Enter database filename: ");
		fileName = in.nextLine();
		
		// src/test
		count = readFile(fileName, title, type, price, quantity);
		
		output(count, title, type, price, quantity);
		
		in.close();
	}
	
	public static int findLargestQuantity(ArrayList<Integer> quantity){
		int index = 0;
		int max = 0;
		
		for (int i = 0; i < quantity.size();i++){
			if (quantity.get(i) > max){
				max = quantity.get(i);
				index = i;
			}
		}
		return index;
	}
	
	public static int findSmallestQuantity(ArrayList<Integer> quantity){
		int index = 0;
		int min = quantity.get(0);
		
		for (int i = 0; i < quantity.size();i++){
			if (quantity.get(i) < min){
				min = quantity.get(i);
				index = i;
			}
		}
		return index;
	}
	
	public static int findHighestPrice(ArrayList<Double> price, ArrayList<Integer> quantity){
		int index = 0;
		double max = 0;
		
		for (int i = 0; i < price.size();i++){
			if ((price.get(i) * quantity.get(i)) > max){
				max = (price.get(i) * quantity.get(i));
				index = i;
			}
		}
		
		return index;
	}
	
	public static int findLowestPrice(ArrayList<Double> price, ArrayList<Integer> quantity){
		int index = 0;
		double min = 0;
		min = (price.get(0) * quantity.get(0));
		
		for (int i = 0; i < price.size();i++){
			if ((price.get(i) * quantity.get(i)) < min){
				min = (price.get(i) * quantity.get(i));
				index = i;
			}
		}
		
		return index;
	}
	
	public static void output(int count, ArrayList<String> title, ArrayList<String> type, 
			ArrayList<Double> price, ArrayList<Integer> quantity){
		int index = 0;
		double cost = 0;
		
		System.out.println("Product Summary Report");
		System.out.println("----------------------------------");
		for (int i = 0;i<count;i++){
			System.out.println("Title: " + title.get(i));
			System.out.println("Product Type: " + type.get(i));
			System.out.println("Price: " + price.get(i));
			System.out.println("Quantity: " + quantity.get(i) + "\n");
		}
		System.out.println("----------------------------------");
		System.out.println("Total products in database: " + count);
		
		index = findLargestQuantity(quantity);
		System.out.println("Largest quantity item: " + title.get(index) + 
				" (" + type.get(index) + ")");
		
		index = findHighestPrice(price, quantity);
		cost = (price.get(index) * quantity.get(index));
		System.out.println("Highest total dollar item: " + title.get(index) + 
				" ($" + cost + ")");
		
		index = findSmallestQuantity(quantity);
		System.out.println("Smallest quantity item: " + title.get(index) + 
				" (" + type.get(index) + ")");
		
		index = findLowestPrice(price, quantity);
		cost = (price.get(index) * quantity.get(index));
		System.out.println("Lowest total dollar item: " + title.get(index) + 
				" ($"  + cost + ")");
		
		System.out.println("----------------------------------");
	}
	
	public static int readFile(String fileName, ArrayList<String> title, ArrayList<String> type, 
			ArrayList<Double> price, ArrayList<Integer> quantity) throws IOException{
		int count = 0;
		BufferedReader inFile = new BufferedReader(new FileReader(fileName));
		String input = "";
		
		while ((input = inFile.readLine()) != null){
			title.add(input);
			
			input = inFile.readLine();
			quantity.add(Integer.parseInt(input));
			
			input = inFile.readLine();
			price.add(Double.parseDouble(input));
			
			input = inFile.readLine();
			type.add(input);
			count++;
		}
		inFile.close();
		return count;
	}

}
