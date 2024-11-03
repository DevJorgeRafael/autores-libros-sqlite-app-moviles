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
import com.example.apputnsqlite.classes.Libro;
import com.example.apputnsqlite.dao.Libros;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LibrosFragment extends Fragment {

    // Variables declaradas a nivel de clase
    private TextView txtLibroId, txtTitulo, txtAutor, txtIsbn, txtAnio, txtRevision, txtNroHojas;
    private Libros lsLibros;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_libros, container, false);

        // Inicializar los TextViews
        txtLibroId = view.findViewById(R.id.libro_id);
        txtTitulo = view.findViewById(R.id.libro_titulo);
        txtAutor = view.findViewById(R.id.libro_autor_id);
        txtIsbn = view.findViewById(R.id.libro_isbn);
        txtAnio = view.findViewById(R.id.libro_anio);
        txtRevision = view.findViewById(R.id.libro_revision);
        txtNroHojas = view.findViewById(R.id.libro_nro_hojas);

        // Inicializar la instancia de Libros
        lsLibros = new Libros(requireContext(), "biblioteca.db", 1);

        // Botones
        Button cmdCrear = view.findViewById(R.id.btn_create_book);
        Button cmdActualizar = view.findViewById(R.id.btn_update_book);
        Button cmdLeer = view.findViewById(R.id.btn_read_books);
        Button cmdEliminar = view.findViewById(R.id.btn_delete_book);

        // Establecer onClickListeners
        cmdCrear.setOnClickListener(v -> cmdCrear_onClick());
        cmdActualizar.setOnClickListener(v -> cmdActualizar_onClick());
        cmdLeer.setOnClickListener(v -> cmdLeer_onClick());
        cmdEliminar.setOnClickListener(v -> cmdEliminar_onClick());

        return view;
    }

    public void cmdCrear_onClick() {
        Libro libro = lsLibros.Create(
                Integer.parseInt(txtLibroId.getText().toString()),
                txtTitulo.getText().toString(),
                Integer.parseInt(txtAutor.getText().toString()),
                txtIsbn.getText().toString(),
                Integer.parseInt(txtAnio.getText().toString()),
                Integer.parseInt(txtRevision.getText().toString()),
                Integer.parseInt(txtNroHojas.getText().toString())
        );

        if (libro != null) {
            Toast.makeText(getContext(), "Libro creado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al crear Libro!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdActualizar_onClick() {
        Libro libro = lsLibros.Create(
                Integer.parseInt(txtLibroId.getText().toString()),
                txtTitulo.getText().toString(),
                Integer.parseInt(txtAutor.getText().toString()),
                txtIsbn.getText().toString(),
                Integer.parseInt(txtAnio.getText().toString()),
                Integer.parseInt(txtRevision.getText().toString()),
                Integer.parseInt(txtNroHojas.getText().toString())
        );

        if (libro != null) {
            Toast.makeText(getContext(), "Libro actualizado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al actualizar Libro!!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdLeer_onClick() {
        Libro libro = lsLibros.Read_ById(Integer.parseInt(txtLibroId.getText().toString()));
        if (libro != null) {
            txtLibroId.setText(libro.Id);
            txtTitulo.setText(libro.Titulo);
            txtAutor.setText(libro.IdAutor);
            txtIsbn.setText(libro.Isbn);
            txtAnio.setText(libro.AnioPublicacion);
            txtRevision.setText(libro.Revision);
            txtNroHojas.setText(libro.NroHojas);
        } else {
            Toast.makeText(getContext(), "Libro no encontrado!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdEliminar_onClick() {
        boolean eliminado = lsLibros.Delete(Integer.parseInt(txtLibroId.getText().toString()));
        if (eliminado) {
            txtLibroId.setText("");
            txtTitulo.setText("");
            txtAutor.setText("");
            txtIsbn.setText("");
            txtAnio.setText("");
            txtRevision.setText("");
            Toast.makeText(getContext(), "Libro eliminado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al eliminar Libro!!", Toast.LENGTH_LONG).show();
        }
    }
}
