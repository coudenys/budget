package budget;

public class Date implements Comparable<Date> {
	
	private int day, month, year;
	
	public Date( int day, int month, int year ){
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	/*
	 * make an object of Date based on a dateString
	 * with this format: dd-mm-yyyy
	 */
	public Date(String dateString){
		String[] numbers = dateString.split("-");
		this.day = Integer.parseInt(numbers[0]);
		this.month = Integer.parseInt(numbers[1]);
		this.year = Integer.parseInt(numbers[2]);
	}
	
	public int getDay(){
		return day;
	}
	public int getMonth(){
		return month;
	}
	public int getYear(){
		return year;
	}
	
	public String toString(){
		return this.day + "-" + this.month + "-" + this.year;
	}
	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * return 1 if the object is seen as bigger than the argument object
	 * returns -1 if the object is seen as smaller than the argument object
	 * returns 0 if the objects are considered the same
	 * not talking about object references
	 */
	public int compareTo(Date o){
		
		if ( this.year > o.getYear())
			return 1;
		else if ( this.year < o.getYear())
			return -1;
		else 
			if ( this.month > o.getMonth())
				return 1;
			else if (this.month < o.getMonth())
				return -1;
			else 
				if ( this.day > o.getDay())
					return 1;
				else if ( this.day < o.getDay())
					return -1;
				else 
					// the dates are the same
					return 0;
	}
}

	
