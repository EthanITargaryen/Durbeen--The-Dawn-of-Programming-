package sample;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.security.spec.ECField;
import java.util.concurrent.TimeUnit;

public class CodeBox extends VBox {

    MotionButton motionButton;
    SoundButton soundButton;
    ObservableList<Node> observableList;
    SpriteView spriteView;
    boolean mainBlock = true;

    CodeBox(String abc, SpriteView spriteView, String mediaPath) {
        motionButton = new MotionButton(abc, spriteView);
        soundButton = new SoundButton(abc, mediaPath);
        observableList = this.getChildrenUnmodifiable();
        try {
            this.setWidth(((Pane) this.getParent()).getWidth());
        } catch (Exception e) {
            System.out.println("Ïn codebox constructor, exception caught: " + e);
        }
        this.spriteView = spriteView;
    }

    CodeBox(MotionButton conMotionButton, SoundButton conSoundButton) {
        motionButton = conMotionButton;
        soundButton = conSoundButton;
        observableList = this.getChildrenUnmodifiable();
        spriteView = motionButton.spriteView;
    }

    CodeBox() {
    }

    CodeBox underBlock(ObservableList q) {
        CodeBox a = new CodeBox(this.motionButton, this.soundButton);
        a.observableList = q;
        a.mainBlock = false;
        return a;
    }

    void refresh() {
        observableList = this.getChildrenUnmodifiable();
        for (Node p : observableList) {
            System.out.println(p);
        }
    }
//    void add(Node )

    void codeExecute() throws InterruptedException {
        //if (mainBlock)
            observableList = this.getChildrenUnmodifiable();
        /*for (Node b : observableList)
            System.out.println(b);*/

        new Thread(() -> {
            for (Node blockNode : observableList) {
                System.out.println("From thread second: " + blockNode);
                Platform.runLater(() -> {
                    System.out.println(blockNode.getClass());
                    if (blockNode.getClass().toString().contains("CodeBox")) {
                        fButton control = (fButton) (((CodeBox) blockNode).getChildren().get(0));
                        String text = control.getText();
                        String com = "";
                        double value = 0;
                        try {
                            com = ((TextField) control.getGraphic()).getText();
                            value = Double.parseDouble(Main.js.eval(com).get(0).value());
                        } catch (Exception e) {
                            System.out.println("Exception caUgHt: " + e);
                        }
                        switch (text) {
                            case "বার করো":
                                System.out.println(value + " বার করো");
                                int blocknode_size=((CodeBox) blockNode).getChildren().size()-2;

                                double finalValue = value;
                                new Thread(()->{

                                    for (int i = 0; i < finalValue; ++i) {

                                        Platform.runLater(()->{
                                            try {
                                                ((CodeBox) blockNode).codeExecute();
                                            } catch (Exception e) {
                                                System.out.println("Exception caught from repeat block: " + e);
                                            }
                                            /*try {
                                                wait();
                                            } catch (Exception e){
                                                System.out.println("Exception caught while waiting: " + e + "with i=" + i);
                                            }*/
                                        });

                                        try {
                                            Thread.sleep(blocknode_size*1000);
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        }

                                    }

                                }).start();


                                break;
                            case "যদি":
                                System.out.println("যদি " + com + " " + value);
                                if ((!com.equalsIgnoreCase("false")) && (!com.equals("0"))) {
                                    try {
                                        ((CodeBox) blockNode).codeExecute();
                                    } catch (Exception e) {
                                        System.out.println("Exception caught from repeat block: " + e);
                                    }
                                }
                        }
                    }
                    //continue
                    else {
                        fButton block;
                        block = (fButton) blockNode;
                        String style = block.getStyle();
                        String text = block.getText();
                        String com;
                        double value = 0;
                        try {
                            com = ((TextField) block.getGraphic()).getText();
                            if (style.contains("56255B")) {
                                System.out.println(Main.js.eval("double " + text + com + ";").get(0).value());
                            } else
                                value = Double.parseDouble(Main.js.eval(com).get(0).value());
                        } catch (Exception e) {
                            System.out.println("Exception caUght: " + e + " in " + text);
                        }
                        System.out.println(text);
                        switch (text) {
                            case "ঘর আগাও":
                                motionButton.moveForward(value);
                                System.out.println("AGACHCHE");
                            /*try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/

                                break;
                            case "ডানে ঘোরো":
                                motionButton.rotateIfTrueClockwise(true, value);
                                System.out.println("DAANE GHOORE");
                            /*try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/
                                break;
                            case "বামে ঘোরো":
                                motionButton.rotateIfTrueClockwise(false, value);
                                System.out.println("BAAME GHOORE");
                            /*try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }*/
                                break;
                            case "Set X":
                                motionButton.setX(value);
                                break;
                            case "Set Y":
                                motionButton.setY(value);
                                break;
                            case "Go To X":
                                motionButton.glideToPosition(2, value, spriteView.getY());
                                break;
                            case "Go To Y":
                                motionButton.glideToPosition(2, spriteView.getX(), value);
                                break;
                            case "Point Towards Mouse":
                                motionButton.pointToMousePointer();
                                break;
                            case "বাজাতে থাকো":
                                soundButton.playSound();
                                break;
                            case "সেকেন্ড ধরে বাজাও":
                                soundButton.playSound(value);
                                break;
                            case "ভলিউম কমাও":
                                soundButton.changeVolumePercentage(-value);
                                break;
                            case "ভলিউম বাড়াও":
                                soundButton.changeVolumePercentage(value);
                                break;
                            case "বিরত রাখো":
                                soundButton.pauseAllSounds();
                                break;
                            case "বন্ধ করো":
                                soundButton.stopAllSounds();
                                break;
                            case "শেষ":
                                System.out.println("Printed from end block");
                                return;
                            case "Wait":
                                try {
                                    TimeUnit.SECONDS.sleep((long) value);
                                } catch (Exception e) {
                                    System.out.println("Sleep exception: " + e);
                                }
                        }
                    }
                });


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //notifyAll();
            }
        }).start();
    }
}