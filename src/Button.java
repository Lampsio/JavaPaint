import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

import java.util.Random;

public class Button {
    private Image i_clear,i_rect,i_rand_color,i_open,i_save,i_move,i_scale,i_brush,i_text,i_oval,i_poly,i_spray,i_line,i_undo,i_redo; // obrazki przycisków
    public Rectangle b_clear,b_rect,b_rand_color,b_open,b_save,b_move,b_scale,b_brush,b_text,b_oval,b_poly,b_spray,b_line,b_undo,b_redo; // przyciski
    private int variable; // zmienna, która będzie ustawiana na 1 lub 2
    private Rectangle hoverRect; // prostokąt po najechaniu na przycisk
    private String hoverText; // tekst prostokąta po najechaniu na przycisk
    public boolean select_cler = false;
    public boolean select_rect = false;
    public boolean select_save = false;
    public boolean select_open = false;
    public boolean select_random_color = false;
    public boolean select_move = false;

    public boolean select_scale = false;
    public boolean select_brush = false;
    public boolean select_text = false;
    public boolean select_oval = false;
    public boolean select_poly = false;
    public boolean select_spray = false;
    public boolean select_line = false;
    public boolean select_redo = false;
    public boolean select_undo = false;

    int click ,click_brush,click_text,click_oval,click_poly,click_spray,click_line= 0;


    public Button() throws SlickException {


        i_clear = new Image("kosz.png");
        i_rect = new Image("prostokat.png");
        i_rand_color = new Image("los.png");
        i_open = new Image("otworz.png");
        i_save = new Image("Zapisz.png");
        i_move = new Image("001-paint.png");
        i_scale = new Image("001-paint.png");
        i_brush = new Image("pedzel.png");
        i_text = new Image("tekst.png");
        i_oval = new Image("Elipsa.png");
        i_poly = new Image("wielokat.png");
        i_spray = new Image("spray.png");
        i_line = new Image("linia.png");
        i_redo = new Image("redo.png");
        i_undo= new Image("undo.png");

        b_rand_color = new Rectangle(750, 40, i_rect.getWidth(), i_rect.getHeight());

        b_clear = new Rectangle(40 , 100, i_clear.getWidth(), i_clear.getHeight());
        b_rect = new Rectangle(40, 150, i_rect.getWidth(), i_rect.getHeight());
        b_open = new Rectangle(90,100,i_open.getWidth(),i_save.getHeight());
        b_save = new Rectangle(90,150,i_save.getWidth(),i_open.getHeight());
        b_move = new Rectangle(40,200,i_save.getWidth(),i_open.getHeight());
        b_scale = new Rectangle(90,200,i_save.getWidth(),i_open.getHeight());
        b_brush = new Rectangle(40,250,i_brush.getWidth(),i_brush.getHeight());
        b_text = new Rectangle(90,250,i_text.getWidth(), i_text.getHeight());
        b_oval = new Rectangle(40,300,i_oval.getWidth(), i_oval.getHeight());
        b_poly = new Rectangle(90,300,i_poly.getWidth(), i_poly.getHeight());
        b_spray = new Rectangle(40,350,i_spray.getWidth(),i_spray.getHeight());
        b_line = new Rectangle(90,350, i_line.getWidth(), i_line.getHeight());
        b_redo = new Rectangle(90,400, i_redo.getWidth(), i_rect.getHeight());
        b_undo = new Rectangle(40,400,i_undo.getWidth(), i_undo.getHeight());
    }

