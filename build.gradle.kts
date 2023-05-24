plugins {
    id("application")
    id("org.openjfx.javafxplugin") version "0.0.13"
}

group = "dev.wson.start"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.3")
}

application {
    mainClass.set("dev.wson.start.ToDoStart")
}

javafx {
    version = "20"
    modules = mutableListOf("javafx.controls", "javafx.fxml")
}