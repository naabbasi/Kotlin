package edu.learn.pattern.creational.factory;

public class MySQLDB extends DBFactory {
    @Override
    public DBFactory getIntance() {
        return new MySQLDB();
    }
}
