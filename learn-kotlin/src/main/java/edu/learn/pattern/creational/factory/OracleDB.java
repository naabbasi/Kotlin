package edu.learn.pattern.creational.factory;

public class OracleDB extends DBFactory {
    @Override
    public DBFactory getIntance() {
        return new OracleDB();
    }
}
