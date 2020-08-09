package com.imooc.enums;

/**
 * @Desc 性别 枚举
 */
public enum Sex {
    woman(0,"女"),
    man(1,"男"),
    secret(2,"保密");

    public final String value;
    public final Integer type;

    Sex(Integer type, String value) {
        this.type = type;
        this.value = value;
    }



}
