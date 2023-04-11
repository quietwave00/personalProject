package blog.jwt;

public class UserContextHolder {
    private static final ThreadLocal<String> userIdHolder = new ThreadLocal<>();

    public static String getUserId() {
        return userIdHolder.get();
    }

    public static void setUserId(String userId) {
        userIdHolder.set(userId);
    }

}
