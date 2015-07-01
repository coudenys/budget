package budget;

public class Entry implements Comparable<Entry>{
	
	private Date date;
	private String store, category, comment;
	private double price;
	
	
	public Entry( Date d, String s , double p , String ca, String co){
		this.date = d;
		this.store = s;
		this.price = p;
		this.category = ca;
		this.comment = co;
		
	}
	
	public String getCategory(){
		return this.category;
	}
	
	public double getPrice(){
		return price;
	}
	
	public String toString(){
		return date + ";" + store + ";" + price + ";" + ( category == null ? "none" : category ) + ";" + 
				(comment == null ? "" : comment);
	}
	
	public Date getDate(){
		return this.date;
	}
	
	public int compareTo(Entry o){
		return this.date.compareTo(o.getDate());
	}

	
	

}
