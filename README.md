一寻
===============
一个交通工作人员使用的手机工作软件

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


### 开发说明
~~~
包结构说明
├─com.soarsky.policeclient   												存在APP基本文件，例如应用程序，常量等  
│  ├─com.soarsky.policeclient.base                                          里面包含一些基类
│  ├─com.soarsky.policeclient.bean                                          程序中所有的实体类都在这里
│  ├─com.soarsky.policeclient.check2                                        稽查第二套方案功能实现
│  ├─com.soarsky.policeclient.data                                          本地数据库操作和数据表实体
│  │  │  ├─com.soarsky.policeclient.data.local.db.bean						本地数据表实体
│  │  │  ├─com.soarsky.policeclient.data.local.db.bean.accident             本地数据表事故分析实体
│  │  │  ├─com.soarsky.policeclient.data.local.db.dao						greendao根据实体类自动生成的代码
│  │  │  ├─com.soarsky.policeclient.data.map.baidu							百度地图图层展示
│  │  │  ├─com.soarsky.policeclient.data.map.design							百度第三方接口的回调
│  │  │  │  ├─com.soarsky.policeclient.data.remote.server1					网络请求一
│  │  │  │  ├─com.soarsky.policeclient.data.remote.server2					网络请求二
│  │  │  │  ├─com.soarsky.policeclient.helper								Rx转换器
│  │  │  │  ├─com.soarsky.policeclient.server.bluetoothle					与智能终端通信的蓝牙功能
│  │  │  │  ├─com.soarsky.policeclient.server.cmd							与智能终端通信的命令
│  │  │  │  ├─com.soarsky.policeclient.server.design						定义了一些与终端交互的接口
│  │  │  │  ├─com.soarsky.policeclient.server.wifi							与终端通信的wifi功能实现
│  │  │  │  ├─com.soarsky.policeclient.ui.accident							事故分析功能
│  │  │  │  ├─com.soarsky.policeclient.ui.accident.add						事故分析添加事故
│  │  │  │  ├─com.soarsky.policeclient.ui.accident.itemcar					事故分析事故车辆详情
│  │  │  │  ├─com.soarsky.policeclient.ui.accident.listandadd				事故分析列表
│  │  │  │  ├─com.soarsky.policeclient.ui.accident.listitem					事故分析每一条事故
│  │  │  │  ├─com.soarsky.policeclient.ui.accident.selectCar				事故分析选择车辆
│  │  │  │  ├─com.soarsky.policeclient.ui.accident.serverbean				事故分析对应的服务器bean
│  │  │  │  ├─com.soarsky.policeclient.ui.audit								以前的稽查界面
│  │  │  │  ├─com.soarsky.policeclient.ui.blacklist							黑名单界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.check								稽查界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.details							车辆详情界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.elecdetails						电子罚单详情界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.forget							忘记密码界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.home								首页界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.home.view							自定义的一些视图
│  │  │  │  ├─com.soarsky.policeclient.ui.login								登录界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.modify							修改密码界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.record							历史记录界面与功能
│  │  │  │  ├─com.soarsky.policeclient.ui.record.fragment					历史记录fragment
│  │  │  │  ├─com.soarsky.policeclient.ui.violation							开电子罚单界面与功能
│  │  │  │  ├─com.soarsky.policeclient.uitl									包含一些常用的工具包 
│  │  │  ├─com.soarsky.policeclient.wfbtserver								wifi和蓝牙的一些api功能
│  │  │  │  ├─com.soarsky.policeclient.wfbtserver.bluetooth					蓝牙的api功能
│  │  │  │  ├─com.soarsky.policeclient.wfbtserver.check						稽查第六套方案的实现
│  │  │  │  ├─com.soarsky.policeclient.wfbtserver.check2					稽查第二套方案的实现
│  │  │  │  ├─com.soarsky.policeclient.wfbtserver.design					wifi和蓝牙的接口定义
│  │  │  │  ├─com.soarsky.policeclient.wfbtserver.wifi						wifi的接口实现

说明API
更多说明，请按照如下操作：
1. git clone http://192.168.100.19/gtw/android_police_app.git
2. cd help
3. 用浏览器打开index.html
所有的类和方法注释都可以查看

~~~

## 参与开发
请参阅http://android.developer.com/


## 版权信息

硕铠电子科技有限公司
更多细节参阅 [LICENSE.txt](LICENSE.txt)
