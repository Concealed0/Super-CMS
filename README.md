#一、项目定位
这个项目为一个CMS管理系统该管理系统可以实现用户订购网站的功能，有CMS可以实现后台查询信息。以及一个网站模块的点击次数，图形表示。
先对后台系统进行功能设计
1、管理员登陆功能，注册
2、查询功能，有录入时间，URL，价格，期限，删除修改。增添。
3、修改模块，网站模板的上传，删除，隐藏。（前台网站点击可以直接跳转到指定H5目录）4、文章管理，前台页面文章管理，对文章的增删改查。
5、会员登录，序号，注册账号，注册密码，注册手机号，注册邮箱，注册时间，购买力度。恢复删除（等一系列用户信息）
6、用户留言，序号，ID，内容，时间，答复，删除。
7、数据分析，前台模块的每天的点击次数，图表表示
8、前台购买，收藏，加入购物车

#二、管理员登陆模块
管理员登陆功能，保留权限控制模块，即通过用户账号来管理的后台权限，在此次设计中保留管理员数据库接口。
先设计下管理员登陆的数据表le_admin_user
字段	类型	长度	空值	描述	
user_id	int	10	×	账号ID	主键
group_id	int	10	×	权限分类	
username	varchar	30	×	账号名称	
password	varchar	32	×	账号密码	
tel	varchar	20	√	手机号	
email	varchar	50	√	邮箱	
status	int	10	×	账号状态	
last_login_time	datetime	0	×	账号登陆时间	
last_login_ip	varchar	20	×	账号登陆IP	

mysql> create table le_admin_user(
    -> user_id int(10) NOT NULL
    -> group_id int(10) NOT NULL
    -> username varchar(30) NOT NULL
    -> password varchar(32) NOT NULL
    -> email varchar(50)
    -> tel int(20)
    -> status int(10) NOT NULL
    -> last_login_time datetime NOT NULL
    -> last_login_ip varchar(20) NOT NULL
    -> PRIMARY KEY (`user_id`)
-> );
create table le_admin_user(user_id int(10) NOT NULL,group_id int(10) NOT NULL,username varchar(30) NOT NULL,password varchar(32) NOT NULL,email varchar(50),tel int(20),status int(10) NOT NULL,last_login_time datetime NOT NULL,last_login_ip varchar(20) NOT NULL, PRIMARY KEY (`user_id`));


截止到2019-05-25 21:14，已经完全完成对管理员登陆模块的管理。除了对注册页面的开发。

#三、账号日志管理
当由用户登陆时，将登陆信息保存到数据库。提高系统的稳定性，也能够对系统的最后登陆着进行记录，可以直接查看后台登陆信息，保证系统的安全性。
解决方案
1、直接通过缓存，存储用户登录信息
2、涉及的数据库。le_admin_log.sql

le_admin_log
实际需求分析
在这个数据表中，主要是存放对登陆系统的用户信息。其中需要获取到用户的账号、登陆ip，登陆时间、通过什么方式进行登录的、是否登陆成功、登陆失败的原因，同时增加一个自增id序列，或者增加一个用户ID的权限，这个最后再加。
E-R图概念设计
日志数据库逻辑设计×√
le_admin_log 序号、所属权限、登陆账号、登陆ip、登陆方式、是否登陆成功。
逻辑结构如下
字段	类型	长度	是否为空	描述
log_id	int	10	×	登陆序号自增
log_group	int	10	×	登陆账号权限
log_user	varchar	250	×	登陆账号
log_ip	varchar	250	×	登陆ip地址
log_time	datetime	0	×	登陆时间
log_app	varchar	250	×	登录方式
log_con	varchar	250	×	是否登陆成功
log_status	int	10	√	判断是否可以登录

建立好存储日志的数据库后，再对权限所属建立其他模块。
le_admin_group逻辑结构如下
字段	类型	长度	是否为空	描述
gruop_id	int	10	×	权限id
name	varchar	10	×	id的名称
base_purview	text	0	×	底层权限序号
menu_purview	text	0	×	首页菜单序号
status	int	5	×	权限状态

已完成对项目的日志管理
目前已经完成对用户的登陆日志的收集，以后可以在后台安全管理中查看登陆日志情况，同时在系统中也可以查看恶意登陆的浏览器，进行禁止的登陆。

#四、账号注册页面

根据业务需求设置一个会员注册页面，根据实际业务需求，这个系统是用于网络广告公司进行业务需求的一个系统，根据后台问题，系统目前不需要增加注册功能。

#五、系统实际功能
以广告公司为例。广告公司
涉及业务：
对于接单来说：
接单项目名称、项目承接时间、项目承接人、项目预计完成时间、项目预算，是否通过、通过时间。
对于员工业务来说：
员工承接总数量、项目承接总金额、月成交数量、月成交金额、月工资分红、月实际工资
对于员工工作记录
员工名称、出去谈业务、业务内容、出去时间、回来时间、所谈情况、


根据实际需求，本项目先完成对网站公司的后台管理，通过后台可以
1、对公司整体运行情况的记录。比如在首页显示今天待完成项目，昨天完成项目，可以对项目进行增删改查。
2、在首页应该有一个完整描述，有指定目录，待完成项目，等
3、
##1、导航菜单实现
思路一、
通过保存的登陆账号，通过session将保存的信息存在缓存中，然后进入前台后，将后台信息返回给数据库，通过数据库查找指定权限的三级菜单信息。
解决方案、
1、通过系统缓存获取登陆账号，并将账号保存在全局变量中。
2、通过全局变量获取到这个账号在系统中的权限，即在权限表中查询到这个账号在系统中拥有几级菜单名称ID，并将菜单名称保存在指定数组中。
3、最后通过菜单ID将菜单从菜单表中读取出，返回前台一个三维数组。
4、最后将三维数组返回给前台，通过前台将三维菜单读取为三级目录。

