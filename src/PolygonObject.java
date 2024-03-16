import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

public class PolygonObject {
    private float x;
    private float y;
    private float width;
    private float height;
    private int vertexCount;
    private Color fillColor;
    private Color borderColor;
    private float borderWidth;
    private String id;
    private float rotation;

    private Polygon polygon;

    public PolygonObject(float x, float y, float width, float height, int vertexCount, Color fillColor, Color borderColor,
                         float borderWidth, String id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.vertexCount = vertexCount;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
        this.borderWidth = borderWidth;
        this.id = id;
        this.rotation = 0.0f;

        createPolygon();
    }

    private void createPolygon() {
        polygon = new Polygon();

        float radius = (float) (Math.sqrt(width * width + height * height) / 2.0);
        float centerX = x + width / 2.0f;
        float centerY = y + height / 2.0f;

        for (int i = 0; i < vertexCount; i++) {
            float angle = (float) (2.0 * Math.PI * i / vertexCount + rotation);
            float vertexX = centerX + radius * (float) Math.cos(angle);
            float vertexY = centerY + radius * (float) Math.sin(angle);
            polygon.addPoint(vertexX, vertexY);
        }
    }

    public void render(Graphics g) {
        g.setColor(fillColor);
        g.fill(polygon);

        g.setColor(borderColor);
        g.setLineWidth(borderWidth);
        g.draw(polygon);
    }

    public void setX(float x) {
        this.x = x;
        createPolygon();
    }

    public void setY(float y) {
        this.y = y;
        createPolygon();
    }

    public void setWidth(float width) {
        this.width = width;
        createPolygon();
    }

    public void setHeight(float height) {
        this.height = height;
        createPolygon();
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
        createPolygon();
    }

    public void setVertexCount(int vertexCount) {
        this.vertexCount = vertexCount;
        createPolygon();
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

    public float getRotation() {
        return rotation;
    }
}
