# You will need a Oracle driver to run this example
mvn install:install-file -Dfile=./ojdbc14.jar -DgroupId=com.oracle
    -DartifactId=ojdbc14  -Dversion=10.2.0.4 -Dpackaging=jar