涉及到的数据库 
1、后台账号登陆数据库。le_admin_user.sql
2、判断后台权限数据库。le_admin_group.sql
3、后台菜单数据库。le_admin_menu.sql


后台菜单数据表le_admin_menu
字段	类型	长度	是否为空	描述
id	int	10	×	目录对应的几级菜单ID
pid	int	10	×	目录ID
name	varchar	50	×	菜单名称
url	varchar	255	√	
act	varchar	255	√	
iconfont	varchar	50	×	图标
sort	int	5	×	排序
lang_id	int	5	×	字段
status	int	5	×	状态
进入首页后将进行后台菜单的流程，后台菜单流程采用多叉树建立，将递归到的每个节点都加入到上级的child节点中，后台使用layUI前端框架的菜单导航，通过点击一级菜单找到对应的二级菜单和三级菜单，在首页导航栏中获取到的菜单JSON数据格式如下

后台建立的AdminMenu实体中采用List<AdminMenu> children 子孩子变量，用于对父节点存储子节点。


##2、多级菜单管理
通过上述对多级菜单的管理，在对多级菜单的管理中使用多叉树，深度遍历的方式进行递归获取
创建多叉树，通过创建菜单数据库的id与pid字段进行确认每一节点树处在什么位置，使用java的list实体进行创建多叉树的节点结构
private AdminMenu nodeEntity;   //用于存放节点的值
private List<MenuTree> childNodes;  //使用list可以在相同节点插入更多的子节点，与二叉树相反。
使用public boolean CreateTree(MenuTree menutree,List<AdminMenu> menuall){}
函数进行创建子节点。
在创建子节点的过程中采用递归的形式创建。
创建好多叉树后使用深度优先搜索进行先序遍历

三级菜单流程图

三级菜单中的显示流程，分级流程

在三级菜单显示程序中使用的是将下级菜单加入到上级菜单中，




id三位数	百位	十位	个位	正则匹配
顶级菜单	*	*	0	^0$
一级菜单	[1-9]个任意随机数	0	0	^[1-9]{1}00$
二级菜单	和上一级百位数相同	[1-9]个任意随机数	0	^[1-9]{2}0$
三级菜单	和上一级百位数相同	和上一级十位数相同	[1-9]个任意随机数	^[1-9]{3}$



截止到2019-06-30 18:47，已经完全完成对管理员登陆模块的管理。
在首页导航栏的多级菜单与超级权限的菜单管理中都采用多叉树的结构进行递归遍历，不同的是二者建立的树结构不同。
导航栏的多级菜单是创建多叉树，将每个子节点加入到父节点的实体中，最终输出的JSON数组为以list实体为首的多维数组。
多级菜单管理中采用的是创建多叉树的，每个子节点的结构为一个list<AdminMenu> 实体，同时将每个子节点加入到父节点中，最终遍历出的JSON数组中是AdminMenu实体的数组。


在多级菜单中使用前端js操作，可以对菜单的status进行更改，在后台更改status中减少多余的冗余代码，将所有的菜单状态更改或者用户状态更改集成到一个接口中，使用的接口逻辑结合实际情况进行判断，通过url传值，后台对值进行区分，来判断使用方法，具体业务逻辑再此不阐述。

截止到目前2019-07-01 22:30
已完成
1、系统登录、退出、对登陆用户的编辑，删除和增加登陆用户，在用户管理页面实现对用户的登陆权限更改操作
2、导航菜单实现、多级菜单的管理，在多级菜单权限页面实现对菜单状态、排序、编辑、删除操作
3、系统用户登录日志查询
具体图片如下
用户登录界面
![网站整体情况](https://github.com/Concealed0/Super-CMS/blob/master/Images/log-001.png)
redis缓存
![网站整体情况](https://github.com/Concealed0/Super-CMS/blob/master/Images/redis-001.png)
首页（未重置）
![网站整体情况](https://github.com/Concealed0/Super-CMS/blob/master/Images/home-001.png)
日志管理
![网站整体情况](https://github.com/Concealed0/Super-CMS/blob/master/Images/log-001.png)
redis-日志显示
![网站整体情况](https://github.com/Concealed0/Super-CMS/blob/master/Images/redis-log-001.png)
账号管理
![网站整体情况](https://github.com/Concealed0/Super-CMS/blob/master/Images/account-001.png)
菜单管理
![网站整体情况](https://github.com/Concealed0/Super-CMS/blob/master/Images/menu-001.png)
hbase接口
![网站整体情况](https://github.com/Concealed0/Super-CMS/blob/master/Images/hbase-001.png)



                    
                                                         2019年7月1日星期一


##4、redis缓存技术
将访问信息量多的并发操作加入缓存，减轻数据库压力，可以用在访问多个网站中加入数据库，高并发的操作等
具体场景：
1、超级管理页面的网站管理，加入新的页面地址等地址页面，图片地址信息-----高并发
2、
3、

##5、网站模板订购
1、在具体页面中有多个详细信息。后台可以做到对单个网站模板的删除、编辑、隐藏。以及增加具体的网站。其中涉及后台数据库创建、以及后台管理的内容、模板的上传、图片的存储等
2、后期增加对模板订购

6、员工数据分析
1、使用表格将每天员工的日报存入数据库、分析表中的数据信息得出员工目前的实际工作情况、加入员工迟到、早退、请假、外出、工作情况、业绩、等具体情况、最终可以导出xls表格
2、对员工数据进行数据分析，查看员工具体工作效率等
3、使用最新的技术人脸识别、人脸录入、人脸签到
