public class Square {
    public String name;
    public String type;
    public int price;
    public int squarePos;
    public String owner;
    public int rent;

    public Square(String name, int squarePos, String type)
    {
        this.name = name;
        this.squarePos = squarePos;
        this.type = type;
        owner = null;

    }

}
