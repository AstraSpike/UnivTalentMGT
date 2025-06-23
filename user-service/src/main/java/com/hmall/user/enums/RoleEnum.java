package com.hmall.user.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.baomidou.mybatisplus.annotation.IEnum;

public enum RoleEnum implements IEnum<Integer> {
    SUPERADMIN(1, "super_admin"),
    CAMPUSLEADER(2, "campus_leader"),
    PS(3, "personnel_specialist"),
    DHEAD(4, "department_head"),
    CADRE(5, "cadre");

    @EnumValue
    private final Integer value;
    private final String desc;

    RoleEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }
}

