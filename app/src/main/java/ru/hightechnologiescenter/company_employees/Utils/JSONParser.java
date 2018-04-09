package ru.hightechnologiescenter.company_employees.Utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

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
                    JSONArray array = new JSONArray(jsonStr);

                    JSONArray jsonArray = array.getJSONArray(0);
                    jsonArray.toString();
                    for (int i = 0; i < jsonArray.length(); i++) {
                        jsonArray.getJSONObject(i).getString("name");
                        Log.d(TAG, jsonArray.getString(0));
                    }
                    for (int i=0; i<array.length(); i++){

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
