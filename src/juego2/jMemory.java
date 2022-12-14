package juego2;

import interfaces.iCentroJuego;
import interfaces.iJugador;
import interfaces.iJuego;

import java.text.SimpleDateFormat;

import java.time.LocalTime;

import java.util.Date;
import java.util.Random;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

/**
 * Class jMemory
 * This class implements the game of memory and its methods
 * Using the singleton pattern
 *
 * @author Karina
 */
public class jMemory extends JFrame {
    private JButton btnC1;
    private JButton btnC10;
    private JButton btnC11;
    private JButton btnC12;
    private JButton btnC13;
    private JButton btnC14;
    private JButton btnC15;
    private JButton btnC16;
    private JButton btnC2;
    private JButton btnC3;
    private JButton btnC4;
    private JButton btnC5;
    private JButton btnC6;
    private JButton btnC7;
    private JButton btnC8;
    private JButton btnC9;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JLabel lbDate;
    private JLabel lbPlayer1;
    private JLabel lbScoreP1;
    private JButton btnExit;
    private boolean cardUp = false;
    private ImageIcon card1;
    private ImageIcon card2;
    private JButton[] btnPressed = new JButton[2];
    private boolean secondCard = false;
    private int score = 0;  //"+20 pts if there are pairs or -10 pts if it fails"
    private iJugador player;
    private static iJuego gameParentMenu;
    private static jMemory instance = null;
    private boolean isFinished = false;

    /**
     * Constructor
     *
     * @param player
     */
    private jMemory(iJugador jugador) {
        initComponents();
        setCards();
        lbPlayer1.setText(jugador.getNombre());
        lbDate.setText(getDate());
        this.player = jugador;
    }

    /**
     * Method getInstance
     * This method returns the instance of the class
     *
     * @param {@link iJugador} player
     * @param {@link iJuego} The type of game
     * @return instance
     */
    public static jMemory getInstance(iJugador jugador, iJuego juego) {
        if (instance == null) {
            instance = new jMemory(jugador);
        }
        gameParentMenu = juego;
        return instance;
    }
    
    /**
     * Method getDate
     * This method returns the date
     *
     * @return date
     */
    private String getDate() {
        Date dateCurrent = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");

        return dateFormat.format(dateCurrent);
    }

    /**
     * This method sets the disabled icon of each button to 
     * the image of the card that corresponds to the number in
     * the array
     */
    private void setCards() { 
        int[] numbers = getCardNumbers();
        btnC1.setDisabledIcon(new ImageIcon("./img/"+numbers[0]+".jpg"));
        btnC2.setDisabledIcon(new ImageIcon("./img/"+numbers[1]+".jpg"));
        btnC3.setDisabledIcon(new ImageIcon("./img/"+numbers[2]+".jpg"));
        btnC4.setDisabledIcon(new ImageIcon("./img/"+numbers[3]+".jpg"));
        btnC5.setDisabledIcon(new ImageIcon("./img/"+numbers[4]+".jpg"));
        btnC6.setDisabledIcon(new ImageIcon("./img/"+numbers[5]+".jpg"));
        btnC7.setDisabledIcon(new ImageIcon("./img/"+numbers[6]+".jpg"));
        btnC8.setDisabledIcon(new ImageIcon("./img/"+numbers[7]+".jpg"));
        btnC9.setDisabledIcon(new ImageIcon("./img/"+numbers[8]+".jpg"));
        btnC10.setDisabledIcon(new ImageIcon("./img/"+numbers[9]+".jpg"));
        btnC11.setDisabledIcon(new ImageIcon("./img/"+numbers[10]+".jpg"));
        btnC12.setDisabledIcon(new ImageIcon("./img/"+numbers[11]+".jpg"));
        btnC13.setDisabledIcon(new ImageIcon("./img/"+numbers[12]+".jpg"));
        btnC14.setDisabledIcon(new ImageIcon("./img/"+numbers[13]+".jpg"));
        btnC15.setDisabledIcon(new ImageIcon("./img/"+numbers[14]+".jpg"));
        btnC16.setDisabledIcon(new ImageIcon("./img/"+numbers[15]+".jpg"));
    }

