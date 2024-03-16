import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

class SprayObject {
    //private static final int SPRAY_LIFETIME = 1000;

    private int x;
    private int y;
    private Color color;

    public SprayObject (int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void update(int delta) {
        //lifeTime += delta;
    }
/*
        public boolean isExpired() {
            return lifeTime >= SPRAY_LIFETIME;
        }*/

    public void render(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, 1, 1);
    }
}