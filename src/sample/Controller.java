package sample;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.Observable;

import static java.lang.Math.abs;

public class Controller extends Observable {
    private View v;
    private Model m;
    private DIRECTIONS dir;
    private int delay = 100;


    public Controller(Model m, View v) {
        this.m = m;
        this.v = v;
        dir = DIRECTIONS.EAST;
        addListeners();
    }

    public void addListeners() {
        m.getScene().addPreLayoutPulseListener(() -> {
            m.getHead().setHeight(m.getHeightRatio() * m.getDim());
            m.getHead().setWidth(m.getWidthRatio() * m.getDim());
            m.getFood().setHeight(m.getHeightRatio() * m.getDim()/1.5);
            m.getFood().setWidth(m.getWidthRatio() * m.getDim()/1.5);
        });

        m.getScene().addPreLayoutPulseListener(() -> {
            double shiftingConstant = (m.getDim() - m.getDecreasingFactor()) / 2.0;
            if(abs(m.getHead().getX() - (m.getFood().getX() - shiftingConstant))  <= 0
            && abs((m.getHead().getY()) - (m.getFood().getY() - shiftingConstant))  <= 0){
                m.getLayout().getChildren().remove(m.getFood());
                m.createFood();
            }
        });

        m.getScene().addPreLayoutPulseListener(() -> {
            var x = m.getHead().getX();
            var y = m.getHead().getY();
            switch (dir) {
                case EAST -> m.getHead().setX(x + m.getDim());
                case WEST -> m.getHead().setX(x - m.getDim());
                case NORTH -> m.getHead().setY(y - m.getDim());
                case SOUTH -> m.getHead().setY(y + m.getDim());
            }
            moveInGrid(m.getHead().getX(), m.getHead().getY());
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
