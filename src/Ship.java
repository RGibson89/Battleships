import java.util.ArrayList;


public class Ship 
{
	String name;
	String shipType;
	String callsign;
	int length;
	int damage;
	int shipXCoord;
	int shipYCoord;
	char direction;
	
	
	public ArrayList<Button> getCoords(int xStart, int yStart, char facing, int length, Board board)
	//creates and arraylist of the sectors that a given ship is occupying
	{	
		ArrayList<Button> shipCoords = new ArrayList<>();
		for (Button testButton: board.sectors)
		{
			if (facing == 'N' && testButton.getXCoord() == xStart && testButton.getYCoord() <= yStart && testButton.getYCoord() > yStart-length)
			{
				shipCoords.add(testButton);
			}
			else if (facing == 'S' && testButton.getXCoord() == xStart && testButton.getYCoord() >= yStart && testButton.getYCoord() < yStart+length)
			{
				shipCoords.add(testButton);
			}
			else if (facing == 'E' && testButton.getYCoord() == yStart && testButton.getXCoord() <= xStart && testButton.getXCoord() > xStart-length)
			{
				shipCoords.add(testButton);
			}
			else if (facing == 'W' && testButton.getYCoord() == yStart && testButton.getXCoord() >= xStart && testButton.getXCoord() < xStart+length)
			{
				shipCoords.add(testButton);
			}
		}
		return shipCoords;
	}
	
	public String getName() 
		{return name;}
	public void setName(String name)
		{this.name = name;}
	public String getShipType()
		{return shipType;}
	public void setShipType(String shipType) 
		{this.shipType = shipType;}
	public int getLength()
		{return length;}
	public void setLength(int length) 
		{this.length = length;}
	public int getDamage()
		{return damage;}
	public void setDamage(int damage) 
		{this.damage = damage;}
	public int getshipXCoord() 
		{return shipXCoord;}
	public void setshipXCoord(int xCoord) 
		{this.shipXCoord = xCoord;}
	public int getshipYCoord() 
		{return shipYCoord;}
	public void setShipYCoord(int yCoord) 
		{this.shipYCoord = yCoord;}
	public char getDirection()
		{return direction;}
	public void setDirection(char direction)
		{this.direction = direction;}
	
	
	
	
	
	
	
	
	
}
