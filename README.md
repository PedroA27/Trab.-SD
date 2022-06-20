# Trab. SD
EP de Sistemas distribuídos

para fazer build (dentro da pasta muitas_parts, com o maven 3.8.6):
mvn package

para rodar o server (dentro da pasta muitas_parts, jdk 8 apenas, maven 3.8.6):

java -cp target/muitas_parts-1.0.jar sys.admin.server.ServerRunner <PORT> <HOST(coloque localhost)> <NOME>

para rodar o client (dentro da pasta muitas_parts, jdk 8 apenas, maven 3.8.6):

java -cp target/muitas_parts-1.0.jar sys.admin.client.ClientMain
