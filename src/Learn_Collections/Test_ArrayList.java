package Learn_Collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class Test_ArrayList {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            int i = iterator.next();
            if(i == 2) {
                iterator.remove();
            }
            System.out.println(i);
        }
        System.out.println(list);
    }
}
