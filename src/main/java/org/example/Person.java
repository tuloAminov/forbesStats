package org.example;

public class Person {

    private String fullName;
    private int rank;
    private double netWorth;
    private int age;
    private String country;
    private String source;
    private String industry;

    public Person(String[] data) {
        this.rank = Integer.parseInt(data[0]);
        this.fullName = data[1];
        this.netWorth = Double.parseDouble(data[2]);
        this.age = Integer.parseInt(data[3]);
        this.country = data[4];
        this.source = data[5];
        this.industry = data[6];
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getNetWorth() {
        return netWorth;
    }

    public void setNetWorth(double netWorth) {
        this.netWorth = netWorth;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", rank=" + rank +
                ", netWorth=" + netWorth +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", source='" + source + '\'' +
                ", industry='" + industry + '\'' +
                '}';
    }
}
