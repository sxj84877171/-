package com.soarsky.car.ui.ticketed;

import com.soarsky.car.uitl.LogUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Andriod_Car_App<br>
 * 作者： 苏云
 * 时间： 2016/12/9<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：suyun@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：解析智能终端数据<br>
 * 该类为<br>
 */

public class TicketUtil {

    private String content;

    public TicketUtil(String content) {
        this.content = content;
        LogUtil.i("content:" + content + " lengtn:" + content.length());
    }


    /**
     * 解析出经度
     *
     * @return
     */
    public String getLon() {

        if (content == null) {
            return "";
        }


        String lonAndLat = content.substring(57, content.length() - 2);


        String[] lon = lonAndLat.split(",");


        return lon[0];

    }

    /**
     * 解析车牌号
     *
     * @return
     */
    public String getCarNum() {
        if (content == null) {
            return "";
        }

        String carNum = content.substring(2, 9);

        return carNum;
    }

    /***
     * 解析出纬度
     *
     * @return
     */
    public String getLat() {

        if (content == null) {
            return "";
        }

        String lonAndLat = content.substring(57, content.length() - 2);


        String[] lat = lonAndLat.split(",");
        if (lat.length > 1) {
            return lat[1];
        } else {
            return "";
        }


    }

    /***
     * 解析出违章时间
     *
     * @return
     */
    public String getStime() {

        if (content == null) {
            return "";
        }

        String stime = content.substring(9, 23);
        return stime;
    }

    /***
     * 解析出违章结束时间
     *
     * @return
     */
    public String getEtime() {

        if (content == null) {
            return "";
        }

        String etime = content.substring(23, 37);
        if (etime.trim().length() == 0) {
            return "";
        }
        return etime;
    }

    /***
     * 解析出驾驶证号
     * 如果后台返回18个0 传空格
     *
     * @return
     */
    public String getIdNo() {

        if (content == null) {
            return "";
        }

        String idNo = content.substring(39, 57);
        if ("000000000000000000".equals(idNo)) {
            return "";
        }
        return idNo;
    }

    /**
     * 获取违章等级
     *
     * @return
     */
    private String getindL() {
        if (content == null) {
            return "";
        }
        return content.substring(content.length() - 1);
    }


    /***
     * 解析违法信息
     *
     * @return
     */
    public String getInf() {

        if (content == null) {
            return "";
        }
        int i;
        try {
            i = Integer.parseInt(content.substring(content.length() - 2, content.length() - 1));
        } catch (Exception e) {
            i = 'a';
        }

        String inf = getInfString(i);

        return inf;
    }


    private String getIno() {

        if (content == null) {
            return "";
        }

        return content.substring(content.length() - 2, content.length() - 1);


    }


    /**
     * 解析准驾车型
     *
     * @return
     */
    public String getdType() {

        if (content == null) {
            return "";
        }

        String dType = content.substring(37, 39);

        return dType;
    }


    /**
     * 封装上传请求数据
     *
     * @return
     */
    public Map<String, String> createCommitParams() {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("carnum", getCarNum());
            params.put("idNo", getIdNo());
            params.put("stime", getStime());
            params.put("etime", getEtime());
            params.put("lon", getLon().trim());
            params.put("lat", getLat().trim());
            params.put("inf", getInf());
            params.put("dType", getdType());
            params.put("ino", getIno());
            params.put("ptype", "1");

            LogUtil.i("carnum:" + getCarNum() + "  idNo:" + getIdNo() + "  stime" + getStime() + "  lon：" + getLon() + "  dType:" + getdType() + "  lat:" + getLat() + " etime:" + getEtime());
            return params;
        } catch (Exception e) {
            LogUtil.i(e.getMessage());
            return null;
        }
    }


    /**
     * 解析违章类型
     */

    private String getInfString(int inf) {
        switch (inf) {
            case 0:
                return "无证驾驶";
            case 1:
                return "滥用远光";
            case 2:
                return "没系安全带";
            case 3:
                return "违规使用安全带";
            case 4:
                return "超速";
            case 5:
                return "疲劳驾驶";
            case 6:
                return "大客车夜间违章运行";
            case 7:
                return "违反限行规定";
            case 8:
                return "违规使用喇叭";
            case 9:
                return "违停";
            case 'a':
                return "开车玩手机";

        }
        return "无证驾驶";
    }


    /**
     * 解析违章等级
     */

}
