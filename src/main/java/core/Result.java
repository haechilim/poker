package core;

public class Result {
    public static final int NONE = 0;
    public static final int STRAIGHT_FLUSH = 1;
    public static final int FOUR_CARDS = 2;
    public static final int FULL_HOUSE = 3;
    public static final int FLUSH = 4;
    public static final int STRAIGHT = 5;
    public static final int TRIPLE = 6;
    public static final int TWO_PAIR = 7;
    public static final int ONE_PAIR = 8;
    public static final int TOP = 9;

    private static final String[] levels = {
            "",
            "스트레이트 플러쉬",
            "포커",
            "풀 하우스",
            "플러쉬",
            "스트레이트",
            "트리플",
            "투페어",
            "원페어",
            "탑"
    };

    public int what = NONE;
    public int top;     //  포커, 풀하우스, 트리플의 숫자
    public int shape;   //  가장 높은 숫자의 모양

    public HaechiArray tops = new HaechiArray();
    public HaechiArray others = new HaechiArray();


    public boolean isDone() {
        return what != NONE;
    }
    
    public String text() {
        return "";
    }

    @Override
    public String toString() {
        return "core.Result{" +
                "what=" + what +
                ", top=" + top +
                ", shape=" + shape +
                ", tops=" + tops +
                ", others=" + others +
                '}';
    }
}