public class SumPrimeByRange {

    public static void main(final String[] args) {

        // final String mode = System.getProperty("mode"), rangeStr = System.getProperty("range");

        final String mode = args[0], rangeStr = args[1];
        final int range = Integer.valueOf(rangeStr);

        if ("speed".equals(mode)) {
            printSum2(range);
        } else if ("lessloop".equals(mode)) {
            printSum1(range);
        } else {
            System.out.println("没有结果，请输入正确的参数值");
        }

        // printSum2(1000000);
        // printSum1(1000000);
    }

    /** 借助合数定义求和 */
    public static void printSum1(final int n) {

        final long start = System.currentTimeMillis();
        long sum = 0;

        for (int j = 2; j <= n; j++) {
            if (isPrime(j)) {
                sum = sum + j;
            }
        }
        System.out.println(n + "以内的素数sum " + sum + " ");
        final long end = System.currentTimeMillis();
        System.out.println("The time cost is " + (end - start));

    }

    /** 借助合数定义与排除法求和 */
    public static void printSum2(final int n) {
        final long start = System.currentTimeMillis();    // 取开始时间
        // 素数总和
        long sum = 0L;
        // 1000万以内的所有素数
        // 用数组将1000万以内的数分为两大派系，素数用0代替数值，合数用1代替数值；
        // 一开始默认全部为素数，所以值全部为0，等到开始筛选的时候再把为合数的赋值为1
        final int num[] = new int[n];
        num[0] = 1;          // 由于1规定不是素数，所以要提前用1标值
        // 根据埃氏筛法的结论，要得到自然数 N 以内的全部素数，必须把不大于" 二次根号 N "的所有素数的倍数剔除，剩下的就是素数
        final double prescription = Math.sqrt(n);
        for (int i = 2; i <= prescription; i++) {
            // 开始把所有素数的倍数剔除，剩下的就是素数
            for (int j = i * i; j <= n; j += i) {
                // 从i*i开始去除，因为比i*i小的倍数，已经在前面去除过了
                // 例如：i=5
                // 5的2倍（10），3倍（15），在i=2的时候，已经去除过了

                num[j - 1] = 1;   // 把素数的倍数剔除，也就是赋值为1，不是素数就是合数
            }
        }
        // 遍历数组，把值为0的数全部统计出来，得到素数之和
        for (int i = 0; i < num.length; i++) {
            if (num[i] == 0) {
                sum = sum + i + 1L;
            }
        }
        System.out.println(n + "以内的素数sum " + sum + " ");
        // System.out.println(n+"以内的素数有"+sum+"个");
        final long end = System.currentTimeMillis();
        System.out.println("The time cost is " + (end - start));

    }

    /** 如果一个数是合数，那么它的最小质因数肯定小于等于他的平方根 */
    public static boolean isPrime(final int n) {// 判断n是否是质数
        boolean isPrime = true;// 是否是质数的标志
        final int s = (int) Math.sqrt(n);// 对n开根号
        for (int i = s; i > 1; i--) {// n除以每个比n开根号小比1大的自然数
            if ((n % i) == 0) {// 如果有能被整除的，则不是质数
                isPrime = false;
                break;
            }
        }
        return isPrime;

    }

}
