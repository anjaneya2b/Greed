//Anjaneya Bhardwaj
package greed;

import java.util.ArrayList;


public class Entry {
	
	private String name;
	private ArrayList<Integer> numbers;
	private double score;
	
	public Entry(String n, ArrayList<Integer> data) {  //this is how to make a new Entry object when you know what values its fields should start with
		name = n;
		numbers = new ArrayList<Integer>();
		for (Integer k : data) {
			numbers.add(k);
		}
		score = 0.0;
	}
	
	public Entry() {
	}


	public String getName() { return name; }
	public void setName(String n) {	name = n; }
	
	public double getScore() { return score; }
	public void setScore(double d) { score = d; }
	public void addToScore(double d) { score += d; }
	
	public ArrayList<Integer> getNumbers() { return numbers; }
	public void setNumbers(ArrayList<Integer> n) {
		numbers.clear();
		for (Integer k : n) {
			numbers.add(k);
		}
	}
	public void appendNumber(int k) { 	//just add a value to this object's numbers field
		numbers.add(k);
	}
	
}
