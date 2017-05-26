伴君行
===============
一个管理电子驾照与电子车牌的手机软件

公司主页
http://www.soarsky-e.com/

开发团队队伍 


## 目录结构

初始的目录结构如下：


## 命名规范

### 目录和文件

*   目录不强制规范，驼峰和小写+下划线模式均支持；
*   类库、函数文件统一以`.jar`为后缀；
*   类的文件名均以命名空间定义，并且命名空间的路径和类库文件所在路径一致；
*   类名和类文件名保持一致，统一采用驼峰法命名（首字母大写）；

### 函数和类、属性命名
*   类的命名采用驼峰法，并且首字母大写，例如 `User`、`UserType`，默认不需要添加后缀，例如`UserController`应该直接命名为`User`；
*   函数的命名使用小写字母和下划线（小写字母开头）的方式，例如 `get_client_ip`；
*   方法的命名使用驼峰法，并且首字母小写，例如 `getUserName`；
*   属性的命名使用驼峰法，并且首字母小写，例如 `tableName`、`instance`；
*   以双下划线“__”打头的函数或方法作为魔法方法，例如 `__call` 和 `__autoload`；

### 常量和配置
*   常量以大写字母和下划线命名，例如 `APP_PATH`和 `THINK_PATH`；
*   配置参数以小写字母和下划线命名，例如 `url_route_on` 和`url_convert`；

### 数据表和字段
*   数据表和字段采用小写加下划线方式命名，并注意字段名不要以下划线开头，例如 `think_user` 表和 `user_name`字段，不建议使用驼峰和中文作为数据表字段命名。


## 环境
选择Google提供的使用比较方便的开发软件Android Studio做Android开发。

### 编码环境
* JDK：JDK1.8 64位
* SDK：24.0.3
* 支持版本：4.0以上 市场占有率99.7%（该数据来源于Google官方统计）
	
### 运行环境
* 硬件要求：计算机，开发手机
* 操作系统：windows，Android 服务器
* 开发环境：Android Studio

	
## 框架设计
伴君行APP主要针对的用户是车主用户，里面包括了违章数据管理，确认驾驶员等逻辑，故项目设计采用分前台UI模块，后台service模块，SQLite数据库模块三大结构进行设计.

UI模块负责与用户交互，主要采取MVP设计，见图2-3-2图；Service层负责确认驾驶员，同步数据，管理wifi ，蓝牙，http/https 等网络连接模块；Database负责违章数据，亲情号码等数据的保存，采取的是GreenDao封装的SQLite数据库。网络连接采用OKHttp库，利用Reconfit对OKHttp进行封装，直接转换成Rx对象，方便前端在线程之中切换使用。图片的处理采用Glide库。


### MVP
项目UI层采用了MVP框架设计。
随着UI创建技术的功能日益增强，UI层也履行着越来越多的职责。为了更好地细分视图(View)与模型(Model)的功能，让View专注于处理数据的可视化以及与用户的交互，同时让Model只关系数据的处理，基于MVC概念的MVP(Model-View-Presenter)模式应运而生。
在MVP模式里通常包含4个要素：
* View:负责绘制UI元素、与用户进行交互(在Android中体现为Activity);
* View interface:需要View实现的接口，View通过View interface与Presenter进行交互，降低耦合，方便进行单元测试;
* Model:负责存储、检索、操纵数据(有时也实现一个Model interface用来降低耦合);
* Presenter:作为View与Model交互的中间纽带，处理与用户交互的负责逻辑。
 
### RxJava
RxJava是一个在 Java VM 上使用可观测的序列来组成异步的、基于事件的程序的库。
异步操作很关键的一点是程序的简洁性，因为在调度过程比较复杂的情况下，异步代码经常会既难写也难被读懂。 Android 创造的AsyncTask 和Handler ，其实都是为了让异步代码更加简洁。RxJava 的优势也是简洁，但它的简洁的与众不同之处在于，随着程序逻辑变得越来越复杂，它依然能够保持简洁。
### Retrofit
Retrofit与okhttp共同出自于Square公司，retrofit就是对okhttp做了一层封装。把网络请求都交给给了Okhttp，我们只需要通过简单的配置就能使用retrofit来进行网络请求了。
相对于Volley，AsyncHttpClient 等库，RxJava+Retrofit 的优势并非特别显著；在执行效率及功能上并无大的亮点；对Volley进行良好的封装同样可以实现类似Retrofit自动转Gson的功能；RxJava+Retrofit 结合会让我们写代码的方式更加有条理，虽然代码量会增多，但逻辑的清晰,所以，RxJava+Retrofit 组合不失为一种好的选择。
### OkHttp3
OkHttp是一款优秀的HTTP框架，它支持get请求和post请求，支持基于Http的文件上传和下载，支持加载图片，支持下载文件透明的GZIP压缩，支持响应缓存避免重复的网络请求，支持使用连接池来降低响应延迟问题。
具有如下优点：
* 支持HTTP2/SPDY黑科技
* socket自动选择最好路线，并支持自动重连
* 拥有自动维护的socket连接池，减少握手次数
* 拥有队列线程池，轻松写并发
* 拥有Interceptors轻松处理请求与响应（比如透明GZIP压缩,LOGGING）
* 基于Headers的缓存策略

