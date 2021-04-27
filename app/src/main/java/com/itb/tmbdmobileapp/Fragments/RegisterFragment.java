package com.itb.tmbdmobileapp.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.itb.tmbdmobileapp.R;


public class RegisterFragment extends Fragment implements View.OnClickListener {
    MaterialButton login, register;
    private TextInputLayout layoutPassword, layoutUsername, layoutEmail;
    private TextInputEditText editTextPassword, editTextUsername, editTextEmail;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login = view.findViewById(R.id.register_button_login);
        register = view.findViewById(R.id.register_button_register);
        layoutPassword = view.findViewById(R.id.register_textInputLayout_password);
        layoutUsername = view.findViewById(R.id.register_textInputLayout_username);
        layoutEmail = view.findViewById(R.id.register_textInputLayout_email);
        editTextPassword = view.findViewById(R.id.register_textInputEditText_password);
        editTextUsername = view.findViewById(R.id.register_textInputEditText_username);
        editTextEmail = view.findViewById(R.id.register_textInputEditText_email);

        register.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        boolean flag = true;
        ResetearInputLayout();
        if (v.getId() == R.id.register_button_register) flag = canGoToLogin();

        if (flag) {
            NavDirections navDirections = RegisterFragmentDirections.registerToLogin();
            Navigation.findNavController(v).navigate(navDirections);
        }
    }

    public boolean canGoToLogin() {
        boolean passwordCheck = !editTextPassword.getText().toString().equals("");
        boolean usernameCheck = !editTextUsername.getText().toString().equals("");
        boolean emailCheck = !editTextEmail.getText().toString().equals("");

        if (!passwordCheck) InputLayoutError(layoutPassword, "Introduce la Contraseña");
        if (!usernameCheck) InputLayoutError(layoutUsername, "Introduce el Usuario");
        if (!emailCheck) InputLayoutError(layoutEmail, "Introduce el email");

        return passwordCheck && usernameCheck && emailCheck;
    }

    private void InputLayoutError(TextInputLayout layout, String mensajeError) {
        layout.setEnabled(true);
        layout.setError(mensajeError);
    }

    private void ResetearInputLayout() {
        layoutUsername.setError("");
        layoutPassword.setError("");
        layoutEmail.setError("");
    }
}