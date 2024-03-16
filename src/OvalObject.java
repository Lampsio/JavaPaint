import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class OvalObject {
    private float x;
    private float y;
    private float width;
    private float height;
    private Color fillColor;
    private Color borderColor;
    private float borderSize;
    private String id;

    public OvalObject(float x, float y, float width, float height, Color fillColor, Color borderColor, float borderSize, String id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderSize = borderSize;
        this.id = id;
    }

    public void render(Graphics g) {
        g.setColor(fillColor);
        g.fillOval(x, y, width, height);

        g.setColor(borderColor);
        g.setLineWidth(borderSize);
        g.drawOval(x, y, width, height);
    }
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getborderColor() {
        return borderColor;
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
        this.borderColor = outlineColor;
    }

    public String getId() {
        return id;
    }


    public boolean isMouseOver(int mouseX, int mouseY) {
        float centerX = x + width / 2;
        float centerY = y + height / 2;

        float radiusX = width / 2;
        float radiusY = height / 2;

        float normalizedX = (mouseX - centerX) / radiusX;
        float normalizedY = (mouseY - centerY) / radiusY;

        return (normalizedX * normalizedX) + (normalizedY * normalizedY) <= 1;
        }

        
    }
