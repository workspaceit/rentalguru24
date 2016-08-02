package validator;

/**
 * Created by mi on 8/2/16.
 */
public class Test {
    public static void main(String args[]){
        String str="hello";
        String[] s = str.split("\\.");
        System.out.println(s.length);
        System.out.println(s[s.length-1]);
    }
}
