package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Observable;

public class Controller extends Observable {
    private View v;
    private Model m;
    private int step = 3;
    private DIRECTIONS dir;


    public Controller(Model m, View v) {
        this.m = m;
        this.v = v;
        dir = DIRECTIONS.EAST;
        addListeners();
    }

    public void addListeners() {
        m.getScene().addPreLayoutPulseListener(() -> {
            m.getHead().setHeight(m.getHeight());
            m.getHead().setWidth(m.getWidth());

            var x = m.getHead().getX();
            var y = m.getHead().getY();

            switch (dir) {
                case EAST -> m.getHead().setX(x + step);
                case WEST -> m.getHead().setX(x - step);
                case NORTH -> m.getHead().setY(y - step);
                case SOUTH -> m.getHead().setY(y + step);
            }
            moveInGrid(m.getHead().getX(), m.getHead().getY());
        });

        m.getScene().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.W && dir != DIRECTIONS.SOUTH)
                dir = DIRECTIONS.NORTH;

            else if (event.getCode() == KeyCode.S && dir != DIRECTIONS.NORTH)
                dir = DIRECTIONS.SOUTH;

            else if (event.getCode() == KeyCode.D && dir != DIRECTIONS.WEST)
                dir = DIRECTIONS.EAST;

            else if (event.getCode() == KeyCode.A && dir != DIRECTIONS.EAST)
                dir = DIRECTIONS.WEST;
        });

    }

    public void moveInGrid(double x, double y) {
        var width = m.getScene().getWidth();
        var height = m.getScene().getHeight();
        var dim = m.getDim();
        if (x > width - dim)
            m.getHead().setX(0);
        else if (x < 0)
            m.getHead().setX(width - dim);


        if (y > height - dim)
            m.getHead().setY(0);
        else if (y < 0)
            m.getHead().setY(height - dim);
    }

}
