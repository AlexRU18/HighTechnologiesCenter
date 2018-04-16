package ru.hightechnologiescenter.company_employees;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ru.hightechnologiescenter.company_employees.Adapter.employeeListAdapter;
import ru.hightechnologiescenter.company_employees.Model.Company;
import ru.hightechnologiescenter.company_employees.Model.Employee;
import ru.hightechnologiescenter.company_employees.Utils.JSONParser;
import ru.hightechnologiescenter.company_employees.Utils.Utils;

public class ListActivity extends AppCompatActivity {

    private static final String URL = "http://www.mocky.io/v2/56fa31e0110000f920a72134";
    List<Employee> employeeList;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        lv = findViewById(R.id.employeesList);
        if (Utils.isNetworkAvailable(this)) {
            new GetData().execute(URL);
        } else {
            Toast.makeText(this, "No Network Connection", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class GetData extends AsyncTask<String, Void, ArrayList<Company>> {

        @Override
        protected ArrayList<Company> doInBackground(String... params) {
            employeeList = new JSONParser().getData(URL);
            return null;
        }

        protected void onPostExecute(ArrayList<Company> result) {
            super.onPostExecute(result);
            if (null == employeeList || employeeList.size() == 0) {
                Toast.makeText(getApplicationContext(), "No data found from web", Toast.LENGTH_LONG).show();
            } else {
                employeeListAdapter lvAdapter = new employeeListAdapter(getApplicationContext(),
                        R.layout.employees_list_item, employeeList);
                lv.setAdapter(lvAdapter);
            }
        }
    }
}
