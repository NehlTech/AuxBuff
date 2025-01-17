package com.cryptech.demoapp.db;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.cryptech.demoapp.adapter.CategoryAdapter;
import com.cryptech.demoapp.adapter.HomePageAdapter;
import com.cryptech.demoapp.model.CategoryModel;
import com.cryptech.demoapp.model.HomePageModel;
import com.cryptech.demoapp.model.HorizontalScrollProductModel;
import com.cryptech.demoapp.model.SliderModel;
import com.cryptech.demoapp.model.WishListModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Dbqueries {

   public static FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    public static List<CategoryModel> categoryModelList = new ArrayList<>();

//    public static List<HomePageModel> homePageModelList = new ArrayList<>();

    public static List<List<HomePageModel>> lists = new ArrayList<>();
    public static List<String> loadedCategoriesNames = new ArrayList<>();


    public static void loadCategories(final CategoryAdapter categoryAdapter, final Context context) {

        firebaseFirestore.collection("CATEGORIES")
                .orderBy("index").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                categoryModelList.add(new CategoryModel(documentSnapshot.get("icon").toString(), documentSnapshot.get("categoryName").toString()));
                            }
                            categoryAdapter.notifyDataSetChanged();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    public static void loadFragmentData(final HomePageAdapter homePageAdapter, final Context context, final int index, String categoryName) {

        firebaseFirestore.collection("CATEGORIES")
                .document(categoryName.toUpperCase())
                .collection("TOP_DEALS")
                .orderBy("index")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {

                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {

                                if (((long) documentSnapshot.get("view_type") == 0)) {

                                    List<SliderModel> sliderModelList = new ArrayList<>();
                                    long no_of_banners = (long)documentSnapshot.get("no_of_banners");
                                    for (long x = 1; x < no_of_banners + 1; x++) {
                                        sliderModelList.add(new SliderModel(documentSnapshot.get("banner_"+x).toString(),
                                                documentSnapshot.get("banner_"+x+"_bkgrd").toString()));
                                    }
                                    lists.get(index).add(new HomePageModel(0,sliderModelList));

                                } else if ((long) documentSnapshot.get("view_type") == 1) {

                                    lists.get(index).add(new HomePageModel(1,
                                            documentSnapshot.get("strip_add_banner").toString(),documentSnapshot.get("strip_add_bkground").toString()));
                                } else if ((long) documentSnapshot.get("view_type") == 2) {


                                    List<WishListModel> viewAllProductList = new ArrayList<>();
                                    List<HorizontalScrollProductModel> horizontalScrollProductModelList = new ArrayList<>();
                                    long no_of_products = (long)documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        horizontalScrollProductModelList.add(new HorizontalScrollProductModel(documentSnapshot.get("product_id_"+x).toString(),
                                                documentSnapshot.get("product_image_"+x).toString(),
                                                documentSnapshot.get("product_title_"+x).toString(),
                                                documentSnapshot.get("product_subtitle_"+x).toString(),
                                                documentSnapshot.get("product_price_"+x).toString()));

                                        viewAllProductList.add(new WishListModel(documentSnapshot.get("product_image_"+x).toString(),
                                                (long)documentSnapshot.get("free_coupons_"+x),
                                                (long)documentSnapshot.get("total_rating_"+x),
                                                documentSnapshot.get("product_full_title_"+x).toString(),
                                                documentSnapshot.get("average_rating_"+x).toString(),
                                                documentSnapshot.get("product_price_"+x).toString(),
                                                documentSnapshot.get("reduced_price_"+x).toString(),
                                                (boolean)documentSnapshot.get("COD_"+x)));

                                    }
                                    lists.get(index).add(new HomePageModel(2,documentSnapshot.get("layout_title").toString(),
                                            documentSnapshot.get("layout_background").toString(),horizontalScrollProductModelList, viewAllProductList));

                                } else if ((long) documentSnapshot.get("view_type") == 3) {
                                    List<HorizontalScrollProductModel> GridlayoutModelList = new ArrayList<>();
                                    long no_of_products = (long)documentSnapshot.get("no_of_products");
                                    for (long x = 1; x < no_of_products + 1; x++) {
                                        GridlayoutModelList.add(new HorizontalScrollProductModel(documentSnapshot.get("product_id_"+x).toString(),
                                                documentSnapshot.get("product_image_"+x).toString(),
                                                documentSnapshot.get("product_title_"+x).toString(),
                                                documentSnapshot.get("product_subtitle_"+x).toString(),
                                                documentSnapshot.get("product_price_"+x).toString()));
                                    }
                                    lists.get(index).add(new HomePageModel(3,documentSnapshot.get("layout_title").toString(),
                                            documentSnapshot.get("layout_backgrd").toString(),GridlayoutModelList));
                                }


                            }
                            homePageAdapter.notifyDataSetChanged();

                        } else {
                            String error = task.getException().getMessage();
                            Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
