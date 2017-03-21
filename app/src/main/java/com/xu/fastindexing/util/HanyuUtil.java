package com.xu.fastindexing.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 创建者     许鹏飞
 * 创建时间   2017/3/21 0:19
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class HanyuUtil {
    public static String getPinyin(String string){
        //输出格式配置
        HanyuPinyinOutputFormat mHanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        //去除声调
        mHanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //转成大写
        mHanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (Character.isWhitespace(aChar))//跳过空格
                continue;
            if (aChar>=-128&&aChar<=127){
                stringBuilder.append(chars);  //字母数字字符进行拼接
            }else{
                //可能是汉字
                try {
                    String s = PinyinHelper.toHanyuPinyinStringArray(aChar, mHanyuPinyinOutputFormat)[0];
                    stringBuilder.append(s);
                } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                    badHanyuPinyinOutputFormatCombination.printStackTrace();
                }
            }
        }
        return stringBuilder.toString();
    }


}
