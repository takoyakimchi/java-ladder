package domain;

public class Height {

    private static final int MIN_HEIGHT = 1;
    private static final int MAX_HEIGHT = 20;

    private final int height;

    private Height(int height) {
        validate(height);
        this.height = height;
    }

    public static Height from(String rawHeight) {
        return new Height(initialize(rawHeight));
    }

    private static void validate(int height) {
        if (height < MIN_HEIGHT || height > MAX_HEIGHT) {
            throw new IllegalArgumentException(
                MIN_HEIGHT + " 이상 " + MAX_HEIGHT + " 이하의 숫자를 입력해 주세요.");
        }
    }

    private static int initialize(String rawHeight) {
        try {
            return Integer.parseInt(rawHeight);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력 가능합니다.");
        }
    }

    public int getHeight() {
        return height;
    }
}
