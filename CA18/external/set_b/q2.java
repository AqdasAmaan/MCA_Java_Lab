
import java.util.Arrays;

class GenericArray<T extends Object & Comparable<T>> {

    T[] arr;

    public GenericArray(int size) {
        arr = (T[]) new Object[size];
    }

    public void genericSort() {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {

                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void insert(T element, int index) {
        arr[index] = element;
    }
}

public class q2 {
    public static void main(String[] args) {
        GenericArray<Integer> intArr = new GenericArray<>(5);
        intArr.insert(5, 0);
        intArr.insert(2, 1);
        intArr.insert(8, 2);
        intArr.insert(1, 3);
        intArr.insert(4, 4);

        intArr.genericSort();
        System.out.println("Sorted Integer Array: " + Arrays.toString(intArr.arr));

        GenericArray<String> strArr = new GenericArray<>(4);
        strArr.insert("banana", 0);
        strArr.insert("apple", 1);
        strArr.insert("orange", 2);
        strArr.insert("grape", 3);

        strArr.genericSort();
        System.out.println("Sorted String Array: " + Arrays.toString(strArr.arr));
    }
}