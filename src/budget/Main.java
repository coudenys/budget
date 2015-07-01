package budget;

import java.util.ArrayList;

public class Main {
	
	public static void main(String[] args ){
		
		Database db = new Database("/home/wouter/.budget/entries.list");
		db.readEntriesFile();
			
		if ( args.length == 0 ) {
			Entry e = db.addEntry();
			db.appendToEntriesFile(e);
		}
		else if ( args.length == 1 && args[0].equals( "dll" ) ) {
			db.deleteLastLine();
		}
		else if ( args.length >= 3 ){
			
			int index = 0;
			double number = 0.0;
			
			//determining subset
			ArrayList<Entry> subset = db.getAllEntries();
			
			
			while ( index < args.length - 1){
				
				if ( args[ index  + 1 ].equals( "month") ){					
					subset = db.subSetBetweenDates( subset, DateRange.fromMonth(args[index + 2]));
					index += 2;
				}
				else if ( args[ index + 1 ].equals("year") ) {
					subset = db.subSetBetweenDates( subset, DateRange.fromYear(args[index + 2]));
					index += 2;
				}
				else if ( args[ index + 1].equals( "cat") ){
					subset = db.subSetForCategory( subset, args[index + 2] );					
					index += 2;
				}
				else if ( args[ index + 1].equals("between" ) && args[index + 3 ].equals( "and" )){
					subset = db.subSetBetweenDates(subset, new DateRange( new Date(args[index + 2]) , new Date ( args[index + 4])));
					index += 4  ;
				}
				
			}
			if ( args[0].equals( "total" )){
				number = Database.calculateTotal(subset);
				System.out.println("Total of " + subset.size() + ": ");
				System.out.printf("%.2f\n", number);
			}
			else if ( args[0].equals("avg")){
				number = Database.calculateAverage(subset);
				System.out.println("Average of " + subset.size() + ": ");
				System.out.printf("%.2f\n", number);
			}
			else if ( args[0].equals("print")){
				Database.printEntries(subset);
				System.out.println("Total number: " + subset.size());
			}
		}
	}

}
