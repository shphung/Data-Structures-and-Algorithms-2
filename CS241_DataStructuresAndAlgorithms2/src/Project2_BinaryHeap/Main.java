//
//	Name: Phung, Steven
//	Project: 1
//	Due: 2/13/18
//	Course: cs-241-02-w18
//
//	Description:
//		Implementation of a max heap using array representation.
//
package Project2_BinaryHeap;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("S. Phung's Max Heap Program");
		System.out.println("Please select how to test the program:");
		System.out.println("(1) 20 sets of 100 randomly generated numbers");
		System.out.println("(2) Fixed integer values 1-100");
		System.out.print("Enter choice:");
		
		int userChoice = keyboard.nextInt();
		if(userChoice == 1) {
			int amountOfSets = 1, totalSwaps = 0, averageSwaps = 0;
			//Loop to create 20 separate sets of heaps using sequential insertion and to add all their swaps together
			while(amountOfSets < 20) {
				Heap heap1 = createHeap();
				int counter = 1;
				while(counter < 100) {
					heap1.sequentialInsertion();
					counter++;
				}
				totalSwaps += heap1.getSwaps();
				amountOfSets++;
			}	
			averageSwaps = totalSwaps / 20;
			
			System.out.println("\nTotal swaps for series of insertions: " + totalSwaps);
			System.out.println("Average swaps for series of insertions: " + averageSwaps);
			
			int amountOfSets2 = 1, totalSwaps2 = 0, averageSwaps2 = 0;
			//Loop to create 20 separate sets of heaps using optimal insertion and to add all their swaps together.
			while(amountOfSets2 < 20) {
				Heap heap1 = createHeap();
				heap1.optimalInsertion();
				totalSwaps2 += heap1.getSwaps();
				amountOfSets2++;
			}
			
			averageSwaps2 = totalSwaps2 / 20;
			System.out.println("\nTotal swaps for optimal method: " + totalSwaps2);
			System.out.println("Average swaps for optimal method: " + averageSwaps2);
			System.out.println("---------------------------------------------------------------------");
			
		} else if(userChoice == 2) {
			Heap heap2 = createHeap();
			int counter = 1;
			//Creates one heap using sequential insertion
			while(counter < 100) {
				heap2.sequentialInsertion();
				counter++;
			}
			System.out.print("\nHeap built using series of insertions: ");
			heap2.printHeap();
			System.out.println();
			//Prints number of swaps and calls removal method.
			System.out.println("Number of swaps: " + heap2.getSwaps());
			heap2.removeTenTimes();
			System.out.print("Heap after 10 removals: 		");
			heap2.printHeap();
			System.out.println();
			
			//Creates a new heap and fills it using optimal insertion.
			heap2.newHeap();
			heap2.optimalInsertion();
			System.out.print("\nHeap built using optimal method:       ");
			heap2.printHeap();
			System.out.println();
			//Prints number of swaps and calls removal method.
			System.out.println("Number of swaps: " + heap2.getSwaps());
			heap2.removeTenTimes();
			System.out.print("Heap after 10 removals: 		");
			heap2.printHeap();
			System.out.println();
			System.out.println("---------------------------------------------------------------------");
		}
		keyboard.close();
		System.exit(0);
	}
	
	//Method to create a new heap.
	public static Heap createHeap() {
		Heap h = new Heap();
		return h;
	}
}