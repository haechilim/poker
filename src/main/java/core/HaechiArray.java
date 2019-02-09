package core;

public class HaechiArray {
    private int DEFAULT_SIZE = 10;
    private int length = 0;
    private int[] array = new int[DEFAULT_SIZE];

    public int size() {
        return length;
    }

    public boolean isEmpty() {
        return length == 0 ? true : false;
    }

    public void add(int number) {
        if(length >= array.length) expand(length);
        array[length++] = number;
    }

    public void addAll(int[] numbers) {
        for(int i = 0; i < numbers.length; i++) {
            add(numbers[i]);
        }
    }

    public boolean contains(int number) {
        return indexOf(number) >= 0;
    }

    public int first() {
        return get(0);
    }

    public int get(int index) {
        if(!isValidIndex(index)) return -1;

        return array[index];
    }

    public int indexOf(int number) {
        for(int i = 0; i < length; i++) {
            if(array[i] == number) return i;
        }

        return -1;
    }

    public void set(int index, int number) {
        if(!isValidIndex(index)) return;

        array[index] = number;
    }

    public void sort(boolean ascending) {
        int temp;

        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((array[i] < array[j] && !ascending) || (array[i] > array[j] && ascending)) {
                    temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }

    public void remove(int index) {
        if(!isValidIndex(index)) return;

        for(int i = index + 1; i < length; i++) {
            array[i - 1] = array[i];
        }

        length--;
    }

    public void clear() {
        length = 0;
    }

    public String toString() {
        String result = "";

        for(int i = 0; i < length; i++) {
            result += array[i] + " ";
        }

        return result;
    }

    private boolean isValidIndex(int index) {
        return (index >= 0 && index < length);
    }

    private void expand(int addCount) {
        long time = System.currentTimeMillis();
        int[] temps = new int[length];

        for(int i = 0; i < length; i++) {
            temps[i] = array[i];
        }

        array = new int[length + addCount];

        for(int i = 0; i < length; i++) {
            array[i] = temps[i];
        }

        System.out.println("expand() 크기:" + array.length + " 처리시간:" + (System.currentTimeMillis() - time));
    }
}