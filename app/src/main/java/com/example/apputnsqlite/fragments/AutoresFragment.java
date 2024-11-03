package com.example.apputnsqlite.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apputnsqlite.R;
import com.example.apputnsqlite.classes.Autor;
import com.example.apputnsqlite.dao.Autores;
import com.example.apputnsqlite.dao.Libros;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AutoresFragment extends Fragment {

    // Variables declaradas a nivel de clase
    private TextView txtId, txtNombres, txtApellidos, txtIsoPais, txtEdad;
    private Autores lsAutores;
    private Libros lsLibros;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_autores, container, false);

        // Inicializar los TextViews
        txtId = view.findViewById(R.id.txtId);
        txtNombres = view.findViewById(R.id.txtNombres);
        txtApellidos = view.findViewById(R.id.txtApellidos);
        txtIsoPais = view.findViewById(R.id.txtIsoPais);
        txtEdad = view.findViewById(R.id.txtEdad);

        // Inicializar las instancias de Autores y Libros
        lsAutores = new Autores(requireContext(), "biblioteca.db", 1);
        lsLibros = new Libros(requireContext(), "biblioteca.db", 1);

        // Botones
        Button cmdCrear = view.findViewById(R.id.cmdCrear);
        Button cmdActualizar = view.findViewById(R.id.cmdActualizar);
        Button cmdLeer = view.findViewById(R.id.cmdLeer);
        Button cmdEliminar = view.findViewById(R.id.cmdEliminar);
        Button cmdLibros = view.findViewById(R.id.cmdLibros);

        // Establecer onClickListeners
        cmdCrear.setOnClickListener(v -> cmdCrear_onClick());
        cmdActualizar.setOnClickListener(v -> cmdActualizar_onClick());
        cmdLeer.setOnClickListener(v -> cmdLeer_onClick());
        cmdEliminar.setOnClickListener(v -> cmdEliminar_onClick());

        return view;
    }

    public void cmdCrear_onClick() {
        Autor r = lsAutores.Create(
                Integer.parseInt(txtId.getText().toString()),
                txtNombres.getText().toString(),
                txtApellidos.getText().toString(),
                txtIsoPais.getText().toString(),
                Integer.parseInt(txtEdad.getText().toString())
        );

        if (r != null) {
            Toast.makeText(getContext(), "Autor creado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al crear Autor!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdActualizar_onClick() {
        Autor r = lsAutores.Update(
                Integer.parseInt(txtId.getText().toString()),
                txtNombres.getText().toString(),
                txtApellidos.getText().toString(),
                txtIsoPais.getText().toString(),
                Integer.parseInt(txtEdad.getText().toString())
        );

        if (r != null) {
            Toast.makeText(getContext(), "Autor actualizado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al actualizar Autor!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdLeer_onClick() {
        Autor r = lsAutores.Read_By_Id(Integer.parseInt(txtId.getText().toString()));
        if (r != null) {
            txtNombres.setText(r.Nombres);
            txtApellidos.setText(r.Apellidos);
            txtIsoPais.setText(r.IsoPais);
            txtEdad.setText(String.valueOf(r.Edad));
        } else {
            Toast.makeText(getContext(), "Autor no encontrado!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdEliminar_onClick() {
        boolean r = lsAutores.Delete(Integer.parseInt(txtId.getText().toString()));
        if (r) {
            txtId.setText("");
            txtNombres.setText("");
            txtApellidos.setText("");
            txtIsoPais.setText("");
            txtEdad.setText("");
            Toast.makeText(getContext(), "Autor eliminado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al eliminar Autor!!", Toast.LENGTH_LONG).show();
        }
    }
}
