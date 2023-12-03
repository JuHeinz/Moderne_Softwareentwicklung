package org.juheinz.utility;


public abstract class AbstractRepository<T>{

    private static final String URLPREFIX = "package-company.databases.";

    public void connectToDatabse(String urlSuffix, String entityName) {
        System.out.println("Connected " + entityName + " repository to database at " + URLPREFIX + urlSuffix);

    }
}
