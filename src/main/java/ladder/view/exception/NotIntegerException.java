package ladder.view.exception;

import ladder.common.CustomException;

public class NotIntegerException extends CustomException {

    private static final String MESSAGE = "입력된 값은 정수가 아닙니다.";

    public NotIntegerException() {
        super(MESSAGE);
    }
}
