import org.newdawn.slick.*;
import org.newdawn.slick.geom.*;

public class ColorChange {

    private final Rectangle rect1, rect2 ;
    public Color rect1Color , rect2Color ;
    public boolean rect1Selected = false, rect2Selected = false;
    //private boolean rect1Highlighted = false, rect2Highlighted = false;
    int mouseX ;
    int mouseY ;

    public ColorChange() {
        rect1 = new Rectangle(250, 10, 50, 50);
        rect2 = new Rectangle(280, 40, 50, 50);
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {

        g.pushTransform();
        g.setColor(rect1Color);
        g.fill(rect1);
        g.setColor(rect2Color);
        g.fill(rect2);
        g.popTransform();

        g.pushTransform();
        if (rect1Selected || rect2Selected) {
            g.setColor(Color.white);
            g.setLineWidth(2f);
            Rectangle selectedRect = rect1Selected ? rect1 : rect2;
            g.draw(selectedRect);
        }
        g.popTransform();

    }

    public void update(GameContainer gc, int delta)  {
        Input input = gc.getInput();

        if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            mouseX = input.getMouseX();
            mouseY = input.getMouseY();

            if (rect1.contains(mouseX, mouseY)) {
                rect1Selected = true;
                rect2Selected = false;
            } else if (rect2.contains(mouseX, mouseY)) {
                rect2Selected = true;
                rect1Selected = false;
            } else {
                //rect1Selected = false;
                //rect2Selected = false;
            }
        }
    }
}
