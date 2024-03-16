import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class LineObject {
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private int rozmiar;
    private Color kolor;

    public LineObject(float startX, float startY, float endX, float endY, int rozmiar, Color kolor) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        this.rozmiar = rozmiar;
        this.kolor = kolor;
    }

    public void render(Graphics g) {
        g.setColor(kolor);
        g.setLineWidth(rozmiar);
        g.drawLine(startX, startY, endX, endY);
    }

    public float getStartX() {
        return startX;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public float getStartY() {
        return startY;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public float getEndX() {
        return endX;
    }

    public void setEndX(float endX) {
        this.endX = endX;
    }

    public float getEndY() {
        return endY;
    }

    public void setEndY(float endY) {
        this.endY = endY;
    }

    public int getRozmiar() {
        return rozmiar;
    }

    public void setRozmiar(int rozmiar) {
        this.rozmiar = rozmiar;
    }

    public Color getKolor() {
        return kolor;
    }

    public void setKolor(Color kolor) {
        this.kolor = kolor;
    }
}
