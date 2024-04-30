package exercise;

import lombok.SneakyThrows;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

// BEGIN
class App {
    public static void save(Path path, Car car) throws IOException {

        var result = car.serialize();
        var writer = new FileWriter(path.toFile(), true);
        writer.write(result);
        writer.close();
    }

    public static Car extract(Path path) throws IOException {

        return Car.unserialize(Files.readString(path));
    }
}
// END
