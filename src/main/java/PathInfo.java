import java.nio.file.Path;
import java.util.List;

/**
 * Created by admin on 10.03.2017.
 */
public class PathInfo {
    Path path;
    long size;
    List<PathInfo> children;

    public PathInfo(Path path) {
        this.path = path;
    }

    public PathInfo(Path path, long size, List<PathInfo> children) {
        this.path = path;
        this.size = size;
        this.children = children;
    }

}
