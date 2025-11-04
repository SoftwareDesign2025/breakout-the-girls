package application;

import javafx.scene.paint.Color;

public class Bullet extends Projectile {
    private final double RADIUS = 5;
	private final double SCREEN_HEIGHT_RATIO = 0.8;


    @Override
    public void createProjectile(WindowDimensions window) {
        projectile = new javafx.scene.shape.Circle();
        double xCoordinate = window.getWindowWidth() / 2;
        double yCoordinate = window.getWindowHeight() * SCREEN_HEIGHT_RATIO;

        projectile.setCenterX(xCoordinate);
        projectile.setCenterY(yCoordinate);
        projectile.setRadius(RADIUS); // use Bullet's radius
        projectile.setFill(Color.BLACK);

        ballVelocity = new javafx.geometry.Point2D(0, 0);
    }

}

