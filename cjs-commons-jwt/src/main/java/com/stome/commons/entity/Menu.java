package com.stome.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc 菜单
 * @date 2020/5/27 20:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    // 菜单id
    private String id;
    // 菜单名称
    private String name;
    // 父菜单id
    private String parentId;
    // 菜单url
    private String url;
    // 菜单图标
    private String icon;
    // 菜单顺序
    private int order;
    // 子菜单
    private List<Menu> childMenus;

    public Menu(String id, String name, String parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", parentId='" + parentId + '\'' +
                ", url='" + url + '\'' +
                ", icon='" + icon + '\'' +
                ", order=" + order +
                ", childMenus=" + childMenus +
                '}';
    }
}
