package com.bnucist2016cs.xieshuzhao.partybranchworkbook.data;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bnucist2016cs.xieshuzhao.partybranchworkbook.R;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class BookFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    List<Book> bookList = new ArrayList<>();
    BookAdapter bookAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;




    // TODO: Rename and change types and number of parameters
    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  view = inflater.inflate(R.layout.fragment_book, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.book_recycler_view);
        bookList = DataSupport.findAll(Book.class);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        bookAdapter = new BookAdapter(bookList);
        recyclerView.setAdapter(bookAdapter);
        return view;
    }

}
