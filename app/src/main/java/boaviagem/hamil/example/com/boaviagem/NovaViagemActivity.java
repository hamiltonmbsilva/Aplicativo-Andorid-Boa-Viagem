package boaviagem.hamil.example.com.boaviagem;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;
import java.util.Date;

public class NovaViagemActivity extends Activity {

    EditText destino, orcamento,numeroPessoas;
    Button dataChegada;
    Button dataSaida;
    Button novaViagem;
    private Date dataChegada1, dataSaida1;
    private int ano, mes, dia;
    RadioButton lazer, negocio;
    final int Tela_Viagem = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_nova_viagem );

        dinding();

        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR);
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);
    }

    public void selecionarData(View view) {
        showDialog(view.getId());
    }

    @Override
    protected Dialog onCreateDialog(int id) {

        switch (id) {
            case R.id.selecioneDataChegadaId:
                return new DatePickerDialog(this, dataChegadaListener, ano, mes, dia);

            case R.id.selecioneDataSaida:
                return new DatePickerDialog(this, dataSaidaListener, ano, mes, dia);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener dataChegadaListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
            dataChegada1 = criarData(anoSelecionado, mesSelecionado, diaSelecionado);
            dataChegada.setText(dia + "/" + (mes + 1) + "/" + ano);
        }
    };

    private DatePickerDialog.OnDateSetListener dataSaidaListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int anoSelecionado, int mesSelecionado, int diaSelecionado) {
            dataSaida1 = criarData(anoSelecionado, mesSelecionado, diaSelecionado);
            dataSaida.setText(dia + "/" + (mes + 1) + "/" + ano);
        }
    };

    private Date criarData(int anoSelecionado, int mesSelecionado, int diaSelecionado) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(anoSelecionado, mesSelecionado, diaSelecionado);
        return calendar.getTime();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.viagem_menu, menu);
        return true;
    }

    @Override
    public boolean onMenuItemSelected(int featureId,
                                      MenuItem item) {
        switch (item.getItemId()) {

            case R.id.novo_gasto:
                startActivity(new Intent(this,
                        GastoActivity.class));
                return true;
            case R.id.remover:
        //remover viagem do banco de dados
                return true;
            default:
                return super.onMenuItemSelected(featureId, item);
        }
    }

    private void dinding() {
        destino = findViewById( R.id.destinoId );
        orcamento = findViewById( R.id.orcamentoId );
        numeroPessoas = findViewById( R.id.numerosPessoasId );
        dataChegada = findViewById( R.id.selecioneDataChegadaId );
        dataSaida = findViewById( R.id.selecioneDataSaida );
        lazer = findViewById( R.id.RadioButtonLazerId );
        negocio = findViewById( R.id.RadioButtonNegocioId );
    }
}
