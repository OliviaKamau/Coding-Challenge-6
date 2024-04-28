package codingChallenge6;

import java.util.*;

public class WordFinder {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int rows, columns;
        
      //Makes sure rows dimension constraints are met.
        while (true) 	
        {
            System.out.print("Enter the number of rows: ");
            rows = scan.nextInt();

            if (rows >= 1 && rows <= 1000) {
                break;
            } else {
                System.out.println("Number of rows must be between 1 and 1000.");
            }
        }

        //Makes sure column dimension constraints are met.
        while (true) 
        {
            System.out.print("Enter the number of columns: ");
            columns = scan.nextInt();

            if (columns >= 1 && columns <= 1000) 
            {
                break; 
            } else {
                System.out.println("Number of columns must be between 1 and 1000.");
            }
        }

        //Creates 2D array with specified dimensions for the user to enter the letters in.
        char[][] board = new char[rows][columns];

        //Lets user begin entering the elements into the 2D array.
        System.out.println("Enter the elements of the board:");
        scan.nextLine(); 
        
        //Iterates over each row in the board.
        for (int i = 0; i < rows; i++) 
        {
            try 
            {
                String row = scan.nextLine();	 //Reads user input.
                
                //Makes sure that user input matches specified number.
                if (row.length() != columns) 
                {
                    throw new InputMismatchException("Row " + (i + 1) + ": Please enter exactly " + columns + " letters.");
                }
                //Makes sure user only enters uppercase letters.
                if (!row.equals(row.toUpperCase())) 
                {
                    throw new InputMismatchException("Row " + (i + 1) + ": Please enter uppercase letters only.");
                }
                //Makes sure user input is letters only.
                for (int j = 0; j < columns; j++) 
                {
                    char c = row.charAt(j);
                    if (!Character.isLetter(c)) 	
                    {
                        throw new InputMismatchException("Row " + (i + 1) + ": Please enter a letter.");
                    }
                    board[i][j] = c;	//Assigns input to corresponding position in the 2D array.
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());		//Prints output statement for invalid input.
                i--; 	//Decrements to allow user to re-enter input for the current row.
            }
        }

        String word;	//Stores the word that the user will input.
        
        while (true) 	//Repeatedly prompts user for input until valid word is entered.
        {
            System.out.print("Enter the word to search for: ");
            word = scan.next().toUpperCase(); 	//Convert input to uppercase
            
            //Ensures word is >1 character and <1000 characters
            if (word.length() >= 1 && word.length() <= 1000) {
                break;
            } else {
                System.out.println("Length of word must be between 1 and 1000.");
            }
        }

        //Output statement for word being found.
        System.out.println("Word exists: " + exist(board, word));
    }

    
    //Method that determines whether the word exists or not
    public static boolean exist(char[][] board, String word) {
    	

        char[] wordArray = word.toCharArray();		//Converts the string array into a character array for easier iteration. 
        
        //Arraylist that stores the characters in a flatlist (1D) format (to easily check if the word exists in the board)
        List<Character> charList = new ArrayList<>();
        
        //Adds characters from 2D array into flatlist array
        for (char[] chars : board) 		//Iterates over each row.
        {
            for (char aChar : chars) 	//Iterates over each character in the row.
            {
                charList.add(aChar);	//Adds each character from the board into the flatlist.
            }
        }

        //Searches for word in the array
        for (char c : wordArray) 
        {
        	//Searches through flatline array and removes the letter if it matches the letter in the word.
            if (!charList.remove(Character.valueOf(c))) //Returns false if word is not present in the board.
            {
                return false;
            }
        }

        return true;
    }

}



/**
 * 			TEST CASE 1:		(regular input)
 * 
 *	(A) When word exists in board:
 * 		Enter the number of rows: 3
		Enter the number of columns: 3
		Enter the elements of the board:
		ABC
		DEF
		GHI
		Enter the word to search for: CAFE
		Word exists: true

	(B) When word doesn't exist in board:
		Enter the number of rows: 2
		Enter the number of columns: 4
		Enter the elements of the board:
		WERT
		LKJH
		Enter the word to search for: APPLE
		Word exists: false


 * 			TEST CASE 2:		(when constraints (>1 and <1000) are not met)
 * 
 * 		Enter the number of rows: 100000000
		Number of rows must be between 1 and 1000.
		Enter the number of rows: 2
		Enter the number of columns: 40000000
		Number of columns must be between 1 and 1000.
		Enter the number of columns: 4
		Enter the elements of the board:
		ABCD
		EFGH
		Enter the word to search for: BAD
		Word exists: true
 * 
 * 
 * 
 * 			TEST CASE 3:		(when user exceeds no. of columns specified)
 * 
 * 		Enter the number of rows: 2
		Enter the number of columns: 3
		Enter the elements of the board:
		ABCDEFG
		Row 1: Please enter exactly 3 letters.
		ABC
		DEFGHIJKLMNOP
		Row 2: Please enter exactly 3 letters.
		DEF
		Enter the word to search for: CAB
		Word exists: true
		
		
		
			TEST CASE 4:		(user enters invalid input for the board) (like numbers or lowercase letters)
			
		Enter the number of rows: 2
		Enter the number of columns: 5
		Enter the elements of the board:
		12345
		Row 1: Please enter a letter.
		ABCDE
		fghij
		Row 2: Please enter uppercase letters only.
		FGHIJ
		Enter the word to search for: JADE
		Word exists: true

			

 * 
 * 
 * 
 */
