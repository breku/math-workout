1. Do budowania używać wrappera gradlowego

./gradlew clean build
./gradlew :core:test

2. Java musi być ustawiona na 7. API 23 jest dla 7, powyżej teoretycznie dla 8
export JAVA_HOME=/home/brekol/Software/jdk1.7.0_79


3. :android ma zależnosć na :core i :BaseGameUtils -> tutaj są zdefiniowane play serwisy