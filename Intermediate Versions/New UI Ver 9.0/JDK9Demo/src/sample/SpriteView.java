package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.Serializable;

import static java.lang.StrictMath.PI;

public class SpriteView extends ImageView implements Serializable {

    public void setOrientAngle(double orientAngle) {
        this.orientAngle = orientAngle;
    }

    private double orientAngle;
    public static double ax,ay,bx,by,cx,cy,dx,dy;
    private double orgSceneX, orgSceneY, orgTranslateX, orgTranslateY;
    private Label label;
    private double one_side=90;
    private ImageView back_image;

    SpriteView(Image image, Label k,ImageView back)
    {
        super(image);
        label = k;
        back_image = back;
        this.setPreserveRatio(false);
        this.setFitHeight(one_side);
        this.setFitWidth(one_side);
        this.setPickOnBounds(true);
        /*this.setLayoutX(0);
        this.setLayoutY(0);*/
        this.setTranslateX(100);
        this.setTranslateY(50);
        this.setOnMousePressed(event -> onMousePressed(event));
        this.setOnMouseDragged(event -> onMouseDragged(event));
        orientAngle = 0;
    }

    void rotateDegree(double degree)
    {
        orientAngle += degree*PI/180.0;
    }

    public double getOrientAngle() {
        return orientAngle;
    }

    public void resize()
    {
        this.setFitHeight(one_side);
        this.setFitWidth(one_side);
    }

    public void find_sprite_position()
    {
        ax = this.getBoundsInParent().getMinX();
        by = this.getBoundsInParent().getMinY();
        cx = this.getBoundsInParent().getMaxX();
        dy = this.getBoundsInParent().getMaxY();

        double angle = this.getRotate();
        double rad = Math.toRadians(angle);

        ay = by + one_side*Math.cos(rad);
        cy = dy - one_side*Math.cos(rad);
        bx = ax + one_side*Math.sin(rad);
        dx = cx - one_side*Math.sin(rad);

        System.out.println("A : " + ax + " , " + ay);
        System.out.println("B : " + bx + " , " + by);
        System.out.println("C : " + cx + " , " + cy);
        System.out.println("D : " + dx + " , " + dy);

        /*label.setText(" Sprite Position " + "X: "+(int)this.getBoundsInParent().getMinX() +" , Y: "+ (int)this.getBoundsInParent().getMinY()+
                "  XX: "+(int)this.getBoundsInParent().getMaxX()+" , "+ "YY: "+(int)this.getBoundsInParent().getMaxY()+
                " Rot : "+this.getRotate());
        */
        label.setText("Sprite Position : X: "+(int)ax +" , "+(int)ay);

    }

    public void onMousePressed(MouseEvent me)
    {
        orgSceneX = me.getSceneX();
        orgSceneY = me.getSceneY();

        System.out.println("It happened at " + orgSceneX + "," + orgSceneY );

        orgTranslateX = this.getTranslateX();
        orgTranslateY = this.getTranslateY();
        //sprite_label.setText("X: "+event.getX()+" , "+event.getSceneX());
        System.out.println("X: "+me.getX()+" , "+me.getSceneX());

        find_sprite_position();
    }

    public void onMouseDragged(MouseEvent me)
    {

        double newTranslateX = orgTranslateX + me.getSceneX() - orgSceneX;
        double newTranslateY = orgTranslateY + me.getSceneY() - orgSceneY;

        ((ImageView)(me.getSource())).setTranslateX(newTranslateX);
        ((ImageView)(me.getSource())).setTranslateY(newTranslateY);

        System.out.println("Source image is at " + this.getTranslateX() + "," + this.getTranslateY());
        System.out.println(" In Parent at " + this.getBoundsInParent());
        System.out.println(" In Local  at " + this.getBoundsInLocal());

        find_sprite_position();

    }

    public void shrink(double percentage)
    {
        double val = this.getFitWidth()-this.getFitWidth()*(percentage/100.0);

        this.setFitWidth(val);
        this.setFitHeight(val);

        one_side = val;
    }

    public void enlarge(double percentage)
    {
        this.setFitWidth(this.getFitWidth()+this.getFitWidth()*(percentage/100.0));
        this.setFitHeight(this.getFitHeight()+this.getFitHeight()*(percentage/100.0));
    }

    public String kon_color(double x, double y)
    {
        Color c = back_image.getImage().getPixelReader().getColor((int) x, (int) y);

        //System.out.println(color.getRed() + "," +color.getBlue() + "," + color.getGreen());
        String hex = String.format( "#%02X%02X%02X",
                (int)( c.getRed() * 255 ),
                (int)( c.getGreen() * 255 ),
                (int)( c.getBlue() * 255 ) );
        System.out.println(hex);

        String text = "Color : Red = " + c.getRed()*100 + "% , blue = " + c.getBlue()*100
                + "% , green = " + c.getGreen()*100 + "%";

        //color_label.setText(text);

        double red_part = c.getRed()*100;
        double blue_part = c.getBlue()*100;
        double green_part = c.getGreen()*100;
        double sum_color = red_part+blue_part+green_part;

        String final_color;

        if(sum_color >=0 && sum_color<=20)
            final_color="Black";
        else if(sum_color >=80 && sum_color<=100)
            final_color="White";
        else if(red_part>blue_part && red_part>green_part)
            final_color = "Red";
        else if(blue_part>red_part && blue_part>green_part)
            final_color = "Blue";
        else
            final_color = "Green";

        return final_color;
    }

    public boolean ei_color_er_upor_aase_kina(String col)
    {
        /*System.out.println("Point A Color : " + kon_color(SpriteView.ax,SpriteView.ay));
        System.out.println("Point B Color : " + kon_color(SpriteView.bx,SpriteView.by));
        System.out.println("Point C Color : " + kon_color(SpriteView.cx,SpriteView.cy));
        System.out.println("Point D Color : " + kon_color(SpriteView.dx,SpriteView.dy));*/

        int cnt = 0;

        if(kon_color(SpriteView.ax,SpriteView.ay)==col)
            cnt++;
        if(kon_color(SpriteView.bx,SpriteView.by)==col)
            cnt++;
        if(kon_color(SpriteView.cx,SpriteView.cy)==col)
            cnt++;
        if(kon_color(SpriteView.dx,SpriteView.dy)==col)
            cnt++;


        if(cnt==4)
            return true;

        return false;
    }

    /*
    <children>
            <ImageView fx:id="imageView" fitHeight="169.0" fitWidth="228.0" pickOnBounds="true" preserveRatio="true" />
    </children>
     */
}
