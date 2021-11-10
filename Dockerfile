FROM maven:3-openjdk-11-slim as build

WORKDIR /build

COPY . .

RUN mvn -DskipTests clean install

FROM openjdk:11-jre-slim
WORKDIR /app

COPY --from=build /build/target .

ENV SERVER_PORT \
    APP_PROFILE="prod"\
    DB_HOST \
    DB_PORT \
    DB_USERNAME \
    DB_PASSWORD \
    DB_NAME

CMD [ \
  "java", \
  "-Xmx256M", \
  "-jar", \
  "unijobs.jar" \
]
