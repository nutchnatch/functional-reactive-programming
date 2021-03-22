public interface CallbackWithPush {

    void pushComplete();

    void pushData(String data);

    void pushError(Exception ex);
}
