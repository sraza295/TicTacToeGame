import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame  
{
	public static void main(String[] args) 
	{	
		TicTacToe ttc = new TicTacToe();
		Scanner s = new Scanner(System.in);
		int x=0,y=0; // these variable is used for enter the position of array by HUMAN
		int m=0, n=0;
		do
		{	
			if(ttc.player==ttc.X)
			{
				//When human's turn, then come to this part
				System.out.println("Your Turn"); // It will print human's turn
				System.out.println("Enter x and y places"); // we ask for enter x & y position [x][y]
				x=s.nextInt();
				y=s.nextInt();			
				ttc.putPosition(x, y); // This method is used for assigning the position
				System.out.println(ttc.boardDesign());
				ttc.checkWinner();
			}
			else
			{
				//When Machine's turn, then come to this part
				Random rand = new Random();
				m = rand.nextInt(3);
				n = rand.nextInt(3);
				ttc.putPosition(m, n);
				if(ttc.machine_turn_count==0)
				{
					System.out.println("Machine Turn"); // It will print machine's turn
					System.out.println(ttc.boardDesign());
					System.out.println("_____________________________");
				}
				ttc.checkWinner();
			}
			
		}while(ttc.isEmpty);
	}
}

class TicTacToe
{
	public static final int X = 1, O = -1; // X for human turn and O for Machine turn
	public static final int EMPTYBOARD = 0; // This is used for check the position is occupied or not
	public int machine_turn_count=0; 
	static int c=0;
	static {
		// From this, it will decide which turn will be first either human or machine.
		Random random = new  Random();
		 c= random.nextBoolean() ? X : O; 
	}
	public int player = c;
	private int[][] arr_board = new int[3][3]; // Take 2d Array for Tic-Tac-Toe Board
	public boolean isEmpty = false; // This is used to check the winner
	
	public void putPosition(int x, int y)
	{
		if(x<0 || x>2 || y<0 || y>2)
		{
			// if we enter invalid position, come to this part.
			System.out.println("Invalid board position");
			return;
		}
		if(arr_board[x][y] != EMPTYBOARD)
		{	
			// If position will be occupied by anyone, come to this part.
			if(player==-1) // If current player is Machine then increment to 'macine_turn_count' by 1.
				machine_turn_count++;
			else
				System.out.println("This position is occupied by Machine"); // This message will be shown to human only.
				
			return;
		}
		arr_board[x][y] = player; // Here we store the position of human's or machine's
		if(player==-1) // If current player is Machine then 'macine_turn_count' assign to zero
			machine_turn_count=0;
		player = -player;
		
	}
	
	public String boardDesign()
	{
		// In this, we print the Board(How the board will be look)
		StringBuilder s = new StringBuilder();
		isEmpty = false;
		s.append("\n-------------\n");
		
		for(int i=0;i<3;i++)
		{
			s.append("|");
			for(int j=0;j<3;j++)
			{
				
				switch(arr_board[i][j])
				{
				case X: 
					s.append(" X ");
					break;
				case O: 
					s.append(" O ");
					break;
				case EMPTYBOARD: 
					s.append("   ");
					isEmpty=true;
					break;
				}
				if(j<=2)
				{
					s.append("|");
				}
				
			}
			if(i<=2)
			{
				s.append("\n-------------\n");
			}
		}
		return s.toString();
	}
	
	public boolean isWin(int player)
	{
		// We check if the value of a horizontally, vertically or diagonally is 3 or -3 then Winner will be decided. 
		return ((arr_board[0][0] + arr_board[0][1] + arr_board[0][2] == player*3) ||
				(arr_board[1][0] + arr_board[1][1] + arr_board[1][2] == player*3) ||
				(arr_board[2][0] + arr_board[2][1] + arr_board[2][2] == player*3) ||
				(arr_board[0][0] + arr_board[1][0] + arr_board[2][0] == player*3) ||
				(arr_board[0][1] + arr_board[1][1] + arr_board[2][1] == player*3) ||
				(arr_board[0][2] + arr_board[1][2] + arr_board[2][2] == player*3) ||
				(arr_board[0][0] + arr_board[1][1] + arr_board[2][2] == player*3) ||
				(arr_board[2][0] + arr_board[1][1] + arr_board[0][2] == player*3));
	}
	
	public void checkWinner()
	{
		if(isWin(X)) // If human wins, come to this portion
		{
			System.out.println("\n You wins...!!");
			isEmpty=false;
		}
		else if(isWin(O)) // If machine wins, come to this portion
		{
			System.out.println("\n Machine wins...!!");
			isEmpty=false;
		}
		else // If match is tie, come to this portion
		{
			if(!isEmpty)
			{
				System.out.println("its a tie");
			}
			
		}
	}
	
	
}