    /**
     * btnEnabled method
     * If the first card is not up, then set the button 
     * to disabled, set the first card to the button's
     * disabled icon, set the first button pressed to 
     * the button, set the card up to true, and set the
     * second card to false. If the first card is up, 
     * then set the button to disabled, set the second card
     * to the button's disabled icon, set the second 
     * button pressed to the button, set the second card to
     * true, and add 20 to the score
     * 
     * @param btn The button that was pressed
     * @return {@link void}
     */
    private void btnEnabled(JButton btn) {
        if(!cardUp) {
            btn.setEnabled(false);
            card1 = (ImageIcon) btn.getDisabledIcon();
            btnPressed[0] = btn;
            cardUp = true;
            secondCard = false;      
        }
        else {
            btn.setEnabled(false);
            card2 = (ImageIcon) btn.getDisabledIcon();
            btnPressed[1] = btn;
            secondCard = true;
            score+=20;
            calcWin(); 
        }
    }

    /**
     * getCardNumbers method 
     * This method is used to get the card numbers 
     *
     * @return cardNumbers
     */
    public int[] getCardNumbers() {
        int[] numbers = new int[16];
        int count = 0;

        while (count < 16) {
            Random r = new Random();
            int na = r.nextInt(8) + 1;
            int nvr = 0;
            
            for(int i = 0; i < 16; i++ ) {
                if(numbers[i] == na) {
                    nvr++;
                }
            }
            if(nvr < 2) {
                numbers[count] = na;
                count++;
            }
        }
        return numbers;
    }
    
    /**
     * Method compare
     * If the two cards are not the same, then the buttons are enabled and the score is reduced by 10
     *
     * @return {@link void}
     */
    private void compare() {
        if(cardUp && secondCard) {
            if(card1.getDescription().compareTo(card2.getDescription()) != 0) {
                btnPressed[0].setEnabled(true);
                btnPressed[1].setEnabled(true); 
                if(score > 10) score -=10;
            }
            cardUp = false;
        }
        lbScoreP1.setText(""+score);
    }
    
    /**
     * This method resets the game by enabling all the buttons, 
     * setting the secondCard boolean to false, setting the 
     * cardUp boolean to false, and setting the score to 0.
     *
     * @return {@link void}
     */
    public void reset() {
        setCards();
        btnC1.setEnabled(true);
        btnC2.setEnabled(true);
        btnC3.setEnabled(true);
        btnC4.setEnabled(true);
        btnC5.setEnabled(true);
        btnC6.setEnabled(true);
        btnC7.setEnabled(true);
        btnC8.setEnabled(true);
        btnC9.setEnabled(true);
        btnC10.setEnabled(true);
        btnC11.setEnabled(true);
        btnC12.setEnabled(true);
        btnC13.setEnabled(true);
        btnC14.setEnabled(true);
        btnC15.setEnabled(true);
        btnC16.setEnabled(true);
        isFinished = false;
        secondCard = false;
        cardUp = false;
        score = 0;
    }

    /**
     * This method returns the player score 
     *
     * @return {@link #score} The player score
     */
    public int getScore() {
        return score;
    }

    /**
     * This method returns the isFinished boolean
     *
     * @return {@link #isFinished} The isFinished boolean
     */
    public boolean getIsFinished() {
        return isFinished;
    }
    
