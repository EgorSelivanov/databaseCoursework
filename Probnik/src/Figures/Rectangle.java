package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.transform.Translate;

public class Rectangle {
    private double x;
    private double y;
    private double width;
    private double height;
    private Color clr;
    final int x_max = 721;
    final int y_max = 761;
    private Color[] clr_mas = {Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.LIME,
            Color.MAROON, Color.NAVY, Color.YELLOW, Color.MAGENTA,Color.TEAL, Color.CYAN, Color.PURPLE, Color.OLIVE};
    private String color_rect_str;

    public Rectangle(Color clr, double x, double y, double width, double height){
        this.clr = clr;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Rectangle(){
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
            color_rect_str = "Красный";
        else if (this.clr == Color.BLACK)
            color_rect_str = "Чёрный";
        else if (this.clr == Color.BLUE)
            color_rect_str = "Голубой";
        else if (this.clr == Color.YELLOW)
            color_rect_str = "Жёлтый";
        else if (this.clr == Color.GREEN)
            color_rect_str = "Зелёный";
        else if (this.clr == Color.LIME)
            color_rect_str = "Лайм";
        else if (this.clr == Color.MAROON)
            color_rect_str = "Бордовый";
        else if (this.clr == Color.NAVY)
            color_rect_str = "Темно-синий";
        else if (this.clr == Color.MAGENTA)
            color_rect_str = "Пурпурный";
        else if (this.clr == Color.TEAL)
            color_rect_str = "Бирюзовый";
        else if (this.clr == Color.CYAN)
            color_rect_str = "Ярко-голубой";
        else if (this.clr == Color.OLIVE)
            color_rect_str = "Оливковый";
        else if (this.clr == Color.PURPLE)
            color_rect_str = "Фиолетовый";
        else
            color_rect_str = this.clr.toString();
        return color_rect_str;
    }

    public void Show(GraphicsContext context){
        context.setFill(clr);
        context.fillRect(x,y,width,height);
    }

    public void MoveTo(double x_move, double y_move, Rectangle rct, GraphicsContext context){
        this.x += x_move;
        this.y += y_move;
        rct.Show(context);
    }

}
