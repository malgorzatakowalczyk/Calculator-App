package com.example.calculator1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;

public class SimpleCalculator extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, minus, plus, divide, multiply, ac, cce, changeSign, dot, equal;
    TextView operation, result;
    Double res = 0.0, previousValue = 0.0;
    String currentValue = "";
    char lastSign = 'a';
    boolean clickedOneTime = false, brackets = false;
    boolean validateSign = false, validateDot = false, validateDot2 = true, validateChangeSign = false, validateZero = true, validateChangeSign2 = false;
    int lenOfNumber=0;

    void count() {
        clickedOneTime = false;
        if (lastSign == '+') {
            res = (previousValue + Double.parseDouble(currentValue));
            previousValue = res;
            currentValue = "";
        } else if (lastSign == '-') {
            res = (previousValue - Double.parseDouble(currentValue));
            previousValue = res;
            currentValue = "";
        } else if (lastSign == '*') {
            res = (previousValue * Double.parseDouble(currentValue));
            previousValue = res;
            currentValue = "";
        } else if (lastSign == '/') {
            if (currentValue.equals("0")) {
                Toast.makeText(SimpleCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
            } else {
                res = (previousValue / Double.parseDouble(currentValue));
                previousValue = res;
                currentValue = "";
            }
        } else {
            previousValue = Double.parseDouble(currentValue);
            currentValue = "";
        }

    }

    void clear() {
        operation.setText("");
        result.setText("");
        lastSign = 'a';
        previousValue = 0.0;
        currentValue = "";
        res = 0.0;
        clickedOneTime = false;
        validateSign = false;
        validateDot = false;
        validateDot2 = true;
        validateChangeSign = false;
        validateZero = true;
        brackets = false;
        validateChangeSign2 = false;
        lenOfNumber=0;
    }

    void addNumber(char num) {
        if(operation.length()>=33 || lenOfNumber>16)
        {
            Toast.makeText(SimpleCalculator.this, "Za długa operacja", Toast.LENGTH_LONG).show();
        }
        else {
            if (validateChangeSign2 == false) {
                String temp = operation.getText().toString();
                currentValue += num;
                operation.setText(operation.getText().toString() + num);
                clickedOneTime = false;
                validateSign = true;
                validateDot = true;
                validateChangeSign = true;
                lenOfNumber++;
            } else
                Toast.makeText(SimpleCalculator.this, "Błędna operacja", Toast.LENGTH_LONG).show();
        }
    }

    void operation(char op) {
        if (validateSign == true) {
            operation.setText(operation.getText().toString() + op);
            count();
            lastSign = op;
            validateSign = false;
            validateDot = false;
            validateDot2 = true;
            validateChangeSign = false;
            validateChangeSign2 = false;
            lenOfNumber=0;
        } else {
            Toast.makeText(SimpleCalculator.this, "Operacja nie moze byc wykonana", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_calculator);

        operation = findViewById(R.id.operation);
        result = findViewById(R.id.result);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        minus = (Button) findViewById(R.id.minus);
        plus = (Button) findViewById(R.id.plus);
        divide = (Button) findViewById(R.id.divide);
        multiply = (Button) findViewById(R.id.multiply);
        ac = (Button) findViewById(R.id.ac);
        cce = (Button) findViewById(R.id.cce);
        changeSign = (Button) findViewById(R.id.changeSign);
        dot = (Button) findViewById(R.id.dot);
        equal = (Button) findViewById(R.id.equaly);


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('1');
            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('2');
            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('3');
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('4');
            }
        });

        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('5');
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('6');
            }
        });

        seven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('7');
            }
        });

        eight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('8');
            }
        });

        nine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('9');
            }
        });

        zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNumber('0');
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation('+');
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation('-');
            }
        });

        multiply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation('*');
            }
        });

        divide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation('/');
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (operation.length() >= 33 || lenOfNumber>16) {
                    Toast.makeText(SimpleCalculator.this, "Za długa operacja", Toast.LENGTH_LONG).show();
                } else {
                    if (validateChangeSign2 == false) {
                        if (validateDot == true && validateDot2 == true) {
                            clickedOneTime = false;
                            operation.setText(operation.getText().toString() + ".");
                            currentValue += ".";
                            validateDot = false;
                            validateDot2 = false;
                            validateChangeSign = false;
                            validateSign = false;
                            lenOfNumber++;
                        } else {
                            Toast.makeText(SimpleCalculator.this, "Kropka nie moze zostać wstawiona", Toast.LENGTH_LONG).show();
                        }
                    } else
                        Toast.makeText(SimpleCalculator.this, "Błędna operacja", Toast.LENGTH_LONG).show();
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedOneTime = false;
                if (lastSign == '+') {
                    if (currentValue.equals("")) {
                        res = previousValue;
                        result.setText("= " + res.toString());
                    } else {
                        res = previousValue + Double.parseDouble(currentValue);
                        BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                        b = b.stripTrailingZeros();
                        result.setText("= " + b.toString());
                    }
                } else if (lastSign == '-') {
                    if (currentValue.equals("")) {
                        res = previousValue;
                        result.setText("= " + res.toString());
                    } else {
                        res = previousValue - Double.parseDouble(currentValue);
                        BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                        b = b.stripTrailingZeros();
                        result.setText("= " + b.toString());
                    }
                } else if (lastSign == '*') {
                    if (currentValue.equals("")) {
                        res = previousValue;
                        result.setText("= " + res.toString());
                    } else {
                        res = previousValue * Double.parseDouble(currentValue);
                        BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                        b = b.stripTrailingZeros();
                        result.setText("= " + b.toString());
                    }
                } else if (lastSign == '/') {
                    if (currentValue.equals("")) {
                        res = previousValue;
                        result.setText("= " + res.toString());
                    } else {
                        if (currentValue.equals("0")) {
                            Toast.makeText(SimpleCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
                        } else {
                            res = previousValue / Double.parseDouble(currentValue);
                            BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                            b = b.stripTrailingZeros();
                            result.setText("= " + b.toString());
                        }
                    }
                } else {
                    if (validateDot == false)
                        Toast.makeText(SimpleCalculator.this, "Błędna liczba", Toast.LENGTH_LONG).show();
                    else result.setText("= " + currentValue);
                }
            }
        });

        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });

        cce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickedOneTime == true) {
                    clear();

                } else {
                    if (currentValue != "") {
                        String temp = currentValue.substring(0, currentValue.length() - 1);
                        currentValue = temp;
                        temp = operation.getText().toString();
                        operation.setText(temp.substring(0, temp.length() - 1));
                        lenOfNumber--;
                    }
                    clickedOneTime = true;
                }
            }
        });


        changeSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateChangeSign2 = true;
                if (validateChangeSign == true) {
                    clickedOneTime = false;
                    if (currentValue.startsWith("-")) {
                        currentValue = currentValue.substring(1);
                        if (operation.length() == currentValue.length() + 1)
                            operation.setText(currentValue);
                        else {
                            if (brackets == false) {
                                String temp = operation.getText().toString();
                                temp = temp.substring(0, temp.length() - currentValue.length() - 1) + currentValue;
                                operation.setText(temp);
                            } else {
                                String temp = operation.getText().toString();
                                temp = temp.substring(0, temp.length() - currentValue.length() - 3) + currentValue;
                                operation.setText(temp);
                            }
                        }
                    } else {
                        String temp = operation.getText().toString();
                        if (temp.length() == currentValue.length()) {
                            currentValue = "-" + currentValue;
                            temp = currentValue;
                            operation.setText(temp);
                        } else {
                            currentValue = "-" + currentValue;
                            temp = temp.substring(0, temp.length() - currentValue.length() + 1) + "(" + currentValue + ")";
                            operation.setText(temp);
                            brackets = true;
                        }
                    }
                } else {
                    Toast.makeText(SimpleCalculator.this, "Nie mozna zmienic znaku", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("operation", operation.getText().toString());
        outState.putString("result", result.getText().toString());
        outState.putString("currentValue", currentValue);
        outState.putDouble("res", res);
        outState.putDouble("previousValue", previousValue);
        outState.putChar("lastSign", lastSign);
        outState.putBoolean("clickedOneTime", clickedOneTime);
        outState.putBoolean("brackets", brackets);
        outState.putBoolean("validateSign", validateSign);
        outState.putBoolean("validateDot", validateDot);
        outState.putBoolean("validateDot2", validateDot2);
        outState.putBoolean("validateChangeSign", validateChangeSign);
        outState.putBoolean("validateChangeSign2", validateChangeSign2);
        outState.putBoolean("validateZero", validateZero);
        outState.putInt("lenOfNumber",lenOfNumber);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        operation.setText(savedInstanceState.getString("operation"));
        result.setText(savedInstanceState.getString("result"));
        currentValue = savedInstanceState.getString("currentValue");
        res = savedInstanceState.getDouble("res");
        previousValue = savedInstanceState.getDouble("previousValue");
        lastSign = savedInstanceState.getChar("lastSign");
        clickedOneTime = savedInstanceState.getBoolean("clickedOneTime");
        brackets = savedInstanceState.getBoolean("brackets");
        validateSign = savedInstanceState.getBoolean("validateSign");
        validateDot = savedInstanceState.getBoolean("validateDot");
        validateDot2 = savedInstanceState.getBoolean("validateDot2");
        validateChangeSign = validateDot = savedInstanceState.getBoolean("validateChangeSign");
        validateChangeSign2 = savedInstanceState.getBoolean("validateChangeSign2");
        validateZero = savedInstanceState.getBoolean("validateZero");
        lenOfNumber=savedInstanceState.getInt("lenOfNumber");
    }
}

