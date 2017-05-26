package com.soarsky.policeclient.bean;

import java.io.Serializable;
import java.util.List;

/**
 * android_police_app<br>
 * 作者： 魏凯<br>
 * 时间： 2016/12/20<br>
 * 公司：长沙硕铠电子科技有限公司<br>
 * Email：weikai@soarsky-e.com<br>
 * 公司网址：http://www.soarsky-e.com<br>
 * 公司地址（Add）  ：湖南省长沙市岳麓区麓谷信息港A座8楼<br>
 * 版本：1.0.0.0<br>
 * 邮编：410000<br>
 * 程序功能：<br>
 * 该类为事故bean<br>
 */
public class AccidentDataBean {
    /**
     * 事故分析列表
     */

    private List<AccidentItemBean> accidentItemBeanList;

    public List<AccidentItemBean> getAccidentItemBeanList() {
        return accidentItemBeanList;
    }

    public void setAccidentItemBeanList(List<AccidentItemBean> accidentItemBeanList) {
        this.accidentItemBeanList = accidentItemBeanList;
    }

    /**
     * 每一个事故的bean
     */
    public static class AccidentItemBean implements Serializable{

        /**
         * Indicates whether some other object is "equal to" this one.
         * <p>
         * The {@code equals} method implements an equivalence relation
         * on non-null object references:
         * <ul>
         * <li>It is <i>reflexive</i>: for any non-null reference value
         *     {@code x}, {@code x.equals(x)} should return
         *     {@code true}.
         * <li>It is <i>symmetric</i>: for any non-null reference values
         *     {@code x} and {@code y}, {@code x.equals(y)}
         *     should return {@code true} if and only if
         *     {@code y.equals(x)} returns {@code true}.
         * <li>It is <i>transitive</i>: for any non-null reference values
         *     {@code x}, {@code y}, and {@code z}, if
         *     {@code x.equals(y)} returns {@code true} and
         *     {@code y.equals(z)} returns {@code true}, then
         *     {@code x.equals(z)} should return {@code true}.
         * <li>It is <i>consistent</i>: for any non-null reference values
         *     {@code x} and {@code y}, multiple invocations of
         *     {@code x.equals(y)} consistently return {@code true}
         *     or consistently return {@code false}, provided no
         *     information used in {@code equals} comparisons on the
         *     objects is modified.
         * <li>For any non-null reference value {@code x},
         *     {@code x.equals(null)} should return {@code false}.
         * </ul>
         * <p>
         * The {@code equals} method for class {@code Object} implements
         * the most discriminating possible equivalence relation on objects;
         * that is, for any non-null reference values {@code x} and
         * {@code y}, this method returns {@code true} if and only
         * if {@code x} and {@code y} refer to the same object
         * ({@code x == y} has the value {@code true}).
         * <p>
         * Note that it is generally necessary to override the {@code hashCode}
         * method whenever this method is overridden, so as to maintain the
         * general contract for the {@code hashCode} method, which states
         * that equal objects must have equal hash codes.
         *
         * @param   obj   the reference object with which to compare.
         * @return  {@code true} if this object is the same as the obj
         *          argument; {@code false} otherwise.
         * @see     #hashCode()
         * @see     java.util.HashMap
         */
        @Override
        public boolean equals(Object obj) {
            if(obj instanceof  AccidentItemBean){
                AccidentItemBean accidentItemBean = (AccidentItemBean)obj;
                if(accidentItemBean.getTime() != null){
                    return accidentItemBean.getTime().equals(time);
                }else{
                    if(time != null){
                        return false;
                    }
                    return true;
                }
            }
            return false;
        }
        /**
         * Returns a hash code value for the object. This method is
         * supported for the benefit of hash tables such as those provided by
         * {@link java.util.HashMap}.
         * <p>
         * The general contract of {@code hashCode} is:
         * <ul>
         * <li>Whenever it is invoked on the same object more than once during
         *     an execution of a Java application, the {@code hashCode} method
         *     must consistently return the same integer, provided no information
         *     used in {@code equals} comparisons on the object is modified.
         *     This integer need not remain consistent from one execution of an
         *     application to another execution of the same application.
         * <li>If two objects are equal according to the {@code equals(Object)}
         *     method, then calling the {@code hashCode} method on each of
         *     the two objects must produce the same integer result.
         * <li>It is <em>not</em> required that if two objects are unequal
         *     according to the {@link java.lang.Object#equals(java.lang.Object)}
         *     method, then calling the {@code hashCode} method on each of the
         *     two objects must produce distinct integer results.  However, the
         *     programmer should be aware that producing distinct integer results
         *     for unequal objects may improve the performance of hash tables.
         * </ul>
         * <p>
         * As much as is reasonably practical, the hashCode method defined by
         * class {@code Object} does return distinct integers for distinct
         * objects. (This is typically implemented by converting the internal
         * address of the object into an integer, but this implementation
         * technique is not required by the
         * Java<font size="-2"><sup>TM</sup></font> programming language.)
         *
         * @return  a hash code value for this object.
         * @see     java.lang.Object#equals(java.lang.Object)
         * @see     java.lang.System#identityHashCode
         */
        @Override
        public int hashCode() {
            if (time == null) {
                return 0;
            }
            return time.hashCode();
        }

        /**
         * id标识
         */
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        /**
         * 时间
         */
        private String time;
        /**
         * 位置
         */
        private String weizhi;
        /**
         * 原因
         */
        private String yuanyin;
        /**
         * 备注
         */
        private String beizhu;
        /**
         * 事故分析车辆列表
         */
        private List<AccidentCarBean> accidentCarBeanList;

