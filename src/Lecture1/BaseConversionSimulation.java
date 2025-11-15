package Lecture1;

import java.util.Scanner;

public class BaseConversionSimulation {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int inputType;
        String value;
        String answer = "";
        int outputType;

        while (true) {
            System.out.println("Welcome Form Base Conversion Simulation");
            System.out.println("Choose Input Base Number");
            System.out.println("1 -> Binary");
            System.out.println("2 -> Decimal");
            System.out.println("3 -> Hexadecimal");

            while (true) {
                System.out.print("Enter Input Base Type: ");

                // Check if the next token is an integer
                if (scan.hasNextInt()) {
                    inputType = scan.nextInt();
                    if (inputType > 0 && inputType < 4) {
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter 1 - 3");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    scan.next(); // Clear the invalid input
                }
            }

            System.out.print("Enter value: ");
            scan.nextLine();
            value = scan.nextLine();

            System.out.println("Choose Output Base Number");
            System.out.println("1 -> Binary");
            System.out.println("2 -> Decimal");
            System.out.println("3 -> Hexadecimal");
            while (true) {
                System.out.print("Enter Output Base Type: ");

                // Check if the next token is an integer
                if (scan.hasNextInt()) {
                    outputType = scan.nextInt();
                    if (outputType > 0 && outputType <4) {
                        break;
                    } else {
                        System.out.println("Invalid input! Please enter 1 - 3");
                    }
                } else {
                    System.out.println("Invalid input! Please enter a number.");
                    scan.next(); // Clear the invalid input
                }
            }

            switch (inputType) {
                case 1 :
                    answer = switch (outputType) {
                        case 1 -> value;
                        case 2 -> BinaryToDecimal(value);
                        case 3 -> BinaryToHexadecimal(value);
                        default -> answer;
                    };
                    break;
                case 2:
                    answer = switch (outputType) {
                        case 1 -> DecimalToBinary(value);
                        case 2 -> value;
                        case 3 -> DecimalToHexadecimal(value);
                        default -> answer;
                    };
                    break;
                case 3:
                    answer = switch (outputType) {
                        case 1 -> HexadecimalToBinary(value);
                        case 2 -> HexadecimalToDecimal(value);
                        case 3 -> value;
                        default -> answer;
                    };
                    break;
                default:
                    System.out.println("Invalid Input");
                    break;
            }

            System.out.println();
            System.out.println("---Answer---");
            switch (inputType) {
                case 1 :
                    System.out.println("Binary: " + value);
                    break;
                case 2 :
                    System.out.println("Decimal: " + value);
                    break;
                case 3 :
                    System.out.println("Hexadecimal: " + value);
                    break;
            }
            switch (outputType) {
                case 1 :
                    System.out.println("Binary: " + answer);
                    break;
                case 2 :
                    System.out.println("Decimal: " + answer);
                    break;
                case 3 :
                    System.out.println("Hexadecimal: " + answer);
                    break;
            }

            System.out.print("\nType 0 to Exit: ");
            if (scan.hasNextInt()) {
                if (scan.nextInt() == 0) {
                    break;
                }
            }
            System.out.println();
        }
    }

    private static String HexadecimalToDecimal(String value) {
        double answer = 0;

        for (int i = 0; i < value.length(); i++) {
            if (!(Character.isDigit(value.charAt(i)))) {
                answer += switch (value.charAt(i)) {
                    case 'A' -> 10 * Math.pow(16, value.length()-i-1);
                    case 'B' -> 11 * Math.pow(16, value.length()-i-1);
                    case 'C' -> 12 * Math.pow(16, value.length()-i-1);
                    case 'D' -> 13 * Math.pow(16, value.length()-i-1);
                    case 'E' -> 14 * Math.pow(16, value.length()-i-1);
                    case 'F' -> 15 * Math.pow(16, value.length()-i-1);
                    default -> 0;
                };
            } else {
                answer += Character.getNumericValue(value.charAt(i)) * Math.pow(16, value.length()-i-1);
            }
        }

        return String.valueOf((int) answer);
    }

    private static String DecimalToHexadecimal(String value) {
        int intValue = Integer.parseInt(value);
        int remainder = 0;
        String answer = "";

        do {
            remainder = intValue % 16;
            if (remainder > 9) {
                answer = switch (remainder) {
                    case 10 -> "A";
                    case 11 -> "B";
                    case 12 -> "C";
                    case 13 -> "D";
                    case 14 -> "E";
                    case 15 -> "F";
                    default -> "-";
                } + answer;
            } else {
                answer = remainder + answer;
            }
            intValue = intValue / 16;
        } while (intValue > 0);

        return answer;
    }

    private static String HexadecimalToBinary(String value) {
        return DecimalToBinary(HexadecimalToDecimal(value));
    }

    private static String DecimalToBinary(String value) {
        String answer = "";
        int intValue = Integer.parseInt(value);

        do {
            answer = String.valueOf(intValue % 2) + answer;
            intValue = intValue / 2;
        } while (intValue > 0);

        return answer;
    }

    private static String BinaryToDecimal(String value) {
        double answer = 0;

        if (!value.matches("[01]+")) {
            return "input value isn't incorrect!";
        }
        for (int i = 0; i < value.length(); i++) {
            answer += Character.getNumericValue(value.charAt(i)) * Math.pow(2, value.length()-i-1);
        }
//        System.out.println(Integer.parseInt(value, 2));

        return String.valueOf((int) answer);
    }

    private static String BinaryToHexadecimal(String value) {
        StringBuilder answer = new StringBuilder();
        String temp = "";
        int checkValue = 0;
        int count = 0;
        int remainder = value.length() % 4;

        if (remainder != 0) {
            int zerosToAdd = 4 - remainder;
            StringBuilder valueBuilder = new StringBuilder(value);
            for (int i = 0; i < zerosToAdd; i++) {
                valueBuilder.insert(0, "0");   // add leading zero
            }
            value = valueBuilder.toString();
        }

        for (int i = 0; i < value.length(); i++) {
            temp = temp + value.charAt(i);
            count++;

            if (count >= 4) {
                checkValue = Integer.parseInt(BinaryToDecimal(temp));
                temp = "";
                count = 0;

                if (checkValue > 9) {
                    switch (checkValue - 9) {
                        case 1:
                            answer.append("A");
                            break;
                        case 2:
                            answer.append("B");
                            break;
                        case 3:
                            answer.append("C");
                            break;
                        case 4:
                            answer.append("D");
                            break;
                        case 5:
                            answer.append("E");
                            break;
                        case 6:
                            answer.append("F");
                            break;
                    }
                } else {
                    answer.append(checkValue);
                }
            }
        }
        return String.valueOf(answer);
    }
}
