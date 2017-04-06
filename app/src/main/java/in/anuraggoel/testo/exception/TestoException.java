package in.anuraggoel.testo.exception;

/**
 * Created by Anurag on 06-04-2017.
 */

public class TestoException extends RuntimeException {
    private int appErrorCode;

    public TestoException() {
    }

    public TestoException(String detailMessage) {
        super(detailMessage);
    }

    public TestoException(Throwable throwable) {
        super(throwable);
    }

    public TestoException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public TestoException(String detailMessage, int appErrorCode) {
        super(detailMessage);
        this.appErrorCode = appErrorCode;
    }

    public void setAppErrorCode(int appErrorCode) {
        this.appErrorCode = appErrorCode;
    }

    public int getAppErrorCode() {
        return this.appErrorCode;
    }
}
