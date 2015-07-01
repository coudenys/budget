package budget;

import java.util.Calendar;
import java.util.TimeZone;

public class DateRange {
	
	private Date begin;
	private Date end;
	
	public DateRange( Date begin , Date end){
		this.begin = begin;
		this.end = end;
	}
	
	public DateRange(){
		this.begin = new Date(0,0,0);
		this.end = new Date(0,0,0);
	}
	
	public Date getBegin(){
		return this.begin;
	}
	public Date getEnd(){
		return this.end;
	}
	
	public static DateRange fromMonth(String ds){
		
		Date[] dates = new Date[2];
		Calendar cal = Calendar.getInstance();
		Calendar cur = Calendar.getInstance(TimeZone.getDefault());
		
		int curMonth = cur.get(Calendar.MONTH) + 1; 
		int year = cur.get(Calendar.YEAR);
		
		if ( ds.matches("\\d{1,2}") ) {
			int month = Integer.parseInt(ds);
			
			if ( month > curMonth )
				year--; //go back one year, no use in calculating total/avg of months yet to come
			
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month - 1); //JANUARY = 0;
			
			
			dates[0] = new Date( 1 , month , year);
			dates[1] = new Date( cal.getActualMaximum(Calendar.DATE), month, year);			
		}
		else if ( ds.matches("\\d{1,2}-\\d{4}")) {
			String[] date = ds.split("-");
			
			
			cal.set(Calendar.MONTH, Integer.parseInt(date[0]) - 1 );
			cal.set(Calendar.YEAR,  Integer.parseInt(date[1]));
			
			dates[0] = new Date( 1, Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			dates[1] = new Date( cal.getActualMaximum(Calendar.DATE), Integer.parseInt(date[0]), Integer.parseInt(date[1]));
			
			
		} else {
			System.err.println("month-string is incorrect");
		}
		
		//System.out.println("Begin date: " + dates[0]);
		//System.out.println("End date:   " + dates[1]);
		
		//this.begin = dates[0];
		//this.end = dates[1];
		
		return new DateRange(dates[0], dates[1]);
			
		
	}
	
	public static DateRange fromYear(String ds){
		if ( ds.matches("\\d{4}")){
			return new DateRange( new Date( 1, 1, Integer.parseInt(ds) ) ,
					new Date( 31, 12, Integer.parseInt(ds)));
			
			//System.out.println("Begin date: " + this.begin);
			//System.out.println("End date:   " + this.end);
		} 
		return new DateRange();
	}

}
