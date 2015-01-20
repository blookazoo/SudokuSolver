package sodukusolver;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SudokuSolver {
	static int[][] puzzle; // puzzle to solve
	static PrintWriter output;
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		if (args.length != 2) {
			throw new IllegalArgumentException();
		}
		Scanner input = new Scanner(args[0]);
		output = new PrintWriter(args[1]);
		puzzle = new int[9][9];
		// building of sudoku puzzle from given input file
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				String data = input.next();
				// assume no wrong inputs given
				if (data.equals("-"))
					puzzle[x][y] = 0;
				else
					puzzle[x][y] = Integer.parseInt(data);
			}
		}
		input.close();
		solvePuzzle(puzzle, 0, 0);
	}
	
	// recursively solve the sudoku puzzle
	public static void solvePuzzle(int[][] puzzle, int x, int y) {
		// check to see if position is filled
		// go on til reach position that = 0.
		// try values 
		// check values
		// move on to next 
		if (puzzle[x][y] == 0) {
			for (int i = 1; i < 10; i++) {
				if (checkValidPlacement(i, x, y)) {
					puzzle[x][y] = i;
					solvePuzzle(puzzle, x, y);
				}
			}
			
		} else {
			while (x < 9) {
				solvePuzzle(puzzle, x++, y);
				if (x == 9) {
					solvePuzzle(puzzle, 1, y++);
						if (y == 9) {
							outputPuzzle();
						}
				}
			}
		}
	}
	
	// checks if value placed is valid 
	// xPos and yPos is position in the array by 0 index
	public static boolean checkValidPlacement(int val, int xPos, int yPos) {
		int xBox = xPos / 3;
		int yBox = yPos / 3;
		// check the box its placed in
		for (int x = xBox; x < xBox + 3; x++) {
			for (int y = yBox; y < yBox + 3; y++) {
				if (puzzle[x][y] == val)
					return false;
			}
		}
		// check the columns along the row its placed in
		for (int col = 0; col < 9; col++) {
			if (puzzle[xPos - 1][col] == val) 
				return false;
		}
		// check the row along the column its placed in
		for (int row = 0; row < 9; row++) {
			if (puzzle[row][yPos-1] == val)
				return false;
		}
		return true;
	}
	
	public static void outputPuzzle() {
		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				output.print(puzzle[x][y] + " ");
			}
			output.println();
		}
	}

}
