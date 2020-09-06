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

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Graph {

		//Adjacency matrix
		private int[][] directedGraph;
		//Vertex of cities
		private Vertex[] cities;
		//Index of vertexes and element of previous vertex
		private int[] previousCities;
		//Visited vertexes
		private boolean[] viewedCities;
		//Scan input object
		private Scanner fileInput;
		//File name
		private String fileName;
		//Counter for how many lines are in the file
		private int fileCount;
		//Get the number infinity
		private final double infinity = Double.POSITIVE_INFINITY;
		
		//Constructor, string holds file name
		public Graph(String fileName) throws IOException {
			//Line count for file starts at zero
			fileCount = 0;
			//Saves local file name to file name field
			this.fileName = fileName;
			//Creates object for scanner with a valid file name
			fileInput = new Scanner(new File(fileName));
			//While loop to count how many lines (cities) are in the file
			while(fileInput.hasNextLine()) {
				//Get line
				String data = fileInput.nextLine();
				//Move onto next line
				if(!data.matches("^"))
					//Increase counter
					fileCount++;
			}
			//Create new vertex array based on counter
			cities = new Vertex[fileCount];
			//Create adjacency matrix required for a graph
			directedGraph = new int[fileCount][];
			//Create objects in the array
			for(int i = 0; i < fileCount; i++) 
				directedGraph[i] = new int[fileCount];
			//Sets default distances for all roads to be zero
			for(int i = 0; i < fileCount; i++)
				for(int j = 0; j < fileCount; j++)
					directedGraph[i][j] = 0;
			//Create previous array based on counter
			previousCities = new int[fileCount];
			//Create visited vertexes array based on counter
			viewedCities = new boolean[fileCount];
			//Close keyboard
			fileInput.close();
		}
		
		//Read cities from file into the proper place
		public void readCities() throws IOException {
			//String to hold cities name
			String name = null;
			//Creates scanner object with file name
			fileInput = new Scanner(new File(fileName));
			//For loop using counter to loop through all cities
			for(int i = 0; i < fileCount; i++) {
				//Set the name to something empty
				name = "";
				//Create a new vertex at an index in cities array
				cities[i] = new Vertex();
				//Looks for int
				if(fileInput.hasNextInt())
					//Found int will set the correct city number
					cities[i].setCityNumber(fileInput.nextInt());
				//Looks for two character city code
				if(fileInput.hasNext("[a-zA-Z]{2}"))
					//Found two characters will be set
					cities[i].setCityCode(fileInput.next("[a-zA-Z]{2}"));
				//Looks for city name
				if(fileInput.hasNext("[a-zA-Z]{2,}"))
					//Adds city name to string
					name += fileInput.next("[a-zA-Z]{3,}");
				//If city name has a second word to it, ex. Garden Grove
				if(fileInput.hasNext("[a-zA-Z]{3,}")) {
					//Space between the two words
					name += " ";
					//Adds city name to string
					name += fileInput.next("[a-zA-Z]{3,}");
					//Set string as city's full name
					cities[i].setFullCityName(name);
				} else
					//If there is no second word for the city name, set name as is
					cities[i].setFullCityName(name);
				//Looks for next int for city population
				if(fileInput.hasNextInt())
					//Sets found int as city population
					cities[i].setPopulation(fileInput.nextInt());
				//Looks for next int for city elevation
				if(fileInput.hasNextInt())
					//Sets found int as city elevation
					cities[i].setElevation(fileInput.nextInt());
			}
		}
		
		//Reads roads from file into proper place in the adjacency matrix
		public void readRoads(String fileName) throws IOException {
			//Source is first array, destination is the second array, and distance is the weight
			int source = 0, destination = 0, distance = 0;	
			//Creates scanner object with given file name
			fileInput = new Scanner(new File(fileName));
			//Use while loop to loop through number of cities
			while(fileInput.hasNextInt()) {
				//First int found is the source
				source = fileInput.nextInt();
				//Second int found is the destination
				destination = fileInput.nextInt();
				//Third int found is the weight
				distance = fileInput.nextInt();
				//Set weight in correct indexes, subtract one to take index zero into account
				directedGraph[source - 1][destination - 1] = distance;
			}
		}
		
		//Find smallest path between two cities
		public int Dijkstras(String source, String destination) {
			//Used to check if found
			boolean check1 = false, check2 = false;
			//Loop through cities array
			for(int i = 0; i < fileCount; i++) {
				//Element cannot be null
				if(cities[i] != null) {
					//If given source code matches element in array
					if(cities[i].getCityCode().equals(source))
						//Mark true
						check1 = true;
					//If given destination matches element in array
					if(cities[i].getCityCode().equals(destination))
						//Mark true
						check2 = true;
					//If both source city and destination city vertexes are found
					if(check1 && check2)
						//End for-loop
						break;
				}
			}
			//If neither source nor destination is found
			if(!check1 && !check2)
				//Return false as unable to add road
				return -1;
			//If only destination was found
			else if(!check1 && check2)
				//Return false as unable to add road
				return -1;
			//If only source was found
			else if(check1 && !check2)
				//Return false as unable to add road
				return -1;
			//Index of current vertex
			int currentVertex = 0;
			//Weight
			double weight = 0;
			//Loop to initialize
			for(int i = 0; i < fileCount; i++) {
				//Set all cities as visited to be false
				viewedCities[i] = false;
				//Since current city would be index of 0, previous city must be -1
				previousCities[i] = -1;
				//If current city is not the same as the source
				if(!(source.equals(cities[i].getCityCode())))
					//Set weight to infinity
					cities[i].setWeight(infinity);
				else {
					//Set weight to zero if current city is the same as the source city
					cities[i].setWeight(0);
					//Hold index of vertex with smallest weight
					currentVertex = i;
				}
			}
			//Create new vertex
			Vertex v = new Vertex();
			//Create new vertex array
			Vertex[] array = new Vertex[fileCount];
			//Copy original array into a new one
			for(int i = 0; i < fileCount; i++)
				array[i] = Vertex.copyVertex(cities[i]);
			//While new array length is not zero
			while(array.length != 0) {
				//Find index of vertex with smallest weight
				currentVertex = findSmallestWeight(array);
				//If smallest vertex does not exist
				if(currentVertex == -1)
					//End loop
					break;
				//If smallest vertex is the destination
				if(array[currentVertex].getCityCode().equals(destination))
					//End loop
					break;
				//If it is infinite
				if(array[currentVertex].getWeight() == infinity)
					//End loop
					break;
				//Copy content from array into vertex
				v = Vertex.copyVertex(array[currentVertex]);
				//Remove
				array[currentVertex] = null;
				//Mark city as been visited
				viewedCities[currentVertex] = true;
				//Loop through neighbor of the current vertex
				for(int i = 0; i < fileCount; i++) {
					//If there is a road and it has not been viewed
					if(directedGraph[currentVertex][i] > 0 && !viewedCities[i]) {
						//Find weight between current vertex and vertex i with current vertex weight
						weight = (v.getWeight() + directedGraph[currentVertex][i]);
						//If weight plus distnace is less than weight of vertex i
						if(weight < array[i].getWeight()) {
							//Set vertex's weight
							array[i].setWeight(weight);
							//Set city's weight
							cities[i].setWeight(weight);
							//Set previous vertex as current vertex
							previousCities[i] = currentVertex;
						}
					}
				}
			}
			//Return index of destination
			return currentVertex;
		}
		
		//Boolean function to add road between two cities, true if add was successful, false if failed
		public boolean addRoad(String source, String destination, int distance) {
			//Holds source city index and destination city index
			int sourceIndex = 0, destinationIndex = 0;
			//Used to check if found
			boolean check1 = false, check2 = false;
			//City cannot have a road leading to itself
			if(source.equals(destination))
				return false;
			//Weight between two cities must be positive
			if(distance < 0)
				return false;
			//Loop through cities array
			for(int i = 0; i < cities.length; i++) {
				//Must have object to continue, cannot be null
				if(cities[i] != null) {
					//Looks for element same as given source vertex
					if(cities[i].getCityCode().equals(source)) {
						//Saves source index
						sourceIndex = i;
						//Mark true
						check1 = true;
					}
					//Looks for element same as given destination vertex
					if(cities[i].getCityCode().equals(destination)) {
						//Saves destination index
						destinationIndex = i;
						//Mark true
						check2 =  true;
					}
					//If both source city and destination city vertexes are found
					if(check1 && check2)
						//End for-loop
						break;
				}
			}
			//If neither source nor destination is found
			if(!check1 && !check2)
				//Return false as unable to add road
				return false;
			//If only destination was found
			else if(!check1 && check2)
				//Return false as unable to add road
				return false;
			//If only source was found
			else if(check1 && !check2)
				//Return false as unable to add road
				return false;
			//Set weight for road
			directedGraph[sourceIndex][destinationIndex] = distance;
			//Return true because add was successful
			return true;
		}
		
		//Boolean function to delete a road between two cities, true if remove was successful, false if failed
		public boolean deleteRoad(String source, String destination) {
			//Holds source city index and destination city index
			int sourceIndex = 0, destinationIndex = 0;
			//Used to check if found
			boolean check1 = false, check2 = false;
			//City cannot have a road leading to itself so it cannot delete such a road
			if(source.equals(destination))
				return false;
			//Loop through cities array
			for(int i = 0; i < cities.length; i++) {
				//Must have object to continue, cannot be null
				if(cities[i] != null) {
					//Loops for element same as given source vertex
					if(cities[i].getCityCode().equals(source)) {
						//Saves source index
						sourceIndex = i;
						//Marks true
						check1 = true;
					}
					//Looks for element same as given destination vertex
					if(cities[i].getCityCode().equals(destination)) {
						//Saves destination index
						destinationIndex = i;
						//Marks true
						check2 =  true;
					}
					//If both source and destination city vertexes are found
					if(check1 && check2)
						//End for-loop
						break;
				}
			}
			//If both source and destination vertexes were found but there is already no weight (road) between them
			if(directedGraph[sourceIndex][destinationIndex] == 0)
			//Return false as unable to add road
				return false;
			//If neither source nor destination is found
			else if(!check1 && !check2)
				//Return false as unable to add road
				return false;
			//If only destination was found
			else if(!check1 && check2)
				//Return false as unable to add road
				return false;
			//If only source was found
			else if(check1 && !check2)
				//Return false as unable to add road
				return false;
			//Set weight for road to be null
			directedGraph[sourceIndex][destinationIndex] = 0;
			return true;
		}
		
		//Method that finds name of a city given any vertex's city code
		public String findCityName(String vertex) {
			//Loops through cities array
			for(int i = 0; i < cities.length; i++) {
				//Element cannot be null
				if(cities[i] != null) {
					//If array's city code matches given city code
					if(cities[i].getCityCode().equals(vertex))
						//Return city's full name
						return cities[i].getFullCityName();
				}
			}
			//If not found, returns null
			return null;
		}
		
		//Method that finds all of a city's information given any vertex's city code
		public String findCityInfo(String vertex) {
			//Loops through cities array
			for(int i = 0; i < cities.length; i++) {
				//Element cannot be null
				if(cities[i] != null) {
					//If array's city code matches given city code
					if(cities[i].getCityCode().equals(vertex))
						//Prints a formatted line of all the city's information
						cities[i].formatedPrint();
				}
			}
			//If not found, returns null
			return null;
		}
		
		//Method that returns a string of the previous city's code given a destination
		public String previous(int index) {
			//String that holds the name
			String s = new String();
			//Index cannot be negative
			if(index < 0)
				//If it is negative, empty String is returned
				return "";
			//Recursively call method to return string
			s += previous(index, s);
			//Delete last comma
			s = s.trim();
			s = s.substring(0, s.length() - 1);
			return s;
		}
		
		//Recursive method that returns a string of the previous city's code given a destination
		private String previous(int index, String s) {
			//Index cannot be negative
			if(index < 0)
				//If it is negative, empty String is returned
				return "";
			//Get current index of previous array, which is one city behind cities array
			int newIndex = previousCities[index];
			//Recursive call to get previous cities
			s = previous(newIndex, s);
			//Add string in between each city
			s += (cities[index].getCityCode() + ", ");
			//Return String of city with the smallest path
			return s;
		}
		
		//Method that looks for smallest weight
		public static int findSmallestWeight(Vertex[] array) {
			//Local variables
			double smallest = -1;
			int index = 0, i = 0;
			//If array is empty
			if(array.length == 0)
				//-1 to indicate empty
				return -1;
			//If array only has one element
			if(array.length == 1)
				//Return corresponding index
				return 0;
			//Loop through array
			for(i = 0; i < array.length; i++) {
				if(array[i] != null) {
					//Assume element in first index has smallest weight
					smallest = array[i].getWeight();
					//Holds index
					index = i;
					//Exit on first loop
					break;
				}
			}
			//Loop through array
			for(i = i + 1; i < array.length; i++) {
				//If element is not null and it has a smaller weight than current weight
				if((array[i] != null) && (array[i].getWeight() < smallest)) {
					//New weight becomes smallest current weight
					smallest = array[i].getWeight();
					//Holds new index
					index = i;
				}
			}
			//If index is null
			if(array[index] == null)
				//-1 to indicate empty
				return -1;
			else
				//Return element
				return index;
		}
		
		//Get weight of city
		public double findWeight(int index) {
			//Vertex in cities array cannot be null
			if(cities[index] == null)
				//-1 to indicate empty
				return -1;
			else
				//Return weight
				return cities[index].getWeight();
		}
		
		//Prints information of all cities
		public void printAllCityInfo() {
			//Loops through cities array
			for(int i = 0; i < cities.length; i++) {
				//Cannot read if null
				if(cities[i] != null) {
					//Prints a city's information
					cities[i].formatedPrint();
				}
			}
		}

		//Boolean function that returns true if the given city code already exists in the cities array, false if not true
		public boolean exists(String vertex) {
			//Local variable
			boolean exists = false;
			//Loops through cities array
			for(int i = 0; i < cities.length; i++) {
				//If given city code matches with a city code in the cities array
				if(cities[i].getCityCode().equals(vertex)) {
					//True that the given city exists in the array
					exists = true;
				}
			}
			//Returns exist variable
			return exists;
		}
	}