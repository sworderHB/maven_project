package design.singleton.pack001;

class M {
    private static int i;
}

public class Singleton001 {
    private static class Singleton001Handle {
        private static  Singleton001 singleINSTANCE = new Singleton001(); 
    }
    
    private Singleton001() {
    }

    public static final Singleton001 getSingleton001() {
        return Singleton001Handle.singleINSTANCE;
    }
    
    public static void main(String[] args) {
        
    }
}
