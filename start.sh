#!/bin/bash
set -e

echo "Frontend  Build..."
cd frontend
npm ci
npm run build -- --configuration=production

echo "Copy to Spring Boot static resources..."
rm -rf ../backend/src/main/resources/static
mkdir -p ../backend/src/main/resources/static
cp -r dist/todo/browser/. ../backend/src/main/resources/static/

echo "Maven Build..."
cd ../backend
chmod +x mvnw
./mvnw clean package -DskipTests

echo "Start application..."
java -jar target/*.jar 
