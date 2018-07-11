package boaviagem.hamil.example.com.boaviagem;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
    private static final String MANTER_CONECTADO = "manter_conectado";
    EditText usuario, senha;
    Button entrar;
    final int Tela_Dashboard = 1;
     CheckBox manterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_login );

        binding();

        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
        boolean conectado = preferencias.getBoolean(MANTER_CONECTADO, false);

        if(conectado){
            startActivity(new Intent(this, DashboardActivity.class));
        }

        entrar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(getApplicationContext(),DashboardActivity.class );

                String usurioINformado = usuario.getText().toString();
                String senhaInformada = senha.getText().toString();


                if("leitor".equals(usurioINformado)  && "123".equals(senhaInformada)){
                        SharedPreferences preferencias = getPreferences(MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferencias.edit();
                        editor.putBoolean(MANTER_CONECTADO, manterConectado.isChecked());
                        editor.commit();

                        startActivityForResult(itn,Tela_Dashboard );
                } else{

                    String mensagemErro = getString(R.string.erro_autenticacao);

                    Toast.makeText( getApplicationContext(),mensagemErro,Toast.LENGTH_LONG ).show();
//                    String mensagemErro = getString( R.string.erro_autenticacao );
//                    Toast toast = Toast.makeText( this, mensagemErro,
//                            Toast.LENGTH_SHORT);
//                    toast.show();
                }
            }
        } );
    }

    private void binding() {
        usuario = findViewById( R.id.usuarioId );
        senha = findViewById( R.id.senhaId );
        entrar = findViewById( R.id.botaoEntrarId );
        manterConectado = findViewById( R.id.manterConectado );
    }
}
