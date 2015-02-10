package mastermind;

import java.util.Random;

public class Board 
{
	private int width;
	private int length;
	
	private Line answer;
	
	private Line[] boardLines;
	private Result[] boardResults;
	
	private Random random = new Random();
	
	public Board(int width, int length)
	{
		this.width = width;
		this.length = length;
		
		this.answer = generateAnswer();
		
		this.boardLines = new Line[length];
		this.boardResults = new Result[length];
		
		for (int i = 0; i < this.boardLines.length; i++)
		{
			this.boardLines[i] = new Line(new char[this.width]);
			this.boardResults[i] = new Result(new char[this.width]);
		}
	}
	
	public Line generateAnswer()
	{
		char[] answerCharshars = new char[this.width];
		
		for (int i = 0; i < answerCharshars.length; i++)
		{
			answerCharshars[i] = (char)((random.nextInt(this.width * 2 > 26 ? 26 : this.width * 2)) + 65);
		}
		
		Line answer = new Line(answerCharshars);
		
		return answer;
	}
	
	public int addToBoard(Line line)
	{
		for (int i = 0; i < this.boardLines.length; i++)
		{
			if (this.boardLines[i].isEmpty())
			{
				this.boardLines[i] = line;
				return 0;
			}
		}
		
		return 1;
	}
	
	public void displayBoard()
	{
		for (int i = 0; i < this.width * 2 + 1; i++) 
		{ 
			System.out.print("_"); 
		}
		System.out.print("\n");
		
		for (int i = boardLines.length - 1; i >= 0; i--)
		{
			for (int j = 0; j < width; j++)
			{
				System.out.print("|" + boardLines[i].getContent()[j]);
			}
			
			//create the results string
			char[] resultCharshars = boardResults[i].getResults();
			//System.out.println(resultStr);
			String resultsForUser = "";
			
			for (int j = 0; j < boardResults[i].getResults().length; j++)
			{
				if (resultCharshars[j] == 'O')
				{
					resultsForUser = 'O' + resultsForUser;
				}
				else if (resultCharshars[j] == 'X')
				{
					resultsForUser += 'X';
				}
			}
			
			System.out.println("|  [" + resultsForUser + "]");
		}
		
		for (int i = 0; i < this.width * 2 + 1; i++) 
		{ 
			System.out.print("*"); 
		}
		System.out.print("\n");
	}

	public Result compareGuessAndAnswer(Line guess)
	{
		char[] resultChars = new char[this.width];
		
		char[] answerChars = this.answer.getContent().clone();
		char[] guessChar  = guess.getContent().clone();
		
		for (int i = this.width - 1; i >= 0; i--)
		{
			if (answerChars[i] == guessChar[i])
			{
				resultChars[i] = 'O';
				answerChars[i] = '_';
			}
		}
		
		for (int i = this.width - 1; i >= 0; i--)
		{
			int index = new String(answerChars).indexOf(guessChar[i]);
			if (index != -1)
			{
				resultChars[i] = 'X';
				answerChars[index] = '_';
			}
		}
		Result result = new Result(resultChars);
		
		return result;
	}
	
	public void setBoardResults(int index, Result result)
	{
		this.boardResults[index] = result;
	}
	
	public Result getBoardResults(int index)
	{
		return this.boardResults[index];
	}
	
	public int getWidth() {
		return width;
	}

	public int getLength() {
		return length;
	}

	public Line getAnswer() {
		return answer;
	}
}
