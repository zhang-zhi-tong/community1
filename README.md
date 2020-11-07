初次使用git往GitHub上上传一个简单的项目
使用如下方式
.url("https://api.github.com/user?token_access="+accessToken)
來傳遞token_access已經過時了
應該按照這種方式來傳遞token_access
.url("https://api.github.com/user")
.header("Authorization","token "+accessToken)
.build();
才嫩訪問到user信息
