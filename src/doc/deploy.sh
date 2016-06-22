#!/bin/sh
pro_home=/opt/script/taovo/dev/lottery
war_name=lottery
remote_dir=/opt/deploy/lottery/webapps
remote_ip=10.46.178.103
remote_user=root
svn update svn $pro_home
mvn -f=$pro_home clean package -Pqhd,update-db liquibase:update
password=fengyun@521
expect -c "
spawn scp $pro_home/target/$war_name.war  $remote_user@$remote_ip:$remote_dir
set timeout 300
expect {
      \"$remote_user@$remote_ip's password:\" {send \"$password\r\";exp_continue}
        \"remote_user@\" {send \"df -h\r exit\r\"; exp_continue}
}
"