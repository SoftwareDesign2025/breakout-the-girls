package application;

import javafx.scene.paint.Color;

public class Bullet extends Projectile {
    private final double RADIUS = 5;

    @Override
    public void createProjectile(int windowWidth, int windowHeight) {
        projectile = new javafx.scene.shape.Circle();
        double xCoordinate = windowWidth / 2;
        double yCoordinate = windowHeight * 0.8;

        projectile.setCenterX(xCoordinate);
        projectile.setCenterY(yCoordinate);
        projectile.setRadius(RADIUS); // use Bullet's radius
        projectile.setFill(Color.BLACK);

        ballVelocity = new javafx.geometry.Point2D(0, 0);
    }

}

