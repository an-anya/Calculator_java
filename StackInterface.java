class EmptyStackException extends Exception{
        public EmptyStackException(){
                System.out.println("Stack is empty");
        }
}

class InvalidPostfixException extends Exception{
        public InvalidPostfixException(){
                System.out.println("Invalid postfix expression!");
        }
}
class  InvalidExprException extends Exception{
        public  InvalidExprException(){
                System.out.println("Invalid infix expression!");
        }
}


public interface StackInterface {

        public void push(Object o);
        public Object pop() throws EmptyStackException;
        public Object top() throws EmptyStackException;
        public boolean isEmpty();
        public String toString();

}


