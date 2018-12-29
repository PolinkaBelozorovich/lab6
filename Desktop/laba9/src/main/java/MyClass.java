public class MyClass {
    public int multiply (int x, int y){
        if(x > 999) throw new IllegalArgumentException("Error");
        return x * y;
    }
}
