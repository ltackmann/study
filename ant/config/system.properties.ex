# Container setup (must not have any trailing spaces)
container.type=glassfish

# JBoss setup
jboss.home=/opt/jboss
# TODO

# Geronimo setup
geronimo.home=/home/lt/software/geronimo
geronimo.url=http://127.0.0.1:8080/
geronimo.bin=${geronimo.home}/bin
geronimo.lib=${geronimo.home}/lib
geronimo.user=system
geronimo.pwd=manager
geronimo.start=startup.sh
geronimo.stop=shutdown.sh
geronimo.deploy=deploy.sh


# Glassfish setup
glassfish.home=/opt/glassfish
glassfish.user=admin
glassfish.passwordfile=/opt/CoffeeBean/src/vendor/ant-pearls/config/glassfish.pwd
asadmin=${glassfish.home}/bin/asadmin
