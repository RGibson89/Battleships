import java.awt.Color;


public class Destroyer extends Ship
{
	public Destroyer(String name, String callsign, int xCoord, int yCoord, char direction, Board board) 
	{
		this.name = name;
		shipType = "Destroyer";
		this.callsign = callsign;
		this.length = 3;
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
 *String shipType;
 *int length;
 *int damage;
 *int xCoord;
 *int yCoord;
 *char direction;
 */