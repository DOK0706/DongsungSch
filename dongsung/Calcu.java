package dongsung;

import sun.misc.REException;

import javax.swing.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

// 스마트폰 계산기 어플 화면과 비슷하게 구현
public class Calcu extends JFrame {

    private Image bufferImage;

    private Graphics screenGraphic;

    private Image zero = new ImageIcon("dongsung/images/0.png").getImage();
    private Image one = new ImageIcon("dongsung/images/1.png").getImage();
    private Image two = new ImageIcon("dongsung/images/2.png").getImage();
    private Image three = new ImageIcon("dongsung/images/3.png").getImage();
    private Image four = new ImageIcon("dongsung/images/4.png").getImage();
    private Image five = new ImageIcon("dongsung/images/5.png").getImage();
    private Image six = new ImageIcon("dongsung/images/6.png").getImage();
    private Image seven = new ImageIcon("dongsung/images/7.png").getImage();
    private Image eight = new ImageIcon("dongsung/images/8.png").getImage();
    private Image nine = new ImageIcon("dongsung/images/9.png").getImage();

    private ImageIcon button1_1I = new ImageIcon("dongsung/images/타원 1-1.png");
    private ImageIcon button1_2I = new ImageIcon("dongsung/images/타원 1-2.png");
    private ImageIcon button2_1I = new ImageIcon("dongsung/images/타원 2-1.png");
    private ImageIcon button2_2I = new ImageIcon("dongsung/images/타원 2-2.png");

    private JButton[] btn = new JButton[20];
    private JButton Cbutton = new JButton();
    private Image background = new ImageIcon("dongsung/images/배경.png").getImage();

    private Image plus_ = new ImageIcon("dongsung/images/플러스.png").getImage();
    private Image minus_ = new ImageIcon("dongsung/images/마이너스.png").getImage();
    private Image times_ = new ImageIcon("dongsung/images/타임즈.png").getImage();
    private Image obelus_ = new ImageIcon("dongsung/images/오벨루스.png").getImage();
    private Image pnnumber_ = new ImageIcon("dongsung/images/플마.png").getImage();
    private Image equal_ = new ImageIcon("dongsung/images/이퀄.png").getImage();
    private Image bracket_ = new ImageIcon("dongsung/images/브래킷.png").getImage();
    private Image percent_ = new ImageIcon("dongsung/images/퍼센트.png").getImage();
    private Image dot_ = new ImageIcon("dongsung/images/점.png").getImage();
    private Image delete_ = new ImageIcon("dongsung/images/지우기.png").getImage();
    private Image c_ = new ImageIcon("dongsung/images/C.png").getImage();

    private double number1, number2;
    private int inputnum;
    private String sign = "+";

    private double Result;
    private boolean MarkResult = false;
    AffineTransform affineTransform = new AffineTransform();
    FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
    Font HH, HB;

