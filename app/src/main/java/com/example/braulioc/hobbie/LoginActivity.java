package com.example.braulioc.hobbie;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    TextView tvRegistro;
    Button btnLogin;
    TextView tvMsg;
    EditText userName;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvRegistro = (TextView) findViewById(R.id.tvRegistrarse);
        btnLogin = (Button) findViewById(R.id.btnInicia);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        userName = (EditText) findViewById(R.id.etEmail);
        pass = (EditText) findViewById(R.id.etPassword);
        tvMsg = (TextView) findViewById(R.id.tvMsg);

    }

    private void enviarOnClic(View v) {

        final String NAMESPACE = "http://tempuri.org/";
        final String URL = "http://localhost:53176/DB.asmx?op=loginUser";
        final String METHOD_NAME = "loginUser";
        final String SOAP_ACTION = "http://tempuri.org/loginUser";

        Thread hilo = new Thread() {
            String res;
            EditText userName = (EditText) findViewById(R.id.etEmail);
            EditText pass = (EditText) findViewById(R.id.etPassword);

            @Override
            public void run() {
                final String NAMESPACE = "http://tempuri.org/";
                final String URL = "http://localhost:53176/DB.asmx?op=loginUser";
                final String METHOD_NAME = "loginUser";
                final String SOAP_ACTION = "http://tempuri.org/loginUser";

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("userName", userName.getText().toString());
                request.addProperty("pass", pass.getText().toString());
                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.dotNet = true;
                envelope.setOutputSoapObject(request);
                HttpTransportSE transporte = new HttpTransportSE(URL);

                try{
                    transporte.call(SOAP_ACTION, envelope);
                    SoapPrimitive resultado_xml = (SoapPrimitive) envelope.getResponse();
                    res = resultado_xml.toString();


                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        Toast toast1 = Toast.makeText(getApplicationContext(),
                                        "Se ha iniciado sesión correctamente", Toast.LENGTH_SHORT);
                        toast1.show();
                        startActivity(intent);
                    }
                });

            }
        };



    }
}
