package boaviagem.hamil.example.com.boaviagem;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

public class GastoActivity extends Activity {

    TextView destino;
    EditText valor;
    Button data;
    private int ano, mes, dia;
    Spinner categoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_gasto );

        binding();

        // busca a data atual para mostrar no botão
        Calendar calendar = Calendar.getInstance();
        ano = calendar.get( Calendar.YEAR );
        mes = calendar.get( Calendar.MONTH );
        dia = calendar.get( Calendar.DAY_OF_MONTH );

        data.setText( dia + "/" + (mes+1) + "/" + ano );

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,R.array.categoria_gasto,
                android.R.layout.simple_spinner_item);

        categoria.setAdapter( adapter );
        //descobrir porque esta dando errado nessa linha abaixo
        //String viagemDestino = getIntent().getExtras().getString(DashboardActivity.VIAGEM_DESTINO);
        //destino.setText( viagemDestino );

    }

    private void binding() {

        destino = findViewById( R.id.destinoId );
        valor = findViewById( R.id.valorID );
        data = findViewById( R.id.dataId );
        categoria = findViewById( R.id.spinnerCategoriaId );
    }


    public void selecionarData(View view){
        showDialog( view.getId() );
    }

    protected Dialog onCreateDialog(int id) {
        if(R.id.dataId == id){
            return new DatePickerDialog(this,
                    listener, ano, mes, dia);
        }
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.gasto_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        finish();
        return true;
    }

//    @Override
//    public boolean onMenuItemSelected(int featureId, MenuItem item) {
//        finish();
//        return true;
//    }

    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            ano = year;
            mes = monthOfYear;
            dia = dayOfMonth;
            data.setText(dia + "/" + (mes + 1) + "/" + ano);
        }
    };
//    private DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view,
//                              int year, int monthOfYear, int dayOfMonth) {
//            ano = year;
//            mes = monthOfYear;
//            dia = dayOfMonth;
//            data.setText( dia + "/" + (mes + 1) + "/" + ano );
//        }
//
//
//    } ;
}
