package ru.hightechnologiescenter.company_employees.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ru.hightechnologiescenter.company_employees.Model.Company;
import ru.hightechnologiescenter.company_employees.Model.Employee;

public class JSONParser {

    private ArrayList competencesArray = new ArrayList<>();
    private ArrayList employeesArray = new ArrayList<>();
    private Company companyModel = new Company();

    private static final String TAG = JSONParser.class.getSimpleName();

    public Company getData(String url) {
        try {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);

            if (url != null) {
                try {

                    JSONObject reader = new JSONObject(jsonStr);
                    JSONObject company = reader.getJSONObject("company");

                    String companyName = company.getString("name");
                    companyModel.setName(companyName);
                    String companyAge = company.getString("age");
                    companyModel.setAge(companyAge);

                    JSONArray competencesJSONArray = company.getJSONArray("competences");
                    for (int i = 0; i < competencesJSONArray.length(); i++) {
                        competencesArray.add(competencesJSONArray.get(i).toString());
                    }
                    companyModel.setCompetences(competencesArray);

                    JSONArray employeesJSONArray = company.getJSONArray("employees");
                    //Проходим по списку сотрудников
                    for (int i = 0; i < employeesJSONArray.length(); i++) {
                        Employee employeeModel = new Employee();
                        ArrayList skillsArray = new ArrayList<>();

                        //Объект сотрудника
                        JSONObject employeeSingleJSONObject = employeesJSONArray.getJSONObject(i);

                        String employeeName = employeeSingleJSONObject.getString("name");
                        employeeModel.setName(employeeName);
                        String employeeNumber = employeeSingleJSONObject.getString("phone_number");
                        employeeModel.setPhone_number(employeeNumber);

                        JSONArray skillsSingleJSONArray = employeeSingleJSONObject.getJSONArray("skills");
                        for (int j = 0; j < skillsSingleJSONArray.length(); j++) {
                            skillsArray.add(skillsSingleJSONArray.get(j).toString());
                        }
                        employeeModel.setSkills(skillsArray);
                        employeesArray.add(employeeModel);
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
        return companyModel;
    }
}