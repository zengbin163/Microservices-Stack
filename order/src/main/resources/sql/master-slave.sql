CREATE USER 'repl' @'%' IDENTIFIED BY '123456';
GRANT REPLICATION SLAVE ON *.* TO 'repl' @'%';
FLUSH PRIVILEGES;

show master status;








stop slave;

CHANGE MASTER TO
         MASTER_HOST='127.0.0.1',
         MASTER_USER='root',
         MASTER_PASSWORD='root',
         MASTER_LOG_FILE='master-bin.000001',
         MASTER_LOG_POS=154;

start slave;

##SET GLOBAL sql_slave_skip_counter =1; 

show slave status;