import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

class Text {
    private int x;
    private int y;
    private String text;
    private Color textColor;
    private TrueTypeFont font;

    public Text(int x, int y, String text, Color textColor, TrueTypeFont font) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.textColor = textColor;
        this.font = font;
    }

    public void render(Graphics g) {
        font.drawString(x, y, text, textColor);
    }
}