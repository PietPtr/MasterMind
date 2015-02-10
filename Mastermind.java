/*License:
 * _     _  ___      .  _     __
 *| \ | / \  |  |_|  | | \ | /
 *|  \| \_/  |  | |  | |  \| \_]
 * 
 */

/* Mastermind
 * 
 * Main class: Mastermind
 * 	- game loop
 * 
 * Board class: Board
 *  - int width (dus difficulty)
 *  - int length (dus turns)
 *  - board array (based on width/length)
 *  - printBoard(): geeft totaal bord weer op de console
 *  - update():
 * 		: vraagt user om input
 *      : rekent output uit
 *      : -> checkt hoeveel goed zijn in zowel positie als kleur
 *      : -> checkt hoeveel goed zijn in kleur
 *      : -> checkt of de user het helemaal goed heeft en het spel is afgelopen
 *  - generateAnswer(): 
 *  	: 1 keer per game gebruikt om het antwoord te maken
 *  	: gebruikt random library om random characters te generaten
 *  	: leidt hoeveelheid characters af uit de width * 2
 *  	: -> max aantal is 26 (alfabet) (Char 65 t/m 90)
 *  
 * Guess & Answer class: Guess (mss betere naam nodig)
 *  - string van size lengte gevuld met characters
 *  - het board bestaat uit [lengt] lines en een line bestaat uit [width] chars
 */

package mastermind;

import java.io.IOException;
import java.util.Scanner;

public class Mastermind 
{
	private static int boardWidth = 6;
	private static int boardLength = 24;
	private static Board board = new Board(boardWidth, boardLength);
	
	public static void main(String[] args) 
	{
		boolean done = false;
		GameEnd gameEnd = GameEnd.NONE;
		int turn = 0;
		
		Scanner scanner = new Scanner(System.in);
		
		//Game loop BGFGEB
		while (!done)
		{
			System.out.println("Available letters: " + getLetters(board.getWidth()));
			System.out.println("Enter your guess (" + board.getWidth() + " letters long): ");
			String guess = scanner.nextLine();
			
			Line guessLine = new Line(new char[board.getWidth()]);
			
			switch(guessLine.setContent(guess, board))
			{
			case 0: if (board.addToBoard(guessLine) == 1)
					{
						gameEnd = GameEnd.LOST;
						break;
					}
					
					board.setBoardResults(turn, board.compareGuessAndAnswer(guessLine));
					board.displayBoard();
					
					if (new String(board.getAnswer().getContent()).equals(new String(guessLine.getContent())))
					{
						gameEnd = GameEnd.WON;
						break;
					}
					
					turn++;
					
					break;
			case 1: System.out.println("Wrong length. Your guess should be " + board.getWidth() + " letters long.");
					break;
			case 2: System.out.println("Wrong letters. You can only guess " + getLetters(board.getWidth()));
			}
			
			if (gameEnd == GameEnd.WON)
			{
				System.out.println("You cracked the code!");
				done = true;
			}
			else if (gameEnd == GameEnd.LOST)
			{
				System.out.println("You ran out of turns. You lost.");
				done = true;
			}
		}
	}
	
	public static String getLetters(int width)
	{
		String letters = "";
		
		for (int i = 0; i < (width * 2 > 26 ? 26 : width * 2); i++)
		{
			letters += (char)(i + 65);
		}
		
		return letters;
	}

	public static int getBoardWidth() 
	{
		return boardWidth;
	}

	public static Board getBoard() 
	{
		return board;
	}
}

