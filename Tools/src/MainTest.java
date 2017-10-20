import com.weston.tools.util.StringUtil;

public class MainTest {
    public static void main(String[] args){
        String str1="123";
        String str2 ="123";
  
        System.out.println(StringUtil.isEmpty(str1));
        System.out.println(StringUtil.isEqual(str1,str2));
 }
}
