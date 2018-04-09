package ru.hightechnologiescenter.company_employees;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ru.hightechnologiescenter.company_employees.Adapter.employeeListAdapter;
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
            String emplName = "name";
            String emplNumber = "phone_number";
            String emplSkills = "skills";
            new GetData().execute(URL, emplName, emplNumber, emplSkills);
        } else {
            Toast.makeText(this, "No Network Connection", Toast.LENGTH_LONG).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    class GetData extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {
            employeeList = new JSONParser().getEmployees(params[0], params[1], params[2], params[3]);
            return null;
        }

        protected void onPostExecute(Void result) {
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
