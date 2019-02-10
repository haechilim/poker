package core;

public class Card {
    private int shape;
    private int number;
    private boolean selected;
    private boolean checked;

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public static String getNumberText(int number) {
        if(number == 11) return "J";
        else if(number == 12) return "Q";
        else if(number == 13) return "K";
        else if(number == 14) return "A";

        return number + "";
    }

    public static String getShapeText(int shape) {
        if (shape == 0) return "♤";
        else if (shape == 1) return "◇";
        else if (shape == 2) return "♡";
        else if (shape == 3) return "♣";

        return "";
    }

    public String toString() {
        return getShapeText(shape) + getNumberText(number);
    }
}