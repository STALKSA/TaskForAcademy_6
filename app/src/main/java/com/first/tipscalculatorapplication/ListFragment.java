package com.first.tipscalculatorapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment {
    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<MyItem> itemList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Создаем список элементов
        itemList = new ArrayList<>();
        itemList.add(new MyItem("Семен С.", "Руководитель отдела web тестирования", R.drawable.image1));
        itemList.add(new MyItem("Гаврила Б.", "Team Lead отдела frontend разработки", R.drawable.image2));
        itemList.add(new MyItem("Савичева И", "Тестировщик(мануал)", R.drawable.image3));

        adapter = new MyAdapter(itemList, new MyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyItem item) {
                Fragment detailFragment = DetailFragment.newInstance(item.getTitle(), item.getDescription(), item.getImageResourceId());
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, detailFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
        recyclerView.setAdapter(adapter);

        return view;
    }
}