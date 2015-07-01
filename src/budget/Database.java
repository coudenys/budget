package budget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TimeZone;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.PrintWriter;
import java.util.Calendar;

public class Database {
	
	private ArrayList<Entry> allEntries;
	private String entriesFile;
	
	public Database(String entriesFile) {
		this.allEntries = new ArrayList<Entry>();
		this.entriesFile = entriesFile;
				
	}
	
	public ArrayList<Entry> getAllEntries(){
		return this.allEntries;
	}
		
	public void readEntriesFile(){
		BufferedReader br = null;
		
		try {
			br = new BufferedReader( new FileReader(this.entriesFile));
			
			String line = null;
			
			while ( ( line = br.readLine()) != null) {
				
				String[] e = line.split(";");
				String[] entry = new String[e.length + 1];
				System.arraycopy(e, 0, entry, 0, e.length);
				 
				allEntries.add(new Entry(new Date(entry[0]), entry[1], Double.parseDouble(entry[2]), entry[3], entry[4]));
				
			}
		} catch ( FileNotFoundException fnfe) {
			fnfe.printStackTrace();
		} catch ( IOException ioe){
			ioe.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				}
				catch ( IOException ioe ) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	public Entry addEntry(){
		
		BufferedReader br = new BufferedReader( new InputStreamReader(System.in));
		int day, month, year;
		String store;
		double price;
		String category;
		String comment;
		
		Calendar cal = Calendar.getInstance(TimeZone.getDefault());
		
		String line = null;
		Entry entry = null;
		
		try {
		
			System.out.print("Day(" + cal.get(Calendar.DATE) + "): ");
			line = br.readLine();
			
			if (line.trim().length() == 0 ){
				day = cal.get(Calendar.DATE);
			} else {
				day = Integer.parseInt(line);
			}
		
			System.out.print("Month(" + ( cal.get(Calendar.MONTH) + 1 ) + "): ");
			line = br.readLine();
			
			if (line.trim().length() == 0 ){
				month = cal.get(Calendar.MONTH) + 1 ;
			} else {
				month = Integer.parseInt(line);
			}
			
			System.out.print("Year(" + cal.get(Calendar.YEAR) + "): ");
			line = br.readLine();
			
			if (line.trim().length() == 0 ){
				year = cal.get(Calendar.YEAR);
			} else {
				year = Integer.parseInt(line);
			}
			
			System.out.print("Store: ");
			line = br.readLine();
			
			store = line;
			
			System.out.print("Price: ");
			line = br.readLine();
			
			price = Double.parseDouble(line);
			
			System.out.print("Category: ");
			line = br.readLine();
			
			category = line;
			
			System.out.print("Comment: ");
			line = br.readLine();
			
			comment = line;
			
			System.out.println();
			System.out.println("New entry: ");
			
			entry = new Entry ( new Date(day, month, year), store, price, category, comment);
			allEntries.add(entry);
			System.out.println(entry);
			
		}
		catch (IOException ioe){
			ioe.printStackTrace();
		}
		
		return entry;
	}
	
	public void appendToEntriesFile(Entry e){
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter( new BufferedWriter( new FileWriter( this.entriesFile, true)));
			pw.println(e);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if ( pw != null){
				pw.close();
			}
		}
	} 
	
	public void printAllEntries(){
		for ( Entry e : allEntries){
			System.out.println(e);
		}
	}
	
	/* 
	 * this method can be used for all requests based on date range
	 */
	public ArrayList<Entry> subSetBetweenDates(ArrayList<Entry> entries, DateRange d){
		ArrayList<Entry> subset = new ArrayList<Entry>();
		for ( Entry e : entries)
			if ( e.getDate().compareTo(d.getBegin()) >= 0 && e.getDate().compareTo(d.getEnd()) <= 0 ) 
				subset.add(e);
		
		
		Collections.sort(subset); //needed for printing
		return subset;
	}
	
	public ArrayList<Entry> subSetForCategory(ArrayList<Entry> entries, String cat){
		ArrayList<Entry> subset = new ArrayList<Entry>();
		
		for ( Entry e : entries) 
			if ( e.getCategory().equals(cat) )
				subset.add(e);
		
		Collections.sort(subset);
		return subset;	
		
	}
	
	public static double calculateTotal( ArrayList<Entry> entries ){
		double total = 0.0;
		for ( Entry e : entries){
			total += e.getPrice();
		}
		return total;
	}
	
	public static double calculateAverage( ArrayList<Entry> entries){
		return calculateTotal(entries) / entries.size();
	}
	
	public static void printEntries(ArrayList<Entry> entries){
		for ( Entry e : entries ) {
			System.out.println(e);
		}
	}
	
	public void deleteLastLine(){
		this.allEntries.remove(allEntries.size() - 1); //removes the last entry
		//then writes all the rest back to the file
		
		PrintWriter pw = null;
		try {
			pw = new PrintWriter( new BufferedWriter( new FileWriter( this.entriesFile)));
			for ( Entry e : allEntries)
				pw.println(e);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if ( pw != null){
				pw.close();
			}
		}
	}
	

	
	
	
	
	
	
	

}
