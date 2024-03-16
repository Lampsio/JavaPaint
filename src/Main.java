import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.imageout.ImageOut;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
Program Java paint stworzony przez Daniela Ślączkę Studenta Państwowej Akademii Nauk Stosowanych w Krośnie
13.06.2023r

Program Java piant created by Daniel Slaczka student of State Academy of Applied Sciences in Krosno
 */

public class Main extends BasicGame {

    private ArrayList<RectangleObject> rectangles;  // lista prostokątów
    private ArrayList<brush> brushList = new ArrayList<>(); //lista pedzli
    private ArrayList<Text> textEdits;
    private TrueTypeFont font;
    private TextField textField,textFieldsize,FieldSizeBrush,FieldPenta,Fieldsizeline;
    private String sentence="Przykladowy teskt";
    private float startX, startY, endX, endY, lastX, lastY; //punkty dla pedzla
    private float size = 3; //rozmiar pedzla
    private boolean isDrawing = false; //malowanie pedzla
    private RectangleObject currentRect;  // aktualnie rysowany prostokąt
    private boolean dragging;  // czy aktualnie przeciągamy myszką
    public Color fillColor,outlineColor;  // kolor wypełnienia prostokątów
    private Rectangle rect,rect_back; // plotno
    private Image texture,image;  //textura do wgrania
    private JFileChooser fileChooser; //wybieranie miejsca
    int result; // resultat filechoser
    private ColorChange colorchange; //wywolanie kolorow pierwszoplanowych oraz drugoplanowych
    private final Rectangle[] rectArr = new Rectangle[6]; //kolory do wybrania
    private Button bclass;
    int mouseX ;
    int mouseY;
    Input input;
    java.awt.Font awtFont;
    private int fontSize = 20;
    String id;
    private ArrayList<OvalObject> ovals; //lista elips
    private OvalObject currentOval ; //aktualnie rysowana elipsa
    private OvalObject oval;  //no null
    private boolean isDrawingOval; //czy rysowane
    private static final float ROTATION_ANGLE = 5.0f;  // kat obrotu
    private List<PolygonObject> polygons; //lista wielokatow
    private PolygonObject currentPolygon; //obecnie rysowany wielokat
    private int vertexCount=6; //liczba wielokotow
    Random random;
    private boolean isSpraying;
    private List<SprayObject > sprays;
    private static final int SPRAY_RADIUS = 20;
    private static final int SPRAY_DENSITY = 10;
    private ArrayList<LineObject> lines;
    private Vector2f start;
    private Vector2f end;
    private LineObject currentLine;
    private boolean drawingLine;
    private int Linesize=2;
    private ArrayList<Object> undoList;
    private ArrayList<Object> redoList;


    public Main(String title) {
        super(title);
    }

