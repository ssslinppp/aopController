# Springboot对Controller层方法进行统一异常处理
## 概述
使用AOP技术，封装Controller请求中的异常处理；

## 规范
1. 函数返回值，统一为 ResultEntity
2. 只对Controller层进行AOP拦截处理；
