//
//	Name: Phung, Steven
//	Project: 3
//	Due: 3/8/18
//	Course: cs-241-02-w18
//
//	Description:
//		Read two input files, 'city.dat' and 'road.dat' into an implementation of a directed graph 
//		using adjacency matrix array representation and Dijkstra's algorithm to find shortest path
//		between two vertexes or other various functions
//
package Project3_DijkstrasAlgorithm;

import java.io.PrintStream;

public class Vertex {

	//City information
	private int cityNumber, population, elevation;
	private String cityCode, fullCityName;
	private double weight;

	//Constructor
	public Vertex() {
		this.cityNumber = 0;
		this.population = 0;
		this.elevation = 0;
		this.cityCode = null;
		this.fullCityName = null;
		this.weight = 0;
	}
	
	//Returns city number (int)
	public int getCityNumber() {
		return cityNumber;
	}
	
	//Returns population of city (int)
	public int getPopulation() {
		return population;
	}

	//Returns elevation of city (int)
	public int getElevation() {
		return elevation;
	}
	
	//Returns two character code of city (String)
	public String getCityCode() {
		return cityCode;
	}
	
	//Returns full name of city (String)
	public String getFullCityName() {
		return fullCityName;
	}
	
	//Returns weight needed for relaxation method (double to get infinity)
	public double getWeight() {
		return weight;
	}
	
	//Sets city number (int)
	public void setCityNumber(int cityNumber) {
		this.cityNumber = cityNumber;
	}
	
	//Sets population of city (int)
	public void setPopulation(int population) {
		this.population = population;
	}
	
	//Sets elevation of city (int)
	public void setElevation(int elevation) {
		this.elevation = elevation;
	}
	
	//Sets two character code of city (String)
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
	//Sets full name of city (String)
	public void setFullCityName(String fullCityName) {
		this.fullCityName = fullCityName;
	}
	
	//Sets weight used in relaxation method (double)
	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	//Prints all city information using quick format
	public PrintStream formatedPrint() {
		return System.out.printf("%2d %3s %15s %8d %5d \n", cityNumber, cityCode, fullCityName, population, elevation);
	}
	
	//Returns a copy of a given vertex
	public static Vertex copyVertex(Vertex v) {
		//Create new vertex
		Vertex newVertex = new Vertex();
		
		//Set new vertex fields according to given vertex
		newVertex.setCityNumber(v.getCityNumber());
		newVertex.setCityCode(v.getCityCode());
		newVertex.setFullCityName(v.getFullCityName());
		newVertex.setPopulation(v.getPopulation());
		newVertex.setElevation(v.getElevation());
		newVertex.setWeight(v.getWeight());
		
		//Return new vertex
		return newVertex;
	}
}