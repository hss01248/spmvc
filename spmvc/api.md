# spmvc



# json

```
request:
/test/getCommonJson.json
GET
int id

response:
{"id":null,"name":"英吉利海峡","price":6897.0,"pic":null,"createtime":null,"detail":null}
```



```
request:
/test/getStandardJson.json
GET
int id

response:
{"code":0,"msg":"请求成功","data":{"id":null,"name":"英吉利海峡","price":6897.0,"pic":null,"createtime":null,"detail":null}}
```



```
/test/postCommonJson.json
POST
int id
```



```
/test/postStandardJson.json
POST
int id
```



```
request:
/test/upload.json
POST
int id,MultipartFile picFile,MultipartFile picFile2

response:
{"code":0,"msg":"请求成功","data":{"id":null,"name":"英吉利海峡","price":6897.0,"pic":null,"createtime":null,"detail":null}}
```


```
/login.json
GET
String usename,String password,
```