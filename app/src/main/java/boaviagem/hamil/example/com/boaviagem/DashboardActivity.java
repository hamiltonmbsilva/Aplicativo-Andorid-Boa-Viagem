package boaviagem.hamil.example.com.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.MenuItemHoverListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DashboardActivity extends Activity {

    TextView novoGasto, novaViagem, minhasViagem,configuracao;

    final int Tela_Gasto = 2;
    final int Tela_NovaViagem = 3;
    final int Tela_MinhaVaigem = 4;
    final int Tela_Configuracao = 5;

    public static final String MODO_VIAGEM = "modo_viagem";
    public static final String VIAGEM_ID = "viagem_id";
    public static final String VIAGEM_DESTINO = "viagem_destino";
    public static final String MODO_SELECIONAR_VIAGEM = "modo_selecionar_viagem";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_dashboard );

        binding();

        novaViagem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(),NovaViagemActivity.class );

                startActivityForResult(itn,Tela_NovaViagem);
            }
        } );

        novoGasto.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(),GastoActivity.class );

                startActivityForResult(itn,Tela_Gasto);
            }
        } );

        minhasViagem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(),ViagemListActivity.class );

                startActivityForResult(itn,Tela_MinhaVaigem);
            }
        } );

        configuracao.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(),ConfiguracoesActivity.class );

                startActivityForResult(itn,Tela_Configuracao);
            }
        } );



    }

    private void binding() {

        novoGasto = findViewById( R.id.novo_gasto );
        novaViagem = findViewById( R.id.nova_viagem );
        minhasViagem = findViewById( R.id.minhas_viagens );
        configuracao = findViewById( R.id.configuracoes );
    }

    public void selecionarOpcao(View view) {
        switch (view.getId()) {
            case R.id.nova_viagem:
                startActivity(new Intent(this, NovaViagemActivity.class));
                break;
            case R.id.novo_gasto:
                SharedPreferences preferencias = PreferenceManager.getDefaultSharedPreferences(this);
                boolean modoViagem = preferencias.getBoolean(MODO_VIAGEM, false);

                if(modoViagem){
                    //obter o id da viagem atual
                    int viagemAtual = 1;
                    String destino = "São Paulo";
                    Intent intent = new Intent(this, GastoActivity.class);
                    intent.putExtra(VIAGEM_ID, viagemAtual);
                    intent.putExtra(VIAGEM_DESTINO, destino);
                    startActivity(intent);
                }else{
                    Intent intent = new Intent(this, ViagemListActivity.class);
                    intent.putExtra(MODO_SELECIONAR_VIAGEM, true);
                    startActivityForResult(intent, 0);
                }
                break;
            case R.id.minhas_viagens:
                startActivity(new Intent(this, ViagemListActivity.class));
                break;
            case R.id.configuracoes:
                startActivity(new Intent(this, ConfiguracoesActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            int id = data.getExtras().getInt(VIAGEM_ID);
            String destino = data.getExtras().getString(VIAGEM_DESTINO);

            Intent intent = new Intent(this, GastoActivity.class);
            intent.putExtra(VIAGEM_ID, id);
            intent.putExtra(VIAGEM_DESTINO, destino);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, getString(R.string.erro_selecionar_viagem),
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.dashbord_menu, menu);
        return true;
    }
    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        finish();
        return true;
    }

}
