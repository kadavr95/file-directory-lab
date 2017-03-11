import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static jdk.nashorn.internal.objects.NativeError.getFileName;

/**
 * Created by admin on 10.03.2017.
 */
public class Main {
    private static int rootLevel;

    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("C:\\Users\\kadav\\Downloads");
        rootLevel = dir.getNameCount();
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
            long sumSize = folderSize(path);
            print(new PathInfo(path, sumSize, children), path.getNameCount() - rootLevel);
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
                for (Path child : ds) {
                    PathInfo childInfo = scan(child);
                    children.add(childInfo);
                }
            }

            return new PathInfo(path, sumSize, children);
        } else {
            long size = Files.size(path);
            List<PathInfo> empty = Collections.emptyList();
            print(new PathInfo(path, size, empty), path.getNameCount() - rootLevel);
            return new PathInfo(path, size, empty);
        }
    }

    static long folderSize(Path path) throws IOException {
        long sumSize = 0;
        if (Files.isDirectory(path)) {
            try (DirectoryStream<Path> ds = Files.newDirectoryStream(path)) {
                for (Path child : ds) {
                    sumSize += folderSize(child);
                }
            }
        } else {
            sumSize += Files.size(path);
        }
        return sumSize;
    }

    static void print(PathInfo pi, int level) {
        String treeFileInfo = "";
        if (level == 0)
            treeFileInfo += "\u251c\u2500";
        else {
            for (int i = 0; i < level; i++)
                treeFileInfo += "\u2502 ";
            treeFileInfo += "\u251c\u2500";
        }
        if (pi.path.getFileName().toString().length() > 30) {
            treeFileInfo = treeFileInfo + pi.path.getFileName().toString().substring(0, 28) + ".. ";
        } else {
            treeFileInfo = treeFileInfo + pi.path.getFileName().toString();
            for (int i = (30 - pi.path.getFileName().toString().length()); i >= 0; i--) {
                treeFileInfo = treeFileInfo + " ";
            }
        }

        if (pi.size > 536870912)
            treeFileInfo = treeFileInfo + String.format("%.1f", (float) pi.size / 1073741824) + "GB";
        else if (pi.size > 524288)
            treeFileInfo = treeFileInfo + String.format("%.1f", (float) pi.size / 1048576) + "MB";
        else if (pi.size > 512)
            treeFileInfo = treeFileInfo + String.format("%.1f", (float) pi.size / 1024) + "kB";
        else
            treeFileInfo = treeFileInfo + pi.size + "bytes";
        System.out.println(treeFileInfo);
    }
}
