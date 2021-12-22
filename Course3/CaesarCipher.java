import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key)+
        alphabet.substring(0,key);
        String shiftedAlphabetLower = alphabet.substring(key)+
                alphabet.substring(0,key);
        //create a case for if the letters are lowercase.
        shiftedAlphabetLower = shiftedAlphabetLower.toLowerCase();
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                if(currChar == Character.toUpperCase(currChar)){
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }else {
                    char newChar = shiftedAlphabetLower.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                }

            }
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder updatedString = new StringBuilder(input);
      //  StringBuilder updatedStringCopy = new StringBuilder(input);
        for(int i = 0; i < updatedString.length(); i++){
            if(i % 2 == 0){
                char currChar = updatedString.charAt(i);
                String encriptedChar = encrypt(String.valueOf(currChar), key1);
                updatedString.setCharAt(i,encriptedChar.charAt(0));
            }else{
                char currChar = updatedString.charAt(i);
                String encriptedChar = encrypt(String.valueOf(currChar), key2);
                updatedString.setCharAt(i,encriptedChar.charAt(0));
            }
        }
        return updatedString.toString();
    }

    public String replaceVowels(String phrase, char ch){
        StringBuilder updatedString = new StringBuilder(phrase);
        for(int i = 0; i < updatedString.length(); i++){
            if(isVowel(updatedString.charAt(i))){
                updatedString.setCharAt(i,ch);
            }
        }

        return updatedString.toString();
    }


    public String emphasize(String phrase, char ch){
        StringBuilder updatedString = new StringBuilder(phrase);
        char charUpper = Character.toUpperCase(ch);
        char charLower = Character.toLowerCase(ch);

        for(int i = 0; i < updatedString.length(); i++) {
            if(charUpper == updatedString.charAt(i)  || charLower == updatedString.charAt(i)){
                if(i % 2 == 0){
                    updatedString.setCharAt(i,'*');
                }else{
                    updatedString.setCharAt(i,'+');
                }
            }
        }
        return updatedString.toString();
    }

    public void testEmphasize(){
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }

    public void testEncryptTwoKeys(){
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }

    public boolean isVowel(char ch){

        ch = Character.toUpperCase(ch);
        if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U'){
            return true;
        }
        return false;
    }





    public void testReplaceVowels(){
        System.out.println(replaceVowels("Hello World", '*'));
    }
    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }

    public void testIsVowel(){
        if(isVowel('a')){
            System.out.println("a True");
        }
        if(isVowel('A')){
            System.out.println("A True");
        }
        if(!isVowel('a')){
            System.out.println("FALSE");
        }
        if(!isVowel('A')){
            System.out.println("FALSE");
        }
        if(!isVowel('k')){
            System.out.println("k hit false");
        }
        if(!isVowel('K')){
            System.out.println("K hit false");
        }
        if(isVowel('k')){
            System.out.println("FALSE");
        }
        if(isVowel('K')){
            System.out.println("FALSE");
        }
    }

    public static void main(String[] args) {
        CaesarCipher c = new CaesarCipher();
        c.testCaesar();
        //c.testIsVowel();
    //    c.testEncryptTwoKeys();
//        c.testEmphasize();
//        c.testReplaceVowels();
    }
}

