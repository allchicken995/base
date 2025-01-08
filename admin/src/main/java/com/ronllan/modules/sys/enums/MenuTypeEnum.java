package com.ronllan.modules.sys.enums;

/**
 * 菜单类型枚举
 *
 * @author glq gugameds066@gmail.com
 * @since 1.0.0
 */
public enum MenuTypeEnum {
    /**
     * 菜单
     */
    MENU(new Integer[]{0,1}),
    /**
     * 按钮
     */
    BUTTON(new Integer[]{2});

    private Integer[] value;

    MenuTypeEnum(Integer[] value) {
        this.value = value;
    }

    public Integer[] value() {
        return this.value;
    }
}
