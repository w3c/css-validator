package org.w3c.css.media;


import java.util.ArrayList;

public class Media {
    boolean only;
    boolean not;
    String media;
    ArrayList<MediaFeature> features;

    public Media() {
    }

    public Media(String media) {
        this.media = media;
    }

    public void setOnly(boolean only) {
        this.only = only;
    }

    public boolean getOnly() {
        return not;
    }

    public void setNot(boolean not) {
        this.not = not;
    }

    public boolean getNot() {
        return not;
    }

    public void setMedia(String media) {
        this.media = media;
    }

    public String getMedia() {
        return media;
    }

    public void addFeature(MediaFeature mf) {
        if (features == null) {
            features = new ArrayList<MediaFeature>();
        }
        features.add(mf);
    }

    public String toString() {
        // simple case, return the media string
        if (!only && !not && features == null) {
            return media;
        }
        StringBuilder sb = new StringBuilder();
        boolean printAnd = false;
        if (only) {
            sb.append("only ");
        } else if (not) {
            sb.append("not ");
        }
        // special case "media and (...)" or directly "(...)"
        if (media != null) {
            sb.append(media);
            printAnd = true;
        }
        if (features != null) {
            for (MediaFeature mf : features) {
                if (printAnd) {
                    sb.append(" and");
                } else {
                    printAnd = true;
                }
                sb.append(" (").append(mf.toString()).append(')');
            }
        }
        return sb.toString();
    }
}
