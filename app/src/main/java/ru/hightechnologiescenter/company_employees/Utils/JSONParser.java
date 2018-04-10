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

    private List<Employee> listArray;

    public List<Employee> getEmployees(String url, String Name, String Phone_number, String Skills) {
        try {
            listArray = new ArrayList<>();

            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            String TAG = "JsonParser";
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
                    JSONArray competencesArray = company.getJSONArray("competences");
                    for (int i = 0; i < competencesArray.length(); i++) {
                        Log.d(TAG, competencesArray.get(i).toString());
                        //companyModel.getCompetences().add(competencesArray.get(i).toString());
                    }
                    /*for (int i = 0; i < array.length(); i++) {
                        JSONObject c = array.getJSONObject(i);

                        Employee employeeItem = new Employee();

                        String name = c.getString(Name);
                        Log.d("Name is: ", name);
                        String phone_number = c.getString(Phone_number);
                        Log.d("Number is: ", phone_number);
                        String skills = c.getString(Skills);
                        Log.d("Skills: ", skills);
                        employeeItem.setName(name);
                        employeeItem.setPhone_number(phone_number);
                        employeeItem.setSkills(skills);
                        listArray.add(employeeItem);
                    }*/
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listArray;
    }
}
