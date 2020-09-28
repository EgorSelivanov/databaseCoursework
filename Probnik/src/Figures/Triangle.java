package Figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Triangle {
    private double x1;
    private double x2;
    private double x3;
    private double y1;
    private double y2;
    private double y3;
    private Color clr;
    final int x_max = 721;
    final int y_max = 761;
    private Color[] clr_mas = {Color.RED, Color.BLUE, Color.BLACK, Color.GREEN, Color.LIME,
    Color.MAROON, Color.NAVY, Color.YELLOW, Color.MAGENTA,Color.TEAL, Color.CYAN, Color.PURPLE, Color.OLIVE};
    private String color_tr_str;

    public Triangle(Color clr, double x1, double y1, double x2, double y2, double x3, double y3){
        this.clr = clr;
        this.x1 = x1;
        this.x2 = x2;
        this.x3 = x3;
        this.y1 = y1;
        this.y2 = y2;
        this.y3 = y3;
    }

    public Triangle(){
        this.clr = clr_mas[(int) (Math.random() * 13)];
        this.x1 = Math.random() * x_max;
        this.x2 = Math.random() * x_max;
        this.x3 = Math.random() * x_max;
        this.y1 = Math.random() * y_max;
        this.y2 = Math.random() * y_max;
        this.y3 = Math.random() * y_max;
    }

    public int[] getPosition(){
        return new int[] {(int) this.x1,(int) this.y1,(int) this.x2,(int) this.y2,(int) this.x3,(int) this.y3};
    }

    public String getColor(){
        if (this.clr == Color.RED)
            color_tr_str = "Красный";
        else if (this.clr == Color.BLACK)
            color_tr_str = "Чёрный";
        else if (this.clr == Color.BLUE)
            color_tr_str = "Голубой";
        else if (this.clr == Color.YELLOW)
            color_tr_str = "Жёлтый";
        else if (this.clr == Color.GREEN)
            color_tr_str = "Зелёный";
        else if (this.clr == Color.LIME)
            color_tr_str = "Лайм";
        else if (this.clr == Color.MAROON)
            color_tr_str = "Бордовый";
        else if (this.clr == Color.NAVY)
            color_tr_str = "Темно-синий";
        else if (this.clr == Color.MAGENTA)
            color_tr_str = "Пурпурный";
        else if (this.clr == Color.TEAL)
            color_tr_str = "Бирюзовый";
        else if (this.clr == Color.CYAN)
            color_tr_str = "Ярко-голубой";
        else if (this.clr == Color.OLIVE)
            color_tr_str = "Оливковый";
        else if (this.clr == Color.PURPLE)
            color_tr_str = "Фиолетовый";
        else
            color_tr_str = this.clr.toString();
        return color_tr_str;
    }

    public void Show(GraphicsContext context){
        context.setFill(clr);
        context.fillPolygon(new double[] {x1,x2,x3}, new double[] {y1,y2,y3}, 3);

    }

    public void MoveTo(double x_move, double y_move, Triangle tr, GraphicsContext context){
        this.x1 += x_move;
        this.x2 += x_move;
        this.x3 += x_move;
        this.y1 += y_move;
        this.y2 += y_move;
        this.y3 += y_move;
        tr.Show(context);
    }
}
