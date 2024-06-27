package com.example.lca;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Switch;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.Gravity;
import android.text.InputFilter;

import com.example.lca.controller.ListaController;
import com.example.lca.model.Lista;
import java.util.List;
import android.widget.Toast;
public class tela2 extends AppCompatActivity {
    private Switch switchDescTitulo, switchValor, switchTempo, switchCategoria, switchSubcategoria, switchData, switchDescItem, switchCheckBox;
    private RelativeLayout innerLayout;
    private EditText nomeTitulo, novoEditText;
    private List<Lista> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

            nomeTitulo = findViewById(R.id.nomeTitulo);
            switchDescTitulo = findViewById(R.id.switchDescTitulo);
            innerLayout = findViewById(R.id.innerLayout);

            switchDescTitulo.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked) {
                    // Se o switch estiver ativado, mova os elementos para baixo
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) innerLayout.getLayoutParams();
                    layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.inner_layout_top_margin_when_switch_on);
                    innerLayout.setLayoutParams(layoutParams);

                    View view2 = findViewById(R.id.view2);
                    ViewGroup.LayoutParams viewLayoutParams = view2.getLayoutParams();
                    viewLayoutParams.height = getResources().getDimensionPixelSize(R.dimen.view_height_when_switch_on);
                    view2.setLayoutParams(viewLayoutParams);

                    novoEditText = new EditText(this);
                    RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                            getResources().getDimensionPixelSize(R.dimen.edit_text_width),
                            getResources().getDimensionPixelSize(R.dimen.edit_text_height));
                    params.addRule(RelativeLayout.BELOW, R.id.nomeTitulo);
                    params.addRule(RelativeLayout.CENTER_HORIZONTAL);
                    novoEditText.setLayoutParams(params);
                    novoEditText.setHint("Digite a descrição");
                    novoEditText.setId(View.generateViewId());
                    novoEditText.setBackgroundResource(R.drawable.border1);
                    novoEditText.setEms(10);
                    novoEditText.setGravity(Gravity.TOP | Gravity.LEFT);
                    novoEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                    novoEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(120)});
                    novoEditText.setPadding(getResources().getDimensionPixelSize(R.dimen.edit_text_padding_start), 0, 0, 0);
                    innerLayout.addView(novoEditText);

                    String textoDoEditText = novoEditText.getText().toString();
                } else {
                    // Se o switch estiver desativado, mantenha os elementos onde estão
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) innerLayout.getLayoutParams();
                    layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.inner_layout_top_margin_when_switch_off);
                    innerLayout.setLayoutParams(layoutParams);

                    View view2 = findViewById(R.id.view2);
                    ViewGroup.LayoutParams viewLayoutParams = view2.getLayoutParams();
                    viewLayoutParams.height = getResources().getDimensionPixelSize(R.dimen.view_height_when_switch_off);
                    view2.setLayoutParams(viewLayoutParams);

                    if (novoEditText != null) {
                        innerLayout.removeView(novoEditText);
                        novoEditText = null;
                    }

                }
            });

        }

        public void tela3 (View view) {
            switchValor = findViewById(R.id.switchValor);
            switchTempo = findViewById(R.id.switchTempo);
            switchCategoria = findViewById(R.id.switchCategoria);
            switchSubcategoria = findViewById(R.id.switchSubcategoria);
            switchData = findViewById(R.id.switchData);
            switchDescItem = findViewById(R.id.switchDescItem);
            switchCheckBox = findViewById(R.id.switchCheckBox);
            nomeTitulo = findViewById(R.id.nomeTitulo);
            boolean switchValorChecked = switchValor.isChecked();
            boolean switchTempoChecked = switchTempo.isChecked();
            boolean switchCategoriaChecked = switchCategoria.isChecked();
            boolean switchSubcategoriaChecked = switchSubcategoria.isChecked();
            boolean switchDataChecked = switchData.isChecked();
            boolean switchDescItemChecked = switchDescItem.isChecked();
            boolean switchCheckBoxChecked = switchCheckBox.isChecked();

            String descricao = novoEditText != null ? novoEditText.getText().toString() : "";
            String tituloDigitado = nomeTitulo.getText().toString().trim();

            if (tituloDigitado.isEmpty()) {
                nomeTitulo.setError("Este campo é obrigatório");
                Toast.makeText(this, "Por favor, preencha o título", Toast.LENGTH_SHORT).show();
            } else {
                Context context = this;
                ListaController controller = new ListaController(context);
                controller.criarLista(tituloDigitado,descricao,false,switchDescItemChecked,switchCheckBoxChecked,switchCategoriaChecked,switchSubcategoriaChecked,switchDataChecked,switchTempoChecked,switchValorChecked);
                Intent in = new Intent(tela2.this, MainActivity.class);
                startActivity(in);
                finish();
            }
        }


            public void MainActivity (View view){
                Intent in = new Intent(tela2.this, MainActivity.class);
                startActivity(in);
            }


    }