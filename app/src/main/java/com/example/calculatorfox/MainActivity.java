package com.example.calculatorfox;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.calculatorfox.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    int secretBlogCounter;
    private ActivityMainBinding binding;
    StringBuilder textBuilder = new StringBuilder();
    char operation;
    double value1, value2, finalResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });


        binding.zeroBtn.setOnClickListener(v -> onClickNumb(binding.zeroBtn));
        binding.oneBtn.setOnClickListener(v -> onClickNumb(binding.oneBtn));
        binding.twoBtn.setOnClickListener(v -> onClickNumb(binding.twoBtn));
        binding.threeBtn.setOnClickListener(v -> onClickNumb(binding.threeBtn));
        binding.forBtn.setOnClickListener(v -> onClickNumb(binding.forBtn));
        binding.fiveBtn.setOnClickListener(v -> onClickNumb(binding.fiveBtn));
        binding.sixBtn.setOnClickListener(v -> onClickNumb(binding.sixBtn));
        binding.sevenBtn.setOnClickListener(v -> onClickNumb(binding.sevenBtn));
        binding.eightBtn.setOnClickListener(v -> onClickNumb(binding.eightBtn));
        binding.nineBtn.setOnClickListener(v -> onClickNumb(binding.nineBtn));
        binding.removeOneBtn.setOnClickListener(v -> onClickNumb(binding.removeOneBtn));
        binding.removeAllBtn.setOnClickListener(v -> onClickNumb(binding.removeAllBtn));
        binding.pointBtn.setOnClickListener(v -> onClickNumb(binding.pointBtn));


        binding.secretBtn.setOnClickListener(v -> onMathNumbClicked(binding.secretBtn));
        binding.divideBtn.setOnClickListener(v -> onMathNumbClicked(binding.divideBtn));
        binding.multiplicationBtn.setOnClickListener(v -> onMathNumbClicked(binding.multiplicationBtn));
        binding.substractionBtn.setOnClickListener(v -> onMathNumbClicked(binding.substractionBtn));
        binding.addBtn.setOnClickListener(v -> onMathNumbClicked(binding.addBtn));


        binding.equalsBtn.setOnClickListener(v -> onEqualsClicked(binding.equalsBtn));


    }

    private void onClickNumb(View view) {
        StringBuilder valueBuilder = new StringBuilder(binding.numbsView.getText().toString());
        ImageView imageView = (ImageView) view;


        if (imageView.getId() == R.id.remove_all_btn) {
            valueBuilder.setLength(0);
            value1 = 0;
            value2 = 0;
            finalResult = 0;
            textBuilder.setLength(0);

        } else if (imageView.getId() == R.id.remove_one_btn) {
            if (valueBuilder.length() > 0) {
                valueBuilder.deleteCharAt(valueBuilder.length() - 1);
            }
            if (textBuilder.length() > 0) {
                textBuilder.deleteCharAt(textBuilder.length() - 1);
            }


            if (textBuilder.toString().contains("=")) {
                if (!binding.numbsView.toString().isEmpty()) {
                    valueBuilder.setLength(0);

                    textBuilder.setLength(0);
                }
            }

        } else if (imageView.getId() == R.id.point_btn) {
            if (!valueBuilder.toString().contains(".")) {
                if (textBuilder.length() > 0) {
                    textBuilder.append(imageView.getTag().toString());
                    valueBuilder.append(imageView.getTag().toString());
                }
            }
        } else {
            valueBuilder.append(imageView.getTag().toString());
            textBuilder.append(imageView.getTag().toString());
        }
        binding.finallyResult.setText(textBuilder.toString());
        binding.numbsView.setText(valueBuilder.toString());


    }

    private void onMathNumbClicked(View view) {
        ImageView operationBtn = (ImageView) view;
        operation = operationBtn.getTag().toString().charAt(0);

        if (!binding.numbsView.getText().toString().isEmpty()) {
            String value = binding.numbsView.getText().toString();
            value1 = Double.parseDouble(value);
            textBuilder.append(" " + operation + " ");
            binding.finallyResult.setText(textBuilder.toString());
            binding.numbsView.setText("");

        }


    }

    private void onEqualsClicked(View view) {

        if (!binding.numbsView.getText().toString().isEmpty()) {
            String value = binding.numbsView.getText().toString();
            value2 = Double.parseDouble(value);
            switch (operation) {
                case '+':
                    finalResult = value1 + value2;
                    break;
                case '-':
                    finalResult = value1 - value2;
                    break;
                case '%':
                    finalResult = value1 % value2;
                    break;
                case '×':
                    finalResult = value1 * value2;
                    break;
                case '÷':
                    try {
                        finalResult = value1 / value2;
                    } catch (ArithmeticException e) {
                        binding.numbsView.setText("0");
                        binding.finallyResult.setText("0");
                    }

                    break;
            }
        }

        //textBuilder.append(" = " + finalResult);

        binding.numbsView.setText(String.valueOf(finalResult));

        binding.finallyResult.setText(String.valueOf(textBuilder));


        binding.equalsBtn.setOnClickListener(v -> {
            ++secretBlogCounter;
            if (secretBlogCounter == 3){
                Toast.makeText(this, "Secret Blog", Toast.LENGTH_SHORT).show();
                secretBlogCounter = 0;
            }


        });
    }
}