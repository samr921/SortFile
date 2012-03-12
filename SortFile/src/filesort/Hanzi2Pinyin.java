package filesort;

import java.util.Arrays;
import java.util.Comparator;


import net.sourceforge.pinyin4j.PinyinHelper;

//将汉字转成拼音，然后对字符串进行比较。
public class Hanzi2Pinyin implements Comparator<String> {
    
	public int compare(String o1, String o2) {
        String str1 = (String) o1;
        String str2 = (String) o2;
        return transHanZi(str1).compareTo(transHanZi(str2));
    }
    private String transHanZi(String hanzi){
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < hanzi.length()) {
            char ch = hanzi.charAt(i);
            if (ch < 128) {
                builder.append(ch);
            } else {
                String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (pinyin != null && pinyin.length > 0) {
                    builder.append(pinyin[0].substring(0, pinyin[0].length() - 1));
                }
            }
            i += 1;
        }
        return builder.toString();
    }
    public static void main(String args[]){
    String[] arr = {"张三", "李四", "王五", "赵六", "ZZ", "JAVA", "yy", "123", "$%$#", "哈哈A",
            "1哈哈A", "1啊哈哈b", "1哈哈a", "哈哈", "哈", "怡情", "和好", "伙计", "~~"};
    Arrays.sort(arr, new Hanzi2Pinyin());
    System.out.println(Arrays.toString(arr));
    }
}
    
