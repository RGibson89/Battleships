import javax.swing.ComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class PlayBattleships
{
	static int x;
	static int y;
	JTextField xDim=new JTextField();
	JTextField yDim=new JTextField();
	JFrame newGame = new JFrame();
	JLabel error = new JLabel("   Please try again");
	JButton newGameButton= new JButton("Confirm?");
	Board player1Board;
	Board player2Board;
	static int stageOfGame = 1;
	static int gameScale = 1;
		 /*gameScale sets how many ships each side has:
		 * 1 = one frigate,
		 * 2 = one frigate, one destroyer,
		 * 3 = two frigates, one destroyer,
		 * 4 = two frigates, two destroyers
		 * 5 = one frigate, two destroyers, one carrier,
		 * 6 = three frigates, four destroyers, one carrier,
		 * 7 = three frigates, four destroyers, two carriers
		 * 8 = six frigates, eight destroyers, three carriers
		*/
	static PlayBattleships pb;
	int noOfFrigates = 0;
	int noOfDestroyers = 0;
	int noOfCarriers = 0;
	String xS;
	String yS;
	ArrayList<Board> boardsList = new ArrayList<>();
	
	//creates and instance of PlayBattleships and starts GoGoGo
	public static void main(String[] args)
	{	
		pb= new PlayBattleships();
		pb.goGoGo();
	}
	
	//
	private void goGoGo() 
	{
		//Creates a JFrame that requires input for the board dimensions.
		newGame.setTitle("Battleships - New Game");
		newGame.add(new JLabel( "  " ));
		newGame.add(error);
		newGame.add(new JLabel( "  " ));
		newGame.add(new JLabel( "   " ));
		newGame.add(new JLabel("Set board size 1-30"));
		newGame.add(new JLabel( "   " ));
		JPanel contentPane = new JPanel();
		contentPane.add(new JLabel( "   " ));
		contentPane.add(xDim);
		contentPane.add(new JLabel( "  by " ));
		contentPane.add(yDim);
		contentPane.add(new JLabel( "   " ));
		contentPane.setLayout(new GridLayout(1,5,4,5));
		newGame.add(new JLabel( "  " ));
		newGame.add(contentPane);
		newGame.add(new JLabel( "  " ));
		newGame.add(new JLabel( "   " ));
		newGame.add(newGameButton);
		newGameButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
//When the dimensions are confirmed, test if they are integers up to 30. If not JFrame remains for new dimensions 			
				xS = xDim.getText();
				yS = yDim.getText();
				Boolean isInt = true;
				try 
				{
					x=Integer.parseInt(xS);
				}
				catch (NumberFormatException e2) 
				{
					error.setVisible(true);
					xDim.setText("");
					newGameButton.setText("Confirm?");
					isInt=false;
				}
				try 
				{
					y=Integer.parseInt(yS);
				}
			    catch (NumberFormatException e2) 
			    {
			    	error.setVisible(true);
			    	yDim.setText("");
			    	newGameButton.setText("Confirm?");
			    	isInt=false;
			    }
			    if(x>30||x<=1)
			    {	
			    	xDim.setText("");
			      	error.setVisible(true);
			      	isInt=false;
			    }
			    if(y>30||y<=1)
			    {
			    	yDim.setText("");
			   	  	error.setVisible(true);
			   	  	isInt=false;
			   	}
			    if(isInt)
			    {
			    	newGame.dispose();
		   	/*If entered dimensions are appropriate the game scale is calculated
		   	 *and a new board is made to those dimensions.
		   	 */
			    	int scale = x*y;
			    	if (scale>10){gameScale = 2;}
			    	if (scale>20){gameScale = 3;}
			    	if (scale>40){gameScale = 4;}
			    	if (scale>80){gameScale = 5;}
			    	if (scale>160){gameScale = 6;}
			    	if (scale>320){gameScale = 7;}
			    	if (scale>640){gameScale = 8;}
			    	generateFleetComposition();
			    	char[][] grid = new char[x][y];
					player1Board = new Board("player 1", grid, "sectorBoard");
					player1Board.createButtons();
					boardsList.add(player1Board);
					player2Board = new Board("player 2", grid, "sectorBoard");
					player2Board.createButtons();
					boardsList.add(player2Board);
					player2Board.playerBoard.setVisible(false);
		    	}
			}
		});
		newGame.add(new JLabel( "  " ));
		newGame.add(new JLabel( "  " ));
		newGame.add(new JLabel( "  " ));
		newGame.add(new JLabel( "  " ));
		newGame.setLayout(new GridLayout(5, 3, 1, 5));
		newGame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newGame.pack();
		newGame.setVisible(true);
		error.setVisible(false);
	}
	
	public void generateFleetComposition() 
	//populates the correct number of ships of each type in the fleet
	{
		if (gameScale==1){noOfFrigates=1;}
		if (gameScale==2){noOfFrigates=1;noOfDestroyers=1;}
		if (gameScale==3){noOfFrigates=2;noOfDestroyers=1;}
		if (gameScale==4){noOfFrigates=2;noOfDestroyers=2;}
		if (gameScale==5){noOfFrigates=1;noOfDestroyers=2;noOfCarriers=1;}
		if (gameScale==6){noOfFrigates=3;noOfDestroyers=4;noOfCarriers=1;}
		if (gameScale==7){noOfFrigates=3;noOfDestroyers=4;noOfCarriers=2;}
		if (gameScale==8){noOfFrigates=6;noOfDestroyers=8;noOfCarriers=3;}
	}
	
	
	int instance = 1;
	String shipType = "Frigate";
	int testLength = 2;
	
	public void placeShip(String boardPlayer, int xCoord, int yCoord)
	//triggered from a button press in stage 1 (for player 1 board) or 2 (for player 2)
	{
		Board board = null;
		for (Board findBoard:boardsList)
		{
			if (findBoard.getPlayer().equals(boardPlayer))
			{
				board = findBoard;
			}
		}
		String shipName = board.getReader();
		char facing = board.getFacing();
		out.println("Sector "+xCoord+","+yCoord + " selected");
		if (testForSpace(xCoord, yCoord, facing, testLength, board))
		{
			board.setAlertPane("");
			if (shipType.equals("Frigate"))
			{
				Frigate placeHolder = new Frigate(shipName, "F"+instance, xCoord, yCoord, facing, board);
			}
			else if (shipType.equals("Destroyer")) 
			{
				Destroyer placeHolder = new Destroyer(shipName, "D"+(instance-noOfFrigates), xCoord, yCoord, facing, board);
			}
			else if (shipType.equals("Carrier"))
			{
				Carrier placeHolder = new Carrier(shipName, "C"+(instance-(noOfFrigates+noOfDestroyers)), xCoord, yCoord, facing, board);
			}
			if (instance>=noOfFrigates)
			{
				shipType = "Destroyer";testLength=3;
				board.instructor.setText("Name and place a destroyer. Destroyer Name:");
			}	//once the correct number of frigates are placed destroyers are placed
			if (instance-noOfFrigates>=noOfDestroyers)
			{
				shipType = "Carrier";testLength=5;
				board.instructor.setText("Name and place a carrier. Carrier Name:");
			}	//then carriers
			instance++;
			if (instance-(noOfFrigates+noOfDestroyers)>noOfCarriers)
			{
				stageOfGame++;
				instance = 1;
				shipType = "Frigate";
				testLength = 2;
				nextStageOfGame();
				board.instructor.setText("Enemy Fleet Detected!");
				char[][] grid = new char[x][y];
				Board targetBoard = new Board(boardPlayer + " Target Grid", grid, "targetBoard");
				
			}	//then the game moves to the next stage
		}
		else
		{
			board.setAlertPane("invalid placement");
		}
	}
	
	public void nextStageOfGame()
	{
		if (stageOfGame == 2)
		{
			player1Board.playerBoard.setVisible(false);
			player2Board.playerBoard.setVisible(true);
		}
		if (stageOfGame == 3)
		{
			player1Board.playerBoard.setVisible(true);
			player2Board.playerBoard.setVisible(false);
		}
		if (stageOfGame == 4)
		{
			player1Board.playerBoard.setVisible(false);
			player2Board.playerBoard.setVisible(true);
		}
	}
	
	public boolean testForSpace(int xT, int yT, char facing, int length, Board board)
	{
	boolean answer = true;	
	for (Button testButton: board.sectors)			//null pointer exceptions not functioning. try setting answer = false if coord+/-i is outside range
		{
			if (facing == 'W')		//Integer.valueOf()
			{
				for (int i=0;i<length;i++)
				{
					if (xT+length > x+1)
					{
						answer = false;
					}
					if (testButton.getXCoord()==(xT+i)&&testButton.getYCoord()==yT) 
					{
						String status = testButton.getStatus();
						if (!status.equals("Empty"))
						{
							answer = false;
						}
					}
				}
			}
			else if (facing == 'E')
			{
				for (int i=0;i<length;i++)
				{
					if (xT-length < 0)
					{
						answer = false;
					}
					if (testButton.getXCoord()==(xT-i)&&testButton.getYCoord()==yT) 
					{
						String status = testButton.getStatus();
						if (!status.equals("Empty"))
						{
						answer = false;
						}
					}
				}
			}	
			else if (facing == 'S')
			{
				if (yT+length > y+1)
				{
					answer = false;
				}
				for (int i=0;i<length;i++)
				{
					if (testButton.getYCoord()==(yT+i)&&testButton.getXCoord()==xT) 
					{
						String status = testButton.getStatus();
						if (!status.equals("Empty"))
						{
							answer = false;
						}
					}
				}
			}	
			else if (facing == 'N')
			{	
				if (yT-length < 0)
				{
					answer = false;
				}
				for (int i=0;i<length;i++)
				{
					if (testButton.getYCoord()==(yT-i)&&testButton.getXCoord()==xT) 
					{
						String status = testButton.getStatus();
						if (!status.equals("Empty"))
						{
							answer = false;
						}
					}	
				}
			}
		}
	return answer;
	}
}