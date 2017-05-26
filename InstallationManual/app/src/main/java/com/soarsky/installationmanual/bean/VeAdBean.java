package com.soarsky.installationmanual.bean;

/**
 * InstallationManual<br>
 * 作者： 魏凯<br>
 * 时间： 2017/2/16<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为 车管所bean<br>
 */

public class VeAdBean {
    /**
     * 车管所名称
     */
    private String name;
    /**
     * id
     */
    private String id;
    /**
     * item编码
     */
    private String itemCode;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }
}
