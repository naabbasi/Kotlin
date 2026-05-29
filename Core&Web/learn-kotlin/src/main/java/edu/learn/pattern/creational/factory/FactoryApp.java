package edu.learn.pattern.creational.factory;

public class FactoryApp {
    public static void main(String[] args) {
        DBFactory dbFactory = null;
        String param = "oracle";
        if(param.equals("mysql")){
            dbFactory = new MySQLDB();
        } else if(param.equals("oracle")){
            dbFactory = new OracleDB();
        }

        System.out.println(dbFactory.getIntance());
    }
}
