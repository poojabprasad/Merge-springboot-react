A simple spring boot app to handle the contact management using the ReactJs front end

Requirements

java 8
Intellij/Eclipse
npm/node installed
MongoDb
Maven


Steps to run the project


1. Download the repository from the git
2. Import the porject into intellij/eclipse as maven project
3. Once the import is complete cd to frontend folder. If it doesnt exist then create it using "create-react-app frontend"
4. Install the necessary packages using the command

    npm install --save react-router-dom
    
    npm install --save-dev bootstrap
    
    npm install --save axios

5. once all the packages are installed run the below command in frontend directory

	npm run build
6. This will build the react project and copies all the necessary files to the java src/main/resources/static folder
7. Now build the java project
8. cd to main project root directory and the below command

	mvn clean install
9. Now run the below command to start the project

	mvn spring-boot:run
10. Navigate to http://localhost:8080 in your browser
11. Alternatively you start the project using below command

	mvn clean install
	
	java -jar target/\<project-name\>.jar


More information can be found in
https://www.djamware.com/post/5ab6397c80aca714d19d5b9c/building-spring-boot-mongodb-and-reactjs-crud-web-application
