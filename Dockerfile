FROM openjdk:11 as build

WORKDIR /build

COPY . .

RUN mvn -DskipTests clean install

FROM build as deploy
WORKDIR /app

COPY --from=build /build/target .

CMD [ \
  "java" \
  "-Xmx256M" \
  "-jar" \
  "unijobs.jar" \
]
