Dùng Docker pull rabbitmq image về, sau đó run container (nhớ mapping port 5762:5762)
Sau đó thực hiện các câu lệnh dưới đây:
```zhs
# Build 2 file Recv.java và Send.java thành 2 file .class vào thư mục target/classes/hovanvy/workqueue trước
# Để dễ quan sát, hãy chia màn hình thành 4 phần, mỗi phần mở một terminal lên
# Chạy câu lệnh sau 3 lần ở 3 terminal khác nhau để tạo 3 woker(consumer)
/usr/lib/jvm/java-11-openjdk-amd64/bin/java -classpath /home/hovanvydut/Documents/coding/rabbitmq/official-rabbitmq.com\ tutorial/rabbitmq-tutorial/target/classes:/home/hovanvydut/.m2/repository/com/rabbitmq/amqp-client/5.7.1/amqp-client-5.7.1.jar:/home/hovanvydut/.m2/repository/org/slf4j/slf4j-api/1.7.26/slf4j-api-1.7.26.jar:/home/hovanvydut/.m2/repository/org/slf4j/slf4j-simple/1.7.26/slf4j-simple-1.7.26.jar hovanvy.workqueue.Recv

# Chạy câu lệnh sau để tạo Publisher
/usr/lib/jvm/java-11-openjdk-amd64/bin/java -classpath /home/hovanvydut/Documents/coding/rabbitmq/official-rabbitmq.com\ tutorial/rabbitmq-tutorial/target/classes:/home/hovanvydut/.m2/repository/com/rabbitmq/amqp-client/5.7.1/amqp-client-5.7.1.jar:/home/hovanvydut/.m2/repository/org/slf4j/slf4j-api/1.7.26/slf4j-api-1.7.26.jar:/home/hovanvydut/.m2/repository/org/slf4j/slf4j-simple/1.7.26/slf4j-simple-1.7.26.jar hovanvy.workqueue.Send 
```