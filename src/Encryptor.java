public class Encryptor {
    public static String encrypt(String plainText, String key) {
        int step = key.chars().sum() % 26;
        return step(plainText, step);
    }
    public static String decrypt(String text, String key) {
        int step = key.chars().sum() % 26;
        return step(text, -step);
    }
    public static String step(String text, int step) {
        StringBuilder result = new StringBuilder();
        for(char c : text.toCharArray()) {
            if(Character.isLetter(c)) {
                char base;
                if(Character.isUpperCase(c)) {
                    base = 'A';
                } else {
                    base = 'a';
                }
                c = (char)((c - base + step + 26) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }
}