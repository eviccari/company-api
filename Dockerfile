FROM openjdk:13-alpine as stage

COPY . .

RUN ./mvnw test && \
    ./mvnw clean package

FROM andriiryzhkov/jre13 as runner 

COPY --from=stage /target/company-api.jar ./company-api.jar
COPY --from=stage /target/classes/application.yml ./application.yml

ENTRYPOINT [ "java",  "-jar", "company-api.jar" ]







