public class TestBoolean {
    //Kiểm tra số chẵn
    public boolean isEven(int number){
        return number % 2 == 0;
    }
    //Kiểm tra lẻ
    public boolean isOdd(int number){
        return number % 2 !=0;
    }
    //Kiểm tra số nguyên tố
    public boolean isPrime(){
        if(number < 2) return false;
        for (int i = 2; i <= Math.sqrt(number); i++){
            if(number % i == 0){
                return false;
            }
        }
        return true;
    }
}
