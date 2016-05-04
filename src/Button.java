import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.System.out;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Button
{
	String name;
	int xCoord;
	int yCoord;
	String label;
	JButton placement;
	String status = "Empty";
	String boardPlayer;
	
	
	public Button (String name, int x, int y, String label, JButton button, String boardPlayer)
	{
		this.name = name;
		xCoord = x;
		yCoord = y;
		this.label = label;
		placement = button;
		placement.setBackground(Color.cyan);
		placement.addActionListener(new buttonClick());
		this.boardPlayer = boardPlayer;
	}
	public class buttonClick implements ActionListener
	{
		
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if (PlayBattleships.stageOfGame==1||PlayBattleships.stageOfGame==2)
			{
				PlayBattleships.pb.placeShip(boardPlayer, xCoord, yCoord);
			}
			
			
		}
		
	}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public int getXCoord() {return xCoord;}
	public void setXCoord(int xCoord) {this.xCoord = xCoord;}
	public int getYCoord() {return yCoord;}
	public void setYCoord(int yCoord) {this.yCoord = yCoord;}
	public String getLabel() {return label;}
	public void setLabel(String label) {this.label = label;}
	public JButton getPlacement() {return placement;}
	public void setPlacement(JButton placement) {this.placement = placement;}
	public String getStatus() {return status;}
	public void setStatus(String status) {this.status = status;}
}
