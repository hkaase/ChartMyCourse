package chartmycourse.chartmycourse;

import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.*;
import java.util.List;

import net.coderazzi.filters.gui.AutoChoices;
import net.coderazzi.filters.gui.TableFilterHeader;

//Main UI class.

/**
 * This is the main class for ChartMyCourse
 * 
 * @author Harm Drenth
 * @version 1.0
 * @since 1.0
 */
public class ChartMyCourseMainPage extends JFrame {

	//These are the required buttons, labels, and other swing elements.
	private JLabel chartmycoursewatermark;
    private JLabel curUserHeading;
    private JLabel curUserLabel;
    private JTextField emailField;
    private JLabel emailLabel;
    private JLabel emailLabelSignup;
    private JButton forgotPasswordButton;
    private JButton homeButton;
    private JPanel homePanel;
    private JButton loginButton;
    private JDialog loginDialog;
    private JLabel loginLabel;
    private JButton loginRequestButton;
    private JButton loginReturnButton;
    private JLabel nameLabel;
    private JLabel securityQuestionLabel;
    private JTextField nameTextField;
    private JTextField securityQuestionField;
    private JPasswordField passwordField;
    private JPasswordField passwordField1;
    private JLabel passwordLabel;
    private JLabel passwordLabelSignup;
    private JButton planningButton;
    private JLabel planningHeading;
    private JPanel planningPanel;
    private JButton postDiscussionButton;
    private JButton qAndAButton;
    private JPanel qAndAPanel;
    private JTable qAndATable;
    private JScrollPane qAndATableScrollPane;
    private JButton recommendedCoursesButton;
    private JDialog recommendedCourseDialog;
    private JButton recommendedProfessorsButton;
    private JDialog recommendedProfessorDialog;
    private JLabel courseListLabel;
    private JLabel profListLabel;
    private JTable courseListTable;
    private JTable profListTable;
    private JScrollPane courseListScrollPane;
    private JScrollPane profListScrollPane;
    private JButton removeCourseButton;
    private JButton removeProfButton;
    private JButton registerButton;
    private JButton reviewsButton;
    private JLabel reviewsHeader;
    private JButton addReview;
    private JTextField addReviewCRN;
    private JTextField addReviewAuthor, addReviewCourse, addReviewProfessor, addReviewRating, addReviewText;
    private JPanel reviewsPanel;
    private JTable reviewsTable;
    private JScrollPane reviewsTableScrollPane;
    //private JLabel searchLabel;
    //private JTextField searchText;
    private JButton saveCourseButton;
    private JButton saveProfButton;
    private JButton signupButton;
    private JDialog signupDialog;
    private JDialog forgotPasswordDialog;
    private JButton signupFormButton;
    private JTextField usernameField;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JTextArea welcomeSplashTextArea;
    private JScrollPane welcomeSplashTextPane;
    private String curUserString = "not logged in";
    private JLabel qAndAHeader;
    private JDialog reviewDialog;

    private JTable replyTable;
    private Post curPost;
    private JDialog replyDialog;
    private JButton removeDiscussionButton;
    public  JButton addReplyButton;

    //This array holds the list of reviews.
    private final ArrayList<Review> reviewArray = new ArrayList<>();
    //This array holds the list of users.
    private final ArrayList<User> userArray = new ArrayList<>();
    //This array holds the list of posts.
    private final ArrayList<Post> postsArray = new ArrayList<>();
    private Boolean loggedIn = false;
    private DefaultTableModel reviewtablemodel;
    private JButton removeReviewButton;
    private User curUser;
    private JDialog postDialog;
    private JTextField addReplyText;
    private JTextField addReply;
    private JTextField addDiscussionText;
    private JTextField addDiscussion;
    
    public class ButtonColumn extends AbstractCellEditor
	implements TableCellRenderer, TableCellEditor, ActionListener, MouseListener
{
	private JTable table;
	private Action action;
	private int mnemonic;
	private Border originalBorder;
	private Border focusBorder;

	private JButton renderButton;
	private JButton editButton;
	private Object editorValue;
	private boolean isButtonColumnEditor;

	/**
	 *  Create the ButtonColumn to be used as a renderer and editor. The
	 *  renderer and editor will automatically be installed on the TableColumn
	 *  of the specified column.
	 *
	 *  @param table the table containing the button renderer/editor
	 *  @param action the Action to be invoked when the button is invoked
	 *  @param column the column to which the button renderer/editor is added
	 */
	public ButtonColumn(JTable table, Action action, int column)
	{
		this.table = table;
		this.action = action;

		renderButton = new JButton("Action");
		editButton = new JButton("Action");
		editButton.setFocusPainted( false );
		editButton.addActionListener( this );
		originalBorder = editButton.getBorder();
		setFocusBorder( new LineBorder(Color.BLUE) );

		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(column).setCellRenderer( this );
		columnModel.getColumn(column).setCellEditor( this );
		table.addMouseListener( this );
	}


	/**
	 *  Get foreground color of the button when the cell has focus
	 *
	 *  @return the foreground color
	 */
	public Border getFocusBorder()
	{
		return focusBorder;
	}

	/**
	 *  The foreground color of the button when the cell has focus
	 *
	 *  @param focusBorder the foreground color
	 */
	public void setFocusBorder(Border focusBorder)
	{
		this.focusBorder = focusBorder;
		editButton.setBorder( focusBorder );
	}

	public int getMnemonic()
	{
		return mnemonic;
	}

	/**
	 *  The mnemonic to activate the button when the cell has focus
	 *
	 *  @param mnemonic the mnemonic
	 */
	public void setMnemonic(int mnemonic)
	{
		this.mnemonic = mnemonic;
		renderButton.setMnemonic(mnemonic);
		editButton.setMnemonic(mnemonic);
	}

	@Override
	public Component getTableCellEditorComponent(
		JTable table, Object value, boolean isSelected, int row, int column)
	{
		if (value == null)
		{
			editButton.setText( "" );
			editButton.setIcon( null );
		}
		else if (value instanceof Icon)
		{
			editButton.setText( "" );
			editButton.setIcon( (Icon)value );
		}
		else
		{
			editButton.setText( value.toString() );
			editButton.setIcon( null );
		}

		this.editorValue = value;
		return editButton;
	}

	@Override
	public Object getCellEditorValue()
	{
		return editorValue;
	}

//
//  Implement TableCellRenderer interface
//
	public Component getTableCellRendererComponent(
		JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	{
		if (isSelected)
		{
			renderButton.setForeground(table.getSelectionForeground());
	 		renderButton.setBackground(table.getSelectionBackground());
		}
		else
		{
			renderButton.setForeground(table.getForeground());
			renderButton.setBackground(UIManager.getColor("Button.background"));
		}

		if (hasFocus)
		{
			renderButton.setBorder( focusBorder );
		}
		else
		{
			renderButton.setBorder( originalBorder );
		}

//		renderButton.setText( (value == null) ? "" : value.toString() );
		if (value == null)
		{
			renderButton.setText( "" );
			renderButton.setIcon( null );
		}
		else if (value instanceof Icon)
		{
			renderButton.setText( "" );
			renderButton.setIcon( (Icon)value );
		}
		else
		{
			renderButton.setText( value.toString() );
			renderButton.setIcon( null );
		}

		return renderButton;
	}

//
//  Implement ActionListener interface
//
	/*
	 *	The button has been pressed. Stop editing and invoke the custom Action
	 */
	public void actionPerformed(ActionEvent e)
	{
		int row = table.convertRowIndexToModel( table.getEditingRow() );
		fireEditingStopped();

		//  Invoke the Action

		ActionEvent event = new ActionEvent(
			table,
			ActionEvent.ACTION_PERFORMED,
			"" + row);
		action.actionPerformed(event);
	}

//
//  Implement MouseListener interface
//
	/*
	 *  When the mouse is pressed the editor is invoked. If you then then drag
	 *  the mouse to another cell before releasing it, the editor is still
	 *  active. Make sure editing is stopped when the mouse is released.
	 */
    public void mousePressed(MouseEvent e)
    {
    	if (table.isEditing()
		&&  table.getCellEditor() == this)
			isButtonColumnEditor = true;
    }

    public void mouseReleased(MouseEvent e)
    {
    	if (isButtonColumnEditor
    	&&  table.isEditing())
    		table.getCellEditor().stopCellEditing();

		isButtonColumnEditor = false;
    }

    public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
	
    
    
    /**
     * Constructor function, makes declaration of instance display. 
     * @author Harm Drenth
     * @version 1.0
     * @since 1.0
     */
    public ChartMyCourseMainPage() {
    	
        createInteractables();
        
        initialize();
    }

    /**
     * This function initializes all of the interactable objects.
     * @author Harm Drenth
     * @version 1.0
     * @since 1.0
     */
	
    private void createInteractables() {

        loginDialog = new JDialog();
        loginLabel = new JLabel();
        passwordField = new JPasswordField();
        usernameField = new JTextField();
        loginButton = new JButton();
        emailLabel = new JLabel();
        passwordLabel = new JLabel();
        forgotPasswordButton = new JButton();
        signupFormButton = new JButton();
        signupDialog = new JDialog();
        forgotPasswordDialog = new JDialog();
        emailLabelSignup = new JLabel();
        passwordLabelSignup = new JLabel();
        passwordField1 = new JPasswordField();
        emailField = new JTextField();
        usernameLabel = new JLabel();
        nameLabel = new JLabel();
        usernameTextField = new JTextField();
        securityQuestionLabel = new JLabel();
        securityQuestionField = new JTextField();
        nameTextField = new JTextField();
        loginReturnButton = new JButton();
        registerButton = new JButton();
        homePanel = new JPanel();
        welcomeSplashTextPane = new JScrollPane();
        welcomeSplashTextArea = new JTextArea();
        curUserHeading = new JLabel();
        curUserLabel = new JLabel();
        chartmycoursewatermark = new JLabel();
        homeButton = new JButton();
        reviewsButton = new JButton();
        qAndAButton = new JButton();
        planningButton = new JButton();
        loginRequestButton = new JButton();
        signupButton = new JButton();
        reviewsPanel = new JPanel();
        saveCourseButton = new JButton();
        saveProfButton = new JButton();
        addReview = new JButton();
        reviewsHeader = new JLabel();
        reviewsTableScrollPane = new JScrollPane();
        reviewsTable = new JTable();
        planningPanel = new JPanel();
        planningHeading = new JLabel();
        recommendedCoursesButton = new JButton();
        recommendedProfessorsButton = new JButton();
        courseListLabel = new JLabel();
        profListLabel = new JLabel();
        courseListTable = new JTable();
        profListTable = new JTable();
        courseListScrollPane = new JScrollPane();
        profListScrollPane = new JScrollPane();
        removeCourseButton = new JButton();
        removeProfButton = new JButton();
        qAndAPanel = new JPanel();
        //searchLabel = new JLabel();
        //searchText = new JTextField();
        qAndATableScrollPane = new JScrollPane();
        qAndATable = new JTable();
        addReviewAuthor = new JTextField();
        addReviewCourse = new JTextField();
        addReviewProfessor = new JTextField();
        addReviewRating = new JTextField();
        addReviewCRN = new JTextField();
        addReviewText = new JTextField();
        postDiscussionButton = new JButton();
        removeReviewButton = new JButton();
        replyTable = new JTable();
        removeDiscussionButton = new JButton();
        qAndAHeader = new JLabel("Q&A");
        
        loginDialog.setTitle("login");
        loginDialog.setBackground(new Color(0, 88, 5));
        loginDialog.setForeground(new Color(40, 151, 21));
        loginDialog.setSize(new Dimension(400, 300));

        loginLabel.setText("login");

        //The text in this field is not visible, so it doesn't matter.
        passwordField.setText("password");
        
        //Default username is the following
        usernameField.setText("tomas_cerny@baylor.edu");

        //Set the text of the login button.
        loginButton.setText("login");
        
        //Create listener to actually log in.
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                //Call the function for when the button is pressed.
            	loginButtonActionPerformed(eventHappens);
            }
        });

        


