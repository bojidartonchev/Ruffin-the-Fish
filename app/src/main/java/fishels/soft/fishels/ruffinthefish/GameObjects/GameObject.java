package fishels.soft.fishels.ruffinthefish.GameObjects;

import android.graphics.Rect;

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
    public Rect getRectangle()
    {
        return new Rect(x, y, x + (int)(width * 0.75), y + (int)(height * 0.75));
    }

    public boolean intersects(GameObject obj2){
        return Rect.intersects(this.getRectangle(), obj2.getRectangle());
    }

}
