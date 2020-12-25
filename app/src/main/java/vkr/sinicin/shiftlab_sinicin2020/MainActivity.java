package vkr.sinicin.shiftlab_sinicin2020;
// я лох
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView nName, nSurname, nData, nPassword, nAccPassword;
    //private String Name,Surname,Bithday,Password,AccPassword;
    private Button nRegistButton;

    Calendar nCalendar;
    DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Меняем шапку
        getSupportActionBar().setTitle("Регистрация");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#009b76")));

        setContentView(R.layout.activity_main);

        // Инициализация переменных
        nName = findViewById(R.id.nameText);
        nSurname = findViewById(R.id.surnameText);
        nData = findViewById(R.id.dataText);
        nPassword = findViewById(R.id.passwordText);
        nAccPassword = findViewById(R.id.passwordAcceptText);
        // Кнопка регистрации
        nRegistButton = findViewById(R.id.registrationButton);
        // Для тестов нужна была
        nData.setEnabled(true);
        nRegistButton.setEnabled(false);

        // DataPicker
        nData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DataPicker
                nCalendar = Calendar.getInstance();
                int day = nCalendar.get(Calendar.DAY_OF_MONTH);
                int month = nCalendar.get(Calendar.MONTH);
                int year = nCalendar.get(Calendar.YEAR);

                dpd = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int mYear, int mMonth, int mDay) {
                        nData.setText(mDay + "/" + (mMonth+1) + "/" + mYear);

                    }
                },day,month,year);
                dpd.show();
            }
        });
        // Устанавливаем условие на динамичное обновление полей
        nName.addTextChangedListener(updateButton);
        nSurname.addTextChangedListener(updateButton);
        nData.addTextChangedListener(updateButton);
        nPassword.addTextChangedListener(updateButton);
        nAccPassword.addTextChangedListener(updateButton);

        nRegistButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Считывает с техтвью имена и проверяем их
                final String Name = nName.getText().toString();
                final String Surname = nSurname.getText().toString();
                final String Bithday = nData.getText().toString();
                final String Password = nPassword.getText().toString();
                final String AccPassword = nAccPassword.getText().toString();

                // Проверка на то, что пользователь ввел пустое имя
                if (TextUtils.isEmpty(Name)) {
                    nName.setError("Введите свое имя.");
                    return;
                }

                if (Name.length() <= 1) {
                    nName.setError("Имя должно быть > 1 символа.");
                    return;
                }

                // Проверка на то, что пользователь ввел свою фамилию
                if (TextUtils.isEmpty(Surname)) {
                    nSurname.setError("Введите свою фамилию.");
                    return;
                }

                if (Surname.length() <= 2) {
                    nSurname.setError("Введите свою фамилию.");
                    return;
                }

                // Проверка на то, что пользователь ввел дату рождения
                if (TextUtils.isEmpty(Bithday))
                {
                    nData.setError("Укажите вашу дату рождения.");
                    return;
                }

                // Проверка на то, что пользователь ввел пароль
                if (TextUtils.isEmpty(Password)) {
                    nPassword.setError("Введите свой пароль.");
                    return;
                }

                if (Password.length() <= 5) {
                    nPassword.setError("Пароль должен быть > 5 символов.");
                    return;
                }

                // Проверка на то, что пользователь ввел подтв. пароля
                if (TextUtils.isEmpty(AccPassword)) {
                    nAccPassword.setError("Введите свой пароль еще раз.");
                    return;
                }

                if (AccPassword.length() <= 5) {
                    nAccPassword.setError("Пароль должен быть > 5 символов.");
                    return;
                }

                // Проверка на то, что пароли совпадают
                if (!AccPassword.equals(Password)) {
                    nAccPassword.setError("Пароли не совпадают.");
                    return;
                }
                // Вызываем второй экран и передаем туда параметр с именем
                Intent nNext = new Intent(MainActivity.this,Welcome_page.class);
                nNext.putExtra("name_user",Name);
                startActivity(nNext);
                finish();

            }
        });


    }

    // Для динамичного изменения кнопки регистрации
    private TextWatcher updateButton = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            String userNameInput = nName.getText().toString().trim();
            String surnameInput = nSurname.getText().toString().trim();
            String dataInput = nData.getText().toString().trim();
            String passInput = nPassword.getText().toString().trim();
            String acsPassInput = nAccPassword.getText().toString().trim();

            nRegistButton.setEnabled(!userNameInput.isEmpty() && !surnameInput.isEmpty()
                    && !dataInput.isEmpty() && !passInput.isEmpty() && !acsPassInput.isEmpty());

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };
}
