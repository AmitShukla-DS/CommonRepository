#!/usr/bin/env bash

DEPLOY_ENV=$1
DOCKER_IMAGE_NAME=udal
DOCKER_REPO=652926606685.dkr.ecr.us-east-1.amazonaws.com/$DOCKER_IMAGE_NAME
DATE_TIME=`date +%y%m%d%H%M`
BUILD_NUMBER=`git log --format=format:"%h" -n 1`
DOCKER_IMAGE_TAG=$DATE_TIME.$BUILD_NUMBER-$DEPLOY_ENV

echo "DOCKER_IMAGE_TAG: " $DOCKER_IMAGE_TAG

echo "Building Java source"
mvn clean package spring-boot:repackage

echo "Building Images..."
docker build -t $DOCKER_REPO:$DOCKER_IMAGE_TAG .
docker tag $DOCKER_REPO:$DOCKER_IMAGE_TAG $DOCKER_REPO:latest-$DEPLOY_ENV


echo " Pushing images to registry ..."
docker push $DOCKER_REPO:$DOCKER_IMAGE_TAG
docker push $DOCKER_REPO:latest-$DEPLOY_ENV

if [ $DEPLOY_ENV == 'RELEASE' ]
then
    echo "Pushing image for latest tag for production"
    docker tag $DOCKER_REPO:$DOCKER_IMAGE_TAG $DOCKER_REPO:latest
    docker push $DOCKER_REPO:latest
fi
