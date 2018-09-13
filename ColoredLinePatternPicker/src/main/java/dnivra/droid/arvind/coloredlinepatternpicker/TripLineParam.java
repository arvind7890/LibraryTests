package dnivra.droid.arvind.coloredlinepatternpicker;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class TripLineParam implements Parcelable {
   private long id;
    private long tripid;
    private int color;
    private  String lnpattern;
    private int lnwidth;
    private float dashWidth;
    private float dashGap;


    public TripLineParam() {
        color= Color.parseColor("#801B60FE");
        lnwidth=16;
        dashGap=5;
        dashWidth=5;
    }
    public TripLineParam(Parcel p){
        this.id=p.readLong();
        this.tripid=p.readLong();
        this.color=p.readInt();
        this.lnpattern=p.readString();
        this.lnwidth=p.readInt();
        this.dashGap=p.readFloat();
        this.dashWidth=p.readFloat();
    }
    public TripLineParam(Cursor p){
        this.id=p.getLong(0);
        this.tripid=p.getLong(1);
        this.color=p.getInt(2);
        this.lnpattern=p.getString(3);
        this.lnwidth=p.getInt(4);
        this.dashGap=p.getFloat(5);
        this.dashWidth=p.getFloat(6);
        Log.e("THIS PARAM",toString());
    }
    public TripLineParam(int color, String lnpattern, int lnwidth, float dashWidth, float dashGap) {
        this.color = color;
        this.lnpattern = lnpattern;
        this.lnwidth = lnwidth;
        this.dashWidth=dashWidth;
        this.dashGap=dashGap;
    }

    public float getDashWidth() {
        return dashWidth;
    }

    public void setDashWidth(float dashWidth) {
        this.dashWidth = dashWidth;
    }

    public float getDashGap() {
        return dashGap;
    }

    public void setDashGap(float dashGap) {
        this.dashGap = dashGap;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getLnpattern() {
        return lnpattern;
    }

    public void setLnpattern(String lnpattern) {
        this.lnpattern = lnpattern;
    }

    public int getLnwidth() {
        return lnwidth;
    }

    public void setLnwidth(int lnwidth) {
        this.lnwidth = lnwidth;
    }

    @Override
    public int describeContents() {
        return this.hashCode();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeLong(tripid);
        dest.writeInt(color);
        dest.writeString(lnpattern);
        dest.writeInt(lnwidth);
        dest.writeFloat(dashGap);
        dest.writeFloat(dashWidth);
    }
    public static final Creator<TripLineParam> CREATOR
            = new Creator<TripLineParam>(){
        @Override
        public TripLineParam createFromParcel(Parcel source) {
            return new TripLineParam(source);
        }

        @Override
        public TripLineParam[] newArray(int size) {
            return new TripLineParam[size];
        }
    };

    @Override
    public String toString() {
        return "TripLineParam{" + "id=" + id + ", tripid=" + tripid + ", color=" + color + ", lnpattern='" + lnpattern + '\'' + ", lnwidth=" + lnwidth + ", dashWidth=" + dashWidth + ", dashGap=" + dashGap + '}';
    }
}
