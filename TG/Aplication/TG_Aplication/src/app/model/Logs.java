package app.model;

public class Logs {
    private Long idLog;
    private String hashCARD;
    private String data;
    private int granted;

    public Long getIdLog() {
        return idLog;
    }

    public void setIdLog(Long idLog) {
        this.idLog = idLog;
    }

    public String getHashCARD() {
        return hashCARD;
    }

    public void setHashCARD(String hashCARD) {
        this.hashCARD = hashCARD;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getGranted() {
        return granted;
    }

    public void setGranted(int granted) {
        this.granted = granted;
    }
}
