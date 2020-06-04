# CC_Backend_Scenario

*Step-1:* Go to Maven website (https://maven.apache.org/download.cgi) and download following Maven plugin
```

apache-maven-3.6.3-bin.zip

```

*Step-2:* Setup Maven path to Environment variable of system using below URL
```

https://www.baeldung.com/install-maven-on-windows-linux-mac

```

*Step-3:* Verify maven is configured correctly by executing following command to see the Maven version
```

mvn --version

```

*Step-4:* Download the code Repository using following git command
```

git clone 'https://github.com/GulRose-Iftikhar/CC_Backend_Scenario.git'

```

*Step-5:* Open the downloaded code and go to `backend_scenario` folder where `pom.xml` file is present

*Step-6:* Execute following command
```

mvn clean test

```

*Step-7:* Go to `backend_scenario/reports` folder and open the *.html* report
