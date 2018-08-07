package tr.com.greensoft.mikromarket.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.http.conn.util.InetAddressUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import tr.com.greensoft.mikromarket.R;

public class UserRegistrationActivity extends AppCompatActivity {
    EditText eposta, sifre, isim, kimlik, telefon, adres;
    String manufacturer = "", model = "";
    String ipaddress = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        eposta = findViewById(R.id.epostaText);
        sifre = findViewById(R.id.sifreText);
        isim = findViewById(R.id.isimText);
        kimlik = findViewById(R.id.kimlikText);
        telefon = findViewById(R.id.telefonText);
        adres = findViewById(R.id.adresText);
    }

    public void sendData(View w) { // Send data to mysql database.
        getDeviceInfo();
        ipaddress = getIPAddress();
        String url = "http://192.168.1.104:8080/sign_in";
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
                        Toast.makeText(UserRegistrationActivity.this, response, Toast.LENGTH_SHORT).show();
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
                params.put("adisoyadi", isim.getText().toString());
                params.put("telefon", telefon.getText().toString());
                params.put("tcno", kimlik.getText().toString());
                params.put("adres", adres.getText().toString());

                params.put("model", model);
                params.put("marka", manufacturer);
                params.put("ipadresi", ipaddress);

                return params;
            }
        };
        queue.add(compareRequest);
    }
    public void clearScreen (View v){
        eposta.setText("");
        sifre.setText("");
        isim.setText("");
        kimlik.setText("");
        telefon.setText("");
        adres.setText("");
    }

    public void getDeviceInfo() {
        manufacturer = Build.MANUFACTURER; // Device Brand Name.
        model = Build.MODEL; // Device Model Name.
    }

    public String getIPAddress() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && InetAddressUtils.isIPv4Address(inetAddress.getHostAddress())) {
                        ipaddress = inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException ex) {
        }

        return ipaddress;
    }
}
