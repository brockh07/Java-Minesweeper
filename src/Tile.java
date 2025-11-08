public class Tile
{
    private int numAdj;//Variable for the number of adjacent tiles with bombs
    private String visStr;//Variable for the stored visible string

    public Tile(){//Creates object with two values
        visStr = "?";
        numAdj = 0;
    }

    public void setBomb(){//Sets the numAdj to the bomb value
        numAdj = -1;
    }

    public String getVisStr() {//Gets the visible string
        return visStr;
    }

    public void setVisStr(String io){//Sets the visible string to input
        visStr = io;
    }

    public int getNumAdj() {//Gets the number of adjacent tiles
        return numAdj;
    }

    public void setNumAdj(int n) {//Sets the number of to input
        numAdj = n;
    }
}
