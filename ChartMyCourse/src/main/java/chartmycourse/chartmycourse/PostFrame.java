package chartmycourse.chartmycourse;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PostFrame extends JFrame implements ActionListener {

    private JTextArea postContents;
    private JButton upvoteButton;
    private JButton addReplyButton;
    private JButton removeUpvoteButton;
    private Post post;
    private JTable table;
    private User user;

    public PostFrame(Post post, JTable table, User user) {
        super("View Post");
        this.post = post;
        this.table = table;
        this.user = user;
    }

    public void createAndShowGUI() {
        postContents = new JTextArea();
        postContents.append(post.getPostContents());
        postContents.setBounds(10,30, 200,200);
        this.setSize(300,300);

        upvoteButton = new JButton("Upvote");
        addReplyButton = new JButton("Add Reply");
        removeUpvoteButton = new JButton("Remove Upvote");
/*
        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(true);

        contentPanel.add(postContents);
        contentPanel.add(upvoteButton); contentPanel.add(addReplyButton);
*/
        this.add(postContents);
        this.add(upvoteButton); this.add(addReplyButton); this.add(removeUpvoteButton);

        upvoteButton.addActionListener(this);
        addReplyButton.addActionListener(this);
        removeUpvoteButton.addActionListener(this);

        removeUpvoteButton.setEnabled(false);

        this.pack();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btnClicked = (JButton)e.getSource();
        if(btnClicked.equals(upvoteButton)) {

            post.setUpvotes(post.getUpvotes() + 1);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.fireTableDataChanged();
            removeUpvoteButton.setEnabled(true);
            upvoteButton.setEnabled(false);

        } else if (btnClicked.equals(addReplyButton)) {
            AddReply reply = new AddReply(post, user);
        } else if (btnClicked.equals(removeUpvoteButton)) {

            post.setUpvotes(post.getUpvotes() - 1);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.fireTableDataChanged();
            upvoteButton.setEnabled(true);
            removeUpvoteButton.setEnabled(false);
        }
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