    public Calcu() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);

        try {
            HH = Font.createFont(Font.TRUETYPE_FONT, new File("dongsung/fonts/HH.ttf")).deriveFont(70f);
            GraphicsEnvironment he = GraphicsEnvironment.getLocalGraphicsEnvironment();
            he.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PROJECT/font/HH.ttf")));

            HB = Font.createFont(Font.TRUETYPE_FONT, new File("dongsung/fonts/HH.ttf")).deriveFont(40f);
            GraphicsEnvironment be = GraphicsEnvironment.getLocalGraphicsEnvironment();
            be.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PROJECT/font/HH.ttf")));
        } catch (IOException | FontFormatException e) {

        }

        setTitle("계산기");
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
        setSize(540, 1100);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        for (int i = 0; i < 20; i++) {
            btn[i] = new JButton(button1_1I);
            btn[i].setVisible(true);
            btn[i].setBorderPainted(false);
            btn[i].setContentAreaFilled(false);
            btn[i].setFocusPainted(false);
            mouse(i);
        }

        Cbutton = new JButton(button1_1I);
        Cbutton.setVisible(true);
        Cbutton.setBorderPainted(false);
        Cbutton.setContentAreaFilled(false);
        Cbutton.setFocusPainted(false);

        Cbutton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                Cbutton.setIcon(button1_2I);
                Cbutton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                Cbutton.setIcon(button1_1I);
                Cbutton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(inputnum);
                number1 = 0;
                sign = "";
                number2 = 0;
                inputnum = 0;
            }
        });
        add(Cbutton);

        Cbutton.setBounds(35, 500, 108, 108);
        btn[7].setBounds(35, 620, 108, 108);
        btn[4].setBounds(35, 740, 108, 108);
        btn[1].setBounds(35, 860, 108, 108);
        btn[10].setBounds(35, 980, 108, 108);

        btn[18].setBounds(155, 500, 108, 108);
        btn[8].setBounds(155, 620, 108, 108);
        btn[5].setBounds(155, 740, 108, 108);
        btn[2].setBounds(155, 860, 108, 108);
        btn[0].setBounds(155, 980, 108, 108);

        btn[17].setBounds(275, 500, 108, 108);
        btn[9].setBounds(275, 620, 108, 108);
        btn[6].setBounds(275, 740, 108, 108);
        btn[3].setBounds(275, 860, 108, 108);
        btn[11].setBounds(275, 980, 108, 108);

        btn[16].setBounds(395, 500, 108, 108);
        btn[15].setBounds(395, 620, 108, 108);
        btn[14].setBounds(395, 740, 108, 108);
        btn[13].setBounds(395, 860, 108, 108);
        btn[12].setBounds(395, 980, 108, 108);

    }

    public void mouse(int i) {
        btn[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                btn[i].setIcon(button1_2I);
                btn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btn[i].setIcon(button1_1I);
                btn[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(inputnum);
                if (i == 19) {
                    number1 = 0;
                    sign = "";
                    number2 = 0;
                    inputnum = 0;
                }
                switch (inputnum) {
                    case 0:
                        number1 = i;
                        inputnum = inputnum + 1;
                        break;
                    case 1:
                        MarkResult = false;

                        switch (i) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9: TenOver(1, i);
                                    break;

                            case 13:
                                sign = "+";
                                inputnum = inputnum + 1;
                                break;
                            case 14:
                                sign = "-";
                                inputnum++;
                                break;
                            case 15:
                                sign = "×";
                                inputnum++;
                                break;
                            case 16:
                                sign = "÷";
                                inputnum++;
                                break;
                            case 17:
                                sign = "%";
                                inputnum++;
                                break;
                            case 18:
                                sign = "()";
                                inputnum++;
                                break;
                        }
                        break;
                    case 2:
                        number2 = i;
                        inputnum = inputnum + 1;
                        break;
                    case 3:
                        if (i == 12) {
                            MarkResult = true;
                            getGraphics();
                            inputnum = 0;
                            break;
                        }
                        switch (i) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                            case 9: TenOver(2, i);
                                    break;
                        }
                        break;

                }
            }
        });
        add(btn[i]);
    }

    public double GetResult() {
        if (inputnum == 2) {
            if (Objects.equals(sign, "%")) {
                number1 = number1 * 0.01;
                return number1;
            }
        }
        if (inputnum >= 3 || inputnum == 0) {
            if (Objects.equals(sign, "+")) {
                Result = number1 + number2;
                return Result;
            } else if (Objects.equals(sign, "-")) {
                Result = number1 - number2;
                return Result;
            } else if (Objects.equals(sign, "×")) {
                Result = number1 * number2;
                return Result;
            } else if (Objects.equals(sign, "÷")) {
                Result = number1 / number2;
                return Result;
            } else if (Objects.equals(sign, "%")) {
                number2 = number2 * 0.01;
                return number2;
            }
        }
        return 0;
    }
    public void TenOver(int numnum, int alpha) {
        if (numnum==1) {
            double beta = 10 * number1;
            number1 = beta + alpha;
        }
        if (numnum == 2) {
            double beta = 10 * number2;
            number2 = beta + number2;
        }
    }
    public void paint(Graphics g) {
        bufferImage = createImage(540, 1100);
        screenGraphic = bufferImage.getGraphics();
        ScreenDraw(screenGraphic);
        IconDraw(screenGraphic);
        ModifiDraw(screenGraphic);
        ResultDraw(screenGraphic);
        g.drawImage(bufferImage, 0, 0, null);
    }

    public void ScreenDraw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        this.repaint();
    }

    public void IconDraw(Graphics i) {
        i.drawImage(c_, 78, 543, null);
        i.drawImage(seven, 78, 663, null);
        i.drawImage(four, 78, 780, null);
        i.drawImage(one, 80, 900, null);
        i.drawImage(pnnumber_, 72, 1023, null);

        i.drawImage(bracket_, 198, 543, null);
        i.drawImage(eight, 198, 663, null);
        i.drawImage(five, 198, 780, null);
        i.drawImage(two, 198, 900, null);
        i.drawImage(zero, 198, 1023, null);

        i.drawImage(percent_, 318, 543, null);
        i.drawImage(nine, 318, 663, null);
        i.drawImage(six, 318, 780, null);
        i.drawImage(three, 318, 900, null);
        i.drawImage(dot_, 318, 1023, null);

        i.drawImage(obelus_, 438, 543, null);
        i.drawImage(times_, 438, 663, null);
        i.drawImage(minus_, 438, 780, null);
        i.drawImage(plus_, 438, 900, null);
        i.drawImage(equal_, 438, 1023, null);
        // this.repain();
    }

    public void ModifiDraw(Graphics j) {
        j.setColor(Color.BLACK);
        j.setFont(HH);
        switch (inputnum) {
            case 1:
                j.drawString("" + number1, 350, 200);
                break;
            case 2:
                j.drawString(number1 + " " + sign, 300, 200);
                break;
            case 3:
                j.drawString(number1 + " " + sign + " " + number2, 200, 200);
                break;
            case 0:
                if (MarkResult) {
                    j.drawString("" + GetResult(), 350, 200);
                    break;
                }
        }
    }

    public void ResultDraw(Graphics k) {
        k.setColor(Color.GRAY);
        k.setFont(HB);
        if (!MarkResult) k.drawString("" + GetResult(), 350, 300);
    }

    public static void main(String[] args) {
        new Calcu();
    }

}