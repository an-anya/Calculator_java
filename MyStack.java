public class MyStack implements StackInterface {
    Object[] s;
    int t=1;
    int index;

    public MyStack(){
        s = new Object[1];
        index=-1;
    }

    public void push(Object o){
        if(o==null){

        }
        else if (index == t-1){
            Object[] newarray = new Object[t];
            for(int i=0;i<t;i++){
                newarray[i]=s[i];
            }

            s = new Object[2*t];
            for(int i=0;i<t;i++){
                s[i]=newarray[i];
            }

            index += 1;
            s[index] = o;
            t = 2*t;
        }
        else{
            index+=1;
            s[index]=o;
        }
    }
    public Object pop() throws EmptyStackException{
        if(index==-1){
            throw new EmptyStackException();
        }
        index-=1;
        return s[index+1];
    }

    public Object top() throws EmptyStackException {
        if(index==-1){
            throw new EmptyStackException();
        }
        else{
            return s[index];
        }
    }
    public boolean isEmpty(){
        if (index==-1){
            return Boolean.TRUE;
        }else{
            return Boolean.FALSE;
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder("");
        str.append("[");
        if (index==-1){
            return("[]");
        }
        for(int i=index;i>0;i--){
            String b= s[i]+", ";
            str.append(b);
        }
        str.append(s[0]).append("]");

        return str.toString();
    }

}
