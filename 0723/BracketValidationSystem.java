import java.util.Stack;

public class BracketValidationSystem {

    public static boolean isValid(String expression) {
        Stack<Character> stack = new Stack<>();

        for (char c : expression.toCharArray()) {
            if (isOpening(c)) {
                stack.push(c);
            } else if (isClosing(c)) {
                if (stack.isEmpty()) {
                    return false; 
                }
                char top = stack.pop();
                if (!isMatching(top, c)) {
                    return false; 
                }
            }
            
        }

        return stack.isEmpty(); 
    }

    private static boolean isOpening(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isClosing(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static boolean isMatching(char open, char close) {
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }

    public static void main(String[] args) {
        String[] tests = {
            "()",                           
            "()[]{}",                      
            "{[()]}",                      
            "({[]})",                      
            "(]",                           
            "([)]",                        
            "(((",                          
            ")))",                          
            "a + (b * [c - {d / e}])",     
            "a + (b * [c - {d / e})",       
            "",                            
            "{[}"                           
        };

        System.out.println("===== 括號驗證系統 =====\n");
        for (String expr : tests) {
            boolean result = isValid(expr);
            System.out.printf("%-30s → %s%n", "\"" + expr + "\"", result ? "有效" : "無效");
        }
    }
}