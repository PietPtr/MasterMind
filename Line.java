package mastermind;

public class Line 
{
	private char[] content;
	private boolean empty = true;
	
	public Line(char[] content)
	{
		this.content = content;
	}
	
	public String toString()
	{
		String contentString = "";
		
		for (int i = 0; i < this.content.length; i++)
		{
			contentString += this.content[i];
		}
		
		return contentString;
	}
	
	public char[] getContent()
	{
		return content;
	}
	
	public int setContent(String input, Board board)
	{
		char[] letters = (input.toUpperCase()).toCharArray();
		String allowedLetters = Mastermind.getLetters(board.getWidth());
		
		if (letters.length != Mastermind.getBoard().getWidth())
		{
			System.out.println(letters.length);
			return 1; // Wrong length
		}
		
		for (int i = 0; i < letters.length; i++)
		{
			if (allowedLetters.indexOf(letters[i]) == -1)
			{
				return 2; // Wrong letters
			}
		}
		
		this.content = letters;
		this.empty = false;
		
		return 0; //Everything okay
	}

	public boolean isEmpty() {
		return empty;
	}
}

