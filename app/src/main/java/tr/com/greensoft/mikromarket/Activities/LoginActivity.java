package tr.com.greensoft.mikromarket.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import tr.com.greensoft.mikromarket.R;

public class LoginActivity extends AppCompatActivity {
    EditText eposta,sifre;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        eposta= findViewById(R.id.epostaLogin);
        sifre=findViewById(R.id.sifreLogin);
    }

    public void userRegistration(View v){
        Intent intent = new Intent(LoginActivity.this, UserRegistrationActivity.class);
        startActivity(intent);
    }

    public void logIn(View v){
        /*String url = "http://192.168.1.104:8080/login";
        final com.android.volley.RequestQueue queue = Volley.newRequestQueue(this); // Sunucu istediği için Volley kütüphanesi kullanıldı.
        StringRequest compareRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObj = new JSONObject(response);
                            response = jsonObj.getString("message");
                            Log.i("Response:",response);
                            if (response.equals("OK")){*/
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                                //Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                /*return;
                            }
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError arg0) {
            }
        }) {
            @Override
            protected Map<String, String> getParams() { // Sunucuya göndereceğimiz istediğin parametreleri
                Map<String, String> params = new HashMap<String, String>();
                params.put("eposta", eposta.getText().toString()); // the entered data as the body.
                params.put("sifre", sifre.getText().toString());

                return params;
            }
        };
        queue.add(compareRequest);
        */
    }
}
