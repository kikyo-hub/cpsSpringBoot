package cn.nrzt.cps.util;

public class StringUtils {
    public  static boolean isEmpty( Object str){
        return (str == null || "".equals(str) || " ".equals(str) ||"“”".equals(str) ||"“ ”".equals(str) );
    }
}
