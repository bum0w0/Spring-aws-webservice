# 빌드 스테이지
FROM gradle:7.5.1-jdk17 as builder
WORKDIR /build

# Gradle 캐시를 위한 파일 복사
COPY build.gradle settings.gradle /build/
RUN gradle dependencies || true

# 소스 코드 복사 및 빌드
COPY . /build
RUN gradle build -x test --parallel

# 실행 스테이지
FROM openjdk:17-jdk-slim
WORKDIR /app

# 빌더 이미지에서 JAR 파일 복사
COPY --from=builder /build/build/libs/spring-aws-0.0.1-SNAPSHOT.jar spring-app.jar

# oauth 설정 파일 복사
COPY application-oauth.properties /config/application-oauth.properties

EXPOSE 8080

# 컨테이너를 non-root 사용자로 실행
USER nobody:nogroup

# 애플리케이션 실행
ENTRYPOINT [ \
   "java", \
   "-jar", \
   "-Dspring.config.location=classpath:/application.properties,/config/application-oauth.properties", \
   "-Djava.security.egd=file:/dev/./urandom", \
   "-Dsun.net.inetaddr.ttl=0", \
   "spring-app.jar" \
]