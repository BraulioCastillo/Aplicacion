package com.example.braulioc.hobbie;

import android.content.Intent;
import android.media.MediaCodec;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {

    TextView tvIS;
    EditText etNombre;
    EditText etApellidos;
    EditText etCorreo;
    EditText etContra;
    Button btnRegistro;
    TextView tvMsg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        tvIS = (TextView) findViewById(R.id.tvIniciarSesion);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        etApellidos = (EditText) findViewById(R.id.etLastName);
        etContra = (EditText) findViewById(R.id.etPassword);
        etCorreo = (EditText) findViewById(R.id.etEmail);
        etNombre = (EditText) findViewById(R.id.etName);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        tvIS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //valida que el correo no esté en uso y sea válido
                if (validarEmail(etCorreo.getText().toString())){
                    etNombre.setText("");
                    etContra.setText("");
                    etCorreo.setText("");
                    etApellidos.setText("");
                    tvMsg.setText("Error con el correo, por favor intente nuevamente.");
                }
                //valida que todos los campos estén llenos
                else if (etApellidos.getText().equals("Apellidos") ||
                        etCorreo.getText().equals("Email") ||
                        etContra.getText().equals("Contraseña") || etNombre.getText().equals("Nombre")){
                    etNombre.setText("");
                    etContra.setText("");
                    etCorreo.setText("");
                    etApellidos.setText("");
                    tvMsg.setText("Por favor llene todos los campos.");
                }
                //valida que la base esté vacia e inserta sin validaciones
                //else if (true){
                  //  tvMsg.setText("Registro exitoso, inicie sesión.");
                //}
            }
        });
    }
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
