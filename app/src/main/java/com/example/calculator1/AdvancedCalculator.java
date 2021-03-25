package com.example.calculator1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;

public class AdvancedCalculator extends AppCompatActivity {


    Button one, two, three, four, five, six, seven, eight, nine, zero, minus, plus, divide, multiply, ac, cce, changeSign, dot, equal, sin, cos, tan, ln, sqrt, squared, pow, log, percent;
    TextView operation, result;
    String currentValue = "";
    Double previousValue = 0.0, powValue = 0.0, res = 0.0;
    char lastSign = 'a', tempSign = 'a';
    boolean clickedOneTime = false, validateSign = false, validateDot = false, validateDot2 = true, validateChangeSign = false, validateZero = true, brackets = false, validateChangeSign2 = false;
    int error=0, lenOfNumber=0;

    void count() {
        clickedOneTime = false;
        if (tempSign == 'p') {
            if (!currentValue.equals("")) {
                if(powValue==0 &&  Double.parseDouble(currentValue)<=0)
                {
                    error=3;
                    Toast.makeText(AdvancedCalculator.this, "Nie można obliczyć potęgi", Toast.LENGTH_LONG).show();
                }
                else previousValue = Math.pow(powValue, Double.parseDouble(currentValue));
            }
        }
        if (lastSign == '+') {
            res = (previousValue + Double.parseDouble(currentValue));
            previousValue = res;
            currentValue = "";
            tempSign = 'a';
        } else if (lastSign == '-') {
            res = (previousValue - Double.parseDouble(currentValue));
            previousValue = res;
            currentValue = "";
            tempSign = 'a';
        } else if (lastSign == '*') {
            res = (previousValue * Double.parseDouble(currentValue));
            previousValue = res;
            currentValue = "";
            tempSign = 'a';
        } else if (lastSign == '/') {
            if (currentValue.equals("0")) {
                Toast.makeText(AdvancedCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
            } else {
                res = (previousValue / Double.parseDouble(currentValue));
                previousValue = res;
                currentValue = "";
                tempSign = 'a';
            }
        } else if (lastSign == 's' || lastSign == 'c' || lastSign == 't' || lastSign == 'n' || lastSign == 'q' || lastSign == 'g' || lastSign == 'k') {
            currentValue = "";
            tempSign = 'a';
        } else {
            if (tempSign != 'p') previousValue = Double.parseDouble(currentValue);
            currentValue = "";
            tempSign = 'a';
        }
    }

    void countFunction(String name) {
        lenOfNumber=0;
        double temp = 0;
        int lenCurrentValue = currentValue.length();
        if (!currentValue.equals("")) {
            temp = Double.parseDouble(currentValue);
        } else {
            currentValue = "0";
        }
        int lenOperation = operation.length();
        String show = operation.getText().toString();
        show = show.substring(0, lenOperation - lenCurrentValue);
        operation.setText(show + name + "(" + temp + ")");

        double degrees = Double.parseDouble(currentValue);
        double radians = Math.toRadians(degrees);
        if (lastSign != 'a') {
            if (name.equals("sin")) res = Math.sin(radians);
            else if (name.equals("cos")) res = Math.cos(radians);
            if (lastSign == '+') {
                res = res + previousValue;
            } else if (lastSign == '*') {
                res = res * previousValue;
            } else if (lastSign == '-') {
                res = previousValue - res;
            } else if (lastSign == '/') {
                if (res != 0) {
                    res = previousValue / res;
                } else {
                    Toast.makeText(AdvancedCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
                }
            }
        } else {
            if (name.equals("sin")) res = Math.sin(radians);
            else if (name.equals("cos")) res = Math.cos(radians);

        }
        previousValue = res;
        if (name.equals("sin")) lastSign = 's';
        else if (name.equals("cos")) lastSign = 'c';
    }

    void addNumber(char num) {
        if(operation.length()>=33 || lenOfNumber>16)
        {
            Toast.makeText(AdvancedCalculator.this, "Za długa operacja", Toast.LENGTH_LONG).show();
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
            }
            else
                Toast.makeText(AdvancedCalculator.this, "Błędna operacja", Toast.LENGTH_LONG).show();
        }
    }

    void clear() {
        operation.setText("");
        result.setText("");
        lastSign = 'a';
        tempSign = 'a';
        previousValue = 0.0;
        currentValue = "";
        res = 0.0;
        clickedOneTime = false;
        validateSign = false;
        validateDot = false;
        validateDot2 = true;
        validateChangeSign = false;
        validateZero = true;
        validateChangeSign2=false;
        lenOfNumber=0;
        error=0;
    }

    void operation(char op) {
        if (validateSign == true) {
            operation.setText(operation.getText().toString() + op);
            System.out.println("Plus");
            count();
            lastSign = op;
            validateSign = false;
            validateDot = false;
            validateDot2 = true;
            validateChangeSign = false;
            validateChangeSign2 = false;
        } else {
            Toast.makeText(AdvancedCalculator.this, "Operacja nie moze byc wykonana", Toast.LENGTH_LONG).show();
        }
    }
    void logarithmFun(String name)
    {
        lenOfNumber=0;
        double temp = 0;
        int lenCurrentValue = currentValue.length();
        if (currentValue.equals("")) {
            Toast.makeText(AdvancedCalculator.this, "Nie można wykonać operacji", Toast.LENGTH_LONG).show();

        } else {
            temp = Double.parseDouble(currentValue);
            int lenOperation = operation.length();
            String show = operation.getText().toString();
            show = show.substring(0, lenOperation - lenCurrentValue);
            operation.setText(show + name+"(" + temp + ")");

            double val = Double.parseDouble(currentValue);
            if (val > 0) {
                if (lastSign != 'a') {
                    if(name.equals("ln")) res = Math.log(val);
                    else if(name.equals("log")) res = Math.log10(val);

                    if (lastSign == '+') {
                        res = res + previousValue;
                    } else if (lastSign == '*') {
                        res = res * previousValue;
                    } else if (lastSign == '-') {
                        res = previousValue - res;
                    } else if (lastSign == '/') {
                        if (res != 0) {
                            res = previousValue / res;
                        } else {
                            Toast.makeText(AdvancedCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    if(name.equals("ln")) res = Math.log(val);
                    else if(name.equals("log")) res = Math.log10(val);
                }
                previousValue = res;
                lastSign = 'n';
            }
            else
            {
                Toast.makeText(AdvancedCalculator.this, "Nie można obliczyć logarytmu", Toast.LENGTH_LONG).show();
                error=2;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_calculator);
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
        sin = (Button) findViewById(R.id.sin);
        cos = (Button) findViewById(R.id.cos);
        tan = (Button) findViewById(R.id.tan);
        ln = (Button) findViewById(R.id.ln);
        sqrt = (Button) findViewById(R.id.sqrt);
        squared = (Button) findViewById(R.id.squared);
        pow = (Button) findViewById(R.id.pow);
        log = (Button) findViewById(R.id.log);
        percent = (Button) findViewById(R.id.percent);
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

        percent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!currentValue.equals("")) {
                    operation.setText(operation.getText().toString() + "%");
                    double temp = Double.parseDouble(currentValue);
                    temp = temp / 100;
                    currentValue = Double.toString(temp);
                }
            }
        });

        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countFunction("sin");
            }
        });

        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countFunction("cos");
            }
        });


        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lenOfNumber=0;
                double temp = 0;
                int lenCurrentValue = currentValue.length();
                if (!currentValue.equals("")) {
                    temp = Double.parseDouble(currentValue);
                } else {
                    currentValue = "0";
                }
                int lenOperation = operation.length();
                String show = operation.getText().toString();
                show = show.substring(0, lenOperation - lenCurrentValue);
                operation.setText(show + "tan(" + temp + ")");

                double degrees = Double.parseDouble(currentValue);
                double radians = Math.toRadians(degrees);
                if (lastSign != 'a') {
                    res = Math.tan(radians);
                    if (lastSign == '+') {
                        res = res + previousValue;
                    } else if (lastSign == '*') {
                        res = res * previousValue;
                    } else if (lastSign == '-') {
                        res = previousValue - res;
                    } else if (lastSign == '/') {
                        if (res != 0) {
                            res = previousValue / res;
                        } else {
                            Toast.makeText(AdvancedCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    res = Math.tan(radians);
                }
                previousValue = res;
                lastSign = 't';
            }
        });


        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logarithmFun("ln");
            }
        });
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logarithmFun("log");
            }
        });


        sqrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lenOfNumber=0;
                double temp = 0;
                int lenCurrentValue = currentValue.length();
                if (currentValue.equals("")) {
                    currentValue = "0";
                }
                temp = Double.parseDouble(currentValue);
                int lenOperation = operation.length();
                String show = operation.getText().toString();
                show = show.substring(0, lenOperation - lenCurrentValue);
                operation.setText(show + "\u221A" + "(" + temp + ")");

                double val = Double.parseDouble(currentValue);
                if (val >= 0) {
                    if (lastSign != 'a') {
                        res = Math.sqrt(val);
                        if (lastSign == '+') {
                            res = res + previousValue;
                        } else if (lastSign == '*') {
                            res = res * previousValue;
                        } else if (lastSign == '-') {
                            res = previousValue - res;
                        } else if (lastSign == '/') {
                            if (res != 0) {
                                res = previousValue / res;
                            } else {
                                Toast.makeText(AdvancedCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
                            }
                        }
                    } else {
                        res = Math.sqrt(val);
                    }

                    previousValue = res;
                    lastSign = 'q';
                }
                else
                {
                    Toast.makeText(AdvancedCalculator.this, "Nie można obliczyć pierwiastka liczby ujemnej", Toast.LENGTH_LONG).show();
                    error=1;
                }
            }

        });


        squared.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lenOfNumber=0;
                double temp = 0;
                int lenCurrentValue = currentValue.length();
                if (currentValue.equals("")) {
                    currentValue = "0";

                }
                temp = Double.parseDouble(currentValue);
                int lenOperation = operation.length();
                String show = operation.getText().toString();
                show = show.substring(0, lenOperation - lenCurrentValue);
                operation.setText(show + "sqr(" + temp + ")");

                double val = Double.parseDouble(currentValue);
                if (lastSign != 'a') {
                    res = val * val;
                    if (lastSign == '+') {
                        res = res + previousValue;
                    } else if (lastSign == '*') {
                        res = res * previousValue;
                    } else if (lastSign == '-') {
                        res = previousValue - res;
                    } else if (lastSign == '/') {
                        if (res != 0) {
                            res = previousValue / res;
                        } else {
                            Toast.makeText(AdvancedCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
                        }
                    }
                } else {
                    res = val * val;
                }
                previousValue = res;
                lastSign = 'k';
            }

        });
        pow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lenOfNumber=0;
                if (!currentValue.equals("")) {
                    powValue = Double.parseDouble(currentValue);
                    currentValue = "";
                    operation.setText(operation.getText().toString() + "^");
                    tempSign = 'p';
                }
            }
        });

        dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(operation.length()>=33 || lenOfNumber>16)
                {
                    Toast.makeText(AdvancedCalculator.this, "Za długa operacja", Toast.LENGTH_LONG).show();
                }
                else {
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
                            Toast.makeText(AdvancedCalculator.this, "Kropka nie moze zostać wstawiona", Toast.LENGTH_LONG).show();
                        }
                    }
                    else
                        Toast.makeText(AdvancedCalculator.this, "Błędna operacja", Toast.LENGTH_LONG).show();
                }
            }
        });

        equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(error==0) {
                    lenOfNumber = 0;
                    clickedOneTime = false;
                    if (tempSign == 'p') {
                        if (!currentValue.equals("")) {
                            if (powValue == 0 && Double.parseDouble(currentValue) <= 0) {
                                error = 3;
                                Toast.makeText(AdvancedCalculator.this, "Nie można obliczyć potęgi", Toast.LENGTH_LONG).show();
                            } else {
                                double pow = Math.pow(powValue, Double.parseDouble(currentValue));
                                currentValue = Double.toString(pow);
                                tempSign = 'a';
                            }
                        }
                    }
                    if (error == 1) {
                        Toast.makeText(AdvancedCalculator.this, "Nie można policzyć pierwiastka liczby ujemnej", Toast.LENGTH_LONG).show();
                    } else if (error == 2) {
                        Toast.makeText(AdvancedCalculator.this, "Nie można policzyć logarytmu", Toast.LENGTH_LONG).show();
                    } else if (error == 3) {
                        Toast.makeText(AdvancedCalculator.this, "Nie można obliczyć potęgi", Toast.LENGTH_LONG).show();
                    } else {

                        if (lastSign == '+') {
                            if (currentValue.equals("")) {
                                res = previousValue;
                                BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                                b = b.stripTrailingZeros();
                                result.setText("= " + b.toString());
                            } else {
                                res = previousValue + Double.parseDouble(currentValue);
                                BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                                b = b.stripTrailingZeros();
                                result.setText("= " + b.toString());
                            }
                        } else if (lastSign == '-') {
                            if (currentValue.equals("")) {
                                res = previousValue;
                                BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                                b = b.stripTrailingZeros();
                                result.setText("= " + b.toString());
                            } else {
                                res = previousValue - Double.parseDouble(currentValue);
                                BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                                b = b.stripTrailingZeros();
                                result.setText("= " + b.toString());
                            }
                        } else if (lastSign == '*') {
                            if (currentValue.equals("")) {
                                res = previousValue;
                                BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                                b = b.stripTrailingZeros();
                                result.setText("= " + b.toString());
                            } else {
                                res = previousValue * Double.parseDouble(currentValue);
                                BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                                b = b.stripTrailingZeros();
                                result.setText("= " + b.toString());
                            }
                        } else if (lastSign == '/') {
                            if (currentValue.equals("")) {
                                res = previousValue;
                                BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                                b = b.stripTrailingZeros();
                                result.setText("= " + b.toString());
                            } else {
                                if (currentValue.equals("0")) {
                                    Toast.makeText(AdvancedCalculator.this, "Nie można dzielić przez zero", Toast.LENGTH_LONG).show();
                                } else {
                                    res = previousValue / Double.parseDouble(currentValue);
                                    BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                                    b = b.stripTrailingZeros();
                                    result.setText("= " + b.toString());
                                }
                            }
                        } else if (lastSign == 's' || lastSign == 'c' || lastSign == 't' || lastSign == 'n' || lastSign == 'q' || lastSign == 'g' || lastSign == 'k') {
                            res = previousValue;
                            BigDecimal b = new BigDecimal(res, MathContext.DECIMAL64);
                            b = b.stripTrailingZeros();
                            result.setText("= " + b.toString());
                        } else {
                            BigDecimal b = new BigDecimal(currentValue, MathContext.DECIMAL64);
                            b = b.stripTrailingZeros();
                            result.setText("= " + b);
                        }
                    }
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
                    Toast.makeText(AdvancedCalculator.this, "Nie mozna zmienic znaku", Toast.LENGTH_LONG).show();
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