# Project Introduction 工程简介

An example integration scenario that used the starter for using Redis key-value data store with Spring Data Redis and the Lettuce client.

What involved:
- Store key-value data by Redis
- Connect to Redis remotely
- Use case of `StringRedisTemplate`
- Serialize and Deserialize between JSON and Java Object (with `com.fastxml.jackson.core:jackson-datatype-jsr310`)(Handled a bunch of version problems!)

# Extended Reading 延伸阅读

Spring-Data-Redis Documentation:

https://docs.spring.io/spring-data/data-redis/docs/current/reference/html/#redis:connectors:connection