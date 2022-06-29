public class Calculator {

    public int evaluatePostFix(String s) throws InvalidPostfixException {
        if (s.length() == 0) {
            throw new InvalidPostfixException();
        }
        try {
            MyStack stack = new MyStack();
            int k = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                if (s.charAt(i) == ' ') {
                    k = 0;
                } else if (k == 0) {
                    k += 1;
                    stack.push(String.valueOf(s.charAt(i)));
                } else {
                    stack.pop();
                    StringBuilder sb = new StringBuilder();
                    sb.append(s.charAt(i + 1));

                    int p = i;
                    while (p >= 0 && s.charAt(p) != ' ' && Character.isDigit(s.charAt(p))) {
                        sb.append(s.charAt(p));
                        p = p - 1;
                    }
                    sb.reverse();
                    stack.push(sb.toString());
                    k = 0;
                    i = i - sb.length() + 2;
                }
            }

            MyStack support = new MyStack();
            while (!stack.isEmpty()) {
                if (stack.top().equals("+") || stack.top().equals("-") || stack.top().equals("*") ) {
                    String a = (String) support.pop();
                    String b = (String) support.pop();
                    String c = (String) stack.pop();
                    int d=0;
                    switch (c) {
                        case ("+"):
                            d= Integer.parseInt(String.valueOf(a)) + Integer.parseInt(String.valueOf(b));
                            break;
                        case ("-") :
                            d=Integer.parseInt(String.valueOf(b)) - Integer.parseInt(String.valueOf(a));
                            break;
                        case ("*") :
                            d=Integer.parseInt(String.valueOf(a)) * Integer.parseInt(String.valueOf(b));
                            break;
                    }
                    support.push(String.valueOf(d));
                } else {
                    support.push(stack.pop());
                }
            }
            return Integer.parseInt(String.valueOf(support.top()));
        } catch (EmptyStackException e) {
            throw new InvalidPostfixException();
        }
    }

        static char precedence(String a, String b){
            if(a.equals("*") ){
                if(b.equals("*")){
                    return '=';
                }
                else if (b.equals("+") || b.equals("-")){
                    return '>';
                }
            }else{
                if(b.equals("*")){
                    return '<';}
            }
            return '=';
        }
        static boolean operands(String a){
            return a.equals("(") || a.equals(")") || a.equals("+") || a.equals("-") || a.equals("*");
        }

        public static String convertExpression(String s) throws InvalidExprException {
            if (s.length() == 0) {
                throw new InvalidExprException();
            }
            StringBuilder postfix;
            try {

                MyStack stack = new MyStack();
                for (int i = 0; i < s.length(); i++) {
                    if(s.charAt(i)==' '){
                        continue;
                    }
                    else if (s.charAt(i) == '(' || s.charAt(i) == ')' || s.charAt(i) == '-' || s.charAt(i) == '+' || s.charAt(i) == '*') {
                        stack.push(String.valueOf(s.charAt(i)));
                    } else {
                        String nums = "";
                        while (i < s.length() && Character.isDigit(s.charAt(i)) ) {
                            nums += s.substring(i,i+1);
                            i+=1;
                        }
                        i = i - 1;
                        stack.push(nums);
                    }
                }
                ////

                MyStack parenthesis = new MyStack();
                while (!stack.isEmpty()) {
                    if (stack.top().equals("(") || stack.top().equals(")")) {
                        parenthesis.push(stack.top());
                    }stack.pop();
                }

                MyStack newparenthesis = new MyStack();
                while (!parenthesis.isEmpty()) {
                    if (parenthesis.top().equals(")")) {
                        newparenthesis.push(parenthesis.pop());
                    } else {
                        parenthesis.pop();
                        newparenthesis.pop();
                    }
                }
                if (parenthesis.isEmpty() && !newparenthesis.isEmpty()) {
                    throw new InvalidExprException();
                }

                int symbols = 0;
                int p = 0;
                while (!stack.isEmpty()) {
                    if (operands(String.valueOf(stack.top())) && !String.valueOf(stack.top()).equals("(") && !String.valueOf(stack.top()).equals(")")) {
                        symbols += 1;
                        stack.pop();
                    } else if (!operands(String.valueOf(stack.top()))) {
                        p += 1;
                        stack.pop();
                    }
                }
                if (symbols + 1 != p) {
                    throw new InvalidExprException();
                }

                ////


                MyStack operand = new MyStack();
                postfix = new StringBuilder("");
                String newelem;
                
                newelem = String.valueOf(stack.top());
                while (!stack.isEmpty()) {
                    if (operands(String.valueOf(stack.top()))) {
                        if (operand.isEmpty()) {
                            if (newelem.equals(")")) {
                                throw new InvalidExprException();
                            } else {
                                operand.push(newelem);
                                stack.pop();
                            }
                        } else if (operand.top().equals("(") && !newelem.equals(")")) {
                            operand.push(newelem);
                            stack.pop();
                        } else if (newelem.equals("(")) {
                            operand.push(newelem);
                            stack.pop();
                        } else if (newelem.equals(")")) {
                            stack.pop();
                            while (!operand.top().equals("(")) {
                                if (operand.top().equals(")")) {
                                    throw new InvalidExprException();
                                } else {
                                    postfix.append(operand.top());
                                    operand.pop();
                                }
                            }
                            operand.pop();
                        } else if (!operand.top().equals("(") && precedence(newelem, String.valueOf(operand.top())) == '>') {
                            operand.push(newelem);

                        } else {
                            while (!operand.top().equals("(") && !operand.top().equals(")") && precedence(newelem, String.valueOf(operand.top())) != '>') {
                                if (precedence(newelem, String.valueOf(operand.top())) == '=') {
                                    postfix.append(operand.top());
                                    operand.pop();
                                    break;
                                } else {
                                    postfix.append(operand.top());
                                    operand.pop();
                                }
                            }
                            operand.push(newelem);
                        }
                    } else {
                        postfix.append(newelem);
                        stack.pop();

                    }
                }return postfix.toString();

            } catch (EmptyStackException e) {
                throw new InvalidExprException();
            }

        }

    }

















