class Main{

    public static void main(String[] args) {
        int[] arr = new int[]{ 1, 2 ,3 ,5 ,4 ,10 ,11};
        int start = arr[0];
        int end = arr[0];

        for (int i = 1; i < arr.length; i++) {
            int current = arr[i];
            if(current == end+1){
                end++;
            }else{
                if(start == end){
                    // pass
                }else{
                    System.out.println(start +"  "+end);
                }
                // Reset start end
                start = current;
                end = current;
            }
        }
        if(start != end){
            System.out.println(start +"  "+end);
        }

    }
}
