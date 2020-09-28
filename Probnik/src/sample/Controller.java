package sample;

import Figures.Oval;
import Figures.Rectangle;
import Figures.Triangle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.css.converter.ColorConverter;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.util.*;


public class Controller {
    private int count_triangles = 0;
    private int count_rectangles = 0;
    private int count_ovals = 0;
    private Color color_str;
    @FXML
    private Label lab_error;
    @FXML
    private Button but_triangle_create;
    @FXML
    private TextField trian_x1;
    @FXML
    private TextField trian_x2;
    @FXML
    private TextField trian_x3;
    @FXML
    private TextField trian_y1;
    @FXML
    private TextField trian_y2;
    @FXML
    private TextField trian_y3;
    @FXML
    private Button but_triangle_random;

    @FXML
    private Canvas canvas1;

    private GraphicsContext gc;

    @FXML
    private Button but_rectangle_create;
    @FXML
    private TextField rect_x;
    @FXML
    private TextField rect_y;
    @FXML
    private TextField rect_width;
    @FXML
    private TextField rect_height;
    @FXML
    private Button but_rectangle_random;

    @FXML
    private Button but_oval_create;
    @FXML
    private TextField oval_x;
    @FXML
    private TextField oval_y;
    @FXML
    private TextField oval_width;
    @FXML
    private TextField oval_height;
    @FXML
    private Button but_oval_random;

    @FXML
    private ComboBox choice_del;
    @FXML
    private Button but_delete;
    @FXML
    private Button but_delete_all;

    @FXML
    private ComboBox choice_moove;
    @FXML
    private TextField x_moove;
    @FXML
    private TextField y_moove;
    @FXML
    private Button but_moove;
    @FXML
    private Button but_moove_random;

    @FXML
    private ColorPicker color_pick;



    private HashMap<String, Triangle> triangles_map = new HashMap<>();
    private HashMap<String, Rectangle> rectangles_map = new HashMap<>();
    private HashMap<String, Oval> ovals_map = new HashMap<>();

    @FXML
    private void triangle_create() {
        gc = canvas1.getGraphicsContext2D();
        try {
            Triangle tr = new Triangle(color_pick.getValue(),
                    Double.parseDouble(trian_x1.getText()),
                    Double.parseDouble(trian_y1.getText()),
                    Double.parseDouble(trian_x2.getText()),
                    Double.parseDouble(trian_y2.getText()),
                    Double.parseDouble(trian_x3.getText()),
                    Double.parseDouble(trian_y3.getText()));
            tr.Show(gc);
            count_triangles++;
            String color_tr_1 = tr.getColor();
            triangles_map.put("Треугольник №" + count_triangles + " " +
                    color_tr_1, tr);
            choice_moove.getItems().addAll("Треугольник №" + count_triangles + " " +
                    color_tr_1);
            choice_del.getItems().addAll("Треугольник №" + count_triangles + " " +
                    color_tr_1);
        } catch (Exception ex) {
            System.out.println("Введите числа!");
        }
    }

    @FXML
    private void triangle_random() {
        gc = canvas1.getGraphicsContext2D();
        Triangle tr_1 = new Triangle();
        tr_1.Show(gc);
        count_triangles++;
        String color_tr_1 = tr_1.getColor();
        triangles_map.put("Треугольник №" + count_triangles + " "+ color_tr_1, tr_1);
        choice_moove.getItems().addAll("Треугольник №" + count_triangles + " " +
                color_tr_1);
        choice_del.getItems().addAll("Треугольник №" + count_triangles + " " +
                color_tr_1);
    }

    @FXML
    private void rectangle_create() {
        gc = canvas1.getGraphicsContext2D();
        try {
            Rectangle rct = new Rectangle(color_pick.getValue(),
                    Double.parseDouble(rect_x.getText()),
                    Double.parseDouble(rect_y.getText()),
                    Double.parseDouble(rect_width.getText()),
                    Double.parseDouble(rect_height.getText()));
            rct.Show(gc);
            count_rectangles++;
            String color_rect = rct.getColor();
            rectangles_map.put("Прямоугольник №" + count_rectangles + " " +
                    color_rect, rct);
            choice_moove.getItems().addAll("Прямоугольник №" + count_rectangles + " " +
                    color_rect);
            choice_del.getItems().addAll("Прямоугольник №" + count_rectangles + " " +
                    color_rect);
        } catch (Exception ex) {
            System.out.println("Введите числа!");
        }
    }

    @FXML
    private void rectangle_random() {
        gc = canvas1.getGraphicsContext2D();
        Rectangle rct_1 = new Rectangle();
        rct_1.Show(gc);
        count_rectangles++;
        String color_rect = rct_1.getColor();
        rectangles_map.put("Прямоугольник №" + count_rectangles + " " +
                color_rect, rct_1);
        choice_moove.getItems().addAll("Прямоугольник №" + count_rectangles + " " +
                color_rect);
        choice_del.getItems().addAll("Прямоугольник №" + count_rectangles + " " +
                color_rect);
    }

    @FXML
    private void oval_create() {
        gc = canvas1.getGraphicsContext2D();
        try {
            Oval ovl = new Oval(color_pick.getValue(),
                    Double.parseDouble(oval_x.getText()),
                    Double.parseDouble(oval_y.getText()),
                    Double.parseDouble(oval_width.getText()),
                    Double.parseDouble(oval_height.getText()));
            ovl.Show(gc);
            count_ovals++;
            String color_oval = ovl.getColor();
            ovals_map.put("Овал №" + count_ovals + " " +
                    color_oval, ovl);
            choice_moove.getItems().addAll("Овал №" + count_ovals + " " +
                    color_oval);
            choice_del.getItems().addAll("Овал №" + count_ovals + " " +
                    color_oval);
        } catch (Exception ex) {
            System.out.println("Введите числа!");
        }
    }

