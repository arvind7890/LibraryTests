package dnivra.droid.arvind.coloredlinepatternpicker;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TableLayout;
import android.widget.TableRow;

public class SettingsScreen extends LinearLayout  implements SeekBar.OnSeekBarChangeListener,CompoundButton.OnCheckedChangeListener{



    private int rectColor;
    private int lineWidth;
    private float lineGap;
    private float lineDash;
    private float[] LineHsv=new float[3];
    private int lineAlpha;


    //private GradientDrawable drawable;
    private DashDrawable lineDrawable;

    Context context;

    private AppCompatSeekBar seekHue;
    private AppCompatSeekBar seekAlfa;
    private AppCompatSeekBar seekLineWidth;
    private AppCompatSeekBar seekDash;
    private AppCompatSeekBar seekGap;

    private AppCompatCheckBox selColor;
    private AppCompatCheckBox selDashGap;

    TableLayout tableLayout;
    View colorSelectView,dashgapSlectView;

    ImageView lineView;


    public static TripLineParam tlp;



    public int getRectColor() {
        return rectColor;
    }

    public void setRectColor(int rectColor) {
        this.rectColor = rectColor;
    }


    public static TripLineParam getTripLineParam(){
        return tlp;
    }
    public static void setPolyLineParam(long id,long tripid, int color,String lnpattern,int lnwidth,float dashWidth, float dashGap){

        tlp=new TripLineParam(color,lnpattern,lnwidth,dashWidth,dashGap);
    }

    public SettingsScreen(Context context) {
        super(context);
        this.context = context;
    }

    public SettingsScreen(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.context = context;
        //tlp=((TripLineParamProvider)context).getTripLineParam();
        if(tlp==null)tlp=new TripLineParam();
        rectColor=tlp.getColor();

        Color.colorToHSV(rectColor,LineHsv);
        lineAlpha=Color.alpha(rectColor);

        lineWidth=tlp.getLnwidth();
        lineGap=tlp.getDashGap();
        lineDash=tlp.getDashWidth();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v=inflater.inflate(R.layout.settings, this, true);
        tableLayout=v.findViewById(R.id.tableLayout);
        selColor= v.findViewById(R.id.selColor);
        selDashGap= v.findViewById(R.id.selDashGap);
        selColor.setOnCheckedChangeListener(this);
        selDashGap.setOnCheckedChangeListener(this);


        //imageView= v.findViewById(R.id.color_preview);
        lineView= v.findViewById(R.id.line_preview);

        //drawable = (GradientDrawable) imageView.getDrawable();
        //drawable.setColor(rectColor);

        Log.e("GETTAG","TAG:"+lineView.getTag());

        lineDrawable=new DashDrawable(tlp.getColor(),tlp.getLnwidth(),tlp.getDashWidth(),tlp.getDashGap());
        lineView.setImageDrawable(lineDrawable);
        lineDrawable.setLineColor(rectColor);


        seekLineWidth= v.findViewById(R.id.seek_width);
        seekLineWidth.setMax(30);
        seekLineWidth.setProgress(lineWidth);
        seekLineWidth.setOnSeekBarChangeListener(this);
    }

    public SettingsScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public SettingsScreen(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.context = context;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar==seekHue){
            LineHsv=new float[]{progress, 1, 1};
            rectColor= Color.HSVToColor(lineDrawable.getAlpha(),LineHsv);

            //drawable.setColor(rectColor);
            lineDrawable.setLineColor(rectColor);
            lineView.invalidate();
            tlp.setColor(rectColor);
            Log.e("PLP","plp is "+tlp);
        }
        if(seekBar==seekAlfa){

            rectColor= Color.HSVToColor(progress,LineHsv);
            lineDrawable.setLineAlpha(progress);
            lineView.invalidate();
            tlp.setColor(rectColor);
            Log.e("PLP","plp is "+tlp);
        }
        if(seekBar==seekLineWidth){
            lineDrawable.setLineWidth(progress);
            lineWidth=progress;
            lineView.invalidate();
            tlp.setLnwidth(progress);
            Log.e("PLP","plp is "+tlp);
        }
        if(seekBar==seekDash){
            lineDash=progress;
            tlp.setDashWidth(progress);
            Log.e("SEEKDASHCHANGE","dash:"+tlp.getDashWidth());
            lineDrawable.setDashLength(progress);
            lineView.invalidate();
            Log.e("PLP","plp is "+tlp);
        }
        if(seekBar==seekGap){
            lineGap=progress;
            tlp.setDashGap(progress);
            Log.e("SEEKGAPCHANGE","gap:"+tlp.getDashGap());
            //lineDrawable.
            lineDrawable.setGapLength(progress);
            lineView.invalidate();

            //lineView.setImageDrawable(lineDrawable);

        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
    public boolean addColorSelection(){
        TableRow colorRow=new TableRow(context);
        colorRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
        LayoutInflater lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        colorSelectView=lf.inflate(R.layout.set_color,colorRow);
        Log.e("CHECKEDCHANGE","going to add layout to table");
        tableLayout.addView(colorSelectView);
        seekHue= colorSelectView.findViewById(R.id.seek_hue);
        seekHue.setMax(360);
        seekHue.setProgress((int)LineHsv[0]);

        seekAlfa= colorSelectView.findViewById(R.id.seek_alpha);
        seekAlfa.setMax(255);
        seekAlfa.setProgress(Color.alpha(rectColor));
        seekHue.setOnSeekBarChangeListener(this);
        seekAlfa.setOnSeekBarChangeListener(this);
        return true;
    }
    public boolean removeColorSelection(){
        if(colorSelectView!=null)tableLayout.removeView(colorSelectView);
        colorSelectView=null;
        return false;
    }
    public boolean addDashGapSelection(){

        TableRow colorRow=new TableRow(context);
        colorRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT,TableRow.LayoutParams.WRAP_CONTENT));
        LayoutInflater lf=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dashgapSlectView=lf.inflate(R.layout.set_dash_gap,colorRow);
        tableLayout.addView(dashgapSlectView);

        seekDash= dashgapSlectView.findViewById(R.id.seek_dash);
        seekDash.setMax(200);
        seekDash.setProgress(5);
        seekDash.setOnSeekBarChangeListener(this);

        seekGap= dashgapSlectView.findViewById(R.id.seek_gap);
        seekGap.setMax(200);
        seekGap.setProgress(5);
        seekGap.setOnSeekBarChangeListener(this);

        return true;
    }
    public boolean removeDashGapSelection(){
        if(dashgapSlectView!=null)tableLayout.removeView(dashgapSlectView);
        dashgapSlectView=null;
        return false;
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(buttonView==selColor){
            Log.e("CHECKEDCHANGE","checked status change");
            if(selColor.isChecked()){
                addColorSelection();
            }
            if(!selColor.isChecked()){
                removeColorSelection();
            }
        }
        if(buttonView==selDashGap){
            Log.e("CHECKEDCHANGE","checked status change");
            if(selDashGap.isChecked()){
                addDashGapSelection();
            }
            if(!selDashGap.isChecked()){
                removeDashGapSelection();
            }
        }
    }
}
