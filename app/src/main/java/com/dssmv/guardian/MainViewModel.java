package com.dssmv.guardian;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

// import java.util.List; // Se quisermos guardar a lista de itens aqui

public class MainViewModel extends ViewModel {

    private final MutableLiveData<Boolean> _isLoading = new MutableLiveData<>(true);
    public LiveData<Boolean> isLoading() {
        return _isLoading;
    }

    public void setLoading(boolean loading) {
        _isLoading.postValue(loading);
    }

    // Exemplo para dados reais (a implementar depois)
    // private final MutableLiveData<List<ItemInventario>> _inventoryItems = new MutableLiveData<>();
    // public LiveData<List<ItemInventario>> getInventoryItems() { return _inventoryItems; }
    // public void loadInventory() {
    //     setLoading(true);
    //     // Lógica para carregar do ficheiro/DB numa thread separada...
    //     // Quando terminar, _inventoryItems.postValue(listaCarregada);
    //     // e setLoading(false);
    // }
}