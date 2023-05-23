
From openjdk:8-jdk-alpine

VOLUME /root/ebookshare/files
VOLUME /root/ebookshare/books
VOLUME /root/ebookshare/books/bookcoverimage
VOLUME /root/ebookshare/auditbooks
VOLUME /root/ebookshare/auditbooks/bookcoverimage


WORKDIR /app

COPY eBookShare-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]