        //Set text of label for password field.
        passwordLabel.setText("password:");

        //setFont is needed in order to reduce the size of the font.
        forgotPasswordButton.setFont(new Font("sansserif", 0, 8));
        //Create the text for the forgot my password button
        forgotPasswordButton.setText("forgot my password");
        
        forgotPasswordButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                forgotPasswordButtonActionPerformed(eventHappens);
            }
        });

        signupFormButton.setFont(new Font("sansserif", 0, 8));
        signupFormButton.setText("sign up");
        signupFormButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                signupFormButtonActionPerformed(eventHappens);
            }
        });

        GroupLayout loginDialogLayout = new GroupLayout(loginDialog.getContentPane());
        loginDialog.getContentPane().setLayout(loginDialogLayout);
        loginDialogLayout.setHorizontalGroup(
            loginDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, loginDialogLayout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(loginLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(184, 184, 184))
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addGroup(loginDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(loginDialogLayout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addGroup(loginDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(emailLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                            .addComponent(passwordLabel, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(loginDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(loginDialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(signupFormButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(forgotPasswordButton))
                            .addGroup(loginDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(usernameField)
                                .addComponent(passwordField))))
                    .addGroup(loginDialogLayout.createSequentialGroup()
                        .addGap(164, 164, 164)
                        .addComponent(loginButton)))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginDialogLayout.setVerticalGroup(
            loginDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(loginDialogLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loginLabel)
                .addGap(75, 75, 75)
                .addGroup(loginDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabel))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(loginDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel))
                .addGap(26, 26, 26)
                .addComponent(forgotPasswordButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(signupFormButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginButton)
                .addGap(67, 67, 67))
        );

        signupDialog.setSize(new Dimension(400, 300));

        emailLabelSignup.setText("email:");

        passwordLabelSignup.setText("password:");

        passwordField1.setText("jPasswordField1");

        emailField.setText("tomas_cerny@baylor.edu");

        usernameLabel.setText("username:");

        nameLabel.setText("name:");

        usernameTextField.setText("BuffTommyC");

        securityQuestionLabel.setText("Fav color?");

        nameTextField.setText("Tomas Cerny");
        nameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                nameTextFieldActionPerformed(eventHappens);
            }
        });

        loginReturnButton.setText("return to login");
        loginReturnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                loginReturnButtonActionPerformed(eventHappens);
            }
        });

        registerButton.setText("register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                registerButtonActionPerformed(eventHappens);
            }
        });

        GroupLayout signupDialogLayout = new GroupLayout(signupDialog.getContentPane());
        signupDialog.getContentPane().setLayout(signupDialogLayout);
        signupDialogLayout.setHorizontalGroup(
            signupDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(signupDialogLayout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                        .addComponent(usernameLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(emailLabelSignup, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE)
                        .addComponent(securityQuestionLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                        .addComponent(passwordLabelSignup, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE))
                    .addComponent(nameLabel, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                    .addComponent(passwordField1, GroupLayout.Alignment.LEADING)
                    .addComponent(emailField, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                    .addComponent(nameTextField)
                    .addComponent(securityQuestionField, GroupLayout.Alignment.LEADING)
                    .addComponent(usernameTextField, GroupLayout.Alignment.LEADING))
                .addContainerGap(103, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, signupDialogLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addComponent(loginReturnButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(144, 144, 144))
        );
        signupDialogLayout.setVerticalGroup(
            signupDialogLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(signupDialogLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(nameLabel)
                    .addComponent(nameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(emailField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(emailLabelSignup))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(securityQuestionLabel, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
                        .addComponent(securityQuestionField))
                .addGroup(signupDialogLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabelSignup))
                .addGap(18, 18, 18)
                .addComponent(loginReturnButton)
                .addGap(18, 18, 18)
                .addComponent(registerButton)
                .addContainerGap(44, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBackground(new Color(185,214,235));

        homePanel.setPreferredSize(new Dimension(589, 332));

        welcomeSplashTextArea.setColumns(20);
        welcomeSplashTextArea.setRows(5);
        welcomeSplashTextArea.setText("Welcome to Chart My Course!\nUse the buttons above to switch tabs\nto your desired functionality.");
        welcomeSplashTextPane.setViewportView(welcomeSplashTextArea);

        curUserHeading.setText("Current User: ");

        curUserLabel.setText(curUserString);
        Color homeColor = new Color(185,214,235);
        homePanel.setBackground(homeColor);

        GroupLayout homePanelLayout = new GroupLayout(homePanel);
        homePanel.setLayout(homePanelLayout);
        homePanelLayout.setHorizontalGroup(
            homePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addGroup(homePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addGap(231, 231, 231)
                        .addComponent(welcomeSplashTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGroup(homePanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(homePanelLayout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                            .addComponent(curUserLabel, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(curUserHeading, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))))
                .addContainerGap(270, Short.MAX_VALUE))
        );
        homePanelLayout.setVerticalGroup(
            homePanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(homePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(curUserHeading)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(curUserLabel)
                .addGap(62, 62, 62)
                .addComponent(welcomeSplashTextPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(211, Short.MAX_VALUE))
        );

        chartmycoursewatermark.setFont(new Font("Cloister Black", Font.PLAIN, 18));
        chartmycoursewatermark.setText("Chart My Course");

        homeButton.setFont(new Font("sansserif", Font.PLAIN, 8));
        homeButton.setText("home");
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                homeButtonActionPerformed(eventHappens);
            }
        });

        reviewsButton.setFont(new Font("sansserif", 0, 8));
        reviewsButton.setText("reviews");
        reviewsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                reviewsButtonActionPerformed(eventHappens);
            }
        });

        qAndAButton.setFont(new Font("sansserif", 0, 8));
        qAndAButton.setText("Q&A");
        qAndAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                qAndAButtonActionPerformed(eventHappens);
            }
        });

        planningButton.setFont(new Font("sansserif", 0, 8));
        planningButton.setText("planning");
        planningButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                planningButtonActionPerformed(eventHappens);
            }
        });

        loginRequestButton.setFont(new Font("sansserif", 0, 8));
        loginRequestButton.setText("login");
        loginRequestButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                loginRequestButtonActionPerformed(eventHappens);
            }
        });

        signupButton.setFont(new Font("sansserif", 0, 8));
        signupButton.setText("signup");
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                signupButtonActionPerformed(eventHappens);
            }
        });

        reviewsPanel.setPreferredSize(new Dimension(589, 332));
        reviewsPanel.setBackground(homeColor);
	    
	removeReviewButton.setText("Remove Review");
        removeReviewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                removeReviewActionPerformed(eventHappens);
            }
        });


        addReview.setText("Add Review");
        addReview.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent eventHappens) {
                addReviewButtonActionPerformed(eventHappens);
            }
        });
        reviewsHeader.setFont(new Font("sansserif", 0, 24));
        reviewsHeader.setText("Reviews");

        saveCourseButton.setText("Save Course");
        saveCourseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveCourseButtonActionPerformed(e);
            }
        });

        saveProfButton.setText("Save Professor");
        saveProfButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveProfButtonActionPerformed(e);
            }
        });

        reviewtablemodel = new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Review Author", "CRN", "Course", "Professor", "Review Score/10", "View Review"
            }
        ) {
            Class[] types = new Class [] {
                String.class, String.class, String.class, String.class, Integer.class, JButton.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        };
        reviewsTable.setModel(reviewtablemodel);
        reviewsTable.setColumnSelectionAllowed(true);
        reviewsTable.getTableHeader().setReorderingAllowed(false);
        reviewsTableScrollPane.setViewportView(reviewsTable);
        reviewsTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (reviewsTable.getColumnModel().getColumnCount() > 0) {
            reviewsTable.getColumnModel().getColumn(1).setMinWidth(50);
            reviewsTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            reviewsTable.getColumnModel().getColumn(1).setMaxWidth(50);
        }
        reviewsTable.setDefaultEditor(Object.class,null);

        Action viewReview = new AbstractAction () {

            @Override
            public void actionPerformed(ActionEvent e) {

                Review curReview = reviewArray.get(reviewsTable.getSelectedRow());
                int option = 0;

                if (curReview.getFlagged() > 0) {
                    option = JOptionPane.showConfirmDialog(null, "This review has been flagged " +
                            curReview.getFlagged() + " time(s). Admin is working on it! Do you still want to view?",
                            "Alert", JOptionPane.OK_CANCEL_OPTION);
                }

                if (option == 0) {
                    reviewDialog = new JDialog(reviewDialog, "View Review");
                    reviewDialog.setLayout(new GridLayout(3, 1));
                    JTextArea reviewContents = new JTextArea();
                    reviewContents.setColumns(50);
                    reviewContents.setRows(3);
                    reviewContents.append(curReview.getReviewBody());
                    reviewContents.setEditable(false);

                    JButton flagButton = new JButton("Flag");
                    JButton removeFlagButton = new JButton("Remove Flag");

                    flagButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            curReview.setFlagged(curReview.getFlagged() + 1);
                            JOptionPane.showMessageDialog(null, "This review has now been flagged "
                            + curReview.getFlagged() + " time(s). Admin is working on it!",
                                    "Alert", JOptionPane.WARNING_MESSAGE);

                            curReview.getFlaggedUsers().add(curUser);

                            try {
                                FileWriter myWriter = new FileWriter("reviews.txt");
                                for (int k = 0; k < reviewArray.size(); k++) {
                                    Review review = reviewArray.get(k);
                                    myWriter.write(review.getAuthor() + "," + review.getCourse() + "," + review.getCRN()
                                            + "," + review.getProfessor() + "," + review.getRating() + "," + review.getReviewBody() + ","
                                            + review.getFlagged());
                                    for (int j = 0; j < review.getFlaggedUsers().size(); j++) {
                                        myWriter.write("," + review.getFlaggedUsers().get(j).getRealName());
                                    }
                                    myWriter.write("\n");
                                }
                                myWriter.close();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            removeFlagButton.setEnabled(true);
                            flagButton.setEnabled(false);
                        }
                    });

                    removeFlagButton.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            curReview.setFlagged(curReview.getFlagged() - 1);
                            JOptionPane.showMessageDialog(null, "Your flag on this review has been removed. " +
                                            "The review has now been flagged " + curReview.getFlagged() + " time(s).",
                                    "Alert", JOptionPane.WARNING_MESSAGE);

                            curReview.getFlaggedUsers().remove(curUser);

                            try {
                                FileWriter myWriter = new FileWriter("reviews.txt");
                                for (int k = 0; k < reviewArray.size(); k++) {
                                    Review review = reviewArray.get(k);
                                    myWriter.write(review.getAuthor() + "," + review.getCourse() + "," + review.getCRN()
                                            + "," + review.getProfessor() + "," + review.getRating() + "," + review.getReviewBody() + ","
                                            + review.getFlagged());
                                    for (int j = 0; j < review.getFlaggedUsers().size(); j++) {
                                        myWriter.write("," + review.getFlaggedUsers().get(j).getRealName());
                                    }
                                    myWriter.write("\n");
                                }
                                myWriter.close();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                            flagButton.setEnabled(true);
                            removeFlagButton.setEnabled(false);
                        }
                    });

                    reviewDialog.add(reviewContents);
                    reviewDialog.add(flagButton);
                    reviewDialog.add(removeFlagButton);

                    if (!loggedIn) {
                        flagButton.setEnabled(false);
                        removeFlagButton.setEnabled(false);
                    } else {
                        if (curReview.getFlaggedUsers().contains(curUser)) {
                            removeFlagButton.setEnabled(true);
                            flagButton.setEnabled(false);
                        } else {
                            flagButton.setEnabled(true);
                            removeFlagButton.setEnabled(false);
                        }
                    }

                    reviewDialog.setSize(250,300);
                    reviewDialog.pack();
                    reviewDialog.setVisible(true);
                }
            }
        };

        ButtonColumn buttonColumn5 = new ButtonColumn(reviewsTable, viewReview, 5);

        new TableFilterHeader(reviewsTable, AutoChoices.ENABLED);
        TableRowSorter<TableModel> sorter = new TableRowSorter(reviewsTable.getModel());
        reviewsTable.setRowSorter(sorter);

        GroupLayout reviewsPanelLayout = new GroupLayout(reviewsPanel);
        reviewsPanel.setLayout(reviewsPanelLayout);
        reviewsPanelLayout.setHorizontalGroup(
            reviewsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(reviewsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reviewsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(reviewsHeader, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveCourseButton)
		    .addComponent(removeReviewButton)
                    .addComponent(saveProfButton, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
                        .addComponent(addReview,GroupLayout.PREFERRED_SIZE,130,GroupLayout.PREFERRED_SIZE))

                .addGap(5, 5, 5)
                .addComponent(reviewsTableScrollPane, GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE))
        );
        reviewsPanelLayout.setVerticalGroup(
            reviewsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(reviewsPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(reviewsHeader)
                .addGap(12, 12, 12)
                .addComponent(saveCourseButton)
		.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(removeReviewButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(saveProfButton)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(addReview)
                .addContainerGap(210, Short.MAX_VALUE))
            .addGroup(GroupLayout.Alignment.TRAILING, reviewsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(reviewsTableScrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
        );
        removeReviewButton.setVisible(false);
        planningPanel.setPreferredSize(new Dimension(589, 332));
        planningPanel.setBackground(homeColor);
        planningPanel.setVisible(false);

        planningHeading.setFont(new Font("sansserif", 0, 36));
        planningHeading.setText("Planning");

        recommendedCoursesButton.setText("Recommended Courses");
        recommendedCoursesButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                recommendedCoursesButtonActionPerformed(eventHappens);
            }
        });

        recommendedProfessorsButton.setText("Recommended Professors");
        recommendedProfessorsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                recommendedProfessorsButtonActionPerformed(eventHappens);
            }
        });

        courseListLabel.setText("Saved Courses");
        profListLabel.setText("Saved Professors");

        removeCourseButton.setText("Remove Course");
        removeCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                removeCourseButtonActionPerformed(eventHappens);
            }
        });

        removeProfButton.setText("Remove Professor");
        removeProfButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                removeProfButtonActionPerformed(eventHappens);
            }
        });

        courseListTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Courses"
                }
        ) {
            Class[] types = new Class [] {
                    String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        courseListTable.setColumnSelectionAllowed(true);
        courseListTable.getTableHeader().setReorderingAllowed(false);
        courseListScrollPane.setViewportView(courseListTable);
        courseListTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (courseListTable.getColumnModel().getColumnCount() > 0) {
            courseListTable.getColumnModel().getColumn(0).setMinWidth(150);
            courseListTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            courseListTable.getColumnModel().getColumn(0).setMaxWidth(150);
        }
        courseListTable.setDefaultEditor(Object.class, null);

        profListTable.setModel(new DefaultTableModel(
                new Object [][] {

                },
                new String [] {
                        "Professors"
                }
        ) {
            Class[] types = new Class [] {
                    String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        profListTable.setColumnSelectionAllowed(true);
        profListTable.getTableHeader().setReorderingAllowed(false);
        profListScrollPane.setViewportView(profListTable);
        profListTable.getColumnModel().getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        if (profListTable.getColumnModel().getColumnCount() > 0) {
            profListTable.getColumnModel().getColumn(0).setMinWidth(150);
            profListTable.getColumnModel().getColumn(0).setPreferredWidth(150);
            profListTable.getColumnModel().getColumn(0).setMaxWidth(150);
        }
        profListTable.setDefaultEditor(Object.class,null);

        GroupLayout planningPanelLayout = new GroupLayout(planningPanel);
        planningPanel.setLayout(planningPanelLayout);
        planningPanelLayout.setHorizontalGroup(
            planningPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(planningPanelLayout.createSequentialGroup()
                .addGap(166, 166, 166)
                .addComponent(recommendedCoursesButton)
                .addGap(70, 70, 70)
                .addComponent(recommendedProfessorsButton)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(planningPanelLayout.createSequentialGroup()
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(planningHeading, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
                .addGap(282, 282, 282))
                    .addGroup(planningPanelLayout.createSequentialGroup()
                            .addGap(180, 180, 180)
                            .addComponent(courseListLabel)
                            .addGap(156, 156, 156)
                            .addComponent(profListLabel))
                    .addGroup(planningPanelLayout.createSequentialGroup()
                            .addGap(166, 166, 166)
                            .addComponent(courseListScrollPane, 150, 150, 150)
                            .addGap(96, 96, 96)
                            .addComponent(profListScrollPane, 150, 150, 150))
                    .addGroup(planningPanelLayout.createSequentialGroup()
                            .addGap(166, 166, 166)
                            .addComponent(removeCourseButton)
                            .addGap(115, 115, 115)
                            .addComponent(removeProfButton))
        );
        planningPanelLayout.setVerticalGroup(
            planningPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, planningPanelLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addComponent(planningHeading)
                .addGap(18, 18, 18)
                .addGroup(planningPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(recommendedCoursesButton)
                    .addComponent(recommendedProfessorsButton))
                    .addGap(18, 18, 18)
                    .addGroup(planningPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(courseListLabel)
                            .addComponent(profListLabel))
                    .addGap(18, 18, 18)
                    .addGroup(planningPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(courseListScrollPane, 150, 150, 150)
                            .addComponent(profListScrollPane, 150, 150, 150))
                    .addGap(18, 18, 18)
                    .addGroup(planningPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(removeCourseButton)
                            .addComponent(removeProfButton))
                .addGap(239, 239, 239))
        );

        qAndAPanel.setPreferredSize(new Dimension(589, 332));
        qAndAPanel.setBackground(homeColor);

        //searchLabel.setFont(new Font("sansserif", 0, 24));
        //searchLabel.setText("Search:");

        /*searchText.setText("search text");
        searchText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent eventHappens) {
                searchTextActionPerformed(eventHappens);
            }
        });*/

        qAndATable.setAutoCreateRowSorter(true);

        qAndATable.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Author", "Replies", "Upvotes", "View Post", "View Replies"
            }
        ) {
            Class[] types = new Class [] {
                String.class, Integer.class, Integer.class, JButton.class, JButton.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        qAndATable.setDefaultEditor(Object.class,null);
        qAndATableScrollPane.setViewportView(qAndATable);

        /**
         * This is the functionality of clicking the View Post button
         * @author Mia Gortney
         * @version 1.0
         * @Since 1.0
         */
        Action view = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel model = (DefaultTableModel) qAndATable.getModel();

                int index = qAndATable.getSelectedRow();
                curPost = postsArray.get(qAndATable.getSelectedRow());

                    postDialog = new JDialog(postDialog, "View Post");
                    postDialog.setLayout(new GridLayout(4, 1));
                    JTextArea postContents = new JTextArea();
                    postContents.setColumns(50);
                    postContents.setRows(3);
                    postContents.append(curPost.getPostContents());
                    postContents.setEditable(false);

                    JButton upvoteButton = new JButton("Upvote");
                    addReplyButton = new JButton("Add Reply");
                    JButton removeUpvoteButton = new JButton("Remove Upvote");

                    postDialog.add(postContents);

                    upvoteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            postsArray.get(index).setUpvotes(curPost.getUpvotes() + 1);
                            qAndATable.getModel().setValueAt(postsArray.get(index).getUpvotes(), index, 2);
                            model.fireTableDataChanged();
                            removeUpvoteButton.setEnabled(true);
                            upvoteButton.setEnabled(false);
                            curPost.getUpvotedUsers().add(curUser);

                            try {
                                FileWriter myWriter = new FileWriter("posts.txt");
                                for(int k = 0; k < postsArray.size(); k++) {
                                    Post p = postsArray.get(k);
                                    myWriter.write(p.getAuthor() + "," + p.getReplyCount() + "," +
                                            p.getUpvotes() + "," + p.getPostContents());
                                    for (int j = 0; j < p.getUpvotedUsers().size(); j++) {
                                        myWriter.write("," + p.getUpvotedUsers().get(j).getRealName());
                                    }
                                    myWriter.write("\n");
                                }

                                myWriter.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    addReplyButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {

                            addReplyText = new JTextField();
                            addReply = new JTextField();
                            addReplyText.setColumns(50);
                            addReply.setColumns(50);
                            Object[] message = {
                                    "Reply:", addReply
                            };
                            int option = JOptionPane.showConfirmDialog(null, message, "Add Reply", JOptionPane.OK_CANCEL_OPTION);
                            Reply reply = new Reply();
                            reply.setAuthor(curUserString);
                            reply.setUpvotes(0);
                            reply.setPostContents(addReply.getText());
                            curPost.getReplies().add(reply);
                            curPost.setReplyCount(curPost.getReplyCount() + 1);

                            qAndATable.getModel().setValueAt(curPost.getReplyCount(), index, 1);

                            model.fireTableDataChanged();

                            try {
                                FileWriter myWriter = new FileWriter("posts.txt");
                                for(int k = 0; k < postsArray.size(); k++) {
                                    Post p = postsArray.get(k);
                                    myWriter.write(p.getAuthor() + "," + p.getReplyCount() + "," +
                                            p.getUpvotes() + "," + p.getPostContents());
                                    for (int j = 0; j < p.getUpvotedUsers().size(); j++) {
                                        myWriter.write("," + p.getUpvotedUsers().get(j).getRealName());
                                    }
                                    myWriter.write("\n");
                                }
                                myWriter.close();

                                myWriter = new FileWriter("replies.txt");
                                for (int i = 0; i < postsArray.size(); i++) {
                                    for(int j = 0; j < postsArray.get(i).getReplies().size(); j++) {

                                        Reply r = postsArray.get(i).getReplies().get(j);
                                        myWriter.write(postsArray.get(i).getAuthor() + "," +
                                                postsArray.get(i).getPostContents() + "," + r.getAuthor() + ","
                                                + r.getUpvotes() + "," + r.getPostContents());
                                        for (int k = 0; k < r.getUpvotedUsers().size(); k++) {
                                            myWriter.write("," + r.getUpvotedUsers().get(k).getRealName());
                                        }
                                        myWriter.write("\n");
                                    }
                                }
                                myWriter.close();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });
                    removeUpvoteButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            postsArray.get(index).setUpvotes(curPost.getUpvotes() - 1);
                            qAndATable.getModel().setValueAt(postsArray.get(index).getUpvotes(), index, 2);
                            model.fireTableDataChanged();
                            upvoteButton.setEnabled(true);
                            removeUpvoteButton.setEnabled(false);
                            curPost.getUpvotedUsers().remove(curUser);

                            try {
                                FileWriter myWriter = new FileWriter("posts.txt");
                                for(int k = 0; k < postsArray.size(); k++) {
                                    Post p = postsArray.get(k);
                                    myWriter.write(p.getAuthor() + "," + p.getReplyCount() + "," +
                                            p.getUpvotes() + "," + p.getPostContents());
                                    for (int j = 0; j < p.getUpvotedUsers().size(); j++) {
                                        myWriter.write("," + p.getUpvotedUsers().get(j).getRealName());
                                    }
                                    myWriter.write("\n");
                                }

                                myWriter.close();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    });

                    postDialog.add(upvoteButton);
                    postDialog.add(addReplyButton);
                    postDialog.add(removeUpvoteButton);

                    if (!loggedIn) {
                        removeUpvoteButton.setEnabled(false);
                        upvoteButton.setEnabled(false);
                        addReplyButton.setEnabled(false);
                    } else {
                        if (curPost.getUpvotedUsers().contains(curUser)) {
                            removeUpvoteButton.setEnabled(true);
                            upvoteButton.setEnabled(false);
                        } else {
                            removeUpvoteButton.setEnabled(false);
                            upvoteButton.setEnabled(true);
                        }
                        addReplyButton.setEnabled(true);
                    }

                    postDialog.setSize(250,300);
                    postDialog.pack();
                    postDialog.setVisible(true);
            }
        };

        Action viewReply = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                DefaultTableModel model = (DefaultTableModel) replyTable.getModel();

                curPost = postsArray.get(qAndATable.getSelectedRow());
                int index = replyTable.getSelectedRow();
                Reply reply = curPost.getReplies().get(index);

                replyDialog = new JDialog(replyDialog, "View Post");
                replyDialog.setLayout(new GridLayout(3, 1));
                JTextArea postContents = new JTextArea();
                postContents.setColumns(50);
                postContents.setRows(3);
                postContents.append(reply.getPostContents());
                postContents.setEditable(false);
                replyDialog.add(postContents);

                JButton upvoteButton = new JButton("Upvote");
                JButton removeUpvoteButton = new JButton("Remove Upvote");

                upvoteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reply.setUpvotes(reply.getUpvotes() + 1);
                        curPost.getReplies().get(index).setUpvotes(reply.getUpvotes());
                        replyTable.getModel().setValueAt(reply.getUpvotes(), index, 1);
                        model.fireTableDataChanged();
                        removeUpvoteButton.setEnabled(true);
                        upvoteButton.setEnabled(false);
                        curPost.getReplies().get(index).getUpvotedUsers().add(curUser);

                        try {
                            FileWriter myWriter = new FileWriter("replies.txt");
                            for (int i = 0; i < postsArray.size(); i++) {
                                for(int j = 0; j < postsArray.get(i).getReplies().size(); j++) {

                                    Reply r = postsArray.get(i).getReplies().get(j);
                                    myWriter.write(postsArray.get(i).getAuthor() + "," +
                                            postsArray.get(i).getPostContents() + "," + r.getAuthor() + ","
                                            + r.getUpvotes() + "," + r.getPostContents());
                                    for (int k = 0; k < r.getUpvotedUsers().size(); k++) {
                                        myWriter.write("," + r.getUpvotedUsers().get(k).getRealName());
                                    }
                                    myWriter.write("\n");
                                }
                            }
                            myWriter.close();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                removeUpvoteButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        reply.setUpvotes(reply.getUpvotes() - 1);
                        curPost.getReplies().get(index).setUpvotes(reply.getUpvotes());
                        replyTable.getModel().setValueAt(reply.getUpvotes(), index, 1);
                        model.fireTableDataChanged();
                        upvoteButton.setEnabled(true);
                        removeUpvoteButton.setEnabled(false);
                        curPost.getReplies().get(index).getUpvotedUsers().remove(curUser);

                        try {
                            FileWriter myWriter = new FileWriter("replies.txt");
                            for (int i = 0; i < postsArray.size(); i++) {
                                for(int j = 0; j < postsArray.get(i).getReplies().size(); j++) {

                                    Reply r = postsArray.get(i).getReplies().get(j);
                                    myWriter.write(postsArray.get(i).getAuthor() + "," +
                                            postsArray.get(i).getPostContents() + "," + r.getAuthor() + ","
                                            + r.getUpvotes() + "," + r.getPostContents());
                                    for (int k = 0; k < r.getUpvotedUsers().size(); k++) {
                                        myWriter.write("," + r.getUpvotedUsers().get(k).getRealName());
                                    }
                                    myWriter.write("\n");
                                }
                            }
                            myWriter.close();

                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                });

                replyDialog.add(upvoteButton);
                replyDialog.add(removeUpvoteButton);

                if (!loggedIn) {
                    removeUpvoteButton.setEnabled(false);
                    upvoteButton.setEnabled(false);
                } else {
                    if (curPost.getReplies().get(replyTable.getSelectedRow()).getUpvotedUsers().contains(curUser)) {
                        removeUpvoteButton.setEnabled(true);
                        upvoteButton.setEnabled(false);
                    } else {
                        removeUpvoteButton.setEnabled(false);
                        upvoteButton.setEnabled(true);
                    }
                }
                replyDialog.setSize(250,300);
                replyDialog.pack();
                replyDialog.setVisible(true);
            }
        };

        /**
         * This is the functionality of removing a reply.
         * @author Mia Gortney
         * @version 1.0
         * @Since 1.0
         */
        Action removeReply = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (loggedIn) {
                    curPost = postsArray.get(qAndATable.getSelectedRow());
                    Reply reply = curPost.getReplies().get(replyTable.getSelectedRow());
                    if (reply.getAuthor().equals(curUser.getRealName())) {

                        int option = JOptionPane.showConfirmDialog(null,
                                "Are you sure you want to remove this reply?");
                        if (option == 0) {
                            ((DefaultTableModel) replyTable.getModel()).removeRow(replyTable.getSelectedRow());
                            curPost.getReplies().remove(reply);
                            curPost.setReplyCount(curPost.getReplyCount() - 1);
                            postsArray.get(qAndATable.getSelectedRow()).setReplyCount(curPost.getReplyCount());
                            qAndATable.getModel().setValueAt(curPost.getReplyCount(), qAndATable.getSelectedRow(), 1);
                            DefaultTableModel model = (DefaultTableModel) replyTable.getModel();
                            model.fireTableDataChanged();

                            try {
                                FileWriter myWriter = new FileWriter("posts.txt");
                                for(int k = 0; k < postsArray.size(); k++) {
                                    Post p = postsArray.get(k);
                                    myWriter.write(p.getAuthor() + "," + p.getReplyCount() + "," +
                                            p.getUpvotes() + "," + p.getPostContents());
                                    for (int j = 0; j < p.getUpvotedUsers().size(); j++) {
                                        myWriter.write("," + p.getUpvotedUsers().get(j).getRealName());
                                    }
                                    myWriter.write("\n");
                                }
                                myWriter.close();

                                myWriter = new FileWriter("replies.txt");
                                for (int i = 0; i < postsArray.size(); i++) {
                                    for(int j = 0; j < postsArray.get(i).getReplies().size(); j++) {

                                        Reply r = postsArray.get(i).getReplies().get(j);
                                        myWriter.write(postsArray.get(i).getAuthor() + "," +
                                                postsArray.get(i).getPostContents() + "," + r.getAuthor() + ","
                                                + r.getUpvotes() + "," + r.getPostContents());
                                        for (int k = 0; k < r.getUpvotedUsers().size(); k++) {
                                            myWriter.write("," + r.getUpvotedUsers().get(k).getRealName());
                                        }
                                        myWriter.write("\n");
                                    }
                                }
                                myWriter.close();

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }

                    } else {
                        JOptionPane.showMessageDialog(null,
                                "You can't delete something you didn't write!",
                                "Alert", JOptionPane.WARNING_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "You have to log in first.",
                            "Alert", JOptionPane.WARNING_MESSAGE);
                }
            }
        };

        /**
         * This is the functionality of clicking the View Replies button
         * @author Mia Gortney
         * @version 1.0
         * @Since 1.0
         */
        Action viewReplies = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                replyDialog = new JDialog(replyDialog, "View Reply");
                replyDialog.setLayout(new GridLayout(1, 1));
                Post post = postsArray.get(qAndATable.getSelectedRow());
                replyTable.setModel(new DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Author", "Upvotes", "View Reply", "Remove"
                        }
                ) {
                    final Class[] types = new Class [] {
                            String.class, Integer.class, JButton.class, JButton.class
                    };

                    public Class getColumnClass(int columnIndex) {
                        return types [columnIndex];
                    }
                });
                JScrollPane replyScrollPane = new JScrollPane();
                replyTable.setDefaultEditor(Object.class,null);
                replyScrollPane.setViewportView(replyTable);

                ButtonColumn buttonColumn3 = new ButtonColumn(replyTable, viewReply,2);
                ButtonColumn buttonColumn4 = new ButtonColumn(replyTable, removeReply, 3);

                DefaultTableModel model = (DefaultTableModel) replyTable.getModel();

                for (Reply iterReply : post.getReplies()) {
                    model.insertRow(replyTable.getRowCount(), new Object[] {iterReply.getAuthor(), iterReply.getUpvotes(), "View Reply", "Remove"});
                }
                model.fireTableDataChanged();

                replyDialog.setContentPane(replyScrollPane);
                replyDialog.setSize(250,200);
                replyDialog.pack();
                replyDialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                replyDialog.setVisible(true);
            }
        };
        
        ButtonColumn buttonColumn = new ButtonColumn(qAndATable, view, 3);
        ButtonColumn buttonColumn2 = new ButtonColumn(qAndATable, viewReplies, 4);

        // TODO render buttons correctly in table
        qAndATable.setDefaultRenderer(JButton.class, new JTableButtonRenderer());

        new TableFilterHeader(qAndATable, AutoChoices.ENABLED);
        TableRowSorter<TableModel> QnAsorter = new TableRowSorter(qAndATable.getModel());
        qAndATable.setRowSorter(QnAsorter);

        postDiscussionButton.setFont(new Font("Segoe UI", 0, 8));
        postDiscussionButton.setText("Post New Discussion");

        postDiscussionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewDiscussionActionPerformed(e);
            }
        });

        removeDiscussionButton.setFont(new Font("Segoe UI", 0, 8));
        removeDiscussionButton.setText("Remove Discussion");

        removeDiscussionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeDiscussionActionPerformed(e);
            }
        });

        GroupLayout qAndAPanelLayout = new GroupLayout(qAndAPanel);
        qAndAPanel.setLayout(qAndAPanelLayout);
        qAndAPanelLayout.setHorizontalGroup(
            qAndAPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, qAndAPanelLayout.createSequentialGroup()
                .addContainerGap(28, Short.MAX_VALUE)
                .addGroup(qAndAPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    .addGroup(qAndAPanelLayout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(postDiscussionButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addComponent(removeDiscussionButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(qAndATableScrollPane, GroupLayout.PREFERRED_SIZE, 452, GroupLayout.PREFERRED_SIZE))
                .addGap(109, 109, 109)
        );
        qAndAPanelLayout.setVerticalGroup(
            qAndAPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(qAndAPanelLayout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(qAndAPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                    //.addComponent(searchLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    //.addComponent(searchText)
                    .addComponent(postDiscussionButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(removeDiscussionButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(qAndATableScrollPane, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(chartmycoursewatermark, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(homeButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reviewsButton, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(qAndAButton, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(planningButton)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 100, Short.MAX_VALUE)
                .addComponent(loginRequestButton, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(signupButton, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(planningPanel, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(reviewsPanel, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(homePanel, GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                    .addContainerGap()))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(79, Short.MAX_VALUE)
                    .addComponent(qAndAPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(79, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(homeButton)
                    .addComponent(reviewsButton)
                    .addComponent(qAndAButton)
                    .addComponent(planningButton)
                    .addComponent(chartmycoursewatermark)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(signupButton)
                            .addComponent(loginRequestButton))))
                .addGap(50, 50, 50)
                .addComponent(planningPanel, GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addGap(32, 32, 32))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(81, 81, 81)
                    .addComponent(reviewsPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(40, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(37, 37, 37)
                    .addComponent(homePanel, GroupLayout.PREFERRED_SIZE, 403, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(13, Short.MAX_VALUE)))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(67, Short.MAX_VALUE)
                    .addComponent(qAndAPanel, GroupLayout.PREFERRED_SIZE, 325, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(61, Short.MAX_VALUE)))
        );

        reviewsPanel.setVisible(false);
        planningPanel.setVisible(false);
        qAndAPanel.setVisible(false);

        pack();
    }
    
    /**
     * This switches to the home tab
     * @author Harm Drenth
     * @version 1.0
     * @since 1.0
     */

    private void homeButtonActionPerformed(ActionEvent eventHappens) {
        hideAll();
        homePanel.setVisible(true);
    }

    
    /**
     * This is the forgot password listener
     * @author Harm Drenth
     * @version 1.0
     * @since 1.0
     */
    private void forgotPasswordButtonActionPerformed(ActionEvent eventHappens) {
        // TODO add forgot password functionality
    }

    /**
     * This is the login button at the top right
     * @author Harm Drenth
     * @version 1.0
     * @since 1.0
     */
    private void loginRequestButtonActionPerformed(ActionEvent eventHappens) {
	 if(loggedIn) {
    		curUserLabel.setText("not logged in");
    		loggedIn = false;
    		loginRequestButton.setText("login");
		removeReviewButton.setVisible(false);
    	}
        loginDialog.setVisible(true);
    }

    //This is the login button within the login dialog
    private void loginButtonActionPerformed(ActionEvent eventHappens) {
        User inputUser = new User();
        inputUser.setEmail(usernameField.getText());
        inputUser.setUserName(usernameField.getText());
        inputUser.setPassword(new String(passwordField.getPassword()));
        
        boolean userFound = false;
        
        for (User iterUser : userArray) {
        	if (iterUser.compare(inputUser)) {
        		userFound = true;
        		inputUser.setRealName(iterUser.getRealName());
                curUser = iterUser;
        		break;
        	}
        }
        
        if (userFound) {
        	loginDialog.setVisible(false);
            curUserString = inputUser.getRealName();
            curUserLabel.setText(curUserString);
	        loggedIn = true;
            loginRequestButton.setText("logout");
	        if (curUserString.equalsIgnoreCase("admin")) {
            	removeReviewButton.setVisible(true);
            }
        } else {
        	JOptionPane.showMessageDialog(null, "Account information not found.");
        }
    	
        
    }

    /**
     * This is the functionality of clicking the Recommended Courses button
     *
     * @param eventHappens the given ActionEvent that called this method
     */
    public void recommendedCoursesButtonActionPerformed(ActionEvent eventHappens) {
        recommendedCourseDialog = new JDialog(this, "Recommended Course");
        recommendedCourseDialog.setLayout(new GridLayout(4, 1));

        List<JLabel> courses = new ArrayList<>();

        List<String> years = new ArrayList<>();
        years.add("--");
        years.add("Freshman");
        years.add("Sophmore");
        years.add("Junior");
        years.add("Senior");

        List<String> sem = new ArrayList<>();
        sem.add("--");
        sem.add("Fall");
        sem.add("Spring");

        JComboBox yearList = new JComboBox(years.toArray());
        JComboBox semList = new JComboBox(sem.toArray());

        JButton refresh = new JButton("Refresh");

        recommendedCourseDialog.add(refresh);
        recommendedCourseDialog.add(yearList);
        recommendedCourseDialog.add(semList);

        JPanel coursePanel = new JPanel();
        coursePanel.setLayout(new BoxLayout(coursePanel, BoxLayout.Y_AXIS));
        recommendedCourseDialog.add(coursePanel);

        for(int i = 0; i < courses.size(); i++) {
            coursePanel.add(courses.get(i));
        }

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                courses.clear();
                int num = coursePanel.getComponentCount();
                for(int i = 0; i < num; i++) {
                    coursePanel.remove(0);
                }
                int value = -1;

                if(yearList.getSelectedItem() == null || yearList.getSelectedItem().equals("--")) {
                    courses.add(new JLabel("Please select a Year"));
                }
                else if(yearList.getSelectedItem().equals("Freshman")) {
                    value = 1;
                }
                else if(yearList.getSelectedItem().equals("Sophmore")) {
                    value = 2;
                }
                else if(yearList.getSelectedItem().equals("Junior")) {
                    value = 3;
                }
                else if(yearList.getSelectedItem().equals("Senior")) {
                    value = 4;
                }


                if(semList.getSelectedItem() == null || semList.getSelectedItem().equals("--")) {
                    courses.add(new JLabel("Please select a Semester"));
                    value = -1;
                }
                else if(semList.getSelectedItem().equals("Fall")) {
                    value = value*2 - 1;
                }
                else if(semList.getSelectedItem().equals("Spring")) {
                    value = value*2;
                }

                if(value >= 1) {
                    try {


                        BufferedReader reader = new BufferedReader(new FileReader("RecCourses.csv"));
                        String line = "";

                        for(int i = 0; i < value; i++) {
                            line = reader.readLine();
                        }

                        String[] split = line.split(",");

                        for(int i = 0; i < Integer.parseInt(split[1]); i++) {
                            courses.add(new JLabel(split[2 + i]));
                        }

                    }
                    catch (IOException e2) {
                        System.out.println("IO Error");
                        e2.printStackTrace();
                    }
                    catch (IndexOutOfBoundsException e2) {
                        System.out.println("Index error");
                        e2.printStackTrace();
                    }
                }

                for(int i = 0; i < courses.size(); i++) {
                    coursePanel.add(courses.get(i));
                }
                coursePanel.updateUI();

            }
        });

        recommendedCourseDialog.setSize(250,300);
        recommendedCourseDialog.setVisible(true);
    }

    /**
     * This is the functionality of clicking the Recommended Professor button
     *
     * @param eventHappens the given ActionEvent that called this method
     */
    public void recommendedProfessorsButtonActionPerformed(ActionEvent eventHappens) {
        recommendedProfessorDialog = new JDialog(this, "Recommended Professor");
        recommendedProfessorDialog.setLayout(new GridLayout(3, 1));

        List<JLabel> profs = new ArrayList<>();

        List<String> courses = new ArrayList<>();
        courses.add("--");

        try {
            BufferedReader reader = new BufferedReader(new FileReader("RecProfessors.csv"));

            String line;
            while((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                if(split.length > 0) {
                    courses.add(split[0]);
                }
            }
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }

        JComboBox courseList = new JComboBox(courses.toArray());

        JButton refresh = new JButton("Refresh");

        recommendedProfessorDialog.add(refresh);
        recommendedProfessorDialog.add(courseList);

        JPanel professorPanel = new JPanel();
        professorPanel.setLayout(new BoxLayout(professorPanel, BoxLayout.Y_AXIS));
        recommendedProfessorDialog.add(professorPanel);

        refresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                profs.clear();
                int num = professorPanel.getComponentCount();
                for(int i = 0; i < num; i++) {
                    professorPanel.remove(0);
                }
                try {
                    BufferedReader reader = new BufferedReader(new FileReader("RecProfessors.csv"));

                    String line;
                    while((line = reader.readLine()) != null) {
                        String[] split = line.split(",");
                        if(split.length > 0) {
                            if(courseList.getSelectedItem().equals(split[0])) {
                                for(int i = 1; i < split.length; i++) {
                                    profs.add(new JLabel(split[i]));
                                }
                                break;
                            }
                        }
                    }
                    if(profs.size() == 0) {
                        profs.add(new JLabel("Please select a course"));
                    }
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
                for(int i = 0; i < profs.size(); i++) {
                    professorPanel.add(profs.get(i));
                }
                professorPanel.updateUI();
            }
        });

        recommendedProfessorDialog.setSize(250,300);
        recommendedProfessorDialog.setVisible(true);

    }


    private void planningButtonActionPerformed(ActionEvent eventHappens) {
        hideAll();
        planningPanel.setVisible(true);
    }

    // Remove a selected course from the table of saved courses
    private void removeCourseButtonActionPerformed(ActionEvent eventHappens) {
        int row = courseListTable.getSelectedRow();
        if (row >= 0) {
            DefaultTableModel model = (DefaultTableModel) courseListTable.getModel();
            String course = "" + model.getValueAt(row, 0);
            int answer = JOptionPane
                    .showConfirmDialog(null,
                            "Do you want to remove " + course + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                model.removeRow(row);
                curUser.removeCourse(course);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No course selected!");
        }
    }

    // Remove a selected professor from the table of saved professors
    private void removeProfButtonActionPerformed(ActionEvent eventHappens) {
        int row = profListTable.getSelectedRow();
        if (row >= 0) {
            DefaultTableModel model = (DefaultTableModel) profListTable.getModel();
            String professor = "" + model.getValueAt(row, 0);
            int answer = JOptionPane
                    .showConfirmDialog(null,
                            "Do you want to remove " + professor + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                model.removeRow(row);
                curUser.removeProfessor(professor);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No professor selected!");
        }

    }

    // Save a course that the user selects from the reviews table
    private void saveCourseButtonActionPerformed(ActionEvent eventHappens) {
        int col = reviewsTable.getSelectedColumn();
        int row = reviewsTable.getSelectedRow();
        if (col == 2 && row >= 0) {
            DefaultTableModel model = (DefaultTableModel) reviewsTable.getModel();
            String course = "" + model.getValueAt(row, col);
            int answer = JOptionPane
                    .showConfirmDialog(null,
                            "Do you want to save " + course + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                if (curUser.saveCourse(course)) {
                    DefaultTableModel courseModel = (DefaultTableModel) courseListTable.getModel();
                    courseModel.addRow(new Object[] {course});
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No course selected!");
        }
    }

    // Save a professor that the user selects from the reviews table
    private void saveProfButtonActionPerformed(ActionEvent eventHappens) {
        int col = reviewsTable.getSelectedColumn();
        int row = reviewsTable.getSelectedRow();
        if (col == 3 && row >= 0) {
            DefaultTableModel model = (DefaultTableModel) reviewsTable.getModel();
            String professor = "" + model.getValueAt(row, col);
            int answer = JOptionPane
                    .showConfirmDialog(null,
                            "Do you want to save " + professor + "?",
                            "Confirm", JOptionPane.YES_NO_OPTION);
            if (answer == 0) {
                if (curUser.saveProfessor(professor)) {
                    DefaultTableModel profModel = (DefaultTableModel) profListTable.getModel();
                    profModel.addRow(new Object[] {professor});
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "No professor selected!");
        }
    }

    private void addReviewButtonActionPerformed(ActionEvent eventHappens){
        if(loggedIn) {
            addReviewText.setColumns(50);
            Object[] message = {
                    "CRN:", addReviewCRN,
                    "Author:", addReviewAuthor,
                    "Course:", addReviewCourse,
                    "Professor:", addReviewProfessor,
                    "Rating 1-10:", addReviewRating,
                    "Review Text:", addReviewText
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Add Review", JOptionPane.OK_CANCEL_OPTION);
            //Input Validation:
            Object message1 = new String[]{"Please enter a numeric value for the course rating"};
            Object message2 = new String[]{"Please enter a value between 1 and 10"};
            try{
                int rating = Integer.parseInt(addReviewRating.getText());
            }
            catch(NumberFormatException nfe){
               JOptionPane.showMessageDialog(null, message1, "ERROR", JOptionPane.OK_CANCEL_OPTION);
                return;
            }
            if(Integer.parseInt(addReviewRating.getText()) > 10 ||  Integer.parseInt(addReviewRating.getText()) < 1){
                JOptionPane.showMessageDialog(null, message2, "ERROR", JOptionPane.OK_CANCEL_OPTION);
                return;
            }
            ((DefaultTableModel) reviewsTable.getModel()).insertRow(reviewsTable.getRowCount(),
                    new Object[]{addReviewAuthor.getText(), addReviewCRN.getText(), addReviewCourse.getText(), addReviewProfessor.getText(), Integer.parseInt(addReviewRating.getText()), "View Review"});
            Review temp = new Review();
            temp.setCourse(addReviewCourse.getText());
            temp.setAuthor(addReviewAuthor.getText());
            temp.setCRN(addReviewCRN.getText());
            temp.setProfessor(addReviewProfessor.getText());
            temp.setReviewBody(addReviewText.getText());
            temp.setRating(Integer.parseInt(addReviewRating.getText()));
            reviewArray.add(temp);

            try {
                FileWriter myWriter = new FileWriter("reviews.txt");
                for (int k = 0; k < reviewArray.size(); k++) {
                    Review review = reviewArray.get(k);
                    myWriter.write(review.getAuthor() + "," + review.getCourse() + "," + review.getCRN()
                            + "," + review.getProfessor() + "," + review.getRating() + "," + review.getReviewBody() + ","
                            + review.getFlagged());
                    for (int j = 0; j < review.getFlaggedUsers().size(); j++) {
                        myWriter.write("," + review.getFlaggedUsers().get(j).getRealName());
                    }
                    myWriter.write("\n");
                }
                myWriter.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            displayNotLoggedInError();
        }
    }

    private void displayNotLoggedInError() {
        JFrame notLogged = new JFrame("Not logged in");
        JLabel notLoggedLbl = new JLabel("You must be logged in to perform this action");

        notLogged.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        notLogged.add(notLoggedLbl);
        notLogged.pack();
        notLogged.setVisible(true);
    }
	
    private void removeReviewActionPerformed(ActionEvent eventHappens){
    	//TODO removeReview
    	
    	
    	int i = reviewsTable.getSelectedRow();
    	reviewtablemodel.removeRow(i);
        Review r = reviewArray.get(i);
        reviewArray.remove(r);

    		try {
        		FileWriter myWriter = new FileWriter("reviews.txt");
                for (int k = 0; k < reviewArray.size(); k++) {
                    Review review = reviewArray.get(k);
                    myWriter.write(review.getAuthor() + "," + review.getCourse() + "," + review.getCRN()
                    + "," + review.getProfessor() + "," + review.getRating() + "," + review.getReviewBody() + ","
                    + review.getFlagged());
                    for (int j = 0; j < review.getFlaggedUsers().size(); j++) {
                        myWriter.write("," + review.getFlaggedUsers().get(j).getRealName());
                    }
                    myWriter.write("\n");
                }
        		myWriter.close();
        	}
        	catch (Exception e) {
    			e.printStackTrace();
    		}	
    }	
	
    private void reviewsButtonActionPerformed(ActionEvent eventHappens) {
        hideAll();
        reviewsPanel.setVisible(true);
        
    }

    private void nameTextFieldActionPerformed(ActionEvent eventHappens) {
        // TODO add name text field functionality
    }

    private void loginReturnButtonActionPerformed(ActionEvent eventHappens) {
        signupDialog.setVisible(false);
        loginDialog.setVisible(true);
        
    }

    private void signupButtonActionPerformed(ActionEvent eventHappens) {
        signupDialog.setVisible(true);
    }

    private void signupFormButtonActionPerformed(ActionEvent eventHappens) {
        loginDialog.setVisible(false);
        signupDialog.setVisible(true);

    }

    /**
     * This is the functionality for add discussion post
     * @author Mia Gortney
     * @version 1.0
     * @since 1.0
     */
    private void addNewDiscussionActionPerformed(ActionEvent eventHappens) {

        if (loggedIn) {
            addDiscussionText = new JTextField();
            addDiscussion = new JTextField();
            addDiscussionText.setColumns(50);
            addDiscussion.setColumns(50);
            Object[] message = {
                    "Discussion:", addDiscussion
            };
            int option = JOptionPane.showConfirmDialog(null, message, "Add Discussion Post", JOptionPane.OK_CANCEL_OPTION);
            if (option == 0) {
                ((DefaultTableModel) qAndATable.getModel()).insertRow(qAndATable.getRowCount(),
                        new Object[]{curUser.getRealName(), 0, 0, "View Post", "View Replies"});
                Post temp = new Post();
                temp.setAuthor(curUser.getRealName());
                temp.setUpvotes(0);
                temp.setReplyCount(0);
                temp.setPostContents(addDiscussion.getText());
                postsArray.add(temp);

                try {
                    FileWriter myWriter = new FileWriter("posts.txt");
                    for(int k = 0; k < postsArray.size(); k++) {
                        Post p = postsArray.get(k);
                        myWriter.write(p.getAuthor() + "," + p.getReplyCount() + "," +
                                p.getUpvotes() + "," + p.getPostContents());
                        for (int j = 0; j < p.getUpvotedUsers().size(); j++) {
                            myWriter.write("," + p.getUpvotedUsers().get(j).getRealName());
                        }
                        myWriter.write("\n");
                    }
                    myWriter.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "You aren't logged in!", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * This is the functionality for remove discussion post
     * @author Mia Gortney
     * @version 1.0
     * @since 1.0
     */
    private void removeDiscussionActionPerformed(ActionEvent eventHappens) {

    	int selectedRow = qAndATable.getSelectedRow();
    	if (selectedRow >= 0) {
	    	if (postsArray.get(qAndATable.getSelectedRow()).getAuthor().equals(curUser.getRealName())) {
	
	            Post post = postsArray.get(qAndATable.getSelectedRow());
	            int answer = JOptionPane
	                    .showConfirmDialog(null,
	                            "Do you want to remove \"" + post.getPostContents() + "\"?",
	                            "Confirm", JOptionPane.YES_NO_OPTION);
	            if (answer == 0) {
	                DefaultTableModel model = (DefaultTableModel) qAndATable.getModel();
	               
	                	model.removeRow(selectedRow);
	                    postsArray.remove(selectedRow);

                    try {
                        FileWriter myWriter = new FileWriter("posts.txt");
                        for(int k = 0; k < postsArray.size(); k++) {
                            Post p = postsArray.get(k);
                            myWriter.write(p.getAuthor() + "," + p.getReplyCount() + "," +
                                    p.getUpvotes() + "," + p.getPostContents());
                            for (int j = 0; j < p.getUpvotedUsers().size(); j++) {
                                myWriter.write("," + p.getUpvotedUsers().get(j).getRealName());
                            }
                            myWriter.write("\n");
                        }
                        myWriter.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
	                
	        } else {
	            JOptionPane.showMessageDialog(null,"You can't remove discussions that you didn't write!","Alert",JOptionPane.WARNING_MESSAGE);
	        }
    	} else {
            JOptionPane.showMessageDialog(null,"No discussion selected!","Alert",JOptionPane.WARNING_MESSAGE);
    	}
    }

    private void qAndAButtonActionPerformed(ActionEvent eventHappens) {
       hideAll();
       qAndAPanel.setVisible(true);
    }

    //This is the event for when the register button is pressed
    private void registerButtonActionPerformed(ActionEvent eventHappens) {
    	
    	//Initialize variables to hold the values of the text fields.
    	String readRealName, readUserName, readEmail, readPassword, readAnswer;
    	
    	//We set the values of the variables to what is in the corresponding fields.
        readRealName = nameTextField.getText();
        readUserName = usernameTextField.getText();
        readEmail = emailField.getText();
        readPassword = (new String(passwordField1.getPassword()));
        readAnswer = securityQuestionField.getText();
        
        //If any of them are empty, the user is alerted that they cannot continue.
        if (readRealName.isEmpty() || readUserName.isEmpty() || readEmail.isEmpty() || readPassword.isEmpty() || readAnswer.isEmpty()) {
        	JOptionPane.showMessageDialog(null, "Error: no fields can be blank");
        }
        
        //If everything is populated, create a new user.
        else {
        	
        	//Create new User with given fields
        	User userToRegister = null;
		try {
			userToRegister = new User(readRealName, readUserName, readEmail, readPassword, readAnswer);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, "Error: please enter a valid baylor email");
		}
        	
        	//We start by assuming the user is unique.
        	boolean isUniqueUser = true;
        	
        	//Iterate through the known users, making sure we don't already have this user
        	//registered.
        	for (User iterUser : userArray) {
        		//If we find it, it must not be a unique user. Tell the user this.
        		if (iterUser.equals(userToRegister)) {
                	JOptionPane.showMessageDialog(null, "Error: user already exists!");
                	isUniqueUser = false;
                	break;
        		}
        	}
        	//If it is indeed unique, add it to the user array.
        	//NOTE: this does not add the user to the users.txt file!
        	//TODO: add user persistence
        	if (isUniqueUser) {
        		userArray.add(userToRegister);
		try {
            		FileWriter myWriter = new FileWriter("users.txt", true);
			myWriter.write("\n");
            		myWriter.write(readRealName + "," + readUserName + "," + readEmail + "," + readPassword + "," + readAnswer);
        
            		myWriter.close();
            	}
            	catch (Exception e) {
        			e.printStackTrace();
        		}
            	JOptionPane.showMessageDialog(null, "User created successfully!");
                signupDialog.setVisible(false);
                loginDialog.setVisible(true);
        	}
        	
        	
        }
    }

    //This is the initialization function that populates internal "databases".
    //We also set the frame as visible here, and display the login dialog.
    //TODO: enforce user login (make sure they can't close the window until they are logged in)
    public void initialize() {
        this.setVisible(true);
        loginDialog.setVisible(true);
        initTestUsers();
        initTestReviews();
        initTestPosts();
        initReplies();

        initCourseListTable();
        initProfListTable();
    }
    
    //In order to understand this function, you need to understand how
    //our frame is rendered. The frame is essentially modeled after an HTML canvas
    // - all the tabs (planning, reviews, etc.) are already created, we just choose 
    //which one we want the user to see. They are all layered on top of each other.
    //This means when we want to show a "tab", we just hide all the JPanels with this
    //function, then explicitly set the panel we want as visible.
    //All this function does is set all tabs to be not visible.
    //We leave the setting of visible to the function that called this one.
    public void hideAll() {
        homePanel.setVisible(false);
        reviewsPanel.setVisible(false);
        planningPanel.setVisible(false);
        qAndAPanel.setVisible(false);

        // used for recommended Course Dialog box
        // change made by Rico
        if(recommendedCourseDialog != null){
            recommendedCourseDialog.setVisible(false);
        }
        if(recommendedProfessorDialog != null) {
            recommendedProfessorDialog.setVisible(false);
        }
    }
    
    //This function loads reviews from the reviews.txt file. 
    
    //TODO: add "make a review" functionality
    public void initTestReviews() {
    	//Open the reviews file and a scanner for it.
    	File reviewFile = new File("reviews.txt");
    	Scanner reviewScanner;
		try {
			reviewScanner = new Scanner(reviewFile);
			while (reviewScanner.hasNextLine()) {
	    		//call createReviewFromLine on read line, which does exactly as it says
                reviewArray.add(createReviewFromLine(reviewScanner.nextLine()));
            }
	    	//Call table creation function
	    	initReviewTable();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //This function creates our tablemodel from our review array.
    public void initReviewTable() {
    	DefaultTableModel model = (DefaultTableModel) reviewsTable.getModel();

    	//Iterate through review array, and create a row in our table for each
    	for (Review iterReview : reviewArray) {
    		model.insertRow(reviewsTable.getRowCount(), new Object[] {iterReview.getAuthor(), iterReview.getCRN(), iterReview.getCourse(), iterReview.getProfessor(), iterReview.getRating(),"View Review"});
    	}
    	//Make sure and tell the table we changed things
    	model.fireTableDataChanged();
    	
    }

    // Create table of saved courses from the user's saved courses list
    public void initCourseListTable() {
        DefaultTableModel model = (DefaultTableModel) courseListTable.getModel();

        // Add the user's saved courses to the course list table
        if (curUser != null) {
        	for (String course : curUser.getCourseList()) {
                model.insertRow(courseListTable.getRowCount(), new Object[] {course});
            }
            // Tell the table we changed things
            model.fireTableDataChanged();
        }
        
    }

    // Create table of saved professors from the user's saved professors list
    public void initProfListTable() {
        DefaultTableModel model = (DefaultTableModel) profListTable.getModel();

        // Add the user's saved professors to the professor list table
        if (curUser != null) {
        	for (String professor : curUser.getProfessorList()) {
                model.insertRow(profListTable.getRowCount(), new Object[] {professor});
            }
            // Tell the table we changed things
            model.fireTableDataChanged();
        }
        
    }

    //This function takes a line of input, and makes a review object from it.
    //The format is as follows: 
    //NAME,CLASS,CRN,PROF,RATING,DESCRIPTION
    public Review createReviewFromLine(String line) {
    	Review readReview = new Review();
    	//Split the line by commas, then use the array to create the user
    	List<String> result = Arrays.asList(line.split(","));
    	readReview.setAuthor(result.get(0));
    	readReview.setCourse(result.get(1));
    	readReview.setCRN(result.get(2));
    	readReview.setProfessor(result.get(3));
    	readReview.setRating(Integer.parseInt(result.get(4)));
    	readReview.setReviewBody(result.get(5));
        readReview.setFlagged(Integer.parseInt(result.get(6)));
        for (int i = 0; i < readReview.getFlagged(); i++) {
            for (int j = 0; j < userArray.size(); j++) {
                if (userArray.get(j).getRealName().equals(result.get(i + 7))) {
                    readReview.getFlaggedUsers().add(userArray.get(j));
                }
            }
        }
    	
    	return readReview;
    }
    
    //This is just like the initTestReviews function, for users.
    public void initTestUsers() {
    	File userFile = new File("users.txt");
    	Scanner userScanner;
		try {
			userScanner = new Scanner(userFile);
			while (userScanner.hasNextLine()) {
	    		userArray.add(createUserFromLine(userScanner.nextLine()));
	    	}
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    }

    // This class defines the button renderer.
    static class JTableButtonRenderer implements TableCellRenderer {
        @Override public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                                                                 boolean hasFocus, int row, int column) {
            return (JButton) value;
        }
    }
    
    //This function creates a user from a line.
    public User createUserFromLine(String line) {
    	User readUser = new User();
    	List<String> result = Arrays.asList(line.split(","));
    	readUser.setRealName(result.get(0));
    	readUser.setUserName(result.get(1));
    	readUser.setEmail(result.get(2));
    	readUser.setPassword(result.get(3));
    	readUser.setSecQuestionAnswer(result.get(4));

    	return readUser;
    }
    
    //See other init() functions
    public void initTestPosts() {
    	File postsFile = new File("posts.txt");
    	Scanner postScanner;
		try {
			postScanner = new Scanner(postsFile);
			while (postScanner.hasNextLine()) {
	    		postsArray.add(createPostFromLine(postScanner.nextLine()));
	    	}
			
			initQAndATable();
	    	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    //See other init() functions
    public void initQAndATable() {
    	DefaultTableModel model = (DefaultTableModel) qAndATable.getModel();

    	for (Post iterPost : postsArray) {
    		model.insertRow(qAndATable.getRowCount(), new Object[] {iterPost.getAuthor(), iterPost.getReplyCount(), iterPost.getUpvotes(), "View Post", "View Replies"});
    	}
    	model.fireTableDataChanged();
    	
    }

    public void initReplies() {
        File repliesFile = new File("replies.txt");
        Scanner replyScanner;
        try {
            replyScanner = new Scanner(repliesFile);
            while (replyScanner.hasNextLine()) {

                String[] split = replyScanner.nextLine().split(",");

                for (int i = 0; i < postsArray.size(); i++) {
                    if (postsArray.get(i).getAuthor().equals(split[0]) && postsArray.get(i).getPostContents().equals(split[1])) {
                        Reply reply = new Reply();
                        reply.setAuthor(split[2]);
                        reply.setUpvotes(Integer.parseInt(split[3]));
                        reply.setPostContents(split[4]);
                        postsArray.get(i).getReplies().add(reply);
                        for (int j = 0; j < reply.getUpvotes(); j++) {
                            for (int k = 0; k < userArray.size(); k++) {
                                if (userArray.get(k).getRealName().equals(split[j + 5])) {
                                    postsArray.get(i).getReplies().get(j).getUpvotedUsers().add(userArray.get(k));
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    //See createUserFromLine or createReviewFromLine
    public Post createPostFromLine(String line) {
    	Post readPost = new Post();
    	List<String> result = Arrays.asList(line.split(","));
    	readPost.setAuthor(result.get(0));
    	readPost.setReplyCount(Integer.parseInt(result.get(1)));
    	readPost.setUpvotes(Integer.parseInt(result.get(2)));
    	readPost.setPostContents(result.get(3));
        for (int i = 0; i < readPost.getUpvotes(); i++) {
            for (int j = 0; j < userArray.size(); j++) {
                if (userArray.get(j).getRealName().equals(result.get(i + 4))) {
                    readPost.getUpvotedUsers().add(userArray.get(j));
                    break;
                }
            }
        }

    	return readPost;
    }
   

}
