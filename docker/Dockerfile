FROM maven:latest
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
WORKDIR /work/vsphere-tp/
RUN mvn initialize; mvn clean install
CMD ["/bin/bash"]
