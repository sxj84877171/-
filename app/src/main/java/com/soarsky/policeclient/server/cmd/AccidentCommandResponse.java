package com.soarsky.policeclient.server.cmd;

import com.soarsky.policeclient.bean.AccidentDataBean;
import com.soarsky.policeclient.uitl.CarTypeUtils;
import com.soarsky.policeclient.uitl.CarUtil;
import com.soarsky.policeclient.uitl.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
public class AccidentCommandResponse extends BaseCmd {
    @Override
    public byte[] toMsgBytes() {
        return new byte[0];
    }

    private String drived ;

    private String carNum ;
    private String drivingLicenseNumber;

    private int contentLength = 0 ;

    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    private AccidentDataBean.AccidentItemBean.AccidentCarBean accidentDataBean = new AccidentDataBean.AccidentItemBean.AccidentCarBean();

    public AccidentDataBean.AccidentItemBean.AccidentCarBean getAccidentDataBean() {
        return accidentDataBean;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    /**
     * 别问为什么这么多行，不解释，数据就是这么复杂
     *
     * 起始字节	字段内容	字段长度	示例	描述
     0	车牌号	9	蒙A12345	UTF-8编码
     9	车辆类型	2	11（小型汽车）	UTF-8编码
     11	驾驶证号	20	11123456789123456789	UTF-8编码
     Byte 0-1，驾驶证类型
     Byte 2-19，驾驶证号
     31	安全带状态	1	0	UTF-8编码
     0：未系安全带
     1：系上安全带
     32	故障数据长度	1	0x0A	十六进制，每组故障数据为5byte
     33	故障数据	5*2	P0001P0002	UTF-8编码
     由故障数据长度决定
     43	变光数据长度	1	0x0F	十六进制,每组变光数据为15byte
     44	变光数据	15*1	20161212101010
     1	UTF-8编码
     Byte 0-13每组变光数据的产生时间
     Byte 14 变光数据
     Byte 14＝0，远光变近光
     Byte 14＝1，近光变远光

     59	异常车速长度	1	0x2C	十六进制,每组异常车速为44byte
     60	异常车速数据	44*1	20161212090909
     120110100090080070060050040005	　UTF-8编码，
     Byte 0-13异常车速的起始时间
     Byte 14-43十个异常车速（间隔1秒）
     104	违章数据长度	2	0x0056	十六进制，每组违章数据为86byte
     106	违章数据　	86*1	0x00000002
     11蒙A12345
     2016121214141420161212141430
     11123456789123456789（C1驾照）
     2308.28715,11322.09876
     0x85
     UTF-8编码
     Byte 0-3，违章数据唯一标志符
     Byte 4-14，车牌号与车辆类型
     Byte 15-42，违章的开始与结束时间
     Byte 43-62，驾驶证号与驾驶证类型
     Byte 63-84，违章时的位置
     Byte 85，违章类型、等级、状态

     * @param bytes
     */
    @Override
    public void parseBytes(byte[] bytes) {
        accidentDataBean.setType(CarTypeUtils.carType(new String(bytes,24,2)));  //TODO 车辆类型需要转换  01有轨电车02无轨电车03轮式自行机械车04轻便摩托车05普通二轮摩托车06普通三轮摩托车07残疾人专用小型自动挡载客汽车08三轮汽车09低速载货汽车10小型自动挡汽车11小型汽车12大型货车13中型客车14城市公交车15牵引车16大型客车

        accidentDataBean.setAnquandai(CarUtil.parseCarAnquandai(new String(bytes,46,1)));
        //String guzhanglen = new String(bytes,47,1);
        int guzhangalllen = BaseCmd.parseLength(bytes,47,48);
        int guzhanglen = guzhangalllen/19;
        List<AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem> guzhangItems = new ArrayList<>();
        for (int i = 0;i<guzhanglen;i++){
            AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem guzhangItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.GuzhangItem();
            guzhangItem.setTime(time(new String(bytes,49+19*i,14)));
            guzhangItem.setGuzhang(new String(bytes,49+19*i+14,5));
            guzhangItems.add(guzhangItem);
        }
        accidentDataBean.setGuzhangItems(guzhangItems);
        int dengAllLen = bytes[34+15+guzhangalllen];
        int denglen = dengAllLen/15;
        List<AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem> dengItems = new ArrayList<>();
        for (int i = 0;i<denglen;i++){
            AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem dengItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.DengItem();
            dengItem.setTime(time(new String(bytes,34+15+guzhangalllen+1+15*i,14)));
            dengItem.setDeng(CarUtil.parseCarDeng(new String(bytes,34+15+guzhangalllen+1+15*i+14,1)));
            dengItems.add(dengItem);
        }
        accidentDataBean.setDengItems(dengItems);
        int suduAllLen = bytes[34+15+guzhangalllen+dengAllLen+1];
        int suduLen = suduAllLen/44;
        List<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu> sudus = new ArrayList<>();
        for (int i = 0;i<suduLen;i++){
            AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu sudu = new AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu();
            String time = time(new String(bytes,34+15+guzhangalllen+dengAllLen+1+1+44*i,14));
            String suduString = new String(bytes,34+15+guzhangalllen+dengAllLen+1+1+44*i+14,30);
            List<AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem> suduItems = new ArrayList<>();
            for (int j = 0;j<10;j++){
                AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem suduItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.Sudu.SuduItem();
                suduItem.setTime(TimeUtils.date2String(TimeUtils.addSecond(TimeUtils.string2Date(time),j)));
                suduItem.setValue(suduString.substring(j,j+3));
                suduItems.add(suduItem);
            }
            sudu.setSuduItems(suduItems);
            sudus.add(sudu);
        }
        accidentDataBean.setSudus(sudus);
        //int weizhangAllLen = bytes[34+15+guzhangalllen+dengAllLen+suduAllLen+1+1];
        int weizhangAllLen = BaseCmd.parseLength(bytes,34+15+guzhangalllen+dengAllLen+suduAllLen+1+1,34+15+guzhangalllen+dengAllLen+suduAllLen+1+1+1);
        int weizhangLen = weizhangAllLen/86;
        List<AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem> weizhangItems = new ArrayList<>();
        for (int i = 0;i<weizhangLen;i++){
            AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem weizhangItem = new AccidentDataBean.AccidentItemBean.AccidentCarBean.WeizhangItem();
            weizhangItem.setTime(time(new String(bytes,34+15+guzhangalllen+dengAllLen+suduAllLen+1+1+2+15+86*i,14)));
            weizhangItem.setDizhi(new String(bytes,34+15+guzhangalllen+dengAllLen+suduAllLen+1+1+2+63+86*i,22));
            String yuanyin= weizhangyuanyin(getLow4(bytes[34+15+guzhangalllen+dengAllLen+suduAllLen+1+1+2+85+86*i]));
            weizhangItem.setYuanyin(yuanyin);
            weizhangItems.add(weizhangItem);
        }
        accidentDataBean.setWeizhangItems(weizhangItems);
    }

    //20161212080808

    /**
     * 时间格式转换
     * @param s 20161212080808这种时间格式
     * @return 转换结果
     */
    private String time(String s){
        StringBuilder sb = new StringBuilder();
        sb.append(s.substring(0,4));
        sb.append("-");
        sb.append(s.substring(4,6));
        sb.append("-");
        sb.append(s.substring(6,8));
        sb.append(" ");
        sb.append(s.substring(8,10));
        sb.append(":");
        sb.append(s.substring(10,12));
        sb.append(":");
        sb.append(s.substring(12,14));
        return sb.toString();

    }

    /**
     * 获取低四位
     * @param data 字节
     * @return 低四位
     */
    public static int getLow4(byte data){//获取低四位
        int low;
        low = (data & 0x0f);
        return low;
    }

    /**
     * 违章原因数字转字符串
     * @param data 违章数字
     * @return 违章字符串
     */
    private String weizhangyuanyin(int data){
        HashMap<Integer,String> map = new HashMap<>();
        map.put(0,"无证驾驶");
        map.put(1,"滥用远光");
        map.put(2,"开车不系安全带");
        map.put(3,"违规使用安全带");
        map.put(4,"超速");
        map.put(5,"疲劳驾驶");
        map.put(6,"大客车夜间违章运行");
        map.put(7,"违法限行规定");
        map.put(8,"违规使用喇叭");
        return map.get(data);
    }

}
