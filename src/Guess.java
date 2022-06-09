import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class Guess extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField guess_field;
	private int max=100;
	private int min=0;
	Random random = new Random();
	private int target = min + random.nextInt(max);
	private JTextField guess_number;
	private int clicks=0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guess frame = new Guess();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Guess() {
		setTitle("Number Guessing Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 299);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel guess_label = new JLabel("Guess my number");
		guess_label.setFont(new Font("Arial", Font.PLAIN, 18));
		guess_label.setBounds(175, 75, 161, 32);
		contentPane.add(guess_label);
		
		guess_field = new JTextField();
		guess_field.setFont(new Font("Arial", Font.PLAIN, 16));
		guess_field.setBounds(200, 115, 96, 19);
		contentPane.add(guess_field);
		guess_field.setColumns(10);
		
		JLabel warning = new JLabel("");
		warning.setFont(new Font("Arial", Font.PLAIN, 14));
		warning.setBounds(215, 135, 245, 32);
		contentPane.add(warning);
		
		guess_number = new JTextField();
		guess_number.setFont(new Font("Arial", Font.BOLD, 20));
		guess_number.setEnabled(false);
		guess_number.setBounds(60, 33, 50, 19);
		contentPane.add(guess_number);
		guess_number.setColumns(10);
		
		JButton guess_button = new JButton("Guess");
		guess_button.setForeground(new Color(255, 255, 255));
		guess_button.setBackground(new Color(0, 0, 128));
		guess_button.setFont(new Font("Arial", Font.PLAIN, 15));
		guess_button.setBounds(207, 228, 85, 21);
		contentPane.add(guess_button);
		guess_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				String guessed_number_text = guess_field.getText();
			    try {
		            int guessed_number_int= Integer.parseInt(guessed_number_text); 
			        System.out.println("Guessed Number: " + guessed_number_int);
			        if (guessed_number_int > target && !(guessed_number_int > 100 || guessed_number_int < 0)) {
						warning.setForeground(new Color(255, 0, 0));
						warning.setBounds(224, 135, 245, 32);
		            	warning.setText("Lower");
						clicks++;

		            }
		            else if (guessed_number_int < target && !(guessed_number_int > 100 || guessed_number_int < 0)) {
						warning.setForeground(new Color(255, 0, 0));
						warning.setBounds(230, 135, 245, 32);
		            	warning.setText("Higher");
						clicks++;

		            }
		            else if (guessed_number_int > 100 || guessed_number_int < 0) {
						warning.setForeground(new Color(255, 0, 0));
						warning.setBounds(150, 135, 245, 32);
		            	warning.setText("Enter a number between 0 and 100");
		            }
		            else if (guessed_number_int == target) {
						clicks++;
						warning.setForeground(new Color(0, 180, 0));
						warning.setBounds(218, 135, 245, 32);
						String total_guess_number = String.valueOf(clicks);
						guess_number.setText(total_guess_number);
		            	warning.setText("You Won!");
		            	
		            }

			    } 
			    catch (NumberFormatException e) {
		            	warning.setForeground(new Color(255, 0, 0));
						warning.setBounds(178, 135, 245, 32);
		                warning.setText("Please guess a number"); 		                
		        }
			}
		});	
		
		JButton give_up_button = new JButton("Give Up!");
		give_up_button.setForeground(Color.WHITE);
		give_up_button.setFont(new Font("Arial", Font.PLAIN, 15));
		give_up_button.setBackground(new Color(0, 0, 128));
		give_up_button.setBounds(83, 180, 100, 21);
		contentPane.add(give_up_button);
		give_up_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				String str_target = String.valueOf(target);
				guess_field.setText(str_target);
				guess_field.setBackground(new Color(12,12,12));
				guess_field.setEnabled(false);
				guess_button.setEnabled(false);
				warning.setBounds(210, 135, 245, 32);
				warning.setForeground(new Color(255, 0, 0));
				warning.setText("Game Over!");
				guess_number.setText("");
			}	
		});
		
		JButton new_game_button = new JButton("New Game");
		new_game_button.setForeground(Color.WHITE);
		new_game_button.setFont(new Font("Arial", Font.PLAIN, 15));
		new_game_button.setBackground(new Color(0, 0, 128));
		new_game_button.setBounds(319, 181, 110, 21);
		contentPane.add(new_game_button);
		
		JLabel guess_number_label = new JLabel("Number of guesses");
		guess_number_label.setForeground(new Color(0, 0, 139));
		guess_number_label.setFont(new Font("Arial", Font.BOLD, 16));
		guess_number_label.setBounds(21, 10, 160, 20);
		contentPane.add(guess_number_label);
		
		new_game_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent a) {
				target = min + random.nextInt(max);
				contentPane.removeAll();
				contentPane.repaint();
				contentPane.add(guess_label);
				contentPane.add(warning);
				contentPane.add(give_up_button);
				contentPane.add(guess_button);
				contentPane.add(guess_field);
				guess_field.setText("");
				guess_field.setBackground(new Color(255,255,255));
				guess_field.setEnabled(true);
				warning.setText("");
				guess_button.setEnabled(true);
				contentPane.add(new_game_button);
				contentPane.add(guess_number);
				contentPane.add(guess_number_label);
				guess_number.setText("");
				clicks=0;

			}	
		});		
	}
}			