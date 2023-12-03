import java.awt.Color;

public class MyColor {
    private int red;
    private int green;
    private int blue;

    public MyColor(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public Color toColor() {
        return new Color(red, green, blue);
    }

    // Getter und Setter für die RGB-Werte

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    public static void main(String[] args) {
        // So kann die Klasse verwendet werden
        MyColor myColor = new MyColor(255, 0, 0); // Rot
        Color javaColor = myColor.toColor();

    }
}

