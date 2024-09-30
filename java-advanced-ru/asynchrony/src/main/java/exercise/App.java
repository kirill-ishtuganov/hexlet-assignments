package exercise;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.Arrays;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.File;
import java.nio.file.StandardOpenOption;

class App {

    // BEGIN
    public static CompletableFuture<String> unionFiles(String p1, String p2, String dest) {
        CompletableFuture<String> result;
        Path path1 = Paths.get(p1).toAbsolutePath().normalize();
        Path path2 = Paths.get(p2).toAbsolutePath().normalize();
        Path destp = Paths.get(dest).toAbsolutePath().normalize();
        CompletableFuture<String> file1 = CompletableFuture.supplyAsync(() -> {
            String f1;
            try {
                f1 = Files.readString(path1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return f1;
        });

        CompletableFuture<String> file2 = CompletableFuture.supplyAsync(() -> {
            String f2;
            try {
                f2 = Files.readString(path2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return f2;
        });
        result = file1.thenCombine(file2, (f1, f2) -> {
            String write = f1 + f2;
            try {
                Files.writeString(destp, write);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return write;
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return null;
        });
        return result;
    }
    // END

    public static void main(String[] args) throws Exception {
        // BEGIN
        unionFiles("src/main/resources/file1.txt", "src/main/resources/file2.txt",
                "src/main/resources/ff.txt");
        // END
    }
}

