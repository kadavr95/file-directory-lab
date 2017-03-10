import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by admin on 10.03.2017.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("C:\\Users\\admin\\Downloads");
        boolean isDir = Files.isDirectory(dir);
        long size = Files.size(dir);
        FileTime t = Files.getLastModifiedTime(dir);
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(dir)) {
            for (Path child : ds) {

            }
        }
        scan(dir);
    }

    static PathInfo scan(Path path) throws IOException {
        if (Files.isDirectory(path)) {
            List<PathInfo> children = new ArrayList<>();
            long sumSize = 0;
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
                for (Path child : ds) {
                    PathInfo childInfo = scan(child);
                    children.add(childInfo);
                    sumSize += childInfo.size;
                }
            }
            return new PathInfo(path, sumSize, children);
        } else {
            long size = Files.size(path);
            List<PathInfo> empty = Collections.emptyList();
            return new PathInfo(path, size, empty);
        }
    }

    static void print (PathInfo pi, int level){

    }
}
