// Generated by view binder compiler. Do not edit!
package com.example.sifflet0.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sifflet0.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetailStadeBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final Button buttonAddLigue;

  @NonNull
  public final Button buttonMap;

  @NonNull
  public final Button buttonShowligue;

  @NonNull
  public final TextView descriptionStade;

  @NonNull
  public final TextView detailsNomStade;

  @NonNull
  public final ImageView imageView3;

  @NonNull
  public final TextView textView2;

  @NonNull
  public final TextView textView5;

  private ActivityDetailStadeBinding(@NonNull ConstraintLayout rootView,
      @NonNull Button buttonAddLigue, @NonNull Button buttonMap, @NonNull Button buttonShowligue,
      @NonNull TextView descriptionStade, @NonNull TextView detailsNomStade,
      @NonNull ImageView imageView3, @NonNull TextView textView2, @NonNull TextView textView5) {
    this.rootView = rootView;
    this.buttonAddLigue = buttonAddLigue;
    this.buttonMap = buttonMap;
    this.buttonShowligue = buttonShowligue;
    this.descriptionStade = descriptionStade;
    this.detailsNomStade = detailsNomStade;
    this.imageView3 = imageView3;
    this.textView2 = textView2;
    this.textView5 = textView5;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetailStadeBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetailStadeBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detail_stade, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetailStadeBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonAddLigue;
      Button buttonAddLigue = ViewBindings.findChildViewById(rootView, id);
      if (buttonAddLigue == null) {
        break missingId;
      }

      id = R.id.buttonMap;
      Button buttonMap = ViewBindings.findChildViewById(rootView, id);
      if (buttonMap == null) {
        break missingId;
      }

      id = R.id.buttonShowligue;
      Button buttonShowligue = ViewBindings.findChildViewById(rootView, id);
      if (buttonShowligue == null) {
        break missingId;
      }

      id = R.id.descriptionStade;
      TextView descriptionStade = ViewBindings.findChildViewById(rootView, id);
      if (descriptionStade == null) {
        break missingId;
      }

      id = R.id.detailsNomStade;
      TextView detailsNomStade = ViewBindings.findChildViewById(rootView, id);
      if (detailsNomStade == null) {
        break missingId;
      }

      id = R.id.imageView3;
      ImageView imageView3 = ViewBindings.findChildViewById(rootView, id);
      if (imageView3 == null) {
        break missingId;
      }

      id = R.id.textView2;
      TextView textView2 = ViewBindings.findChildViewById(rootView, id);
      if (textView2 == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      return new ActivityDetailStadeBinding((ConstraintLayout) rootView, buttonAddLigue, buttonMap,
          buttonShowligue, descriptionStade, detailsNomStade, imageView3, textView2, textView5);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
