# cas-client
cas单点登录客户端demo
cas-client1和cas-client2是使用
```
<dependency>
    <groupId>net.unicon.cas</groupId>
    <artifactId>cas-client-autoconfig-support</artifactId>
    <version>2.3.0-GA</version>
</dependency>
```
官方依赖包做的默认demo。

+ customer-cas-client-spring-boot-starter是整合官方依赖包后写的适用于目前主流的前后分离的分布式系统。
+ cas原理:利用浏览器重定向到cas服务器登录认证，cas服务器返回cookie和ticket信息进行验证。

+ 官方默认的cas-client包执行流程:
```
1、客户端请求
->  2、服务端验证是否单点登录->否
->  3、重定向到cas服务端登录接口
->  4、用户登录
->  5、重定向回客户端一开始请求的url，并且带上ticket参数
->  6、服务端携带ticket参数进行http请求cas服务器的ticket验证接口
->  7、ticket验证通过
->  8、cas服务器返回服务端登录会话信息，用户名、认证时间等。
->  9、服务端进行会话信息存储，默认使用session
->  10、单点登录成功
```
#### 由此可见，问题的关键在于第3步、第5步，对于前后分离的系统来说，这第3部和第5步着实挺为难，因为会产生跨域问题。
#### 因此，customer-cas-client-spring-boot-starter对执行流程做了修改。
#### 第3步改为服务端返回浏览器cas登录认证
#### url和认证通过后的重定向url，还需要浏览器拼接前端页面路由url信息，拼接成一个新的url在浏览器地址栏请求，这一步
#### 需要前端配合。
#### 第5步把cas服务器进行重定向会服务端的url改为自定义的默认的认证url，并携带上前端路由url作为参数，服务端验证ticket
#### 通过后，登录会话信息存储后，意味着单点登录认证已完成，将重定向到前端路由，由此，单点登录完成。
#### 此举不会产生跨域问题，也能无缝衔接cas-server。就是需要前端配合这一步比较复杂。

# 自定义cas-client使用，仅适用于springboot项目:
```
引入依赖:需要下载代码，代码没有发到中央仓库
<dependency>
    <groupId>com.kim</groupId>
    <artifactId>customer-cas-client-spring-boot-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>

启动类加入注解:@EnableCustomerCasClient
配置文件:
cas:
  server-url-prefix: http://localhost:8443/cas    #cas-server前缀
  server-login-url: http://localhost:8443/cas/login    #cas-server登陆url
  client-host-url: http://localhost:9004              #客户端服务地址
  validation-type: cas   #验证协议Patterns

自定义配置类继承com.kim.cas.client.starter.config.CasClientConfigurerAdapter这个适配器，注入spring容器
重写相应的接口方法进行自定义处理器，例如登录会话信息处理，可以保存在session可以保存在redis等，
重写TicketValidationManager ticketValidationAssertionManager()方法。
还有cas验证的url集合配置，忽略验证的url集合配置，登录验证失败返回响应处理器等等，都可以重写相应的方法。
如果没有配置类，将采用默认的配置。
customer-cas-client这个项目是一个例子，可以参考。
```
+ 官方包提供的支持更适合后端渲染前端页面的单体架构系统 
