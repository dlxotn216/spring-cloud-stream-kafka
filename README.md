#Spring cloud stream kafka를 통한 메일 발송 서비스

git clone https://github.com/wurstmeister/kafka-docker

docker-compose.yml 파일에 호스트 정보에 IP 기재

docker-compose up -d


vm의 터미널 접속
docker exec -it kafka-docker_kafka_1 bash


프로듀셔의 ACK란?
https://www.popit.kr/kafka-%EC%9A%B4%EC%98%81%EC%9E%90%EA%B0%80-%EB%A7%90%ED%95%98%EB%8A%94-producer-acks/

컨슈머 주요 설정
https://coding-start.tistory.com/137

Spring retry template
https://docs.spring.io/spring-cloud-stream/docs/current/reference/html/_programming_model.html#_retry_template

생각보다 Message broker를 이용할 때 아키텍처 적으로 고안해야 할 포인트가 많음  
메일 발송 로그를 보려면 어짜피 DB에 저장해야 할 텐데?  
차라리 Spring batch 기반으로 작업을 돌리는것이 낫지 않을까