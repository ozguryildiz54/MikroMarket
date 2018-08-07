package tr.com.greensoft.mikromarket.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import tr.com.greensoft.mikromarket.R;

public class UrunEkleActivity extends AppCompatActivity {
    EditText urunadi, barkod, kdvOrani, kdvHaricAlis, kdvDahilAlis, kdvHaricSatis, kdvDahilSatis, adet;
    Spinner dropdown;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_ekle);

        urunadi = findViewById(R.id.urunAdiText);
        barkod = findViewById(R.id.barkodText);
        kdvOrani = findViewById(R.id.kdvText);

        kdvHaricAlis = findViewById(R.id.kdvHaricAlisText);
        kdvDahilAlis = findViewById(R.id.kdvDahilAlisText);

        kdvHaricSatis = findViewById(R.id.kdvHaricSatisText);
        kdvDahilSatis = findViewById(R.id.kdvDahilSatis);
        adet = findViewById(R.id.adetText);
        dropdown = findViewById(R.id.kdvSpinner);

        getKdvValues();
    }

    public void insert_support(View w) { // Send data to mysql database.
        String url = "http://192.168.1.104:8080/support_add";
        final com.android.volley.RequestQueue queue = Volley.newRequestQueue(this); // Sunucu istediği için Volley kütüphanesi kullanıldı.
        StringRequest compareRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            response = jsonObj.getString("message");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(UrunEkleActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() { // Sunucuya göndereceğimiz istediğin parametreleri
                Map<String, String> params = new HashMap<String, String>();
                params.put("urunadi", urunadi.getText().toString()); // the entered data as the body.
                params.put("barkod", barkod.getText().toString());
                params.put("kdvOrani", kdvOrani.getText().toString());

                params.put("kdvHaricAlis", kdvHaricAlis.getText().toString()); // the entered data as the body.
                params.put("kdvDahilAlis", kdvDahilAlis.getText().toString());

                params.put("kdvHaricSatis", kdvHaricSatis.getText().toString());
                params.put("kdvDahilSatis", kdvDahilSatis.getText().toString()); // the entered data as the body.
                params.put("adet", adet.getText().toString());
                return params;
            }
        };
        queue.add(compareRequest);
    }

    public void clearScreen(View v) {
        urunadi.setText("");
        barkod.setText("");
        kdvOrani.setText("");
        kdvHaricAlis.setText("");
        kdvDahilAlis.setText("");
        kdvHaricSatis.setText("");
        kdvDahilSatis.setText("");
        adet.setText("");
    }

    public void getKdvValues() {
        RequestQueue queue = Volley.newRequestQueue(this); // Java da http protokolünü için volley kütüphanesi tercih edildi.
        String url = "http://192.168.1.104:8080/kdvOranlari"; // Sunucumuzun tam adresi ve sunucuya gönderilen isteğimizin parametresi
        StringRequest compareRequest = new StringRequest(Request.Method.GET, url, // Sunucuya istedğimiz bu blokta ki tanımlama ile gönderiyoruz.
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            JSONArray jarray = new JSONArray(response); // Sunucumuzdan dönen cevap response nesnesinde saklanır ve biz bu nesne ile sunucunun gönderdiği kişileri ulaşacağız.
                            // Bunun için bu nesne string tipinde olduğundan Json array tipine dönüştürüyoruz.

                            int j = jarray.length();
                            int n = ++j;
                            List<String> a = new ArrayList<>();
                            for (int i = 0; i < jarray.length(); i++) { // Cevap olarak gelen tüm kişilerin bilgisine ulaşıp hepsini listeye aktaracağız.
                                String kdvOrani = jarray.getJSONObject(i).getString("kdvorani").toString();
                                a.add(kdvOrani);
                            }

                            ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, a);

                            //dropdown.setSelection(2);
                            dropdown.setPrompt("Select an item");
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            dropdown.setAdapter(adapter);

                            dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                    String selectedItemText = (String) parent.getItemAtPosition(position);
                                    // If user change the default selection
                                    // First item is disable and it is used for hint
                                    if(position > 0){
                                        // Notify the selected item text
                                        Toast.makeText
                                                (getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        });
        queue.add(compareRequest); // Bu isteğimizi sıraya ekler ve her bu isteği gerçekleştirmek istediğimizde bu kuyruk sayesinde bu isteklerin takibi yapılır ve gerçekleştirilir.

    }
    }
