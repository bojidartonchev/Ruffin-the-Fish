package fishels.soft.fishels.ruffinthefish.GameObjects;

public abstract class GameObject {

    protected int x;
    protected int y;
    protected int dy;
    protected int dx;
    protected int width;
    protected int height;

    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }
    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }

    public boolean intersects(GameObject obj2){
        int playerCenterX = x+getWidth()/2;
        int playerCenterY = y+getHeight()/2;
        int enemyCenterX = obj2.x+obj2.getHeight()/2;
        int enemyCenterY = obj2.y+obj2.getHeight()/2;
        if(Math.abs(playerCenterX-enemyCenterX)<=40*getWidth()/100 && Math.abs(playerCenterY-enemyCenterY)<=50*getHeight()/100){
            return true;
        }
        return false;
    }

}
