package co.edu.udea.compumovil.gr04_20191.lab1;

public class Dish {
    private String mDefaulTranslation;
    private String mMiwokTranslation;


    public Dish(String mDefaulTranslation, String mMiwokTranslation) {
        this.mDefaulTranslation = mDefaulTranslation;
        this.mMiwokTranslation = mMiwokTranslation;
    }

    public String getDefaulTranslation() {
        return mDefaulTranslation;
    }

    public String getMiwokTranslation() {
        return mMiwokTranslation;
    }
}
