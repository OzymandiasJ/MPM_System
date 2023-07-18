使用说明：
1，请修改druid.properties中的url以及数据库账户名username和password
2，运行mysql.txt脚本，创建数据库
2，将web.WEBINF.lib中的所有文件加入依赖
4，配置tomcat启动URL参数：http://localhost:8080/yourProjectName/index，即后面加上index
5，启动项目，会自动打开浏览器

注：
1,暂时没精力提高鲁棒性，尽量不要非法输入或者空缺输入提交
2,为了解决项目运行过程中突然出现的错误：
    The last packet sent successfully to the server was 44,476 milliseconds ago.
    is longer than the server configured value of 'wait_timeout'.

    这个错误是因为navicat默认设置的wait_timeout参数为20(秒)过小的问题，所以最好设置一下mysql连接参数：
    在navicat——新建查询，执行以下语句即可：

    set global wait_timeout=3600;

    意思是把wait_timeout设置为3600秒，1小时



工程说明：

一，myssm.utils工具包：
    1，jdbcutils
    负责简化封装：
        注册驱动
        获取连接
        管理连接池
        回收连接
    主要负责的是连接相关的内容
    2，BaseDao
    负责简化封装：
        查询的方法
        非查询的方法
    主要负责的是数据库具体的动作 curd
二，myssm.myspringmvc包
    ViewBase工具，用于thymeleaf页面渲染
三，dao包
    针对User类的数据库操作封装
四，view包
    实现控制台输出
五，beans包
    里面放的是Product类的实现
六，demo_xxx包
    学习过程中用到的，不需要的，可以删掉
七，Products包
    1，dao
        实现数据库查询
    2，servlets
        实现各个servlet








