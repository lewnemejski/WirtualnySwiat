import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.lang.System.out;

public class SwiatGUI implements ActionListener, KeyListener {
    private Toolkit toolkit;
    private Dimension dimension;
    private JFrame jFrame;
    private JMenu menu;
    private JMenuItem newGame, load, save, exit;
    private PlanszaGraphics planszaGraphics = null;
    private DziennikGraphics DziennikGraphics = null;
    private Oznaczenia oznaczenia = null;
    private JPanel mainPanel;
    private final int ODSTEP;
    private Swiat swiat;

    public SwiatGUI(String title) {
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        ODSTEP = dimension.height / 100;


        jFrame = new JFrame(title);
        jFrame.setBounds((dimension.width - 800) / 2, (dimension.height - 600) / 2, 800, 600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JMenuBar menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        newGame = new JMenuItem("New game");
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        newGame.addActionListener(this);
        load.addActionListener(this);
        save.addActionListener(this);
        exit.addActionListener(this);
        menu.add(newGame);
        menu.add(load);
        menu.add(save);
        menu.add(exit);
        menuBar.add(menu);
        jFrame.setJMenuBar(menuBar);
        jFrame.setLayout(new CardLayout());

        mainPanel = new JPanel();
        mainPanel.setBackground(Color.GRAY);
        mainPanel.setLayout(null);


        jFrame.addKeyListener(this);
        jFrame.add(mainPanel);
        jFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGame) {
            Dziennik.WyczyscDziennik();
            int sizeX = Integer.parseInt(JOptionPane.showInputDialog(jFrame,
                    "Szerokosc swiata", "20"));
            int sizeY = Integer.parseInt(JOptionPane.showInputDialog(jFrame,
                    "Wysokosc swiata", "20"));
            double zapelnienieSwiatu = Double.parseDouble(JOptionPane.showInputDialog
                    (jFrame, "Zapelnienie swiata(wartosc od 0 do 1)", "0.5"));
            swiat = new Swiat(sizeX, sizeY, this);
            swiat.stworzSwiat(zapelnienieSwiatu);
            if (planszaGraphics != null)
                mainPanel.remove(planszaGraphics);
            if (DziennikGraphics != null)
                mainPanel.remove(DziennikGraphics);
            if (oznaczenia != null)
                mainPanel.remove(oznaczenia);
            startGame();
        }
        if (e.getSource() == load) {
            Dziennik.WyczyscDziennik();
            String nameOfFile = JOptionPane.showInputDialog(jFrame, "Podaj nazwe pliku", "test");
            swiat = Swiat.wczytaj(nameOfFile);
            swiat.setSwiatGUI(this);
            planszaGraphics = new PlanszaGraphics(swiat);
           DziennikGraphics = new DziennikGraphics();
            oznaczenia = new Oznaczenia();
            if (planszaGraphics != null)
                mainPanel.remove(planszaGraphics);
            if (DziennikGraphics != null)
                mainPanel.remove(DziennikGraphics);
            if (oznaczenia != null)
                mainPanel.remove(oznaczenia);
            startGame();
        }
        if (e.getSource() == save) {
            String nameOfFile = JOptionPane.showInputDialog(jFrame, "Podaj nazwe pliku", "test");
            swiat.zapisz(nameOfFile);
            Dziennik.dodajWpis("Swiat zostal zapisany");
           DziennikGraphics.odswiezDziennik();
        }
        if (e.getSource() == exit) {
            jFrame.dispose();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (swiat != null && swiat.isPauza()) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_ENTER) {
                if (swiat.getCzyCzlowiekZyje()){
                    swiat.getCzlowiek().kierunek= Czlowiek.Kierunek.NIC;
                }
            } else if (swiat.getCzyCzlowiekZyje()) {
                if (keyCode == KeyEvent.VK_UP) {
                    swiat.getCzlowiek().kierunek= Czlowiek.Kierunek.GORA;
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    swiat.getCzlowiek().kierunek= Czlowiek.Kierunek.DOL;
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    swiat.getCzlowiek().kierunek= Czlowiek.Kierunek.LEWO;
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    swiat.getCzlowiek().kierunek= Czlowiek.Kierunek.PRAWO;
                } else if (keyCode == KeyEvent.VK_P) {
                    Czlowiek Czlowiektmp=swiat.getCzlowiek();
                    if (Czlowiektmp.getSuperMocCd()==0) {
                        Czlowiektmp.setSuper();
                    }
                } else {
                   Dziennik.dodajWpis("\nNieoznaczony symbol, sprobuj ponownie");
                   DziennikGraphics.odswiezDziennik();
                    return;
                }
            } else if (!swiat.getCzyCzlowiekZyje()) {
               DziennikGraphics.odswiezDziennik();
                return;
            } else {
               Dziennik.dodajWpis("\nZly znak, sprobuj ponownie");
               DziennikGraphics.odswiezDziennik();
                return;
            }
            Dziennik.WyczyscDziennik();
            swiat.setPauza(false);
            swiat.nowaTura();
            odswiezSwiat();
            swiat.setPauza(true);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    private class PlanszaGraphics extends JPanel {
        private final int sizeX;
        private final int sizeY;
        private PolePlanszy[][] polaPlanszy;
        private Swiat SWIAT;

        public PlanszaGraphics(Swiat swiat) {
            super();
            setBounds(mainPanel.getX() + ODSTEP, mainPanel.getY() + ODSTEP,
                    mainPanel.getHeight() * 5 / 6 - ODSTEP, mainPanel.getHeight() * 5 / 6 - ODSTEP);
            SWIAT = swiat;
            this.sizeX = swiat.getX();
            this.sizeY = swiat.getY();

            polaPlanszy = new PolePlanszy[sizeY][sizeX];
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    polaPlanszy[i][j] = new PolePlanszy(j, i);
                    polaPlanszy[i][j].addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if (e.getSource() instanceof PolePlanszy) {
                                PolePlanszy tmpPole = (PolePlanszy) e.getSource();
                                if (tmpPole.isEmpty == true) {
                                    //System.out.println(tmpPole.getX()+"x y"+tmpPole.getY());
                                    ListaOrganizmow listaOrganizmow = new ListaOrganizmow
                                            (tmpPole.getX() + jFrame.getX(),tmpPole.getY() + jFrame.getY(),
                                                    tmpPole.getPozX(), tmpPole.getPozY());
                                }
                            }
                        }
                    });
                }
            }

            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    this.add(polaPlanszy[i][j]);
                }
            }
            this.setLayout(new GridLayout(sizeY, sizeX));
        }

        private class PolePlanszy extends JButton {
            private boolean isEmpty;
            private Color kolor;
            private final int pozX;
            private final int pozY;

            public PolePlanszy(int X, int Y) {
                super();
                kolor = Color.WHITE;
                setBackground(kolor);
                isEmpty = true;
                pozX = X;
                pozY = Y;
            }

            public boolean isEmpty() {
                return isEmpty;
            }

            public void setEmpty(boolean empty) {
                isEmpty = empty;
            }


            public Color getKolor() {
                return kolor;
            }

            public void setKolor(Color kolor) {
                this.kolor = kolor;
                setBackground(kolor);
            }

            public int getPozX() {
                return pozX;
            }

            public int getPozY() {
                return pozY;
            }
        }

        public void odswiezPlansze() {
            for (int i = 0; i < sizeY; i++) {
                for (int j = 0; j < sizeX; j++) {
                    Organizm tmpOrganizm = swiat.getPlansza()[i][j];
                    if (tmpOrganizm != null) {
                        polaPlanszy[i][j].setEmpty(false);
                        polaPlanszy[i][j].setEnabled(false);
                        polaPlanszy[i][j].setKolor(tmpOrganizm.getKolor());
                    } else {
                        polaPlanszy[i][j].setEmpty(true);
                        polaPlanszy[i][j].setEnabled(true);
                        polaPlanszy[i][j].setKolor(Color.WHITE);
                    }
                }
            }
        }
    }

    private class DziennikGraphics extends JPanel {
        private String tekst;
        private final String instriction = "Autor: Kacper Wszeborowski 189477\nStrzalki - kierowanie Czlowiekiem\n" +
                "P - aktywacja umiejetnosci\nEnter - przejscie do nastepnej tury\n";
        private JTextArea textArea;

        public DziennikGraphics() {
            super();
            setBounds(planszaGraphics.getX() + planszaGraphics.getWidth() + ODSTEP,
                    mainPanel.getY() + ODSTEP,
                    mainPanel.getWidth() - planszaGraphics.getWidth() - ODSTEP * 3,
                    mainPanel.getHeight() * 5 / 6 - ODSTEP);
            tekst = Dziennik.getTekst();
            textArea = new JTextArea(tekst);
            textArea.setEditable(false);
            setLayout(new CardLayout());

            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setMargin(new Insets(5, 5, 5, 5));
            JScrollPane sp = new JScrollPane(textArea);
            add(sp);
        }

        public void odswiezDziennik() {
            tekst = instriction +Dziennik.getTekst();
            textArea.setText(tekst);
        }
    }

    private class ListaOrganizmow extends JFrame {
        private String[] listaOrganizmow;
        private Organizm.TypOrganizmu[] typOrganizmuList;
        private JList jList;

        public ListaOrganizmow(int Jx, int Jy, int x, int y) {
            super("Lista organizmow");
            setBounds(Jx, Jy, 100, 300);
            listaOrganizmow = new String[]{"Barszcz Sosnowskiego", "Guarana", "Mlecz", "Trawa",
                    "Wilcze jagody", "Antylopa", "Lis", "Owca", "Wilk", "Zolw"};
            typOrganizmuList = new Organizm.TypOrganizmu[]{Organizm.TypOrganizmu.BARSZCZ_SOSNOWSKIEGO,
                    Organizm.TypOrganizmu.GUARANA, Organizm.TypOrganizmu.MLECZ, Organizm.TypOrganizmu.TRAWA,
                    Organizm.TypOrganizmu.WILCZE_JAGODY, Organizm.TypOrganizmu.ANTYLOPA,
                    Organizm.TypOrganizmu.LIS,
                    Organizm.TypOrganizmu.OWCA, Organizm.TypOrganizmu.WILK,
                    Organizm.TypOrganizmu.ZOLW
            };

            jList = new JList(listaOrganizmow);
            jList.setVisibleRowCount(listaOrganizmow.length);
            jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            jList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    Organizm tmpOrganizm = DodajOrganizm.nowyOrganizm
                            (swiat, x, y, typOrganizmuList[jList.getSelectedIndex()]);
                    swiat.DodajOrganizm(tmpOrganizm);
                   Dziennik.dodajWpis("Stworzono nowy organizm x: "+x+" y: "+y+" " + tmpOrganizm.getZnak());
                    odswiezSwiat();
                    dispose();

                }
            });

            JScrollPane sp = new JScrollPane(jList);
            add(sp);

            setVisible(true);
        }
    }

    private class Oznaczenia extends JPanel {
        private final int ILOSC_TYPOW = 11;
        private JButton[] jButtons;

        public Oznaczenia() {
            super();
            setBounds(mainPanel.getX() + ODSTEP, mainPanel.getHeight() * 5 / 6 + ODSTEP,
                    mainPanel.getWidth() - ODSTEP * 2,
                    mainPanel.getHeight() * 1 / 6 - 2 * ODSTEP);
            setBackground(Color.WHITE);
            setLayout(new FlowLayout(FlowLayout.CENTER));
            jButtons = new JButton[ILOSC_TYPOW];
            jButtons[0] = new JButton("Barszcz Sosnowskiego");
            jButtons[0].setBackground(new Color(128, 172, 0));

            jButtons[1] = new JButton("Guarana");
            jButtons[1].setBackground(Color.RED);

            jButtons[2] = new JButton("Mlecz");
            jButtons[2].setBackground(Color.YELLOW);

            jButtons[3] = new JButton("Trawa");
            jButtons[3].setBackground(Color.GREEN);

            jButtons[4] = new JButton("Wilcze jagody");
            jButtons[4].setBackground(new Color(25, 0, 51));

            jButtons[5] = new JButton("Antylopa");
            jButtons[5].setBackground(new Color(153, 76, 0));

            jButtons[6] = new JButton("Czlowiek");
            jButtons[6].setBackground(new Color(158, 129, 52));

            jButtons[7] = new JButton("Lis");
            jButtons[7].setBackground(new Color(255, 128, 0));

            jButtons[8] = new JButton("Owca");
            jButtons[8].setBackground(new Color(255, 153, 204));

            jButtons[9] = new JButton("Wilk");
            jButtons[9].setBackground(new Color(120, 114, 118));

            jButtons[10] = new JButton("Zolw");
            jButtons[10].setBackground(new Color(0, 102, 0));

            for (int i = 0; i < ILOSC_TYPOW; i++) {
                jButtons[i].setEnabled(false);
                add(jButtons[i]);
            }

        }
    }

    private void startGame() {
        planszaGraphics = new PlanszaGraphics(swiat);
        mainPanel.add(planszaGraphics);

        DziennikGraphics = new DziennikGraphics();
        mainPanel.add(DziennikGraphics);

        oznaczenia = new Oznaczenia();
        mainPanel.add(oznaczenia);

        odswiezSwiat();
    }
    public void odswiezSwiat() {
        planszaGraphics.odswiezPlansze();
       DziennikGraphics.odswiezDziennik();
        SwingUtilities.updateComponentTreeUI(jFrame);
        jFrame.requestFocusInWindow();
    }

    public Swiat getSwiat() {
        return swiat;
    }
}
