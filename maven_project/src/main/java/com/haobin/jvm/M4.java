package com.haobin.jvm;

class MyClass {
}


public class M4 {
    final private static Integer a = 1;

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        int a, b, c;
        int m;
        if (2 > 1) {
            try {
                int i = 3 > 2 ? 1 : 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (3 > 2) {
            System.out.println("BBBB");
        } else {
            System.out.println("AAAA");
        }

        m = 10;
    }


}
