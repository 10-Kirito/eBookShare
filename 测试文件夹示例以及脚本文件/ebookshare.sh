#!/bin/bash

docker stop ebookshare

docker container rm ebookshare

docker rmi ebookshare

docker build -t ebookshare .

docker run --name ebookshare -d -p 9091:9091 \
 -v /root/ebookshare/files:/root/ebookshare/files  \
 -v /root/ebookshare/books:/root/ebookshare/books \
 -v /root/ebookshare/books/bookcoverimage:/root/ebookshare/books/bookcoverimage \
 -v /root/ebookshare/auditbooks:/root/ebookshare/auditbooks \
 -v /root/ebookshare/auditbooks/bookcoverimage:/root/ebookshare/auditbooks/bookcoverimage  \
 ebookshare
