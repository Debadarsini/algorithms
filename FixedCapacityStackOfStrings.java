

public class FixedCapacityStackOfStrings {
    
    private String[] stack;
    private int N=0;
    
    public FixedCapacityStackOfStrings(){
        stack = new String[1];
    }

    public boolean isEmpty(){
        return N==0;
    }
    
    public void push(String item){
        if(N==stack.length) {resize(2*stack.length);}
        stack[N++] = item;
    }
    
    private void resize(int i) {
        String[] copy = new String[i];
        for(int j=0;i<N;i++){
            copy[i] =stack[i];
        }
        stack = copy;
    }

    public String pop(){
        
        String item = stack[--N];
        stack[N] = null;
        if(N>0&&N==stack.length/4){resize(stack.length/2);}
        return item;
    }
    
    public static void main(String [] args){
        int n = 0;
        int i = n++;
        int j = ++n;
        System.out.println(" i "+i+" n "+n +"j "+j) ;
        
        i = --n;
        System.out.println(" i "+i+" n "+n);
    }
}
