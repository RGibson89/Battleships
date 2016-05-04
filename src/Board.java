import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Board
{
	String player;
	char[][] grid;
	JFrame playerBoard;
	int bX = 1;
	int bY = PlayBattleships.y;
	ArrayList <Button> sectors =new ArrayList<>();
	String boardType;
	
	JLabel instructor;
	JLabel alertPane;
	JTextField reader;
	String[] cardinals = new String[] {"North", "East", "South", "West"};
	JComboBox<String> facing;
	
	String a;
	
	//fields need to become local variables, maybe?
	public Board(String player, char[][] grid, String boardType) //Board constructor
	{
		this.player = player;
		this.grid=grid;
		this.playerBoard= new JFrame();
		this.boardType=boardType;
		/*the following lines create the board with instructions at the top,a button in
		 *each sector according to the dimensions set and an alert panel at the base.
		 */ 
		instructor = new JLabel("          Name and place a frigate. Frigate Name:",SwingConstants.CENTER);
		alertPane = new JLabel("", SwingConstants.CENTER);
		reader = new JTextField("HMS Shippy McShipface", 15);
		facing = new JComboBox<String>(cardinals);
		a = reader.getText();
	}
	
	public void createButtons()
	{
		playerBoard.pack();
		JPanel top = new JPanel();
		top.add(instructor,BorderLayout.LINE_START);
		top.add(reader,BorderLayout.CENTER);
		JPanel facingPan = new JPanel();
		facingPan.add(new JLabel("Facing:"),BorderLayout.LINE_START);
		facingPan.add(facing, BorderLayout.LINE_END);
		top.add(facingPan,BorderLayout.LINE_END);
		playerBoard.add(top, BorderLayout.PAGE_START);
		JPanel playerBoardContentPane = new JPanel();
		for (char[] i:grid)
		{
			bX=1;
			for (char j:i)
			{
				Button button =new Button(bX+","+bY, bX, bY, "~", new JButton("~"), player);
				playerBoardContentPane.add(button.placement);
				sectors.add(button);
				bX++;
			}
			bY--;
		}
		playerBoard.setTitle(player+" Board");
		playerBoard.add(playerBoardContentPane,BorderLayout.CENTER);
		playerBoardContentPane.setLayout(new GridLayout(PlayBattleships.y, PlayBattleships.x, 5, 5));
		playerBoard.add(alertPane, BorderLayout.PAGE_END);
		playerBoard.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		playerBoard.pack();
		playerBoard.setVisible(true);
	}	
	//getters and setters
	public String getPlayer() 
		{return player;}

	public void setPlayer(String player) 
		{this.player = player;}

	public char[][] getGrid() 
		{return grid;}

	public void setGrid(char[][] grid) 
		{this.grid = grid;}

	public String getReader()
		{String text = reader.getText();
		return text;}
	
	public char getFacing()
		{String text = (String) facing.getSelectedItem();
		char t = text.charAt(0); 
		return t;}
	
	public void setAlertPane (String text)
		{alertPane.setText(text);}
		
	//public String getReader()
	//	{String text = reader.getText();
	//	return text;
	
}