    @Override
    public void init(GameContainer container) throws SlickException {
        rectangles = new ArrayList<>();

        currentRect = null;
        dragging = false;
        fillColor = Color.white;
        outlineColor = Color.black;
        random = new Random();
        rect = new Rectangle(200, 100, 900, 600);
        oval = new OvalObject(0,0,0,0,Color.white,Color.white,0,"");
        rect_back = new Rectangle(rect.getX()-5,rect.getY()-5,rect.getWidth()+10,rect.getHeight()+10);
        fileChooser = new JFileChooser();
        colorchange = new ColorChange();
        bclass = new Button();
        bclass.init(container);

        for (int i = 0; i < rectArr.length; i++) {
            rectArr[i] = new Rectangle(350 + i * 60, 30, 50, 50);
        }
        image = new Image(container.getWidth(),container.getHeight());   //do save

        textEdits = new ArrayList<>();

        // Inicjalizacja czcionki
        awtFont = new java.awt.Font("Arial", java.awt.Font.PLAIN, 20);
        font = new TrueTypeFont(awtFont, true);

        textField = new TextField(container, container.getDefaultFont(), 800, 30, 200, 20);
        textField.setBackgroundColor(Color.white);
        textField.setTextColor(Color.black);
        textField.setMaxLength(100);

        textFieldsize = new TextField(container, container.getDefaultFont(), 800, 60, 200, 20);
        textFieldsize.setBackgroundColor(Color.white);
        textFieldsize.setTextColor(Color.black);
        textFieldsize.setMaxLength(100);

        FieldSizeBrush = new TextField(container, container.getDefaultFont(), 1000, 20, 200, 20);
        FieldSizeBrush.setBackgroundColor(Color.white);
        FieldSizeBrush.setTextColor(Color.black);
        FieldSizeBrush.setMaxLength(100);

        FieldPenta = new TextField(container, container.getDefaultFont(), 1000, 40, 200, 20);
        FieldPenta.setBackgroundColor(Color.white);
        FieldPenta.setTextColor(Color.black);
        FieldPenta.setMaxLength(100);

        Fieldsizeline = new TextField(container, container.getDefaultFont(), 1000, 60, 200, 20);
        Fieldsizeline.setBackgroundColor(Color.white);
        Fieldsizeline.setTextColor(Color.black);
        Fieldsizeline.setMaxLength(100);

        //textField.setCursorVisible(true);
        //elispa
        ovals = new ArrayList<>();
        isDrawingOval = false;
        ////
        //wielokat
        polygons = new ArrayList<>();
        currentPolygon = null;
        /////
        sprays = new ArrayList<>();
        //////
        lines = new ArrayList<>();
        start = new Vector2f();
        end = new Vector2f();
        currentLine = null;
        drawingLine = false;
        /////////////
        undoList = new ArrayList<>();
        redoList = new ArrayList<>();

    }

    @Override
    public void render(GameContainer container, Graphics g) throws SlickException {

        // kolor tła
        g.pushTransform();
        g.setBackground(new Color(13,42,33));

        g.setColor(new Color(114,146,134));
        g.fill(rect_back);
        ////////////////////////

        // rysuj biały prostokąt z czarną obwódką
        g.setColor(Color.white);
        g.fill(rect);
        g.setColor(Color.black);
        g.draw(rect);

        g.popTransform();
        /////////////////////////
        g.pushTransform();
        for (Text textEdit : textEdits) {
            textEdit.render(g);
        }
        g.setColor(Color.white);
        g.drawString("Program Java paint stworzony przez Daniela Slaczke Studenta Panstwowej Akademii Nauk Stosowanych w Krosnie", 10, 770);
        g.popTransform();
        /////////////////////////
        g.pushTransform();
        if(bclass.select_text) {
            textField.render(container, g);
            textFieldsize.render(container,g);
            g.drawString("Nazwa", 1010, 30);
            g.drawString("Rozmiar", 1010, 60);
        }
        g.popTransform();
        ////////////////////////
        g.pushTransform();
        if(bclass.select_brush) {
            FieldSizeBrush.render(container, g);
            g.drawString("Rozmiar", 900, 20);
        }
        g.popTransform();
        /////////////////////////
        g.pushTransform();
        if(bclass.select_poly)
        {
            FieldPenta.render(container,g);
            g.drawString("Wierzcholki", 900, 40);
        }
        /////////////////////////
        g.popTransform();

        g.pushTransform();
        if(bclass.select_line)
        {
            Fieldsizeline.render(container,g);
            g.drawString("Grubosc", 900, 60);
        }
        /////////////////////////
        g.popTransform();

        g.pushTransform();

        g.setColor(Color.yellow);
        g.fill(rectArr[0]);
        g.setColor(Color.green);
        g.fill(rectArr[1]);
        g.setColor(Color.red);
        g.fill(rectArr[2]);
        g.setColor(Color.blue);
        g.fill(rectArr[3]);
        g.setColor(Color.black);
        g.fill(rectArr[4]);
        g.setColor(Color.white);
        g.fill(rectArr[5]);

        g.popTransform();

        /////////////////////////////

        g.pushTransform();


        // rysowanie wszystkich prostokątów

        if (rect != null) {
            for (RectangleObject rect : rectangles) {
                if (rect != null) {
                    rect.render(g);
                }
            }

        }
        // rysowanie aktualnie rysowanego prostokąta (jeśli jest)
        if (currentRect != null) {
            currentRect.render(g);
        }
        g.popTransform();
        ///////////////////////////////
        //elispa
        g.pushTransform();
        if (oval != null) {
            for (OvalObject oval : ovals) {
                if (oval != null) {
                    oval.render(g);
                }
            }
        }

        if (currentOval != null) {
            currentOval.render(g);
        }
        g.popTransform();

        //////////////////////////////

        g.pushTransform();
        if (texture != null) {
            //g.drawImage(texture, rect.getX(), rect.getY());
            texture.draw(rect.getX(), rect.getY());
            //g.drawImage(texture, rect.getX(), rect.getY(), texture.getWidth() / 2, texture.getHeight() / 2, 0, 0, texture.getWidth(), texture.getHeight());
        }
        g.popTransform();

        ///////////////////////////////
        g.pushTransform();
        for (brush brush : brushList) {
            brush.draw(g);
        }
        g.popTransform();
        ///////////////////////////////
        g.pushTransform();
        for (SprayObject spray : sprays) {
            spray.render(g);
        }
        g.popTransform();
        ///////////////////////////////

        g.pushTransform();
        for (PolygonObject polygon : polygons) {
            polygon.render(g);
        }

        if (currentPolygon != null) {
            currentPolygon.render(g);
        }
        g.popTransform();

        ///////////////////////
        g.pushTransform();
        for (LineObject line : lines) {
            line.render(g);
        }

        if (currentLine != null) {
            currentLine.render(g);
        }
        g.popTransform();
        /////////

        g.pushTransform();
        colorchange.render(container,g);
        bclass.render(container,g);
        g.popTransform();

    }

