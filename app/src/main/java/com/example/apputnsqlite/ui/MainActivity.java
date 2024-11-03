package com.example.apputnsqlite.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.apputnsqlite.fragments.AutoresFragment;
import com.example.apputnsqlite.fragments.LibrosFragment;
import com.example.apputnsqlite.R;
import com.example.apputnsqlite.dao.Autores;
import com.example.apputnsqlite.dao.Libros;
import com.example.apputnsqlite.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    TextView txtId ,txtNombres, txtApellidos, txtIsoPais, txtEdad;
    Autores lsAutores;
    Libros lsLibros;
    TextView lblUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Configurar el Toolbar:
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        lblUsuario = binding.txtUsuario;
        lsAutores = new Autores(this, "biblioteca.db", 1);

        // leer los datos del diccionario Extra
        Bundle extra = getIntent().getExtras();
        if (extra != null) {
            String usuario = extra.getString("usuario");
            lblUsuario.setText("Bienvenido " + usuario);
        }

        replaceFragment(new AutoresFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.menu_autores) {
                replaceFragment(new AutoresFragment());
                return true;
            } else if (item.getItemId() == R.id.menu_libros) {
                replaceFragment(new LibrosFragment());
                return true;
            }
            return false;
        });
    }

    public void cmdRegresar_onClick(View v) {
        finish();
    }

    private void replaceFragment( Fragment fragment ) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}