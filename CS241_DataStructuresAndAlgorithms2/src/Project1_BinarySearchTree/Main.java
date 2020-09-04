//
//	Name: Phung, Steven
//	Project: 1
//	Due: 1/30/18
//	Course: cs-241-02-w18
//
//	Description:
//		Implementation of a binary search tree using node representation.
//
//Main class including the user interface that interacts with the Binary Tree.
package Project1_BinarySearchTree;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		//Tests that I used:
		//51 29 68 90 36 40 22 59 44 99 77 60 27 83 15 75 3
		//9 17 53 14 4 7 11 15 3 13
		
		//Create object keyboard for input and new empty binary tree.
		Scanner keyboard = new Scanner(System.in);
		BinaryTree tree = new BinaryTree();
		
		//Reads in a string of integers separated by a space and stores them into an array of String.
		//An integer array is created with the exact same size as the String array.
		//Use for loop to loop through the String array, convert the String to int and storing it in the Int array.
		//Adds all values to binary tree at the same time.
		System.out.println("S. Phung's Binary Tree Program");
		System.out.println("Please enter the initial sequence of values: ");
		String[] stringArray = keyboard.nextLine().split(" ");
		int[] intArray = new int[stringArray.length];
		for(int i = 0; i < intArray.length; i++) {
			intArray[i] = Integer.parseInt(stringArray[i]);
			tree.add(intArray[i]);
		}
		
		//Prints all traversals and displays command messages.
		System.out.print("Preorder Traversal:   ");
		tree.preorderTraversal(tree.rootNode);
		System.out.print("\nInorder Traversal:    ");
		tree.inorderTraversal(tree.rootNode);
		System.out.print("\nPostorder Traversal:  ");
		tree.postorderTraversal(tree.rootNode);
		displayMessage();
		
		//Use while loop and switch block to keep looping through commands until there is a decision to exit.
		//1. allows user to add a value to the binary tree
		//2. allows user to remove a value from the binary tree
		//3. searches for predecessor and returns int value
		//4. searches for successor and returns int value
		//5. exits program
		//6. command methods again.
		while(true) {
			int userChoice, num; 
			userChoice = keyboard.nextInt();
			switch(userChoice) {
			case 1:
				System.out.print("\nPlease enter a number you want to add: \n");
				num = keyboard.nextInt();
				tree.add(num);
				System.out.print("Inorder Traversal:    ");
				tree.inorderTraversal(tree.rootNode);
				System.out.println();
				break;
			case 2:
				System.out.print("\nPlease enter a number you want to remove: \n");
				num = keyboard.nextInt();
				tree.remove(num);
				System.out.print("Inorder Traversal:    ");
				tree.inorderTraversal(tree.rootNode);
				System.out.println();
				break;
			case 3:
				System.out.print("\nPlease enter a number where you want to find its predecessor:\n");
				num = keyboard.nextInt();
				BinaryTree.BinaryNode[] search1 = new BinaryTree.BinaryNode[3];
				tree.inorderSearch(tree.rootNode, num, search1);
				System.out.print(num +"'s successor is " + search1[0].getData());
				System.out.println();
				break;
			case 4:
				System.out.print("\nPlease enter a number where you want to find its successor:\n");
				num = keyboard.nextInt();
				BinaryTree.BinaryNode[] search2 = new BinaryTree.BinaryNode[3];
				tree.inorderSearch(tree.rootNode, num, search2);
				System.out.print(num +"'s successor is " + search2[2].getData());
				System.out.println();
				break;
			case 5:
				System.out.print("Thank you for using my program!");
				keyboard.close();
				System.exit(0);
				break;
			case 6:
				displayMessage();
				break;
			}
		}
	}

	//Function that displays command messages so I don't have to retype them
	private static void displayMessage() {
		System.out.println("\nCommands: \n"
				+ "	1 > Insert a value\n"
				+ "	2 > Delete a value\n"
				+ "	3 > Find predecessor\n"
				+ "	4 > Find successor\n"
				+ "	5 > Exit the program\n"
				+ "	6 > Display this message again");
	}
}