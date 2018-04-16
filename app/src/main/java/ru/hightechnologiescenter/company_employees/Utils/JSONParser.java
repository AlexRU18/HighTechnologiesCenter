package ru.hightechnologiescenter.company_employees.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ru.hightechnologiescenter.company_employees.Model.Company;
import ru.hightechnologiescenter.company_employees.Model.Employee;

public class JSONParser {

    private ArrayList competencesArray = new ArrayList<>();
    private ArrayList employeesArray = new ArrayList<>();
    private ArrayList skillsArray = new ArrayList<>();

    public List<Company> getData(String url) {
        try {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            String TAG = "JSONParser";
            if (url != null) {
                try {
                    JSONObject reader = new JSONObject(jsonStr);
                    JSONObject company = reader.getJSONObject("company");
                    Company companyModel = new Company();
                    String compamyName = company.getString("name");
                    Log.d("Name is: ", compamyName);
                    companyModel.setName(compamyName);
                    String compamyAge = company.getString("age");
                    Log.d("Age is: ", compamyAge);
                    companyModel.setAge(compamyAge);
                    JSONArray competencesJSONArray = company.getJSONArray("competences");
                    for (int i = 0; i < competencesJSONArray.length(); i++) {
                        competencesArray.add(competencesJSONArray.get(i).toString());
                        Log.d(TAG, competencesJSONArray.get(i).toString());
                    }
                    companyModel.setCompetences(competencesArray);
                    JSONArray employeesJSONArray = company.getJSONArray("employees");

                    //Проходим по списку сотрудников
                    for (int i = 0; i < employeesJSONArray.length(); i++) {
                        Employee employeeModel = new Employee();

                        //Объект сотрудника
                        JSONObject employeeSingleJSONObject = employeesJSONArray.getJSONObject(i);

                        String employeeName = employeeSingleJSONObject.getString("name");
                        Log.d("Name is: ", employeeName);
                        employeeModel.setName(employeeName);
                        String employeeNumber = employeeSingleJSONObject.getString("phone_number");
                        Log.d("Number is: ", employeeNumber);
                        employeeModel.setPhone_number(employeeNumber);

                        JSONArray skillsSingleJSONArray = employeeSingleJSONObject.getJSONArray("skills");
                        for (int j = 0; j < skillsSingleJSONArray.length(); j++) {
                            skillsArray.add(skillsSingleJSONArray.get(j).toString());
                        }
                        Log.d(TAG, "Skills is: " + skillsSingleJSONArray.toString());
                        employeeModel.setSkills(skillsArray);
                        employeesArray.add(employeeModel);
                        //companyModel.setEmployee(employeeModel);
                    }
                    companyModel.setEmployee(employeesArray);
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
