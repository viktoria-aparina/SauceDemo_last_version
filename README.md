TASK #1
PS C:\Users\User\IdeaProjects\TMS\testershmester> mvn versions:display-dependency-updates

[INFO] The following dependencies in Dependencies have newer versions:
[INFO]   io.github.bonigarcia:webdrivermanager ................. 5.2.0 -> 5.2.1
[INFO]   org.apache.commons:commons-collections4 ................... 4.1 -> 4.4
[INFO]   org.seleniumhq.selenium:selenium-java ................. 4.2.2 -> 4.3.0
[INFO]   org.testng:testng ..................................... 7.6.0 -> 7.6.1
[INFO]
[INFO] artifact org.aspectj:aspectjweaver: checking for updates from central
[INFO] The following dependencies in Plugin Dependencies have newer versions:
[INFO]   org.aspectj:aspectjweaver ........................... 1.9.9 -> 1.9.9.1
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  3.785 s
[INFO] Finished at: 2022-07-17T14:21:59+03:00
[INFO] ------------------------------------------------------------------------

TASK #2
PS C:\Users\User\IdeaProjects\TMS\testershmester> mvn clean test

[INFO]
[ERROR] Tests run: 22, Failures: 11, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------      
[INFO] Total time:  02:25 min
[INFO] Finished at: 2022-07-17T14:25:36+03:00
[INFO] ------------------------------------------------------------------------      
[ERROR] Failed to execute goal org.apache.maven.plugins:maven-surefire-plugin:3.0.0-M
7:test (default-test) on project SauceDemo2.0: There are test failures.

TASK #3
PS C:\Users\User\IdeaProjects\TMS\testershmester> mvn -Dtest=LogoutTest#logoutInBurge
rMenuShouldLogoutUser test

[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 13.897 s - in
org.example.LogoutTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------      
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------      
[INFO] Total time:  24.804 s
[INFO] Finished at: 2022-07-17T14:32:22+03:00
[INFO] ------------------------------------------------------------------------      

TASK #4
PS C:\Users\User\IdeaProjects\TMS\testershmester> mvn clean test -Dtest=LoginTest#use
rShouldLoginWithValidCredentials -DpasswordProp=secret_sauce

secret_sauce
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 18.274 s - in org.example.LoginTest
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  28.727 s
[INFO] Finished at: 2022-07-17T14:44:05+03:00
[INFO] ------------------------------------------------------------------------