        /**
         * 图片列表
         */
        private List<Image> images;

        public List<Image> getImages() {
            return images;
        }

        public void setImages(List<Image> images) {
            this.images = images;
        }

        public static class Image implements Serializable{
            /**
             * 图片本地路径
             */
            private String myPath;
            /**
             * 服务器路径
             */
            private String serverPath;

            public String getMyPath() {
                return myPath;
            }

            public void setMyPath(String myPath) {
                this.myPath = myPath;
            }

            public String getServerPath() {
                return serverPath;
            }

            public void setServerPath(String serverPath) {
                this.serverPath = serverPath;
            }
        }
        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getWeizhi() {
            return weizhi;
        }

        public void setWeizhi(String weizhi) {
            this.weizhi = weizhi;
        }

        public String getYuanyin() {
            return yuanyin;
        }

        public void setYuanyin(String yuanyin) {
            this.yuanyin = yuanyin;
        }

        public String getBeizhu() {
            return beizhu;
        }

        public void setBeizhu(String beizhu) {
            this.beizhu = beizhu;
        }

        public List<AccidentCarBean> getAccidentCarBeanList() {
            return accidentCarBeanList;
        }

        public void setAccidentCarBeanList(List<AccidentCarBean> accidentCarBeanList) {
            this.accidentCarBeanList = accidentCarBeanList;
        }

        public static class AccidentCarBean implements Serializable{
            /**
             * 事故车辆id
             */
            private int id;
            /**
             * 事故车辆
             */
            private Car car;
            /**
             * 事故车辆类型
             */
            private String type;
            /**
             * 事故车辆安全带类型
             */
            private String anquandai = "2";
            /**
             * 事故车辆灯列表
             */
            private List<DengItem> dengItems;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
            /**
             * 事故车辆违章列表
             */
            private List<WeizhangItem> weizhangItems;
            /**
             * 事故车辆故障列表
             */
            private List<GuzhangItem> guzhangItems;
            /**
             * 事故车辆速度列表
             */
            private List<Sudu> sudus;

            public List<Sudu> getSudus() {
                return sudus;
            }

            public void setSudus(List<Sudu> sudus) {
                this.sudus = sudus;
            }

            public Car getCar() {
                return car;
            }

            public void setCar(Car car) {
                this.car = car;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getAnquandai() {
                return anquandai;
            }

            public void setAnquandai(String anquandai) {
                this.anquandai = anquandai;
            }

            public List<DengItem> getDengItems() {
                return dengItems;
            }

            public void setDengItems(List<DengItem> dengItems) {
                this.dengItems = dengItems;
            }

            public List<WeizhangItem> getWeizhangItems() {
                return weizhangItems;
            }

            public void setWeizhangItems(List<WeizhangItem> weizhangItems) {
                this.weizhangItems = weizhangItems;
            }

            public List<GuzhangItem> getGuzhangItems() {
                return guzhangItems;
            }

            public void setGuzhangItems(List<GuzhangItem> guzhangItems) {
                this.guzhangItems = guzhangItems;
            }

            /**
             * 事故车辆所有速度类
             */
            public static class Sudu implements Serializable{
                /**
                 * 事故车辆速度列表
                 */
                private List<SuduItem> suduItems;

                public List<SuduItem> getSuduItems() {
                    return suduItems;
                }

                public void setSuduItems(List<SuduItem> suduItems) {
                    this.suduItems = suduItems;
                }
                /**
                 * 事故车辆单个速度类
                 */
                public static class SuduItem  implements Serializable{
                    /**
                     * 事故车辆速度时间
                     */
                    private String time;
                    /**
                     * 事故车辆速度值
                     */
                    private String value;

                    public String getTime() {
                        return time;
                    }

                    public void setTime(String time) {
                        this.time = time;
                    }

                    public String getValue() {
                        return value;
                    }

                    public void setValue(String value) {
                        this.value = value;
                    }
                }
            }
            /**
             * 事故车辆每个灯类
             */
            public static class DengItem implements Serializable{
                /**
                 * 事故车辆打灯的值
                 */
                private String deng;
                /**
                 * 事故车辆打灯时间
                 */
                private String time;

                public String getDeng() {
                    return deng;
                }

                public void setDeng(String deng) {
                    this.deng = deng;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }
            }

            public static class WeizhangItem implements Serializable {
                /**
                 * 违章时间
                 */
                private String time;
                /**
                 * 违章原因
                 */
                private String yuanyin;
                /**
                 * 违章地址
                 */
                private String dizhi;    //2308.28715,11322.09876

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getYuanyin() {
                    return yuanyin;
                }

                public void setYuanyin(String yuanyin) {
                    this.yuanyin = yuanyin;
                }

                public String getDizhi() {
                    return dizhi;
                }

                public void setDizhi(String dizhi) {
                    this.dizhi = dizhi;
                }
            }

            public static class GuzhangItem implements Serializable {
                /**
                 * 故障时间
                 */
                private String time;
                /**
                 * 故障值
                 */
                private String guzhang;
                /**
                 * 故障id
                 */
                private int id;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getTime() {
                    return time;
                }

                public void setTime(String time) {
                    this.time = time;
                }

                public String getGuzhang() {
                    return guzhang;
                }

                public void setGuzhang(String guzhang) {
                    this.guzhang = guzhang;
                }
            }
        }
    }
}
