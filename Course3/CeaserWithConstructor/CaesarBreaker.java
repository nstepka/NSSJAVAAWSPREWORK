import edu.duke.*;
//
///**
// * Write a description of CaesarBreaker here.
// *
// * @author Nick Stepka
// * @version (a version number or a date)
// */
public class CaesarBreaker {
    private String alphabet;
    private String shiftedAlphabet;

    public CaesarBreaker() {
    }

    public CaesarBreaker(int key) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
    }

    public String encrypt(String input, int key) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        //Compute the shifted alphabet
        String shiftedAlphabet = alphabet.substring(key) +
                alphabet.substring(0, key);
        String shiftedAlphabetLower = alphabet.substring(key) +
                alphabet.substring(0, key).toLowerCase();
        //Count from 0 to < length of encrypted, (call it i)
        for (int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            //If currChar is in the alphabet
            if (idx != -1) {
                //Get the idxth character of shiftedAlphabet (newChar)
                if (currChar == Character.toUpperCase(currChar)) {
                    char newChar = shiftedAlphabet.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    encrypted.setCharAt(i, newChar);
                } else {
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

    public int indexOfMax(int[] values) {
        int indexOfMax = 0;
        for (int index = 0; index < values.length; index++) {
            if (values[index] > values[indexOfMax]) {
                indexOfMax = index;
            }
        }
        return indexOfMax;
    }


    public String encryptTwoKeys(String input, int key1, int key2) {
        StringBuilder updatedString = new StringBuilder(input);
        //  StringBuilder updatedStringCopy = new StringBuilder(input);
        for (int i = 0; i < updatedString.length(); i++) {
            if (i % 2 == 0) {
                char currChar = updatedString.charAt(i);
                String encriptedChar = encrypt(String.valueOf(currChar), key1);
                updatedString.setCharAt(i, encriptedChar.charAt(0));
            } else {
                char currChar = updatedString.charAt(i);
                String encriptedChar = encrypt(String.valueOf(currChar), key2);
                updatedString.setCharAt(i, encriptedChar.charAt(0));
            }
        }
        return updatedString.toString();
    }

    public int[] countLetters(String message) {
        int[] counters = new int[26];
        for (int i = 0; i < 26; i++) {
            counters[i] = 0;
        }
        String abc = "abcdefghijklmnopqrstuvwxyz";
        message = message.toLowerCase();
        for (int i = 0; i < message.length(); i++) {
            int position = abc.indexOf(message.charAt(i));
            if (position != -1) counters[position] += 1;
        }

        return counters;
    }

    public String replaceVowels(String phrase, char ch) {
        StringBuilder updatedString = new StringBuilder(phrase);
        for (int i = 0; i < updatedString.length(); i++) {
            if (isVowel(updatedString.charAt(i))) {
                updatedString.setCharAt(i, ch);
            }
        }

        return updatedString.toString();
    }


    public String emphasize(String phrase, char ch) {
        StringBuilder updatedString = new StringBuilder(phrase);
        char charUpper = Character.toUpperCase(ch);
        char charLower = Character.toLowerCase(ch);

        for (int i = 0; i < updatedString.length(); i++) {
            if (charUpper == updatedString.charAt(i) || charLower == updatedString.charAt(i)) {
                if (i % 2 == 0) {
                    updatedString.setCharAt(i, '*');
                } else {
                    updatedString.setCharAt(i, '+');
                }
            }
        }
        return updatedString.toString();
    }


    public String decrypt(String encrypted, int key) {
        CaesarBreaker cc = new CaesarBreaker();
        System.out.println("Encrypted = " + encrypted);
        int[] freqs = countLetters(encrypted);
        int maxDex = indexOfMax(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey = 26 - (4 - maxDex);
        }
        return cc.encrypt(encrypted, 26 - dkey);
    }

    public void testEmphasize() {
        System.out.println(emphasize("dna ctgaaactga", 'a'));
        System.out.println(emphasize("Mary Bella Abracadabra", 'a'));
    }



    public void testEncryptTwoKeys() {
        System.out.println(encryptTwoKeys("At noon be in the conference room with your hat on for a surprise party. YELL LOUD!", 8, 21));
    }

    public boolean isVowel(char ch) {

        ch = Character.toUpperCase(ch);
        if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
            return true;
        }
        return false;
    }

    public String halfOfString(String message, int start) {
        StringBuilder newMessage = new StringBuilder();
        char validChar = ' ';
        for (int i = 0; i < message.length(); i++) {
            if (start % 2 == 0 && i % 2 == 0) {
                validChar = message.charAt(i);
                newMessage.append(validChar);
            }
            if (start % 2 != 0 && i % 2 != 0) {
                validChar = message.charAt(i);
                newMessage.append(validChar);
            }
        }
        String finalMessage = newMessage.toString();
        return newMessage.toString();
    }

    public void testhalfOfString() {
        String message = "Qbkm Zgis";
        int start = 1;
        String execute = halfOfString(message, start);
        System.out.println(execute);
    }


    public void testReplaceVowels() {
        System.out.println(replaceVowels("Hello World", '*'));
    }

    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted, 15);
        System.out.println(decrypted);
        testhalfOfString();
        System.out.println(decryptTwoKeys(encrypted));
    }

    public String decryptTwoKeys(String encrypted) {
        String decrypt1 = decrypt(halfOfString(encrypted, 0), 2);
        String decrypt2 = decrypt(halfOfString(encrypted, 1), 20);
        String key1 = getKey(decrypt1);
        String key2 = getKey(decrypt2);
        StringBuilder sb = new StringBuilder();
        int a = 0;
        int b = 0;
        for (int i = 0; i < decrypt1.length() + decrypt2.length(); i++) {
            if (i % 2 == 0) {
                sb = sb.append(decrypt1.charAt(a));
                a++;
            }
            if (i % 2 != 0) {
                sb = sb.append(decrypt2.charAt(b));
                b++;
            }
        }
        return sb.toString();
    }

    public String getKey(String s) {
        String alph = "abcdefghijklmnopqrstuvwxyz";
        int[] freqs = countLetters(s);
        int max = 0;
        for (int i = 0; i < freqs.length; i++) {
            if (freqs[i] > freqs[max]) {
                max = i;
            }
        }
        return alph.substring(max, max + 1);
    }

    public void testIsVowel() {
        if (isVowel('a')) {
            System.out.println("a True");
        }
        if (isVowel('A')) {
            System.out.println("A True");
        }
        if (!isVowel('a')) {
            System.out.println("FALSE");
        }
        if (!isVowel('A')) {
            System.out.println("FALSE");
        }
        if (!isVowel('k')) {
            System.out.println("k hit false");
        }
        if (!isVowel('K')) {
            System.out.println("K hit false");
        }
        if (isVowel('k')) {
            System.out.println("FALSE");
        }
        if (isVowel('K')) {
            System.out.println("FALSE");
        }
    }

    public static void main(String[] args) {
        CaesarCipher c = new CaesarCipher();
        c.testCaesar();
        //c.testIsVowel();
        c.testEncryptTwoKeys();
//        c.testEmphasize();
//        c.testReplaceVowels();
    }
}
