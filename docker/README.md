# vSphere Automation SDK for Java Dockerfile

This contains a dockerfile to deploy the vsphere app that interract to deployed vcenter infra

## Building the container

Install a docker client for your chosen OS and run the following command to build the container directly from this repository:

```console
docker build https://github.com/pascalito007/vcenter-app.git#:docker -t vmware/vsphere
```

## Run the Container

Once the Container has been successfully built, you can run it using the following command:

```console
$ docker run --rm -it vmware/vsphere
```

At this point you are now logged into the container that you have just built and you can connect using [localhost:8080/login](http://localhost:8080/login)

