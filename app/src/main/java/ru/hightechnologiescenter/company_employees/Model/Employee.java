package ru.hightechnologiescenter.company_employees.Model;

import java.util.Comparator;
import java.util.List;

public class Employee {
    private String name;
    private String phone_number;
    private List<String> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public static Comparator<Employee> COMPARE_BY_NAME = new Comparator<Employee>() {
        public int compare(Employee one, Employee other) {
            return one.name.compareTo(other.name);
        }
    };

}
