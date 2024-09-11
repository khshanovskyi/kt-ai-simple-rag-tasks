package khshanovskyi.ktaiinterviewassistantrag.utils;

import lombok.experimental.UtilityClass;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;

@UtilityClass
public class Utils {

    public static Path toPath(String relativePath) throws URISyntaxException {
        URL fileUrl = Utils.class.getClassLoader().getResource(relativePath);
        assert fileUrl != null;
        return Paths.get(fileUrl.toURI());
    }

    public static PathMatcher glob(String glob) {
        return FileSystems.getDefault().getPathMatcher("glob:" + glob);
    }

}
