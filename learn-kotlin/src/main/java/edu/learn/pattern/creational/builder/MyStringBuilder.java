package edu.learn.pattern.creational.builder;

public class MyStringBuilder {
    private String stringText;
    private Integer number;

    static class Builder{
        private String stringText;
        private Integer number;

        public Builder append(String value){
            if(this.stringText != null){
                this.stringText += value;
            } else {
                this.stringText = value;
            }

            return this;
        }

        public Builder append(Object obj) {
            return append(String.valueOf(obj));
        }

        public Builder append(Integer value) {
            return append(String.valueOf(value));
        }

        public MyStringBuilder build(){
            return new MyStringBuilder(this);
        }
    }

    private MyStringBuilder(Builder builder){
        this.stringText = builder.stringText;
    }

    @Override
    public String toString(){
        return this.stringText;
    }

    public static void main(String[] args) {
        Object obj = new String("Ali");
        MyStringBuilder builder = new MyStringBuilder.Builder().append("Noman").append(obj).append("Abbasi").append(1).build();
        StringBuilder builder1;
        System.out.println(builder);
    }
}