    @FXML
    private void oval_random() {
        gc = canvas1.getGraphicsContext2D();
        Oval ovl_1 = new Oval();
        ovl_1.Show(gc);
        count_ovals++;
        String color_oval = ovl_1.getColor();
        ovals_map.put("Овал №" + count_ovals + " " +
                color_oval, ovl_1);
        choice_moove.getItems().addAll("Овал №" + count_ovals + " " +
                color_oval);
        choice_del.getItems().addAll("Овал №" + count_ovals + " " +
                color_oval);
    }

    @FXML
    private void figure_delete() {
        Object val = choice_del.getValue();
        if (val != null) {
            lab_error.setText("");
            gc = canvas1.getGraphicsContext2D();
            gc.clearRect(0, 0, 720, 760);
            if (val.toString().charAt(0) == 'Т') {
                triangles_map.remove(val);
                choice_del.getItems().remove(val);
                choice_moove.getItems().remove(val);
                printer();
            } else if (val.toString().charAt(0) == 'П') {
                rectangles_map.remove(val);
                choice_del.getItems().remove(val);
                choice_moove.getItems().remove(val);
                printer();
            } else if (val.toString().charAt(0) == 'О') {
                ovals_map.remove(val);
                choice_del.getItems().remove(val);
                choice_moove.getItems().remove(val);
                printer();
            }
        } else
            lab_error.setText("Выберите фигуру!");
    }

    @FXML
    private void all_figure_delete() {
        lab_error.setText("");
        gc = canvas1.getGraphicsContext2D();
        gc.clearRect(0, 0, 720, 760);
        triangles_map.clear();
        rectangles_map.clear();
        ovals_map.clear();
        choice_moove.getItems().clear();
        choice_del.getItems().clear();
        count_triangles = 0;
        count_rectangles = 0;
        count_ovals = 0;
    }

    private void printer(){
        for (Map.Entry<String, Triangle> entry : triangles_map.entrySet()) {
            Triangle tr_1 = entry.getValue();
            tr_1.Show(gc);
        }
        for (Map.Entry<String, Rectangle> entry : rectangles_map.entrySet()) {
            Rectangle rct_1 = entry.getValue();
            rct_1.Show(gc);
        }
        for (Map.Entry<String, Oval> entry : ovals_map.entrySet()) {
            Oval ovl_1 = entry.getValue();
            ovl_1.Show(gc);
        }
    }

    @FXML
    private void figure_move() {
        Object val = choice_moove.getValue();
        try {
            Double x_move = Double.parseDouble(x_moove.getText());
            Double y_move = Double.parseDouble(y_moove.getText());
            mover(val, x_move, y_move);
        } catch (Exception ex) {
            System.out.println("Введите числа!");
            lab_error.setText("Введите числа!");
        }
    }

    @FXML
    private void figure_move_random(){
        Object val = choice_moove.getValue();
        Double x_move = -400 + Math.random() * 801;
        Double y_move = -400 + Math.random() * 801;
        mover(val, x_move, y_move);
    }

    private void mover(Object val, double x_move, double y_move){
        if (val != null) {
            lab_error.setText("");
            gc = canvas1.getGraphicsContext2D();
            gc.clearRect(0, 0, 720, 760);
            if (val.toString().charAt(0) == 'Т') {
                Triangle tr = triangles_map.get(val);
                for (Map.Entry<String, Triangle> entry : triangles_map.entrySet()) {
                    Triangle tr_1 = entry.getValue();
                    if (tr_1 == tr)
                        tr_1.MoveTo(x_move,y_move, tr_1, gc);
                    else
                        tr_1.Show(gc);
                }
                for (Map.Entry<String, Rectangle> entry : rectangles_map.entrySet()) {
                    Rectangle rct_1 = entry.getValue();
                    rct_1.Show(gc);
                }
                for (Map.Entry<String, Oval> entry : ovals_map.entrySet()) {
                    Oval ovl_1 = entry.getValue();
                    ovl_1.Show(gc);
                }
            }
            else if(val.toString().charAt(0) == 'П'){
                Rectangle rct = rectangles_map.get(val);
                for (Map.Entry<String, Triangle> entry : triangles_map.entrySet()) {
                    Triangle tr_1 = entry.getValue();
                    tr_1.Show(gc);
                }
                for (Map.Entry<String, Rectangle> entry : rectangles_map.entrySet()) {
                    Rectangle rct_1 = entry.getValue();
                    if (rct_1 == rct)
                        rct_1.MoveTo(x_move, y_move, rct_1, gc);
                    else
                        rct_1.Show(gc);
                }
                for (Map.Entry<String, Oval> entry : ovals_map.entrySet()) {
                    Oval ovl_1 = entry.getValue();
                    ovl_1.Show(gc);
                }
            }
            else if(val.toString().charAt(0) == 'О'){
                Oval ovl = ovals_map.get(val);
                for (Map.Entry<String, Triangle> entry : triangles_map.entrySet()) {
                    Triangle tr_1 = entry.getValue();
                    tr_1.Show(gc);
                }
                for (Map.Entry<String, Rectangle> entry : rectangles_map.entrySet()) {
                    Rectangle rct_1 = entry.getValue();
                    rct_1.Show(gc);
                }
                for (Map.Entry<String, Oval> entry : ovals_map.entrySet()) {
                    Oval ovl_1 = entry.getValue();
                    if (ovl_1 == ovl)
                        ovl_1.MoveTo(x_move, y_move, ovl_1, gc);
                    else
                        ovl_1.Show(gc);
                }
            }
        } else
            lab_error.setText("Выберите фигуру!");
    }

}
