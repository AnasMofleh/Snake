package sample;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.Random;

public class Model {
    private Rectangle head,food;
    private Rectangle[] snake;
    private Scene scene;
    private Pane layout;
    private int height = 600;
    private int width = 800;
    private int dim = 20;
    private int decreasingFactor = (int) (dim/1.5);


    public Model() {
        layout = new BorderPane();
        scene = new Scene(layout, width, height);
        createSnake();
        createFood();
        scene.setFill(Color.DARKSEAGREEN);
    }


    private void createSnake() {
        head = new Rectangle(dim, dim, dim, dim);
        head.setFill(Color.RED);
        layout.getChildren().add(head);
    }

    public void createFood() {
        double shiftToCenter = (dim - decreasingFactor) / 2.0;
        food = new Rectangle(decreasingFactor, decreasingFactor);
        food.setX(((new Random().nextInt(width) / dim) * dim) + shiftToCenter);
        food.setY(((new Random().nextInt(height) / dim) * dim)  + shiftToCenter);
        food.setFill(Color.YELLOW);
        layout.getChildren().add(food);
    }

    public Scene getScene() {
        return scene;
    }

    public Rectangle getHead() {
        return head;
    }


    public int getDim() {
        return dim;
    }

    public double getWidthRatio() {
        return (getScene().getWidth() / width) ;
    }

    public double getHeightRatio() {
        return (getScene().getHeight() / height) ;
    }

    public Rectangle getFood() {
        return food;
    }

    public Pane getLayout() {
        return layout;
    }

    public int getDecreasingFactor() {
        return decreasingFactor;
    }
}
