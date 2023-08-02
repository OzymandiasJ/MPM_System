JavaWeb极简商品后台管理系统（Minimalist Product Back-end Management System）， 实现了最基本的商品增删改查功能，适用于课设项目

项目预览：

1，index页面：
![image](https://github.com/Pluto365/MPM_System/assets/69197910/de18384b-a372-4e8a-8d53-6567cbc89e00)



2，点击商品名进入编辑页面，输入商品名可以进行搜索，然后进入商品编辑页面，编辑页面可以完成商品的删除：
![image](https://github.com/Pluto365/MPM_System/assets/69197910/3c797288-f0cd-49ff-bb0b-829991a6d5c7)



3，点击增加商品进入商品添加页面：
![image](https://github.com/Pluto365/MPM_System/assets/69197910/d0c537d3-ad6f-4bd2-be2f-92af7a03a224)







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