### GreenDAO
GreenDAO是一个开源的用于简便操作SQLite数据库的安卓对象关系映射框架。如下图所示，greenDAO就是连接SQLite和Java对象的桥梁，它能简便的将对象映射到SQLite 数据库中。

具有如下优点：
*	一个精简的库
*	性能最大化
*	内存开销最小化
*	易于使用的 APIs
*	对 Android 进行高度优化


### Glide
Glide 是 Google 员工的开源项目， Google I/O 上被推荐使用，一个高效、开源、Android设备上的媒体管理框架，它遵循BSD、MIT以及Apache 2.0协议发布。Glide具有获取、解码和展示视频剧照、图片、动画等功能，它还有灵活的API，这些API使开发者能够将Glide应用在几乎任何网络协议栈里。创建Glide的主要目的有两个，一个是实现平滑的图片列表滚动效果，另一个是支持远程图片的获取、大小调整和展示。
具有如下优点：
*	使用简单
*	可配置度高，自适应程度高
*	支持常见图片格式 Jpg png gif webp
*	支持多种数据源  网络、本地、资源、Assets 等
*	高效缓存策略    支持Memory和Disk图片缓存 默认Bitmap格式采用RGB_565内存使用至少减少一半
*	生命周期集成   根据Activity/Fragment生命周期自动管理请求
*	高效处理Bitmap  使用Bitmap Pool使Bitmap复用，主动调用recycle回收需要回收的Bitmap，减小系统回收压力


## 开发详细说明
~~~

