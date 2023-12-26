package com.mohamedtaofig.bookstore.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mohamedtaofig.bookstore.Model.Book;
import com.mohamedtaofig.bookstore.R;
import com.mohamedtaofig.bookstore.UI.BookActivity;
import com.mohamedtaofig.bookstore.databinding.BookItemBinding;

import java.util.ArrayList;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {

    ArrayList<Book> bookArrayList;
    Context context;

    public BookAdapter(ArrayList<Book> bookArrayList, Context context) {
        this.bookArrayList = bookArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookViewHolder(LayoutInflater.from(context).inflate(R.layout.book_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookArrayList.get(position);
        holder.binding.bookTitle.setText(book.getTitle());
        String description = book.getDescription();
        String title = book.getTitle();
        String image = book.getImage();
        String link = book.getLink();
        String date = book.getDate();
        Glide.with(context)
                .load(book.getImage())
                .into(holder.binding.bookImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, BookActivity.class);
                intent.putExtra("title",title);
                intent.putExtra("description",description);
                intent.putExtra("image",image);
                intent.putExtra("link",link);
                intent.putExtra("date",date);
                context.startActivity(intent);


            }
        });
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder{
        BookItemBinding binding;

        public BookViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = BookItemBinding.bind(itemView);
        }
    }
}
