package com.example.myapplication.order.productdetail;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myapplication.Constant;
import com.example.myapplication.R;
import com.example.myapplication.data.order.DataItem;
import com.example.myapplication.data.order.VariantsItem;
import com.example.myapplication.order.FormatPrice;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DetailDialogFragment extends DialogFragment {
    private ImageView imgViewProduct;
    private TextView tvName;
    private TextView tvDescription, tvSeeMore;
    private int isCheck = 0;
    private TextView tvPrice;
    private Button btnAddToCart;
    private ImageView imgViewDecrease;
    private ImageView imgViewIncrease;
    private ImageView imgViewClose;
    private DataItem dataItem;
    private TextView tvQuality;
    private int count = 1;
    private String size = "";
    private RadioButton radSmall, radMedium, radLarge;
    private List<VariantsItem> varList;
    private int price;
    private FormatPrice formatPrice = new FormatPrice ();

    public static DetailDialogFragment newInstance(DataItem item) {
        DetailDialogFragment fragment = new DetailDialogFragment ();
        Bundle bundle = new Bundle ();
        bundle.putSerializable ("Product", item);
        fragment.setArguments (bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach (context);
        if (getArguments () != null) {
            dataItem = (DataItem) getArguments ().getSerializable ("Product");
            varList = dataItem.getVariants ();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate (R.layout.dialog_fragment_detail_product, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setStyle (DialogFragment.STYLE_NORMAL, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated (view, savedInstanceState);
        initViewId (view);
        initEvent ();
        initData ();
    }

    private void initData() {
        Glide.with (getContext ()).load (dataItem.getImage ()).into (imgViewProduct);
        tvName.setText (dataItem.getProductName ());
        tvDescription.setText (dataItem.getDescription ());
        tvPrice.setText (String.format ("+%s", formatPrice.formatPrice (Integer.parseInt (String.valueOf (dataItem.getBasePrice ())))));
        List<VariantsItem> variantsItems = dataItem.getVariants ();
        for (VariantsItem item : variantsItems) {
            if (item.getVal ().toLowerCase ().equals ("nhỏ")) {
                radSmall.setVisibility (View.VISIBLE);
            }
            if (item.getVal ().toLowerCase ().equals ("vừa")) {
                radMedium.setVisibility (View.VISIBLE);
            }
            if (item.getVal ().toLowerCase ().equals ("lớn")) {
                radLarge.setVisibility (View.VISIBLE);
            }
        }

        switch (dataItem.getVariants().get(0).getVal().toLowerCase()) {
            case "nhỏ":
                radSmall.setChecked (true);
                break;
            case "vừa":
                radMedium.setChecked (true);
                break;
            case "lớn":
                radLarge.setChecked (true);
                break;
        }
    }
    private boolean valOption(String val) {
        for (VariantsItem variantsItem : varList) {
            if (variantsItem.getVal ().equalsIgnoreCase (val)) return true;
        }
        return false;
    }
    private void initEvent() {
        imgViewClose.setOnClickListener (v -> DetailDialogFragment.this.dismiss ());
        tvDescription.setOnClickListener (v -> {
            showMore ();
        });
        tvSeeMore.setOnClickListener (v -> showMore ());
        imgViewDecrease.setOnClickListener (v -> {
            if (count > 1) {
                count--;
            } else {
                count = 1;
            }
            tvQuality.setText (String.valueOf (count));
            formatPrice (size);
        });
        imgViewIncrease.setOnClickListener (v -> {
            if (count < 100)
                count++;
            else
                count = 99;
            tvQuality.setText (String.valueOf (count));
            formatPrice (size);
        });
        radSmall.setOnCheckedChangeListener ((buttonView, isChecked) -> {
            if (isChecked) {
                size = radSmall.getText ().toString ();
                formatPrice (size);
            }
        });
        radMedium.setOnCheckedChangeListener ((buttonView, isChecked) -> {
            if (isChecked) {
                size = radMedium.getText ().toString ();
                formatPrice (size);
            }
        });
        radLarge.setOnCheckedChangeListener ((buttonView, isChecked) -> {
            if (isChecked) {
                size = radLarge.getText ().toString ();
                formatPrice (size);
            }
        });
        btnAddToCart.setOnClickListener(view->{
           this.dismiss();
        });
    }

    private void showMore() {
        if (isCheck == 0) {
            tvDescription.setMaxLines (30);
            tvSeeMore.setVisibility (View.INVISIBLE);
            isCheck = 1;
        } else {
            tvDescription.setMaxLines (3);
            tvSeeMore.setVisibility (View.VISIBLE);
            isCheck = 0;
        }
    }

    private void initViewId(View view) {
        imgViewProduct = view.findViewById (R.id.img_btn_product);
        tvName = view.findViewById (R.id.tv_name_product);
        tvDescription = view.findViewById (R.id.tv_description);
        tvSeeMore = view.findViewById (R.id.tv_see_more);
        tvPrice = view.findViewById (R.id.tv_price);
        btnAddToCart = view.findViewById (R.id.btn_add_cart);
        imgViewDecrease = view.findViewById (R.id.img_btn_decrease);
        imgViewIncrease = view.findViewById (R.id.img_btn_increase);
        imgViewClose = view.findViewById (R.id.img_btn_close);
        tvQuality = view.findViewById (R.id.tv_quatity);
        radSmall = view.findViewById (R.id.radio_small);
        radSmall.setVisibility (View.GONE);
        radMedium = view.findViewById (R.id.radio_medium);
        radMedium.setVisibility (View.GONE);
        radLarge = view.findViewById (R.id.radio_large);
        radLarge.setVisibility (View.GONE);
    }

    private int getPrice(String size) {
        for (VariantsItem item : varList) {
            if (item.getVal ().equalsIgnoreCase (size)) {
                price = item.getPrice ();
            }
        }
        return price;
    }
    private void formatPrice(String size) {
        tvPrice.setText (String.format ("+%s", formatPrice.formatPrice (Integer.parseInt (String.valueOf (count * ("".equalsIgnoreCase (size)
                ? dataItem.getBasePrice () : getPrice (size)))))));
    }
}
