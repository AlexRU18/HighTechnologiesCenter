package ru.hightechnologiescenter.company_employees.Model;

import java.util.List;

public class Company {
    private String name;
    private String age;
    private List<String> competences;
    private List<Employee> employees;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<String> getCompetences() {
        return competences;
    }

    public void setCompetences(List<String> competences) {
        this.competences = competences;
    }

    public void addEmployee(Employee e) {
        employees.add(e);
    }

    public Employee getEmpl(int position) {
        return employees.get(position);
    }

    public List<Employee> getEmplList() {
        return employees;
    }
}