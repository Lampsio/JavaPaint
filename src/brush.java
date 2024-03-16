import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class brush {

    private float startX, startY; // początek rysowania
    private float endX, endY; // koniec rysowania
    private Color color; // kolor pędzla
    private float size; // rozmiar pędzla

    public brush(float startX, float startY, float endX, float endY, Color color, float size) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.color = color;
        this.size = size;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.setLineWidth(size);
        g.drawLine(startX, startY, endX, endY);
    }

    public float getStartX(){
        return startX;
    }

    public float getStartY(){
        return startY;
    }

    public float getEndX(){
        return endX;
    }

    public float getEndY() {
        return endY;
    }

    public Color getColorbrush()
    {
        return color;
    }

    public float getSize()
    {
        return size;
    }
}
