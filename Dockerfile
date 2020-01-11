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

FROM jetty:9.4.18-jre8-alpine

USER root

RUN mkdir -p /home/jetty && chown -R jetty:jetty /home/jetty

RUN chown -R jetty:jetty /var/lib/jetty/webapps

USER jetty

RUN rm -rf $JETTY_BASE/webapps/*

COPY --chown=jetty:jetty --from=build app/starbucks-backend/api/target/ROOT.war $JETTY_BASE/webapps/ROOT.war

COPY --chown=jetty:jetty --from=build app/starbucks-backend/api/target/ROOT $JETTY_BASE/webapps/ROOT

#Build the image
# docker build -t sunil28/starbucks-backend .

#Run the image
# docker run -it -p 8080:8080 sunil28/starbucks-backend

#Enter into the container
# docker exec -it 3274b10d06ee /bin/bash
