# front-end of NMSI

此文件夹中存放NMSI中的前端代码，使用vue.js构建

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build
```
# 代码结构

使用vue.js构建，app.vue作为主入口，在component中存放其他的页面。
为了顺应web2.0潮流以及更加舒适的交互体验，前端部分决定使用SPA的单页面方式构建。整体无刷新链接操作。
在页面中，主要分为登陆以及主页面。其中登陆页面负责验证个人信息以及用户。当密码和用户名正确以后将可以进入到主界面中。
暂时我们将只支持用户admin以及密码为123，后续用户注册将会在之后的迭代中开发。

# 使用到的技术

 - SPA单页面结构
 - vue.js框架构建
 - vue-router路由跳转
 - element-ui交互样式库
 - api.douban 豆瓣电影接口
 
# 第一次迭代
 - 增加基本的登陆页面和电影主页面的架构
 - 完成各项将会使用到的函数以及接口的设计
 - 引入elemnt-ui以及vue-router等技术，为将来的各种功能做基本的插件库引入

# 第二次迭代
 - 引入豆瓣电影接口，可以获取到时下热门的电影资讯，方便我们直接得到电影数据发送给后台
 - 持续开发前端交互，增加多种交互
 - 增加选座系统
