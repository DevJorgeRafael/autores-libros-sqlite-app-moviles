package com.example.apputnsqlite.fragments;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
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
public class AutoresFragment extends Fragment {

    // Variables declaradas a nivel de clase
    private TextInputEditText txtId, txtNombres, txtApellidos, txtIsoPais, txtEdad;
    private Autores lsAutores;
    private Libros lsLibros;

    private TableLayout tableLayout;

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

        tableLayout = view.findViewById(R.id.tableLayoutLibros);

        // Inicializar las instancias de Autores y Libros
        lsAutores = new Autores(getContext(), "biblioteca.db", 1);
        lsLibros = new Libros(getContext(), "biblioteca.db", 1);

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

            // Obtener los libros del autor
            Libro[] libros = lsLibros.Read_ByAutorId(r.Id);

            // Limpiar el TableLayout antes de agregar los nuevos datos
            tableLayout.removeAllViews();

            // Crear la cabecera de la tabla
            TableRow headerRow = new TableRow(getContext());
            headerRow.setBackgroundColor(Color.DKGRAY); // Color de fondo para la cabecera

            // Encabezados de las columnas
            String[] headers = {"Id", "Título", "ISBN", "Año de Publicación", "N° de Hojas", "Revisión"};
            for (String header : headers) {
                TextView headerView = new TextView(getContext());
                headerView.setText(header);
                headerView.setPadding(8, 8, 8, 8); // Espaciado interno
                headerView.setTextColor(Color.WHITE); // Color del texto
                headerRow.addView(headerView);
            }
            tableLayout.addView(headerRow);

            // Agregar los libros a la tabla
            for (Libro libro : libros) {
                TableRow row = new TableRow(getContext());
                row.setBackgroundColor(Color.BLACK); // Color de fondo de la fila

                // Datos del libro

                TextView txtId = new TextView(getContext());
                txtId.setText(String.valueOf(libro.getId()));
                txtId.setPadding(8, 8, 8, 8);
                row.addView(txtId);

                TextView txtTitulo = new TextView(getContext());
                txtTitulo.setText(libro.getTitulo());
                txtTitulo.setPadding(8, 8, 8, 8); // Espaciado interno
                row.addView(txtTitulo);

                TextView txtIsbn = new TextView(getContext());
                txtIsbn.setText(libro.getIsbn());
                txtIsbn.setPadding(8, 8, 8, 8);
                row.addView(txtIsbn);

                TextView txtAnioPublicacion = new TextView(getContext());
                txtAnioPublicacion.setText(String.valueOf(libro.getAnioPublicacion()));
                txtAnioPublicacion.setPadding(8, 8, 8, 8);
                row.addView(txtAnioPublicacion);

                TextView txtNroHojas = new TextView(getContext());
                txtNroHojas.setText(String.valueOf(libro.getNroHojas()));
                txtNroHojas.setPadding(8, 8, 8, 8);
                row.addView(txtNroHojas);

                TextView txtRevision = new TextView(getContext());
                txtRevision.setText(String.valueOf(libro.getRevision()));
                txtRevision.setPadding(8, 8, 8, 8);
                row.addView(txtRevision);

                tableLayout.addView(row);
            }

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
