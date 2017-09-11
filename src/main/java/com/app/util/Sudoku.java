package com.app.util;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sudoku {
	
	private static final Logger log = LoggerFactory.getLogger(Sudoku.class);
	public static final int SUDOKU_ELEMENTS_NUMBERS=81;
	public static final int SUDOKU_ROWS_NUMBERS=9;
	public static final int SUDOKU_COLUMNS_NUMBERS=9;
	public static final String X="x";
	public static final int MAX_NUMBER=9;
	
	public static String solveSudoku(String input){
		log.info("Inside method solveSudoku with input param ="+ input);
		String[]data=input.split(",");
		if(data==null || data.length!=SUDOKU_ELEMENTS_NUMBERS){
			log.error("Invalid input param ="+ input);
			return null;
		}
		String[][]board=buildSudokuBoard(data);
		if(hasSudokuSolution(board,0)){
			return printSudoko(board);
		}
		log.info("Invalid Sudoko input params ");
		return null;
		
	}
	
	private static String[][] buildSudokuBoard(String[]data) {
		log.info("Inside method BuildSudokuBoard");
		String[][] board= new String[SUDOKU_ROWS_NUMBERS][SUDOKU_COLUMNS_NUMBERS];
		int count=0;
		for(int row=0;row<SUDOKU_ROWS_NUMBERS;row++){
			int column=0;
			while (column<SUDOKU_COLUMNS_NUMBERS){
				board[row][column]=data[count];
				column++;
				count++;
			}
		}
		return board;
	}
	
	private static String printSudoko(String[][]board) {
		log.info("Inside method printSudoko");
		return Arrays.stream(board).map(a -> String.join(",", a)).collect(Collectors.joining(","));
	}
	
	private static boolean hasSudokuSolution(String[][]board,int startRow){
		log.info("Inside method getSudokuSolution");
	
		for(int row=startRow;row<SUDOKU_ROWS_NUMBERS;row++){
			for(int column=0;column<SUDOKU_COLUMNS_NUMBERS;column++){
				if(board[row][column].equals(X)){
					for(int begin=1;begin<=MAX_NUMBER;begin++){
						board[row][column]=begin+"";
						if(validSudokoBoard(board,row,column)&&hasSudokuSolution(board,row)){
								return true;
						}
					}
					board[row][column]=X;
					log.error("Invalid Sudoku Board");
					return false;	
				}
			}
		}
		return true;
		
	}
	
	private static boolean validSudokoBoard(String[][]board,int row,int column){
		log.info("Inside method validSudokoBoard");
		//check if there is a duplicate number in the giving row
		boolean[]rowNumbers=new boolean[9];
		for(int col=0;col<SUDOKU_COLUMNS_NUMBERS;col++){
			if (!board[row][col].equals(X)) {
				if (rowNumbers[Integer.valueOf(board[row][col]) - 1])
					return false;
				rowNumbers[Integer.valueOf(board[row][col]) - 1] = true;
			}
		}
		
		//check if there is a duplicate number in the giving column
		boolean[]colNumbers=new boolean[9];
		for(int r=0;r<SUDOKU_ROWS_NUMBERS;r++){
			if (!board[r][column].equals(X)) {
			if(colNumbers[Integer.valueOf(board[r][column])-1])
				return false;
			colNumbers[Integer.valueOf(board[r][column])-1]=true;
			}
		}
		
		//check if there is a duplicate number in the 3*3 cells
		boolean[]cellsNumbers=new boolean[9];
		for(int r=(row/3)*3;r<(row/3)*3+3;r++){
			for(int col=(column/3)*3;col<(column/3)*3+3;col++){
			if (!board[r][col].equals(X)) {	
				if(cellsNumbers[Integer.valueOf(board[r][col])-1])
					return false;
				cellsNumbers[Integer.valueOf(board[r][col])-1]=true;
				}
			}
		}
		
		return true;
	}
	/*public static void main(String[] args) {
		String result="4,3,5,2,6,9,7,8,1,6,8,2,5,7,1,4,9,3,1,9,7,8,3,4,5,6,2,8,2,6,1,9,5,3,4,7,3,7,4,6,8,2,9,1,5,9,5,1,7,4,3,6,2,8,5,1,9,3,2,6,8,7,4,2,4,8,9,5,7,1,3,6,7,6,3,4,1,8,2,5,9";
		String input ="x,x,x,2,6,x,7,x,1,6,8,x,x,7,x,x,9,x,1,9,x,x,x,4,5,x,x,8,2,x,1,x,x,x,4,x,x,x,4,6,x,2,9,x,x,x,5,x,x,x,3,x,2,8,x,x,9,3,x,x,x,7,4,x,4,8,9,5,x,x,3,6,7,x,3,x,1,8,x,x,x";
		System.out.println(solveSudoku(input).equals(result));
	}*/
}
