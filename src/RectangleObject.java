import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class RectangleObject {
    private float x;
    private float y;
    private int width;
    private int height;
    private Color fillColor;
    private Color outlineColor;
    private float outlineSize;
    private String id;

    public RectangleObject(float x, float y, int width, int height, Color fillColor, Color outlineColor,float outlineSize,String id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.outlineColor = outlineColor;
        this.outlineSize = outlineSize;
        this.id = id;
    }

    public void render(Graphics g) {
        g.setColor(fillColor);
        g.fillRect(x, y, width, height);
        g.setColor(outlineColor);
        g.setLineWidth(outlineSize);
        g.drawRect(x, y, width, height);
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setOutlineColor(Color outlineColor) {
        this.outlineColor = outlineColor;
    }

    public String getId() {
        return id;
    }

    public boolean col(float px, float py) {
        return px >= x && px <= x + width && py >= y && py <= y + height;
    }

}
