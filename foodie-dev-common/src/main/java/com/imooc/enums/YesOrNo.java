package com.imooc.enums;

/**
 * @Desc 是否 枚举
 */
public enum YesOrNo {
    NO(0,"否"),
    YES(1,"是");

    public final String value;
    public final Integer type;

    YesOrNo(Integer type, String value) {
        this.type = type;
        this.value = value;
    }



}
