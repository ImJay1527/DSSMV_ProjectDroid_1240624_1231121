package com.dssmv.guardian;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel viewModel;

    private TextView tvExpiringSoonContent;
    private TextView tvTotalItems;
    private TextView tvExpiredItems;
    private Button btnScanBarcode;
    private Button btnAddManual;
    private Button btnShoppingList;
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewModel ainda é usado para o loading, mas não da splash
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        findViews();
        setupActionButtonsListeners();
        setupBottomNavigationListener();

        // Inicia o carregamento de dados (simulado)
        simulateDataLoading();
    }

    private void findViews() {
        tvExpiringSoonContent = findViewById(R.id.tvExpiringSoonContent);
        tvTotalItems = findViewById(R.id.tvTotalItems);
        tvExpiredItems = findViewById(R.id.tvExpiredItems);
        btnScanBarcode = findViewById(R.id.btnScanBarcode);
        btnAddManual = findViewById(R.id.btnAddManual);
        btnShoppingList = findViewById(R.id.btnShoppingList);
        bottomNavigationView = findViewById(R.id.bottom_navigation_view);
    }

    private void setupActionButtonsListeners() {
        btnScanBarcode.setOnClickListener(v -> {
            Toast.makeText(this, getString(R.string.toast_scan), Toast.LENGTH_SHORT).show();
        });
        btnAddManual.setOnClickListener(v -> {
            Toast.makeText(this, getString(R.string.toast_add_manual), Toast.LENGTH_SHORT).show();
        });
        btnShoppingList.setOnClickListener(v -> {
            Toast.makeText(this, getString(R.string.toast_shopping_list), Toast.LENGTH_SHORT).show();
        });
    }

    private void setupBottomNavigationListener() {
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.navigation_home) {
                    return true;
                } else if (itemId == R.id.navigation_inventory) {
                    Toast.makeText(MainActivity.this, getString(R.string.toast_inventory), Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.navigation_map) {
                    Toast.makeText(MainActivity.this, getString(R.string.toast_map), Toast.LENGTH_SHORT).show();
                    return true;
                } else if (itemId == R.id.navigation_settings) {
                    Toast.makeText(MainActivity.this, getString(R.string.toast_settings), Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });
    }

    private void simulateDataLoading() {
        // Inicialmente, mostra "(Carregando...)" (já definido no XML)
        // Inicia a simulação
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (viewModel != null) {
                viewModel.setLoading(false); // Sinaliza que o "loading" terminou
            }
            updateUIWithLoadedData(); // Atualiza a UI com os dados
        }, 2000);
    }

    private void updateUIWithLoadedData() {
        // Dados simulados
        int total = 15;
        int expired = 1;

        tvExpiringSoonContent.setText("Leite (expira em 2 dias)\nQueijo (expira em 5 dias)");
        tvTotalItems.setText(getString(R.string.total_items_label, total));
        tvExpiredItems.setText(getString(R.string.expired_items_label, expired));
    }
}