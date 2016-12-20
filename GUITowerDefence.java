import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.*;

/**
 * Very, very basic GUI for very basic "Tower Defence" game
 */
public class GUITowerDefence extends JFrame implements ActionListener {

    private final Map<Position, JPanel> positionsPanels = new HashMap<>();
    private final Timer timer;
    private static final int SPEED = 500;
    private static final int PAUSE = 500;
    private JPanel lands = getLandscapePanel();
    private Game twrDfc;
    private MonsterPanel monsterPanel;
    public static final int row = 3;
    public static final int col = 8;
    private Position tiles;

    public static void main(String[] args) {
        new GUITowerDefence("Tower Defence").setVisible(true);
    }

    public GUITowerDefence(String title) {
        super(title);
        this.twrDfc = buildTowerDefence();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setResizable(false);

        // To choose path, type "pathOne" or "pathTwo" into init
        // then choose path in the twrDfc-constructor
        init("pathOne");

        this.tiles = new Position(0,1);

        this.add(lands, BorderLayout.CENTER);
        this.setSize(800, 300);
        timer = new Timer(SPEED, this);
        timer.setInitialDelay(PAUSE);

        timer.start();
        lands.setVisible(true);
    }

    // ---------- Event handling --------------------

    @Override
    public void actionPerformed(ActionEvent e) {

        if(!twrDfc.endGame() && !twrDfc.deadMonster()) {

            twrDfc.nextTurn();

            monsterPanel.setHealth(twrDfc.getMonsterHealth());
            positionsPanels.get(twrDfc.getMonsterPosition()).add(monsterPanel);

            repaint();
        }

        else if (twrDfc.deadMonster()){

            timer.stop();
            dispose();

            JOptionPane.showMessageDialog(null, "The monster died, you win!");
        }

        else {

            timer.stop();
            dispose();

            JOptionPane.showMessageDialog(null, "Game Over!");
        }
    }

    public void init(String path){
        lands.setLayout(new GridLayout(row,col));
        JPanel[] place = new JPanel[row*col];
        int p = 0;

        for(int i = row - 1; i >= 0; i = i - 1) {
            for (int n = 0; n < col; n = n + 1) {
                place[p]= new JPanel();
                lands.add(place[p]);
                positionsPanels.put(new Position(n,i), place[p]);
                p++;
            }
        }

        int[] greenPos = new int[row*col];

        for(int j = 0; j < row*col; j++) {
            greenPos[j] = j;
        }

        int[] whitePos = setPath(path);

        for(int i = 0; i < greenPos.length; i++){
            place[greenPos[i]].setBackground(Color.green);
            place[greenPos[i]].setBorder(BorderFactory.createLineBorder(Color.black));
        }

        for(int n = 0; n < whitePos.length; n++){
            place[whitePos[n]].setBackground(Color.white);
            place[whitePos[n]].setBorder(BorderFactory.createLineBorder(Color.black));
        }

        JLabel tower1 = getIconLabel("icons/tower-icon.png");
        JLabel tower2 = getIconLabel("icons/tower-icon.png");

        place[5].add(tower1);
        place[11].add(tower2);

        monsterPanel = new MonsterPanel();
        positionsPanels.get(twrDfc.getMonsterPosition()).add(monsterPanel);
        monsterPanel.setHealth(twrDfc.getMonsterHealth());

    }

    public int[] setPath(String path){

        if(path.equals("pathOne"))
            return new int[]{2,3,4,8,9,10,12,20,21,22,23};

        else if(path.equals("pathTwo")){
            return new int[]{8,9,10,12,13,14,15,18,19,20};
        }
        return null;
    }

    // ---------- Build model ----------

    private TowerDefenceGame buildTowerDefence() {
        return new TowerDefenceGame();
    }


    // ----------- Build GUI ---------------------

    private JPanel getLandscapePanel() {
        return new JPanel(); // Just for now ...
    }

    private JLabel getIconLabel(String fileName) {
        URL url = this.getClass().getResource(fileName);
        ImageIcon ii = new ImageIcon(url);
        JLabel lbl = new JLabel(ii);
        return lbl;
    }


    // -------------- Inner class ------------------

    private class MonsterPanel extends JPanel {

        private JLabel monster;
        private JLabel health = new JLabel();

        public MonsterPanel() {
            this.setOpaque(false);
            this.setLayout(new BorderLayout());
            this.monster = getIconLabel("icons/monster10.gif");
            health.setFont(new Font("Serif", Font.BOLD, 10));
            this.add(monster, BorderLayout.CENTER);
            this.add(health, BorderLayout.SOUTH);
        }

        public void setHealth(int health) {
            this.health.setText(String.valueOf(health));
        }

        public JLabel getMonster(){ return this.monster; }

        public JLabel getHealth() { return this.health; }


    }

}