    @Override
    public void update(GameContainer container, int delta) throws SlickException {
        input = container.getInput();

        colorchange.rect2Color = fillColor;
        colorchange.rect1Color = outlineColor;

        // pobieranie pozycji myszki

        mouseX = container.getInput().getMouseX();
        mouseY = container.getInput().getMouseY();

        if(bclass.select_rect) {
            if (rect.contains(mouseX, mouseY)) {
                if (dragging) {
                    // aktualizacja pozycji i rozmiaru aktualnie rysowanego prostokąta
                    int width = mouseX - (int) currentRect.getX();
                    int height = mouseY - (int) currentRect.getY();
                    currentRect.setWidth(width);
                    currentRect.setHeight(height);
                }
            } else {
                dragging = false;
            }
        }
        ///////////////////////////////////////////////////////////////
        if(bclass.select_poly)
        {
            if(rect.contains(mouseX, mouseY)) {
                if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                    if (currentPolygon == null) {
                        float x = input.getMouseX();
                        float y = input.getMouseY();
                        currentPolygon = new PolygonObject(x, y, 0, 0, vertexCount, fillColor, outlineColor, 5, "Polygon");
                    } else {
                        polygons.add(currentPolygon);
                        //
                        undoList.add(currentPolygon);
                        redoList.clear();
                        //

                        currentPolygon = null;
                    }
                }

                if (currentPolygon != null) {
                    float x = input.getMouseX();
                    float y = input.getMouseY();
                    currentPolygon.setWidth(x - currentPolygon.getX());
                    currentPolygon.setHeight(y - currentPolygon.getY());
                }

                if (input.isKeyPressed(Input.KEY_K)) {
                    rotateCurrentPolygon(ROTATION_ANGLE);
                }

                if (input.isKeyPressed(Input.KEY_L)) {
                    rotateCurrentPolygon(-ROTATION_ANGLE);
                }
            }
            if (FieldPenta.hasFocus()) {
                String Pamount = FieldPenta.getText();
                FieldSizeBrush.setFocus(false);
                try {
                    vertexCount = Integer.parseInt(Pamount); // Konwersja tekstu na liczbę całkowitą
                } catch (NumberFormatException e) {
                }

            }
            else{
                FieldPenta.setFocus(true);
            }
        }
        ////////////////////////////////////////////////////////////////
        if(bclass.select_oval)
        {
            if (rect.contains(mouseX, mouseY)){
                if (isDrawingOval) {
                    float width_Oval = mouseX - (int)currentOval.getX();
                    float height_Oval = mouseY - (int)currentOval.getY();

                    // Sprawdź czy lewy Shift jest wciśnięty
                    if ((input.isKeyDown(Input.KEY_LSHIFT) || input.isKeyDown(Input.KEY_RSHIFT))) {
                        // Ustaw taką samą szerokość i wysokość
                        float maxDimension = Math.max(Math.abs(width_Oval), Math.abs(height_Oval));
                        width_Oval = Math.signum(width_Oval) * maxDimension;
                        height_Oval = Math.signum(height_Oval) * maxDimension;
                    }

                    currentOval.setWidth((int) width_Oval);
                    currentOval.setHeight((int) height_Oval);
                }
            }else {
                isDrawingOval = false;
            }
        }
        ///////////////////////////////////////////////////////////////
        if(bclass.select_spray)
        {
            if(rect.contains(mouseX, mouseY)){
                if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                    isSpraying = true;
                } else {
                    isSpraying = false;
                }

                if (isSpraying) {
                    int mouseX = input.getMouseX();
                    int mouseY = input.getMouseY();

                    for (int i = 0; i < SPRAY_DENSITY; i++) {
                        int offsetX = random.nextInt(SPRAY_RADIUS * 2) - SPRAY_RADIUS;
                        int offsetY = random.nextInt(SPRAY_RADIUS * 2) - SPRAY_RADIUS;

                        int sprayX = mouseX + offsetX;
                        int sprayY = mouseY + offsetY;

                        SprayObject spray = new SprayObject(sprayX, sprayY, fillColor);
                        sprays.add(spray);
                        //
                        undoList.add(spray);
                        redoList.clear();
                        //

                    }
                }
            }
        }
        /////////////////////////////////////////////////////////////////

        if (input.isKeyPressed(Input.KEY_S ) || bclass.select_save) {
            Save();
            bclass.select_save = false;
        }
        //////////////////////////////////////////////////////


        if (input.isKeyPressed(Input.KEY_C) || bclass.select_cler) {
            rectangles.clear();
            brushList.clear();
            /*
            if (texture != null) {
                texture.destroy();
            }*/
            textEdits.clear();
            ovals.clear();
            polygons.clear();
            sprays.clear();
            lines.clear();
            bclass.select_cler = false;
        }
        /////////////////////////////////////////////////////

        if(bclass.select_random_color)
        {
            RandonColor();
            bclass.select_random_color = false;
        }

        if(bclass.select_text) {
            if (rect.contains(mouseX, mouseY)) {

            if (input.isMousePressed(Input.MOUSE_LEFT_BUTTON)) {
                if (textField.hasFocus()) {
                    sentence = textField.getText();
                    textField.setFocus(false);
                } else if (textFieldsize.hasFocus()) {
                    try {
                        fontSize = Integer.parseInt(textFieldsize.getText());
                        java.awt.Font awtFont = new java.awt.Font("Arial", java.awt.Font.BOLD, fontSize);
                        font = new TrueTypeFont(awtFont, true);
                    } catch (NumberFormatException e) {
                        // Ignorowanie błędnych wartości
                    }
                    textFieldsize.setFocus(false);
                } else {
                    textField.setFocus(true);
                    textFieldsize.setFocus(true);
                }

                Text newTextEdit = new Text(mouseX, mouseY, sentence, outlineColor, font);
                textEdits.add(newTextEdit);
                //
                undoList.add(newTextEdit);
                redoList.clear();
                //

            }}
        }

        if(bclass.select_line)
        {
            if (rect.contains(mouseX, mouseY)) {
                if (drawingLine) {
                    end.set(mouseX, mouseY);
                    currentLine.setEndX(end.x);
                    currentLine.setEndY(end.y);
                }
            }
            if (Fieldsizeline.hasFocus()) {
                String LineSizeText = Fieldsizeline.getText();
                Fieldsizeline.setFocus(false);
                try {
                    Linesize = Integer.parseInt(LineSizeText); // Konwersja tekstu na liczbę całkowitą
                } catch (NumberFormatException e) {
                }

            }
            else{
                Fieldsizeline.setFocus(true);
            }
        }

        if(bclass.select_brush)
        {
            if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                DrawBrush();
                if (FieldSizeBrush.hasFocus()) {
                    String BrushSize = FieldSizeBrush.getText();
                    FieldSizeBrush.setFocus(false);
                    try {
                        size = Integer.parseInt(BrushSize); // Konwersja tekstu na liczbę całkowitą
                    } catch (NumberFormatException e) {
                    }

                }
                else{
                    FieldSizeBrush.setFocus(true);
                }

            } else {
                isDrawing = false;
            }
        }

        if(bclass.select_open)
        {
            open();
            bclass.select_open = false;
        }

        if(bclass.select_undo)
        {
            undo();
            bclass.select_undo = false;
        }

        if(bclass.select_redo)
        {
            redo();
            bclass.select_redo = false;
        }

        /////////////////////////////////////////////////////
        colorchange.update(container,delta);
        bclass.update(container,delta);
        /////////////////////////////////////////////////////
        int deltamouse = Mouse.getDWheel(); // Pobranie wartości przewinięcia kółka myszy

        // Powiększanie i pomniejszanie na podstawie przewinięcia kółka myszy
        if (deltamouse < 0) {
            // Przewinięto w dół - pomniejszenie
            texture = texture.getScaledCopy(0.9f); // Pomniejszenie o 10%
        } else if (deltamouse > 0) {
            // Przewinięto w górę - powiększenie
            texture = texture.getScaledCopy(1.1f); // Powiększenie o 10%
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            // Klawisz strzałki w lewo - przesuń w lewo
            texture = texture.getSubImage(5, 0, texture.getWidth() , texture.getHeight());
        } else if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            // Klawisz strzałki w prawo - przesuń w prawo
            texture = texture.getSubImage(-5, 0, texture.getWidth() , texture.getHeight());
        } else if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            // Klawisz strzałki w górę - przesuń w górę
            texture = texture.getSubImage(0, -5, texture.getWidth(), texture.getHeight() );
        } else if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
            // Klawisz strzałki w dół - przesuń w dół
            texture = texture.getSubImage(0, 5, texture.getWidth(), texture.getHeight() );
        }

    }

    @Override
    public void mousePressed(int button, int x, int y) {
        // rozpoczęcie rysowania nowego prostokąta
        if (bclass.select_rect) {
            if (rect.contains(mouseX, mouseY)) {
                // rozpoczęcie rysowania nowego prostokąta
                id = "rect" + rectangles.size();
                currentRect = new RectangleObject(mouseX, mouseY, 0, 0, fillColor, outlineColor, 5, id);
                dragging = true;
            }
        }

        if (bclass.select_oval){
            if(rect.contains(mouseX, mouseY)){
                currentOval = new OvalObject(x, y, 0, 0, fillColor, outlineColor, 2.0f, "oval" + ovals.size());
                isDrawingOval = true;
            }
        }

        if(bclass.select_line){
            if (rect.contains(mouseX, mouseY)) {
                if (button == Input.MOUSE_LEFT_BUTTON) {
                    start.set(x, y);
                    currentLine = new LineObject(start.x, start.y, start.x, start.y, Linesize, fillColor);
                    drawingLine = true;
                }
            }
        }

        if (button == 0) { // lewy przycisk myszy
            if (colorchange.rect1Selected ) {
                //rect1Color = new Color(input.getMouseX() % 256, input.getMouseY() % 256, 0);
                if (rectArr[0].contains(mouseX, mouseY)) {
                    outlineColor = Color.yellow;
                    System.out.println("zolty");
                }
                if (rectArr[1].contains(mouseX, mouseY)) {
                    outlineColor = Color.green;
                    System.out.println("zielony");
                }
                if (rectArr[2].contains(mouseX, mouseY)) {
                    outlineColor = Color.red;
                    System.out.println("czerwony");
                }
                if (rectArr[3].contains(mouseX, mouseY)) {
                    outlineColor = Color.blue;
                    System.out.println("niebieski");
                }
                if (rectArr[4].contains(mouseX, mouseY)) {
                    outlineColor = Color.black;
                    System.out.println("czarny");
                }
                if (rectArr[5].contains(mouseX, mouseY)) {
                    outlineColor = Color.white;
                    System.out.println("bialy");
                }
            } else if (colorchange.rect2Selected) {
                //rect2Color = new Color(input.getMouseX() % 256, 0, input.getMouseY() % 256);
                if (rectArr[0].contains(mouseX, mouseY)) {
                    fillColor = Color.yellow;
                    System.out.println("tak");
                }
                if (rectArr[1].contains(mouseX, mouseY)) {
                    fillColor = Color.green;
                    System.out.println("tak");
                }
                if (rectArr[2].contains(mouseX, mouseY)) {
                    fillColor = Color.red;
                    System.out.println("tak");
                }
                if (rectArr[3].contains(mouseX, mouseY)) {
                    fillColor = Color.blue;
                    System.out.println("tak");
                }
                if (rectArr[4].contains(mouseX, mouseY)) {
                    fillColor = Color.black;
                    System.out.println("tak");
                }
                if (rectArr[5].contains(mouseX, mouseY)) {
                    fillColor = Color.white;
                    System.out.println("tak");
                }
            }
        }

    }

    @Override
    public void mouseReleased(int button, int x, int y) {
        // dodanie aktualnie rysowanego prostokąta do listy
        rectangles.add(currentRect);
        //
        undoList.add(currentRect);
        redoList.clear();
        //
        currentRect = null;
        dragging = false;


        ovals.add(currentOval);
        //
        undoList.add(currentOval);
        //System.out.println("Oval: "+currentOval);
        redoList.clear();
        //
        currentOval = null;
        isDrawingOval = false;

        ////////

        if (button == Input.MOUSE_LEFT_BUTTON && drawingLine) {
            end.set(x, y);
            currentLine.setEndX(end.x);
            currentLine.setEndY(end.y);
            if(currentLine !=null) {
                lines.add(currentLine);
                //
                //System.out.println(currentLine);
                undoList.add(currentLine);
                redoList.clear();
                //
            }

            currentLine = null;
            drawingLine = false;
        }

    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_L) {
            // losowanie nowego koloru wypełnienia prostokątów
            RandonColor();
            bclass.select_random_color = false;
        }
        if (key == Input.KEY_O) {
            open();
        }
        if (key == Input.KEY_Z) {
            undo();
        } else if (key == Input.KEY_U) {
            redo();
        }

    }



    public void RandonColor()
    {
        // losowanie nowego koloru wypełnienia prostokątów
        fillColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        //colorchange.rect2Color = fillColor;
        outlineColor = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        //colorchange.rect1Color = outlineColor;
    }

    private void rotateCurrentPolygon(float angle) { //obrot wielokata
        if (currentPolygon != null) {
            float rotation = currentPolygon.getRotation();
            rotation += angle;
            currentPolygon.setRotation(rotation);
        }
    }

    public void DrawBrush(){
        if (rect.contains(mouseX, mouseY)) {
            if (!isDrawing) {
                startX = input.getMouseX();
                startY = input.getMouseY();
                lastX = startX;
                lastY = startY;
                isDrawing = true;
            } else {
                endX = input.getMouseX();
                endY = input.getMouseY();
                brush brush = new brush(lastX, lastY, endX, endY, outlineColor, size);
                brushList.add(brush);
                //
                undoList.add(brush);
                redoList.clear();
                //
                lastX = endX;
                lastY = endY;
            }
        }
    }

    public void Save()
    {
        try {
            Graphics g = image.getGraphics();

            // rysowanie białego tła
            g.setBackground(Color.white);
            g.clear();

            if (texture != null) {
                   /* if(texture.getHeight() > rect.getHeight())
                    {
                        if(texture.getWidth() > rect.getWidth()){
                            g.drawImage(texture, rect.getX(), rect.getY(), texture.getWidth() / 2, texture.getHeight() / 2, 0, 0, texture.getWidth(), texture.getHeight());
                        }
                    } */
                g.drawImage(texture, rect.getX(), rect.getY());
            }


            // rysowanie prostokątów

            if (rect != null) {
                for (RectangleObject rect : rectangles) {
                    if (rect != null) {
                        //g.drawImage(image, rect.getX(), rect.getY(),rect.getWidth(), rect.getHeight());
                        g.setColor(rect.getFillColor());
                        g.fillRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                        g.setColor(rect.getOutlineColor());
                        g.drawRect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
                    }
                }
            }

            for (brush br : brushList)
            {
                /*
                g.setColor(br.getColorbrush());
                g.drawLine(br.getStartX(), br.getStartY(), br.getEndX(), br.getEndY());
                 */
                br.draw(g);
            }
            ///////////////////////
            for (PolygonObject polygon : polygons) {
                polygon.render(g);
            }
            ////////////////////

            for (Text textEdit : textEdits) {
                textEdit.render(g);
            }
            ////////////////////
            for (SprayObject spray : sprays) {
                spray.render(g);
            }
            //////////////////////

            if (oval != null){
            for(OvalObject Roval : ovals) {
                if (Roval != null) {
                    Roval.render(g);
                }
                }
            }
            //////////////////
            for (LineObject line : lines) {
                line.render(g);
            }
            /////////////////


            // zapis do pliku
            //ImageIO.write((RenderedImage) image, "png", new File(fileName));
            // getSubImage wycina obszar image
            Image croppedImage = image.getSubImage((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight());
            ImageOut.write(croppedImage, "figure1.png");

            //g.destroy();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }


    public void open()
    {
        result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            // wczytanie pliku PNG jako tekstury
            String filePath = fileChooser.getSelectedFile().getAbsolutePath();
            try {
                texture = new Image(filePath);
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void undo() {
        Object lastObject = null;

        while (lastObject == null && !undoList.isEmpty()) {
            lastObject = undoList.remove(undoList.size() - 1);
        }

        if (lastObject != null) {
            System.out.println("Undo: " + lastObject);

            if (lastObject instanceof OvalObject) {
                ovals.remove(lastObject);
            } else if (lastObject instanceof LineObject) {
                lines.remove(lastObject);
            }else if (lastObject instanceof RectangleObject) {
                rectangles.remove(lastObject);
            }else if (lastObject instanceof brush) {
                brushList.remove(lastObject);
            }else if (lastObject instanceof Text) {
                textEdits.remove(lastObject);
            }else if (lastObject instanceof PolygonObject) {
                polygons.remove(lastObject);
            }else if (lastObject instanceof SprayObject) {
                sprays.remove(lastObject);
            }

            redoList.add(lastObject);
        }
    }

    public void redo() {
        Object redoObject = null;

        while (redoObject == null && !redoList.isEmpty()) {
            redoObject = redoList.remove(redoList.size() - 1);
        }

        if (redoObject != null) {
            System.out.println("Redo: " + redoObject);

            if (redoObject instanceof OvalObject) {
                ovals.add((OvalObject) redoObject);
            } else if (redoObject instanceof LineObject) {
                lines.add((LineObject) redoObject);
            }else if (redoObject instanceof RectangleObject) {
                rectangles.add((RectangleObject) redoObject);
            }else if (redoObject instanceof brush) {
                brushList.add((brush) redoObject);
            }else if (redoObject instanceof Text) {
                textEdits.add((Text) redoObject);
            }else if (redoObject instanceof PolygonObject) {
                polygons.add((PolygonObject) redoObject);
            }else if (redoObject instanceof SprayObject) {
                sprays.add((SprayObject) redoObject);
            }

            undoList.add(redoObject);
        }
    }


    public static void main(String[] args) throws SlickException {
        AppGameContainer app = new AppGameContainer(new Main("Java Paint"));
        app.setDisplayMode(1280, 800, false);
        //app.setTargetFrameRate(120);
        app.setShowFPS(true);
        app.setIcon("brush.png");
        app.start();
    }
}
