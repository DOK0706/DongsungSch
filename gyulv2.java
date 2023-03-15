package dongsung;

import javax.swing.*;
import javax.xml.stream.FactoryConfigurationError;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class gyulv2 extends JFrame {

    private Image bufferImage;

    private Graphics screenGraphic;

    private Image w_b = new ImageIcon("dongsung/gimages/w_b.png").getImage();
    private Image g_b = new ImageIcon("dongsung/gimages/g_b.png").getImage();
    private Image b_b = new ImageIcon("dongsung/gimages/b_b.png").getImage();

    private Image r_t = new ImageIcon("dongsung/gimages/r_t.png").getImage();
    private Image r_s = new ImageIcon("dongsung/gimages/r_s.png").getImage();
    private Image r_c = new ImageIcon("dongsung/gimages/r_c.png").getImage();

    private Image y_t = new ImageIcon("dongsung/gimages/y_t.png").getImage();
    private Image y_s = new ImageIcon("dongsung/gimages/y_s.png").getImage();
    private Image y_c = new ImageIcon("dongsung/gimages/y_c.png").getImage();

    private Image b_t = new ImageIcon("dongsung/gimages/b_t.png").getImage();
    private Image b_s = new ImageIcon("dongsung/gimages/b_s.png").getImage();
    private Image b_c = new ImageIcon("dongsung/gimages/b_c.png").getImage();

    private Image basic = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image select = new ImageIcon("dongsung/gimages/select.png").getImage();
    private Image edge1 = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image edge2 = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image edge3 = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image edge4 = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image edge5 = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image edge6 = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image edge7 = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image edge8 = new ImageIcon("dongsung/gimages/basic.png").getImage();
    private Image edge9 = new ImageIcon("dongsung/gimages/basic.png").getImage();

    private Image gy = new ImageIcon("dongsung/gimages/hap.png").getImage();
    private Image quit = new ImageIcon("dongsung/gimages/quit.png").getImage();
    private Image end = new ImageIcon("dongsung/gimages/end.png").getImage();

    private JButton[] btn = new JButton[9];
    private JButton Gy = new JButton();
    private JButton Qu = new JButton();
    boolean[] but = new boolean[9];
    int[] arr = new int[9];
    int[] coarr = new int[20];
    int[] colog = new int[20];
    int stac, points, cnt;

    boolean END = false;
    int cocnt = 0;
    private Image background = new ImageIcon("dongsung/gimages/background.png").getImage();

    AffineTransform affineTransform = new AffineTransform();
    FontRenderContext frc = new FontRenderContext(affineTransform, true, true);
    Font HH, HB;

    public gyulv2() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        Random random = new Random();

        try {
            HH = Font.createFont(Font.TRUETYPE_FONT, new File("dongsung/fonts/HH.ttf")).deriveFont(70f);
            GraphicsEnvironment he = GraphicsEnvironment.getLocalGraphicsEnvironment();
            he.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PROJECT/font/HH.ttf")));

            HB = Font.createFont(Font.TRUETYPE_FONT, new File("dongsung/fonts/HH.ttf")).deriveFont(130f);
            GraphicsEnvironment be = GraphicsEnvironment.getLocalGraphicsEnvironment();
            be.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PROJECT/font/HH.ttf")));
        } catch (IOException | FontFormatException e) {

        }

        setTitle("결합게임");
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
        setSize(1440, 900);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(null);

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (((random.nextInt(3) + 1) * 100) + ((random.nextInt(3) + 1) * 10) + (random.nextInt(3) + 1));
        }
        // 중복된 수가 있으면 다시 무작위의 수 할당
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) { // 중복된 수가 있으면
                    arr[j] = (((random.nextInt(3) + 1) * 100) + ((random.nextInt(3) + 1) * 10) + (random.nextInt(3) + 1));
                    j = i + 1; // 다시 처음부터 검사
                }
            }
        }
        System.out.println("카드 배열: " + Arrays.toString(arr));
        System.out.print("가능한 합: ");
        for (int i = 0; i < 9 - 2; i++) {
            for (int j = i + 1; j < 9 - 1; j++) {
                for (int k = j + 1; k < 9; k++) {
                    if (cal(arr[i], arr[j]) == arr[k]) {
                        System.out.printf("%d/%d/%d  ", (i + 1), (j + 1), (k + 1));
                        coarr[stac] = (((i + 1) * 100) + ((j + 1) * 10) + (k + 1));
                        stac++;
                        break;
                    }
                }
            }
        }
        System.out.println();
        System.out.println("총 합의 갯수: " + stac);
        for (int i = 0; i < 9; i++) {
            btn[i] = new JButton();
            btn[i].setVisible(true);
            btn[i].setBorderPainted(false);
            btn[i].setContentAreaFilled(false);
            btn[i].setFocusPainted(false);
            mouse(i);
        }
        Mouse();
        MOuse();

        btn[0].setBounds(500, 50, 230, 230);
        btn[1].setBounds(800, 50, 230, 230);
        btn[2].setBounds(1100, 50, 230, 230);

        btn[3].setBounds(500, 300, 230, 230);
        btn[4].setBounds(800, 300, 230, 230);
        btn[5].setBounds(1100, 300, 230, 230);

        btn[6].setBounds(500, 550, 230, 230);
        btn[7].setBounds(800, 550, 230, 230);
        btn[8].setBounds(1100, 550, 230, 230);

        Gy.setBounds(270, 325, 230, 230);
        Qu.setBounds(270, 575, 230, 230);
    }

    public static int cal(int a, int b) {
        int result = 0;
        if (a / 100 == b / 100) {
            result = (a / 100) * 100 + result;
        } else result = (6 - (a / 100) - (b / 100)) * 100 + result;

        if ((a / 10) % 10 == (b / 10) % 10) {
            result = ((a / 10) % 10) * 10 + result;
        } else result = (6 - ((a / 10) % 10) - ((b / 10) % 10)) * 10 + result;

        if (a % 10 == b % 10) {
            result = (a % 10) + result;
        } else result = 6 - (a % 10) - (b % 10) + result;

        return result;
    }

    public Image give1(int num) {
//        if (num % 1000 / 100 == 1) System.out.print("빨강색");
//        else if (num % 1000 / 100 == 2) System.out.print("노랑색");
//        else if (num % 1000 / 100 == 3) System.out.print("파랑색");
//
//        if (num % 100 / 10 == 1) System.out.print(" 삼각형에");
//        else if (num % 100 / 10 == 2) System.out.print(" 사각형에");
//        else if (num % 100 / 10 == 3) System.out.print(" 원에");

        if ((num % 1000 / 100 == 1) && (num % 100 / 10 == 1)) return r_t;
        else if ((num % 1000 / 100 == 1) && (num % 100 / 10 == 2)) return r_s;
        else if ((num % 1000 / 100 == 1) && (num % 100 / 10 == 3)) return r_c;

        else if ((num % 1000 / 100 == 2) && (num % 100 / 10 == 1)) return y_t;
        else if ((num % 1000 / 100 == 2) && (num % 100 / 10 == 2)) return y_s;
        else if ((num % 1000 / 100 == 2) && (num % 100 / 10 == 3)) return y_c;

        else if ((num % 1000 / 100 == 3) && (num % 100 / 10 == 1)) return b_t;
        else if ((num % 1000 / 100 == 3) && (num % 100 / 10 == 2)) return b_s;
        else if ((num % 1000 / 100 == 3) && (num % 100 / 10 == 3)) return b_c;
        return null;
    }

    public Image give2(int num) {
//        if (num % 10 == 1) System.out.print(" 흰색배경");
//        else if (num % 10 == 2) System.out.print(" 회색배경");
//        else if (num % 10 == 3) System.out.print(" 검정배경");
        if (num % 10 == 1) return w_b;
        else if (num % 10 == 2) return g_b;
        else if (num % 10 == 3) return b_b;
        return null;
    }

    public void insert(int num) {
        int correct = 0;
        if (cnt == 3) {

            for (int i = 0; i < but.length; i++) {
                if (but[i] == true) {
                    for (int j = i + 1; j < but.length; j++) {
                        if (but[j] == true) {
                            for (int k = j + 1; k < but.length; k++) {
                                if (but[k] == true) {
                                    correct = ((i + 1) * 100 + ((j + 1) * 10) + (k + 1));
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    break;
                }
            }
            System.out.printf("correct: " + correct);
            for (int i = 0; i < 20; i++) {
                if (coarr[i] == correct && correct != colog[cocnt]) {
                    System.out.println(" 합 성공");
                    points = points + 1;
                    colog[cocnt] = correct;
                    cocnt++;
                    break;
                } else {
                    if (i == 19) {
                        System.out.println("합 실패");
                        points = points - 1;
                        break;
                    }
                }
            }
            cnt = 0;
            for (int i = 0; i < 9; i++) {
                but[i] = false;
            }
            edge1 = basic;
            edge2 = basic;
            edge3 = basic;
            edge4 = basic;
            edge5 = basic;
            edge6 = basic;
            edge7 = basic;
            edge8 = basic;
            edge9 = basic;
        }
    }

    public void paint(Graphics g) {
        bufferImage = createImage(1440, 900);
        screenGraphic = bufferImage.getGraphics();
        ScreenDraw(screenGraphic);
        IconDraw(screenGraphic);
        PointDraw(screenGraphic);
        LogDraw(screenGraphic);
        EndDraw(screenGraphic);
        g.drawImage(bufferImage, 0, 0, null);
    }

    public void ScreenDraw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        this.repaint();
    }


    public void IconDraw(Graphics i) {
        i.drawImage(give2(arr[0]), 500, 50, null);
        i.drawImage(give2(arr[1]), 800, 50, null);
        i.drawImage(give2(arr[2]), 1100, 50, null);

        i.drawImage(give2(arr[3]), 500, 300, null);
        i.drawImage(give2(arr[4]), 800, 300, null);
        i.drawImage(give2(arr[5]), 1100, 300, null);

        i.drawImage(give2(arr[6]), 500, 550, null);
        i.drawImage(give2(arr[7]), 800, 550, null);
        i.drawImage(give2(arr[8]), 1100, 550, null);

        i.drawImage(give1(arr[0]), 560, 110, null);
        i.drawImage(give1(arr[1]), 860, 110, null);
        i.drawImage(give1(arr[2]), 1160, 110, null);

        i.drawImage(give1(arr[3]), 560, 360, null);
        i.drawImage(give1(arr[4]), 860, 360, null);
        i.drawImage(give1(arr[5]), 1160, 360, null);

        i.drawImage(give1(arr[6]), 560, 610, null);
        i.drawImage(give1(arr[7]), 860, 610, null);
        i.drawImage(give1(arr[8]), 1160, 610, null);

        i.drawImage(edge1, 525, 75, null);
        i.drawImage(edge2, 825, 75, null);
        i.drawImage(edge3, 1125, 75, null);

        i.drawImage(edge4, 525, 325, null);
        i.drawImage(edge5, 825, 325, null);
        i.drawImage(edge6, 1125, 325, null);

        i.drawImage(edge7, 525, 575, null);
        i.drawImage(edge8, 825, 575, null);
        i.drawImage(edge9, 1125, 575, null);

        i.drawImage(gy, 270, 325, null);
        i.drawImage(quit, 270, 575, null);
    }

    public void PointDraw(Graphics j) {
        j.setColor(Color.BLACK);
        j.setFont(HH);
        j.drawString("점수: " + points, 300, 860);
    }

    public void LogDraw(Graphics n) {
        n.setFont(HH);
        n.setColor(Color.BLUE);
        if (colog[0] != 0)
            n.drawString("" + (colog[0] % 1000 / 100) + "/" + (colog[0] % 100 / 10) + "/" + (colog[0] % 10), 35, 75);
        if (colog[1] != 0)
            n.drawString("" + (colog[1] % 1000 / 100) + "/" + (colog[1] % 100 / 10) + "/" + (colog[1] % 10), 35, 150);
        if (colog[2] != 0)
            n.drawString("" + (colog[2] % 1000 / 100) + "/" + (colog[2] % 100 / 10) + "/" + (colog[2] % 10), 35, 225);
        if (colog[3] != 0)
            n.drawString("" + (colog[3] % 1000 / 100) + "/" + (colog[3] % 100 / 10) + "/" + (colog[3] % 10), 35, 300);
        if (colog[4] != 0)
            n.drawString("" + (colog[4] % 1000 / 100) + "/" + (colog[4] % 100 / 10) + "/" + (colog[4] % 10), 35, 375);
        if (colog[5] != 0)
            n.drawString("" + (colog[5] % 1000 / 100) + "/" + (colog[5] % 100 / 10) + "/" + (colog[5] % 10), 35, 450);
        if (colog[6] != 0)
            n.drawString("" + (colog[6] % 1000 / 100) + "/" + (colog[6] % 100 / 10) + "/" + (colog[6] % 10), 35, 525);
        if (colog[7] != 0)
            n.drawString("" + (colog[7] % 1000 / 100) + "/" + (colog[7] % 100 / 10) + "/" + (colog[7] % 10), 35, 600);
        if (colog[8] != 0)
            n.drawString("" + (colog[8] % 1000 / 100) + "/" + (colog[8] % 100 / 10) + "/" + (colog[8] % 10), 35, 675);
        if (colog[9] != 0)
            n.drawString("" + (colog[9] % 1000 / 100) + "/" + (colog[9] % 100 / 10) + "/" + (colog[9] % 10), 35, 750);


    }

    public void EndDraw(Graphics o) {
        o.setFont(HB);
        o.setColor(Color.BLACK);
        if (END) {
            o.drawImage(end, 0, 0, null);
            o.drawString("" + points, 425, 625);
        }
    }

    public void MOuse() {
        Qu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent z) {
                Qu.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent z) {
                Qu.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent z) {
                END = true;
            }
        });
        add(Qu);
    }

    public void Mouse() {
        Gy.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent f) {
                Gy.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent f) {
                Gy.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent f) {
                if (stac == 0) {
                    points = points + 3;
                    cocnt = -999;
                    System.out.println("합 성공");
                } else if (stac == cocnt && cocnt != 0) {
                    points = points + 3;
                    cocnt = -999;
                    System.out.println("합 성공");
                } else {
                    points--;
                    System.out.println("합 실패");
                }
            }
        });
        add(Gy);
    }

    public void mouse(int i) {

        btn[i].addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {

                btn[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {

                btn[i].setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }

            @Override
            public void mousePressed(MouseEvent e) {
                switch (i) {
                    case 0:
                        if (edge1 == basic) {
                            edge1 = select;
                            cnt++;
                            but[0] = true;
                        } else {
                            edge1 = basic;
                            cnt--;
                            but[0] = false;
                        }
                        insert(i);
                        break;
                    case 1:
                        if (edge2 == basic) {
                            edge2 = select;
                            cnt++;
                            but[1] = true;
                        } else {
                            edge2 = basic;
                            cnt--;
                            but[1] = false;
                        }
                        insert(i);
                        break;
                    case 2:
                        if (edge3 == basic) {
                            edge3 = select;
                            cnt++;
                            but[2] = true;
                        } else {
                            edge3 = basic;
                            cnt--;
                            but[2] = false;
                        }
                        insert(i);
                        break;
                    case 3:
                        if (edge4 == basic) {
                            edge4 = select;
                            cnt++;
                            but[3] = true;
                        } else {
                            edge4 = basic;
                            cnt--;
                            but[3] = false;
                        }
                        insert(i);
                        break;
                    case 4:
                        if (edge5 == basic) {
                            edge5 = select;
                            cnt++;
                            but[4] = true;
                        } else {
                            edge5 = basic;
                            cnt--;
                            but[4] = false;
                        }
                        insert(i);
                        break;
                    case 5:
                        if (edge6 == basic) {
                            edge6 = select;
                            cnt++;
                            but[5] = true;
                        } else {
                            edge6 = basic;
                            cnt--;
                            but[5] = false;
                        }
                        insert(i);
                        break;
                    case 6:
                        if (edge7 == basic) {
                            edge7 = select;
                            cnt++;
                            but[6] = true;
                        } else {
                            edge7 = basic;
                            cnt--;
                            but[6] = false;
                        }
                        insert(i);
                        break;
                    case 7:
                        if (edge8 == basic) {
                            edge8 = select;
                            cnt++;
                            but[7] = true;
                        } else {
                            edge8 = basic;
                            cnt--;
                            but[7] = false;
                        }
                        insert(i);
                        break;
                    case 8:
                        if (edge9 == basic) {
                            edge9 = select;
                            cnt++;
                            but[8] = true;
                        } else {
                            edge9 = basic;
                            cnt--;
                            but[8] = false;
                        }
                        insert(i);
                        break;
                }
            }
        });
        add(btn[i]);
    }

    public static void main(String[] args) {
        new gyulv2();
    }

}