import java.awt.Color;
import java.util.ArrayList;


public class Frigate extends Ship
{
	public Frigate(String name, String callsign, int xCoord, int yCoord, char direction, Board board) 
	{
		this.name = name;
		shipType = "Frigate";
		this.callsign = callsign;
		this.length = 2;
		this.shipXCoord = xCoord;
		this.shipYCoord = yCoord;
		this.direction = direction;
		for (Button shipPlace: getCoords(xCoord, yCoord, direction, length, board))
		{
		shipPlace.placement.setText(this.callsign);
		shipPlace.placement.setBackground(Color.gray);
		shipPlace.setStatus("Occupied");
		}
		
	}
	
}




/*String name;
String shipType;
int length;
int damage;
int xCoord;
int yCoord;
char direction;*/