    /**
     * This method checks if all the buttons are disabled, 
     * if they are, it shows a message dialog with
     * the player's name and score
     */
    public void calcWin() {
        if(!btnC1.isEnabled() && !btnC2.isEnabled() && !btnC3.isEnabled() && !btnC4.isEnabled() && 
           !btnC5.isEnabled() && !btnC6.isEnabled() && !btnC7.isEnabled() && !btnC8.isEnabled() && 
           !btnC9.isEnabled() && !btnC10.isEnabled() && !btnC11.isEnabled() && !btnC12.isEnabled() && 
           !btnC13.isEnabled() && !btnC14.isEnabled() && !btnC15.isEnabled() && !btnC16.isEnabled()) {
            JOptionPane.showMessageDialog(this,"??Congratulations " + player.getNombre() + "!"+"\n Your score is :"+ score, "WIN", JOptionPane.INFORMATION_MESSAGE);  
            isFinished = true;
            gameParentMenu.terminarPartida();
        }  
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    private void initComponents() {
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btnC1 = new javax.swing.JButton();
        btnC2 = new javax.swing.JButton();
        btnC3 = new javax.swing.JButton();
        btnC4 = new javax.swing.JButton();
        btnC5 = new javax.swing.JButton();
        btnC6 = new javax.swing.JButton();
        btnC7 = new javax.swing.JButton();
        btnC8 = new javax.swing.JButton();
        btnC9 = new javax.swing.JButton();
        btnC12 = new javax.swing.JButton();
        btnC10 = new javax.swing.JButton();
        btnC11 = new javax.swing.JButton();
        btnC14 = new javax.swing.JButton();
        btnC15 = new javax.swing.JButton();
        btnC13 = new javax.swing.JButton();
        btnC16 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbScoreP1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lbPlayer1 = new javax.swing.JLabel();
        lbDate = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();


        jLabel7.setText("jLabel7");

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gameParentMenu.terminarPartida();
            }
        });

        setTitle("Memory Game");
        setAutoRequestFocus(false);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Malgun Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Memory Game");

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        btnC1.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC1.setBorder(null);
        btnC1.setBorderPainted(false);
        btnC1.setContentAreaFilled(false);
        btnC1.setFocusable(false);
        btnC1.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC1MouseExited(evt);
            }
        });

        btnC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC1ActionPerformed(evt);
            }
        });

        btnC2.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC2.setBorder(null);
        btnC2.setBorderPainted(false);
        btnC2.setContentAreaFilled(false);
        btnC2.setFocusable(false);
        btnC2.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC2MouseExited(evt);
            }
        });
        btnC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC2ActionPerformed(evt);
            }
        });

        btnC3.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC3.setBorder(null);
        btnC3.setBorderPainted(false);
        btnC3.setContentAreaFilled(false);
        btnC3.setFocusable(false);
        btnC3.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC3MouseExited(evt);
            }
        });
        btnC3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC3ActionPerformed(evt);
            }
        });

        btnC4.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC4.setBorder(null);
        btnC4.setBorderPainted(false);
        btnC4.setContentAreaFilled(false);
        btnC4.setFocusable(false);
        btnC4.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC4MouseExited(evt);
            }
        });
        btnC4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC4ActionPerformed(evt);
            }
        });

        btnC5.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC5.setBorder(null);
        btnC5.setBorderPainted(false);
        btnC5.setContentAreaFilled(false);
        btnC5.setFocusable(false);
        btnC5.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC5MouseExited(evt);
            }
        });
        btnC5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC5ActionPerformed(evt);
            }
        });

        btnC6.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC6.setBorder(null);
        btnC6.setBorderPainted(false);
        btnC6.setContentAreaFilled(false);
        btnC6.setFocusable(false);
        btnC6.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC6MouseExited(evt);
            }
        });
        btnC6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC6ActionPerformed(evt);
            }
        });

        btnC7.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC7.setBorder(null);
        btnC7.setBorderPainted(false);
        btnC7.setContentAreaFilled(false);
        btnC7.setFocusable(false);
        btnC7.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC7MouseExited(evt);
            }
        });
        btnC7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC7ActionPerformed(evt);
            }
        });

        btnC8.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC8.setBorder(null);
        btnC8.setBorderPainted(false);
        btnC8.setContentAreaFilled(false);
        btnC8.setFocusable(false);
        btnC8.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC8MouseExited(evt);
            }
        });
        btnC8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC8ActionPerformed(evt);
            }
        });

        btnC9.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC9.setBorder(null);
        btnC9.setBorderPainted(false);
        btnC9.setContentAreaFilled(false);
        btnC9.setFocusable(false);
        btnC9.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC9MouseExited(evt);
            }
        });
        btnC9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC9ActionPerformed(evt);
            }
        });

        btnC12.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC12.setBorder(null);
        btnC12.setBorderPainted(false);
        btnC12.setContentAreaFilled(false);
        btnC12.setFocusable(false);
        btnC12.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC12MouseExited(evt);
            }
        });
        btnC12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC12ActionPerformed(evt);
            }
        });

        btnC10.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC10.setBorder(null);
        btnC10.setBorderPainted(false);
        btnC10.setContentAreaFilled(false);
        btnC10.setFocusable(false);
        btnC10.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC10MouseExited(evt);
            }
        });
        btnC10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC10ActionPerformed(evt);
            }
        });

        btnC11.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC11.setBorder(null);
        btnC11.setBorderPainted(false);
        btnC11.setContentAreaFilled(false);
        btnC11.setFocusable(false);
        btnC11.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC11MouseExited(evt);
            }
        });
        btnC11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC11ActionPerformed(evt);
            }
        });

        btnC14.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC14.setBorder(null);
        btnC14.setBorderPainted(false);
        btnC14.setContentAreaFilled(false);
        btnC14.setFocusable(false);
        btnC14.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC14MouseExited(evt);
            }
        });
        btnC14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC14ActionPerformed(evt);
            }
        });

        btnC15.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC15.setBorder(null);
        btnC15.setBorderPainted(false);
        btnC15.setContentAreaFilled(false);
        btnC15.setFocusable(false);
        btnC15.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC15MouseExited(evt);
            }
        });
        btnC15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC15ActionPerformed(evt);
            }
        });

        btnC13.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC13.setBorder(null);
        btnC13.setBorderPainted(false);
        btnC13.setContentAreaFilled(false);
        btnC13.setFocusable(false);
        btnC13.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC13MouseExited(evt);
            }
        });
        btnC13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC13ActionPerformed(evt);
            }
        });

        btnC16.setIcon(new javax.swing.ImageIcon("./img/c0.png")); // NOI18N
        btnC16.setBorder(null);
        btnC16.setBorderPainted(false);
        btnC16.setContentAreaFilled(false);
        btnC16.setFocusable(false);
        btnC16.setRolloverIcon(new javax.swing.ImageIcon("./img/cr.png")); // NOI18N
        btnC16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnC16MouseExited(evt);
            }
        });
        btnC16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnC16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnC5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(btnC9, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnC13, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnC1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnC14, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnC15, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnC16, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnC10, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnC11, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnC12, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(btnC2, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnC3, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnC4, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(btnC6, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(btnC7, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(5, 5, 5)
                            .addComponent(btnC8, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(5, 5, 5))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnC1, btnC10, btnC11, btnC12, btnC13, btnC14, btnC15, btnC16, btnC2, btnC3, btnC4, btnC5, btnC6, btnC7, btnC8, btnC9});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnC2)
                    .addComponent(btnC1)
                    .addComponent(btnC3)
                    .addComponent(btnC4))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnC6)
                        .addComponent(btnC7)
                        .addComponent(btnC8))
                    .addComponent(btnC5))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnC9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnC10)
                        .addComponent(btnC11)
                        .addComponent(btnC12)))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnC13)
                    .addComponent(btnC14)
                    .addComponent(btnC15)
                    .addComponent(btnC16))
                .addGap(5, 5, 5))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnC10, btnC11, btnC12, btnC13, btnC14, btnC15, btnC16, btnC2, btnC3, btnC4, btnC5, btnC6, btnC7, btnC8, btnC9});

        jPanel2.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        jLabel2.setFont(new java.awt.Font("Malgun Gothic", 1, 12)); // NOI18N
        jLabel2.setText("Player:");

        lbScoreP1.setFont(new java.awt.Font("Dubai", 1, 14)); // NOI18N
        lbScoreP1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbScoreP1.setToolTipText("");
        lbScoreP1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(41, 43, 45)), "Score:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Malgun Gothic", 1, 12))); // NOI18N
        lbScoreP1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton1.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Malgun Gothic", 1, 14)); // NOI18N
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        jLabel3.setText("Fecha:");
        
        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbScoreP1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(lbDate, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPlayer1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbScoreP1, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        setBounds(0, 0, 464, 457);
    }

    /**
     * Below are the methods that are called when the user clicks on the buttons
     * @param evt 
     */

    private void btnC1ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC1);
    }

    private void btnC2ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC2);
    }

    private void btnC3ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC3);
    }

    private void btnC4ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC4);
    }

    private void btnC5ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC5);
    }

    private void btnC6ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC6);
    }

    private void btnC7ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC7);
    }

    private void btnC8ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC8);
    }

    private void btnC9ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC9);
    }

    private void btnC10ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC10);
    }

    private void btnC11ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC11);
    }

    private void btnC12ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC12);
    }

    private void btnC13ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC13);
    }

    private void btnC14ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC14);
    }

    private void btnC15ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC15);
    }

    private void btnC16ActionPerformed(java.awt.event.ActionEvent evt) {
        btnEnabled(btnC16);
    }

    private void btnC1MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC2MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC3MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC4MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC5MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC6MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC7MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC8MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC9MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC10MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC11MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC12MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC13MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC14MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC15MouseExited(java.awt.event.MouseEvent evt) {
        compare();
    }

    private void btnC16MouseExited(java.awt.event.MouseEvent evt) {
       compare();
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        reset();
    }
    
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {
        gameParentMenu.terminarPartida();
    }
}
