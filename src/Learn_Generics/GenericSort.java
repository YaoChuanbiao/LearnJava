package Learn_Generics;

import java.util.Comparator;

public class GenericSort {
    public static <T> void sort(T[] arr, Comparator<? super T> comparator) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (comparator.compare(arr[j], arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        String[] strings = {"yaochuanbiao", "lidongni", "zhangyuanqing"};
        Comparator<String> comparator = String::compareTo; // 使用默认的比较器
        sort(strings, comparator);
        for (String s : strings) {
            System.out.print(s + " ");
        }
    }
}
