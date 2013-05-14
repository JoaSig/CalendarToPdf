package dk.hagendazzlers;

/**
 * Date: 09/05/13
 */
public class FontColor {
    private int[] color = new int[3];

    public FontColor(int[] color) {
        this.color = color;
    }

    public FontColor() {
    }

    public void setColor(String[] colorStrings) {
        int[] colors = new int[3];
        for (int i = 0; i < colorStrings.length; i++) {
            colors[i] = Integer.parseInt(colorStrings[i].trim());
        }
        this.color = colors;
    }

    public int[] getColor() {
        return color;
    }

    public void setColor(int[] color) {
        this.color = color;
    }
}
