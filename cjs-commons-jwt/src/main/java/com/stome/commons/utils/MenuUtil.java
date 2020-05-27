package com.stome.commons.utils;

import com.alibaba.fastjson.JSON;
import com.stome.commons.entity.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc 目录操作工具类
 * @date 2020/5/27 20:21
 */
public class MenuUtil {
    public static List<Menu> getChild(String id, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentId().equals(id)) {
                childList.add(menu);
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {
            menu.setChildMenus(getChild(menu.getId(), rootMenu));// 递归
        }

        // 判断递归结束
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

    public static String getJsonData(List<Menu> menuData) {
//        for (Menu menu : menuData) {
//			 System.out.println(menu.toString());
//        }

        List<Menu> menuList = new ArrayList<Menu>(); // 树递归

        // 先找到所有的一级菜单
        for (int i = 0; i < menuData.size(); i++) {
            // 一级菜单父ID为0
            if (menuData.get(i).getParentId().equals("0")) {
                menuList.add(menuData.get(i));
            }
        }

        // 为一级菜单设置子菜单，getChild是递归调用的
        for (Menu menu : menuList) {
            menu.setChildMenus(getChild(menu.getId(), menuData));
        }

        return JSON.toJSONString(menuList);
    }

}