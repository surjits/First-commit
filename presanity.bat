set projectLocation=D:\SplashBI\Splash-autotest\SplashBi
set classpath=%projectLocation%\target\SplashBi-0.0.1-SNAPSHOT.jar;%projectLocation%\lib\*%classpath%
java -Dlog4j.debug=true -Dlog4j.configuration=file:"%projectLocation%\log4j.properties" org.testng.TestNG %projectLocation%\testng.xml
pause