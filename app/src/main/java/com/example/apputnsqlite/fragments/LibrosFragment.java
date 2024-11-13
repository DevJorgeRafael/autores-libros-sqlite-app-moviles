package com.example.apputnsqlite.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apputnsqlite.R;
import com.example.apputnsqlite.classes.Autor;
import com.example.apputnsqlite.classes.Libro;
import com.example.apputnsqlite.dao.Autores;
import com.example.apputnsqlite.dao.Libros;
import com.google.android.material.textfield.TextInputEditText;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class LibrosFragment extends Fragment {

    // Variables declaradas a nivel de clase
    private TextInputEditText txtLibroId, txtTitulo, txtLibroAutorId, txtIsbn, txtAnio, txtRevision, txtNroHojas;
    private Libros lsLibros;
    private Autores lsAutores;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_libros, container, false);

        // Inicializar los TextViews
        txtLibroId = view.findViewById(R.id.txtLibroId);
        txtTitulo = view.findViewById(R.id.txtLibroTitulo);
        txtLibroAutorId = view.findViewById(R.id.txtLibroAutorId);
        txtIsbn = view.findViewById(R.id.txtLibroIsbn);
        txtAnio = view.findViewById(R.id.txtLibroAnio);
        txtRevision = view.findViewById(R.id.txtLibroRevision);
        txtNroHojas = view.findViewById(R.id.txtLibroNroHojas);

        // Inicializar la instancia de Libros
        lsLibros = new Libros(requireContext(), "biblioteca.db", 1);
        lsAutores = new Autores(requireContext(), "biblioteca.db", 1);

        // Botones
        ImageButton cmdCrear = view.findViewById(R.id.cmdCrear);
        ImageButton cmdActualizar = view.findViewById(R.id.cmdActualizar);
        ImageButton cmdLeer = view.findViewById(R.id.cmdLeer);
        ImageButton cmdEliminar = view.findViewById(R.id.cmdEliminar);

        // Establecer onClickListeners
        cmdCrear.setOnClickListener(v -> cmdCrear_onClick());
        cmdActualizar.setOnClickListener(v -> cmdActualizar_onClick());
        cmdLeer.setOnClickListener(v -> cmdLeer_onClick());
        cmdEliminar.setOnClickListener(v -> cmdEliminar_onClick());

        return view;
    }

    public void cmdCrear_onClick() {
        boolean isValid = true;

        // Validación de los campos de texto
        if (txtLibroId.getText().toString().isEmpty()) {
            txtLibroId.setError("El ID del libro no puede estar vacío");
            isValid = false;
        } else {
            txtLibroId.setError(null); // Elimina el error si el campo es válido
        }

        if (txtTitulo.getText().toString().isEmpty()) {
            txtTitulo.setError("El título no puede estar vacío");
            isValid = false;
        } else {
            txtTitulo.setError(null);
        }

        if (txtLibroAutorId.getText().toString().isEmpty()) {
            txtLibroAutorId.setError("El ID del autor no puede estar vacío");
            isValid = false;
        } else {
            txtLibroAutorId.setError(null);
        }

        if (txtIsbn.getText().toString().isEmpty()) {
            txtIsbn.setError("El ISBN no puede estar vacío");
            isValid = false;
        } else {
            txtIsbn.setError(null);
        }

        if (txtAnio.getText().toString().isEmpty()) {
            txtAnio.setError("El año no puede estar vacío");
            isValid = false;
        } else {
            txtAnio.setError(null);
        }

        if (txtRevision.getText().toString().isEmpty()) {
            txtRevision.setError("La revisión no puede estar vacía");
            isValid = false;
        } else {
            txtRevision.setError(null);
        }

        if (txtNroHojas.getText().toString().isEmpty()) {
            txtNroHojas.setError("El número de hojas no puede estar vacío");
            isValid = false;
        } else {
            txtNroHojas.setError(null);
        }

        // Si algún campo no es válido, no continuamos con la creación del libro
        if (!isValid) {
            return; // Detiene el proceso si hay errores
        }

        // Verificación si el autor existe
        Autor autor = lsAutores.Read_By_Id(Integer.parseInt(txtLibroAutorId.getText().toString()));
        if (autor == null) {
            txtLibroAutorId.setError("Autor no encontrado");
            return;
        } else {
            txtLibroAutorId.setError(null); // Elimina el error si el autor existe
        }

        // Si los campos están llenos y el autor es válido, se crea el libro
        Libro libro = lsLibros.Create(
                Integer.parseInt(txtLibroId.getText().toString()),
                txtTitulo.getText().toString(),
                Integer.parseInt(txtLibroAutorId.getText().toString()),
                txtIsbn.getText().toString(),
                Integer.parseInt(txtAnio.getText().toString()),
                Integer.parseInt(txtRevision.getText().toString()),
                Integer.parseInt(txtNroHojas.getText().toString())
        );

        // Resultado del intento de creación
        if (libro != null) {
            Toast.makeText(getContext(), "Libro creado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al crear Libro!!", Toast.LENGTH_LONG).show();
        }
    }


    public void cmdActualizar_onClick() {

        boolean isValid = true;

        // Validación de los campos de texto
        if (txtLibroId.getText().toString().isEmpty()) {
            txtLibroId.setError("El ID del libro no puede estar vacío");
            isValid = false;
        } else {
            txtLibroId.setError(null); // Elimina el error si el campo es válido
        }

        if (txtTitulo.getText().toString().isEmpty()) {
            txtTitulo.setError("El título no puede estar vacío");
            isValid = false;
        } else {
            txtTitulo.setError(null);
        }

        if (txtLibroAutorId.getText().toString().isEmpty()) {
            txtLibroAutorId.setError("El ID del autor no puede estar vacío");
            isValid = false;
        } else {
            txtLibroAutorId.setError(null);
        }

        if (txtIsbn.getText().toString().isEmpty()) {
            txtIsbn.setError("El ISBN no puede estar vacío");
            isValid = false;
        } else {
            txtIsbn.setError(null);
        }

        if (txtAnio.getText().toString().isEmpty()) {
            txtAnio.setError("El año no puede estar vacío");
            isValid = false;
        } else {
            txtAnio.setError(null);
        }

        if (txtRevision.getText().toString().isEmpty()) {
            txtRevision.setError("La revisión no puede estar vacía");
            isValid = false;
        } else {
            txtRevision.setError(null);
        }

        if (txtNroHojas.getText().toString().isEmpty()) {
            txtNroHojas.setError("El número de hojas no puede estar vacío");
            isValid = false;
        } else {
            txtNroHojas.setError(null);
        }

        // Si algún campo no es válido, no continuamos con la creación del libro
        if (!isValid) {
            return; // Detiene el proceso si hay errores
        }

        // Verificación si el autor existe
        Autor autor = lsAutores.Read_By_Id(Integer.parseInt(txtLibroAutorId.getText().toString()));
        if (autor == null) {
            txtLibroAutorId.setError("Autor no encontrado");
            return;
        } else {
            txtLibroAutorId.setError(null); // Elimina el error si el autor existe
        }

        Libro libro = lsLibros.Update(
                Integer.parseInt(txtLibroId.getText().toString()),
                txtTitulo.getText().toString(),
                Integer.parseInt(txtLibroAutorId.getText().toString()),
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
        if( txtLibroId.getText().toString().isEmpty() ) {
            txtLibroId.setError("El ID del libro no puede estar vacío");
            return;
        }

        Libro libro =  (Libro) lsLibros.Read_ById(Integer.parseInt(txtLibroId.getText().toString()));
        if (libro != null) {

            txtTitulo.setText(libro.getTitulo() != null ? libro.getTitulo() : "");
            txtLibroAutorId.setText(String.valueOf(libro.getIdAutor()));
            txtIsbn.setText(libro.getIsbn() != null ? libro.getIsbn() : "");
            txtAnio.setText(String.valueOf(libro.getAnioPublicacion()));
            txtRevision.setText(String.valueOf(libro.getRevision()));
            txtNroHojas.setText(String.valueOf(libro.getNroHojas()));
        } else {
            Toast.makeText(getContext(), "Libro no encontrado!", Toast.LENGTH_LONG).show();
        }
    }

    public void cmdEliminar_onClick() {
        if( txtLibroId.getText().toString().isEmpty() ) {
            txtLibroId.setError("El ID del libro no puede estar vacío");
            return;
        }

        boolean eliminado = lsLibros.Delete(Integer.parseInt(txtLibroId.getText().toString()));
        if (eliminado) {
            txtLibroId.setText("");
            txtTitulo.setText("");
            txtLibroAutorId.setText("");
            txtIsbn.setText("");
            txtAnio.setText("");
            txtRevision.setText("");
            Toast.makeText(getContext(), "Libro eliminado correctamente", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), "Error al eliminar Libro!!", Toast.LENGTH_LONG).show();
        }
    }
}
