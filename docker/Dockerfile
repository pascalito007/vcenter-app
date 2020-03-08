FROM maven:3.6.3-jdk-8
MAINTAINER pasciano007@gmail.com

# Set the working directory to /work
WORKDIR /work

# Update and install packages
RUN apt-get update
RUN apt-get -y install vim \
    apt-utils \
    git

# Clone the project
RUN git clone  https://github.com/pascalito007/vcenter-app.git

# Build the project
WORKDIR /work/vcenter-app/
RUN mvn initialize; mvn clean install
#CMD ["/bin/bash"]
ENTRYPOINT ["java", "-jar", "./target/adconnect-0.0.1-SNAPSHOT.jar"]
