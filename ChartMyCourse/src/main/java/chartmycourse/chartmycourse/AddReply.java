package chartmycourse.chartmycourse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddReply extends JFrame implements ActionListener {

    Post post;
    JButton add;
    User user;

    public AddReply(Post post, User user) {
        super("Add Reply");
        this.post = post;
        this.user = user;
        createAndShowGUI();
    }

    public void createAndShowGUI() {
        add = new JButton("Add");
        add.addActionListener(this);



    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
