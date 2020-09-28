package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;



public class Oval {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color clr;
    final int x_max = 721;
    final int y_max = 761;
    private Color[] clr_mas = {Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.LIME,
            Color.MAROON, Color.NAVY, Color.YELLOW, Color.MAGENTA,Color.TEAL, Color.CYAN, Color.PURPLE, Color.OLIVE};
    private String color_oval_str;

    public Oval(Color clr, double x, double y, double width, double height){
        this.clr = clr;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Oval(){
        //this.clr = Color.color(Math.random(), Math.random(), Math.random());
        this.clr = clr_mas[(int) (Math.random() * 13)];
        this.x = Math.random() * x_max;
        this.y = Math.random() * y_max;
        this.width = Math.random() * (x_max - (int) x) + 1;
        this.height = Math.random() * (y_max - (int) y) + 1;
    }

    public int[] getPosition(){
        return new int[] {(int) this.x,(int) this.y,(int) this.width,(int) this.height};
    }

    public String getColor(){
        if (this.clr == Color.RED)
            color_oval_str = "Красный";
        else if (this.clr == Color.BLACK)
            color_oval_str = "Чёрный";
        else if (this.clr == Color.BLUE)
            color_oval_str = "Голубой";
        else if (this.clr == Color.YELLOW)
            color_oval_str = "Жёлтый";
        else if (this.clr == Color.GREEN)
            color_oval_str = "Зелёный";
        else if (this.clr == Color.LIME)
            color_oval_str = "Лайм";
        else if (this.clr == Color.MAROON)
            color_oval_str = "Бордовый";
        else if (this.clr == Color.NAVY)
            color_oval_str = "Темно-синий";
        else if (this.clr == Color.MAGENTA)
            color_oval_str = "Пурпурный";
        else if (this.clr == Color.TEAL)
            color_oval_str = "Бирюзовый";
        else if (this.clr == Color.CYAN)
            color_oval_str = "Ярко-голубой";
        else if (this.clr == Color.OLIVE)
            color_oval_str = "Оливковый";
        else if (this.clr == Color.PURPLE)
            color_oval_str = "Фиолетовый";
        else
            color_oval_str = this.clr.toString();
        return color_oval_str;
    }

    public void Show(GraphicsContext context){
        context.setFill(clr);
        context.fillOval(x, y, width, height);
    }

    public void MoveTo(double x_move, double y_move, Oval ovl, GraphicsContext context){
        this.x += x_move;
        this.y += y_move;
        ovl.Show(context);
    }
}
