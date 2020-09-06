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

import java.io.IOException;
import java.util.Scanner;

public class Main {

public static void main(String[] args) throws IOException {
		
		//Scanner object that will allow user interaction
		Scanner keyboard = new Scanner(System.in);
		//Holds name of file
		String fileName = null;
		//Creates a graph class
		Graph graph;
		//Used to end loops
		boolean validity = true;
		//Holds user choice for menu
		String choice1 = new String();
		//Holds user choice for city information
		String choice2 = new String();
		//Store source's city name
		String source = new String();
		//Store destination's city name
		String destination = new String();
		//Marks index and weight
		int index = -1, weight = 0;
		
		//Assign file name as 'city.dat'
		fileName = "city.dat";
		//Create graph object using file name
		graph = new Graph(fileName);
		//Read cities into graph
		graph.readCities();
		//Assign file name as 'road.dat'
		fileName = "road.dat";
		//Read road weights into graph
		graph.readRoads(fileName);
		//Print basic menu
		printMenu();
		//One time print that shows a cheat sheet of all city information at once
		System.out.println("P:	Quick print of all city information");
		
		//While loop that allows user to keep interacting with menu
		while(true) {
			System.out.print("Enter command: ");
			choice1 = keyboard.nextLine();
			switch(choice1.charAt(0)) {
			//Gets city information
			case 'Q':
				//Gets user input
				System.out.print("City Code: ");
				choice2 = keyboard.nextLine();
				//If the given input matches a city code
				if(choice2.matches("[A-Z]{2}") && graph.exists(choice2))
					//City info is found and printed
					graph.findCityInfo(choice2);
				else
					//If not found
					System.out.println("Invalid City");
				break;
			//Gets distance between two cities
			case 'D':
				//Continually gets user input if user enters invalid input
				do {
					//Gets user input
					System.out.print("City Codes: ");
					choice2 = keyboard.nextLine();
					//If give input is two sets of two characters
					if(choice2.matches("[A-Z]{2}\\s[A-Z]{2}"))
						//Allow exit of loop
						validity = true;
					else
						//Forces loop to continue
						validity = false;
				} while(!validity);
				//Splits into sub Strings to get source and destination
				source = choice2.substring(0, 2);
				destination = choice2.substring(3, 5);
				//Get index by calculating shortest path
				index = graph.Dijkstras(source, destination);
				//If index is invalid
				if(index == -1)
					System.out.println("Invalid cities");
				else {
					//Display
					System.out.print("The minimum distance between " 
							+ graph.findCityName(source) + " and "
							+ graph.findCityName(destination) + " is " 
							+ (int)graph.findWeight(index) + " through the route: ");
					System.out.println(graph.previous(index));
				}
				break;
			//Insert a new road between two cities
			case 'I':
				//Continually gets user input if user enters invalid input
				do {
					//Gets user input
					System.out.print("City Codes and Distances: ");
					choice2 = keyboard.nextLine();
					//If give input is two sets of two characters and a number
					if(choice2.matches("[A-Z]{2}\\s[A-Z]{2}\\s\\d+"))
						//Allow exit of loop
						validity = true;
					else
						//Forces loop to continue
						validity = false;
				} while(!validity);
				//Splits into sub Strings to get source and destination
				source = choice2.substring(0, 2);
				destination = choice2.substring(3, 5);
				//Get weight
				weight = Integer.parseInt(choice2.substring(6, choice2.length()));
				//Add road, if successful then print information
				if(graph.addRoad(source, destination, weight)) {
					System.out.println("There is now a new road from "
							+ graph.findCityName(source) + " to "
							+ graph.findCityName(destination)
							+ " with a distance of " + weight);
				} else
					//Failed to add
					System.out.println("Failed to create road");
				break;
			//Remove a road between two cities
			case 'R':
				//Continually gets user input if user enters invalid input
				do {
					//Gets user input
					System.out.print("City Codes: ");
					choice2 = keyboard.nextLine();
					//If give input is two sets of two characters
					if(choice2.matches("[A-Z]{2}\\s[A-Z]{2}"))
						//Allow exit of loop
						validity = true;
					else
						//Forces loop to continue
						validity = false;
				} while(!validity);
				//Splits into sub Strings to get source and destination
				source = choice2.substring(0, 2);
				destination = choice2.substring(3, 5);
				//If road is deleted successfully, print correct information
				if(graph.deleteRoad(source, destination)) {
					System.out.println("Road from " + graph.findCityName(source)
							+ " to " + graph.findCityName(destination)
							+ " with distance " + weight + " has been deleted");
				} else {
					//Print what was unable to be removed
					System.out.println("Road from " + graph.findCityName(source)
							+ " to " + graph.findCityName(destination)
							+ " does not exist");
				}
				break;
			//Print Menu again
			case 'H':
				//Menu
				printMenu();
				break;
			//Exit
			case 'E':
				//Close and exit
				keyboard.close();
				System.exit(0);
				break;
			//Print all city information
			case 'P':
				//Cheat sheet
				graph.printAllCityInfo();
				break;
			default:
				//If user enters a character other than QDIRHE
				System.out.print("Invalid input, try again:");
				break;
			}
		}
	}
	
	//Prints user menu
	public static void printMenu() {
		System.out.println("Q:	Query the city information by entering the city code");
		System.out.println("D:	Find the minimum distance between the two cities");
		System.out.println("I:	Insert a road by entering two city codes and distance");
		System.out.println("R:	Remove an existing road by entering two city codes");
		System.out.println("H:	Display this message");
		System.out.println("E:	Exit program");
	}
}
