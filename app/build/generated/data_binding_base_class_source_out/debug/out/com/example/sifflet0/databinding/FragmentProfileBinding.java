// Generated by view binder compiler. Do not edit!
package com.example.sifflet0.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.sifflet0.R;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class FragmentProfileBinding implements ViewBinding {
  @NonNull
  private final FrameLayout rootView;

  @NonNull
  public final TextView profileEmail;

  @NonNull
  public final CircleImageView profileImage;

  @NonNull
  public final TextView profileNom;

  @NonNull
  public final TextView profilePrenom;

  @NonNull
  public final ConstraintLayout profilePrenom2;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textView7;

  @NonNull
  public final TextView textView8;

  private FragmentProfileBinding(@NonNull FrameLayout rootView, @NonNull TextView profileEmail,
      @NonNull CircleImageView profileImage, @NonNull TextView profileNom,
      @NonNull TextView profilePrenom, @NonNull ConstraintLayout profilePrenom2,
      @NonNull TextView textView6, @NonNull TextView textView7, @NonNull TextView textView8) {
    this.rootView = rootView;
    this.profileEmail = profileEmail;
    this.profileImage = profileImage;
    this.profileNom = profileNom;
    this.profilePrenom = profilePrenom;
    this.profilePrenom2 = profilePrenom2;
    this.textView6 = textView6;
    this.textView7 = textView7;
    this.textView8 = textView8;
  }

  @Override
  @NonNull
  public FrameLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentProfileBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_profile, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentProfileBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.profileEmail;
      TextView profileEmail = ViewBindings.findChildViewById(rootView, id);
      if (profileEmail == null) {
        break missingId;
      }

      id = R.id.profileImage;
      CircleImageView profileImage = ViewBindings.findChildViewById(rootView, id);
      if (profileImage == null) {
        break missingId;
      }

      id = R.id.profileNom;
      TextView profileNom = ViewBindings.findChildViewById(rootView, id);
      if (profileNom == null) {
        break missingId;
      }

      id = R.id.profilePrenom;
      TextView profilePrenom = ViewBindings.findChildViewById(rootView, id);
      if (profilePrenom == null) {
        break missingId;
      }

      id = R.id.profilePrenom2;
      ConstraintLayout profilePrenom2 = ViewBindings.findChildViewById(rootView, id);
      if (profilePrenom2 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textView7;
      TextView textView7 = ViewBindings.findChildViewById(rootView, id);
      if (textView7 == null) {
        break missingId;
      }

      id = R.id.textView8;
      TextView textView8 = ViewBindings.findChildViewById(rootView, id);
      if (textView8 == null) {
        break missingId;
      }

      return new FragmentProfileBinding((FrameLayout) rootView, profileEmail, profileImage,
          profileNom, profilePrenom, profilePrenom2, textView6, textView7, textView8);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}