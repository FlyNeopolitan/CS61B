public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> output = new LinkedListDeque<Character>();
        for (int k = 0; k < word.length(); k++) {
            output.addLast(word.charAt(k));
        }
        return output;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque, cc);
     //  The iterative version of isPalindrome
     /*   Deque<Character> wordWeHave = wordToDeque(word);
        while(!wordWeHave.isEmpty()) {
            Character first = wordWeHave.removeFirst();
            if(!wordWeHave.isEmpty()) {
                Character last = wordWeHave.removeLast();
                if (last != first) {
                    return false;
                }
            }
        }
        return true; */
    }
    public boolean isPalindrome(String word) {
        Deque<Character> wordWeHave = wordToDeque(word);
        while (!wordWeHave.isEmpty()) {
            Character first = wordWeHave.removeFirst();
            if (!wordWeHave.isEmpty()) {
                Character last = wordWeHave.removeLast();
                if (last != first) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPalindromeHelper(Deque<Character> word, CharacterComparator cc) {
        if (word.size() == 1) {
            return true;
        }
        if (word.isEmpty()) {
            return true;
        }
        Character first = word.removeFirst();
        Character last = word.removeLast();
        if (cc.equalChars(first, last)) {
            return isPalindromeHelper(word, cc);
        } else {
            return false;
        }
    }



}

