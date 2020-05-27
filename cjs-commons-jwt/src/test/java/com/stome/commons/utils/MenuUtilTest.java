package com.stome.commons.utils;


import com.alibaba.fastjson.JSON;
import com.stome.commons.entity.Menu;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SHIYU463
 * @version 1.0
 * @Desc 测试菜单工具类
 * @date 2020/5/27 20:33
 */


public class MenuUtilTest {

    private  static  List<Menu> treeData = new ArrayList<Menu>();
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        treeData.add(new Menu("1","劳务管理","0"));
        treeData.add(new Menu("2","设备管理","0"));
        treeData.add(new Menu("3","物料管理","0"));
        treeData.add(new Menu("4","施工安全","0"));
        treeData.add(new Menu("5","施工质量","0"));
        treeData.add(new Menu("6","施工进度","0"));
        treeData.add(new Menu("7","技术难点","0"));
        treeData.add(new Menu("8","资料查询","0"));
        treeData.add(new Menu("9","任务管理","0"));
        treeData.add(new Menu("10","物联感控","0"));
        treeData.add(new Menu("11","考勤","1"));
        treeData.add(new Menu("12","项目部","1"));
        treeData.add(new Menu("13","劳务队","1"));
        treeData.add(new Menu("14","劳务分析","1"));
        treeData.add(new Menu("15","工资结算","1"));
        treeData.add(new Menu("16","健康防控","1"));
        treeData.add(new Menu("17","教育培训","1"));
        treeData.add(new Menu("18","当前位置","2"));
        treeData.add(new Menu("19","行驶轨迹","2"));
        treeData.add(new Menu("20","工作状态","2"));
        treeData.add(new Menu("21","设备总览","2"));
        treeData.add(new Menu("22","设备搜索","2"));
        treeData.add(new Menu("23","原材信息","3"));
        treeData.add(new Menu("24","地磅数据","3"));
        treeData.add(new Menu("25","出入库","3"));
        treeData.add(new Menu("26","教育培训","4"));
        treeData.add(new Menu("27","教育培训","5"));
        treeData.add(new Menu("28","教育培训","6"));
        treeData.add(new Menu("29","教育培训","7"));
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        treeData = null;
    }


    @Test
    public void testGetChild() {
        List<Menu> child = MenuUtil.getChild("3", treeData);
        System.out.println(JSON.toJSONString(child));
    }

    @Test
    public void testGetJsonData() {

        String jsonData = MenuUtil.getJsonData(treeData);
        System.out.println(jsonData);
    }


}