FROM alpine:3.12.0
RUN ["apk", "add", "openjdk11"]
ARG SOURCEDIR=./target
ARG JARFILENAME=train-of-thoughts-0.0.1-SNAPSHOT.jar
ARG TARGETDIR=/usr/app/
COPY $SOURCEDIR/$JARFILENAME $TARGETDIR
WORKDIR /usr/app
ENV JAR=$JARFILENAME
ENTRYPOINT ["sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -jar $JAR"]
