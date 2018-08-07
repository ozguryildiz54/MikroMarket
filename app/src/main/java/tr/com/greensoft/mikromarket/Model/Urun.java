package tr.com.greensoft.mikromarket.Model;

/**
 * Created by Özgür on 10.07.2018.
 */

public class Urun {

    // Variables
    private String urunAdi;
    private String barkod;
    private int giren;
    private int cikan;
    private int kalan;
    private String uyeId;
    private double fiyat;

    public double getFiyat() {
        return fiyat;
    }

    public void setFiyat(double fiyat) {
        this.fiyat = fiyat;
    }

    public String getUrunAdi() {
        return urunAdi;
    }

    public void setUrunAdi(String urunAdi) {
        this.urunAdi = urunAdi;
    }

    public String getBarkod() {
        return barkod;
    }

    public void setBarkod(String barkod) {
        this.barkod = barkod;
    }

    public int getGiren() {
        return giren;
    }

    public void setGiren(int giren) {
        this.giren = giren;
    }

    public int getCikan() {
        return cikan;
    }

    public void setCikan(int cikan) {
        this.cikan = cikan;
    }

    public int getKalan() {
        return kalan;
    }

    public void setKalan(int kalan) {
        this.kalan = kalan;
    }

    public String getUyeId() {
        return uyeId;
    }

    public void setUyeId(String uyeId) {
        this.uyeId = uyeId;
    }
}
