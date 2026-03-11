public class Calculate {
    //add
    public static int add(int a,int b){
        return a + b;
    }
    //minus
    public static int minus(int a, int b){
        return a -b;
    }
    //nunus
    public static int nulti(int a, int b){
        return a * b;
    }
    //devide
    public static double devide(int a, int b){
        if (b ==0){
            throw new RuntimeException("Cannot devide by zero");
        }
        return (double) a /b;
    }
}
