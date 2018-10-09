Install Apache Tomcat 8 or later and Maven
Clone repository git clone https://github.com/lyalyalya/Servlet.git
Build package and start Tomcat
Open http://localhost:8080/simplewebapp/main in your browser

Page shows the name of pressed buttons, count of clicks and list of inner states.

Additionally:
Button GET shows last state in the list of inner states;
Button PUT adds state "PUT" to the list of inner states (remember, it doesn't add state if the last state of list is "PUT");
Button POST adds state "POST" to the list of inner states;
Button DELETE deletes last state from the list of inner states;

