package design.composite.pack001;

public class M {
    public static void main(String[] args) {
        A a = new B();
        
        System.out.println(a instanceof B);
        System.out.println(a instanceof A);
    }
}
