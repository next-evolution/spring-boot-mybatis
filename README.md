# spring-boot
spring-boot sample mybatis

# setup
```
mkdir -pv ./docker-mysql/data

docker-compose up -d

docker exec -it docker-mysql bash

cd /tmp/tools
sh 00_ddl_database.sh
sh 10_ddl_schema1.sh

mysql -uapp_user -papp_user_password schema1

mysql> desc account;
+----------------+------------------+------+-----+-------------------+-----------------------------+
| Field          | Type             | Null | Key | Default           | Extra                       |
+----------------+------------------+------+-----+-------------------+-----------------------------+
| account_number | int(10) unsigned | NO   | PRI | NULL              | auto_increment              |
| email_address  | varchar(128)     | NO   |     | NULL              |                             |
| nick_name      | varchar(64)      | NO   |     | NULL              |                             |
| delete_flag    | bit(1)           | NO   |     | b'0'              |                             |
| created_at     | datetime         | NO   |     | CURRENT_TIMESTAMP |                             |
| created_by     | varchar(8)       | NO   |     | NULL              |                             |
| updated_at     | datetime         | NO   |     | CURRENT_TIMESTAMP | on update CURRENT_TIMESTAMP |
| updated_by     | varchar(8)       | NO   |     | NULL              |                             |
+----------------+------------------+------+-----+-------------------+-----------------------------+
8 rows in set (0.00 sec)

exit
exit
```

# build for Dockerfile
```
docker build ./ -t spring-boot-api

docker run --name spring-boot-api -p 20080:8080 -t spring-boot-api

http://localhost:20080/api
```

# application start
```
./gradlew bootRun

http://localhost:20080/api
```
