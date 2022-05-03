package chartmycourse.chartmycourse;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddReply extends JFrame implements ActionListener {

    Post post;
    JButton add;
    User user;
    JTextArea replyField;

    public AddReply(Post post, User user) {
        super("Add Reply");
        this.post = post;
        this.user = user;
        createAndShowGUI();
    }

    public void createAndShowGUI() {

        replyField = new JTextArea();
        add = new JButton("Add");
        add.addActionListener(this);

        this.add(replyField);
        this.add(add);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Reply reply = new Reply();
        reply.setAuthor(user.realName);
        reply.setUpvotes(0);
        reply.setPostContents(replyField.getText());
        post.getReplies().add(reply);
    }

    @Override
    public void dispose() { super.dispose(); }
}
