//
//	Name: Phung, Steven
//	Project: 2
//	Due: 2/13/18
//	Course: cs-241-02-w18
//
//	Description:
//		Implementation of a max heap using array representation.
//
package Project2_BinaryHeap;

import java.util.Random;

public class Heap {
	private int[] maxHeap;
	private int index;
	private int swaps;
	private static final int arraySize = 100;
	
	//Constructor that sets the heap to a max size of 100 and defaults all fields to 0.
	public Heap() {
		maxHeap = new int[arraySize + 1];
		this.index = 0;
		this.swaps = 0;
	}
	
	//Method that uses optimal insertion, adding all integers into an array before any swapping is done.
	public void optimalInsertion() {
		int current = index, number, counter = 1;
		
		//Adds unique new random integer into array until array is completely filled.
		while(counter < 100) {
			number = duplicateChecker();
			maxHeap[current++] = number;
			index++;
			counter++;
		}
		
		//Starts from last non-leaf node and reheaps until it is done with all nodes.
		for(int rootIndex = index / 2; rootIndex > 0; rootIndex--) {
			reheap(rootIndex);
			swaps++;
		}
	}
	
	//Non-optimal method that adds in one integer to the heap array.
	public void sequentialInsertion() {
		//Keeps track of where new data will go and it's parent.
		int current = index + 1;
		int parent = current / 2;
		int number = duplicateChecker();
		
		//Essentially upheap, swaps data with parent if data is larger than parent and re-references Index and Parentindex.
		while((parent > 0) && (number > maxHeap[parent])) {
			swaps++;
			maxHeap[current] = maxHeap[parent];
			current = parent;
			parent = current / 2;
		}
		//Adds in newEntry and ups the counter that keeps track of the last elements index.
		maxHeap[current] = number;
		index++;
	}
	
	//Method that runs through the array and tells the other methods whether or not there is the desired value in the array.
	private boolean contains(int newEntry) {
		boolean found = false;
		for(int i = 0; i < maxHeap.length; i++) {
			if(maxHeap[i] == newEntry) {
				found = true;
			}
		}
		return found;
	}

	//Method that randomly generates a number from 1-100
	public int randomNumberGenerator() {
		Random rng = new Random();
		return rng.nextInt(100)+1;
	}
	
	//Method that checks for duplicates
	public int duplicateChecker() {
		int number;
		//Generates new number and runs it through a method that checks for duplicates in the array until the
		//randomly generated number is unique
		do {
			number = randomNumberGenerator();
		} while(contains(number));
		return number;
	}

	//Method that returns how many times there was a swap.
	public int getSwaps() {
		return swaps;
	}
	
	//Method that prints the first 10 values in the heap
	public void printHeap() {
		for(int i = 1; i <= 10; i++) {
			System.out.print(maxHeap[i] + " ");
		}
	}
	
	//Method that creates a new heap from scratch
	public void newHeap() {
		maxHeap = new int[arraySize + 1];
		this.index = 0;
		this.swaps = 0;
	}
	
	//Method that deletes 10 values from heap and calls reheap
	public void removeTenTimes() {
		int counter = 0;
		while(counter < 10) {
			maxHeap[1] = maxHeap[index--];
			for(int rootIndex = index / 2; rootIndex > 0; rootIndex--) {
				reheap(rootIndex);
			}
			counter++;
		}
	}
	
	//
	public void reheap(int givenNumber) {
		boolean continueLoop = true;
		//Given last non-leaf node and also keeps track of left child.
		int currentNumber = maxHeap[givenNumber];
		int leftChild = givenNumber * 2;
		
		//Loop to keep reheaping until there are no more possible swaps
		while(continueLoop && (leftChild <= index)) {
			//Keeps index of left and right child
			int leftChild2 = leftChild;
			int rightChild = leftChild + 1;
			
			//Checks the value with right child
			if((rightChild <= index) && (maxHeap[rightChild] > maxHeap[leftChild2])) {
				leftChild2 = rightChild;
			}
			
			//Checks the value with left child.
			//If the number at the last non-leaf node is smaller than its child value,
			//the larger value is swapped to the parent node
			if(currentNumber < maxHeap[leftChild2]) {
				maxHeap[givenNumber] = maxHeap[leftChild2];
				//The node we check is re-referenced as well as that node's child
				givenNumber = leftChild2;
				leftChild = givenNumber * 2;
			}
			else
				//Gets out of loop if there are no more swaps
				continueLoop = false;
		}
		maxHeap[givenNumber] = currentNumber;
	}
}