package core;

public class Card {
    public int shape;
    public int number;
    public boolean selected;
    public boolean checked;

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

    @Override
    public String toString() {
        return "Card{" +
                "shape=" + shape +
                ", number=" + number +
                ", selected=" + selected +
                ", checked=" + checked +
                '}';
    }
}