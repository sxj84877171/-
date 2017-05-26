package com.soarsky.policeclient.server.cmd;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/26<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 电子罚单内容回应包<br>
 */
public class ElectronicTicketCommandResponse extends BaseCmd {
    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    private String drived ;

    private String carNum ;
    private String drivingLicenseNumber;

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    /**
     * 根据字节数组，组织成电子罚单回应包
     * @param bytes
     */
    @Override
    public void parseBytes(byte[] bytes) {

    }


    /**
     * 从字节数组中解析出驾驶证号
     * @param bytes 字节数组
     */
    public void parseDrivingLicenseNumber(byte[] bytes) {
        drivingLicenseNumber = new String(bytes,24,18);

    }
}
