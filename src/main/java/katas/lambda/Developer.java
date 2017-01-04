package katas.lambda;

import java.math.BigDecimal;

public class Developer {
    String name;
    BigDecimal salary;
    int age;
    
    public Developer(String name, BigDecimal salary, int age) {
        this.name = name;
        this.salary = salary;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public BigDecimal getSalary() {
        return this.salary;
    }
}
