

public class BitonicSearch {
    
  private static int binarySearch(int[] arr,int low, int high,int element, boolean ascending){
        if(high>low){
        int mid=(low+high)/2;
     //   System.out.println("low "+low+" mid "+mid+" high "+high);
        int index=-1;
        if(element == arr[low]){
           index= low;
        }else if (element == arr[high]){
           index= high;
        }else if(element == arr[mid]){
            index= mid;
        }
        

        if(index!=-1){
            System.out.println(index);   
            return index;
            
        }
      
        System.out.println(ascending);
        if(element>arr[mid]){
            if(ascending){
                return binarySearch(arr,mid+1,high,element,true);
            }else{
                return binarySearch(arr,low,mid,element,false);
            }
        }else{
            if(ascending){
                return binarySearch(arr,low,mid,element,true);
            }else{
                return binarySearch(arr,mid+1,high,element,false);
            }
        }
        }return -1;
         
    }
    
    public static void main(String [] args){
        int[] input = {1, 3, 4, 6, 9, 14, 15,16,17,18,11, 7, 2, -4, -9};
        int low = 0;
        
        int element = 3;
        
        int high = input.length-1;
        
       // int mid = (low+high)/2;
        
        int mid = findMid(input,low,high);
        System.out.println(mid);
        if(mid==-1){
            System.out.println("middle element not foub");
        }
      
     
        int elementIndex=-1;
        if(element == input[low]){
            elementIndex= low;
        }else if (element == input[high]){
            elementIndex= high;
        }else if(element == input[mid]){
            elementIndex= mid;
        }
        
        if(elementIndex != -1){
            System.out.println("element index "+elementIndex);
            return;
        }
        
        elementIndex= binarySearch(input,low,mid,element,true);
        if(elementIndex ==-1){
            elementIndex=  binarySearch(input,mid+1,high,element,false);
        }
        
        System.out.println("element index "+elementIndex);
    }

    private static int findMid(int[] input, int low, int high) {
        if(low <=high){
            int mid = (low+high)/2;
          //  System.out.println(mid);
         
            if(input[mid-1]<input[mid]&&input[mid]>input[mid+1]){
                return mid;
            }
            
            if(input[mid]<input[mid+1]){
                return findMid(input,mid+1,high);
            }else{
                return findMid(input,low,mid);
            }
        }
        return -1;
    }

}
