package com.xu.fastindexing.bean;

import com.xu.fastindexing.util.HanyuUtil;

/**
 * 创建者     许鹏飞
 * 创建时间   2017/3/21 0:30
 * 描述	      ${TODO}
 * <p>
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class Person implements Comparable<Person>{
    String name;
    String pinyin;
    String letter;
    public Person(String name) {
        this.name = name;
        this.pinyin= HanyuUtil.getPinyin(name);
        this.letter=pinyin.charAt(0)+"";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    @Override
    public int compareTo(Person another) {

        return this.pinyin.compareTo(another.pinyin);
    }
}