    public void init(GameContainer gc) throws SlickException {
        gc.getInput().addMouseListener(new org.newdawn.slick.InputListener() {
            @Override
            public void keyPressed(int i, char c) {

            }

            @Override
            public void keyReleased(int i, char c) {

            }

            @Override
            public void controllerLeftPressed(int i) {

            }

            @Override
            public void controllerLeftReleased(int i) {

            }

            @Override
            public void controllerRightPressed(int i) {

            }

            @Override
            public void controllerRightReleased(int i) {

            }

            @Override
            public void controllerUpPressed(int i) {

            }

            @Override
            public void controllerUpReleased(int i) {

            }

            @Override
            public void controllerDownPressed(int i) {

            }

            @Override
            public void controllerDownReleased(int i) {

            }

            @Override
            public void controllerButtonPressed(int i, int i1) {

            }

            @Override
            public void controllerButtonReleased(int i, int i1) {

            }

            @Override
            public void mouseMoved(int oldx, int oldy, int newx, int newy) {
                if (b_clear.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 120, 30);
                    hoverText = "Wyczysc";
                } else if (b_rect.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 120, 30);
                    hoverText = "Prostokat";
                } else if (b_rand_color.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 180, 30);
                    hoverText = "Losowanie Koloru";
                } else if (b_open.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 120, 30);
                    hoverText = "otworz obraz";
                } else if (b_save.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 180, 30);
                    hoverText = "zapisz plik jako png";
                }else if (b_brush.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 180, 30);
                    hoverText = "Pędzel";
                }else if (b_text.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 180, 30);
                    hoverText = "Pisz Teskt";
                }else if (b_oval.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 180, 30);
                    hoverText = "Elipsa";
                }else if (b_poly.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 180, 30);
                    hoverText = "Wielokat foremny";
                }else if (b_spray.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 120, 30);
                    hoverText = "Spray";
                }else if (b_line.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 120, 30);
                    hoverText = "Linia";
                }else if (b_undo.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 120, 30);
                    hoverText = "Undo";
                }else if (b_redo.contains(newx, newy)) {
                    hoverRect = new Rectangle(newx , newy - 30, 120, 30);
                    hoverText = "Redo";
                }
                else {
                    hoverRect = null;
                    hoverText = null;
                }
            }

            @Override
            public void mouseWheelMoved(int i) {

            }

            @Override
            public void mouseClicked(int i, int i1, int i2, int i3) {

            }

            @Override
            public void mousePressed(int button, int x, int y) {
                if (button == 0) { // lewy przycisk myszy
                    if (b_clear.contains(x, y)) {
                        select_cler = true;
                        System.out.println("wyczyszczono");
                    } else if (b_rect.contains(x, y)) {
                        select_rect = true;
                        select_brush = false;
                        select_text = false;
                        select_oval = false;
                        select_poly = false;
                        select_spray = false;
                        System.out.println("rysowanie prostokatow");
                        if(click==1)
                        {
                            select_rect = false;
                            click = 0;
                        }
                        else {
                            click++;
                        }
                    } else if (b_rand_color.contains(x, y)) {
                        select_random_color = true;
                        System.out.println("Losowanie kolorow");
                    }else if (b_undo.contains(x, y)) {
                        select_undo = true;
                        System.out.println("Undo");
                    }else if (b_redo.contains(x, y)) {
                        select_redo = true;
                        System.out.println("Redo");
                    }else if (b_open.contains(x, y)) {
                        select_open = true;
                        System.out.println("Losowanie kolorow");
                    }else if (b_save.contains(x, y)) {
                        select_save = true;
                        System.out.println("Losowanie kolorow");
                    }else if (b_move.contains(x, y)) {
                        select_move = true;
                        select_rect = false;
                        System.out.println("Losowanie kolorow");
                    }else if (b_brush.contains(x, y)) {
                        select_brush = true;
                        select_rect = false;
                        select_text = false;
                        select_line = false;
                        select_oval = false;
                        select_poly = false;
                        select_spray = false;
                        System.out.println("pedzel");
                        if(click_brush==1)
                        {
                            select_rect = false;
                            click_brush = 0;
                        }
                        else {
                            click_brush++;
                        }
                    }else if (b_poly.contains(x, y)) {
                        select_poly = true;
                        select_brush = false;
                        select_rect = false;
                        select_line = false;
                        select_text = false;
                        select_oval = false;
                        select_spray = false;
                        System.out.println("pedzel");
                        if(click_poly==1)
                        {
                            select_poly = false;
                            click_poly = 0;
                        }
                        else {
                            click_poly++;
                        }
                    }else if (b_oval.contains(x, y)) {
                        select_oval = true;
                        select_brush = false;
                        select_rect = false;
                        select_line = false;
                        select_text = false;
                        select_poly = false;
                        select_spray = false;
                        System.out.println("oval");
                        if(click_oval==1)
                        {
                            select_oval = false;
                            click_oval = 0;
                        }
                        else {
                            click_oval++;
                        }
                    }else if (b_spray.contains(x, y)) {
                        select_spray = true;
                        select_brush = false;
                        select_line = false;
                        select_rect = false;
                        select_text = false;
                        select_oval = false;
                        select_poly = false;
                        System.out.println("pedzel");
                        if(click_spray==1)
                        {
                            select_spray = false;
                            click_spray = 0;
                        }
                        else {
                            click_spray++;
                        }
                    }else if (b_line.contains(x, y)) {
                        select_line = true;
                        select_brush = false;
                        select_rect = false;
                        select_text = false;
                        select_oval = false;
                        select_poly = false;
                        select_spray = false;
                        System.out.println("pedzel");
                        if(click_line==1)
                        {
                            select_line = false;
                            click_line = 0;
                        }
                        else {
                            click_line++;
                        }
                    }else if (b_text.contains(x, y)) {
                        //select_move = false;
                        select_line = false;
                        select_rect = false;
                        select_brush = false;
                        select_text = true;
                        select_oval = false;
                        select_poly = false;
                        select_spray = false;
                        System.out.println("Wpisywanie tekstu");
                        if(click_text==1)
                        {
                            select_text = false;
                            click_text = 0;
                        }
                        else {
                            click_text++;
                        }
                    }
                }
            }
            @Override
            public void mouseReleased(int button, int x, int y) {}
            @Override
            public void mouseDragged(int oldx, int oldy, int newx, int newy) {}
            @Override
            public void setInput(Input input) {}
            @Override
            public boolean isAcceptingInput() { return true; }
            @Override
            public void inputEnded() {}
            @Override
            public void inputStarted() {}
        });
    }

    public void render(GameContainer gc, Graphics g) throws SlickException {

        g.pushTransform();
        g.setColor(Color.yellow);
        // rysowanie tekstur przycisków
        g.drawImage(i_clear, b_clear.getX(), b_clear.getY());
        g.drawImage(i_rect, b_rect.getX(), b_rect.getY());
        g.drawImage(i_open,b_open.getX(),b_open.getY());
        g.drawImage(i_save,b_save.getX(),b_save.getY());
        g.drawImage(i_rand_color,b_rand_color.getX(),b_rand_color.getY());
        //g.drawImage(i_move,b_move.getX(),b_move.getY());
        //g.drawImage(i_scale,b_scale.getX(),b_scale.getY());
        g.drawImage(i_brush,b_brush.getX(), b_brush.getY());
        g.drawImage(i_text,b_text.getX(),b_text.getY());
        g.drawImage(i_oval,b_oval.getX(),b_oval.getY());
        g.drawImage(i_poly,b_poly.getX(),b_poly.getY());
        g.drawImage(i_spray,b_spray.getX(), b_spray.getY());
        g.drawImage(i_line,b_line.getX(), b_line.getY());
        g.drawImage(i_undo,b_undo.getX(), b_undo.getY());
        g.drawImage(i_redo, b_redo.getX(), b_redo.getY());
        g.popTransform();

        // rysowanie białego prostokąta i tekstu po najechaniu na przycisk
        g.pushTransform();
        if (hoverRect != null && hoverText != null) {
            g.setColor(Color.white);
            g.fill(hoverRect);
            g.setColor(Color.black);
            g.drawString(hoverText, hoverRect.getX() + 10, hoverRect.getY() + 10);
        }
        g.popTransform();
    }

    public void update(GameContainer gc, int delta) throws SlickException {


    }
}