com.baidu.mapapi.clusterutil													百度导航第三方包（不需要修改）
com.baidu.mapapi.clusterutil.clustering											百度导航第三方包（不需要修改）
com.baidu.mapapi.clusterutil.clustering.algo									百度导航第三方包（不需要修改）
com.baidu.mapapi.clusterutil.clustering.view									百度导航第三方包（不需要修改）
com.baidu.mapapi.clusterutil.projection											百度导航第三方包（不需要修改）
com.baidu.mapapi.clusterutil.quadtree											百度导航第三方包（不需要修改）
com.baidu.mapapi.clusterutil.ui													百度导航第三方包（不需要修改）
com.baidu.mapapi.overlayutil													百度导航第三方包（不需要修改）
com.soarsky.car																	应用程序入口
com.soarsky.car.base															里面包含一些基类
com.soarsky.car.bean															程序中所有的实体类都在这里
com.soarsky.car.customview														里面包含一些自定义View
com.soarsky.car.data.local.db													本地数据库操作的基本方法
com.soarsky.car.data.local.db.bean												本地数据库的实体类
com.soarsky.car.data.local.db.dao												greendao根据实体类自动生成的代码
com.soarsky.car.data.map.baidu													百度地图图层展示
com.soarsky.car.data.map.design													百度第三方接口的回调
com.soarsky.car.data.remote.server1												网络请求一
com.soarsky.car.data.remote.server2												网络请求二
com.soarsky.car.data.remote.server3												网络请求三
com.soarsky.car.helper															Rx转换器
com.soarsky.car.server.check													后台服务用来管理扫描终端和与终端数据交互，监听网络,来电等等
com.soarsky.car.server.cmd														与终端交互发送数据的封装和接受数据的解析
com.soarsky.car.server.dangerousdriving											开车玩手机监听实现
com.soarsky.car.server.design													定义了一些与终端交互的接口
com.soarsky.car.server.wifi														确认驾驶员,行车记录，连接终端的具体实现
com.soarsky.car.ui.blindterm													绑定终端
com.soarsky.car.ui.blindterm.validation											绑定终端验证
com.soarsky.car.ui.borrowandreturn                        						借车还车，有借车、还车和查看借车记录的功能
com.soarsky.car.ui.borrowandreturn.borrow                 						借车，输入正确的车牌号和车主手机号可以发送借车申请
com.soarsky.car.ui.borrowandreturn.borrow.picker                                
com.soarsky.car.ui.borrowandreturn.borrowaplication        						收到的借车申请详细信息，可以同意或进行拒绝操作。
com.soarsky.car.ui.borrowandreturn.borrowaplication.aplicationrefuse         	借车方收到的车主拒绝理由的页面
com.soarsky.car.ui.borrowandreturn.borrowaplication.refuse       				车主填写借车理由的界面
com.soarsky.car.ui.borrowandreturn.borrowaplication.sucess       				借车成功页面
com.soarsky.car.ui.borrowandreturn.borrowrecord               					借车记录，展示借车记录的列表，点击可以查看记录列表详情
com.soarsky.car.ui.borrowandreturn.recorddetails              					记录详情
com.soarsky.car.ui.borrowandreturn.retur                      					还车，点击所需归还的车辆进行还车
com.soarsky.car.ui.borrowandreturn.retur.carreturn            					车主看到的车辆归还成功显示的页面，有借用车牌和借车人详细信息
com.soarsky.car.ui.borrowandreturn.retur.returnsuccess       				 	借车人看到的车辆归还成功显示的页面
com.soarsky.car.ui.borrowandreturn.wheelview                				    时间弹框
com.soarsky.car.ui.borrowandreturn.wheelview.bean                               时间弹框bean
com.soarsky.car.ui.callphone													开车玩手机			
com.soarsky.car.ui.car															行驶证
com.soarsky.car.ui.car.validation												行驶证验证
com.soarsky.car.ui.carchange													切换车辆
com.soarsky.car.ui.carcheck                                                     车辆检测
com.soarsky.car.ui.carcheck.carchecklogin                                       车辆检测的起始页
com.soarsky.car.ui.carlocation.alarm											防盗报警
com.soarsky.car.ui.carlocation.lovecar											爱车位置
com.soarsky.car.ui.carlocation.position											爱车位置的详情信息
com.soarsky.car.ui.carnews                    									汽车资讯，有三类资讯信息，分别为：车辆保养、交通安全、交通法规。此模块还有收藏、最近阅读、展示收藏的资讯列表、根据关键字在线搜索相关资讯的功能             
com.soarsky.car.ui.carnews.carnewsfragment    									TabPageIndicator的效果展示三类资讯信息
com.soarsky.car.ui.carnews.carnewssearch       									点击搜索框后会跳转到搜索页面，输入关键字可搜索相关资讯，还可以展示搜索记录
com.soarsky.car.ui.carnews.mycolection         									点击我的收藏可查看收藏的资讯，可删除，可点击阅读详情
com.soarsky.car.ui.danger.compen												出险报警基本信息和图片上传
com.soarsky.car.ui.danger.responsibility										出险报警责任认定列表
com.soarsky.car.ui.danger.responsibilitydetails									出险报警责任认定详情
com.soarsky.car.ui.danger.responsibilitydetails.haveobjection   				出险报警这人认定无异议
com.soarsky.car.ui.danger.start													出险报警入口
com.soarsky.car.ui.drivrecord													行驶记录
com.soarsky.car.ui.drivrecord.map												行驶记录地图
com.soarsky.car.ui.drivrecord.nosimcard											没simcard
com.soarsky.car.ui.family														亲情号码
com.soarsky.car.ui.family.familynum                                             亲情号码设置
com.soarsky.car.ui.family.familynumconfirm                                      亲情号码确认修改
com.soarsky.car.ui.family.familynumupdate										亲情号更新
com.soarsky.car.ui.flashlight             										在一定距离内通过闪灯快速找到车辆
com.soarsky.car.ui.forget														忘记密码
com.soarsky.car.ui.home															首页
com.soarsky.car.ui.home.fragment												主界面包含的子界面
com.soarsky.car.ui.home.fragment.personal										个人界面
com.soarsky.car.ui.home.main													主界面
com.soarsky.car.ui.home.map														地图
com.soarsky.car.ui.home.map.route												地图路线
com.soarsky.car.ui.home.map.route.plan											地图路线规划
com.soarsky.car.ui.home.map.route.point											地图起始点设置
com.soarsky.car.ui.home.map.route.select										选择地图路线
com.soarsky.car.ui.home.map.route.set											路线规划设置
com.soarsky.car.ui.home.map.search												地图周边城市搜索
com.soarsky.car.ui.home.view													首页view
com.soarsky.car.ui.illegal														违章管理入口
com.soarsky.car.ui.illegal.adapter												违章适配
com.soarsky.car.ui.illegal.advise												违章劝离
com.soarsky.car.ui.illegal.detail												违章详细信息
com.soarsky.car.ui.illegal.electronic											违章电子告知单
com.soarsky.car.ui.illegal.fragment.driver										根据驾照证获取违章信息列表
com.soarsky.car.ui.illegal.fragment.personal									根据车牌好获取违章信息列表
com.soarsky.car.ui.illegal.query												违章查询
com.soarsky.car.ui.illegal.query.list											违章列表
com.soarsky.car.ui.illegal.query.set											违章切换车辆设置查询密码
com.soarsky.car.ui.license														驾照证信息
com.soarsky.car.ui.license.pwd													驾照证密码设置
com.soarsky.car.ui.license.validation											驾照证密码验证
com.soarsky.car.ui.license.view													驾照证view工具类
com.soarsky.car.ui.licenseinformation											驾驶证信息
com.soarsky.car.ui.licenseinformation.cardetails								驾驶证车辆详情
com.soarsky.car.ui.licenseinformation.cardetails.authentication                 身份验证页面
com.soarsky.car.ui.licenseinformation.cardetails.fragment.carrecord             用车记录
com.soarsky.car.ui.licenseinformation.cardetails.fragment.faultrecord           故障记录
com.soarsky.car.ui.login														登陆
com.soarsky.car.ui.modifypwd   													修改密码
com.soarsky.car.ui.movecar                  									发送短信通知车主前来移车
com.soarsky.car.ui.register														注册
com.soarsky.car.ui.roadside														道路救援
com.soarsky.car.ui.roadside.detail												道路救援详情
com.soarsky.car.ui.roadside.dialog												道路救援dialog
com.soarsky.car.ui.roadside.fee													道路救援支付费用
com.soarsky.car.ui.roadside.fee.state											道路救援支付状态
com.soarsky.car.ui.roadside.list												申请救援列表
com.soarsky.car.ui.roadside.list.detail											申请救援列表中的详情信息
com.soarsky.car.ui.roadside.list.order											申请救援列表的订单
com.soarsky.car.ui.roadside.order												申请救援确认救援
com.soarsky.car.ui.roadside.rescue												申请救援
com.soarsky.car.ui.ticketed														电子罚单解析工具类				
com.soarsky.car.ui.trivelrecord													包含新车记录功能
com.soarsky.car.ui.trivelrecord.alarm											行车记录报警界面
com.soarsky.car.ui.trivelrecord.autoopen										设置行车记录保护时间功能
com.soarsky.car.ui.trivelrecord.blacklist										行车记录黑名单界面
com.soarsky.car.ui.trivelrecord.carlist											行车记录附近车辆
com.soarsky.car.ui.trivelrecord.riderecordstart									行车记录入口
com.soarsky.car.ui.trivelrecord.selectweek										行车记录保护时间重复日期设置界面
com.soarsky.car.ui.validdriverlistener											手动确认驾驶员功能的实现
com.soarsky.car.ui.violationdeal												违章处理
com.soarsky.car.ui.violationquery												违章查询
com.soarsky.car.uitl															包含一些常用的工具包				


~~~


~~~
说明API
更多说明，请按照如下操作：
1. git clone http://192.168.100.19/gtw/Andriod_Car_App.git
2. cd help
3. 用浏览器打开index.html
所有的类和方法注释都可以查看
~~~

## 编译
* 安装好JDK1.8， 把JDK环境变量配置好。
* 进入项目目录，打开cmd 命令窗口，windows 按 window键+ R键 
* 输入cmd 回车
* cd 进入代码跟目录
* 执行 gradlew clean 在linux下面 执行./gradlew clean
* 上个命令执行完成后，执行gradlew build   在linux下面 执行./gradlew build
* 执行成功后，在../app/build/output/apk/ 目前下会生成两个APP，一个debug 版本，一个release版本。
* release是签名版本。

## 参考信息 
请参阅http://android.developer.com/


## 版权信息

硕铠电子科技有限公司
更多细节参阅



