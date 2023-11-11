#!/bin/bash

curdir=`dirname $0`
pushd $curdir

# java project selection
pushd apps
echo select java project
select proj in `ls -d */ -1 | tr -d /`; do
  break;
done
popd
echo selected [$proj]
echo

# web container selection
pushd docker/base
echo select web container
select webcon in `ls -d */ -1 | tr -d /`; do
  break;
done
popd
echo selected [$webcon]
echo

# build java project
pushd apps/${proj}
mvn clean install
popd

# copy build target files
rm ./docker/build/*
cp ./apps/${proj}/target/${proj}.war ./docker/build/
cp ./docker/base/${webcon}/Dockerfile ./docker/build/Dockerfile

# recreate and run docker image
pushd ./docker/build
docker stop test-docker-proc
docker rm test-docker-proc
docker image rm test-docker
docker build -t test-docker --build-arg WAR=${proj}.war .
docker run --name test-docker-proc -p 8080:8080 -d test-docker
popd

popd
