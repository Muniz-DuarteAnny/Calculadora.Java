import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculadora2 extends JFrame implements ActionListener {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora2();
            }
        });
    }

    private JTextField textField;
    private JButton[] buttons;
    private String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+"
    };

    private String firstNumber;
    private String operator;

    public Calculadora2() {
        textField = new JTextField(15);
        textField.setEditable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        buttons = new JButton[buttonLabels.length];

        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].addActionListener(this);
            buttonPanel.add(buttons[i]);
        }

        setLayout(new BorderLayout());
        add(textField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (isNumber(command)) {
            textField.setText(textField.getText() + command);
        } else if (isOperator(command)) {
            firstNumber = textField.getText();
            operator = command;
            textField.setText("");
        } else if (command.equals("=")) {
            calculate();
        }
    }

    private boolean isNumber(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String input) {
        return input.equals("+") || input.equals("-") || input.equals("*") || input.equals("/");
    }

    private void calculate() {
        String secondNumber = textField.getText();
        double result = 0;

        double num1 = Double.parseDouble(firstNumber);
        double num2 = Double.parseDouble(secondNumber);

        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 == 0) {
                    textField.setText("Erro");
                    return;
                }
                result = num1 / num2;
                break;
        }

        textField.setText(Double.toString(result));
    }
}
