import java.util.StringTokenizer;
//TODO: Put catch in main function
public class SolveProblem {
    static String calculate(StringBuffer s) {
        String str = s.toString();
        if (!str.contains("/")) {
            if (str.equals("x")) {
                return str;
            }
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException nfe) {
                return "Wrong expression.";
            }
            return str;
        }
        try {
            if ((str.endsWith("/")) || (str.startsWith("/"))) {
                throw new ExpressionException("Wrong expression. String starts or ends with /");
            }
        } catch (ExpressionException e) {
            return (e.getMessage());
        }
        StringTokenizer text = new StringTokenizer(str, "/", true);
        int current = 1;
        int degree = 0;
        boolean delim = false;
        String token = text.nextToken();
        if (token.equals("x")) {
            degree++;
        } else {
            try {
                current = Integer.parseInt(token);
            } catch (NumberFormatException nfe) {
                return "Wrong expression";
            }
            //current = Integer.parseInt(token);
            degree = 0;
        }
        while (text.hasMoreTokens()) {
             token = text.nextToken();
            if (!token.equals("/")) {
                if (token.equals("x")) {
                    degree--;
                } else {
                    try {
                        current /= Integer.parseInt(token);
                    } catch (NumberFormatException nfe) {
                        return "Wrong expression";
                    }
                    //current /= Integer.parseInt(token);
                }
                delim = false;
            } else {
                try {
                    if (delim) {
                        throw new ExpressionException("Wrong expression. / after another /");
                    }
                } catch (ExpressionException e) {
                    return (e.getMessage());
                }
                delim = true;
            }
        }
        //result = Integer.toString(current) + "*x^" + Integer.toString(degree);
        return current + "*x^" + degree;
    }

    public static void main(String[] args) {
        for (int i = 0; i < args.length; ++i) {
            System.out.println("N " + i + " = " + args[i]);
            StringBuffer str = new StringBuffer(args[i]);
            System.out.println(SolveProblem.calculate(str));
        }
    }

    static class ExpressionException extends Exception {
        public ExpressionException(String str) {
            super(str);
        }
    }
}
