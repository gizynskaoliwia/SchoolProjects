package Game;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class HighScores extends JFrame {
    private JButton menu;

    UserDataComb userDataComb;

    private boolean visibleHS;

    public HighScores(boolean visibleHS) {
        if (visibleHS)
            init();
        this.visibleHS = visibleHS;

        loadData();
    }

    public void init() {
        this.setSize(150, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.LIGHT_GRAY);
        this.setLocation(1920 / 2 - getWidth() / 2, 1080 / 2 - getHeight() / 2);
        this.setLayout(null);
        this.setResizable(false);
        initComp();
        this.setVisible(true);
    }
    JLabel score1;
    JLabel score2;
    JLabel score3;
    JLabel score4;
    JLabel score5;
    public void initComp() {

        menu = new JButton("Menu");
        menu.setSize(100, 30);
        menu.setLocation(25, 420);
        menu.addActionListener(e -> {
            this.dispose();
            new MainMenu();
        });
        add(menu);

        score1 = new JLabel("");
        score2 = new JLabel("");
        score3 = new JLabel("");
        score4 = new JLabel("");
        score5 = new JLabel("");

        score1.setSize(50, 30);
        score1.setLocation(getWidth() / 2 - score1.getWidth() / 2, 20);
        score2.setSize(50, 30);
        score2.setLocation(getWidth() / 2 - score2.getWidth() / 2, 100);
        score3.setSize(50, 30);
        score3.setLocation(getWidth() / 2 - score3.getWidth() / 2, 180);
        score4.setSize(50, 30);
        score4.setLocation(getWidth() / 2 - score4.getWidth() / 2, 260);
        score5.setSize(50, 30);
        score5.setLocation(getWidth() / 2 - score5.getWidth() / 2, 340);
        add(score1);
        add(score2);
        add(score3);
        add(score4);
        add(score5);

    }

    public void loadData() {
        userDataComb = UserDataComb.loadData();

        if (userDataComb == null) {
            defaultData();
        }

        if (visibleHS) {
            score1.setText(userDataComb.scores.get(0).points);
            score2.setText(userDataComb.scores.get(1).points);
            score3.setText(userDataComb.scores.get(2).points);
            score4.setText(userDataComb.scores.get(3).points);
            score5.setText(userDataComb.scores.get(4).points);
        }
    }

    public void defaultData() {
        UserData u1 = new UserData();
        UserData u2 = new UserData();
        UserData u3 = new UserData();
        UserData u4 = new UserData();
        UserData u5 = new UserData();

        u1.points = "100";
        u2.points = "80";
        u3.points = "60";
        u4.points = "40";
        u5.points = "20";

        if (userDataComb == null)
        {
            userDataComb = new HighScores.UserDataComb();
            userDataComb.scores.add(u1);
            userDataComb.scores.add(u2);
            userDataComb.scores.add(u3);
            userDataComb.scores.add(u4);
            userDataComb.scores.add(u5);
        }
        UserDataComb.saveUserData(userDataComb);
    }

    public class UserDataComb implements Serializable {

        public ArrayList<UserData> scores = new ArrayList<>();

        public static UserDataComb loadData() {

            try {
                FileInputStream fileInputStream = new FileInputStream("userdata");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                UserDataComb ret = (UserDataComb) objectInputStream.readObject();
                ret.scores.sort((o1, o2) -> {
                    if (Integer.parseInt(o1.points) > Integer.parseInt(o2.points)) return -1;
                    return 1;
                });
                return ret;
            }
            catch (Exception e) {
            }

            return null;
        }

        public static void saveUserData(UserDataComb userDataComb) {
            try {
                FileOutputStream fos = new FileOutputStream("userdata");
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(userDataComb);
                oos.close();
                fos.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

        public static void addUserData(int score) {
            UserDataComb userdatacomb = UserDataComb.loadData();
            userdatacomb.scores.add(new UserData(Integer.toString(score)));
            UserDataComb.saveUserData(userdatacomb);
        }
    }
}

