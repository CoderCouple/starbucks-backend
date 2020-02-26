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

#Expose the port
EXPOSE 8080

#compile the source code
RUN mvn clean install

WORKDIR api

# start up command
CMD ["mvn","jetty:run"]

#Build the image
# docker build -t sunil28/starbucks-backend .

#Run the image
# docker run -it -p 8080:8080 sunil28/starbucks-backend mvn jetty:run

#Enter into the container
# docker exec -it 3274b10d06ee /bin/bash
