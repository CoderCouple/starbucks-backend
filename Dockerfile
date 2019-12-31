# This file demonstrate how perform a mutistage build

############################# Stage 1 - Build ######################

# fetch basic image
FROM maven:3-jdk-8 as build

#Arguments
ARG build_dir=app/starbucks-backend
RUN mkdir -p ${build_dir}

# application placed into app/starbucks-backend
WORKDIR ${build_dir}

# copy the source code
# install dependencies
COPY ./ ./

#compile the source code
RUN mvn clean install
############################# Stage 2 - Deployment ######################

FROM tomcat:8.5.16-jre8-alpine

EXPOSE 8080

RUN rm -rf /usr/local/tomcat/webapps/*

COPY --from=build app/starbucks-backend/api/target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

CMD ["catalina.sh","run"]

#Build the image
# docker build -t starbucks .

#Run the image
# docker run -it -p 8080:8080 starbucks

#Enter into the container
# docker exec -it 3274b10d06ee /bin/bash
