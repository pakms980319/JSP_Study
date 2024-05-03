# 구인구직웹 프로젝트

:cd: DEVLOPMENT MOTIVATION
---
> Recruit site
> > -- 개발 동기 
> > -- empty

:date: HISTORY
---
[TOTAL](DOCUMENT/HISTORY/TOTAL) | [박민석](DOCUMENT/HISTORY/박민석) | 
[황보성현](DOCUMENT/HISTORY/황보성현) | [최원준](DOCUMENT/HISTORY/최원준) | 

:construction: PLANS
---
 |LANGUAGE|PLAN|IMPLEMENT|DESCRIPTION|
 |-|-|-|-|
 |JAVA|2024/02/27 ~ 2024/04/04|-|-|
 |JSP/SERVLET|2024/04/05 ~ 2024/04/28|-|-|
 |SPRING STS3|2024/04/29 ~ |-|-|
 |SPRING BOOT|-|-|-|

:satellite: MEMBERERS
--- 
|NAME|ROLE|DETAILS|DESCRIPTION| 
|---|---|---|---|
|박민석|BN| /  / |---|
|황보성현|BN|  -  /  / |---|
|최원준|FN/BN| -  / |---|


:four_leaf_clover: SKILLS
---

:pencil: FN
---

<img src="https://img.shields.io/badge/HTML5-3366CC?style=for-the-badge&logo=htmlacademy&logoColor=white"> <img src="https://img.shields.io/badge/CSS-1572B6?style=for-the-badge&logo=css3&logoColor=white"> <img src="https://img.shields.io/badge/JAVASCRIPT-F7DF1E?style=for-the-badge&logo=javascript&logoColor=white"> <img src="https://img.shields.io/badge/JQUERY-0769AD?style=for-the-badge&logo=jquery&logoColor=white"> 


:hammer: BN
---

<img src="https://img.shields.io/badge/JAVA-005571?style=for-the-badge&logo=doubanread&logoColor=white"> <img src="https://img.shields.io/badge/JSP-1E8CBE?style=for-the-badge&logo=doubanread&logoColor=white"> <img src="https://img.shields.io/badge/SERVLET-4B4B77?style=for-the-badge&logo=doubanread&logoColor=white"> <img src="https://img.shields.io/badge/SPRING-STS3-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/SPRINGBOOT-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"> 


:computer: DATABASE
---
<img src="https://img.shields.io/badge/MYSQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white">  

:clipboard: DEVOPS
---
<img src="https://img.shields.io/badge/GIT-F05032?style=for-the-badge&logo=git&logoColor=white"> <img src="https://img.shields.io/badge/GITHUB-181717?style=for-the-badge&logo=github2&logoColor=white">

:blue_book: END POINT DOC
---
|URI|REQUEST METHOD|REQUEST PARAMETER TYPE|RESPONSE VALUE TYPE|DESCRIPTION|
|---|---|---|---|---|
|/|---|---|---|---|
|/favicon.ico|---|---|---|---|
|---|---|---|---|---|
|/user/delete|---|---|---|---|
|/user/info|---|---|---|---|
|/user/join/businessMan|---|---|---|---|
|/user/join|---|---|---|---|
|/user/join/user|---|---|---|---|
|/user/login|---|---|---|---|
|/user/logout|---|---|---|---|
|/user/update|---|---|---|---|
|---|---|---|---|---|
|/item/businessMan/add|---|---|---|---|
|/item/businessMan/delete|---|---|---|---|
|/item/businessMan/info|---|---|---|---|
|/item/businessMan/list|---|---|---|---|
|/item/businessMan/update|---|---|---|---|
|/item/info|---|---|---|---|
|/item/list|---|---|---|---|


:notebook_with_decorative_cover: DEPENDENCIES LIST
---
|CAT|NAME|DESCRIPTION|LINK|-|-|
|-|-|-|-|-|-|
|FN|-|-|-|-|-|
|FN|-|-|-|-|-|
|BN|-|-|-|-|-|
|BN|-|-|-|-|-|
|DB|-|-|-|-|-|
|DEVOPS|-|-|-|-|-|

:dart: ERD
---
<link>

