package org.juheinz.utility;


/**
 * Abstract class for repositories used in this application
 *
 * @param <T> the entity to be managed in the repository
 */
public abstract class AbstractRepository<T> {

    private static final String URLPREFIX = "package-company.databases.";
    private String fullURL;

    private void setFullURL(String urlSuffix) {
        this.fullURL = URLPREFIX + urlSuffix;
    }

    public void connectToDatabse(String urlSuffix, String entityName) {
        setFullURL(urlSuffix);
        String msg = "Connected to database at " + fullURL;
        Logger logger = new Logger(entityName + "repository");
        logger.log(msg, "admin");
    }
}
