package com.example.user.ebooks.db;

import com.example.user.ebooks.base.Book;

import java.util.List;

import io.realm.Realm;


public class RealmHelper {

    public static long getNextId(Realm realm,Class clazz){
        Number maxValue = realm.where(clazz).max("id");
        return (long) ((maxValue != null) ? maxValue.intValue() + 1 : 0);
    }
    public static void insertBook(Realm realm, final Book book){
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(book);
            }
        });
    }
    public static Book getBookById(Realm realm,int id){
        Book realmBook = realm.where(Book.class).equalTo("id",id).findFirst();
        if(realmBook==null)return null;
        return realm.copyFromRealm(realmBook);
    }
    public static List<Book> fetchInProgressBooks (Realm realm){
        return realm.where(Book.class).equalTo("isInProgress",true).findAll();

    }

    public static List<Book> fetchInFavoriteBooks (Realm realm){
        return realm.where(Book.class).equalTo("isInFavorite",true).findAll();
    }
}