:page_with_curl: File Tree
---
```
C:.
│  .classpath
│  .project
│  README.md
│
├─.settings
│      .jsdtscope
│      org.eclipse.core.resources.prefs
│      org.eclipse.jdt.core.prefs
│      org.eclipse.wst.common.component
│      org.eclipse.wst.common.project.facet.core.xml
│      org.eclipse.wst.jsdt.ui.superType.container
│      org.eclipse.wst.jsdt.ui.superType.name
│
├─build
│  └─classes
│      │  .gitignore
│      │
│      └─com
│          └─example
│              └─app
│                  ├─controller
│                  │  │  FrontController.class
│                  │  │  HomeController.class
│                  │  │  SubController.class
│                  │  │
│                  │  ├─item
│                  │  │      ItemAdd.class
│                  │  │      ItemBusinessManDelete.class
│                  │  │      ItemBusinessManInfo.class
│                  │  │      ItemBusinessManSearch.class
│                  │  │      ItemBusinessManUpdate.class
│                  │  │      ItemInfoController.class
│                  │  │      ItemSearchController.class
│                  │  │
│                  │  └─user
│                  │          UserDeleteController.class
│                  │          UserInfoController.class
│                  │          UserJoinBusinessManSignUpController.class
│                  │          UserJoinController.class
│                  │          UserJoinUserSignUpController.class
│                  │          UserLoginController.class
│                  │          UserLogoutController.class
│                  │          UserUpdateController.class
│                  │
│                  ├─domain
│                  │  ├─common
│                  │  │      CommonDao.class
│                  │  │      ConnectionPool_ByHikari.class
│                  │  │      Crud.class
│                  │  │
│                  │  ├─item
│                  │  │  ├─dao
│                  │  │  │      ItemDao.class
│                  │  │  │      ItemDaoImpl.class
│                  │  │  │
│                  │  │  ├─dto
│                  │  │  │      Criteria.class
│                  │  │  │      Item.class
│                  │  │  │      PageDto.class
│                  │  │  │
│                  │  │  └─service
│                  │  │          ItemService.class
│                  │  │          ItemServiceImpl.class
│                  │  │
│                  │  └─user
│                  │      ├─dao
│                  │      │      BussinessManDao.class
│                  │      │      BussinessManDaoImpl.class
│                  │      │      SessionDao.class
│                  │      │      SessionDaoImpl.class
│                  │      │      UserDao.class
│                  │      │      UserDaoImpl.class
│                  │      │
│                  │      ├─dto
│                  │      │      BussinessMan.class
│                  │      │      Session.class
│                  │      │      User.class
│                  │      │
│                  │      └─service
│                  │              UserService.class
│                  │              UserServiceImpl.class
│                  │
│                  └─filter
│                      │  PermissionFilter.class
│                      │
│                      └─type
│                              Role.class
│
└─src
    │  .classpath
    │  .project
    │  JSP_Study.eml
    │  JSP_Study.iml
    │
    ├─.idea
    │  │  .gitignore
    │  │  misc.xml
    │  │  modules.xml
    │  │  vcs.xml
    │  │
    │  ├─artifacts
    │  │      unnamed.xml
    │  │
    │  └─libraries
    │          lib.xml
    │
    ├─main
    │  ├─java
    │  │  └─com
    │  │      └─example
    │  │          └─app
    │  │              ├─controller
    │  │              │  │  FrontController.java
    │  │              │  │  HomeController.java
    │  │              │  │  SubController.java
    │  │              │  │
    │  │              │  ├─item
    │  │              │  │      ItemAdd.java
    │  │              │  │      ItemBusinessManDelete.java
    │  │              │  │      ItemBusinessManInfo.java
    │  │              │  │      ItemBusinessManSearch.java
    │  │              │  │      ItemBusinessManUpdate.java
    │  │              │  │      ItemInfoController.java
    │  │              │  │      ItemSearchController.java
    │  │              │  │
    │  │              │  └─user
    │  │              │          UserDeleteController.java
    │  │              │          UserInfoController.java
    │  │              │          UserJoinBusinessManSignUpController.java
    │  │              │          UserJoinController.java
    │  │              │          UserJoinUserSignUpController.java
    │  │              │          UserLoginController.java
    │  │              │          UserLogoutController.java
    │  │              │          UserUpdateController.java
    │  │              │
    │  │              ├─domain
    │  │              │  ├─common
    │  │              │  │      CommonDao.java
    │  │              │  │      ConnectionPool_ByHikari.java
    │  │              │  │      Crud.java
    │  │              │  │
    │  │              │  ├─item
    │  │              │  │  ├─dao
    │  │              │  │  │      ItemDao.java
    │  │              │  │  │      ItemDaoImpl.java
    │  │              │  │  │
    │  │              │  │  ├─dto
    │  │              │  │  │      Criteria.java
    │  │              │  │  │      Item.java
    │  │              │  │  │      PageDto.java
    │  │              │  │  │
    │  │              │  │  └─service
    │  │              │  │          ItemService.java
    │  │              │  │          ItemServiceImpl.java
    │  │              │  │
    │  │              │  └─user
    │  │              │      ├─dao
    │  │              │      │      BussinessManDao.java
    │  │              │      │      BussinessManDaoImpl.java
    │  │              │      │      SessionDao.java
    │  │              │      │      SessionDaoImpl.java
    │  │              │      │      UserDao.java
    │  │              │      │      UserDaoImpl.java
    │  │              │      │
    │  │              │      ├─dto
    │  │              │      │      BussinessMan.java
    │  │              │      │      Session.java
    │  │              │      │      User.java
    │  │              │      │
    │  │              │      └─service
    │  │              │              UserService.java
    │  │              │              UserServiceImpl.java
    │  │              │
    │  │              └─filter
    │  │                  │  PermissionFilter.java
    │  │                  │
    │  │                  └─type
    │  │                          Role.java
    │  │
    │  └─webapp
    │      ├─META-INF
    │      │      context.xml
    │      │      MANIFEST.MF
    │      │
    │      ├─resources
    │      │  └─static
    │      │      ├─css
    │      │      │  │  common.css
    │      │      │  │
    │      │      │  ├─error
    │      │      │  │      error.css
    │      │      │  │
    │      │      │  ├─item
    │      │      │  │      itemInfo.css
    │      │      │  │
    │      │      │  └─user
    │      │      │          join.css
    │      │      │          login.css
    │      │      │          userInfo.css
    │      │      │          userInfoChkPW.css
    │      │      │          userJoin.css
    │      │      │          userJoinBusinessMan.css
    │      │      │
    │      │      └─jsp
    │      │              footer.jsp
    │      │              link.jsp
    │      │              nav.jsp
    │      │
    │      └─WEB-INF
    │          │  web.xml
    │          │
    │          ├─lib
    │          │      commons-logging-1.3.1.jar
    │          │      HikariCP-5.1.0.jar
    │          │      jstl-1.2.jar
    │          │      logback-classic-1.5.3.jar
    │          │      mysql-connector-j-8.3.0.jar
    │          │      slf4j-api-2.0.12.jar
    │          │      spring-security-crypto-3.2.10.RELEASE.jar
    │          │
    │          └─view
    │              │  index.jsp
    │              │  template.jsp
    │              │
    │              ├─error
    │              │      error.jsp
    │              │
    │              ├─item
    │              │      .jsp
    │              │      businessManSearch.jsp
    │              │      itemAdd.jsp
    │              │      ItemBusinessManUpdate.jsp
    │              │      itemDetail.jsp
    │              │      itemDetailShow.jsp
    │              │      itemInfo.jsp
    │              │      list.jsp
    │              │
    │              └─user
    │                      join.jsp
    │                      login.jsp
    │                      userDeleteChkPW.jsp
    │                      userInfo.jsp
    │                      userInfoChkPW.jsp
    │                      userJoin.jsp
    │                      userJoinBusinessMan.jsp
    │                      userUpdate.jsp
    │
    └─out
        └─production
            └─JSP_Study
                └─com
                    └─example
                        └─app
                            ├─controller
                            │  │  FrontController.class
                            │  │  HomeController.class
                            │  │  SubController.class
                            │  │
                            │  ├─item
                            │  │      ItemBusinessManDelete.class
                            │  │      ItemBusinessManInfo.class
                            │  │      ItemBusinessManSearch.class
                            │  │      ItemBusinessManUpdate.class
                            │  │      ItemInfoController.class
                            │  │      ItemSearchController.class
                            │  │
                            │  └─user
                            │          UserDeleteController.class
                            │          UserInfoController.class
                            │          UserJoinBusinessManSignUpController.class
                            │          UserJoinController.class
                            │          UserJoinUserSignUpController.class
                            │          UserLoginController.class
                            │          UserLogoutController.class
                            │          UserUpdateController.class
                            │
                            ├─domain
                            │  ├─common
                            │  │      CommonDao.class
                            │  │      ConnectionPool_ByHikari.class
                            │  │      Crud.class
                            │  │
                            │  ├─item
                            │  │  ├─dao
                            │  │  │      ItemDao.class
                            │  │  │      ItemDaoImpl.class
                            │  │  │
                            │  │  ├─dto
                            │  │  │      Item.class
                            │  │  │
                            │  │  └─service
                            │  │          ItemService.class
                            │  │          ItemServiceImpl.class
                            │  │
                            │  └─user
                            │      ├─dao
                            │      │      BussinessManDao.class
                            │      │      BussinessManDaoImpl.class
                            │      │      SessionDao.class
                            │      │      SessionDaoImpl.class
                            │      │      UserDao.class
                            │      │      UserDaoImpl.class
                            │      │
                            │      ├─dto
                            │      │      BussinessMan.class
                            │      │      Session.class
                            │      │      User.class
                            │      │
                            │      └─service
                            │              UserService.class
                            │              UserServiceImpl.class
                            │
                            └─filter
                                │  PermissionFilter.class
                                │
                                └─type
                                        Role.class



