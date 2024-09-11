package khshanovskyi.ktaiinterviewassistantrag.utils;

import khshanovskyi.ktaiinterviewassistantrag.setting.DbSettings;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.postgresql.ds.PGSimpleDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * <b>You don't need to change here anything, this utility class performs preconfiguration of DB to be able to work with Vectors</b>
 */
@UtilityClass
@Slf4j
public class DbUtils {

    /**
     * Set up Vector DB extension and drop table with embeddings.
     *
     * @param dbSettings class with initialized DB settings (username, password, url, etc...)
     */
    public static void initDB(DbSettings dbSettings, String dropTableName) {
        log.info("Initializing DB...");
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setURL(dbSettings.getUrl());
        dataSource.setUser(dbSettings.getUsername());
        dataSource.setPassword(dbSettings.getPassword());

        {
            String script = read("init/setup_vector_db.sql");
            execute(script, dataSource);
            log.info("Vector extensions successfully initialized.");
            execute(String.format("DROP TABLE IF EXISTS %s;", dropTableName), dataSource);
            log.info(String.format("Executing drop '%s' table", dropTableName));
        }

        log.info("DB successfully initialized");
    }

    private static String read(String path) {
        try {
            return new String(Files.readAllBytes(Utils.toPath(path)));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private static void execute(String sql, DataSource dataSource) {
        try (Connection connection = dataSource.getConnection(); Statement statement = connection.createStatement()) {
            for (String sqlStatement : sql.split(";")) {
                statement.execute(sqlStatement.trim());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
